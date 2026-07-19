package com.capybara.intuition.game.domain.handlers

import android.util.Log
import com.capybara.intuition.game.domain.model.GameContext
import com.capybara.intuition.game.domain.model.GameMode
import com.capybara.intuition.game.domain.model.Resource
import com.capybara.intuition.game.domain.model.RoundType
import com.capybara.intuition.game.domain.round.GameRound

// =====================================================
// HANDLER
// =====================================================

fun handleCreateRound(
    state: GameContext
): HandlerResult {

    Log.d(
        "Runtime",
        "handleCreateRound"
    )

    // =================================================
    // SESSION REQUIRED
    // =================================================
    val session = state.session
        ?: return HandlerResult(
            state = state
        )

    // =================================================
    // CREATE GAME SET
    // =================================================
    val gameSet = createGameSet(
        resources = session.resources,
        size = session.profile.currentLevel
    )



    // =================================================
    // DETERMINE ROUND TYPE
    // =================================================
    val roundType = determineRoundType(
        mode = session.profile.preferredMode
    )

    // =================================================
    // CREATE ROUND
    // =================================================
    val round = GameRound(

        topology = session.profile.activeTopology,
        correctOccupancyIndex = null,
        selectedOccupancyIndex = null,
        // =========================================
        // GAME SET
        // =========================================
        gameSet = gameSet,

        // =========================================
        // ROUND META
        // =========================================
        roundType = roundType,

        // =========================================
        // SYSTEM BET
        // =========================================
        correctResource = null,

        // =========================================
        // USER BET
        // =========================================
        selectedResource = null,

        // =========================================
        // RESULT
        // =========================================
        result = null,

        // =========================================
        // TIMING
        // =========================================
        startedAt = System.currentTimeMillis(),
        userSelectedAt = null,
        resultShownAt = null,
        firstUserActionAt = null
    )

    Log.d(

        "Runtime",

        "Round created: " +
                "type=$roundType, " +
                "gameSet=${gameSet.size}"
    )

    // =================================================
    // RETURN UPDATED STATE
    // =================================================

    return HandlerResult(
        state = state.copy(
            currentRound = round,
            roundIndex = state.roundIndex + 1
        )
    )
}

// =====================================================
// GAME SET
// =====================================================

private fun createGameSet(
    resources: List<Resource>,
    size: Int
): List<Resource> {
    return resources
        .shuffled()
        .take(size)
}



// =====================================================
// ROUND TYPE
// =====================================================

private fun determineRoundType(
    mode: GameMode
): RoundType {
    return when (mode) {
        GameMode.AFTER -> {
            RoundType.AFTER
        }
        GameMode.BEFORE -> {
            RoundType.BEFORE
        }
        GameMode.HYBRID -> {
            listOf(
                RoundType.AFTER,
                RoundType.BEFORE
            ).random()
        }
    }
}