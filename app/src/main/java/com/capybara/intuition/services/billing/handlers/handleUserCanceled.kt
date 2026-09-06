package com.capybara.intuition.services.billing.handlers

import com.capybara.intuition.services.billing.ui.model.BillingState

fun handleUserCanceled (

): BillingState {
    // Користувач просто закрив діалог — тихо повертаємо стан Idle
    return BillingState.Idle
}