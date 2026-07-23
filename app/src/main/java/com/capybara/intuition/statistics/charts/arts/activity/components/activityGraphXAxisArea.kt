package com.capybara.intuition.statistics.charts.arts.activity.components

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
import com.capybara.intuition.statistics.charts.model.chartsAreas.Area
import com.capybara.intuition.statistics.charts.model.chartsAreas.getDayCenterX
import com.capybara.intuition.statistics.charts.model.data.AnalyticsChartPoint
import java.time.format.DateTimeFormatter

fun DrawScope.activityGraphXAxisArea (
    layoutModel: Area,
    chartsData: List<AnalyticsChartPoint>,
    textMeasurer: TextMeasurer,
    colors: IntuitionColors,
    typography: IntuitionTypography
){


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

    val axisY = layoutModel.top

    drawLine(
        color = colors.chartAxis,
        start = Offset(layoutModel.left, axisY),
        end = Offset(layoutModel.left + layoutModel.width, axisY),
        strokeWidth = 2f
    )

    val cellWidth = layoutModel.width / chartsData.size.toFloat()

    val formatter = DateTimeFormatter.ofPattern("dd.MM")

    chartsData.forEachIndexed { index, point ->

        if (!point.weekStart) return@forEachIndexed

        // Центр комірки дня
        val x = layoutModel.getDayCenterX(
            index = index,
            daysCount = chartsData.size
        )

        drawLine(
            color = colors.chartAxisTick,
            start = Offset(x, axisY),
            end = Offset(x, axisY + 8f),
            strokeWidth = 2f
        )

        val label = point.day.format(formatter)

        val textLayout = textMeasurer.measure(
            text = label,
            style = TextStyle(
                color = colors.chartAxisLabel,
                fontSize = 12.sp
            )
        )

        drawText(
            textLayout,
            topLeft = Offset(
                x = x - textLayout.size.width / 2f,
                y = axisY + 12f
            )
        )
    }



}