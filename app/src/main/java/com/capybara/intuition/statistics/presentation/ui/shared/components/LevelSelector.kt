package com.capybara.intuition.statistics.presentation.ui.shared.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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
        //horizontalArrangement = Arrangement.Center
        horizontalArrangement = Arrangement.spacedBy(
            4.dp,
            Alignment.CenterHorizontally
        )
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
            if (level != availableLevels.last) {
                Spacer(
                    Modifier.weight(1f)
                )
            }

        }
    }

}