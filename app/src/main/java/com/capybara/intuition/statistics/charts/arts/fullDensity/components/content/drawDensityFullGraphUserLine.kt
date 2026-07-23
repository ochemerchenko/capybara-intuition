package com.capybara.intuition.statistics.charts.arts.fullDensity.components.content

import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import com.capybara.intuition.app.presentation.theme.colors.IntuitionColors
import com.capybara.intuition.app.presentation.theme.typography.IntuitionTypography
import com.capybara.intuition.statistics.charts.calculators.buildChartPoints
import com.capybara.intuition.statistics.charts.calculators.buildSmoothPath
import com.capybara.intuition.statistics.charts.calculators.yScale.model.FullDensityYScaleModel
import com.capybara.intuition.statistics.charts.model.chartsAreas.Area
import com.capybara.intuition.statistics.charts.model.data.AnalyticsChartPoint


fun DrawScope.drawDensityFullGraphUserLine(
    layoutModel: Area,
    chartsData: List<AnalyticsChartPoint>,
    yScaleModel: FullDensityYScaleModel,
    colors: IntuitionColors,
    typography: IntuitionTypography
) {

    val points = buildChartPoints(
        chartsData = chartsData,
        layoutModel = layoutModel,
        yScaleModel = yScaleModel
    ) {
        it.userLineEma
    }

    val path = buildSmoothPath(points)


    drawPath(
        path = path,
        color = colors.chartUserLine,
        style = Stroke(width = 6f)
    )
}

