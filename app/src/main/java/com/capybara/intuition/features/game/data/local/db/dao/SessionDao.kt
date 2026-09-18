package com.capybara.intuition.features.game.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.capybara.intuition.features.game.data.local.db.entity.GameSessionEntity

@Dao
interface SessionDao {

    // =====================================================
    // INSERT / UPDATE
    // =====================================================

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insert(
        session: GameSessionEntity
    ): Long

    @Update
    suspend fun update(
        session: GameSessionEntity
    )

    // =====================================================
    // READ
    // =====================================================

    @Query(
        """
        SELECT *
        FROM sessions
        WHERE id = :id
        """
    )
    suspend fun getById(
        id: String
    ): GameSessionEntity?


    // =====================================================
    // STATS
    // =====================================================

    @Query(
        """
        SELECT COUNT(*)
        FROM sessions
        """
    )
    suspend fun countSessions():
            Int

    // =====================================================
    // DEBUG / MAINTENANCE
    // =====================================================

    @Query(
        """
        DELETE FROM sessions
        """
    )
    suspend fun deleteAll()

    @Query("""
        DELETE FROM sessions
        WHERE profileId = (
            SELECT id
            FROM profiles
            WHERE type = 'TESTER'
        )
        """)
    suspend fun deleteTesterDataset()

    @Insert(
        onConflict =
            OnConflictStrategy.REPLACE
    )
    suspend fun insertSessions(
        sessions: List<GameSessionEntity>
    )

    @Query(
        """
    SELECT COALESCE(MAX(id), 0)
    FROM sessions
    """
    )
    suspend fun getLastSessionId(): Long

    @Query("""
        DELETE FROM sessions
        WHERE startedAt < :threshold
    """)
    suspend fun deleteSessionsStartedBefore(
        threshold: Long
    ): Int


    @Query(
        """
    SELECT *
    FROM sessions
    WHERE startedAt >= :dayStart
      AND startedAt < :dayEnd
    ORDER BY startedAt ASC
    """
    )
    suspend fun getSessionsForExport(
        dayStart: Long,
        dayEnd: Long
    ): List<GameSessionEntity>

}
