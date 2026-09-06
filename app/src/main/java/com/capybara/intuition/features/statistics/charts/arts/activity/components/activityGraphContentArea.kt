package com.capybara.intuition.features.statistics.charts.arts.activity.components


import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import com.capybara.intuition.core.theme.colors.IntuitionColors
import com.capybara.intuition.core.theme.typography.IntuitionTypography
import com.capybara.intuition.features.statistics.charts.arts.activity.components.content.drawActivityGraphActivityBars
import com.capybara.intuition.features.statistics.charts.arts.activity.components.content.drawActivityGraphHorizontalGrid
import com.capybara.intuition.features.statistics.charts.arts.activity.components.content.drawActivityGraphVerticalGrid
import com.capybara.intuition.features.statistics.charts.calculators.yScale.model.ActivityYScaleModel
import com.capybara.intuition.features.statistics.charts.model.chartsAreas.Area
import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint

fun DrawScope.activityGraphContentArea (
    layoutModel: Area,
    yScaleModel: ActivityYScaleModel,
    chartsData: List<AnalyticsChartPoint>,
    textMeasurer: TextMeasurer,
    colors: IntuitionColors,
    typography: IntuitionTypography
) {

    // Горизонтальна сітка
    drawActivityGraphHorizontalGrid(
        layoutModel = layoutModel,
        activityYScaleModel = yScaleModel,
        colors = colors,
        typography = typography
    )

    // Вертикальна сітка
    drawActivityGraphVerticalGrid(
        layoutModel = layoutModel,
        chartsData = chartsData,
        colors = colors,
        typography = typography
    )

    // Стовпчики активності
    drawActivityGraphActivityBars(
        layoutModel = layoutModel,
        chartsData = chartsData,
        activityYScaleModel = yScaleModel,
        colors = colors,
        typography = typography
    )

    // Підписи над стовпчиками
    /*
    drawActivityGraphActivityValues(
        layoutModel = layoutModel,
        chartsData = chartsData,
        activityYScaleModel = yScaleModel,
        textMeasurer = textMeasurer,
        colors = colors,
        typography = typography
    )

     */
}