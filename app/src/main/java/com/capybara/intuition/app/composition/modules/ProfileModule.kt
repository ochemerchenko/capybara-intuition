package com.capybara.intuition.app.composition.modules

import com.capybara.intuition.app.composition.modules.profile.ProfileResourcesModule
import com.capybara.intuition.app.composition.modules.profile.ProfileSecurityModule
import com.capybara.intuition.app.composition.modules.profile.ProfileTopologyModule
import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.profile.data.repository.ProfileRepository
import com.capybara.intuition.profile.presentation.viewmodel.ProfileViewModel

class ProfileModule(
    database: AppDatabase
) {
    val topology = ProfileTopologyModule(database)
    val resources = ProfileResourcesModule(database)
    val security = ProfileSecurityModule()
    val profileDao = database.profileDao()
    val profileRepository = ProfileRepository(
        profileDao = profileDao,
        pinHasher = security.pinHasher,
    )

    val profileViewModel = ProfileViewModel(
        repository = profileRepository,
        recoveryChallengeGenerator = security.recoveryChallengeGenerator,
        pinHasher = security.pinHasher
    )
}