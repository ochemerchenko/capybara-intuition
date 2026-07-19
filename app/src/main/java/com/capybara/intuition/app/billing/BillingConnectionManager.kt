package com.capybara.intuition.app.billing

import android.util.Log
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingResult
import com.capybara.intuition.app.billing.ui.model.BillingConnectionState
import com.android.billingclient.api.BillingClient.BillingResponseCode
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class BillingConnectionManager {

    var connectionState: BillingConnectionState =
        BillingConnectionState.DISCONNECTED
        private set

    suspend fun connect(
        billingClient: BillingClient
    ): BillingResult {
        Log.d("Billing", "BillingConnectionManager.connect(...)")
        if (billingClient.isReady) {
            connectionState = BillingConnectionState.CONNECTED

            return BillingResult.newBuilder()
                .setResponseCode(BillingResponseCode.OK)
                .setDebugMessage("BillingClient already connected.")
                .build()
        }

        connectionState = BillingConnectionState.CONNECTING

        return suspendCancellableCoroutine { continuation ->

            billingClient.startConnection(
                object : BillingClientStateListener {

                    override fun onBillingSetupFinished(
                        billingResult: BillingResult
                    ) {

                        Log.d(
                            "Billing",
                            "onBillingSetupFinished: ${billingResult.responseCode}, ${billingResult.debugMessage}"
                        )

                        connectionState =
                            if (billingResult.responseCode == BillingResponseCode.OK)
                                BillingConnectionState.CONNECTED
                            else
                                BillingConnectionState.DISCONNECTED

                        if (continuation.isActive) {
                            continuation.resume(billingResult)
                        }
                    }

                    override fun onBillingServiceDisconnected() {

                        Log.d(
                            "Billing",
                            "onBillingServiceDisconnected()"
                        )

                        connectionState =
                            BillingConnectionState.DISCONNECTED
                    }
                }
            )
        }
    }

    fun disconnect(
        billingClient: BillingClient
    ) {
        if (!billingClient.isReady) {
            connectionState = BillingConnectionState.DISCONNECTED
            return
        }
        billingClient.endConnection()
        connectionState = BillingConnectionState.DISCONNECTED
    }
}

