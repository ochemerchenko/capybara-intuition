package com.capybara.intuition.features.profile.domain.security

interface PinHasher {

    fun hash(
        pin: String
    ): String
}