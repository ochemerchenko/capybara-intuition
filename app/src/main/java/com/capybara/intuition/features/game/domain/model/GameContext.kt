package com.capybara.intuition.features.game.domain.model

import com.capybara.intuition.features.game.domain.round.GameRound

data class GameContext(
    val session: GameSession?,
    val currentRound: GameRound?,
    val roundIndex: Int = 0
)