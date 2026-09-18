package com.capybara.intuition.features.statistics.presentation.ui.free

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.capybara.intuition.features.statistics.presentation.ui.free.header.FreeStatisticsHeader
import com.capybara.intuition.features.statistics.charts.arts.activity.ActivityGraphic
import com.capybara.intuition.features.statistics.charts.calculators.calculateAnalyticsChartPoints
import com.capybara.intuition.features.statistics.charts.arts.decisionTime.DecisionTimeGraphic
import com.capybara.intuition.features.statistics.charts.arts.fullDensity.DensityFullGraphic
import com.capybara.intuition.features.statistics.charts.model.data.DayDensity


@Composable
fun FreeStatisticsScreen (
    selectedLevel: Int,
    availableLevels: IntRange,
    emaModelingPeriod: Int,
    analyticPeriod: Int,
    dateFrom: Long,
    dateNow: Long,
    analyticalGraph: List<DayDensity>,

    noiseCorridorVisibility: Boolean,
    physicalStateVisibility: Boolean,
    happinessVisibility: Boolean,
    selfConfidenceVisibility: Boolean,

    onToggleNoiseCorridor: () -> Unit,
    onTogglePhysicalState: () -> Unit,
    onToggleHappiness: () -> Unit,
    onToggleSelfConfidence: () -> Unit,

    onLevelSelected: (Int) -> Unit,
    onHelpLinkClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
)  {

    val statisticsSelectorSpacing = 12.dp
    val scrollState = rememberScrollState()
    val statisticsActivityBottomSpacing = 24.dp


    val chartsData = remember(
        selectedLevel,
        analyticalGraph
    ) {
        calculateAnalyticsChartPoints(
            level = selectedLevel,
            dayDensities = analyticalGraph,
            dateFrom = dateFrom,
            dateNow = dateNow,
            emaModelingPeriod = emaModelingPeriod,
            analyticPeriod = analyticPeriod
        )
    }

    // Прибрали падінг (8.dp), оскільки StatisticsScreen тепер надає уніфікований корінь з 16.dp.
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        FreeStatisticsHeader(
            selectedLevel = selectedLevel,
            availableLevels = availableLevels,
            onLevelSelected = onLevelSelected
        )

        Spacer(
            modifier = Modifier.height(
                statisticsSelectorSpacing
            )
        )

        // =========================================
        // MAIN CHART
        // =========================================
        DensityFullGraphic (
            chartsData = chartsData,
            noiseCorridorVisibility = noiseCorridorVisibility,
            physicalStateVisibility = physicalStateVisibility,
            happinessVisibility = happinessVisibility,
            selfConfidenceVisibility = selfConfidenceVisibility,
            onToggleNoiseCorridor = onToggleNoiseCorridor,
            onTogglePhysicalState = onTogglePhysicalState,
            onToggleHappiness = onToggleHappiness,
            onToggleSelfConfidence = onToggleSelfConfidence,
            onHelpLinkClick = onHelpLinkClick
        )

        Spacer(
            modifier = Modifier.height(
                statisticsSelectorSpacing
            )
        )

        // =========================================
        // INSIGHT
        // =========================================

        /*
        PerformanceInsightSection(
            chartPoints = chartsData
        )

        Spacer(
            modifier = Modifier.height(
                statisticsSelectorSpacing
            )
        )

         */

        // =========================================
        // DECISION TIME
        // =========================================

        DecisionTimeGraphic(
            chartsData = chartsData,
            onHelpLinkClick = onHelpLinkClick
        )

        Spacer(
            modifier = Modifier.height(
                statisticsSelectorSpacing
            )
        )

        // =========================================
        // ACTIVITY CHART
        // =========================================

        ActivityGraphic(
            chartsData = chartsData,
            onHelpLinkClick = onHelpLinkClick
        )


        Spacer(
            modifier = Modifier.height(
                statisticsActivityBottomSpacing
            )
        )
    }
}
