package com.capybara.intuition.profile.domain.security

interface PinHasher {

    fun hash(
        pin: String
    ): String
}