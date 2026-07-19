package com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.calculators.graphLayoutsModels

import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.chartsAreas.Area
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.chartsAreas.DecisionTimeGraphLayoutModel


fun getActivityGraphLayoutModel (
    canvasWidth: Float,
    canvasHeight: Float
): DecisionTimeGraphLayoutModel {

    val titleWidth = 40f
    val xAxisHeight = 60f
    val yAxisWidth = 60f


    return DecisionTimeGraphLayoutModel(
        titleArea = Area(
            top = 0f,
            left = 0f,
            width = titleWidth,
            height = canvasHeight - xAxisHeight
        ),
        yAxisArea = Area(
            top = 0f,
            left = titleWidth,
            width = yAxisWidth,
            height = canvasHeight - xAxisHeight

        ),
        chartArea = Area(
            top = 0f,
            left = titleWidth + yAxisWidth,
            width = canvasWidth - titleWidth - yAxisWidth,
            height = canvasHeight - xAxisHeight

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