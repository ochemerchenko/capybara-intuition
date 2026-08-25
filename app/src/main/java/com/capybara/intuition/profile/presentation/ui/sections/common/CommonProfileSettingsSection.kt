package com.capybara.intuition.profile.presentation.ui.sections.common

import androidx.compose.runtime.Composable
import com.capybara.intuition.game.domain.model.GameMode
import com.capybara.intuition.license.capability.RuntimeCapabilities
import com.capybara.intuition.profile.domain.model.Profile
import com.capybara.intuition.profile.presentation.ui.sections.LevelSettingsSection
import com.capybara.intuition.profile.presentation.ui.sections.ProfileList
import com.capybara.intuition.profile.presentation.ui.sections.premium.ModeSettingsSection

@Composable
fun CommonProfileSettingsSection(
    profile: Profile,
    profiles: List<Profile>,
    capabilities: RuntimeCapabilities,
    onDecreaseLevel: () -> Unit,
    onIncreaseLevel: () -> Unit,
    onProfileSelected: (Profile) -> Unit,
    onModeSelected: (GameMode) -> Unit = {}
) {
    if (capabilities.supportsMultiProfileRuntime) {
        ModeSettingsSection(
            profile = profile,
            onModeSelected = onModeSelected
        )
    }

    LevelSettingsSection(
        profile = profile,
        onDecreaseLevel = onDecreaseLevel,
        onIncreaseLevel = onIncreaseLevel
    )

    ProfileList(
        profiles = profiles,
        onProfileSelected = onProfileSelected,
    )
}
