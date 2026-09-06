package com.capybara.intuition.features.statistics.charts.arts.preview.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.capybara.intuition.core.theme.colors.IntuitionColors
import com.capybara.intuition.core.theme.typography.IntuitionTypography
import com.capybara.intuition.features.statistics.charts.arts.preview.components.content.drawPreviewGraphBaseline
import com.capybara.intuition.features.statistics.charts.arts.preview.components.content.drawPreviewGraphUserLine
import com.capybara.intuition.features.statistics.charts.calculators.yScale.model.PreviewDensityYScaleModel
import com.capybara.intuition.features.statistics.charts.model.chartsAreas.Area
import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint

fun DrawScope.drawPreviewDensityGraphContentArea (
    layoutModel: Area,
    yScaleModel: PreviewDensityYScaleModel,
    chartsData: List<AnalyticsChartPoint>,
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

    drawPreviewGraphBaseline(
        layoutModel = layoutModel,
        chartsData = chartsData,
        yScaleModel = yScaleModel,
        colors = colors,
        typography = typography
    )

    drawPreviewGraphUserLine(
        layoutModel = layoutModel,
        chartsData = chartsData,
        yScaleModel = yScaleModel,
        colors = colors,
        typography = typography
    )
}