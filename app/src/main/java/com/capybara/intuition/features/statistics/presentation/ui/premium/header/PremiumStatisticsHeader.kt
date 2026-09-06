package com.capybara.intuition.features.statistics.presentation.ui.premium.header

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.statistics.presentation.ui.model.PivotMode
import com.capybara.intuition.features.statistics.presentation.ui.model.StatisticsSelectionState
import com.capybara.intuition.features.statistics.presentation.ui.premium.components.PivotSwitch
import com.capybara.intuition.features.statistics.presentation.ui.premium.components.SessionFilterSelector
import com.capybara.intuition.features.statistics.presentation.ui.shared.components.LevelSelector



@Composable
fun PremiumStatisticsHeader(
    selectionState: StatisticsSelectionState,
    onSelectionStateChanged: (
        StatisticsSelectionState
    ) -> Unit,
    availableLevels: IntRange,
    modifier: Modifier = Modifier
) {

    // =====================================================
    // STATISTICS SCREEN PRESENTATION SEMANTICS
    // =====================================================
    val statisticsHeaderVerticalPadding = 12.dp
    val statisticsDividerThickness = 1.dp
    val statisticsDividerAlpha = 0.12f
    val statisticsSelectorSpacing = 12.dp

    val colors  = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = statisticsHeaderVerticalPadding
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        PivotSwitch(
            pivot = selectionState.pivotMode,
            onChange = {
                onSelectionStateChanged(
                    selectionState.copy(
                        pivotMode = it
                    )
                )
            }
        )
    }


    Spacer(
        modifier = Modifier.height(
            statisticsSelectorSpacing
        )
    )

    if (selectionState.pivotMode == PivotMode.LEVEL) {
        LevelSelector(
            selected = selectionState.selectedLevel,
            availableLevels = availableLevels,
            onSelect = {
                onSelectionStateChanged(
                    selectionState.copy(
                        selectedLevel = it
                    )
                )
            }
        )
    } else {
        SessionFilterSelector(
            selected = selectionState.selectedFilter,
            onSelect = {
                onSelectionStateChanged(
                    selectionState.copy(
                        selectedFilter = it
                    )
                )
            }
        )
    }

    Spacer(
        modifier = Modifier.height(
            statisticsSelectorSpacing
        )
    )

    HorizontalDivider(
        thickness = statisticsDividerThickness,
        color = IntuitionTheme
            .colors
            .textSecondary
            .copy(alpha = statisticsDividerAlpha)
    )

    Spacer(
        modifier = Modifier.height(
            statisticsSelectorSpacing
        )
    )
}