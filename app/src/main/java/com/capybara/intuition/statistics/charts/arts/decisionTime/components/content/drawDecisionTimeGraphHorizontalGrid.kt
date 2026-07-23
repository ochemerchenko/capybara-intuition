package com.capybara.intuition.statistics.charts.arts.decisionTime.components.content

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.capybara.intuition.app.presentation.theme.colors.IntuitionColors
import com.capybara.intuition.app.presentation.theme.typography.IntuitionTypography
import com.capybara.intuition.statistics.charts.calculators.yScale.model.DecisionTimeYScaleModel
import com.capybara.intuition.statistics.charts.model.chartsAreas.Area

fun DrawScope.drawDecisionTimeGraphHorizontalGrid (
    layoutModel: Area,
    yScaleModel: DecisionTimeYScaleModel,
    colors: IntuitionColors,
    typography: IntuitionTypography
) {
    val dash = PathEffect.dashPathEffect(
        floatArrayOf(10f, 10f)
    )

    var value = yScaleModel.axisMin

    while (value <= yScaleModel.axisMax) {

        val y = yScaleModel.toY(
            value = value,
            chartTop = layoutModel.top,
            chartHeight = layoutModel.height
        )

        val isBorder =
            value == yScaleModel.axisMin ||
                    value == yScaleModel.axisMax

        if (!isBorder) {

            drawLine(
                color = colors.chartGrid,
                start = Offset(
                    x = layoutModel.left,
                    y = y
                ),
                end = Offset(
                    x = layoutModel.left + layoutModel.width,
                    y = y
                ),
                strokeWidth = 2f,
                pathEffect = dash
            )
        }

        value += yScaleModel.tickStep
    }
}
