package com.capybara.intuition.features.statistics.charts.arts.fullDensity

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoGraph
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.drawDensityFullGraphContentArea
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.drawDensityFullGraphStartPointArea
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.drawDensityFullGraphXAxisArea
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.drawDensityFullGraphYAxisArea
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.drawDensityFullGraphYTitleArea
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.components.header.ChartHeader
import com.capybara.intuition.features.statistics.charts.calculators.graphLayoutsModels.getFullDensityGraphLayoutModel
import com.capybara.intuition.features.statistics.charts.calculators.yScale.calculateFullDensityYScale
import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint

@Composable
fun DensityFullGraphic(
    chartsData: List<AnalyticsChartPoint>,
    noiseCorridorVisibility: Boolean,
    physicalStateVisibility: Boolean,
    happinessVisibility: Boolean,
    selfConfidenceVisibility: Boolean,
    onToggleNoiseCorridor: () -> Unit,
    onTogglePhysicalState: () -> Unit,
    onToggleHappiness: () -> Unit,
    onToggleSelfConfidence: () -> Unit,
    onHelpLinkClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val graphAspectRatio = 4f / 3f
    if (chartsData.isEmpty()) return

    val textMeasurer = rememberTextMeasurer()
    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography
    val yTitle = stringResource(R.string.statistics_density_axis_title)

    Column(modifier = Modifier.fillMaxWidth()) {
        ChartHeader(
            title = stringResource(R.string.statistics_density_full_graph_title),
            helpBaseName = "statistics_full_chart_legend",
            onHelpLinkClick = onHelpLinkClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(graphAspectRatio)
                .clip(RoundedCornerShape(16.dp))
                .background(colors.chartBackground),
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                // Графік забирає весь вільний простір, окрім місця для кнопок
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
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
                        title = yTitle
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
                        noiseCorridorVisibility = noiseCorridorVisibility,
                        physicalStateVisibility = physicalStateVisibility,
                        happinessVisibility = happinessVisibility,
                        selfConfidenceVisibility = selfConfidenceVisibility,
                        colors = colors,
                        typography = typography
                    )
                }

                // Кнопки притиснуті до низу всередині того ж самого бекграунду
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 12.dp,
                            bottom = 12.dp,
                            start = 36.dp,
                            end = 36.dp
                        ),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ChartControlStatButton(
                        icon = Icons.Default.AutoGraph,
                        baseColor = colors.chartUncertaintyCorridor,
                        isSelected = noiseCorridorVisibility,
                        onClick = onToggleNoiseCorridor,
                    )
                    ChartControlStatButton(
                        icon = Icons.Default.Favorite,
                        baseColor = colors.wellbeingPhysical,
                        isSelected = physicalStateVisibility,
                        onClick = onTogglePhysicalState,
                    )
                    ChartControlStatButton(
                        icon = Icons.Default.Face,
                        baseColor = colors.wellbeingHappiness,
                        isSelected = happinessVisibility,
                        onClick = onToggleHappiness,
                    )
                    ChartControlStatButton(
                        icon = Icons.Default.Psychology,
                        baseColor = colors.wellbeingConfidence,
                        isSelected = selfConfidenceVisibility,
                        onClick = onToggleSelfConfidence,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun ChartControlStatButton(
    icon: ImageVector,
    baseColor: Color,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val colors = IntuitionTheme.colors
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(
                if (isSelected) baseColor else baseColor.copy(alpha = 0.25f)
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(20.dp),
            tint = if (isSelected) colors.activeLevelButtonText else baseColor
        )
    }
}
