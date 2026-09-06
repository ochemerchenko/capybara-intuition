package com.capybara.intuition.features.profile.di

import com.capybara.intuition.features.profile.domain.recovery.RecoveryChallengeGenerator
import com.capybara.intuition.features.profile.domain.security.Sha256PinHasher

class ProfileSecurityModule() {
    val pinHasher = Sha256PinHasher()
    val recoveryChallengeGenerator = RecoveryChallengeGenerator()


}