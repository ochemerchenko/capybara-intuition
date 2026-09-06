package com.capybara.intuition.services.license.domain

enum class LicenseType {
    FREE,
    PREMIUM;

    companion object {
        val synchronizationOrder =
            listOf(
                PREMIUM,
                FREE
            )
    }
}