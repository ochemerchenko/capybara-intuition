package com.capybara.intuition.app.billing.purchase

import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.ProductDetails

fun createBillingFlowParams(
    productDetails: ProductDetails
): BillingFlowParams {

    val productDetailsParams =
        BillingFlowParams.ProductDetailsParams
            .newBuilder()
            .setProductDetails(productDetails)
            .build()

    return BillingFlowParams
        .newBuilder()
        .setProductDetailsParamsList(
            listOf(productDetailsParams)
        )
        .build()
}