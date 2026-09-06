package com.capybara.intuition.features.statistics.charts.calculators.yScale.model

data class PreviewDensityYScaleModel(

    val axisMin: Int,
    val axisMax: Int,
    val tickStep: Int,

) {
    fun clip(value: Double): Double =
        value.coerceIn(
            axisMin / 100.0,
            axisMax / 100.0
        )

    fun toY(
        value: Double,
        chartTop: Float,
        chartHeight: Float
    ): Float {

        if (axisMax == axisMin) {
            return chartTop + chartHeight / 2f
        }

        val fraction =
            ((value * 100.0) - axisMin) /
                    (axisMax - axisMin).toDouble()

        return chartTop +
                chartHeight -
                (fraction * chartHeight).toFloat()
    }
}