package com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.calculators.yScale.model

data class ActivityYScaleModel(

    val axisMin: Double,
    val axisMax: Double,
    val tickStep: Double,

) {

    fun toY(
        value: Double,
        chartTop: Float,
        chartHeight: Float
    ): Double {

        if (axisMax == axisMin) {
            return chartTop + chartHeight / 2.0
        }

        val fraction = (value - axisMin) / (axisMax - axisMin)

        return chartTop + chartHeight - fraction * chartHeight
    }

}