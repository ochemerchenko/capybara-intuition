package com.capybara.intuition.services.billing

import android.util.Log
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingResult
import com.capybara.intuition.services.billing.ui.model.BillingConnectionState
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
        Log.d(
            "Billing",
            "[Connection] Connect requested"
        )
        if (billingClient.isReady) {
            Log.d(
                "Billing",
                "[Connection] BillingClient already connected, State -> CONNECTED"
            )
            connectionState = BillingConnectionState.CONNECTED

            return BillingResult.newBuilder()
                .setResponseCode(BillingResponseCode.OK)
                .setDebugMessage("BillingClient already connected.")
                .build()
        }

        connectionState = BillingConnectionState.CONNECTING

        return suspendCancellableCoroutine { continuation ->
            Log.d(
                "Billing",
                "[Connection] Starting Google Play connection"
            )
            billingClient.startConnection(
                object : BillingClientStateListener {

                    override fun onBillingSetupFinished(
                        billingResult: BillingResult
                    ) {
                        Log.d(
                            "Billing",
                            "[Connection] Setup finished: " +
                                    "response=${billingResult.responseCode}, " +
                                    "message='${billingResult.debugMessage}'"
                        )

                        connectionState =
                            if (billingResult.responseCode == BillingResponseCode.OK)
                                BillingConnectionState.CONNECTED
                            else
                                BillingConnectionState.DISCONNECTED
                        Log.d(
                            "Billing",
                            "[Connection] State -> $connectionState"
                        )
                        if (continuation.isActive) {
                            continuation.resume(billingResult)
                        }
                    }

                    override fun onBillingServiceDisconnected() {

                        Log.d(
                            "Billing",
                            "[Connection] Google Play service disconnected"
                        )
                        connectionState = BillingConnectionState.DISCONNECTED
                        Log.d(
                            "Billing",
                            "[Connection] State -> DISCONNECTED"
                        )
                    }
                }
            )
        }
    }

    fun disconnect(
        billingClient: BillingClient
    ) {
        Log.d(
            "Billing",
            "[Connection] Disconnect requested"
        )
        if (!billingClient.isReady) {
            Log.d(
                "Billing",
                "[Connection] Disconnect ignored (already disconnected)"
            )
            connectionState = BillingConnectionState.DISCONNECTED
            return
        }
        Log.d(
            "Billing",
            "[Connection] Connection closed"
        )
        billingClient.endConnection()
        Log.d(
            "Billing",
            "[Connection] State -> DISCONNECTED"
        )
        connectionState = BillingConnectionState.DISCONNECTED
    }
}

