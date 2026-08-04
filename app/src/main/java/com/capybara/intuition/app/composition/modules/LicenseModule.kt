package com.capybara.intuition.app.composition.modules

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

    val capabilities = licenseRepository.capabilities

    /*
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

     */
}