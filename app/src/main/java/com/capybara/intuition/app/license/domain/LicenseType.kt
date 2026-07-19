package com.capybara.intuition.license.domain

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