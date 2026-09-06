package com.capybara.intuition.features.statistics.charts.arts.decisionTime.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import com.capybara.intuition.core.theme.colors.IntuitionColors
import com.capybara.intuition.core.theme.typography.IntuitionTypography
import com.capybara.intuition.features.statistics.charts.calculators.yScale.model.DecisionTimeYScaleModel
import com.capybara.intuition.features.statistics.charts.calculators.yScale.model.TimeMeasureUnit
import com.capybara.intuition.features.statistics.charts.model.chartsAreas.Area

fun DrawScope.drawDecisionTimeGraphYTitleArea (
    layoutModel: Area,
    yScaleModel: DecisionTimeYScaleModel,
    textMeasurer: TextMeasurer,
    colors: IntuitionColors,
    typography: IntuitionTypography,
    sec_time_unit_name: String,
    min_time_unit_name: String,
    title: String

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

    val measureUnit = when( yScaleModel.measureUnit ) {
        TimeMeasureUnit.SECOND -> sec_time_unit_name
        TimeMeasureUnit.MINUTE -> min_time_unit_name
    }

    val textLayout = textMeasurer.measure(
        text = "$title, $measureUnit",
        style = typography.chartYAxisTitle.copy(
            color = colors.chartTitle
        )
    )

    val centerX = layoutModel.left + layoutModel.width / 2f
    val centerY = layoutModel.top + layoutModel.height / 2f

    rotate(
        degrees = -90f,
        pivot = Offset(centerX, centerY)
    ) {

        drawText(
            textLayout,
            topLeft = Offset(
                x = centerX - textLayout.size.width / 2f,
                y = centerY - textLayout.size.height / 2f
            )
        )
    }
}