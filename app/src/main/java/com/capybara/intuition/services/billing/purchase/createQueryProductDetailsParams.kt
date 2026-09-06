package com.capybara.intuition.services.billing.purchase

import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.QueryProductDetailsParams

fun createQueryProductDetailsParams(
    productId: String
): QueryProductDetailsParams = QueryProductDetailsParams
    .newBuilder()
    .setProductList(
        listOf(
            QueryProductDetailsParams.Product
                .newBuilder()
                .setProductId(productId)
                .setProductType(BillingClient.ProductType.INAPP)
                .build()
        )
    )
    .build()