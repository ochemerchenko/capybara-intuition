package com.capybara.intuition.statistics.charts.arts.decisionTime.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.capybara.intuition.app.presentation.theme.colors.IntuitionColors
import com.capybara.intuition.app.presentation.theme.typography.IntuitionTypography
import com.capybara.intuition.statistics.charts.arts.decisionTime.components.content.drawDecisionTimeGraphHorizontalGrid
import com.capybara.intuition.statistics.charts.arts.decisionTime.components.content.drawDecisionTimeGraphLine
import com.capybara.intuition.statistics.charts.arts.decisionTime.components.content.drawDecisionTimeGraphVerticalGrid
import com.capybara.intuition.statistics.charts.calculators.yScale.model.DecisionTimeYScaleModel
import com.capybara.intuition.statistics.charts.model.chartsAreas.Area
import com.capybara.intuition.statistics.charts.model.data.AnalyticsChartPoint


fun DrawScope.drawDecisionTimeGraphContentArea (
    layoutModel: Area,
    yScaleModel: DecisionTimeYScaleModel,
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

// Горизонтальна сітка
    drawDecisionTimeGraphHorizontalGrid(
        layoutModel = layoutModel,
        yScaleModel = yScaleModel,
        colors = colors,
        typography = typography
    )

    // Вертикальна сітка
    drawDecisionTimeGraphVerticalGrid(
        layoutModel = layoutModel,
        chartsData = chartsData,
        colors = colors,
        typography = typography
    )

    // Дані користувача про середній час прийняття рішення
    drawDecisionTimeGraphLine(
        layoutModel = layoutModel,
        chartsData = chartsData,
        yScaleModel = yScaleModel,
        colors = colors,
        typography = typography
    )

}