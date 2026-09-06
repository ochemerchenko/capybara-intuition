package com.capybara.intuition.features.statistics.charts.calculators.yScale

import com.capybara.intuition.features.statistics.charts.calculators.yScale.model.ActivityYScaleModel
import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint
import kotlin.math.ceil

fun calculateActivityYScale (
    chartsData: List<AnalyticsChartPoint>
): ActivityYScaleModel {
    val dataMax = chartsData.maxOf { it.sessionCount }

    val tickStep = when {
        dataMax <= 10.0 -> 1.0
        dataMax <= 25.0 -> 5.0
        dataMax <= 50.0 -> 10.0
        dataMax <= 100.0 -> 25.0
        dataMax <= 200.0 -> 50.0
        dataMax <= 500.0 -> 100.0
        else -> 200.0
    }

    val axisMin = 0.0
    val axisMax = ceil(dataMax / tickStep) * tickStep

    return ActivityYScaleModel(
        axisMin = axisMin,
        axisMax = axisMax,
        tickStep = tickStep
    )
}