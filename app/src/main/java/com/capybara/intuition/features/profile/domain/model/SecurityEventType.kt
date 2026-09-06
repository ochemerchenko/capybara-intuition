package com.capybara.intuition.features.profile.domain.model

enum class SecurityEventType {
    FAILED_PIN_ATTEMPT,
    PROFILE_LOCKED,
    PROFILE_UNLOCKED,
    PIN_CHANGED
}