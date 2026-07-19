package com.capybara.intuition.app.billing.handlers

import com.capybara.intuition.app.billing.ui.model.BillingState

fun handleAlreadyOwned (

): BillingState {
    return BillingState.LicenseActivationRequired
}