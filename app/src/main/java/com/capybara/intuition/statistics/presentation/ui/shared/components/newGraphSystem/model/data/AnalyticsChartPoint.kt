package com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data

import java.time.LocalDate

data class AnalyticsChartPoint(

    val day: LocalDate,
    val weekStart: Boolean,
    val userLine: Double,
    val baseLine: Double,
    val upperCorridorLine: Double,
    val lowerCorridorLine: Double,
    val averageDayDecisionTime: Double,
    val sessionCount: Double,

    val userLineEma: Double,
    val upperCorridorLineEma: Double,
    val lowerCorridorLineEma: Double,
    val averageDayDecisionTimeEma: Double

)