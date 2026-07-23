package com.capybara.intuition.statistics.charts.arts.fullDensity.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import com.capybara.intuition.app.presentation.theme.colors.IntuitionColors
import com.capybara.intuition.app.presentation.theme.typography.IntuitionTypography
import com.capybara.intuition.statistics.charts.model.chartsAreas.Area


fun DrawScope.drawDensityFullGraphYTitleArea(
    layoutModel: Area,
    textMeasurer: TextMeasurer,
    colors: IntuitionColors,
    typography: IntuitionTypography,
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

    val textLayout = textMeasurer.measure(
        text = title,
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