package com.capybara.intuition.features.statistics.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.capybara.intuition.features.statistics.charts.model.data.DayDensity


@Dao
interface StatisticsDao {

    @Query(
        """
        SELECT
            DATE(startedAt / 1000, 'unixepoch') AS day,
            1.0 * AVG(sessionDensity) AS dayDensity,
            SUM(sessionRounds) AS dayRoundCount,
            COUNT(sessionId) AS daySessionCount,
            SUM(sessionRoundsTime) / SUM(sessionRounds) AS averageDecisionTime
        FROM (

            SELECT
                  s.id                          AS sessionId
                , s.startedAt                   AS startedAt
                , 1.0 * SUM( rr.result ) / COUNT(rr.id)                AS sessionDensity
                , COUNT(rr.id)                  AS sessionRounds
                , SUM(rr.finishedAt - rr.startedAt) AS sessionRoundsTime

            FROM sessions s
            INNER JOIN round_records rr
                ON rr.sessionId = s.id
            WHERE
                s.profileId = :profileId
                AND s.level = :level
                AND s.startedAt BETWEEN :fromTimestamp AND :toTimestamp
                AND (
                    :sessionMode IS NULL
                    OR s.mode = :sessionMode
                )

            GROUP BY s.id
            HAVING COUNT(rr.id) > :noiseLevel

        ) AS session_stats
        GROUP BY day
        ORDER BY day ASC
        """
    )
    suspend fun getDayDensities(
        profileId: Int,
        level: Int,
        fromTimestamp: Long,
        toTimestamp: Long,
        sessionMode: Short?,
        noiseLevel: Int
    ): List<DayDensity>

}
