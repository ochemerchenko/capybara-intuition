package com.capybara.intuition.statistics.charts.model.data

import com.capybara.intuition.game.domain.model.GameMode

data class PreviewByLevelResponse(
    val level: Int,
    val datasets: LinkedHashMap<GameMode?, List<DayDensity>>
)