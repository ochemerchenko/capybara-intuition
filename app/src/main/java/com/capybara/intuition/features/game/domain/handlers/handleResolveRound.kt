
package com.capybara.intuition.features.game.domain.handlers

import android.util.Log
import com.capybara.intuition.features.game.domain.model.GameContext
import com.capybara.intuition.features.game.domain.model.RoundResult

fun handleResolveRound(
    state: GameContext
): HandlerResult {

    Log.d(
        "RESOLVE",
        "state = $state"
    )

    Log.d(
        "RESOLVE",
        "before result=${state.currentRound!!.result} " +
                "wins=${state.session!!.winCount}"
    )

    // =================================================
    // SESSION REQUIRED
    // =================================================
    val session = state.session
        ?: return HandlerResult(
            state = state
        )

    // =================================================
    // ROUND REQUIRED
    // =================================================

    val round = state.currentRound
        ?: return HandlerResult(
            state = state
        )

    // =================================================
    // REQUIRED ROUND DATA
    // =================================================

    val selectedResource = round.selectedResource
        ?: return HandlerResult(
            state = state
        )

    val correctResource = round.correctResource
        ?: return HandlerResult(
            state = state
        )

    // =================================================
// PLAYABLE TOPOLOGY CELLS
// =================================================

    val playableCells =
        round.topology.cells
            .filter { !it.blocked }
            .sortedBy { it.index }

    // =================================================
    // DETERMINE OCCUPANCY INDICES
    // =================================================

        val correctOccupancyIndex =
            round.gameSet.indexOf(
                correctResource
            )

        val selectedOccupancyIndex =
            round.gameSet.indexOf(
                selectedResource
            )

    // =================================================
    // VALIDATE OCCUPANCY INDICES
    // =================================================

        if (
            correctOccupancyIndex !in playableCells.indices
        ) {
            return HandlerResult(
                state = state
            )
        }

        if (
            selectedOccupancyIndex !in playableCells.indices
        ) {
            return HandlerResult(
                state = state
            )
        }

    // =================================================
    // RESOLVE RESULT
    // =================================================

    val isWin = selectedResource == correctResource
    val result =
        if (isWin) {
            RoundResult.WIN
        } else {
            RoundResult.LOSE
        }

    // =================================================
    // UPDATE ROUND
    // =================================================

    val updatedRound =
        round.copy(
            result = result,
            correctOccupancyIndex = correctOccupancyIndex,
            selectedOccupancyIndex = selectedOccupancyIndex,
            resultShownAt = System.currentTimeMillis()
        )

    // =================================================
    // PREVIOUS RESULT
    // =================================================
    val previousResult = session.isWinLastBet
    var lastBrokenStreak = session.lastBrokenStreak
    var winCount = session.winCount
    var loseCount = session.loseCount
    var winStreak = session.winStreak
    var loseStreak = session.loseStreak

    // =================================================
    // UPDATE SESSION
    // =================================================
    if (isWin) {
        winCount++
        if (previousResult == true) {
            winStreak++
        } else {
            lastBrokenStreak = loseStreak
            winStreak = 1
        }
        loseStreak = 0

    } else {
        loseCount++

        if (previousResult == false) {
            loseStreak++
        } else {
            lastBrokenStreak = winStreak
            loseStreak = 1
        }
        winStreak = 0
    }


    val updatedSession = session.copy(
        winCount = winCount,
        loseCount = loseCount,
        winStreak = winStreak,
        loseStreak = loseStreak,
        lastBrokenStreak = lastBrokenStreak,
        isWinLastBet = isWin,
    )



    Log.d(
        "Runtime",
        "Round resolved: $result"
    )

    // =================================================
    // RETURN UPDATED STATE
    // =================================================

    Log.d(
        "RESOLVE",
        "after result=${updatedRound.result} " +
                "wins=${updatedSession.winCount}"
    )

    return HandlerResult(
        state = state.copy(
            session = updatedSession,
            currentRound = updatedRound
        )
    )
}
