package com.capybara.intuition.features.wellbeing.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capybara.intuition.features.wellbeing.data.local.db.entity.WellbeingRecordEntity

@Dao
interface WellbeingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(record: WellbeingRecordEntity)

    /**
     * Checks if there is a record for the profile in the given time range.
     */
    @Query("SELECT * FROM wellbeing_records WHERE profileId = :profileId AND timestamp >= :startTime AND timestamp < :endTime LIMIT 1")
    suspend fun getRecordInPeriod(profileId: Int, startTime: Long, endTime: Long): WellbeingRecordEntity?

    @Query("SELECT * FROM wellbeing_records WHERE profileId = :profileId ORDER BY timestamp DESC LIMIT 1")
    suspend fun getLatestRecord(profileId: Int): WellbeingRecordEntity?

    @Query("SELECT * FROM wellbeing_records WHERE profileId = :profileId ORDER BY timestamp DESC")
    suspend fun getAllRecordsForProfile(profileId: Int): List<WellbeingRecordEntity>

    @Query("SELECT * FROM wellbeing_records WHERE timestamp >= :dayStart AND timestamp < :dayEnd")
    suspend fun getRecordsForExport(dayStart: Long, dayEnd: Long): List<WellbeingRecordEntity>

    @Query("DELETE FROM wellbeing_records WHERE profileId = :profileId")
    suspend fun deleteRecordsByProfileId(profileId: Int)
}
