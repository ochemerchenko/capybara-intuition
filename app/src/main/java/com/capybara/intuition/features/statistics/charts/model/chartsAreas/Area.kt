package com.capybara.intuition.features.statistics.charts.model.chartsAreas

data class Area(
    val left: Float,
    val top: Float,
    val width: Float,
    val height: Float
)

fun Area.getDayCenterX(
    index: Int,
    daysCount: Int
): Float {

    val cellWidth = width / daysCount.toFloat()
    return left + (index + 0.5f) * cellWidth

}