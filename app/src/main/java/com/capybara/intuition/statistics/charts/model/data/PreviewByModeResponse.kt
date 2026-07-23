package com.capybara.intuition.statistics.charts.model.data

import com.capybara.intuition.game.domain.model.GameMode

data class PreviewByModeResponse(
    val mode: GameMode?,
    val datasets: LinkedHashMap<Int, List<DayDensity>>
)