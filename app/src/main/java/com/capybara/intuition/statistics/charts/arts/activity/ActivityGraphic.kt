package com.capybara.intuition.statistics.charts.arts.activity

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.statistics.charts.arts.activity.components.activityGraphContentArea
import com.capybara.intuition.statistics.charts.arts.activity.components.activityGraphStartPointArea
import com.capybara.intuition.statistics.charts.arts.activity.components.activityGraphXAxisArea
import com.capybara.intuition.statistics.charts.arts.activity.components.activityGraphYAxisArea
import com.capybara.intuition.statistics.charts.arts.activity.components.activityGraphYTitleArea
import com.capybara.intuition.statistics.charts.arts.fullDensity.components.header.ChartHeader
import com.capybara.intuition.statistics.charts.calculators.graphLayoutsModels.getActivityGraphLayoutModel
import com.capybara.intuition.statistics.charts.calculators.yScale.calculateActivityYScale
import com.capybara.intuition.statistics.charts.model.data.AnalyticsChartPoint

@Composable
fun ActivityGraphic (
    chartsData: List<AnalyticsChartPoint>,
    onHelpLinkClick: (String) -> Unit = {},
    modifier: Modifier = Modifier.Companion
){
    val graphAspectRatio = 4f/3f
    if (chartsData.isEmpty()) return // І тут треба передбачити Text(Даних нема)
    val textMeasurer = rememberTextMeasurer()

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    ChartHeader(
        title = stringResource(
            R.string.statistics_session_activity
        ),
        helpBaseName = "statistics_activity_chart_legend",
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

        val title = stringResource(R.string.statistics_activity_graph_title)

        Canvas(
            modifier = Modifier.Companion
                .fillMaxSize()
                .padding(8.dp)
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
                title = title
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