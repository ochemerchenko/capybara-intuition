package com.capybara.intuition.profile.presentation.ui.sections.premium

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.game.domain.model.GameMode
import com.capybara.intuition.profile.domain.model.Profile
import com.capybara.intuition.profile.presentation.ui.components.SettingsOptionRow
import com.capybara.intuition.profile.presentation.ui.components.SettingsSection

@Composable
fun ModeSettingsSection(
    profile: Profile,
    onModeSelected: (GameMode) -> Unit
) {

    // =====================================================
    // MODE SETTINGS SECTION PRESENTATION SEMANTICS
    // =====================================================

    val modeSettingsSectionOptionSpacing = 12.dp

    val mode = profile.preferredMode
    val colors = IntuitionTheme.colors

    SettingsSection(
        title = stringResource(
            R.string.settings_mode_selector_header
        )
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                modeSettingsSectionOptionSpacing
            ),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            GameMode.values().forEach { option ->
                SettingsOptionRow(
                    selected = mode == option,
                    text = when (option) {
                        GameMode.BEFORE -> stringResource(
                            R.string.settings_mode_before
                        )

                        GameMode.AFTER -> stringResource(
                            R.string.settings_mode_after
                        )

                        GameMode.HYBRID -> stringResource(
                            R.string.settings_mode_hybrid
                        )
                    },
                    onClick = {
                        onModeSelected(option)
                    }
                )
            }
        }
    }
}