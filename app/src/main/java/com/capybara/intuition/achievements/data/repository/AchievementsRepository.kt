package com.capybara.intuition.achievements.data.repository

import com.capybara.intuition.achievements.data.local.db.dao.AchievementsDao
import com.capybara.intuition.achievements.domain.model.Achievement
import com.capybara.intuition.app.AppDatabase
import kotlinx.coroutines.flow.Flow


class AchievementsRepository (
    private val dao: AchievementsDao,
    private val database: AppDatabase
){

    fun getRecords(): Flow<List<Achievement>> {
        return dao.getRecords()
    }

    fun getRegularities(): Flow<List<Achievement>> {
        return dao.getRegularities()
    }


    fun getSkills(): Flow<List<Achievement>> {
        return dao.getSkills()
    }


    fun getMilestones(): Flow<List<Achievement>> {
        return dao.getMilestones()
    }

    suspend fun markAllAsSeen() {
        dao.markAllAsSeen()
    }

}