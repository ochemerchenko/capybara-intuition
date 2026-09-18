package com.capybara.intuition.features.achievements.data.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.capybara.intuition.features.achievements.domain.model.LevelMetric
import kotlinx.coroutines.flow.Flow

@Dao
interface AchievementsDao {

    @Query("""
        SELECT COUNT(*)
        FROM (
            SELECT 
                d as session_date,
                (ROW_NUMBER() OVER (ORDER BY d DESC) - 1) as idx
            FROM (
                SELECT DISTINCT DATE(startedAt / 1000, 'unixepoch') as d
                FROM sessions
                WHERE profileId = (SELECT id FROM profiles WHERE active = 1)
                  AND DATE(startedAt / 1000, 'unixepoch') <= :today
            )
        )
        WHERE session_date = DATE(:today, '-' || idx || ' days')
    """)
    fun getLastActiveDays(today: String): Flow<Long>

    @Query("""
        SELECT COUNT(DISTINCT DATE(startedAt / 1000, 'unixepoch'))
        FROM sessions
        WHERE profileId = (SELECT id FROM profiles WHERE active = 1)
          AND DATE(startedAt / 1000, 'unixepoch') <= :today
    """)
    fun getTotalActiveDays(today: String): Flow<Long>

    @Query("""
        SELECT level, MAX(daily_count) as value
        FROM (
            SELECT level, DATE(startedAt / 1000, 'unixepoch') as d, COUNT(*) as daily_count
            FROM sessions
            WHERE profileId = (SELECT id FROM profiles WHERE active = 1)
              AND DATE(startedAt / 1000, 'unixepoch') <= :today
            GROUP BY level, d
        )
        GROUP BY level
        HAVING value > 0
    """)
    fun getMaxSessionsPerDayPerLevel(today: String): Flow<List<LevelMetric>>

    @Query("""
        SELECT s.level, MAX(r.session_rounds) as value
        FROM sessions s
        INNER JOIN (
            SELECT sessionId, COUNT(*) as session_rounds
            FROM round_records
            GROUP BY sessionId
        ) r ON s.id = r.sessionId
        WHERE s.profileId = (SELECT id FROM profiles WHERE active = 1)
          AND DATE(s.startedAt / 1000, 'unixepoch') <= :today
        GROUP BY s.level
        HAVING value > 0
    """)
    fun getMaxRoundsPerSessionPerLevel(today: String): Flow<List<LevelMetric>>

    @Query("""
        SELECT s.level, MAX(r.correct_count) as value
        FROM sessions s
        INNER JOIN (
            SELECT sessionId, SUM(CASE WHEN result = 1 THEN 1 ELSE 0 END) as correct_count
            FROM round_records
            GROUP BY sessionId
        ) r ON s.id = r.sessionId
        WHERE s.profileId = (SELECT id FROM profiles WHERE active = 1)
          AND DATE(s.startedAt / 1000, 'unixepoch') <= :today
        GROUP BY s.level
        HAVING value > 0
    """)
    fun getMaxCorrectPerSessionPerLevel(today: String): Flow<List<LevelMetric>>

    @Query("""
        SELECT s.level, MAX(r.miss_count) as value
        FROM sessions s
        INNER JOIN (
            SELECT sessionId, SUM(CASE WHEN result = 0 THEN 1 ELSE 0 END) as miss_count
            FROM round_records
            GROUP BY sessionId
        ) r ON s.id = r.sessionId
        WHERE s.profileId = (SELECT id FROM profiles WHERE active = 1)
          AND DATE(s.startedAt / 1000, 'unixepoch') <= :today
        GROUP BY s.level
        HAVING value > 0
    """)
    fun getMaxMissesPerSessionPerLevel(today: String): Flow<List<LevelMetric>>

    @Query("""
        WITH session_rounds AS (
            SELECT sessionId, result, startedAt,
                   ROW_NUMBER() OVER (PARTITION BY sessionId ORDER BY startedAt) -
                   ROW_NUMBER() OVER (PARTITION BY sessionId, result ORDER BY startedAt) as grp
            FROM round_records
        ),
        streaks AS (
            SELECT sessionId, result, COUNT(*) as streak_len
            FROM session_rounds
            GROUP BY sessionId, result, grp
        )
        SELECT s.level, MAX(st.streak_len) as value
        FROM sessions s
        JOIN streaks st ON s.id = st.sessionId
        WHERE s.profileId = (SELECT id FROM profiles WHERE active = 1)
          AND DATE(s.startedAt / 1000, 'unixepoch') <= :today
          AND st.result = 1
        GROUP BY s.level
        HAVING value > 0
    """)
    fun getMaxWinStreakPerSessionPerLevel(today: String): Flow<List<LevelMetric>>

    @Query("""
        WITH session_rounds AS (
            SELECT sessionId, result, startedAt,
                   ROW_NUMBER() OVER (PARTITION BY sessionId ORDER BY startedAt) -
                   ROW_NUMBER() OVER (PARTITION BY sessionId, result ORDER BY startedAt) as grp
            FROM round_records
        ),
        streaks AS (
            SELECT sessionId, result, COUNT(*) as streak_len
            FROM session_rounds
            GROUP BY sessionId, result, grp
        )
        SELECT s.level, MAX(st.streak_len) as value
        FROM sessions s
        JOIN streaks st ON s.id = st.sessionId
        WHERE s.profileId = (SELECT id FROM profiles WHERE active = 1)
          AND DATE(s.startedAt / 1000, 'unixepoch') <= :today
          AND st.result = 0
        GROUP BY s.level
        HAVING value > 0
    """)
    fun getMaxLoseStreakPerSessionPerLevel(today: String): Flow<List<LevelMetric>>

    @Query("""
        WITH days AS (
            SELECT DISTINCT DATE(startedAt / 1000, 'unixepoch') as d
            FROM sessions
            WHERE profileId = (SELECT id FROM profiles WHERE active = 1)
              AND DATE(startedAt / 1000, 'unixepoch') <= :today
            ORDER BY d
        ),
        streak_groups AS (
            SELECT d, julianday(d) - ROW_NUMBER() OVER (ORDER BY d) as grp
            FROM days
        )
        SELECT COALESCE(MAX(streak), 0)
        FROM (
            SELECT COUNT(*) as streak
            FROM streak_groups
            GROUP BY grp
        )
    """)
    fun getMaxStreak(today: String): Flow<Long>

    @Query("""
        WITH days AS (
            SELECT DISTINCT DATE(startedAt / 1000, 'unixepoch') as d
            FROM sessions
            WHERE profileId = (SELECT id FROM profiles WHERE active = 1)
              AND DATE(startedAt / 1000, 'unixepoch') <= :today
            ORDER BY d
        ),
        day_breaks AS (
            SELECT julianday(d) - julianday(LAG(d) OVER (ORDER BY d)) - 1 as break_len
            FROM days
        )
        SELECT CAST(COALESCE(MAX(break_len), 0) AS INTEGER)
        FROM day_breaks
    """)
    fun getMaxBreak(today: String): Flow<Long>
}
