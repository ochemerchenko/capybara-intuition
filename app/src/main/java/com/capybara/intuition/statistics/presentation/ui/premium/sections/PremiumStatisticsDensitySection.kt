package com.capybara.intuition.statistics.presentation.ui.premium.sections

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.statistics.presentation.mapper.sessionFilterLabelRes
import com.capybara.intuition.statistics.presentation.model.SelectedChart
import com.capybara.intuition.statistics.presentation.model.SessionFilter
import com.capybara.intuition.statistics.presentation.ui.model.PivotMode
import com.capybara.intuition.statistics.presentation.ui.model.StatisticsSelectionState
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.arts.preview.PreviewDensityGraphic
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.calculators.calculateAnalyticsChartPoints
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data.DayDensity

@Composable
fun PremiumStatisticsDensitySection(
    analyticalGraphsPerLevel: LinkedHashMap<SessionFilter, List<DayDensity>>,
    analyticalGraphsPerMode: LinkedHashMap<Int, List<DayDensity>>?,

    emaModelingPeriod: Int,
    analyticPeriod: Int,
    dateFrom: Long,
    dateNow: Long,

    selectionState: StatisticsSelectionState,
    onCardClick: (selectedChart: SelectedChart) -> Unit,

) {

    // =====================================================
    // STATISTICS DENSITY SECTION PRESENTATION SEMANTICS
    // =====================================================

    when (selectionState.pivotMode) {

        PivotMode.LEVEL -> {

            val rows = analyticalGraphsPerLevel
                .entries
                .chunked(2)
            Column {

                rows.forEach { row ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        row.forEach { (sessionFilter, dayDensities) ->
                            val chartsData = remember(
                                selectionState.selectedLevel,
                                dayDensities
                            ) {
                                calculateAnalyticsChartPoints(
                                    level = selectionState.selectedLevel,
                                    dayDensities = dayDensities,
                                    dateFrom = dateFrom,
                                    dateNow = dateNow,
                                    emaModelingPeriod = emaModelingPeriod,
                                    analyticPeriod = analyticPeriod
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        onCardClick(
                                            SelectedChart(
                                                mode = sessionFilter,
                                                level = selectionState.selectedLevel,
                                                dayDensities = dayDensities
                                            )
                                        )
                                    }
                            ) {


                                PreviewDensityGraphic(
                                    chartsData = chartsData,
                                    title = stringResource(
                                        sessionFilterLabelRes(sessionFilter)
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }

                        if (row.size == 1) {
                            Spacer(
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                }
            }
        }

        PivotMode.SESSION_FILTER -> {

            val rows =
                analyticalGraphsPerMode
                    ?.entries
                    ?.chunked(2)
                    ?: emptyList()

            Column {

                rows.forEach { row ->

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {

                        row.forEach { (level, dayDensities) ->

                            val chartsData = remember(
                                level,
                                dayDensities
                            ) {
                                calculateAnalyticsChartPoints(
                                    level = level,
                                    dayDensities = dayDensities,
                                    dateFrom = dateFrom,
                                    dateNow = dateNow,
                                    emaModelingPeriod = emaModelingPeriod,
                                    analyticPeriod = analyticPeriod
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {

                                        onCardClick(
                                            SelectedChart(
                                                mode = selectionState.selectedFilter,
                                                level = level,
                                                dayDensities = dayDensities
                                            )
                                        )


                                    }
                            ) {
                                PreviewDensityGraphic(
                                    chartsData = chartsData,
                                    title = stringResource(
                                        R.string.statistics_detail_card_preview_header,
                                        level
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }

                        if (row.size == 1) {
                            Spacer(
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )
                }
            }
        }
    }
}