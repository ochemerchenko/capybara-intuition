package com.capybara.intuition.app.composition.modules

import com.capybara.intuition.app.BuildConfiguration
import com.capybara.intuition.license.capability.RuntimeCapabilities
import com.capybara.intuition.license.capability.configs.ProductionFreeCapabilities
import com.capybara.intuition.license.capability.configs.ProductionPremiumCapabilities
import com.capybara.intuition.license.capability.configs.TestFreeCapabilities
import com.capybara.intuition.license.capability.configs.TestPremiumCapabilities
import com.capybara.intuition.license.domain.CapabilityEnvironment
import com.capybara.intuition.license.domain.LicenseType
import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.app.DataStoreAdapter
import com.capybara.intuition.app.license.data.datastore.SynchronizationDataStore
import com.capybara.intuition.app.license.data.repository.LicenseRepository
import com.capybara.intuition.app.license.data.repository.SynchronizationRepository
import com.capybara.intuition.license.data.datastore.LicenseDataStore


class LicenseModule(
    dataStoreAdapter: DataStoreAdapter,
) {

    private val licenseDataStore = LicenseDataStore(
        dataStoreAdapter
    )

    val licenseRepository = LicenseRepository(
        licenseDataStore = licenseDataStore,
    )

    private val synchronizationDataStore = SynchronizationDataStore(
        dataStoreAdapter
    )

    val synchronizationRepository = SynchronizationRepository(
        synchronizationDataStore = synchronizationDataStore
    )

    val capabilities: RuntimeCapabilities
        get() =
            when (BuildConfiguration.environment) {

                CapabilityEnvironment.TEST ->
                    when (licenseRepository.currentLicense.value) {
                        LicenseType.FREE -> TestFreeCapabilities
                        LicenseType.PREMIUM -> TestPremiumCapabilities
                    }

                CapabilityEnvironment.PRODUCTION ->
                    when (licenseRepository.currentLicense.value) {
                        LicenseType.FREE -> ProductionFreeCapabilities
                        LicenseType.PREMIUM -> ProductionPremiumCapabilities
                    }
            }
}