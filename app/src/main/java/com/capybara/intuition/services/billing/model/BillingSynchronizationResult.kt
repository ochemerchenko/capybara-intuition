package com.capybara.intuition.services.billing.model

import com.capybara.intuition.services.license.domain.LicenseType

data class BillingSynchronizationResult(
    val connected: Boolean,
    val licenses: Map<LicenseType, Boolean>
)