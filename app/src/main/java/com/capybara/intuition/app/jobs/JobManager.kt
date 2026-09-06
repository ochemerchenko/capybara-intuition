package com.capybara.intuition.app.jobs

import android.content.Context
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.capybara.intuition.app.DataStoreAdapter
import com.capybara.intuition.app.billing.GooglePlayBillingManager
import com.capybara.intuition.app.bootstrap.installation.data.local.db.dao.InstallationDao
import com.capybara.intuition.app.jobs.appUpdater.AppUpdateManager
import com.capybara.intuition.app.jobs.db_cleaner.DatabaseCleanupManager
import com.capybara.intuition.app.jobs.export.exportGameData
import com.capybara.intuition.app.jobs.export.exportNavigation
import com.capybara.intuition.app.jobs.export.uploadExport
import com.capybara.intuition.app.navigation.telemetry.data.repository.TelemetryRepository
import com.capybara.intuition.game.data.local.db.dao.GameRoundDao
import com.capybara.intuition.game.data.local.db.dao.SessionDao
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
