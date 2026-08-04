package com.capybara.intuition.app.billing.ui


import android.app.Activity
import android.util.Log
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import androidx.fragment.app.FragmentActivity
import com.capybara.intuition.R
import com.capybara.intuition.app.App
import com.capybara.intuition.app.billing.ui.components.PurchaseLoadingSection
import com.capybara.intuition.app.billing.ui.components.PurchaseFailedSection
import com.capybara.intuition.app.billing.ui.components.PurchaseOfferSection
import com.capybara.intuition.app.billing.ui.components.PurchasePendingSection
import com.capybara.intuition.app.billing.ui.components.PurchaseSuccessSection
import com.capybara.intuition.app.billing.ui.components.PurchaseUnknownResultSection
import com.capybara.intuition.app.billing.ui.model.BillingState
import com.capybara.intuition.app.billing.ui.model.BillingUiState
import com.capybara.intuition.license.domain.LicenseType



@Composable
fun LicensePurchaseDialog(
    targetLicense: LicenseType,
    onDismiss: () -> Unit
) {
    val app = LocalContext.current.applicationContext as App
    val container = app.appContainer
    val activity = LocalContext.current as Activity

    val billingManager = container.googlePlayBillingManager

    val billingUiState by billingManager.billingUiState.collectAsState()

    Dialog(
        onDismissRequest = {
            billingManager.resetState()
            onDismiss()
        }
    ) {
        Card {

            when (val state = billingUiState) {

                // =========================================
                // OFFER
                // =========================================
                BillingUiState.Idle -> {
                    Log.d("Billing", "Before call PurchaseOfferSection()")
                    PurchaseOfferSection(
                        licenseType = targetLicense,
                        onBuyClick = {
                            Log.d("Billing", "Before called: onBuyClick()")
                            activity?.let {
                                billingManager.startPurchase(
                                    it,
                                    targetLicense
                                )
                            }
                            Log.d("Billing", "CalledPurchaseOfferSection.onBuyClick")
                        }
                    )
                }

                // =========================================
                // LOADING
                // =========================================
                BillingUiState.Loading -> {

                    PurchaseLoadingSection(
                        message = stringResource(R.string.billing_connection_setup)
                    )
                }

                // =========================================
                // PENDING
                // =========================================
                BillingUiState.Pending -> {

                    PurchasePendingSection(
                        onCloseClick = {
                            billingManager.resetState()
                            onDismiss()
                        }
                    )
                }

                // =========================================
                // UNKNOWN RESULT
                // =========================================
                BillingUiState.UnknownResult -> {

                    PurchaseUnknownResultSection(
                        //onCheckPurchaseClick = {
                        //    billingManager.refreshPurchases()
                        //},
                        onCloseClick = {
                            billingManager.resetState()
                            onDismiss()
                        }
                    )
                }

                // =========================================
                // SUCCESS
                // =========================================
                BillingUiState.Success -> {

                    PurchaseSuccessSection(
                        onCloseClick = {
                            billingManager.resetState()
                            onDismiss()
                        }
                    )
                }

                // =========================================
                // ERROR
                // =========================================
                is BillingUiState.Error -> {

                    PurchaseFailedSection(
                        errorMessage = state.message,
                        onRetryClick = {
                            activity?.let {
                                billingManager.startPurchase(
                                    it,
                                    targetLicense
                                )
                            }
                        },
                        onCloseClick = {
                            billingManager.resetState()
                            onDismiss()
                        }
                    )
                }
            }
        }
    }
}

