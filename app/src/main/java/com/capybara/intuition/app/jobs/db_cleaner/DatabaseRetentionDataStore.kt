package com.capybara.intuition.app.jobs.db_cleaner

import com.capybara.intuition.app.DataStoreAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatabaseRetentionDataStore(
    private val dataStore: DataStoreAdapter
) {

    companion object {
        const val RETENTION_DAYS_KEY = "database_retention_days"
    }

    fun observe(): Flow<Long?> =
        dataStore
            .observe(RETENTION_DAYS_KEY)
            .map { value ->
                value?.toLong()
            }

    suspend fun edit(
        retentionDays: Long
    ) {
        dataStore.edit(
            key = RETENTION_DAYS_KEY,
            value = retentionDays.toString()
        )
    }

    suspend fun delete() {
        dataStore.delete(
            RETENTION_DAYS_KEY
        )
    }
}