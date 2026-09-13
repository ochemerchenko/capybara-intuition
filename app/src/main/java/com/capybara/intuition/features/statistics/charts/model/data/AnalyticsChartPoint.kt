package com.capybara.intuition.features.statistics.charts.model.data

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
    val averageDayDecisionTimeEma: Double,

    val physical: Double,
    val happiness: Double,
    val confidence: Double,

    val physicalEma: Double = 0.5,
    val happinessEma: Double = 0.5,
    val confidenceEma: Double = 0.5

)
