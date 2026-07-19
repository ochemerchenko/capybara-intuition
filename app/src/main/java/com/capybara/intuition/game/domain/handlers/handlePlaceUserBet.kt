package com.capybara.intuition.game.domain.handlers

import android.util.Log
import com.capybara.intuition.game.domain.model.GameContext
import com.capybara.intuition.game.domain.model.Resource


fun handlePlaceUserBet(
    state: GameContext,
    selectedResource: Resource
): HandlerResult {

    Log.d(
        "Runtime",
        "handlePlaceUserBet"
    )

    // =================================================
    // ROUND REQUIRED
    // =================================================

    val round = state.currentRound
        ?: return HandlerResult(
            state = state
        )

    // =================================================
    // UPDATE ROUND
    // =================================================

    val updatedRound = round.copy(
        selectedResource = selectedResource,
        userSelectedAt = System.currentTimeMillis(),
        firstUserActionAt = round.firstUserActionAt ?: System.currentTimeMillis()
    )

    Log.d(
        "Runtime",
        "User selected: " +
                selectedResource.name
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