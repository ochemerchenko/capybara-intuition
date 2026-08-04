package com.capybara.intuition.app.license.data.repository

import com.capybara.intuition.app.BuildConfiguration
import com.capybara.intuition.license.capability.RuntimeCapabilities
import com.capybara.intuition.license.capability.configs.ProductionFreeCapabilities
import com.capybara.intuition.license.capability.configs.ProductionPremiumCapabilities
import com.capybara.intuition.license.capability.configs.TestFreeCapabilities
import com.capybara.intuition.license.capability.configs.TestPremiumCapabilities
import com.capybara.intuition.license.data.datastore.LicenseDataStore
import com.capybara.intuition.license.domain.CapabilityEnvironment
import com.capybara.intuition.license.domain.LicenseType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class LicenseRepository(
    private val licenseDataStore: LicenseDataStore,
) {

    private val scope = CoroutineScope(
        SupervisorJob() + Dispatchers.IO
    )

    private val _currentLicense =
        MutableStateFlow(LicenseType.FREE)
    val currentLicense: StateFlow<LicenseType> =
        _currentLicense.asStateFlow()

    private val _lastConfirmedLicense =
        MutableStateFlow(LicenseType.FREE)
    val lastConfirmedLicense: StateFlow<LicenseType> =
        _lastConfirmedLicense.asStateFlow()

    private val _capabilities = MutableStateFlow(
        resolveCapabilities(LicenseType.FREE)
    )

    val capabilities: StateFlow<RuntimeCapabilities> = _capabilities.asStateFlow()

    init {

        scope.launch {

            licenseDataStore
                .observe(
                    LicenseDataStore.LICENSE_KEY
                )
                .collect { license ->

                    if (license != null) {
                        _currentLicense.value = license
                    }
                }
        }

        scope.launch {

            licenseDataStore
                .observe(
                    LicenseDataStore.LAST_CONFIRMED_LICENSE_KEY
                )
                .collect { license ->

                    if (license != null) {
                        _lastConfirmedLicense.value = license
                    }
                }
        }
    }

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
