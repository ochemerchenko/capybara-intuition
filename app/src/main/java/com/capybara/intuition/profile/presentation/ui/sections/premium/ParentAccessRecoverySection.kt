package com.capybara.intuition.profile.presentation.ui.sections.premium

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.app.presentation.styles.IntuitionOutlinedTextFieldStyle
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.profile.domain.model.recovery.ChallengeTask
import com.capybara.intuition.profile.presentation.ui.components.SettingsSection
import com.capybara.intuition.profile.tester.model.RecoveryError

@Composable
fun ParentAccessRecoverySection(

    challenge: ChallengeTask,
    isRecoveryVerified: Boolean,
    error: RecoveryError?,
    onAnswerSubmitted: (String) -> Unit,
    onPinResetConfirmed: (String) -> Unit,

    ) {

    // =====================================================
    // PARENT ACCESS RECOVERY SECTION SEMANTICS
    // =====================================================
    val parentAccessRecoveryContentSpacing = 12.dp

    // =====================================================
    // COLORS
    // =====================================================
    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    // =====================================================
    // CHALLENGE INPUT
    // =====================================================
    var answerInput by remember {
        mutableStateOf("")
    }

    // =====================================================
    // PIN RESET INPUT
    // =====================================================
    var newPinInput by remember {
        mutableStateOf("")
    }

    var confirmPinInput by remember {
        mutableStateOf("")
    }

    val errorText =
        when(error) {
            RecoveryError.INVALID_NUMBER ->
                stringResource(
                    R.string.settings_profile_recovery_enter_number_error
                )

            RecoveryError.WRONG_ANSWER ->
                stringResource(
                    R.string.settings_profile_recovery_wrong_answer_error
                )

            null ->
                null
        }

    SettingsSection(
        title = stringResource(R.string.settings_parent_access_recovery_section_title),
    ) {

        Column(

            verticalArrangement =
                Arrangement.spacedBy(
                    parentAccessRecoveryContentSpacing
                )
        ) {

            // =================================================
            // CHALLENGE STAGE
            // =================================================

            if (
                !isRecoveryVerified
            ) {

                Text(
                    text = stringResource(
                        challenge.descriptionRes
                    ),
                    style = typography.bodyMedium,
                    color = colors.activeElementText
                )

                Text(
                    text = stringResource(
                        challenge.leftOperand!!.descriptionRes,
                        challenge.leftOperand!!.value
                    ),
                    style = typography.bodyMedium,
                    color = colors.activeElementText
                )

                Text(
                    text = stringResource(
                        challenge.rightOperand!!.descriptionRes,
                        challenge.rightOperand!!.value
                    ),
                    style = typography.bodyMedium,
                    color = colors.activeElementText
                )

                OutlinedTextField(
                    value = answerInput,
                    onValueChange = {
                        answerInput = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = IntuitionOutlinedTextFieldStyle.textStyle(),
                    colors = IntuitionOutlinedTextFieldStyle.colors(),
                    label = {
                        Text(
                            stringResource(R.string.settings_parent_access_recovery_section_your_answer),
                            style = IntuitionOutlinedTextFieldStyle.labelStyle(),
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true
                )

                if (
                    error != null
                ) {

                    Text(
                        text = errorText!!
                    )
                }

                Button(
                    modifier = Modifier.Companion.fillMaxWidth(),
                    enabled = answerInput.isNotBlank(),
                    onClick = {
                        onAnswerSubmitted(
                            answerInput
                        )
                        answerInput = ""
                    },
                    colors = ButtonDefaults
                        .buttonColors(
                            containerColor =
                                colors.accent
                        )
                ) {
                    Text(
                        text = stringResource(R.string.settings_parent_access_recovery_section_verify)
                    )
                }
            }

            // =================================================
            // PIN RESET STAGE
            // =================================================

            else {

                OutlinedTextField(
                    value = newPinInput,
                    onValueChange = {
                        newPinInput = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = IntuitionOutlinedTextFieldStyle.textStyle(),
                    colors = IntuitionOutlinedTextFieldStyle.colors(),
                    label = {
                        Text(
                            stringResource(R.string.settings_parent_access_recovery_section_new_pin),
                            style = IntuitionOutlinedTextFieldStyle.labelStyle(),
                        )
                    },

                    keyboardOptions = KeyboardOptions(
                        keyboardType =
                            KeyboardType.Companion.NumberPassword
                    ),

                    singleLine = true
                )

                OutlinedTextField(
                    value = confirmPinInput,
                    onValueChange = {
                        confirmPinInput = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    textStyle = IntuitionOutlinedTextFieldStyle.textStyle(),
                    colors = IntuitionOutlinedTextFieldStyle.colors(),
                    label = {
                        Text(
                            stringResource(R.string.settings_parent_access_recovery_section_confirm_pin),
                            style = IntuitionOutlinedTextFieldStyle.labelStyle(),
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword
                    ),
                    singleLine = true
                )

                if (
                    error != null
                ) {

                    Text(
                        text = errorText!!
                    )
                }

                Button(
                    modifier = Modifier.Companion.fillMaxWidth(),
                    enabled = newPinInput
                        .isNotBlank() &&
                            confirmPinInput
                                .isNotBlank(),
                    onClick = {
                        if (
                            newPinInput !=
                            confirmPinInput
                        ) {
                            return@Button
                        }
                        onPinResetConfirmed(
                            newPinInput
                        )
                        newPinInput = ""
                        confirmPinInput = ""
                    },

                    colors = ButtonDefaults
                        .buttonColors(
                            containerColor =
                                colors.accent
                        )
                ) {

                    Text(
                        text = stringResource(R.string.settings_parent_access_recovery_section_update_pin)

                    )
                }
            }
        }
    }
}