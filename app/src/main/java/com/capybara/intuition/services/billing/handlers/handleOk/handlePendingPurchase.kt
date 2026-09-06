package com.capybara.intuition.services.billing.handlers.handleOk

import com.android.billingclient.api.Purchase
import com.capybara.intuition.services.billing.ui.model.BillingState

fun handlePendingPurchase(
    purchase: Purchase
): BillingState {
    return BillingState.Pending
}