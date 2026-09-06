package com.capybara.intuition.features.game.domain.handlers

import com.capybara.intuition.features.game.domain.model.GameContext

data class HandlerResult(
    val state: GameContext,
    val error: String? = null
)