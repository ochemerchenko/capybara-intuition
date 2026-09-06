package com.capybara.intuition.features.statistics.charts.calculators

import androidx.compose.ui.geometry.Offset
import com.capybara.intuition.features.statistics.charts.calculators.yScale.model.DecisionTimeYScaleModel
import com.capybara.intuition.features.statistics.charts.model.chartsAreas.Area
import com.capybara.intuition.features.statistics.charts.model.chartsAreas.getDayCenterX
import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint

fun buildChartPointsTimeDecision(
    chartsData: List<AnalyticsChartPoint>,
    layoutModel: Area,
    decisionTimeYScaleModel: DecisionTimeYScaleModel,
    clipToViewport: Boolean = false,
    selector: (AnalyticsChartPoint) -> Double
): List<Offset> {

    return chartsData.mapIndexed { index, point ->

        val y = decisionTimeYScaleModel.toY(
            value = selector(point),
            chartTop = layoutModel.top,
            chartHeight = layoutModel.height
        )

        Offset(
            x = layoutModel.getDayCenterX(
                index = index,
                daysCount = chartsData.size
            ),
            y = if (clipToViewport) {
                y.coerceIn(
                    layoutModel.top,
                    layoutModel.top + layoutModel.height
                )
            } else {
                y
            }
        )
    }
}