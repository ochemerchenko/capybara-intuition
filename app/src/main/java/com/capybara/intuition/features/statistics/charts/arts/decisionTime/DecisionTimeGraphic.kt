package com.capybara.intuition.features.statistics.charts.arts.decisionTime

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.rememberTextMeasurer
import com.capybara.intuition.R
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.profile.presentation.ui.components.SettingsSection
import com.capybara.intuition.features.statistics.charts.arts.decisionTime.components.drawDecisionTimeGraphContentArea
import com.capybara.intuition.features.statistics.charts.arts.decisionTime.components.drawDecisionTimeGraphStartPointArea
import com.capybara.intuition.features.statistics.charts.arts.decisionTime.components.drawDecisionTimeGraphXAxisArea
import com.capybara.intuition.features.statistics.charts.arts.decisionTime.components.drawDecisionTimeGraphYAxisArea
import com.capybara.intuition.features.statistics.charts.arts.decisionTime.components.drawDecisionTimeGraphYTitleArea
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.header.ChartHeader
import com.capybara.intuition.features.statistics.charts.calculators.graphLayoutsModels.getDecisionTimeGraphLayoutModel
import com.capybara.intuition.features.statistics.charts.calculators.yScale.calculateDecisionTimeYScale
import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint

@Composable
fun DecisionTimeGraphic(
    chartsData: List<AnalyticsChartPoint>,
    onHelpLinkClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val graphAspectRatio = 2f / 1f
    if (chartsData.isEmpty()) return

    val textMeasurer = rememberTextMeasurer()
    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    val sec_time_unit_name = stringResource(R.string.statistics_decision_time_unit_seconds)
    val min_time_unit_name = stringResource(R.string.statistics_decision_time_unit_minutes)
    val yAxisTitle = stringResource(R.string.statistics_decision_time_axis_title)

    SettingsSection(
        titleContent = {
            ChartHeader(
                title = stringResource(R.string.statistics_decision_time_title),
                helpBaseName = "statistics_decision_time_chart_legend",
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
            Canvas(modifier = Modifier.fillMaxSize()) {
                val decisionTimeGraphLayoutModel = getDecisionTimeGraphLayoutModel(
                    canvasWidth = size.width,
                    canvasHeight = size.height
                )

                val yScaleModel = calculateDecisionTimeYScale(
                    chartsData = chartsData
                )

                drawDecisionTimeGraphYAxisArea(
                    layoutModel = decisionTimeGraphLayoutModel.yAxisArea,
                    yScaleModel = yScaleModel,
                    textMeasurer = textMeasurer,
                    colors = colors,
                    typography = typography
                )

                drawDecisionTimeGraphYTitleArea(
                    layoutModel = decisionTimeGraphLayoutModel.titleArea,
                    yScaleModel = yScaleModel,
                    textMeasurer = textMeasurer,
                    colors = colors,
                    typography = typography,
                    sec_time_unit_name = sec_time_unit_name,
                    min_time_unit_name = min_time_unit_name,
                    title = yAxisTitle,
                )

                drawDecisionTimeGraphXAxisArea(
                    layoutModel = decisionTimeGraphLayoutModel.xAxisArea,
                    chartsData = chartsData,
                    textMeasurer = textMeasurer,
                    colors = colors,
                    typography = typography
                )

                drawDecisionTimeGraphStartPointArea(
                    layoutModel = decisionTimeGraphLayoutModel.startPointArea,
                    colors = colors,
                    typography = typography
                )

                drawDecisionTimeGraphContentArea(
                    layoutModel = decisionTimeGraphLayoutModel.chartArea,
                    yScaleModel = yScaleModel,
                    chartsData = chartsData,
                    colors = colors,
                    typography = typography
                )
            }
        }
    }
}
