package com.capybara.intuition.app.billing

import android.app.Activity
import android.content.Context
import android.util.Log
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.PendingPurchasesParams
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.capybara.intuition.app.billing.ui.model.BillingConnectionState
import com.capybara.intuition.app.billing.ui.model.BillingState
import com.capybara.intuition.app.license.data.repository.LicenseRepository
import com.capybara.intuition.license.domain.LicenseType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.capybara.intuition.app.billing.ui.model.BillingUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.stateIn
import com.capybara.intuition.app.license.data.repository.SynchronizationRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


class GooglePlayBillingManager(
    context: Context,
    licenseRepository: LicenseRepository,
    synchronizationRepository: SynchronizationRepository,
) : PurchasesUpdatedListener {

    // ============================================================
    // Infrastructure
    // ============================================================

    private val scope = CoroutineScope(
        SupervisorJob() + Dispatchers.IO
    )

    /**
     * Єдина точка синхронізації доступу до BillingClient.
     */
    private val billingMutex = Mutex()

    private val billingClient: BillingClient = BillingClient.newBuilder(context)
        .setListener(this)
        .enablePendingPurchases(
            PendingPurchasesParams.newBuilder()
                .enableOneTimeProducts()
                .build()
        )
        // TODO:
        // Після переходу проєкту на Billing 8.x
        // увімкнути enableAutoServiceReconnection().
        //.enableAutoServiceReconnection()
        .build()

    // ============================================================
    // Purchase UI state
    // ============================================================
    private val _billingState = MutableStateFlow<BillingState>(BillingState.Idle)
    val billingState: StateFlow<BillingState> = _billingState.asStateFlow()
    val billingUiState: StateFlow<BillingUiState> = billingState
        .mapNotNull { state ->
            when (state) {
                BillingState.Idle -> BillingUiState.Idle
                BillingState.Loading -> BillingUiState.Loading
                BillingState.Pending -> BillingUiState.Pending
                BillingState.UnknownResult -> BillingUiState.UnknownResult
                BillingState.Success -> BillingUiState.Success
                is BillingState.Error ->
                    BillingUiState.Error(state.message)

                BillingState.LicenseActivationRequired ->
                    null
            }
        }
        .stateIn(
            scope,
            SharingStarted.Eagerly,
            BillingUiState.Idle
        )

    // ============================================================
    // Business processors
    // ============================================================
    private val connectionManager = BillingConnectionManager()
    private val purchaseProcessor = BillingPurchaseProcessor(
        licenseRepository,
        _billingState
    )
    private val synchronizationProcessor = BillingSynchronizationProcessor(
        licenseRepository,
        synchronizationRepository
    )

    // ============================================================
    // Connection
    // ============================================================
    suspend fun start() {
        Log.d("Billing", "GooglePlayBillingManager.start()")
        billingMutex.withLock {
            val result: BillingResult = connectionManager.connect(
                billingClient
            )
            if ( result.responseCode == BillingClient.BillingResponseCode.OK ) {
                synchronizationProcessor.synchronize(
                    billingClient
                )
            }
        }
    }


    fun stop() {
        connectionManager.disconnect(
            billingClient
        )
    }


    // ============================================================
    // Purchase
    // ============================================================
    fun startPurchase(
        activity: Activity,
        licenseType: LicenseType
    ) {
        Log.d("Billing", "GooglePlayBillingManager.startPurchase()")
        purchaseProcessor.startPurchase(
            billingClient,
            activity,
            licenseType
        )
    }


    override fun onPurchasesUpdated(
        billingResult: BillingResult,
        purchases: MutableList<Purchase>?
    ) {
        scope.launch {
            billingMutex.withLock {
                purchaseProcessor.onPurchasesUpdated(
                    billingClient,
                    scope,
                    billingResult,
                    purchases
                )
            }
        }
    }

    // ============================================================
    // UI
    // ============================================================
    fun resetState() {
        purchaseProcessor.resetState()
    }
}
