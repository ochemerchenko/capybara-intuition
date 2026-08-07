package com.capybara.intuition.app.jobs.appUpdater

import android.app.Activity
import android.content.Context
import android.util.Log
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.appupdate.AppUpdateOptions
import com.google.android.play.core.install.InstallStateUpdatedListener
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

class AppUpdateManager (
    private val context: Context
){

    val TAG = "AppUpdateManager"

    private val appContext = context.applicationContext
    private val googleAppUpdateManager = AppUpdateManagerFactory.create (
        appContext
    )

    private val _appUpdateProcessState =
        MutableStateFlow<AppUpdateInfo?>(null)
    val appUpdateProcessState = _appUpdateProcessState.asStateFlow()

    private var isListenerRegistered = false

    private val installStateUpdatedListener = InstallStateUpdatedListener { state ->
        Log.d(TAG, "InstallStatus=${state.installStatus()}, error=${state.installErrorCode()}")
        when (state.installStatus()) {
            InstallStatus.DOWNLOADED,
            InstallStatus.DOWNLOADING,
            InstallStatus.FAILED,
            InstallStatus.CANCELED -> {
                // При будь-якій фоновій зміні стану оновлюємо AppUpdateInfo у Flow
                refreshUpdateInfo()
            }
            else -> Unit
        }
    }



    suspend fun synchronize() {
        // 1. Отримуємо свіжий стан від Google Play Services
        val updateInfo = checkUpdates()

        if (updateInfo == null) {
            _appUpdateProcessState.value = null
            return
        }

        // 2. Аналізуємо повернений стан
        when (updateInfo.installStatus()) {
            InstallStatus.DOWNLOADED -> {
                // Файл ВЖЕ завантажено раніше (поки додаток був закритий або в минулій сесії)
                try {
                    // Пробуємо одразу перезапустити додаток для встановлення
                    googleAppUpdateManager.completeUpdate().await()
                    return
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to complete update automatically on start", e)
                    // Якщо автоматичний перезапуск впав (наприклад, заблоковано системою),
                    // зберігаємо updateInfo, щоб ViewModel показав стан UpdateDownloaded
                    _appUpdateProcessState.value = updateInfo
                }
            }

            InstallStatus.DOWNLOADING,
            InstallStatus.PENDING,
            InstallStatus.INSTALLING -> {
                // Оновлення скачується ПРЯМО ЗАРАЗ у фоновому сервісі Google Play!
                // Обов'язково підключаємо слухач, щоб спіймати момент переходу в DOWNLOADED
                registerInstallStateListener()
                _appUpdateProcessState.value = updateInfo
            }

            else -> {
                // Для всіх інших випадків (UNKNOWN, FAILED, CANCELED або якщо завантаження ще не починалося)
                _appUpdateProcessState.value = updateInfo
            }
        }
    }

    private suspend fun checkUpdates(): AppUpdateInfo? {
        return try {
            googleAppUpdateManager.appUpdateInfo.await()
        } catch (exception: Exception) {
            Log.w(TAG, "Unable to check updates.", exception)
            null
        }
    }

    private fun refreshUpdateInfo() {
        googleAppUpdateManager.appUpdateInfo.addOnSuccessListener { info ->
            _appUpdateProcessState.value = info
        }
    }

    fun registerInstallStateListener() {
        if (!isListenerRegistered) {
            googleAppUpdateManager.registerListener(installStateUpdatedListener)
            isListenerRegistered = true
        }
    }

    fun unregisterInstallStateListener() {
        if (isListenerRegistered) {
            googleAppUpdateManager.unregisterListener(installStateUpdatedListener)
            isListenerRegistered = false
        }
    }




    /**
     * Старт процесу оновлення за запитом користувача
     */
    suspend fun startUpdateByUser(activity: Activity) {
        // 1. Беремо актуальний чи свіжий AppUpdateInfo
        val updateInfo = _appUpdateProcessState.value ?: checkUpdates() ?: run {
            Log.w(TAG, "Unable to get AppUpdateInfo to start update.")
            return
        }

        // 2. Перевіряємо, чи доступне оновлення саме через Flexible Flow
        if (updateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
            updateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
        ) {
            // Спочатку реєструємо слухач, щоб не пропустити початок завантаження
            registerInstallStateListener()

            try {
                val options = AppUpdateOptions.newBuilder(AppUpdateType.FLEXIBLE).build()

                // Запускаємо системний UI від Google Play
                val resultCode = googleAppUpdateManager
                    .startUpdateFlow(updateInfo, activity, options)
                    .await()

                when (resultCode) {
                    Activity.RESULT_OK -> {
                        Log.d(TAG, "Update flow started successfully by user.")
                        // Google перейняв керування, чекаємо на події від installStateUpdatedListener
                    }
                    Activity.RESULT_CANCELED -> {
                        Log.i(TAG, "Update flow canceled by user.")
                        unregisterInstallStateListener()
                    }
                    ActivityResult.RESULT_IN_APP_UPDATE_FAILED -> {
                        Log.w(TAG, "Update flow failed to launch.")
                        unregisterInstallStateListener()
                        refreshUpdateInfo()
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Exception while starting update flow", e)
                unregisterInstallStateListener()
                refreshUpdateInfo()
            }
        } else {
            Log.w(TAG, "Flexible update is not available or not allowed.")
        }
    }


    suspend fun restartAfterUpdateByUser() {
        try {
            googleAppUpdateManager.completeUpdate().await()
        } catch (e: Exception) {
            Log.e(TAG, "Failed to complete update and restart app", e)
        }
    }






}