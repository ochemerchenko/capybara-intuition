package com.capybara.intuition.features.statistics.charts.arts.activity.components.content

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capybara.intuition.core.theme.colors.IntuitionColors
import com.capybara.intuition.core.theme.typography.IntuitionTypography
import com.capybara.intuition.features.statistics.charts.calculators.yScale.model.ActivityYScaleModel
import com.capybara.intuition.features.statistics.charts.model.chartsAreas.Area
import com.capybara.intuition.features.statistics.charts.model.chartsAreas.getDayCenterX
import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint

fun DrawScope.drawActivityGraphActivityValues(
    layoutModel: Area,
    activityYScaleModel: ActivityYScaleModel,
    chartsData: List<AnalyticsChartPoint>,
    textMeasurer: TextMeasurer,
    colors: IntuitionColors,
    typography: IntuitionTypography
) {

    if (chartsData.isEmpty()) return

    chartsData.forEachIndexed { index, point ->

        if (point.sessionCount <= 0.0) {
            return@forEachIndexed
        }

        val centerX = layoutModel.getDayCenterX(
            index = index,
            daysCount = chartsData.size
        )

        val top = activityYScaleModel.toY(
            value = point.sessionCount,
            chartTop = layoutModel.top,
            chartHeight = layoutModel.height
        )

        val textLayout = textMeasurer.measure(
            text = point.sessionCount.toInt().toString(),
            style = typography.chartAxisLabel.copy(
                color = colors.activityValue
            )
        )

        drawText(
            textLayoutResult = textLayout,
            color = colors.activityValue,
            topLeft = Offset(
                x = centerX - textLayout.size.width / 2f,
                y = top.toFloat() -
                        textLayout.size.height -
                        4.dp.toPx()
            )
        )
    }
}