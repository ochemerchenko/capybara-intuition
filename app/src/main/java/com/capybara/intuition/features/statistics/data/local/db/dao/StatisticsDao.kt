package com.capybara.intuition.features.statistics.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.capybara.intuition.features.statistics.charts.model.data.DayDensity


@Dao
interface StatisticsDao {

    @Query(
        """
        SELECT
            session_stats.day AS day,
            1.0 * AVG(sessionDensity) AS dayDensity,
            SUM(sessionRounds) AS dayRoundCount,
            COUNT(sessionId) AS daySessionCount,
            SUM(sessionRoundsTime) / SUM(sessionRounds) AS averageDecisionTime,
            MAX(wr.physical) AS physical,
            MAX(wr.happiness) AS happiness,
            MAX(wr.confidence) AS confidence
        FROM (

            SELECT
                  s.id                          AS sessionId
                , s.startedAt                   AS startedAt
                , DATE(s.startedAt / 1000, 'unixepoch') AS day
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
        LEFT JOIN wellbeing_records wr
            ON DATE(wr.timestamp / 1000, 'unixepoch') = session_stats.day
            AND wr.profileId = :profileId
        GROUP BY session_stats.day
        ORDER BY session_stats.day ASC
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
