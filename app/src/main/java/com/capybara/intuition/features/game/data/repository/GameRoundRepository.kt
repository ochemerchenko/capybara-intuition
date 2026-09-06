package com.capybara.intuition.features.game.data.repository

import android.util.Log
import androidx.room.withTransaction
import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.features.game.data.local.db.dao.GameRoundDao
import com.capybara.intuition.features.game.data.local.db.entity.ObservedGameCardEntity
import com.capybara.intuition.features.game.data.local.db.entity.ObservedRoundEntity
import com.capybara.intuition.features.game.data.mapper.GameModeMapper
import com.capybara.intuition.features.game.data.mapper.RoundResultMapper
import com.capybara.intuition.features.game.data.mapper.RoundTypeMapper
import com.capybara.intuition.features.game.domain.round.GameRound

class GameRoundRepository(

    private val dao: GameRoundDao,
    private val database: AppDatabase

) {

    suspend fun insert(
        round: GameRound,
        sessionId: Long,
        level: Int,
        mode: String
    ) {
        Log.d("SUBMIT","GameRoundRepository: insert called")
        val result = round.result ?: return
        val correctOccupancyIndex = round.correctOccupancyIndex ?: return
        val selectedOccupancyIndex = round.selectedOccupancyIndex ?: return

        database.withTransaction {

            // ============================================
            // ROUND ENTITY
            // ============================================

            val roundId = dao.insertRound(

                ObservedRoundEntity(

                    sessionId = sessionId,
                    roundType = RoundTypeMapper.toId(
                        round.roundType
                    ),
                    level = level,
                    startedAt = round.startedAt,
                    finishedAt = round.resultShownAt ?: System.currentTimeMillis(),
                    result = RoundResultMapper.toBoolean(result),
                    userSelectedAt = round.userSelectedAt,
                )
            )

            // ============================================
            // ROUND CARDS
            // ============================================
            val cardEntities =
                round.gameSet.mapIndexed { index, resource ->
                    ObservedGameCardEntity(
                        roundId = roundId,
                        resourceId = resource.id,
                        topologyCellIndex = index,
                        isCorrect = index == correctOccupancyIndex,
                        isSelected = index == selectedOccupancyIndex
                    )
                }

            dao.insertRoundCards(
                cardEntities
            )
        }
    }
}