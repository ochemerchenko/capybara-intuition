package com.capybara.intuition.features.statistics.charts.model.data

import com.capybara.intuition.features.game.domain.model.GameMode

data class PreviewByModeRequest(
    val mode: GameMode?,
    val levels: List<Int>,
    val scope: StatisticsQueryContext
)