package com.capybara.intuition.features.statistics.charts.calculators

import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint
import com.capybara.intuition.features.statistics.charts.model.data.DayDensity
import java.time.LocalDate

fun applyDayDensities(
    chartPoints: List<AnalyticsChartPoint>,
    dayDensities: List<DayDensity>
): List<AnalyticsChartPoint> {

    val densityByDay = dayDensities.associateBy {
        LocalDate.parse(
            it.day
        )
    }

    return chartPoints.map { point ->
        val density = densityByDay[point.day]
        if (density == null) {
            point
        } else {
            point.copy(
                userLine = density.dayDensity,
                averageDayDecisionTime = density.averageDecisionTime,
                sessionCount = density.daySessionCount.toDouble()
            )
        }
    }
}