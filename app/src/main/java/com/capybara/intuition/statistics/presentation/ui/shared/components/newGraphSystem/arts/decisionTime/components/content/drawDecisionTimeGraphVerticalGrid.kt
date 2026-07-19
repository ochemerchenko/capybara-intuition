package com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.arts.decisionTime.components.content

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.capybara.intuition.app.presentation.theme.colors.IntuitionColors
import com.capybara.intuition.app.presentation.theme.typography.IntuitionTypography
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.chartsAreas.Area
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.chartsAreas.getDayCenterX
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data.AnalyticsChartPoint

fun  DrawScope.drawDecisionTimeGraphVerticalGrid (
    layoutModel: Area,
    chartsData: List<AnalyticsChartPoint>,
    colors: IntuitionColors,
    typography: IntuitionTypography
){
    val dash = PathEffect.dashPathEffect(
        floatArrayOf(10f, 10f)
    )

    chartsData.forEachIndexed { index, point ->

        if (!point.weekStart) return@forEachIndexed

        val x = layoutModel.getDayCenterX(
            index = index,
            daysCount = chartsData.size
        )

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

