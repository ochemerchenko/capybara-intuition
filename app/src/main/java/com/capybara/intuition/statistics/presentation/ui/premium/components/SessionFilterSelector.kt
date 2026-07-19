package com.capybara.intuition.statistics.presentation.ui.premium.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.capybara.intuition.statistics.presentation.mapper.sessionFilterLabelRes
import com.capybara.intuition.statistics.presentation.model.SessionFilter
import com.capybara.intuition.statistics.presentation.ui.shared.components.StatisticsChipSelector

@Composable
fun SessionFilterSelector(
    selected: SessionFilter,
    onSelect: (SessionFilter) -> Unit,
    modifier: Modifier = Modifier
) {


    Row(
        modifier = modifier
            .fillMaxWidth()

    ) {

        SessionFilter.entries.forEach { filter ->
            StatisticsChipSelector(
                text = stringResource(
                    sessionFilterLabelRes(filter)
                ),
                selected = selected == filter,
                onClick = {
                    onSelect(filter)
                },
                modifier = Modifier
                    .weight(1f),
            )
        }
    }
}
