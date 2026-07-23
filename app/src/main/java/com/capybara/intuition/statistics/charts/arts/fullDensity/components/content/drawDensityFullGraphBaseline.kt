package com.capybara.intuition.statistics.charts.arts.fullDensity.components.content

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.capybara.intuition.app.presentation.theme.colors.IntuitionColors
import com.capybara.intuition.app.presentation.theme.typography.IntuitionTypography
import com.capybara.intuition.statistics.charts.calculators.yScale.model.FullDensityYScaleModel
import com.capybara.intuition.statistics.charts.model.chartsAreas.Area
import com.capybara.intuition.statistics.charts.model.data.AnalyticsChartPoint

fun DrawScope.drawDensityFullGraphBaseline(
    layoutModel: Area,
    chartsData: List<AnalyticsChartPoint>,
    yScaleModel: FullDensityYScaleModel,
    colors: IntuitionColors,
    typography: IntuitionTypography
) {

    if (chartsData.isEmpty()) return

    val y = yScaleModel.toY(
        value = chartsData.first().baseLine,
        chartTop = layoutModel.top,
        chartHeight = layoutModel.height
    )

    drawLine(
        color = colors.chartBaseLine,
        start = Offset(
            x = layoutModel.left,
            y = y
        ),
        end = Offset(
            x = layoutModel.left + layoutModel.width,
            y = y
        ),
        strokeWidth = 4f
    )
}
