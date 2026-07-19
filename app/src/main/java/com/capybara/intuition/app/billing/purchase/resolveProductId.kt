package com.capybara.intuition.app.billing.purchase

import com.capybara.intuition.license.domain.LicenseType

fun resolveProductId(
    licenseType: LicenseType
): String =

    when (licenseType) {

        LicenseType.PREMIUM -> "premium"
        LicenseType.FREE ->
            throw IllegalArgumentException(
                "FREE license cannot be purchased."
            )

    }