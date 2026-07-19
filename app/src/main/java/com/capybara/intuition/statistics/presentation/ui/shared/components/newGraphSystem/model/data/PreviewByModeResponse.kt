package com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data

import com.capybara.intuition.game.domain.model.GameMode

data class PreviewByModeResponse(
    val mode: GameMode?,
    val datasets: LinkedHashMap<Int, List<DayDensity>>
)