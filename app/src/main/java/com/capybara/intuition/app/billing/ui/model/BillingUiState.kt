package com.capybara.intuition.app.billing.ui.model

sealed interface BillingUiState {

    data object Idle : BillingUiState
    data object Loading : BillingUiState
    data object Pending : BillingUiState
    data object UnknownResult : BillingUiState
    data object Success : BillingUiState

    data class Error(
        val message: String
    ) : BillingUiState
}