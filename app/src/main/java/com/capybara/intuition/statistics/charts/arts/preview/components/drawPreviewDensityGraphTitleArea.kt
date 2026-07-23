package com.capybara.intuition.statistics.charts.arts.preview.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.drawText
import com.capybara.intuition.app.presentation.theme.colors.IntuitionColors
import com.capybara.intuition.app.presentation.theme.typography.IntuitionTypography
import com.capybara.intuition.statistics.charts.model.chartsAreas.Area

fun DrawScope.drawPreviewDensityGraphTitleArea (
    layoutModel: Area,
    title: String,
    textMeasurer: TextMeasurer,
    colors: IntuitionColors,
    typography: IntuitionTypography
){
    val textLayout = textMeasurer.measure(
        text = title
    )

    drawText(
        textLayoutResult = textLayout,
        topLeft = Offset(
            x = layoutModel.left +
                    (layoutModel.width - textLayout.size.width) / 2f,

            y = layoutModel.top +
                    (layoutModel.height - textLayout.size.height) / 2f
        )
    )
}