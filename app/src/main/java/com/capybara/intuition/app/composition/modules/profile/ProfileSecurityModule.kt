package com.capybara.intuition.app.composition.modules.profile

import com.capybara.intuition.profile.domain.recovery.RecoveryChallengeGenerator
import com.capybara.intuition.profile.domain.security.Sha256PinHasher

class ProfileSecurityModule() {
    val pinHasher = Sha256PinHasher()
    val recoveryChallengeGenerator = RecoveryChallengeGenerator()


}