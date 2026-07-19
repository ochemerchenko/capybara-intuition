package com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data

import com.capybara.intuition.game.domain.model.GameMode

data class PreviewByLevelResponse(
    val level: Int,
    val datasets: LinkedHashMap<GameMode?, List<DayDensity>>
)