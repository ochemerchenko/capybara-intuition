package com.capybara.intuition.app.billing

import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.QueryPurchasesParams
import com.android.billingclient.api.acknowledgePurchase
import com.android.billingclient.api.queryPurchasesAsync
import com.capybara.intuition.app.billing.purchase.resolveLicenseType
import com.capybara.intuition.app.license.data.repository.LicenseRepository
import com.capybara.intuition.app.license.data.repository.SynchronizationRepository
import com.capybara.intuition.license.domain.LicenseType
import kotlinx.coroutines.delay

class BillingSynchronizationProcessor(
    private val licenseRepository: LicenseRepository,
    private val synchronizationRepository: SynchronizationRepository
) {

    suspend fun synchronize(
        billingClient: BillingClient
    ) {

        val params = QueryPurchasesParams.newBuilder()
            .setProductType(
                BillingClient.ProductType.INAPP
            )
            .build()

        val purchasesResult = billingClient.queryPurchasesAsync(params)

        if (
            purchasesResult.billingResult.responseCode ==
            BillingClient.BillingResponseCode.OK
        ) {

            synchronizationRepository.setLastBillingSynchronization(
                System.currentTimeMillis()
            )

            val purchases = purchasesResult.purchasesList ?: emptyList()

            if (purchases.isEmpty()) {

                licenseRepository.setLicense(
                    LicenseType.FREE
                )

                licenseRepository.setLastConfirmedLicense(
                    LicenseType.FREE
                )

                return
            }

            for (licenseType in LicenseType.synchronizationOrder) {

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
                    return
                }

                if (
                    purchase.purchaseState == Purchase.PurchaseState.PURCHASED
                ) {

                    licenseRepository.setLicense(
                        licenseType
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
                    return
                }
            }

        } else {

            val lastSynchronization = synchronizationRepository
                .lastBillingSynchronization
                .value

            if (lastSynchronization == null) {
                return
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

            val billingResult =
                billingClient.acknowledgePurchase(
                    params
                )

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