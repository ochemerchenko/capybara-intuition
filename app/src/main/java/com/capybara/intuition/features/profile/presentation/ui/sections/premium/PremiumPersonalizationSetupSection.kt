package com.capybara.intuition.features.profile.presentation.ui.sections.premium

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import com.capybara.intuition.R
import com.capybara.intuition.core.styles.IntuitionOutlinedTextFieldStyle
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.profile.domain.model.PersonalizationSettings

@Composable
fun PremiumPersonalizationSetupSection (
    initialName: String,
    shouldRequireName: Boolean,
    onSubmit: (PersonalizationSettings) -> Unit
){

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    val premiumHumanProfileMinimumNameLength = 3
    val premiumHumanProfilePinLength = 4

    var parentName by remember {
        mutableStateOf(
            initialName
        )
    }

    var parentPin by remember {
        mutableStateOf("")
    }

    var confirmParentPin by remember {
        mutableStateOf("")
    }

    // =====================================================
    // VALIDATION
    // =====================================================
    val isParentNameValid =
        parentName.trim().length >=
                premiumHumanProfileMinimumNameLength &&
                parentName.trim() != "Player"

    val isParentPinValid =
        parentPin.length ==
                premiumHumanProfilePinLength &&
                parentPin.all(Char::isDigit)

    val isConfirmParentPinValid =
        confirmParentPin == parentPin &&
                confirmParentPin.isNotBlank()

    val isSetupFormValid =
        if (shouldRequireName) {
            isParentNameValid &&
                    isParentPinValid &&
                    isConfirmParentPinValid
        } else {
            isParentPinValid &&
                    isConfirmParentPinValid
        }





    Text(
        text = stringResource(R.string.settings_premium_human_profile_setup_label),
        style = typography.bodyMedium,
        color = colors.activeElementText
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
        textStyle = IntuitionOutlinedTextFieldStyle.textStyle(),
        label = {
            Text(
                stringResource(R.string.settings_premium_human_profile_setup_name),
                style = IntuitionOutlinedTextFieldStyle.labelStyle()
            )
        },
        colors = IntuitionOutlinedTextFieldStyle.colors(),
        singleLine = true
    )

    Text(
        text =
            if (shouldRequireName) {
                if (isParentNameValid) {
                    stringResource(R.string.settings_premium_human_profile_setup_correct)
                } else {
                    stringResource(R.string.settings_premium_human_profile_setup_incorrect)
                }
            } else {
                stringResource(R.string.settings_premium_human_profile_setup_rename)
            },
        color =
            if (
                !shouldRequireName || isParentNameValid
            ) {
                colors.validationCorrect
            } else {
                colors.validationIncorrect
            },
        style = typography.bodyMedium
    )

    // =========================================
    // PIN
    // =========================================

    OutlinedTextField(
        value = parentPin,
        onValueChange = {
            if (
                it.length <=
                premiumHumanProfilePinLength &&
                it.all(Char::isDigit)
            ) {
                parentPin = it
            }
        },
        modifier = Modifier.fillMaxWidth(),
        textStyle = IntuitionOutlinedTextFieldStyle.textStyle(),
        label = {
            Text(
                stringResource(R.string.settings_premium_human_profile_setup_pin),
                style = IntuitionOutlinedTextFieldStyle.labelStyle()
            )
        },
        colors = IntuitionOutlinedTextFieldStyle.colors(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        singleLine = true
    )

    Text(

        text =

            if (isParentPinValid) {
                stringResource(R.string.settings_premium_human_profile_setup_correct)
            } else {
                stringResource(R.string.settings_premium_human_profile_setup_pin_incorrect)
                //"PIN must contain exactly 4 digits"
            },

        color =
            if (isParentPinValid) {
                colors.validationCorrect
            } else {
                colors.validationIncorrect
            },

        style = typography.bodyMedium
    )

    // =========================================
    // CONFIRM PIN
    // =========================================

    OutlinedTextField(
        value = confirmParentPin,
        onValueChange = {
            if (
                it.length <=
                premiumHumanProfilePinLength &&
                it.all(Char::isDigit)
            ) {
                confirmParentPin = it
            }
        },
        modifier = Modifier.fillMaxWidth(),
        textStyle = IntuitionOutlinedTextFieldStyle.textStyle(),
        label = {
            Text(
                stringResource(R.string.settings_premium_human_profile_setup_confirm_pin),
                style = IntuitionOutlinedTextFieldStyle.labelStyle(),
            )
        },
        colors = IntuitionOutlinedTextFieldStyle.colors(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        singleLine = true
    )

    Text(
        text =
            if (isConfirmParentPinValid) {
                stringResource(R.string.settings_premium_human_profile_setup_pin_incorrect)
            } else {
                stringResource(R.string.settings_premium_human_profile_setup_pin_not_matched)
            },

        color =
            if (isParentPinValid) {
                colors.validationCorrect
            } else {
                colors.validationIncorrect
            },
        style = typography.bodyMedium
    )

    // =========================================
    // SUBMIT
    // =========================================

    Button(
        onClick = {
            onSubmit(
                PersonalizationSettings(
                    name = parentName.trim(),
                    pin = parentPin
                )
            )
        },
        enabled = isSetupFormValid,
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
            text = stringResource(R.string.settings_premium_human_profile_setup_save)
        )
    }














}