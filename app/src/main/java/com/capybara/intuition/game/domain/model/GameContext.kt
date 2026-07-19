package com.capybara.intuition.game.domain.model

import com.capybara.intuition.game.domain.round.GameRound

data class GameContext(
    val session: GameSession?,
    val currentRound: GameRound?,
    val roundIndex: Int = 0
)