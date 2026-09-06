package com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.content

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.capybara.intuition.core.theme.colors.IntuitionColors
import com.capybara.intuition.core.theme.typography.IntuitionTypography
import com.capybara.intuition.features.statistics.charts.calculators.buildChartPoints
import com.capybara.intuition.features.statistics.charts.calculators.buildSmoothPath
import com.capybara.intuition.features.statistics.charts.calculators.yScale.model.FullDensityYScaleModel
import com.capybara.intuition.features.statistics.charts.model.chartsAreas.Area
import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint


fun DrawScope.drawDensityFullGraphBaselineCorridor(
    layoutModel: Area,
    chartsData: List<AnalyticsChartPoint>,
    yScaleModel: FullDensityYScaleModel,
    colors: IntuitionColors,
    typography: IntuitionTypography
) {

    if (chartsData.size < 2) return

    val upperPoints = buildChartPoints(
        chartsData = chartsData,
        layoutModel = layoutModel,
        yScaleModel = yScaleModel,
        clipToViewport = true
    ) {
        it.upperCorridorLineEma
    }

    val lowerPoints = buildChartPoints(
        chartsData = chartsData,
        layoutModel = layoutModel,
        yScaleModel = yScaleModel,
        clipToViewport = true
    ) {
        it.lowerCorridorLineEma
    }

    val upperPath = buildSmoothPath(upperPoints)

    val corridorPath = Path()

    corridorPath.addPath(upperPath)

    val reversedLowerPoints = lowerPoints.asReversed()

    corridorPath.lineTo(
        reversedLowerPoints.first().x,
        reversedLowerPoints.first().y
    )

    for (i in 0 until reversedLowerPoints.lastIndex) {

        val p0 =
            if (i == 0) reversedLowerPoints[i]
            else reversedLowerPoints[i - 1]

        val p1 = reversedLowerPoints[i]
        val p2 = reversedLowerPoints[i + 1]

        val p3 =
            if (i + 2 < reversedLowerPoints.size)
                reversedLowerPoints[i + 2]
            else
                reversedLowerPoints[i + 1]

        val cp1 = Offset(
            x = p1.x + (p2.x - p0.x) / 6f,
            y = p1.y + (p2.y - p0.y) / 6f
        )

        val cp2 = Offset(
            x = p2.x - (p3.x - p1.x) / 6f,
            y = p2.y - (p3.y - p1.y) / 6f
        )

        corridorPath.cubicTo(
            cp1.x,
            cp1.y,
            cp2.x,
            cp2.y,
            p2.x,
            p2.y
        )
    }

    corridorPath.close()

    drawPath(
        path = corridorPath,
        color = colors.chartUncertaintyCorridor.copy(alpha = 0.12f)
    )
}