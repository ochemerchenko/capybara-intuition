package com.capybara.intuition.features.statistics.charts.model.data

import com.capybara.intuition.features.game.domain.model.GameMode

data class PreviewByLevelRequest(
    val level: Int,
    val gameModes: List<GameMode?>,
    val scope: StatisticsQueryContext
)