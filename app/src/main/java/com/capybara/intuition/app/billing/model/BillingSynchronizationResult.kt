package com.capybara.intuition.app.billing.model

import com.capybara.intuition.license.domain.LicenseType

data class BillingSynchronizationResult(
    val connected: Boolean,
    val licenses: Map<LicenseType, Boolean>
)