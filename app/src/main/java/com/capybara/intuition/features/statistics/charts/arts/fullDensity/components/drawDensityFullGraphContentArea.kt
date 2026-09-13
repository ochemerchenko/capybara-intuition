package com.capybara.intuition.features.statistics.charts.arts.fullDensity.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.capybara.intuition.core.theme.colors.IntuitionColors
import com.capybara.intuition.core.theme.typography.IntuitionTypography
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.content.drawDensityFullGraphBaseline
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.content.drawDensityFullGraphBaselineCorridor
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.content.drawDensityFullGraphHorizontalGrid
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.content.drawDensityFullGraphUserLine
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.content.drawDensityFullGraphVerticalGrid
import com.capybara.intuition.features.statistics.charts.calculators.buildSmoothPath
import com.capybara.intuition.features.statistics.charts.calculators.yScale.model.FullDensityYScaleModel
import com.capybara.intuition.features.statistics.charts.model.chartsAreas.Area
import com.capybara.intuition.features.statistics.charts.model.chartsAreas.getDayCenterX
import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint

fun DrawScope.drawDensityFullGraphContentArea(
    layoutModel: Area,
    yScaleModel: FullDensityYScaleModel,
    chartsData: List<AnalyticsChartPoint>,
    noiseCorridorVisibility: Boolean,
    physicalStateVisibility: Boolean,
    happinessVisibility: Boolean,
    selfConfidenceVisibility: Boolean,
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
    if (noiseCorridorVisibility) {
        drawDensityFullGraphBaselineCorridor(
            layoutModel = layoutModel,
            yScaleModel = yScaleModel,
            chartsData = chartsData,
            colors = colors,
            typography = typography
        )
    }

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

    // --- Wellbeing Lines (Using EMA values for stability) ---

    if (physicalStateVisibility) {
        drawWellbeingLine(
            layoutModel = layoutModel,
            chartsData = chartsData,
            color = colors.wellbeingPhysical
        ) { it.physicalEma }
    }

    if (happinessVisibility) {
        drawWellbeingLine(
            layoutModel = layoutModel,
            chartsData = chartsData,
            color = colors.wellbeingHappiness
        ) { it.happinessEma }
    }

    if (selfConfidenceVisibility) {
        drawWellbeingLine(
            layoutModel = layoutModel,
            chartsData = chartsData,
            color = colors.wellbeingConfidence
        ) { it.confidenceEma }
    }
}

private fun DrawScope.drawWellbeingLine(
    layoutModel: Area,
    chartsData: List<AnalyticsChartPoint>,
    color: Color,
    valueSelector: (AnalyticsChartPoint) -> Double
) {
    // 0.0 (0%) -> низ графіка, 1.0 (100%) -> верх графіка.
    val points = chartsData.mapIndexed { index, point ->
        val value = valueSelector(point)
        val x = layoutModel.getDayCenterX(index, chartsData.size)
        val y = layoutModel.top + layoutModel.height - (value * layoutModel.height).toFloat()
        Offset(x, y)
    }

    if (points.isNotEmpty()) {
        val path = buildSmoothPath(points)
        drawPath(
            path = path,
            color = color,
            style = Stroke(width = 4f)
        )
    }
}
