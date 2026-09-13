package com.capybara.intuition.features.tester.components.wrappers

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.capybara.intuition.R
import com.capybara.intuition.features.tester.components.TesterChipSelector

private val PERIOD_OPTIONS = listOf(
    7,
    14,
    30,
    60,
    90,
    120,
    180
)

@Composable
fun HistoricalDatasetPeriodSelector(
    selectedPeriodDays: Int,
    onPeriodSelected: (Int) -> Unit,
    modifier: Modifier = Modifier.Companion
) {


    TesterChipSelector(
        title = stringResource(
            R.string.settings_period_selector_title
        ),
        values = PERIOD_OPTIONS,
        selectedValue = selectedPeriodDays,
        labelProvider = { days ->
            stringResource(
                R.string.settings_period_selector_days,
                days
            )
        },
        onValueSelected = onPeriodSelected,
        modifier = modifier
    )
}