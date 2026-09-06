package com.capybara.intuition.features.statistics.charts.model.data

import com.capybara.intuition.features.game.domain.model.GameMode

data class PreviewByLevelResponse(
    val level: Int,
    val datasets: LinkedHashMap<GameMode?, List<DayDensity>>
)