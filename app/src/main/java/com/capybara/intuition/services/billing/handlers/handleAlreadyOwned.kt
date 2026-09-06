package com.capybara.intuition.services.billing.handlers

import com.capybara.intuition.services.billing.GooglePlayBillingManager
import com.capybara.intuition.services.billing.model.BillingSynchronizationResult
import com.capybara.intuition.services.billing.ui.model.BillingState
import com.capybara.intuition.services.license.domain.LicenseType

suspend fun handleAlreadyOwned(
    startSynchronization: suspend () -> BillingSynchronizationResult
): BillingState {

    val synchronizationResult = startSynchronization()

    if (!synchronizationResult.connected) {
        return BillingState.Error(
            "Failed to synchronize with Google Play."
        )
    }

    return if (
        synchronizationResult.licenses[LicenseType.PREMIUM] == true
    ) {
        BillingState.Success
    } else {
        BillingState.Error(
            "Premium license is not installed."
        )
    }
}