package com.capybara.intuition.statistics.data.repository

import android.util.Log
import com.capybara.intuition.game.data.mapper.GameModeMapper
import com.capybara.intuition.game.domain.model.GameMode
import com.capybara.intuition.statistics.data.local.db.dao.StatisticsDao
import com.capybara.intuition.statistics.charts.model.data.DayDensity
import com.capybara.intuition.statistics.charts.model.data.PreviewByLevelRequest
import com.capybara.intuition.statistics.charts.model.data.PreviewByLevelResponse
import com.capybara.intuition.statistics.charts.model.data.PreviewByModeRequest
import com.capybara.intuition.statistics.charts.model.data.PreviewByModeResponse

class StatisticsRepository(

    private val statisticsDao: StatisticsDao
) {

    suspend fun getDayDensities(
        profileId: Int,
        level: Int,
        fromTimestamp: Long,
        toTimestamp: Long,
        sessionMode: GameMode?,
        noiseLevel: Int,
    ): List<DayDensity> {

        val dayDensities = statisticsDao.getDayDensities(
            profileId = profileId,
            level = level,
            fromTimestamp = fromTimestamp,
            toTimestamp = toTimestamp,
            sessionMode = sessionMode?.let(GameModeMapper::toId),
            noiseLevel = noiseLevel
        )
        Log.d(
            "ANALYTICS",
            "REPOSITORY: dayDensities count = ${dayDensities.size}"
        )
        return dayDensities
    }


    suspend fun getPreviewByMode(
        request: PreviewByModeRequest
    ): PreviewByModeResponse {

        val datasets = LinkedHashMap<Int, List<DayDensity>>()
        val scope = request.scope

        request.levels.forEach { level ->

            val dayDensities = statisticsDao.getDayDensities(
                sessionMode = request.mode?.let(GameModeMapper::toId),
                level = level,
                profileId = scope.profileId,
                fromTimestamp = scope.fromTimestamp,
                toTimestamp = scope.toTimestamp,
                noiseLevel = scope.noiseLevel
            )

            if (dayDensities.isNotEmpty()) {
                datasets[level] = dayDensities
            }

        }

        return PreviewByModeResponse(
            mode = request.mode,
            datasets = datasets
        )
    }


    suspend fun getPreviewByLevel(
        request: PreviewByLevelRequest
    ): PreviewByLevelResponse {

        val datasets = LinkedHashMap<GameMode?, List<DayDensity>>()

        val scope = request.scope

        request.gameModes.forEach { gameMode ->

            val dayDensities = statisticsDao.getDayDensities(
                profileId = scope.profileId,
                level = request.level,
                fromTimestamp = scope.fromTimestamp,
                toTimestamp = scope.toTimestamp,
                sessionMode = gameMode?.let(GameModeMapper::toId),
                noiseLevel = scope.noiseLevel
            )

            if (dayDensities.isNotEmpty()) {
                datasets[gameMode] = dayDensities
            }
        }

        return PreviewByLevelResponse(
            level = request.level,
            datasets = datasets
        )
    }

}
