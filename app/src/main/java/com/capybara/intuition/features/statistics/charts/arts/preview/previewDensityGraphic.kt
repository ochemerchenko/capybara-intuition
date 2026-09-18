package com.capybara.intuition.features.statistics.charts.arts.preview

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.OpenInFull
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.statistics.charts.arts.preview.components.drawPreviewDensityGraphContentArea
import com.capybara.intuition.features.statistics.charts.arts.preview.components.drawPreviewDensityGraphTitleArea
import com.capybara.intuition.features.statistics.charts.arts.preview.components.drawPreviewDensityGraphYAxisArea
import com.capybara.intuition.features.statistics.charts.calculators.graphLayoutsModels.getPreviewDensityGraphLayoutModel
import com.capybara.intuition.features.statistics.charts.calculators.yScale.calculatePreviewDensityYScale
import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint

@Composable
fun PreviewDensityGraphic(
    chartsData: List<AnalyticsChartPoint>,
    title: String,
    modifier: Modifier = Modifier.Companion,

    ) {

    val graphAspectRatio = 4f / 3f

    if (chartsData.isEmpty()) {
        // TODO: Empty state
        return
    }

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    val textMeasurer = rememberTextMeasurer()
    val density = LocalDensity.current

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(graphAspectRatio)
            .clip(RoundedCornerShape(24.dp)) // Updated to 24.dp to match Settings
            .background(colors.chartBackground)
        .padding(8.dp)
    ) {

        val layoutModel = remember(
            constraints.maxWidth,
            constraints.maxHeight
        ) {
            getPreviewDensityGraphLayoutModel(
                canvasWidth = constraints.maxWidth.toFloat(),
                canvasHeight = constraints.maxHeight.toFloat()
            )
        }

        val yScaleModel = remember(chartsData) {
            calculatePreviewDensityYScale(
                chartsData = chartsData
            )
        }

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            drawPreviewDensityGraphTitleArea(
                layoutModel = layoutModel.titleArea,
                title = title,
                textMeasurer = textMeasurer,
                colors = colors,
                typography = typography
            )

            drawPreviewDensityGraphYAxisArea(
                layoutModel = layoutModel.yAxisArea,
                yScaleModel = yScaleModel,
                textMeasurer = textMeasurer,
                colors = colors,
                typography = typography
            )

            drawPreviewDensityGraphContentArea(
                layoutModel = layoutModel.chartArea,
                yScaleModel = yScaleModel,
                chartsData = chartsData,
                colors = colors,
                typography = typography
            )
        }

        val iconOffsetX = with(density) {
            layoutModel.iconArea.left.toDp()
        }

        val iconOffsetY = with(density) {
            layoutModel.iconArea.top.toDp()
        }

        val iconSize = with(density) {
            layoutModel.iconArea.width.toDp()
        }

        Icon(
            imageVector = Icons.Default.OpenInFull,
            contentDescription = null,
            tint = IntuitionTheme.colors.textSecondary.copy(alpha = 0.45f),
            modifier = Modifier
                .offset(
                    x = iconOffsetX,
                    y = iconOffsetY
                )
                .size(iconSize)
        )
    }
}
