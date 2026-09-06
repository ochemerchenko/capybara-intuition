package com.capybara.intuition.app.composition


import DatabaseRetentionRepository
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.capybara.intuition.app.composition.modules.GameplayModule
import com.capybara.intuition.app.composition.modules.InstallationModule
import com.capybara.intuition.app.composition.modules.LicenseModule
import com.capybara.intuition.app.composition.modules.ProfileModule
import com.capybara.intuition.app.composition.modules.StatisticsModule
import com.capybara.intuition.app.composition.modules.TesterModule
import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.app.DataStoreAdapter
import com.capybara.intuition.app.billing.GooglePlayBillingManager
import com.capybara.intuition.app.bootstrap.BootstrapLoader
import com.capybara.intuition.app.composition.modules.AchievementsModule
import com.capybara.intuition.app.composition.modules.SettingsModule
import com.capybara.intuition.app.composition.modules.TelemetryModule
import com.capybara.intuition.app.jobs.db_cleaner.DatabaseRetentionDataStore
import com.capybara.intuition.app.jobs.db_cleaner.DatabaseCleanupManager
import com.capybara.intuition.app.jobs.JobManager
import com.capybara.intuition.app.jobs.appUpdater.AppUpdateManager
import com.capybara.intuition.app.jobs.appUpdater.AppUpdateViewModel
import kotlinx.coroutines.CoroutineScope


class AppContainer(
    context: Context,
    appScope: CoroutineScope
) {

    // =====================================================
    // DATABASE
    // =====================================================
    val database: AppDatabase =
        Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "intuition_db"
        )
            .addCallback(
                object : RoomDatabase.Callback() {
                    override fun onOpen(
                        db: SupportSQLiteDatabase
                    ) {
                        super.onOpen(db)
                        db
                            .setForeignKeyConstraintsEnabled(
                                true
                            )
                    }
                }
            )
            .fallbackToDestructiveMigration()
            .build()

    val dataStoreAdapter = DataStoreAdapter(
        context = context
    )

    val license = LicenseModule(
        dataStoreAdapter = dataStoreAdapter
    )

    val googlePlayBillingManager =
        GooglePlayBillingManager(
            context = context,
            licenseRepository = license.licenseRepository,
            synchronizationRepository = license.synchronizationRepository
        )

    val databaseRetentionDataStore =
        DatabaseRetentionDataStore(dataStoreAdapter)

    val databaseRetentionRepository =
        DatabaseRetentionRepository(
            databaseRetentionDataStore
        )

    val databaseCleanupManager = DatabaseCleanupManager(
        sessionDao = database.sessionDao(),
        databaseRetentionRepository = databaseRetentionRepository
    )

    val appUpdateManager = AppUpdateManager(
        context = context
    )
    val appUpdateViewModel = AppUpdateViewModel(
        appUpdateManager
    )

    // =====================================================
    // MODULES
    // =====================================================
    val installation = InstallationModule(
        database = database,
        context = context,
        dataStoreAdapter
    )

    val profile = ProfileModule(
        database = database
    )

    val settings = SettingsModule(
        databaseRetentionRepository = databaseRetentionRepository
    )

    val gameplay = GameplayModule(
        database = database
    )

    val statistics = StatisticsModule(
        database = database
    )

    val achievements = AchievementsModule(
        database = database
    )

    val tester = TesterModule(
        database = database
    )

    val telemetry = TelemetryModule(
        database = database
    )

    val jobManager = JobManager(
        scope = appScope,
        billingManager = googlePlayBillingManager,
        context = context,

        installationDao = installation.installationDao,
        sessionDao = gameplay.sessionDao,
        gameRoundDao = gameplay.roundDao,
        telemetryRepository = telemetry.telemetryRepository,

        databaseCleanupManager = databaseCleanupManager,
        appUpdateManager = appUpdateManager,

        dataStoreAdapter = dataStoreAdapter
    )




    // =====================================================
    // BOOTSTRAP
    // =====================================================
    val bootstrapLoader = BootstrapLoader(
        context = context,
        database = database,
        capabilities = license.capabilities.value,
        installationRepository = installation.installationRepository,
        licenseRepository = license.licenseRepository,
        bootstrapStateRepository = installation.bootstrapStateRepository
    )


}
