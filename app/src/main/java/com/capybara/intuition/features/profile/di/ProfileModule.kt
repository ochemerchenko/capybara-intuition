package com.capybara.intuition.features.profile.di

import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.features.profile.data.repository.ProfileRepository
import com.capybara.intuition.features.profile.presentation.viewmodel.ProfileViewModel

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