package com.capybara.intuition.services.bootstrap.di

import android.content.Context
import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.core.data.DataStoreAdapter
import com.capybara.intuition.services.bootstrap.data.BootstrapDataStore
import com.capybara.intuition.services.bootstrap.data.BootstrapStateRepository
import com.capybara.intuition.services.bootstrap.domain.BootstrapLoader
import com.capybara.intuition.services.installation.di.InstallationModule
import com.capybara.intuition.services.license.di.LicenseModule

class BootstrapModule(
    context: Context,
    database: AppDatabase,
    dataStoreAdapter: DataStoreAdapter,
    installation: InstallationModule,
    license: LicenseModule
) {
    private val bootstrapDataStore = BootstrapDataStore(
        dataStore = dataStoreAdapter
    )

    val bootstrapStateRepository = BootstrapStateRepository(
        bootstrapDataStore = bootstrapDataStore
    )

    val bootstrapLoader = BootstrapLoader(
        context = context,
        database = database,
        installationRepository = installation.installationRepository,
        capabilities = license.capabilities.value,
        licenseRepository = license.licenseRepository,
        bootstrapStateRepository = bootstrapStateRepository
    )
}
