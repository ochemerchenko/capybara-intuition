package com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.calculators.yScale


import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.calculators.yScale.model.DecisionTimeYScaleModel
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.calculators.yScale.model.TimeMeasureUnit
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data.AnalyticsChartPoint
import kotlin.math.ceil
import kotlin.math.floor


fun calculateDecisionTimeYScale (
    chartsData: List<AnalyticsChartPoint>
): DecisionTimeYScaleModel {
    val dataMin = chartsData.minOf { it.averageDayDecisionTime }
    val dataMax = chartsData.maxOf { it.averageDayDecisionTime }

    val dataRange = dataMax - dataMin

    val measureUnit =
        if (dataRange < 100_000)
            TimeMeasureUnit.SECOND
        else
            TimeMeasureUnit.MINUTE

    val stepCandidates = when (measureUnit) {
        TimeMeasureUnit.SECOND ->
            listOf(
                100.0,    // 0.1 c
                500.0,    // 0.5 c
                1000.0,   // 1 c
                5000.0,   // 5 c
                10000.0   // 10 c
            )
        TimeMeasureUnit.MINUTE ->
            listOf(
                60000.0,   // 1 хв
                300000.0,  // 5 хв
                600000.0   // 10 хв
            )
    }

    val tickStep = stepCandidates.firstOrNull { step ->
        val tickCount = ceil(dataRange/ step).toInt() + 1
        tickCount in 3..7
    } ?: stepCandidates.last()

    val axisMin = (floor(dataMin / tickStep) * tickStep)
    val axisMax = (ceil(dataMax / tickStep) * tickStep)

    return DecisionTimeYScaleModel(
        axisMin = axisMin,
        axisMax = axisMax,
        tickStep = tickStep,
        measureUnit = measureUnit
    )
}
