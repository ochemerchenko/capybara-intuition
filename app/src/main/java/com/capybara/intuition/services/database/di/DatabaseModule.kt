package com.capybara.intuition.services.database.di

import com.capybara.intuition.core.data.DataStoreAdapter
import com.capybara.intuition.features.game.data.local.db.dao.SessionDao
import com.capybara.intuition.services.database.data.DatabaseRetentionDataStore
import com.capybara.intuition.services.database.data.DatabaseRetentionRepository
import com.capybara.intuition.services.database.domain.DatabaseCleanupManager

class DatabaseModule(
    dataStoreAdapter: DataStoreAdapter,
    sessionDao: SessionDao
) {
    private val databaseRetentionDataStore = DatabaseRetentionDataStore(dataStoreAdapter)
    
    val databaseRetentionRepository = DatabaseRetentionRepository(databaseRetentionDataStore)
    
    val databaseCleanupManager = DatabaseCleanupManager(
        sessionDao = sessionDao,
        databaseRetentionRepository = databaseRetentionRepository
    )
}
