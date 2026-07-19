package com.capybara.intuition.app

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.capybara.intuition.app.composition.AppContainer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class App : Application() {

    lateinit var appContainer: AppContainer
        private set

    private val appScope = CoroutineScope(
        SupervisorJob() + Dispatchers.IO
    )

    override fun onCreate() {
        super.onCreate()

        appContainer = AppContainer(
            context = this,
            appScope = appScope

        )

        // =========================================
        // Application initialization
        // =========================================
        appScope.launch {
            try {

                // =========================================
                // Installation
                // =========================================
                appContainer
                    .installation
                    .installationInitializer
                    .ensureInstallationExists()

                // =========================================
                // Bootstrap
                // =========================================
                appContainer
                    .bootstrapLoader
                    .bootstrap()

                // =========================================
                // Google Play Billing
                // =========================================
                appContainer
                    .googlePlayBillingManager
                    .start()

                //appContainer
                //    .googlePlayBillingManager
                //    .stop()

                // =========================================
                // Billing lifecycle
                // =========================================
                withContext(Dispatchers.Main) {
                    ProcessLifecycleOwner
                        .get()
                        .lifecycle
                        .addObserver(
                            appContainer.jobManager
                            //(
                                //billingManager = appContainer.googlePlayBillingManager,
                                //scope = appScope
                            //)
                        )
                }


            } catch (e: Exception) {
                // TODO Log initialization error
            }
        }
    }
}


/*
class App : Application() {

    lateinit var appContainer: AppContainer
        private set

    private val appScope = CoroutineScope(
        SupervisorJob() + Dispatchers.IO
    )

    override fun onCreate() {
        super.onCreate()

        appContainer = AppContainer(this)

        appScope.launch {
            try {
                // =========================================
                // Installation
                // =========================================
                appContainer
                    .installation
                    .installationInitializer
                    .ensureInstallationExists()

                // =========================================
                // Bootstrap
                // =========================================
                appContainer
                    .bootstrapLoader
                    .bootstrap()

                // =========================================
                // Google Play Billing
                // =========================================
                appContainer
                    .googlePlayBillingManager
                    .start()

                // =========================================
                // Startup synchronization
                // =========================================
                JobManager(
                    billingManager = appContainer.googlePlayBillingManager
                ).synchronize()

            } catch (e: Exception) {
                // TODO Log initialization error
            }
        }
    }
}



 */