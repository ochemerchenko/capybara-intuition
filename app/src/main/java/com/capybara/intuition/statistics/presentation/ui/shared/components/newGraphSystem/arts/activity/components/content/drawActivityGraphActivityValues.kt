package com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.arts.activity.components.content

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capybara.intuition.app.presentation.theme.colors.IntuitionColors
import com.capybara.intuition.app.presentation.theme.typography.IntuitionTypography
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.calculators.yScale.model.ActivityYScaleModel
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.chartsAreas.Area
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.chartsAreas.getDayCenterX
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data.AnalyticsChartPoint

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
            style = TextStyle(
                color = Color.Black,
                fontSize = 12.sp
            )
        )

        drawText(
            textLayoutResult = textLayout,
            color = Color.Black,
            topLeft = Offset(
                x = centerX - textLayout.size.width / 2f,
                y = top.toFloat() -
                        textLayout.size.height -
                        4.dp.toPx()
            )
        )
    }
}