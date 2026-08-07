package com.capybara.intuition.app.jobs

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.capybara.intuition.app.billing.GooglePlayBillingManager
import com.capybara.intuition.app.jobs.appUpdater.AppUpdateManager
import com.capybara.intuition.app.jobs.db_cleaner.DatabaseCleanupManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class JobManager(
    private val scope: CoroutineScope,
    private val billingManager: GooglePlayBillingManager,
    // private val historicalExportManager: HistoricalExportManager,
    // private val databaseCleanupManager: DatabaseCleanupManager,
    private val databaseCleanupManager: DatabaseCleanupManager,
    private val appUpdateManager: AppUpdateManager
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
        // historicalExportManager.stop()
        // databaseCleanupManager.stop()
    }

    suspend fun synchronize() {
        executeSafely("Google Play Billing") {
            billingManager.start()
        }

        // executeSafely("Historical Export") {
        //     historicalExportManager.synchronize()
        // }

        // executeSafely("Database Cleanup") {
        //     databaseCleanupManager.cleanup()
        // }

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