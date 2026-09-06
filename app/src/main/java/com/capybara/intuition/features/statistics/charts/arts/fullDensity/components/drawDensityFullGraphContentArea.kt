package com.capybara.intuition.features.statistics.charts.arts.fullDensity.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.capybara.intuition.core.theme.colors.IntuitionColors
import com.capybara.intuition.core.theme.typography.IntuitionTypography
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.content.drawDensityFullGraphBaseline
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.content.drawDensityFullGraphBaselineCorridor
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.content.drawDensityFullGraphHorizontalGrid
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.content.drawDensityFullGraphUserLine
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.content.drawDensityFullGraphVerticalGrid
import com.capybara.intuition.features.statistics.charts.calculators.yScale.model.FullDensityYScaleModel
import com.capybara.intuition.features.statistics.charts.model.chartsAreas.Area
import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint

fun DrawScope.drawDensityFullGraphContentArea(
    layoutModel: Area,
    yScaleModel: FullDensityYScaleModel,
    chartsData: List<AnalyticsChartPoint>,
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

    // Горизонтальна сітка
    drawDensityFullGraphHorizontalGrid(
        layoutModel = layoutModel,
        yScaleModel = yScaleModel,
        colors = colors,
        typography = typography
    )

    // Вертикальна сітка
    drawDensityFullGraphVerticalGrid(
        layoutModel = layoutModel,
        chartsData = chartsData,
        colors = colors,
        typography = typography
    )

    // Коридор навколо baseLine
    drawDensityFullGraphBaselineCorridor(
        layoutModel = layoutModel,
        yScaleModel = yScaleModel,
        chartsData = chartsData,
        colors = colors,
        typography = typography
    )

    drawDensityFullGraphBaseline(
        layoutModel = layoutModel,
        chartsData = chartsData,
        yScaleModel = yScaleModel,
        colors = colors,
        typography = typography
    )

    drawDensityFullGraphUserLine(
        layoutModel = layoutModel,
        chartsData = chartsData,
        yScaleModel = yScaleModel,
        colors = colors,
        typography = typography
    )

}