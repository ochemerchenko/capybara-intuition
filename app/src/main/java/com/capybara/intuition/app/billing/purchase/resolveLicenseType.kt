package com.capybara.intuition.app.billing.purchase

import com.capybara.intuition.license.domain.LicenseType

fun resolveLicenseType(
    productId: String
): LicenseType =

    when (productId) {

        "premium" -> LicenseType.PREMIUM
        else -> throw IllegalArgumentException(
            "Unknown productId: $productId"
        )

    }