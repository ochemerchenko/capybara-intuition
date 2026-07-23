package com.capybara.intuition.statistics.charts.arts.preview.components.content

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.capybara.intuition.app.presentation.theme.colors.IntuitionColors
import com.capybara.intuition.app.presentation.theme.typography.IntuitionTypography
import com.capybara.intuition.statistics.charts.calculators.yScale.model.PreviewDensityYScaleModel
import com.capybara.intuition.statistics.charts.model.chartsAreas.Area
import com.capybara.intuition.statistics.charts.model.data.AnalyticsChartPoint

fun DrawScope.drawPreviewGraphUserLine(
    layoutModel: Area,
    chartsData: List<AnalyticsChartPoint>,
    yScaleModel: PreviewDensityYScaleModel,
    colors: IntuitionColors,
    typography: IntuitionTypography
) {

    if (chartsData.size < 2) return

    val coordinates = chartsData.mapIndexed { index, point ->

        val x =
            layoutModel.left +
                    (index.toFloat() / (chartsData.size - 1)) *
                    layoutModel.width

        val y = yScaleModel.toY(
            value = point.userLineEma,
            chartTop = layoutModel.top,
            chartHeight = layoutModel.height
        )

        Offset(x, y)
    }

    val path = Path()

    val first = coordinates.first()

    path.moveTo(
        first.x,
        first.y
    )

    // Менше значення = плавніша крива
    val tension = 0.08f

    for (i in 0 until coordinates.size - 1) {

        val p0 =
            if (i == 0)
                coordinates[i]
            else
                coordinates[i - 1]

        val p1 = coordinates[i]
        val p2 = coordinates[i + 1]

        val p3 =
            if (i + 2 < coordinates.size)
                coordinates[i + 2]
            else
                coordinates[i + 1]

        val controlPoint1 = Offset(
            x = p1.x + (p2.x - p0.x) * tension,
            y = p1.y + (p2.y - p0.y) * tension
        )

        val controlPoint2 = Offset(
            x = p2.x - (p3.x - p1.x) * tension,
            y = p2.y - (p3.y - p1.y) * tension
        )

        path.cubicTo(
            controlPoint1.x,
            controlPoint1.y,
            controlPoint2.x,
            controlPoint2.y,
            p2.x,
            p2.y
        )
    }

    drawPath(
        path = path,
        color = colors.chartUserLine,
        style = Stroke(
            width = 2.dp.toPx()
        )
    )
}