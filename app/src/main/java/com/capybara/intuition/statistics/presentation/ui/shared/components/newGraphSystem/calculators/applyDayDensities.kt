package com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.calculators

import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data.AnalyticsChartPoint
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data.DayDensity
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