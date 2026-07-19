package com.capybara.intuition.profile.presentation.ui.sections.premium

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.app.presentation.styles.IntuitionOutlinedTextFieldStyle
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.profile.domain.model.Profile
import com.capybara.intuition.profile.domain.model.ProfileType
import com.capybara.intuition.profile.presentation.ui.components.SettingsSection


@Composable
fun ChildProfileCreateSection(
    profiles: List<Profile>,
    onChildProfileCreate: (String) -> Unit,
) {

    // =====================================================
    // CHILD PROFILE CREATE SECTION SEMANTICS
    // =====================================================
    val childProfileCreateSectionContentSpacing = 12.dp
    val childProfileMinimumNameLength = 3

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
    val isProfileSetupCompleted = isHumanProfile && hasPin && hasCustomName
    val shouldShowChildProfileCreation = isProfileSetupCompleted

    // =====================================================
    // LOCAL UI STATE
    // =====================================================
    var childProfileName by remember {
        mutableStateOf("")
    }
    val isChildProfileNameAlreadyExists =
        profiles.any {
            it.name.equals(
                childProfileName.trim(),
                ignoreCase = true
            )
        }

    // =====================================================
    // VALIDATION
    // =====================================================
    val isChildProfileNameValid =
        childProfileName.trim().length >=
                childProfileMinimumNameLength &&
                !isChildProfileNameAlreadyExists

    // =====================================================
    // COLORS
    // =====================================================
    val colors = IntuitionTheme.colors

    // =====================================================
    // UI
    // =====================================================
    if (shouldShowChildProfileCreation) {

        SettingsSection(
            title = stringResource(R.string.settings_child_profile_create_section_title)
        ) {

            Column(
                verticalArrangement =
                    Arrangement.spacedBy(
                        childProfileCreateSectionContentSpacing
                    )
            ) {

                OutlinedTextField(

                    value = childProfileName,
                    onValueChange = {
                        childProfileName = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = IntuitionOutlinedTextFieldStyle.textStyle(),
                    colors = IntuitionOutlinedTextFieldStyle.colors(),
                    label = {
                        Text(
                            stringResource(R.string.settings_child_profile_create_section_name_label),
                            style = IntuitionOutlinedTextFieldStyle.labelStyle(),
                        )
                    },
                    singleLine = true

                )

                val isChildProfileNameValid =
                    childProfileName.trim().length >=
                            childProfileMinimumNameLength &&
                            !isChildProfileNameAlreadyExists

                Button(
                    onClick = {
                        onChildProfileCreate(
                            childProfileName.trim()
                        )
                        childProfileName = ""
                    },
                    enabled = isChildProfileNameValid,
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.activeLevelButton,
                        contentColor = colors.activeLevelButtonText,
                        disabledContainerColor = colors.passiveLevelButton,
                        disabledContentColor = colors.passiveLevelButtonText
                    )
                ) {
                    Text(
                        text = stringResource(R.string.settings_child_profile_create_section_button_text)
                    )
                }
            }
        }
    }
}
