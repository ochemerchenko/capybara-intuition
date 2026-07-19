package com.capybara.intuition.profile.domain.model

enum class SecurityEventType {
    FAILED_PIN_ATTEMPT,
    PROFILE_LOCKED,
    PROFILE_UNLOCKED,
    PIN_CHANGED
}