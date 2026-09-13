package com.capybara.intuition.features.tester.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.capybara.intuition.R
import com.capybara.intuition.core.theme.IntuitionTheme
import kotlin.math.roundToInt

@Composable
fun MotivationScaleSelector(
    value: Int,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    Column(
        modifier = modifier
    ) {

        Text(
            text = stringResource(
                R.string.settings_motivation_scale,
                value),
            color = colors.activeElementText
        )

        Slider(
            value = value.toFloat(),
            onValueChange = {
                onValueChange(
                    it.roundToInt()
                )
            },
            valueRange = 10f..100f,
            steps = 89,
            colors = SliderDefaults.colors(
                thumbColor = colors.sliderThumb ,
                activeTrackColor = colors.sliderActiveTrack,
                inactiveTrackColor = colors.sliderUnActiveTrack,
                activeTickColor = colors.capybaraTerracotta,
                inactiveTickColor = colors.capybaraTerracotta
            )
        )
    }
}