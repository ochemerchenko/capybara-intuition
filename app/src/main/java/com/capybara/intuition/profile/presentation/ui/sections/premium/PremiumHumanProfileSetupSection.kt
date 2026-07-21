package com.capybara.intuition.profile.presentation.ui.sections.premium

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.app.db_cleaner.DatabaseRetentionPeriod
import com.capybara.intuition.app.presentation.styles.IntuitionOutlinedTextFieldStyle
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.game.domain.model.GameMode
import com.capybara.intuition.profile.domain.model.PersonalizationSettings
import com.capybara.intuition.profile.domain.model.Profile
import com.capybara.intuition.profile.domain.model.ProfileType
import com.capybara.intuition.profile.presentation.ui.components.SettingsSection
import com.capybara.intuition.profile.presentation.ui.sections.LevelSettingsSection
import com.capybara.intuition.profile.presentation.ui.sections.ProfileList
import com.capybara.intuition.profile.presentation.ui.sections.ThresholdHistorySection


@Composable
fun PremiumHumanProfileSetupSection(
    profiles: List<Profile>,
    setupParentProfile: (String, String) -> Unit,
    onProfileSelected: (Profile) -> Unit,
    onDecreaseLevel: () -> Unit,
    onIncreaseLevel: () -> Unit,
    onModeSelected: (GameMode) -> Unit,
    retentionPeriod: DatabaseRetentionPeriod,
    onRetentionPeriodChanged: (DatabaseRetentionPeriod) -> Unit
) {

    // =====================================================
    // PREMIUM HUMAN PROFILE SETUP SECTION SEMANTICS
    // =====================================================
    val premiumHumanProfileSetupSectionContentSpacing = 12.dp
    val premiumHumanProfileValidValueColor = Color(0xFF2E7D32)

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

        ModeSettingsSection(
            profile = activeProfile,
            onModeSelected = onModeSelected
        )

        LevelSettingsSection(
            profile = activeProfile,
            onDecreaseLevel = onDecreaseLevel,
            onIncreaseLevel = onIncreaseLevel
        )

        ThresholdHistorySection(
            retentionPeriod = retentionPeriod,
            onRetentionPeriodChanged = onRetentionPeriodChanged
        )

        ProfileList(
            profiles = profiles,
            onProfileSelected = onProfileSelected,
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
    }

}
