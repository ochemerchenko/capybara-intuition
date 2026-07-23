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
import com.capybara.intuition.app.composition.modules.SettingsModule
import com.capybara.intuition.app.jobs.db_cleaner.DatabaseRetentionDataStore
import com.capybara.intuition.app.jobs.DatabaseCleanupManager
import com.capybara.intuition.app.jobs.JobManager
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

    val jobManager =
        JobManager(
            scope = appScope,
            billingManager = googlePlayBillingManager,

            // historicalExportManager = ...
            databaseCleanupManager = databaseCleanupManager,
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

    val settings =
        SettingsModule(
            databaseRetentionRepository = databaseRetentionRepository
        )

    val gameplay = GameplayModule(
        database = database
    )
    val statistics = StatisticsModule(
        database = database
    )
    val tester = TesterModule(
        database = database
    )

    // =====================================================
    // BOOTSTRAP
    // =====================================================
    val bootstrapLoader = BootstrapLoader(
        context = context,
        database = database,
        capabilities = license.capabilities,
        installationRepository = installation.installationRepository,
        licenseRepository = license.licenseRepository,
        bootstrapStateRepository = installation.bootstrapStateRepository
    )


}



