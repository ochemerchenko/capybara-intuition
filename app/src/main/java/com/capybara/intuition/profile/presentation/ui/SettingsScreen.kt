package com.capybara.intuition.profile.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.license.capability.RuntimeCapabilities
import com.capybara.intuition.app.presentation.layout.LocalAppLayoutEnvironment
import com.capybara.intuition.profile.domain.model.ProfileType
import com.capybara.intuition.profile.presentation.ui.sections.premium.ChildProfileCreateSection
import com.capybara.intuition.profile.presentation.ui.sections.free.FreeHumanProfileSetupSection
import com.capybara.intuition.profile.presentation.ui.sections.LevelSettingsSection
import com.capybara.intuition.profile.presentation.ui.sections.premium.ModeSettingsSection
import com.capybara.intuition.profile.presentation.ui.sections.premium.ParentAccessRecoverySection
import com.capybara.intuition.profile.presentation.ui.sections.premium.PremiumHumanProfileSetupSection
import com.capybara.intuition.profile.presentation.viewmodel.ProfileViewModel
import com.capybara.intuition.profile.presentation.ui.sections.ProfileList
import com.capybara.intuition.profile.presentation.ui.sections.premium.ReturnToParentByPinSection
import com.capybara.intuition.profile.presentation.viewmodel.SettingsViewModel


@Composable
fun SettingsScreen(
    profileViewModel: ProfileViewModel,
    settingsViewModel: SettingsViewModel,
    capabilities: RuntimeCapabilities,
    onOpenTesterTools: () -> Unit = {},
    onLearnMoreClick: () -> Unit = {},
    modifier: Modifier
) {

    val env = LocalAppLayoutEnvironment.current
    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    // =====================================================
    // PROFILE STATE
    // =====================================================

    val profiles by profileViewModel
        .profiles
        .collectAsState()

    val retentionPeriod by settingsViewModel
        .retentionPeriod
        .collectAsState()

    val activeProfile =
        profiles.firstOrNull {
            it.active
        }

    Log.d(
        "PROFILES",
        "SettingsScreen: profiles = $profiles"
    )

    if (activeProfile == null) {
        return
    }


    val isWaitingForParentPin = profileViewModel.isWaitingForParentPin
    val scrollState = rememberScrollState()

    LaunchedEffect(isWaitingForParentPin) {
        if (isWaitingForParentPin) {
            scrollState.animateScrollTo(
                scrollState.maxValue
            )
        }
    }

    // =====================================================
    // UI
    // =====================================================

    Box(
        modifier = Modifier
            .padding(
                env.screenContentPadding
            ),
        contentAlignment = Alignment.TopCenter
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(
                    env.screenSectionSpacing
                ),
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {

            Log.d("CAPABILITIES", "$capabilities")

            if ( capabilities.supportsMultiProfileRuntime ) {
                PremiumHumanProfileSetupSection(
                    profiles = profiles,
                    setupParentProfile = profileViewModel ::setupPremiumParentProfile,
                    onProfileSelected = profileViewModel ::onProfileSelected,
                    onDecreaseLevel = profileViewModel ::decreaseLevel,
                    onIncreaseLevel = profileViewModel ::increaseLevel,
                    onModeSelected = profileViewModel ::updatePreferredMode,
                    retentionPeriod = retentionPeriod,
                    onRetentionPeriodChanged = settingsViewModel::setRetentionPeriod
                )
            }
            else {
                FreeHumanProfileSetupSection(
                    profiles = profiles,
                    setupParentProfile = profileViewModel ::setupFreeParentProfile,
                    onProfileSelected = profileViewModel ::onProfileSelected,
                    onDecreaseLevel = profileViewModel ::decreaseLevel,
                    onIncreaseLevel = profileViewModel ::increaseLevel,
                    onLearnMoreClick = onLearnMoreClick,
                    retentionPeriod = retentionPeriod,
                    onRetentionPeriodChanged = settingsViewModel::setRetentionPeriod
                )
            }

            if ( capabilities.supportsMultiProfileRuntime )
            {

                ChildProfileCreateSection(
                    profiles = profiles,

                    onChildProfileCreate =
                        profileViewModel
                        ::createChildProfile
                )
            }

            if ( activeProfile.type == ProfileType.CHILD &&profileViewModel.isWaitingForParentPin ) {
                ReturnToParentByPinSection(
                    onConfirmPinClicked = { enteredPin ->
                        profileViewModel
                            .onReturnToParentPinEntered(
                                enteredPin
                            )
                    },

                    onPinRecoveryRequired = {
                        // profileViewModel.startPinRecoveryFlow()
                    }
                )
            }
            if ( profileViewModel.isWaitingForParentRecovery ) {
                val challenge = profileViewModel.recoveryChallenge

                if (challenge != null) {

                    ParentAccessRecoverySection(
                        challenge = challenge,
                        isRecoveryVerified = profileViewModel.isParentRecoveryVerified,
                        error = profileViewModel.recoveryErrorMessage,
                        onAnswerSubmitted = { answer ->
                            profileViewModel
                                .verifyRecoveryAnswer(
                                    answer
                                )
                        },
                        onPinResetConfirmed = { newPin ->
                            profileViewModel
                                .resetParentPinAfterRecovery(
                                    newPin
                                )
                        }
                    )
                }
            }

            if (
                capabilities.supportsAnalyticTesterProfileRuntime &&
                activeProfile.type == ProfileType.TESTER
            ) {
                Button(
                    onClick = onOpenTesterTools,
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.activeLevelButton,
                        contentColor = colors.activeLevelButtonText,
                        disabledContainerColor = colors.passiveLevelButton,
                        disabledContentColor = colors.passiveLevelButtonText
                    ),
                ) {
                    Text(
                        stringResource(R.string.settings_tester_tools),
                        style = typography.bodyMedium
                    )
                }

                Spacer(
                    Modifier.height(
                        16.dp
                    )
                )
            }
        }
    }
}
