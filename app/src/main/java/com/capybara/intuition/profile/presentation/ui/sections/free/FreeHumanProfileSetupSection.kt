package com.capybara.intuition.profile.presentation.ui.sections.free

import androidx.compose.runtime.Composable
import com.capybara.intuition.profile.domain.model.Profile
import com.capybara.intuition.profile.domain.model.ProfileType
import com.capybara.intuition.profile.presentation.ui.sections.LevelSettingsSection
import com.capybara.intuition.profile.presentation.ui.sections.ProfileList

@Composable
fun FreeHumanProfileSetupSection(
    profiles: List<Profile>,
    setupParentProfile: (String) -> Unit,
    onProfileSelected: (Profile) -> Unit,
    onDecreaseLevel: () -> Unit,
    onIncreaseLevel: () -> Unit,
    onLearnMoreClick: () -> Unit
) {

    // =====================================================
    // ACTIVE PROFILE
    // =====================================================
    val activeProfile = profiles.first {
        it.active
    }

    // =====================================================
    // RUNTIME POLICY
    // =====================================================
    val isHumanProfile = activeProfile.type == ProfileType.HUMAN
    val hasCustomName = activeProfile.name != "Player"
    val isProfileSetupCompleted = isHumanProfile && hasCustomName

    val shouldShowProfileSetup =
        isHumanProfile &&
                !isProfileSetupCompleted



    // =====================================================
    // UI
    // =====================================================



    LevelSettingsSection(
        profile = activeProfile,
        onDecreaseLevel = onDecreaseLevel,
        onIncreaseLevel = onIncreaseLevel
    )

    ProfileList(
        profiles = profiles,
        onProfileSelected = onProfileSelected,
    )

    if (shouldShowProfileSetup) {

        FreePersonalizationSetupSection(
            onSubmit = setupParentProfile
        )

    }

    ModeRestrictedSection(
        onLearnMoreClick = onLearnMoreClick
    )

}