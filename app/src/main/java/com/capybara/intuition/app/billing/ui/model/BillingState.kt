package com.capybara.intuition.app.billing.ui.model

sealed interface BillingState {

    data object Idle : BillingState
    data object Loading : BillingState
    data object UnknownResult : BillingState
    data object Pending : BillingState
    data object LicenseActivationRequired : BillingState
    data object Success : BillingState
    data class Error(
        val message: String
    ) : BillingState
}