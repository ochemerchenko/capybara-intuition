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

    val currentLicense = licenseRepository.currentLicense

    val lastConfirmedLicense = licenseRepository.lastConfirmedLicense

}