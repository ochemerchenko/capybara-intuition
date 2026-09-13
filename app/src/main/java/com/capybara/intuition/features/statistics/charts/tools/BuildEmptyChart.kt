package com.capybara.intuition.features.statistics.charts.tools

import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint
import java.time.Instant
import java.time.ZoneId
import java.time.temporal.WeekFields
import java.util.Locale

fun buildEmptyChart(
    dateFrom: Long,
    dateNow: Long,
    level: Int
): List<AnalyticsChartPoint> {
    val from = Instant
        .ofEpochMilli(dateFrom)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()

    val to = Instant
        .ofEpochMilli(dateNow)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()

    val baseLineValue = 1.0 / level

    val result = mutableListOf<AnalyticsChartPoint>()

    var current = from

    while (!current.isAfter(to)) {

        result.add(
            AnalyticsChartPoint(
                day = current,
                weekStart = current.dayOfWeek == WeekFields.of(Locale.getDefault()).firstDayOfWeek,
                userLine = baseLineValue,
                baseLine = baseLineValue,
                upperCorridorLine = baseLineValue,
                lowerCorridorLine = baseLineValue,
                averageDayDecisionTime = 0.0,
                userLineEma = 0.0,
                upperCorridorLineEma = 0.0,
                lowerCorridorLineEma = 0.0,
                averageDayDecisionTimeEma = 0.0,
                sessionCount = 0.0,
                physical = 0.5,
                happiness = 0.5,
                confidence = 0.5
            )
        )

        current = current.plusDays(1)
    }

    return result
}
