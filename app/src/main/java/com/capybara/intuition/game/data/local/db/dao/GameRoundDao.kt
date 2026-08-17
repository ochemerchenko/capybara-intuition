package com.capybara.intuition.game.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capybara.intuition.game.data.local.db.entity.GameSessionEntity
import com.capybara.intuition.game.data.local.db.entity.ObservedGameCardEntity
import com.capybara.intuition.game.data.local.db.entity.ObservedRoundEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.ZoneOffset

@Dao
interface GameRoundDao {

    // =====================================================
    // INSERT
    // =====================================================
    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertRound(
        record: ObservedRoundEntity
    ): Long

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertRoundCards(
        cards: List<ObservedGameCardEntity>
    )

    // =====================================================
    // QUERIES
    // =====================================================

    @Query(
        """
        SELECT *
        FROM round_records
        WHERE
                sessionId = :sessionId
            AND result IS NOT NULL
        ORDER BY startedAt ASC
        """
    )
    suspend fun getRoundsBySession(
        sessionId: String
    ): List<ObservedRoundEntity>

    @Query(
        """
        SELECT COUNT(*)
        FROM round_records
        WHERE
                sessionId = :sessionId
            AND result IS NOT NULL
        """
    )
    suspend fun getTotalRounds(
        sessionId: String
    ): Int

    @Query(
        """
        SELECT COUNT(*)
        FROM round_records
        WHERE
            sessionId = :sessionId
        AND result = 'WIN'
        """
    )
    suspend fun getWinCount(
        sessionId: String
    ): Int

    @Query(
        """
        SELECT COUNT(*)
        FROM round_records
        WHERE
            sessionId = :sessionId
        AND result = 'LOSE'
        """
    )
    suspend fun getLoseCount(
        sessionId: String
    ): Int


    @Query(
        """
        SELECT
            CASE

                WHEN COUNT(*) = 0
                THEN 0

                ELSE
                    (
                        100 *
                        SUM(
                            CASE
                                WHEN result = 'WIN'
                                THEN 1
                                ELSE 0
                            END
                        )
                    ) / COUNT(*)

            END

        FROM round_records

        WHERE
            sessionId = :sessionId

        AND result IN (
            'WIN',
            'LOSE'
        )
        """
    )
    suspend fun getSessionDensity(
        sessionId: String
    ): Int

    // =====================================================
    // MAINTENANCE
    // =====================================================

    @Query(
        """
        DELETE FROM round_records
        """
    )
    suspend fun deleteAll()

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertRounds(
        rounds: List<ObservedRoundEntity>
    )

    @Query(
        """
            SELECT *
            FROM round_records rr
            JOIN sessions s ON s.id = rr.sessionId
            WHERE s.startedAt >= :dayStart
              AND s.startedAt < :dayEnd
        """
    )
    suspend fun getRoundsForExport(
        dayStart: Long,
        dayEnd: Long
    ): List<ObservedRoundEntity>

    @Query(
        """
            SELECT *
            FROM round_cards rc
            JOIN round_records rr ON rr.id = rc.roundId
            JOIN sessions s ON s.id = rr.sessionId
            WHERE s.startedAt >= :dayStart
              AND s.startedAt < :dayEnd
        """
    )
    suspend fun getGameCardsForExport(
        dayStart: Long,
        dayEnd: Long
    ): List<ObservedGameCardEntity>

}