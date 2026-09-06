package com.capybara.intuition.services.telemetry.data.repository

import com.capybara.intuition.services.telemetry.data.local.db.dao.TelemetryDao
import com.capybara.intuition.services.telemetry.data.local.db.entity.ScreenTransitionEntity
import com.capybara.intuition.services.telemetry.model.AppScreen

class TelemetryRepository(
    private val telemetryDao: TelemetryDao
) {

    /**
     * Records a new screen transition.
     * Mapping from domain model (AppScreen) to DB entity happens here.
     */
    suspend fun recordTransition(from: AppScreen?, to: AppScreen) {
        val entity = ScreenTransitionEntity(
            fromScreen = from,
            toScreen = to,
            timestamp = System.currentTimeMillis()
        )
        telemetryDao.insertTransition(entity)
    }

    /**
     * Returns the timestamp of the oldest record in the database.
     */
    suspend fun getOldestTransitionTimestamp(): Long? {
        return telemetryDao.getOldestTransitionTimestamp()
    }

    /**
     * Returns transitions for a specific day for export purposes.
     */
    suspend fun getTransitionsForExport(startTime: Long, endTime: Long): List<ScreenTransitionEntity> {
        return telemetryDao.getTransitionsForPeriod(startTime, endTime)
    }

    /**
     * Cleans up old telemetry after successful export.
     */
    suspend fun cleanup(beforeTimestamp: Long) {
        telemetryDao.deleteOldTransitions(beforeTimestamp)
    }
}
