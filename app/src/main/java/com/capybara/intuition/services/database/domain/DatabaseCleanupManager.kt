package com.capybara.intuition.services.database.domain

import com.capybara.intuition.services.database.data.DatabaseRetentionRepository
import com.capybara.intuition.features.game.data.local.db.dao.SessionDao

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