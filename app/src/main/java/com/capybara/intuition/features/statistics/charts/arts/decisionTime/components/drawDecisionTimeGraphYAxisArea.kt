package com.capybara.intuition.features.statistics.charts.arts.decisionTime.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import com.capybara.intuition.core.theme.colors.IntuitionColors
import com.capybara.intuition.core.theme.typography.IntuitionTypography
import com.capybara.intuition.features.statistics.charts.calculators.yScale.model.DecisionTimeYScaleModel
import com.capybara.intuition.features.statistics.charts.calculators.yScale.model.TimeMeasureUnit
import com.capybara.intuition.features.statistics.charts.model.chartsAreas.Area

fun DrawScope.drawDecisionTimeGraphYAxisArea (
    layoutModel: Area,
    yScaleModel: DecisionTimeYScaleModel,
    textMeasurer: TextMeasurer,
    colors: IntuitionColors,
    typography: IntuitionTypography
) {
    drawRect(
        color = Color.Transparent,
        topLeft = Offset(
            x = layoutModel.left,
            y = layoutModel.top
        ),
        size = Size(
            width = layoutModel.width,
            height = layoutModel.height
        )
    )

    var value = yScaleModel.axisMin

    while (
        value <= yScaleModel.axisMax
    ) {

        val y = yScaleModel.toY(
            value = value,
            chartTop = layoutModel.top,
            chartHeight = layoutModel.height
        )

        val isBorder =
            value == yScaleModel.axisMin ||
                    value == yScaleModel.axisMax

        if (!isBorder) {

            val textLayout = textMeasurer.measure(
                text = when (yScaleModel.measureUnit) {

                    TimeMeasureUnit.SECOND ->
                        if (value / 1000.0 % 1.0 == 0.0) {
                            (value / 1000.0).toInt().toString()
                        } else {
                            "%.1f".format(value / 1000.0)
                        }
                    TimeMeasureUnit.MINUTE ->
                        if (value / 60000.0 % 1.0 == 0.0) {
                            (value / 60000.0).toInt().toString()
                        } else {
                            "%.1f".format(value / 60000.0)
                        }

                },
                style = typography.chartAxisLabel.copy(
                    color = colors.chartAxisLabel
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
        value += yScaleModel.tickStep
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