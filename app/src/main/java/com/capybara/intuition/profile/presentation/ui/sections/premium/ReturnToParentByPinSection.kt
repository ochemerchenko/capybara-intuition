package com.capybara.intuition.profile.presentation.ui.sections.premium

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import com.capybara.intuition.app.presentation.styles.IntuitionOutlinedTextFieldStyle
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.profile.presentation.ui.components.SettingsSection

@Composable
fun ReturnToParentByPinSection(
    onConfirmPinClicked: (String) -> Unit,
    onPinRecoveryRequired: () -> Unit,
) {

    // =====================================================
    // RETURN TO PARENT BY PIN SECTION SEMANTICS
    // =====================================================
    val returnToParentPinSectionContentSpacing = 12.dp
    val returnToParentPinLength = 4

    // =====================================================
    // LOCAL STATE
    // =====================================================
    var pinInput by remember {
        mutableStateOf("")
    }

    var errorMessage by remember {
        mutableStateOf<String?>(null)
    }

    // =====================================================
    // COLORS
    // =====================================================
    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    // =====================================================
    // VALIDATION
    // =====================================================
    val isPinShapeValid = pinInput.length == returnToParentPinLength

    // =====================================================
    // UI
    // =====================================================
    SettingsSection(
        title = stringResource(R.string.settings_return_by_pin_section_title)
    ) {

        Column(
            verticalArrangement =
                Arrangement.spacedBy(
                    returnToParentPinSectionContentSpacing
                )
        ) {

            Text(
                text = stringResource(R.string.settings_return_by_pin_section_enter_pin),
                style = typography.bodyMedium,
                color = colors.activeElementText
            )

            // =============================================
            // PIN INPUT
            // =============================================
            OutlinedTextField(
                value = pinInput,
                onValueChange = {
                    if (
                        it.length <=
                        returnToParentPinLength &&
                        it.all(Char::isDigit)
                    ) {
                        pinInput = it
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                textStyle = IntuitionOutlinedTextFieldStyle.textStyle(),
                colors = IntuitionOutlinedTextFieldStyle.colors(),
                label = {
                    Text(
                        stringResource(R.string.settings_return_by_pin_section_enter_pin_label),
                        style = IntuitionOutlinedTextFieldStyle.labelStyle(),
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType =
                        KeyboardType.NumberPassword
                ),
                singleLine = true
            )

            // =============================================
            // VALIDATION MESSAGE
            // =============================================
            if (errorMessage != null) {

                Text(
                    text = errorMessage!!,
                    color = Color.Red,
                    style = MaterialTheme
                        .typography
                        .bodySmall
                )

            }

            // =============================================
            // CONFIRM
            // =============================================
            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = isPinShapeValid,
                onClick = {
                    onConfirmPinClicked(
                        pinInput
                    )
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.accent
                )
            ) {
                Text(
                    text = stringResource(R.string.settings_return_by_pin_section_confirm)
                )
            }
        }
    }
}