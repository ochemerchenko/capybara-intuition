package com.capybara.intuition.features.statistics.charts.arts.preview.components.content

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.capybara.intuition.core.theme.colors.IntuitionColors
import com.capybara.intuition.core.theme.typography.IntuitionTypography
import com.capybara.intuition.features.statistics.charts.calculators.yScale.model.PreviewDensityYScaleModel
import com.capybara.intuition.features.statistics.charts.model.chartsAreas.Area
import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint


fun DrawScope.drawPreviewGraphBaseline(
    layoutModel: Area,
    chartsData: List<AnalyticsChartPoint>,
    yScaleModel: PreviewDensityYScaleModel,
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
        strokeWidth = 2f
    )
}