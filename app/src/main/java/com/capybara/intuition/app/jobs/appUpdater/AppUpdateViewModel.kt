package com.capybara.intuition.app.jobs.appUpdater

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capybara.intuition.app.jobs.appUpdater.model.UpdateUiStatus
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.InstallStatus
import com.google.android.play.core.install.model.UpdateAvailability
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppUpdateViewModel(
    private val appUpdateManager: AppUpdateManager
) : ViewModel() {

    /**
     * Потік стану UI для TopNavigation та AppUpdateDialog
     */
    val appUpdateUiState: StateFlow<UpdateUiStatus> = appUpdateManager.appUpdateProcessState
        .map { updateInfo ->
            mapToUpdateUiStatus(updateInfo)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UpdateUiStatus.Checking
        )

    /**
     * Мапінг внутрішнього стану AppUpdateInfo у ваш enum UpdateUiStatus
     */
    private fun mapToUpdateUiStatus(info: AppUpdateInfo?): UpdateUiStatus {
        if (info == null) {
            return UpdateUiStatus.NoUpdateAvailable
        }

        return when (info.installStatus()) {
            InstallStatus.DOWNLOADED -> UpdateUiStatus.UpdateDownloaded

            InstallStatus.DOWNLOADING,
            InstallStatus.PENDING,
            InstallStatus.INSTALLING -> UpdateUiStatus.UpdateInProgress

            InstallStatus.FAILED -> UpdateUiStatus.RetryNeeded

            else -> {
                if (info.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE &&
                    info.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)
                ) {
                    UpdateUiStatus.UpdateAvailable
                } else {
                    UpdateUiStatus.NoUpdateAvailable
                }
            }
        }
    }

    // =====================================================
    // UI ACTIONS (Викликаються з AppUpdateDialog)
    // =====================================================

    /**
     * Старт скачування оновлення у фоні.
     * Потрібно передати Activity (зазвичай через LocalContext.current as Activity в Composable).
     */
    fun onUpdate(activity: Activity) {
        viewModelScope.launch {
            appUpdateManager.startUpdateByUser(activity)
        }
    }

    /**
     * Завершення оновлення та перезапуск додатка
     */
    fun onRestart() {
        viewModelScope.launch {
            appUpdateManager.restartAfterUpdateByUser()
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Очищаємо слухач для запобігання витокам пам'яті
        appUpdateManager.unregisterInstallStateListener()
    }
}