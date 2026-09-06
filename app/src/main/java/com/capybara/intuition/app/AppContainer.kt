package com.capybara.intuition.app

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.app.jobs.JobManager
import com.capybara.intuition.core.data.DataStoreAdapter
import com.capybara.intuition.features.achievements.di.AchievementsModule
import com.capybara.intuition.features.game.di.GameplayModule
import com.capybara.intuition.features.profile.di.ProfileModule
import com.capybara.intuition.features.profile.di.SettingsModule
import com.capybara.intuition.features.profile.di.TesterModule
import com.capybara.intuition.features.statistics.di.StatisticsModule
import com.capybara.intuition.services.billing.di.BillingModule
import com.capybara.intuition.services.bootstrap.di.BootstrapModule
import com.capybara.intuition.services.database.di.DatabaseModule
import com.capybara.intuition.services.installation.di.InstallationModule
import com.capybara.intuition.services.license.di.LicenseModule
import com.capybara.intuition.services.telemetry.di.TelemetryModule
import com.capybara.intuition.services.updater.di.UpdaterModule
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

    val billing = BillingModule(
        context = context,
        license = license
    )

    val googlePlayBillingManager = billing.googlePlayBillingManager

    val storage = DatabaseModule(
        dataStoreAdapter = dataStoreAdapter,
        sessionDao = database.sessionDao()
    )

    val databaseRetentionRepository = storage.databaseRetentionRepository
    val databaseCleanupManager = storage.databaseCleanupManager

    val updater = UpdaterModule(
        context = context
    )

    val appUpdateManager = updater.appUpdateManager
    val appUpdateViewModel = updater.appUpdateViewModel

    // =====================================================
    // MODULES
    // =====================================================
    val installation = InstallationModule(
        database = database,
        context = context,
        dataStoreAdapter = dataStoreAdapter
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

    val bootstrap = BootstrapModule(
        context = context,
        database = database,
        dataStoreAdapter = dataStoreAdapter,
        installation = installation,
        license = license
    )

    val bootstrapLoader = bootstrap.bootstrapLoader
    val bootstrapStateRepository = bootstrap.bootstrapStateRepository

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


}
