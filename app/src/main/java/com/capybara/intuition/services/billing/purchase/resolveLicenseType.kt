package com.capybara.intuition.services.billing.purchase

import com.capybara.intuition.services.license.domain.LicenseType

fun resolveLicenseType(
    productId: String
): LicenseType =

    when (productId) {

        "premium" -> LicenseType.PREMIUM
        else -> throw IllegalArgumentException(
            "Unknown productId: $productId"
        )

    }