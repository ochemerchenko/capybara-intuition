package com.capybara.intuition.features.profile.presentation.ui.sections.free

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.profile.presentation.ui.components.SettingsSection

@Composable
fun FreePersonalizationSetupSection (
    onSubmit: (String) -> Unit
){

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography


    val freeHumanProfileSetupSectionContentSpacing = 12.dp

    val freeHumanProfileMinimumNameLength = 3

    var parentName by remember {
        mutableStateOf("")
    }

    val isParentNameValid = parentName.trim().length >=
            freeHumanProfileMinimumNameLength &&
            parentName.trim() != "Player"

    val isSetupFormValid = isParentNameValid

    SettingsSection(
        title = stringResource(R.string.settings_free_human_setup_profile_section_title)
    ) {

        Column(
            verticalArrangement =
                Arrangement.spacedBy(
                    freeHumanProfileSetupSectionContentSpacing
                )
        ) {

            Text(
                text = stringResource(R.string.settings_free_human_setup_profile_set_player_name),
                color = colors.activeElementText,
                style = typography.bodyMedium
            )

            // =========================================
            // OWNER NAME
            // =========================================
            OutlinedTextField(
                value = parentName,
                onValueChange = {
                    parentName = it
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = typography.bodyMedium.copy(
                    color = colors.outlinedTextFieldText
                ),
                label = {
                    Text(
                        stringResource(R.string.settings_free_human_setup_profile_player_name),
                        color = colors.activeElementText,
                        style = typography.bodySmallItalic
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(

                    // Рамка
                    focusedBorderColor = colors.outlinedFocusedBorderColor,
                    unfocusedBorderColor = colors.outlinedUnfocusedBorderColor,
                    disabledBorderColor = colors.outlinedDisabledBorderColor.copy(alpha = 0.4f),
                    errorBorderColor = colors.outlinedErrorBorderColor,

                    // Введений текст
                    focusedTextColor = colors.outlinedFocusedTextColor,
                    unfocusedTextColor = colors.outlinedUnfocusedTextColor,

                    // Cursor
                    cursorColor = colors.outlinedCursorColor,

                    // Label
                    focusedLabelColor = colors.outlinedFocusedLabelColor,
                    unfocusedLabelColor = colors.outlinedUnfocusedLabelColor
                ),
                singleLine = true
            )

            Text(
                text =
                    if (isParentNameValid) {
                        stringResource(R.string.settings_free_human_setup_profile_correct)
                    } else {
                        stringResource(R.string.settings_free_human_setup_profile_incorrect)
                    },
                color =
                    if (isParentNameValid) {
                        colors.validationCorrect
                    } else {
                        colors.validationIncorrect
                    },
                style = MaterialTheme
                    .typography
                    .bodySmall
            )

            // =========================================
            // SAVE
            // =========================================

            Button(
                onClick = {
                    onSubmit(parentName.trim())
                },
                enabled = isSetupFormValid,
                modifier = Modifier.fillMaxWidth(),

                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.activeButton,
                    contentColor = colors.activeButtonText,
                    disabledContainerColor = colors.passiveButton,
                    disabledContentColor = colors.passiveButtonText
                )
            ) {
                Text(
                    text = stringResource(R.string.settings_free_human_setup_profile_continue),
                    style = typography.bodyMedium
                )
            }
        }
    }
}