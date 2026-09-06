package com.capybara.intuition.services.billing.handlers.handleOk

import android.util.Log
import com.android.billingclient.api.Purchase
import com.capybara.intuition.services.billing.ui.model.BillingState

fun handleUnknownPurchaseState(
    purchase: Purchase
): BillingState {

    Log.w(
        "Billing",
        "Purchase state is UNSPECIFIED_STATE."
    )

    return BillingState.UnknownResult
}