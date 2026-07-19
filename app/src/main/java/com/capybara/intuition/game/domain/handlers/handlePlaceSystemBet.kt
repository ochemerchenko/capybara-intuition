package com.capybara.intuition.game.domain.handlers

import android.util.Log
import com.capybara.intuition.game.domain.model.GameContext

fun handlePlaceSystemBet(
    state: GameContext
): HandlerResult {
    Log.d(
        "Runtime",
        "handlePlaceSystemBet"
    )

    // =================================================
    // ROUND REQUIRED
    // =================================================
    val round = state.currentRound ?: return HandlerResult(
        state = state
    )

    // =================================================
    // SELECT SYSTEM RESOURCE
    // =================================================

    val correctResource = round.gameSet
        .random()

    // =================================================
    // UPDATE ROUND
    // =================================================

    val updatedRound = round.copy(
        correctResource = correctResource
    )

    Log.d(
        "Runtime",
        "System selected: " +
                correctResource.name
    )

    // =================================================
    // RETURN UPDATED STATE
    // =================================================

    return HandlerResult(
        state = state.copy(
            currentRound = updatedRound
        )
    )
}