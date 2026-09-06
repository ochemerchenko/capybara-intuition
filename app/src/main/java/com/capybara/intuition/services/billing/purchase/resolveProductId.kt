package com.capybara.intuition.services.billing.purchase

import com.capybara.intuition.services.license.domain.LicenseType

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