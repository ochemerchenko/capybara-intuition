package com.capybara.intuition.features.wellbeing.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.capybara.intuition.R
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.profile.domain.model.Profile
import com.capybara.intuition.features.wellbeing.presentation.viewmodel.WellbeingViewModel

@Composable
fun WellbeingDialog(
    profile: Profile,
    viewModel: WellbeingViewModel
) {
    val physical by viewModel.physical.collectAsState()
    val happiness by viewModel.happiness.collectAsState()
    val confidence by viewModel.confidence.collectAsState()

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    Dialog(onDismissRequest = { viewModel.skip() }) {
        Column(
            modifier = Modifier
                .background(
                    color = colors.backgroundSection,
                    shape = RoundedCornerShape(28.dp)
                )
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.wellbeing_dialog_hello, profile.name),
                style = typography.h1,
                color = colors.textH1
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.wellbeing_dialog_title),
                style = typography.h2,
                color = colors.textH2
            )

            Spacer(modifier = Modifier.height(32.dp))

            // --- Physical ---
            WellbeingSlider(
                label = stringResource(R.string.wellbeing_physical_label),
                value = physical,
                baseColor = colors.wellbeingPhysical,
                onValueChange = viewModel::updatePhysical,
                icon = { WellbeingImageIcon(mapPhysicalToResId(physical)) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- Happiness ---
            WellbeingSlider(
                label = stringResource(R.string.wellbeing_happiness_label),
                value = happiness,
                baseColor = colors.wellbeingHappiness,
                onValueChange = viewModel::updateHappiness,
                icon = { WellbeingImageIcon(mapHappinessToResId(happiness)) }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // --- Confidence ---
            WellbeingSlider(
                label = stringResource(R.string.wellbeing_confidence_label),
                value = confidence,
                baseColor = colors.wellbeingConfidence,
                onValueChange = viewModel::updateConfidence,
                icon = { WellbeingImageIcon(mapConfidenceToResId(confidence)) }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = viewModel::skip,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.activeButton,
                        contentColor = colors.activeButtonText
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.wellbeing_button_skip),
                        style = typography.bodyMedium,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )
                }

                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { viewModel.save(profile.id) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.activeButton,
                        contentColor = colors.activeButtonText
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.wellbeing_button_save),
                        style = typography.bodyMedium,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun WellbeingSlider(
    label: String,
    value: Int,
    baseColor: Color,
    onValueChange: (Int) -> Unit,
    icon: @Composable () -> Unit
) {
    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = typography.bodyMedium,
            color = colors.textH2,
            modifier = Modifier
                .padding(bottom = 12.dp)
        )

        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Slider(
                value = value.toFloat(),
                onValueChange = {
                    onValueChange(it.toInt())
                },
                valueRange = 0f..100f,
                colors = SliderDefaults.colors(
                    activeTrackColor = baseColor,
                    inactiveTrackColor = baseColor.copy(alpha = 0.25f),
                    thumbColor = baseColor
                ),
                track = { sliderState ->
                    SliderDefaults.Track(
                        sliderState = sliderState,
                        modifier = Modifier.height(8.dp), // Тут ми задаємо висоту доріжки
                        colors = SliderDefaults.colors(
                            activeTrackColor = baseColor,
                            inactiveTrackColor = baseColor.copy(alpha = 0.25f)
                        )
                    )
                },
                thumb = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .offset(
                                y = (8).dp
                            ) // Центруємо групу на треку
                    ) {
                        icon()
                        Text(
                            text = value.toString(),
                            style = typography.bodySmall,
                            color = colors.activeElementText,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            )
        }

        val zeroLabelAlpha = (value / 10f).coerceIn(0f, 1f)
        val hundredLabelAlpha = ((100 - value) / 10f).coerceIn(0f, 1f)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "0",
                style = typography.bodySmall,
                color = colors.activeElementText,
                modifier = Modifier.graphicsLayer { alpha = zeroLabelAlpha }
            )
            Text(
                text = "100",
                style = typography.bodySmall,
                color = colors.activeElementText,
                modifier = Modifier.graphicsLayer { alpha = hundredLabelAlpha }
            )
        }
    }
}

@Composable
private fun WellbeingImageIcon(resId: Int) {
    Image(
        painter = painterResource(resId),
        contentDescription = null,
        modifier = Modifier.size(36.dp) // Adjusted size for WebP icons
    )
}

private fun mapPhysicalToResId(value: Int): Int {
    return when {
        value < 20 -> R.drawable.wellbeing_physical_0_19
        value < 40 -> R.drawable.wellbeing_physical_20_39
        value < 60 -> R.drawable.wellbeing_physical_40_59
        value < 80 -> R.drawable.wellbeing_physical_60_79
        else -> R.drawable.wellbeing_physical_80_100
    }
}

private fun mapHappinessToResId(value: Int): Int {
    return when {
        value < 20 -> R.drawable.wellbeing_happyness_0_19
        value < 40 -> R.drawable.wellbeing_happyness_20_39
        value < 60 -> R.drawable.wellbeing_happyness_40_59
        value < 80 -> R.drawable.wellbeing_happyness_60_79
        else -> R.drawable.wellbeing_happyness_80_100
    }
}

private fun mapConfidenceToResId(value: Int): Int {
    return when {
        value < 20 -> R.drawable.wellbeing_self_confidence_0_19
        value < 40 -> R.drawable.wellbeing_self_confidence_20_39
        value < 60 -> R.drawable.wellbeing_self_confidence_40_59
        value < 80 -> R.drawable.wellbeing_self_confidence_60_79
        else -> R.drawable.wellbeing_self_confidence_80_100
    }
}
