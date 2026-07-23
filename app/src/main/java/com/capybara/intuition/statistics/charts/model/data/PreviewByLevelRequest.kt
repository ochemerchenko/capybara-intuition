package com.capybara.intuition.statistics.charts.model.data

import com.capybara.intuition.game.domain.model.GameMode

data class PreviewByLevelRequest(
    val level: Int,
    val gameModes: List<GameMode?>,
    val scope: StatisticsQueryContext
)