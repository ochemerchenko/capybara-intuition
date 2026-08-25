package com.capybara.intuition.app.composition.modules

import com.capybara.intuition.achievements.data.repository.AchievementsRepository
import com.capybara.intuition.achievements.presentation.viewmodel.AchievementsViewModel
import com.capybara.intuition.app.AppDatabase

class AchievementsModule(
    database: AppDatabase
) {

    val achievementsDao = database.achievementsDao()

    val achievementsRepository = AchievementsRepository(
        dao = achievementsDao,
        database = database
    )

    val achievementsViewModel = AchievementsViewModel(
        achievementsRepository = achievementsRepository
    )
}