package com.capybara.intuition.profile.presentation.ui.sections.premium

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.app.jobs.db_cleaner.DatabaseRetentionPeriod
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.game.domain.model.GameMode
import com.capybara.intuition.license.capability.RuntimeCapabilities
import com.capybara.intuition.profile.domain.model.Profile
import com.capybara.intuition.profile.domain.model.ProfileType
import com.capybara.intuition.profile.presentation.ui.sections.RetentionPeriodSection
import com.capybara.intuition.profile.presentation.ui.sections.common.CommonProfileSettingsSection


@Composable
fun PremiumHumanProfileSetupSection(
    profiles: List<Profile>,
    setupParentProfile: (String, String) -> Unit,
    onProfileSelected: (Profile) -> Unit,
    onDecreaseLevel: () -> Unit,
    onIncreaseLevel: () -> Unit,
    onModeSelected: (GameMode) -> Unit,
    retentionPeriod: DatabaseRetentionPeriod,
    onRetentionPeriodChanged: (DatabaseRetentionPeriod) -> Unit,
    capabilities: RuntimeCapabilities
) {

    // =====================================================
    // PREMIUM HUMAN PROFILE SETUP SECTION SEMANTICS
    // =====================================================
    val premiumHumanProfileSetupSectionContentSpacing = 12.dp

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
    val hasPin = !activeProfile.pinHash.isNullOrBlank()
    val hasCustomName = activeProfile.name != "Player"
    val isProfileSetupCompleted = isHumanProfile && hasPin
    val shouldShowProfileSetup = isHumanProfile && !isProfileSetupCompleted
    val shouldRequireName = !hasCustomName


    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography


    Column(
        verticalArrangement = Arrangement.spacedBy(
            premiumHumanProfileSetupSectionContentSpacing
        )
    ) {

        Text(
            text = stringResource(R.string.settings_premium_human_profile_setup_title),
            style = typography.h1,
            color = colors.textH1
        )

        CommonProfileSettingsSection(
            profile = activeProfile,
            profiles = profiles,
            capabilities = capabilities,
            onDecreaseLevel = onDecreaseLevel,
            onIncreaseLevel = onIncreaseLevel,
            onProfileSelected = onProfileSelected,
            onModeSelected = onModeSelected
        )

        if (shouldShowProfileSetup) {
            PremiumPersonalizationSetupSection(
                initialName = activeProfile.name,
                shouldRequireName = shouldRequireName,
                onSubmit = { settings ->
                    setupParentProfile(
                        settings.name,
                        settings.pin
                    )
                }
            )
        }

        if (isHumanProfile) {
            RetentionPeriodSection(
                retentionPeriod = retentionPeriod,
                onRetentionPeriodChanged = onRetentionPeriodChanged
            )
        }
    }
}
