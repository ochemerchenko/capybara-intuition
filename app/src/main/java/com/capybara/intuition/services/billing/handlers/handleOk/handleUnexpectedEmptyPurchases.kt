package com.capybara.intuition.services.billing.handlers.handleOk

import com.capybara.intuition.services.billing.ui.model.BillingState

fun handleUnexpectedEmptyPurchases(): BillingState = BillingState.UnknownResult