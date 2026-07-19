package com.capybara.intuition.app.billing.handlers.handleOk

import android.util.Log
import com.android.billingclient.api.Purchase
import com.capybara.intuition.app.billing.ui.model.BillingState

fun handleUnexpectedPurchaseState(
    purchase: Purchase
): BillingState {

    Log.e(
        "Billing",
        "Unexpected PurchaseState: ${purchase.purchaseState}"
    )

    return BillingState.UnknownResult
}