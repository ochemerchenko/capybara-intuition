package com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.arts.fullDensity

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.arts.fullDensity.components.drawDensityFullGraphContentArea
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.arts.fullDensity.components.drawDensityFullGraphStartPointArea
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.arts.fullDensity.components.drawDensityFullGraphXAxisArea
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.arts.fullDensity.components.drawDensityFullGraphYAxisArea
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.arts.fullDensity.components.drawDensityFullGraphYTitleArea
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.arts.fullDensity.components.header.ChartHeader
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.calculators.graphLayoutsModels.getFullDensityGraphLayoutModel
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.calculators.yScale.calculateFullDensityYScale
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data.AnalyticsChartPoint

@Composable
fun DensityFullGraphic(
    chartsData: List<AnalyticsChartPoint>,
    onHelpLinkClick: (String) -> Unit = {},
    modifier: Modifier = Modifier.Companion
) {

    val graphAspectRatio = 4f/3f
    if (chartsData.isEmpty()) return // І тут треба передбачити Text(Даних нема)
    val textMeasurer = rememberTextMeasurer()
    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography
    val y_title = stringResource(R.string.statistics_density_axis_title)

    ChartHeader(
        title = stringResource(R.string.statistics_density_full_graph_title),
        helpBaseName = "statistics_full_chart_legend",
        onHelpLinkClick = onHelpLinkClick
    )

    Spacer(
        modifier = Modifier.height(
            16.dp
        )
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(graphAspectRatio)
            .clip(RoundedCornerShape(16.dp))
            .background(colors.chartBackground),
    ) {

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            val fullGraphLayoutModel = getFullDensityGraphLayoutModel(
                canvasWidth = size.width,
                canvasHeight = size.height
            )
            val yScaleModel = calculateFullDensityYScale(
                chartsData = chartsData
            )



            drawDensityFullGraphYAxisArea(
                layoutModel = fullGraphLayoutModel.yAxisArea,
                yScaleModel = yScaleModel,
                textMeasurer = textMeasurer,
                colors = colors,
                typography = typography
            )

            drawDensityFullGraphYTitleArea(
                layoutModel = fullGraphLayoutModel.titleArea,
                textMeasurer = textMeasurer,
                colors = colors,
                typography = typography,
                title = y_title
            )

            drawDensityFullGraphXAxisArea(
                layoutModel = fullGraphLayoutModel.xAxisArea,
                chartsData = chartsData,
                textMeasurer = textMeasurer,
                colors = colors,
                typography = typography
            )

            drawDensityFullGraphStartPointArea(
                layoutModel = fullGraphLayoutModel.startPointArea,
                colors = colors,
                typography = typography
            )

            drawDensityFullGraphContentArea(
                layoutModel = fullGraphLayoutModel.chartArea,
                yScaleModel = yScaleModel,
                chartsData = chartsData,
                colors = colors,
                typography = typography
            )
        }
    }
}