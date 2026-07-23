package com.capybara.intuition.statistics.charts.arts.fullDensity.components.content

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.capybara.intuition.app.presentation.theme.colors.IntuitionColors
import com.capybara.intuition.app.presentation.theme.typography.IntuitionTypography
import com.capybara.intuition.statistics.charts.calculators.yScale.model.FullDensityYScaleModel
import com.capybara.intuition.statistics.charts.model.chartsAreas.Area

fun DrawScope.drawDensityFullGraphHorizontalGrid (
    layoutModel: Area,
    yScaleModel: FullDensityYScaleModel,
    colors: IntuitionColors,
    typography: IntuitionTypography
){
    val dash = PathEffect.dashPathEffect(
        floatArrayOf(10f, 10f)
    )

    val axisRange = yScaleModel.axisMax - yScaleModel.axisMin

    for (value in yScaleModel.axisMin..yScaleModel.axisMax step yScaleModel.tickStep) {

        val fraction =
            (value - yScaleModel.axisMin).toFloat() / axisRange.toFloat()

        val y =
            layoutModel.top +
                    layoutModel.height -
                    fraction * layoutModel.height

        if (
            y != layoutModel.top + layoutModel.height &&
            y != layoutModel.top
        ) {
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
    }
}
