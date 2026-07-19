package com.capybara.intuition.statistics.presentation.ui.premium.sections

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.statistics.presentation.model.SelectedChart
import com.capybara.intuition.statistics.presentation.ui.premium.header.PremiumDensityDetailStatisticsHeader
import com.capybara.intuition.statistics.presentation.ui.shared.components.dialogs.MarkdownDialog
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.arts.activity.ActivityGraphic
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.calculators.calculateAnalyticsChartPoints
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.arts.decisionTime.DecisionTimeGraphic
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.arts.fullDensity.DensityFullGraphic


@Composable
fun PremiumDensityDetailSection(
    selectedChart: SelectedChart,
    dateFrom: Long,
    dateNow: Long,
    analyticPeriod: Int,
    emaModelingPeriod: Int,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onHelpLinkClick: (String) -> Unit = {},

    ) {

    // =====================================================
    // DENSITY DETAIL SCREEN PRESENTATION SEMANTICS
    // =====================================================
    val statisticsSelectorSpacing = 12.dp
    val scrollState = rememberScrollState()
    var showDetailLegend by remember {
        mutableStateOf(false)
    }

    selectedChart.dayDensities.forEachIndexed { index, point ->

        Log.d(
            "GRAPH_CHECK",
            "dayDensities.$index " +
                    "dayDensity=${point.dayDensity}, " +
                    "averageDecisionTime=${point.averageDecisionTime}"
        )
    }

    val chartsData = remember(
        selectedChart.level,
        selectedChart.dayDensities,
        dateFrom,
        dateNow,
        analyticPeriod,
        emaModelingPeriod
    ) {
        calculateAnalyticsChartPoints(
            level = selectedChart.level,
            dayDensities = selectedChart.dayDensities,
            dateFrom = dateFrom,
            dateNow = dateNow,
            emaModelingPeriod = emaModelingPeriod,
            analyticPeriod = analyticPeriod
        )
    }

    chartsData.forEachIndexed { index, point ->

        Log.d(
            "GRAPH_CHECK",
            "chartsData.$index " +
                    "userLine=${point.userLine}, " +
                    "userLineEma=${point.userLineEma}, " +
                    "time=${point.averageDayDecisionTime} " +
                    "timeEma=${point.averageDayDecisionTimeEma}"
        )
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(28.dp),
        color = IntuitionTheme.colors.backgroundSection
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {

            // =========================================
            // HEADER
            // =========================================
            PremiumDensityDetailStatisticsHeader(
                mode = selectedChart.mode,
                level = selectedChart.level,
                onBack = onBack,
                //onHelpClick = {
                //    showDetailLegend = true
                //},
                modifier = Modifier.fillMaxWidth()
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
                onHelpLinkClick = onHelpLinkClick
            )

            Spacer(
                modifier = Modifier.height(
                    16.dp
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
                    16.dp
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
            ActivityGraphic (
                chartsData = chartsData,
                onHelpLinkClick = onHelpLinkClick
            )

        }

        if (showDetailLegend) {

            MarkdownDialog(
                baseName = "density_detail_legend",
                onDismiss = {
                    showDetailLegend = false
                }
            )
        }
    }
}