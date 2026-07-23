package com.capybara.intuition.statistics.charts.calculators.yScale.model

data class DecisionTimeYScaleModel(
    val axisMin: Double,
    val axisMax: Double,
    val tickStep: Double,
    val measureUnit: TimeMeasureUnit
) {
    fun clip(value: Double): Double =
        value.coerceIn(
            axisMin,
            axisMax
        )

    fun toY(
        value: Double,
        chartTop: Float,
        chartHeight: Float
    ): Float {

        if (axisMax == axisMin) {
            return (chartTop + chartHeight / 2.0).toFloat()
        }

        val fraction =
            (value - axisMin) / (axisMax - axisMin)

        return (chartTop +
                chartHeight -
                (fraction * chartHeight)).toFloat()
    }
}