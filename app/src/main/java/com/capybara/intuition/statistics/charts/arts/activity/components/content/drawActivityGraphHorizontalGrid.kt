package com.capybara.intuition.statistics.charts.arts.activity.components.content

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.capybara.intuition.app.presentation.theme.colors.IntuitionColors
import com.capybara.intuition.app.presentation.theme.typography.IntuitionTypography
import com.capybara.intuition.statistics.charts.calculators.yScale.model.ActivityYScaleModel
import com.capybara.intuition.statistics.charts.model.chartsAreas.Area

fun DrawScope.drawActivityGraphHorizontalGrid (
    layoutModel: Area,
    activityYScaleModel: ActivityYScaleModel,
    colors: IntuitionColors,
    typography: IntuitionTypography
) {
    val dash = PathEffect.dashPathEffect(
        floatArrayOf(10f, 10f)
    )

    var value = activityYScaleModel.axisMin

    while (value <= activityYScaleModel.axisMax) {

        val y = activityYScaleModel.toY(
            value = value,
            chartTop = layoutModel.top,
            chartHeight = layoutModel.height
        )

        drawLine(
            color = colors.chartGrid,
            start = Offset(
                x = layoutModel.left,
                y = y.toFloat()
            ),
            end = Offset(
                x = layoutModel.left + layoutModel.width,
                y = y.toFloat()
            ),
            strokeWidth = 2f,
            pathEffect = dash
        )

        value += activityYScaleModel.tickStep
    }

}