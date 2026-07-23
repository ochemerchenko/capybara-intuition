package com.capybara.intuition.statistics.charts.calculators

import com.capybara.intuition.statistics.charts.model.data.AnalyticsChartPoint
import com.capybara.intuition.statistics.charts.model.data.DayDensity
import java.time.LocalDate
import kotlin.math.sqrt


fun enrichWithCorridors(
    chartPoints: List<AnalyticsChartPoint>,
    dayDensities: List<DayDensity>
): List<AnalyticsChartPoint> {

    val densityByDay = dayDensities.associateBy {
        LocalDate.parse(it.day)
    }

    return chartPoints.map { point ->

        val density = densityByDay[point.day]

        if (density == null || density.dayRoundCount == 0) {

            point.copy(
                upperCorridorLine = point.baseLine,
                lowerCorridorLine = point.baseLine
            )

        } else {

            val p = point.baseLine

            val sigma = sqrt(
                p * (1.0 - p) / density.dayRoundCount
            )

            val corridor = 2.0 * sigma

            point.copy(
                upperCorridorLine = p + corridor,
                lowerCorridorLine = p - corridor
            )
        }
    }
}
