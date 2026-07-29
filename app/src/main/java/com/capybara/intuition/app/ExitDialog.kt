package com.capybara.intuition.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.app.presentation.theme.IntuitionTheme

@Composable
fun ExitDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = colors.backgroundSection,

        title = {
            Text(
                text = stringResource(R.string.exit_application_question),
                color = colors.activeElementText,
                style = typography.h2

            )
        },

        dismissButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextButton(
                    onClick = onDismiss,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = colors.activeElementBackground,
                        contentColor = colors.activeElementText
                    ),
                    modifier = Modifier
                        .weight(1f),
                ) {
                    Text(
                        text = stringResource(R.string.exit_application_no),
                        style = typography.bodyMedium
                    )
                }

                Spacer(
                    modifier = Modifier.weight(1f)
                )

                TextButton(
                    onClick = onConfirm,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.textButtonColors(
                        containerColor = colors.activeElementBackground,
                        contentColor = colors.activeElementText
                    ),
                    modifier = Modifier
                        .weight(1f),

                ) {
                    Text(stringResource(
                        R.string.exit_application_yes),
                        style = typography.bodyMedium
                    )
                }
            }
        },

                confirmButton = {}
    )
}