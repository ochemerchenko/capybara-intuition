package com.capybara.intuition.services.license.di

import com.capybara.intuition.core.data.DataStoreAdapter
import com.capybara.intuition.services.license.data.datastore.LicenseDataStore
import com.capybara.intuition.services.license.data.datastore.SynchronizationDataStore
import com.capybara.intuition.services.license.data.repository.LicenseRepository
import com.capybara.intuition.services.license.data.repository.SynchronizationRepository


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