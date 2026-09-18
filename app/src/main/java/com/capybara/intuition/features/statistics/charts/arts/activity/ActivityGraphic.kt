package com.capybara.intuition.features.statistics.charts.arts.activity

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.profile.presentation.ui.components.SettingsSection
import com.capybara.intuition.features.statistics.charts.arts.activity.components.activityGraphContentArea
import com.capybara.intuition.features.statistics.charts.arts.activity.components.activityGraphStartPointArea
import com.capybara.intuition.features.statistics.charts.arts.activity.components.activityGraphXAxisArea
import com.capybara.intuition.features.statistics.charts.arts.activity.components.activityGraphYAxisArea
import com.capybara.intuition.features.statistics.charts.arts.activity.components.activityGraphYTitleArea
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.header.ChartHeader
import com.capybara.intuition.features.statistics.charts.calculators.graphLayoutsModels.getActivityGraphLayoutModel
import com.capybara.intuition.features.statistics.charts.calculators.yScale.calculateActivityYScale
import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint

@Composable
fun ActivityGraphic (
    chartsData: List<AnalyticsChartPoint>,
    onHelpLinkClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
){
    val graphAspectRatio = 4f/3f
    if (chartsData.isEmpty()) return

    val textMeasurer = rememberTextMeasurer()
    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography
    val yAxisTitle = stringResource(R.string.statistics_activity_graph_title)

    SettingsSection(
        titleContent = {
            ChartHeader(
                title = stringResource(R.string.statistics_session_activity),
                helpBaseName = "statistics_activity_chart_legend",
                onHelpLinkClick = onHelpLinkClick
            )
        },
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(graphAspectRatio)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp) // Трохи місця для верхньої осі всередині Canvas
            ) {
                val activityGraphLayoutModel = getActivityGraphLayoutModel(
                    canvasWidth = size.width,
                    canvasHeight = size.height
                )

                val yScaleModel = calculateActivityYScale(
                    chartsData = chartsData
                )

                activityGraphYAxisArea(
                    layoutModel = activityGraphLayoutModel.yAxisArea,
                    yScaleModel = yScaleModel,
                    textMeasurer = textMeasurer,
                    colors = colors,
                    typography = typography
                )

                activityGraphYTitleArea(
                    layoutModel = activityGraphLayoutModel.titleArea,
                    textMeasurer = textMeasurer,
                    yScaleModel = yScaleModel,
                    colors = colors,
                    typography = typography,
                    title = yAxisTitle
                )

                activityGraphXAxisArea(
                    layoutModel = activityGraphLayoutModel.xAxisArea,
                    chartsData = chartsData,
                    textMeasurer = textMeasurer,
                    colors = colors,
                    typography = typography
                )

                activityGraphStartPointArea(
                    layoutModel = activityGraphLayoutModel.startPointArea,
                    colors = colors,
                    typography = typography
                )

                activityGraphContentArea(
                    layoutModel = activityGraphLayoutModel.chartArea,
                    yScaleModel = yScaleModel,
                    chartsData = chartsData,
                    textMeasurer = textMeasurer,
                    colors = colors,
                    typography = typography
                )
            }
        }
    }
}
