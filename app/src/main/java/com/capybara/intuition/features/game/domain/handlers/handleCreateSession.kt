package com.capybara.intuition.features.game.domain.handlers

import android.util.Log
import com.capybara.intuition.features.game.data.repository.NotebookRepository
import com.capybara.intuition.features.game.domain.model.GameContext
import com.capybara.intuition.features.game.domain.model.GameSession
import com.capybara.intuition.features.game.domain.model.Resource
import com.capybara.intuition.features.profile.domain.model.Profile
import java.util.Objects.toString
import java.util.UUID

suspend fun handleCreateSession(
    profile: Profile,
    notebookRepository: NotebookRepository,

): HandlerResult {

    // =================================================
    // LOAD RUNTIME RESOURCES
    // =================================================


    val resources: List<Resource> =
        notebookRepository
            .getResources(
                notebookId = profile.activeNotebookId
            )
    val _s_res = toString(resources)
    Log.d("SUBMIT", "handleCreateSession: resources: $_s_res ")

    // =================================================
    // CREATE RUNTIME SESSION
    // =================================================

    val session = GameSession(
        // =========================================
        // RUNTIME EXECUTION CONTEXT
        // =========================================
        profile = profile,
        resources = resources,

        // =========================================
        // SESSION SHORT STATISTICS
        // =========================================
        winCount = 0,
        loseCount = 0,
        isWinLastBet = null,
        winStreak = 0,
        loseStreak = 0,
        lastBrokenStreak = 0,

        // =========================================
        // LIFECYCLE
        // =========================================
        startedAt = System.currentTimeMillis(),
        finishedAt = null
    )






    // =================================================
    // RETURN RESULT
    // =================================================

    return HandlerResult(
        state = GameContext(
            session = session,
            currentRound = null,
            roundIndex = 0
        ),
        error = null
    )
}