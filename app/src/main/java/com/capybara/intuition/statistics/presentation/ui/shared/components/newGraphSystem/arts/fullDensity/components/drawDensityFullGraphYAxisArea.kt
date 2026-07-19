package com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.arts.fullDensity.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.sp
import com.capybara.intuition.app.presentation.theme.colors.IntuitionColors
import com.capybara.intuition.app.presentation.theme.typography.IntuitionTypography
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.calculators.yScale.model.FullDensityYScaleModel
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.chartsAreas.Area


fun DrawScope.drawDensityFullGraphYAxisArea(
    layoutModel: Area,
    yScaleModel: FullDensityYScaleModel,
    textMeasurer: TextMeasurer,
    colors: IntuitionColors,
    typography: IntuitionTypography
) {

    for (value in yScaleModel.axisMin..yScaleModel.axisMax step yScaleModel.tickStep) {

        val y = yScaleModel.toY(
            value = value / 100.0,
            chartTop = layoutModel.top,
            chartHeight = layoutModel.height
        )

        val isBorder =
            value == yScaleModel.axisMin ||
                    value == yScaleModel.axisMax

        if (!isBorder) {

            val textLayout = textMeasurer.measure(
                text = "$value",
                style = TextStyle(
                    color = colors.chartAxisLabel,
                    fontSize = 12.sp
                )
            )

            drawText(
                textLayout,
                topLeft = Offset(
                    x = layoutModel.left +
                            layoutModel.width -
                            textLayout.size.width -
                            12f,
                    y = y - textLayout.size.height / 2f
                )
            )

            drawLine(
                color = colors.chartAxisTick,
                start = Offset(
                    x = layoutModel.left + layoutModel.width - 8f,
                    y = y
                ),
                end = Offset(
                    x = layoutModel.left + layoutModel.width,
                    y = y
                ),
                strokeWidth = 2f
            )
        }
    }

    drawLine(
        color = colors.chartAxis,
        start = Offset(
            x = layoutModel.left + layoutModel.width,
            y = layoutModel.top
        ),
        end = Offset(
            x = layoutModel.left + layoutModel.width,
            y = layoutModel.top + layoutModel.height
        ),
        strokeWidth = 2f
    )
}
