package com.capybara.intuition.features.achievements.data.repository

import com.capybara.intuition.features.achievements.data.local.db.dao.AchievementsDao
import com.capybara.intuition.features.achievements.domain.model.LevelMetric
import com.capybara.intuition.app.AppDatabase
import kotlinx.coroutines.flow.Flow


class AchievementsRepository (
    private val dao: AchievementsDao,
    private val database: AppDatabase
){

    fun getLastActiveDays(today: String): Flow<Long> {
        return dao.getLastActiveDays(today)
    }

    fun getTotalActiveDays(today: String): Flow<Long> {
        return dao.getTotalActiveDays(today)
    }

    fun getMaxSessionsPerDayPerLevel(today: String): Flow<List<LevelMetric>> {
        return dao.getMaxSessionsPerDayPerLevel(today)
    }

    fun getMaxRoundsPerSessionPerLevel(today: String): Flow<List<LevelMetric>> {
        return dao.getMaxRoundsPerSessionPerLevel(today)
    }

    fun getMaxCorrectPerSessionPerLevel(today: String): Flow<List<LevelMetric>> {
        return dao.getMaxCorrectPerSessionPerLevel(today)
    }

    fun getMaxMissesPerSessionPerLevel(today: String): Flow<List<LevelMetric>> {
        return dao.getMaxMissesPerSessionPerLevel(today)
    }

    fun getMaxWinStreakPerSessionPerLevel(today: String): Flow<List<LevelMetric>> {
        return dao.getMaxWinStreakPerSessionPerLevel(today)
    }

    fun getMaxLoseStreakPerSessionPerLevel(today: String): Flow<List<LevelMetric>> {
        return dao.getMaxLoseStreakPerSessionPerLevel(today)
    }

    fun getMaxStreak(today: String): Flow<Long> {
        return dao.getMaxStreak(today)
    }

    fun getMaxBreak(today: String): Flow<Long> {
        return dao.getMaxBreak(today)
    }

}
