package com.capybara.intuition.statistics.charts.calculators.graphLayoutsModels

import com.capybara.intuition.statistics.charts.model.chartsAreas.Area
import com.capybara.intuition.statistics.charts.model.chartsAreas.PreviewDensityGraphLayoutModel

fun getPreviewDensityGraphLayoutModel (
    canvasWidth: Float,
    canvasHeight: Float
): PreviewDensityGraphLayoutModel {

    val titleHeight = 40f
    val iconWidth = 40f
    val yAxisWidth = 60f

    return PreviewDensityGraphLayoutModel(
        titleArea = Area(
            top = 0f,
            left = 0f,
            width = canvasWidth - iconWidth,
            height = titleHeight
        ),
        iconArea = Area(
            top = 0f,
            left = canvasWidth - iconWidth,
            width = iconWidth,
            height = titleHeight

        ),
        yAxisArea = Area(
            top = titleHeight,
            left = 0f,
            width = yAxisWidth,
            height = canvasHeight - titleHeight

        ),
        chartArea = Area(
            top = titleHeight,
            left = yAxisWidth,
            width = canvasWidth - yAxisWidth,
            height = canvasHeight - titleHeight
        ),
    )
}
