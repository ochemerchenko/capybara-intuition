package com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.arts.activity.components.content

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.capybara.intuition.app.presentation.theme.colors.IntuitionColors
import com.capybara.intuition.app.presentation.theme.typography.IntuitionTypography
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.calculators.yScale.model.ActivityYScaleModel
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.chartsAreas.Area
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.chartsAreas.getDayCenterX
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data.AnalyticsChartPoint

fun DrawScope.drawActivityGraphActivityBars (
    layoutModel: Area,
    activityYScaleModel: ActivityYScaleModel,
    chartsData: List<AnalyticsChartPoint>,
    colors: IntuitionColors,
    typography: IntuitionTypography
){

    if (chartsData.isEmpty()) return

    val barWidth =
        layoutModel.width / chartsData.size * 0.7f

    chartsData.forEachIndexed { index, point ->

        val centerX = layoutModel.getDayCenterX(
            index = index,
            daysCount = chartsData.size
        )

        val top = activityYScaleModel.toY(
            value = point.sessionCount,
            chartTop = layoutModel.top,
            chartHeight = layoutModel.height
        )

        val barColor = if (index == chartsData.lastIndex) {
            colors.chartLastBarColors
        } else {
            colors.chartBarColors
        }

        drawRect(
            color = barColor,
            topLeft = Offset(
                x = centerX - barWidth / 2f,
                y = top.toFloat()
            ),
            size = Size(
                width = barWidth,
                height = layoutModel.top +
                        layoutModel.height -
                        top.toFloat()
            )
        )
    }

}