package com.capybara.intuition.services.billing.handlers

import com.android.billingclient.api.Purchase
import com.capybara.intuition.services.billing.handlers.handleOk.handlePendingPurchase
import com.capybara.intuition.services.billing.handlers.handleOk.handleSuccessfullPurchase
import com.capybara.intuition.services.billing.handlers.handleOk.handleUnexpectedEmptyPurchases
import com.capybara.intuition.services.billing.handlers.handleOk.handleUnexpectedPurchaseState
import com.capybara.intuition.services.billing.handlers.handleOk.handleUnknownPurchaseState
import com.capybara.intuition.services.billing.ui.model.BillingState

fun handleGoogleOk (
    purchases: MutableList<Purchase>?
): BillingState {


    if (purchases.isNullOrEmpty()) {
        return handleUnexpectedEmptyPurchases()
    }

    val purchase = purchases.first()

    return when (purchase.purchaseState) {

        Purchase.PurchaseState.PENDING -> handlePendingPurchase(purchase)
        Purchase.PurchaseState.PURCHASED -> handleSuccessfullPurchase(purchase)
        Purchase.PurchaseState.UNSPECIFIED_STATE -> handleUnknownPurchaseState(purchase)
        else ->
            handleUnexpectedPurchaseState(purchase)
    }

}