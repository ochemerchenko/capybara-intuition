package com.capybara.intuition.app.billing.handlers

import com.capybara.intuition.app.billing.ui.model.BillingState

fun handleUserCanceled (

): BillingState {
    // Користувач просто закрив діалог — тихо повертаємо стан Idle
    return BillingState.Idle
}