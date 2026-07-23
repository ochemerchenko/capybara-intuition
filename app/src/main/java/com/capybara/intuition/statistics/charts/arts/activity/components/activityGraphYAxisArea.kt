package com.capybara.intuition.statistics.charts.arts.activity.components

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.sp
import com.capybara.intuition.app.presentation.theme.colors.IntuitionColors
import com.capybara.intuition.app.presentation.theme.typography.IntuitionTypography
import com.capybara.intuition.statistics.charts.calculators.yScale.model.ActivityYScaleModel
import com.capybara.intuition.statistics.charts.model.chartsAreas.Area

fun DrawScope.activityGraphYAxisArea (
    layoutModel: Area,
    yScaleModel: ActivityYScaleModel,
    textMeasurer: TextMeasurer,
    colors: IntuitionColors,
    typography: IntuitionTypography
){
    Log.d("ACTIVITY_Y", "value = $yScaleModel")


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

            val textLayout = textMeasurer.measure(
                text = value.toInt().toString(),
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
                    y = y.toFloat() - textLayout.size.height / 2f
                )
            )

            drawLine(
                color = colors.chartAxisTick,
                start = Offset(
                    x = layoutModel.left + layoutModel.width - 8f,
                    y = y.toFloat()
                ),
                end = Offset(
                    x = layoutModel.left + layoutModel.width,
                    y = y.toFloat()
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