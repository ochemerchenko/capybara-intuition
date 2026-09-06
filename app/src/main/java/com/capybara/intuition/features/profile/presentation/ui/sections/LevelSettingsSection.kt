package com.capybara.intuition.features.profile.presentation.ui.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.profile.domain.model.Profile
import com.capybara.intuition.features.profile.presentation.ui.components.SettingsSection

@Composable
fun LevelSettingsSection(
    profile: Profile,
    onDecreaseLevel: () -> Unit,
    onIncreaseLevel: () -> Unit,
) {

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    // =====================================================
    // LEVEL SETTINGS SECTION PRESENTATION SEMANTICS
    // =====================================================

    val levelSettingsSectionControlsSpacing = 10.dp

    val minLevel = profile.minLevel
    val maxLevel = profile.maxLevel
    val level = profile.currentLevel


    SettingsSection(
        title = stringResource(
            R.string.settings_level_selector_header
        )
    ) {

        Row(
            //modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                levelSettingsSectionControlsSpacing
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = onDecreaseLevel,
                enabled = level > minLevel,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.activeLevelButton,
                    contentColor = colors.activeLevelButtonText,
                    disabledContainerColor = colors.passiveLevelButton,
                    disabledContentColor = colors.passiveLevelButtonText
                ),
                contentPadding = PaddingValues(0.dp),
            ) {
                Text(
                    text = stringResource(
                        R.string.settings_level_selector_down
                    ),
                    style = typography.bodyLarge
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = level.toString(),
                color = colors.activeElementText,
                style = typography.level
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = onIncreaseLevel,
                enabled = level < maxLevel,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.activeLevelButton,
                    contentColor = colors.activeLevelButtonText,
                    disabledContainerColor = colors.passiveLevelButton,
                    disabledContentColor = colors.passiveLevelButtonText
                ),
            ) {
                Text(
                    stringResource(
                        R.string.settings_level_selector_up
                    ),
                    style = typography.bodyLarge
                )
            }
        }
    }
}