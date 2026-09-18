package com.capybara.intuition.features.game.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.capybara.intuition.features.game.data.local.db.entity.ObservedGameCardEntity
import com.capybara.intuition.features.game.data.local.db.entity.ObservedRoundEntity

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
