package com.capybara.intuition.features.statistics.charts.arts.fullDensity.components

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.capybara.intuition.core.theme.colors.IntuitionColors
import com.capybara.intuition.core.theme.typography.IntuitionTypography
import com.capybara.intuition.features.statistics.charts.model.chartsAreas.Area


fun DrawScope.drawDensityFullGraphStartPointArea(
    layoutModel: Area,
    colors: IntuitionColors,
    typography: IntuitionTypography
) {
    drawRect(
        color = Color.Transparent,
        topLeft = Offset(
            x =layoutModel.left,
            y =layoutModel.top
        ),
        size = Size(
            width = layoutModel.width,
            height = layoutModel.height
        )
    )
}