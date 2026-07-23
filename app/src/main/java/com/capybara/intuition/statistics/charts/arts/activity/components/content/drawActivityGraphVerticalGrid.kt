package com.capybara.intuition.statistics.charts.arts.activity.components.content

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.capybara.intuition.app.presentation.theme.colors.IntuitionColors
import com.capybara.intuition.app.presentation.theme.typography.IntuitionTypography
import com.capybara.intuition.statistics.charts.model.chartsAreas.Area
import com.capybara.intuition.statistics.charts.model.chartsAreas.getDayCenterX
import com.capybara.intuition.statistics.charts.model.data.AnalyticsChartPoint

fun DrawScope.drawActivityGraphVerticalGrid(
    layoutModel: Area,
    chartsData: List<AnalyticsChartPoint>,
    colors: IntuitionColors,
    typography: IntuitionTypography
) {
    val dash = PathEffect.dashPathEffect(
        floatArrayOf(10f, 10f)
    )

    if (chartsData.isEmpty()) return

    chartsData.forEachIndexed { index, point ->

        val x = layoutModel.getDayCenterX(
            index = index,
            daysCount = chartsData.size
        )

        if (point.weekStart) {

            drawLine(
                color = colors.chartGrid,
                start = Offset(
                    x = x,
                    y = layoutModel.top
                ),
                end = Offset(
                    x = x,
                    y = layoutModel.top + layoutModel.height
                ),
                strokeWidth = 2f,
                pathEffect = dash
            )
        }
    }

}