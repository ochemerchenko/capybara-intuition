package com.capybara.intuition.features.game.domain.round

import com.capybara.intuition.features.game.domain.model.Resource
import com.capybara.intuition.features.game.domain.model.RoundResult
import com.capybara.intuition.features.game.domain.model.RoundType
import com.capybara.intuition.features.game.domain.model.topology.Topology


data class GameRound(

    val topology: Topology,
    val correctOccupancyIndex: Int?,
    val selectedOccupancyIndex: Int?,
    // =====================================================
    // GAME SET
    // =====================================================
    /**
     * Semantic playable subset
     * participating in current round.
     */
    val gameSet: List<Resource>,

    // =====================================================
    // ROUND META
    // =====================================================
    val roundType: RoundType,

    // =====================================================
    // SYSTEM BET
    // =====================================================
    /**
     * Resource selected by system.
     */
    val correctResource: Resource?,

    // =====================================================
    // USER BET
    // =====================================================

    /**
     * Resource selected by user.
     */
    val selectedResource: Resource?,

    // =====================================================
    // RESULT
    // =====================================================
    val result: RoundResult?,

    // =====================================================
    // TIMING
    // =====================================================
    val startedAt: Long,
    val userSelectedAt: Long?,
    val resultShownAt: Long?,
    val firstUserActionAt: Long?
)