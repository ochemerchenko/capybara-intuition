package com.capybara.intuition.app.billing.handlers.handleOk

import com.android.billingclient.api.Purchase
import com.capybara.intuition.app.billing.ui.model.BillingState

fun handleUnexpectedEmptyPurchases(): BillingState = BillingState.UnknownResult