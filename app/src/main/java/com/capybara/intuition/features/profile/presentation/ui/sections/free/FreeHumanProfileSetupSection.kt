package com.capybara.intuition.features.profile.presentation.ui.sections.free

import androidx.compose.runtime.Composable
import com.capybara.intuition.services.database.model.DatabaseRetentionPeriod
import com.capybara.intuition.services.license.capability.RuntimeCapabilities
import com.capybara.intuition.features.profile.domain.model.Profile
import com.capybara.intuition.features.profile.domain.model.ProfileType
import com.capybara.intuition.features.profile.presentation.ui.sections.RetentionPeriodSection
import com.capybara.intuition.features.profile.presentation.ui.sections.common.CommonProfileSettingsSection

@Composable
fun FreeHumanProfileSetupSection(
    profiles: List<Profile>,
    setupParentProfile: (String) -> Unit,
    onProfileSelected: (Profile) -> Unit,
    onDecreaseLevel: () -> Unit,
    onIncreaseLevel: () -> Unit,
    onLearnMoreClick: () -> Unit,
    retentionPeriod: DatabaseRetentionPeriod,
    onRetentionPeriodChanged: (DatabaseRetentionPeriod) -> Unit,
    capabilities: RuntimeCapabilities
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

    CommonProfileSettingsSection(
        profile = activeProfile,
        profiles = profiles,
        capabilities = capabilities,
        onDecreaseLevel = onDecreaseLevel,
        onIncreaseLevel = onIncreaseLevel,
        onProfileSelected = onProfileSelected
    )

    if (shouldShowProfileSetup) {

        FreePersonalizationSetupSection(
            onSubmit = setupParentProfile
        )

    }

    ModeRestrictedSection(
        onLearnMoreClick = onLearnMoreClick
    )

    if (isHumanProfile) {
        RetentionPeriodSection(
            retentionPeriod = retentionPeriod,
            onRetentionPeriodChanged = onRetentionPeriodChanged
        )
    }

}