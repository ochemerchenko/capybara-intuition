package com.capybara.intuition.features.statistics.charts.model.data

import com.capybara.intuition.features.game.domain.model.GameMode

data class PreviewByModeResponse(
    val mode: GameMode?,
    val datasets: LinkedHashMap<Int, List<DayDensity>>
)