package com.capybara.intuition.statistics.charts.model.data

import com.capybara.intuition.game.domain.model.GameMode

data class PreviewByModeRequest(
    val mode: GameMode?,
    val levels: List<Int>,
    val scope: StatisticsQueryContext
)