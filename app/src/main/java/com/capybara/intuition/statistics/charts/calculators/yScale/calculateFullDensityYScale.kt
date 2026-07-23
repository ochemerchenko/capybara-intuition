package com.capybara.intuition.statistics.charts.calculators.yScale

import android.util.Log
import com.capybara.intuition.statistics.charts.calculators.yScale.model.FullDensityYScaleModel
import com.capybara.intuition.statistics.charts.model.data.AnalyticsChartPoint
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min

fun calculateFullDensityYScale (
    chartsData: List<AnalyticsChartPoint>
): FullDensityYScaleModel {
    val userMin = chartsData.minOf { it.userLineEma }
    val userMax = chartsData.maxOf { it.userLineEma }
    val baseline = chartsData.first().baseLine

    val dataMin = min(userMin, baseline)
    val dataMax = max(userMax, baseline)

    val dataRange = dataMax - dataMin

    val stepCandidates = listOf(1, 5, 10)
    val rangePercent = ceil(dataRange * 100.0).toInt()
    val tickStep = stepCandidates.firstOrNull { step ->
        val tickCount = ceil(rangePercent.toDouble() / step).toInt() + 1
        tickCount in 3..7
    } ?: stepCandidates.last()

    val axisMin = (floor(100 * dataMin / tickStep) * tickStep).toInt()
    val axisMax = (ceil(100 * dataMax / tickStep) * tickStep).toInt()

    Log.d(
        "NEW_GRAPH",
        "userMin=$userMin, " +
                "userMax=$userMax, " +
                "baseline=$baseline, " +
                "dataMin=$dataMin, " +
                "dataMax=$dataMax, " +
                "rangePercent=$rangePercent, " +
                "tickStep=$tickStep, " +
                "axisMin=$axisMin, " +
                "axisMax=$axisMax"
    )

    return FullDensityYScaleModel(
        axisMin = axisMin,
        axisMax = axisMax,
        tickStep = tickStep,
    )


}