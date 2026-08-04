package com.capybara.intuition.app.billing.handlers

import com.capybara.intuition.app.billing.GooglePlayBillingManager
import com.capybara.intuition.app.billing.model.BillingSynchronizationResult
import com.capybara.intuition.app.billing.ui.model.BillingState
import com.capybara.intuition.license.domain.LicenseType

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