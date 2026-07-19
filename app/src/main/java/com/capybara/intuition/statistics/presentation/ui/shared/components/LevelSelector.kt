package com.capybara.intuition.statistics.presentation.ui.shared.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.capybara.intuition.R

@Composable
fun LevelSelector(
    selected: Int?,
    availableLevels: IntRange,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

        availableLevels.forEach { level ->

            StatisticsChipSelector(
                text = stringResource(
                    R.string.statistics_level_selected,
                    level
                ),
                selected = selected == level,
                onClick = {
                    onSelect(level)
                },
            )

        }
    }

}