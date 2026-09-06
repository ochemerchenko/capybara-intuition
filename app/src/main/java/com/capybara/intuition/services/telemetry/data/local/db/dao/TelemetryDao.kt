package com.capybara.intuition.services.telemetry.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.capybara.intuition.services.telemetry.data.local.db.entity.ScreenTransitionEntity

@Dao
interface TelemetryDao {
    
    @Insert
    suspend fun insertTransition(transition: ScreenTransitionEntity)

    @Query("SELECT MIN(timestamp) FROM screen_transitions")
    suspend fun getOldestTransitionTimestamp(): Long?

    @Query("SELECT * FROM screen_transitions WHERE timestamp >= :startTime AND timestamp < :endTime ORDER BY timestamp ASC")
    suspend fun getTransitionsForPeriod(startTime: Long, endTime: Long): List<ScreenTransitionEntity>

    @Query("DELETE FROM screen_transitions WHERE timestamp < :beforeTimestamp")
    suspend fun deleteOldTransitions(beforeTimestamp: Long)
}
