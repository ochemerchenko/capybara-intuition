package com.capybara.intuition.app.jobs

import android.content.Context
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.capybara.intuition.core.data.DataStoreAdapter
import com.capybara.intuition.services.billing.GooglePlayBillingManager
import com.capybara.intuition.services.installation.data.local.db.dao.InstallationDao
import com.capybara.intuition.services.updater.data.AppUpdateManager
import com.capybara.intuition.services.database.domain.DatabaseCleanupManager
import com.capybara.intuition.services.sync.domain.exportGameData
import com.capybara.intuition.services.sync.domain.exportNavigation
import com.capybara.intuition.services.sync.domain.uploadExport
import com.capybara.intuition.services.telemetry.data.repository.TelemetryRepository
import com.capybara.intuition.features.game.data.local.db.dao.GameRoundDao
import com.capybara.intuition.features.game.data.local.db.dao.SessionDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class JobManager(
    private val scope: CoroutineScope,
    private val context: Context,
    private val billingManager: GooglePlayBillingManager,

    private val installationDao: InstallationDao,
    private val sessionDao: SessionDao,
    private val gameRoundDao: GameRoundDao,
    private val telemetryRepository: TelemetryRepository,

    private val databaseCleanupManager: DatabaseCleanupManager,
    private val appUpdateManager: AppUpdateManager,
    private val dataStoreAdapter: DataStoreAdapter
) : DefaultLifecycleObserver {


    override fun onStart(owner: LifecycleOwner) {
        Log.d(TAG, "Starting synchronization...")
        scope.launch {
            synchronize()
            Log.d(TAG, "Synchronization finished.")
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        billingManager.stop()
    }

    suspend fun synchronize() {

        executeSafely("Firebase Authentication") {

            Log.d(
                "EXPORT",
                "Firebase Authentication: START"
            )

            val result = FirebaseAuth
                .getInstance()
                .signInAnonymously()
                .await()

            val user = result.user
                ?: error("Firebase Authentication succeeded, but user is null")

            Log.d(
                "EXPORT",
                "Firebase Authentication: SUCCESS, uid=${user.uid}"
            )
        }

        executeSafely("Google Play Billing") {
            billingManager.start()
        }

        executeSafely("Game data local export") {
            exportGameData(
                context = context,
                installationDao = installationDao,
                sessionDao = sessionDao,
                roundDao = gameRoundDao,
                dataStoreAdapter = dataStoreAdapter
            )
        }

        executeSafely("Navigation data local export") {
            exportNavigation(
                context = context,
                installationDao = installationDao,
                telemetryRepository = telemetryRepository
            )
        }

        executeSafely("Game data export upload") {
            uploadExport(
                context = context,
            )
        }

        executeSafely("Database Cleanup") {
            databaseCleanupManager.cleanup()
        }

        executeSafely("Check available updates") {
            appUpdateManager.synchronize()
        }
    }

    private suspend fun executeSafely(
        taskName: String,
        task: suspend () -> Unit
    ) {
        try {
            task()
        } catch (e: Exception) {
            Log.e(
                TAG,
                "Task \"$taskName\" failed.",
                e
            )
        }
    }

    private companion object {
        const val TAG = "Billing"
    }
}
