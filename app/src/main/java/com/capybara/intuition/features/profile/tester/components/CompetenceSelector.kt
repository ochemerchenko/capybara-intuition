package com.capybara.intuition.features.profile.tester.components

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
fun CompetenceSelector (
    value: Double,
    onValueChange: (Double) -> Unit,
    modifier: Modifier = Modifier
) {

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    Column(
        modifier = modifier
    ) {

        Text(
            text = stringResource(
                R.string.settings_competence,
            value),
            color = colors.activeElementText
        )

        Slider(
            value = value.toFloat(),
            onValueChange = {
                onValueChange(
                    (it * 100f).roundToInt() / 100.0
                )
            },
            valueRange = 0f..1f,
            steps = 99,
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