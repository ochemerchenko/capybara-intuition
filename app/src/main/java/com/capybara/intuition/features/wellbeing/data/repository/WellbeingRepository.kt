package com.capybara.intuition.features.wellbeing.data.repository

import com.capybara.intuition.features.wellbeing.data.local.db.dao.WellbeingDao
import com.capybara.intuition.features.wellbeing.data.local.db.entity.WellbeingRecordEntity
import java.time.LocalDate
import java.time.ZoneOffset

class WellbeingRepository(
    private val wellbeingDao: WellbeingDao
) {

    /**
     * Checks if a wellbeing record exists for the given profile for the current UTC day.
     */
    suspend fun hasRecordForToday(profileId: Int): Boolean {
        val today = LocalDate.now(ZoneOffset.UTC)
        val startTime = today.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()
        val endTime = today.plusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()

        return wellbeingDao.getRecordInPeriod(profileId, startTime, endTime) != null
    }

    /**
     * Returns the latest wellbeing record for the profile.
     */
    suspend fun getLatestRecord(profileId: Int): WellbeingRecordEntity? {
        return wellbeingDao.getLatestRecord(profileId)
    }

    /**
     * Saves a new wellbeing record.
     */
    suspend fun saveRecord(
        profileId: Int,
        physical: Int,
        happiness: Int,
        confidence: Int
    ) {
        val record = WellbeingRecordEntity(
            profileId = profileId,
            physical = physical,
            happiness = happiness,
            confidence = confidence,
            timestamp = System.currentTimeMillis()
        )
        
        wellbeingDao.insertRecord(record)
    }
}
