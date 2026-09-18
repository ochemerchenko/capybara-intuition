package com.capybara.intuition.features.achievements.di

import com.capybara.intuition.features.achievements.data.repository.AchievementsRepository
import com.capybara.intuition.features.achievements.presentation.viewmodel.AchievementsViewModel
import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.services.installation.data.repository.InstallationRepository

class AchievementsModule(
    database: AppDatabase,
    installationRepository: InstallationRepository
) {

    val achievementsDao = database.achievementsDao()

    val achievementsRepository = AchievementsRepository(
        dao = achievementsDao,
        database = database
    )

    val achievementsViewModel = AchievementsViewModel(
        achievementsRepository = achievementsRepository,
        installationRepository = installationRepository
    )
}