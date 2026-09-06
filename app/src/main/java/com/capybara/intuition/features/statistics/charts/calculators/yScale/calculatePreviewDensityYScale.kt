package com.capybara.intuition.features.statistics.charts.calculators.yScale


import com.capybara.intuition.features.statistics.charts.calculators.yScale.model.PreviewDensityYScaleModel
import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min

fun calculatePreviewDensityYScale (
    chartsData: List<AnalyticsChartPoint>
): PreviewDensityYScaleModel {

    val userMin = chartsData.minOf { it.userLineEma }
    val userMax = chartsData.maxOf { it.userLineEma }
    val baseline = chartsData.first().baseLine

    val dataMin = min(userMin, baseline)
    val dataMax = max(userMax, baseline)

    val dataRange = dataMax - dataMin

    val stepCandidates = listOf(1, 5, 10, 20, 25, 50)
    val rangePercent = ceil(dataRange * 100.0).toInt()
    val tickStep = stepCandidates.firstOrNull { step ->
        val tickCount = ceil(rangePercent.toDouble() / step).toInt() + 1
        tickCount in 2..3
    } ?: stepCandidates.last()

    val axisMin = (floor(100 * dataMin / tickStep) * tickStep).toInt()
    val axisMax = (ceil(100 * dataMax / tickStep) * tickStep).toInt()



    return PreviewDensityYScaleModel(
        axisMin = axisMin,
        axisMax = axisMax,
        tickStep = tickStep,
    )


}