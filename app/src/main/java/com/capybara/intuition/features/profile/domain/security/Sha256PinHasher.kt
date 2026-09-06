package com.capybara.intuition.features.profile.domain.security

import java.security.MessageDigest

class Sha256PinHasher : PinHasher {

    override fun hash(
        pin: String
    ): String {

        val bytes =
            MessageDigest
                .getInstance("SHA-256")
                .digest(
                    pin.toByteArray()
                )

        return bytes.joinToString("") {

            "%02x".format(it)
        }
    }
}