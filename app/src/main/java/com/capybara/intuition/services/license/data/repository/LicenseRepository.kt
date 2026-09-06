package com.capybara.intuition.services.license.data.repository

import com.capybara.intuition.app.BuildConfiguration
import com.capybara.intuition.services.license.capability.RuntimeCapabilities
import com.capybara.intuition.services.license.capability.configs.ProductionFreeCapabilities
import com.capybara.intuition.services.license.capability.configs.ProductionPremiumCapabilities
import com.capybara.intuition.services.license.capability.configs.TestFreeCapabilities
import com.capybara.intuition.services.license.capability.configs.TestPremiumCapabilities
import com.capybara.intuition.services.license.data.datastore.LicenseDataStore
import com.capybara.intuition.services.license.domain.CapabilityEnvironment
import com.capybara.intuition.services.license.domain.LicenseType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class LicenseRepository(
    private val licenseDataStore: LicenseDataStore,
) {

    private val scope = CoroutineScope(
        SupervisorJob() + Dispatchers.IO
    )

    val currentLicense: StateFlow<LicenseType> =
        licenseDataStore
            .observe(LicenseDataStore.LICENSE_KEY)
            .map { it ?: LicenseType.FREE }
            .stateIn(
                scope = scope,
                started = SharingStarted.Eagerly,
                initialValue = LicenseType.FREE
            )

    val lastConfirmedLicense: StateFlow<LicenseType> =
        licenseDataStore
            .observe(LicenseDataStore.LAST_CONFIRMED_LICENSE_KEY)
            .map { it ?: LicenseType.FREE }
            .stateIn(
                scope = scope,
                started = SharingStarted.Eagerly,
                initialValue = LicenseType.FREE
            )


    val capabilities: StateFlow<RuntimeCapabilities> = licenseDataStore
        .observe(LicenseDataStore.LICENSE_KEY)
        .map { license ->
            resolveCapabilities(license ?: LicenseType.FREE)
        }
        .stateIn(
            scope = scope,
            started = SharingStarted.Eagerly, // або WhileSubscribed(5000)
            initialValue = resolveCapabilities(LicenseType.FREE)
        )

    suspend fun setLicense(
        license: LicenseType
    ) {
        licenseDataStore.edit(
            LicenseDataStore.LICENSE_KEY,
            license
        )
    }

    suspend fun setLastConfirmedLicense(
        license: LicenseType
    ) {
        licenseDataStore.edit(
            LicenseDataStore.LAST_CONFIRMED_LICENSE_KEY,
            license
        )
    }

    private fun resolveCapabilities(
        licenseType: LicenseType
    ): RuntimeCapabilities =
        when (BuildConfiguration.environment) {

            CapabilityEnvironment.TEST ->
                when (licenseType) {
                    LicenseType.FREE -> TestFreeCapabilities
                    LicenseType.PREMIUM -> TestPremiumCapabilities
                }

            CapabilityEnvironment.PRODUCTION ->
                when (licenseType) {
                    LicenseType.FREE -> ProductionFreeCapabilities
                    LicenseType.PREMIUM -> ProductionPremiumCapabilities
                }
        }
}
