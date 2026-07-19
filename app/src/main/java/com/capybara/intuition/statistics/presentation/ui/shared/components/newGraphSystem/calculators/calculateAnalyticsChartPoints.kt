package com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.calculators

import android.util.Log
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.EmaTarget
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data.AnalyticsChartPoint
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data.DayDensity
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.tools.buildEmptyChart

fun calculateAnalyticsChartPoints(
    level: Int,
    dayDensities: List<DayDensity>,
    dateFrom: Long,
    dateNow: Long,
    emaModelingPeriod: Int,
    analyticPeriod: Int
): List<AnalyticsChartPoint> {

    Log.d(
        "PIPELINE",
        "calculateAnalyticsChartPoints hash=${System.identityHashCode(dayDensities)} " +
                "size=${dayDensities.size}"
    )

    if (dayDensities.isEmpty()) {
        return emptyList()
    }

    var analyticsChartPoints = buildEmptyChart(
        dateFrom = dateFrom,
        dateNow = dateNow,
        level = level
    )

    if (dayDensities.isNotEmpty()) {
        analyticsChartPoints = applyDayDensities(
            chartPoints = analyticsChartPoints,
            dayDensities = dayDensities
        )
    }

    analyticsChartPoints.forEach {
        Log.d("POINT", "analyticsChartPoints = ${it.toString()}")
    }


    analyticsChartPoints = enrichWithEma(
        chartPoints = analyticsChartPoints,
        target = EmaTarget.USER_LINE,
        emaModelingPeriod = emaModelingPeriod
    )

    analyticsChartPoints = enrichWithCorridors(
        chartPoints = analyticsChartPoints,
        dayDensities = dayDensities
    )

    analyticsChartPoints = enrichWithEma(
        chartPoints = analyticsChartPoints,
        target = EmaTarget.UPPER_CORRIDOR,
        emaModelingPeriod = emaModelingPeriod
    )

    analyticsChartPoints = enrichWithEma(
        chartPoints = analyticsChartPoints,
        target = EmaTarget.LOWER_CORRIDOR,
        emaModelingPeriod = emaModelingPeriod
    )

    analyticsChartPoints = enrichWithEma(
        chartPoints = analyticsChartPoints,
        target = EmaTarget.DECISION_TIME,
        emaModelingPeriod = emaModelingPeriod
    )




    analyticsChartPoints.forEachIndexed { index, point ->

        Log.d(
            "TARTAR",
            "[$index] " +
                    "base=${point.baseLine} " +
                    "rawUpper=${point.upperCorridorLine} " +
                    "rawLower=${point.lowerCorridorLine} " +
                    "rawSum=${point.upperCorridorLine + point.lowerCorridorLine} " +
                    "emaUpper=${point.upperCorridorLineEma} " +
                    "emaLower=${point.lowerCorridorLineEma} " +
                    "emaSum=${point.upperCorridorLineEma + point.lowerCorridorLineEma}"
        )
    }

    analyticsChartPoints = trimAnalyticPeriod(
        analyticsChartPoints = analyticsChartPoints,
        analyticPeriod = analyticPeriod
    )

    return analyticsChartPoints
}