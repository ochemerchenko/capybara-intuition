package com.capybara.intuition.profile.domain.model

data class ProfileSecurityState(
    val failedPinAttempts: Int,
    val recoveryRequired: Boolean,
    val recoveryVerified: Boolean
)