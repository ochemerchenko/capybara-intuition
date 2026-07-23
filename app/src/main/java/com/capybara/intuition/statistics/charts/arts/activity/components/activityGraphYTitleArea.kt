package com.capybara.intuition.statistics.charts.arts.activity.components

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import com.capybara.intuition.app.presentation.theme.colors.IntuitionColors
import com.capybara.intuition.app.presentation.theme.typography.IntuitionTypography
import com.capybara.intuition.statistics.charts.calculators.yScale.model.ActivityYScaleModel
import com.capybara.intuition.statistics.charts.model.chartsAreas.Area

fun DrawScope.activityGraphYTitleArea (
    layoutModel: Area,
    textMeasurer: TextMeasurer,
    yScaleModel: ActivityYScaleModel,
    colors: IntuitionColors,
    typography: IntuitionTypography,
    title: String
){

    val textLayout = textMeasurer.measure(
        text = title,
        style = typography.chartYAxisTitle.copy(
            color = colors.chartTitle
        )
    )

    Log.d(
        "TITLE",
        "text = ${textLayout.size.width} x ${textLayout.size.height}"
    )

    Log.d(
        "TITLE",
        "area = ${layoutModel.width} x ${layoutModel.height}"
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