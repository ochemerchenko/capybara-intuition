package com.capybara.intuition.app.billing

import android.app.Activity
import android.util.Log
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.acknowledgePurchase
import com.capybara.intuition.app.billing.handlers.handleAlreadyOwned
import com.capybara.intuition.app.billing.handlers.handleGoogleError
import com.capybara.intuition.app.billing.handlers.handleGoogleOk
import com.capybara.intuition.app.billing.handlers.handleUserCanceled
import com.capybara.intuition.app.billing.purchase.createBillingFlowParams
import com.capybara.intuition.app.billing.purchase.createQueryProductDetailsParams
import com.capybara.intuition.app.billing.purchase.resolveLicenseType
import com.capybara.intuition.app.billing.purchase.resolveProductId
import com.capybara.intuition.app.billing.ui.model.BillingState
import com.capybara.intuition.app.license.data.repository.LicenseRepository
import com.capybara.intuition.license.domain.LicenseType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class BillingPurchaseProcessor(
    private val licenseRepository: LicenseRepository,
    private val _billingState: MutableStateFlow<BillingState>
) {

    fun startPurchase(
        billingClient: BillingClient,
        activity: Activity,
        licenseType: LicenseType
    ) {

        Log.d("Billing", "BillingPurchaseProcessor.startPurchase(...)")
        _billingState.value = BillingState.Loading

        val productId = resolveProductId(
            licenseType
        )
        val queryProductDetailParams = createQueryProductDetailsParams(
            productId
        )

        Log.d("Billing", "queryProductDetailParams=$queryProductDetailParams")

        billingClient.queryProductDetailsAsync(
            queryProductDetailParams
        ) { billingResult, productDetailsList ->

            Log.d("Billing", "billingResult; productDetailsList = $billingResult; $productDetailsList")

            if (
                billingResult.responseCode == BillingClient.BillingResponseCode.OK &&
                productDetailsList.isNotEmpty()
            ) {
                val productDetails = productDetailsList.first()
                val billingFlowParams = createBillingFlowParams(
                    productDetails
                )
                val launchResult = billingClient.launchBillingFlow(
                    activity,
                    billingFlowParams
                )
                if (
                    launchResult.responseCode != BillingClient.BillingResponseCode.OK
                ) {
                    _billingState.value =
                        BillingState.Error(
                            launchResult.debugMessage
                        )
                }
            } else {
                _billingState.value = BillingState.Error(
                    billingResult.debugMessage
                )
            }
        }
    }

    fun onPurchasesUpdated(
        billingClient: BillingClient,
        scope: CoroutineScope,
        billingResult: BillingResult,
        purchases: MutableList<Purchase>?
    ) {
        val calculatedBillingState =
            when (billingResult.responseCode) {

                BillingClient.BillingResponseCode.OK -> handleGoogleOk(purchases)
                BillingClient.BillingResponseCode.USER_CANCELED -> handleUserCanceled()
                BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> handleAlreadyOwned()
                else ->
                    handleGoogleError(
                        billingResult
                    )
            }

        when (calculatedBillingState) {

            BillingState.Idle,
            BillingState.Pending,
            BillingState.UnknownResult,
            is BillingState.Error -> {
                _billingState.value = calculatedBillingState
            }

            BillingState.LicenseActivationRequired -> {
                // Intuition підтримує покупку лише однієї ліцензії за один Billing Flow.
                // У цій гілці вже гарантовано purchases.size > 0
                val purchase = purchases!!.first()
                scope.launch {
                    processPurchase(
                        billingClient,
                        scope,
                        purchase
                    )
                }
            }

            else -> {
                Log.d("BILLING_STATE", "Not processed state: $calculatedBillingState")
            }
        }
    }

    private suspend fun processPurchase(
        billingClient: BillingClient,
        scope: CoroutineScope,
        purchase: Purchase
    ) {
        // =========================================
        // Resolve license type
        // =========================================
        val productId = purchase.products.first()

        val licenseType = resolveLicenseType(
            productId
        )

        // =========================================
        // Activate license
        // =========================================
        licenseRepository.setLicense(
            licenseType
        )

        // =========================================
        // Notify Google Play
        // =========================================
        acknowledgePurchase(
            billingClient,
            scope,
            purchase
        )
    }

    private suspend fun acknowledgePurchase(
        billingClient: BillingClient,
        scope: CoroutineScope,
        purchase: Purchase
    ) {
        val acknowledgePurchaseParams = AcknowledgePurchaseParams.newBuilder()
            .setPurchaseToken(
                purchase.purchaseToken
            )
            .build()

        val retryDelays = listOf( 0L, 1_000L, 3_000L, 10_000L )

        for (delayMs in retryDelays) {

            if (delayMs > 0) {
                delay(delayMs)
            }

            val billingResult = billingClient.acknowledgePurchase(
                acknowledgePurchaseParams
            )

            processAcknowledgeResult(
                billingResult,
                scope
            )

            if (_billingState.value != BillingState.LicenseActivationRequired) {
                return
            }
        }

        // Усі повторні спроби вичерпані.
        // Ліцензію НЕ відкочуємо.
        _billingState.value = BillingState.Error(
            "Failed to acknowledge purchase after multiple attempts."
        )
    }

    private fun processAcknowledgeResult(
        billingResult: BillingResult,
        scope: CoroutineScope
    ) {
        when (billingResult.responseCode) {

            BillingClient.BillingResponseCode.OK,
            BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED,
            BillingClient.BillingResponseCode.USER_CANCELED -> {
                // Для користувача процес вважається успішно завершеним.
                // Фактичний стан покупки буде перевірено під час наступної
                // синхронізації через queryPurchasesAsync().
                _billingState.value = BillingState.Success
            }

            BillingClient.BillingResponseCode.DEVELOPER_ERROR -> {
                // TODO Crashlytics.recordException(...)
                // TODO Analytics.logEvent(...)
                Log.e(
                    "Billing",
                    "Unexpected DEVELOPER_ERROR during acknowledgePurchase(): " +
                            billingResult.debugMessage
                )
                _billingState.value = BillingState.Success
            }

            BillingClient.BillingResponseCode.ITEM_NOT_OWNED -> {
                scope.launch {
                    licenseRepository.setLicense(
                        LicenseType.FREE
                    )
                    _billingState.value = BillingState.Error(
                        billingResult.debugMessage.ifBlank {
                            "Purchase is no longer owned by this Google account."
                        }
                    )
                }
            }

            BillingClient.BillingResponseCode.NETWORK_ERROR,
            BillingClient.BillingResponseCode.SERVICE_DISCONNECTED,
            BillingClient.BillingResponseCode.SERVICE_UNAVAILABLE,
            BillingClient.BillingResponseCode.ERROR -> {

                // Retry acknowledgePurchase()
            }

            BillingClient.BillingResponseCode.BILLING_UNAVAILABLE,
            BillingClient.BillingResponseCode.FEATURE_NOT_SUPPORTED,
            BillingClient.BillingResponseCode.ITEM_UNAVAILABLE -> {

                Log.w(
                    "Billing",
                    "Unexpected response after acknowledgePurchase(): ${billingResult.responseCode}"
                )

                // TODO Crashlytics.recordException(...)

                _billingState.value = BillingState.Success
            }
        }
    }

    fun resetState() {
        _billingState.value = BillingState.Idle
    }
}