package com.capybara.intuition.app.jobs.db_cleaner

import DatabaseRetentionRepository
import com.capybara.intuition.game.data.local.db.dao.SessionDao

class DatabaseCleanupManager(
    private val sessionDao: SessionDao,
    private val databaseRetentionRepository: DatabaseRetentionRepository
) {
    suspend fun cleanup() {
        val threshold = databaseRetentionRepository.getThreshold()

        sessionDao.deleteSessionsStartedBefore(
            threshold
        )
    }
}