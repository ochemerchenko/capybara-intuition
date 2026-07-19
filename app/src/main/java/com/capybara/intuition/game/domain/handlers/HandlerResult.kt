package com.capybara.intuition.game.domain.handlers

import com.capybara.intuition.game.domain.model.GameContext

data class HandlerResult(
    val state: GameContext,
    val error: String? = null
)