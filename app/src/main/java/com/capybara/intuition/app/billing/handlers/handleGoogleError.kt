package com.capybara.intuition.app.billing.handlers

import com.android.billingclient.api.BillingResult
import com.capybara.intuition.app.billing.ui.model.BillingState

fun handleGoogleError (
    billingResult: BillingResult

): BillingState {
    // Усі інші коди — це помилки різного характеру
    return BillingState.Error(
        billingResult.debugMessage
    )
}