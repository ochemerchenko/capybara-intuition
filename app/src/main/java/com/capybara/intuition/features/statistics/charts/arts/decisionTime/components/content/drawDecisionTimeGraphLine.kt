package com.capybara.intuition.features.statistics.charts.arts.decisionTime.components.content

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.capybara.intuition.core.theme.colors.IntuitionColors
import com.capybara.intuition.core.theme.typography.IntuitionTypography
import com.capybara.intuition.features.statistics.charts.calculators.buildChartPointsTimeDecision
import com.capybara.intuition.features.statistics.charts.calculators.buildSmoothPathTimeDecision
import com.capybara.intuition.features.statistics.charts.calculators.yScale.model.DecisionTimeYScaleModel
import com.capybara.intuition.features.statistics.charts.model.chartsAreas.Area
import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint

fun DrawScope.drawDecisionTimeGraphLine(
    layoutModel: Area,
    chartsData: List<AnalyticsChartPoint>,
    yScaleModel: DecisionTimeYScaleModel,
    colors: IntuitionColors,
    typography: IntuitionTypography
) {

    val points = buildChartPointsTimeDecision(
        chartsData = chartsData,
        layoutModel = layoutModel,
        decisionTimeYScaleModel = yScaleModel
    ) {
        it.averageDayDecisionTime
    }

    val path = buildSmoothPathTimeDecision(points)

    drawPath(
        path = path,
        color = colors.chartUserLine,
        style = Stroke(width = 3f)
    )
}