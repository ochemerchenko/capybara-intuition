package com.capybara.intuition.features.profile.tester.components.wrappers


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.capybara.intuition.R
import com.capybara.intuition.features.profile.tester.components.TesterChipSelector
import com.capybara.intuition.features.profile.tester.model.TrainingIntensity


@Composable
fun TrainingIntensitySelector(
    selectedValue: TrainingIntensity,
    onValueSelected: (TrainingIntensity) -> Unit,
    modifier: Modifier = Modifier
) {

    TesterChipSelector(
        title = stringResource(R.string.settings_training_intensity_selector_title),
        values = TrainingIntensity.entries,
        selectedValue = selectedValue,
        labelProvider = {
            when (it) {
                TrainingIntensity.LOW -> stringResource(R.string.settings_training_intensity_selector_low)
                TrainingIntensity.MEDIUM -> stringResource(R.string.settings_training_intensity_selector_medium)
                TrainingIntensity.HIGH -> stringResource(R.string.settings_training_intensity_selector_high)
            }
        },
        onValueSelected = onValueSelected,
        modifier = modifier
    )
}