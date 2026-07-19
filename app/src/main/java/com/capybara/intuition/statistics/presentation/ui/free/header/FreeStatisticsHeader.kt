package com.capybara.intuition.statistics.presentation.ui.free.header

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.statistics.presentation.ui.shared.components.LevelSelector

@Composable
fun FreeStatisticsHeader(
    selectedLevel: Int?,
    availableLevels: IntRange,
    onLevelSelected: (Int) -> Unit,
) {

    // =====================================================
    // STATISTICS SCREEN PRESENTATION SEMANTICS
    // =====================================================

    val statisticsHeaderVerticalPadding = 12.dp
    val statisticsDividerThickness = 1.dp
    val statisticsDividerAlpha = 0.12f
    val statisticsSelectorSpacing = 12.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = statisticsHeaderVerticalPadding
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = stringResource(
                R.string.statistics_title
            ),
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.titleLarge,
            color = IntuitionTheme.colors.textPrimary
        )

        Spacer(
            modifier = Modifier.weight(1f)
        )
    }

    Spacer(
        modifier = Modifier.height(
            statisticsSelectorSpacing
        )
    )

    LevelSelector(
        selected = selectedLevel,
        availableLevels = availableLevels,
        onSelect = onLevelSelected
    )

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