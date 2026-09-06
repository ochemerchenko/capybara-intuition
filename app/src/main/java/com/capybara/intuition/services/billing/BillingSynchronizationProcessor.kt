package com.capybara.intuition.services.billing

import android.util.Log
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.QueryPurchasesParams
//import com.android.billingclient.api.acknowledgePurchase
//import com.android.billingclient.api.queryPurchasesAsync
import com.capybara.intuition.services.billing.model.BillingSynchronizationResult
import com.capybara.intuition.services.billing.purchase.resolveLicenseType
import com.capybara.intuition.services.license.data.repository.LicenseRepository
import com.capybara.intuition.services.license.data.repository.SynchronizationRepository
import com.capybara.intuition.services.license.domain.LicenseType
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


class BillingSynchronizationProcessor(
    private val licenseRepository: LicenseRepository,
    private val synchronizationRepository: SynchronizationRepository
) {

    suspend fun synchronize(
        billingClient: BillingClient
    ): BillingSynchronizationResult {
        Log.d(
            "Billing",
            "[Synchronization] Started"
        )
        val params = QueryPurchasesParams.newBuilder()
            .setProductType(
                BillingClient.ProductType.INAPP
            )
            .build()

        //val purchasesResult = billingClient.queryPurchasesAsync(params)
        val (billingResult, purchasesList) = suspendCancellableCoroutine { continuation ->
            billingClient.queryPurchasesAsync(params) { result, purchases ->
                if (continuation.isActive) {
                    continuation.resume(Pair(result, purchases))
                }
            }
        }



        Log.d(
            "Billing",
            "[Synchronization] Google response=${billingResult.responseCode}, purchases=${purchasesList.size}"
        )


        if (
            billingResult.responseCode ==
            BillingClient.BillingResponseCode.OK
        ) {

            synchronizationRepository.setLastBillingSynchronization(
                System.currentTimeMillis()
            )

            val purchases = purchasesList ?: emptyList()

            if (purchases.isEmpty()) {

                licenseRepository.setLicense(
                    LicenseType.FREE
                )

                licenseRepository.setLastConfirmedLicense(
                    LicenseType.FREE
                )
                Log.d(
                    "Billing",
                    "[Synchronization] No purchases found"
                )
                return BillingSynchronizationResult(
                    connected = true,
                    licenses = mapOf(
                        LicenseType.PREMIUM to false
                    )
                )
            }


            for (licenseType in LicenseType.synchronizationOrder) {
                Log.d(
                    "Billing",
                    "[Synchronization] Checking $licenseType"
                )
                val purchasesForLicense =
                    purchases.filter { purchase ->
                        resolveLicenseType(
                            purchase.products.first()
                        ) == licenseType
                    }

                if (purchasesForLicense.isEmpty()) {
                    continue
                }

                val purchase = purchasesForLicense.maxBy {
                    it.purchaseTime
                }

                if (purchase.isAcknowledged) {
                    licenseRepository.setLicense(
                        licenseType
                    )
                    licenseRepository.setLastConfirmedLicense(
                        licenseType
                    )
                    Log.d(
                        "Billing",
                        "[Synchronization] $licenseType already acknowledged"
                    )
                    return BillingSynchronizationResult(
                        connected = true,
                        licenses = mapOf(
                            licenseType to true
                        )
                    )
                }

                if (
                    purchase.purchaseState == Purchase.PurchaseState.PURCHASED
                ) {

                    licenseRepository.setLicense(
                        licenseType
                    )
                    Log.d(
                        "Billing",
                        "[Synchronization] Acknowledge required for $licenseType"
                    )
                    val acknowledged = acknowledgeCurrentPurchase(
                        billingClient,
                        purchase
                    )

                    if (acknowledged) {
                        licenseRepository
                            .setLastConfirmedLicense(
                                licenseType
                            )
                    } else {

                        licenseRepository.setLicense(
                            licenseRepository
                                .lastConfirmedLicense
                                .value
                        )
                    }
                    Log.d(
                        "Billing",
                        "[Synchronization] acknowledgeCurrentPurchase() -> $acknowledged"
                    )
                    Log.d(
                        "Billing",
                        "[Synchronization] Returning: $licenseType -> $acknowledged"
                    )
                    return BillingSynchronizationResult(
                        connected = true,
                        licenses = mapOf(
                            licenseType to acknowledged
                        )
                    )
                }
            }

        } else {
            Log.d(
                "Billing",
                "[Synchronization] Google request failed: response=${billingResult.responseCode}"
            )
            val lastSynchronization = synchronizationRepository
                .lastBillingSynchronization
                .value

            if (lastSynchronization == null) {
                return BillingSynchronizationResult(
                    connected = false,
                    licenses = emptyMap()
                )
            }

            val synchronizationTimeout = 3 * 24 * 60 * 60 * 1000L

            if (
                System.currentTimeMillis() - lastSynchronization >= synchronizationTimeout
            ) {
                licenseRepository.setLicense(
                    LicenseType.FREE
                )
                licenseRepository.setLastConfirmedLicense(
                    LicenseType.FREE
                )
            }
        }
        Log.d(
            "Billing",
            "[Synchronization] Finished without matching licenses"
        )
        return BillingSynchronizationResult(
            connected = true,
            licenses = emptyMap()
        )
    }

    private suspend fun acknowledgeCurrentPurchase(
        billingClient: BillingClient,
        purchase: Purchase
    ): Boolean {

        val params = AcknowledgePurchaseParams.newBuilder()
            .setPurchaseToken(
                purchase.purchaseToken
            )
            .build()

        val retryDelays = listOf(
            0L,
            1_000L,
            3_000L,
            10_000L
        )

        for (delayMs in retryDelays) {

            if (delayMs > 0) {
                delay(delayMs)
            }

            //val billingResult = billingClient.acknowledgePurchase(
            //    params
            //)

            val billingResult = suspendCancellableCoroutine { continuation ->
                billingClient.acknowledgePurchase(
                    params
                ) { result ->
                    continuation.resume(
                        value =result
                    )
                }
            }

            when (billingResult.responseCode) {

                BillingClient.BillingResponseCode.OK,
                BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED -> {
                    return true
                }

                BillingClient.BillingResponseCode.NETWORK_ERROR,
                BillingClient.BillingResponseCode.SERVICE_DISCONNECTED,
                BillingClient.BillingResponseCode.SERVICE_UNAVAILABLE,
                BillingClient.BillingResponseCode.ERROR -> {
                    // retry
                }

                BillingClient.BillingResponseCode.ITEM_NOT_OWNED,
                BillingClient.BillingResponseCode.BILLING_UNAVAILABLE,
                BillingClient.BillingResponseCode.FEATURE_NOT_SUPPORTED,
                BillingClient.BillingResponseCode.ITEM_UNAVAILABLE,
                BillingClient.BillingResponseCode.DEVELOPER_ERROR,
                BillingClient.BillingResponseCode.USER_CANCELED -> {
                    return false
                }
            }
        }

        return false
    }
}