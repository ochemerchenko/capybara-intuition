package com.capybara.intuition.features.statistics.charts.calculators.graphLayoutsModels

import com.capybara.intuition.features.statistics.charts.model.chartsAreas.Area
import com.capybara.intuition.features.statistics.charts.model.chartsAreas.FullDensityGraphLayoutModel

fun getFullDensityGraphLayoutModel (
    canvasWidth: Float,
    canvasHeight: Float
): FullDensityGraphLayoutModel {

    val titleWidth = 40f
    val xAxisHeight = 60f
    val yAxisWidth = 60f
    val heightBorder = 4f


    return FullDensityGraphLayoutModel(
        titleArea = Area(
            top = heightBorder,
            left = 0f,
            width = titleWidth,
            height = canvasHeight - xAxisHeight - heightBorder
        ),
        yAxisArea = Area(
            top = heightBorder,
            left = titleWidth,
            width = yAxisWidth,
            height = canvasHeight - xAxisHeight - heightBorder

        ),
        chartArea = Area(
            top = heightBorder,
            left = titleWidth + yAxisWidth,
            width = canvasWidth - titleWidth - yAxisWidth,
            height = canvasHeight - xAxisHeight - heightBorder

        ),
        startPointArea = Area(
            top = canvasHeight - xAxisHeight,
            left = titleWidth,
            width = yAxisWidth,
            height = xAxisHeight
        ),
        xAxisArea = Area(
            top = canvasHeight - xAxisHeight,
            left = titleWidth + yAxisWidth,
            width = canvasWidth - titleWidth - yAxisWidth,
            height = xAxisHeight
        )
    )
}