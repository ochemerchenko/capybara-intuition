package com.capybara.intuition.features.statistics.presentation.ui.premium

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.statistics.presentation.model.SelectedChart
import com.capybara.intuition.features.statistics.presentation.model.SessionFilter
import com.capybara.intuition.features.statistics.presentation.ui.model.StatisticsSelectionState
import com.capybara.intuition.features.statistics.presentation.ui.premium.header.PremiumStatisticsHeader
import com.capybara.intuition.features.statistics.presentation.ui.premium.sections.PremiumDensityDetailSection
import com.capybara.intuition.features.statistics.presentation.ui.premium.sections.PremiumStatisticsDensitySection
import com.capybara.intuition.features.statistics.presentation.ui.shared.components.dialogs.MarkdownDialog
import com.capybara.intuition.features.statistics.charts.model.data.DayDensity

@Composable
fun PremiumStatisticsScreen(
    emaModelingPeriod: Int,
    analyticPeriod: Int,
    dateFrom: Long,
    dateNow: Long,

    analyticalGraphsPerLevel: LinkedHashMap<SessionFilter, List<DayDensity>>,
    analyticalGraphsPerMode: LinkedHashMap<Int, List<DayDensity>>?,

    availableLevels: IntRange,
    selectionState: StatisticsSelectionState,
    onSelectionStateChanged: (
        StatisticsSelectionState
    ) -> Unit,
    onHelpLinkClick: (String) -> Unit = {},
    onNavigateToInfo: () -> Unit,
    modifier: Modifier = Modifier,

) {
    val colors = IntuitionTheme.colors
    val statisticsSelectorSpacing = 12.dp
    val listState = rememberLazyListState()
    var showActivityLegend by remember {
        mutableStateOf(false)
    }


    var selectedChart by remember {
        mutableStateOf<SelectedChart?>(null)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .background(
                colors.backgroundScreen
            )
    ) {

        Column {

            PremiumStatisticsHeader(
                selectionState = selectionState,
                onSelectionStateChanged = onSelectionStateChanged,
                availableLevels = availableLevels
            )

            Spacer(
                modifier = Modifier.height(
                    statisticsSelectorSpacing
                )
            )

            LazyColumn(
                state = listState
            ) {
                item {
                    PremiumStatisticsDensitySection(
                        analyticalGraphsPerLevel = analyticalGraphsPerLevel,
                        analyticalGraphsPerMode = analyticalGraphsPerMode,
                        emaModelingPeriod = emaModelingPeriod,
                        analyticPeriod = analyticPeriod,
                        dateFrom = dateFrom,
                        dateNow = dateNow,
                        selectionState = selectionState,
                        onCardClick = {  selected ->
                            selectedChart = selected
                        },

                    )
                }
            }

            if (showActivityLegend) {
                MarkdownDialog(
                    "activity_chart_legend",
                    onDismiss = {
                        showActivityLegend = false
                    }
                )
            }
        }
        selectedChart?.let { selected ->

            PremiumDensityDetailSection(
                selectedChart = selected,
                dateFrom = dateFrom,
                dateNow = dateNow,
                analyticPeriod = analyticPeriod,
                emaModelingPeriod = emaModelingPeriod,
                onHelpLinkClick = onHelpLinkClick,
                onBack = {
                    selectedChart = null
                }
            )
        }
    }
}