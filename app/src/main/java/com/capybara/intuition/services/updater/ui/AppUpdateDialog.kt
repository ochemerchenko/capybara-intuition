package com.capybara.intuition.services.updater.ui

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.services.updater.model.UpdateUiStatus
import com.capybara.intuition.core.theme.IntuitionTheme

@Composable
fun AppUpdateDialog(
    updateUiStatus: UpdateUiStatus,
    onDismiss: () -> Unit,
    onUpdate: (Activity) -> Unit,
    onRestart: () -> Unit,
    onRetry: () -> Unit = {}
) {

    Log.d("Bell", "Started: AppUpdateDialog")

    val context = LocalContext.current
    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    // Бепечне визначення Activity
    val activity = remember(context) {
        context as? Activity
            ?: (context as? android.content.ContextWrapper)?.baseContext as? Activity
    }

    val title: String
    val message: String
    val confirmButtonText: String?
    val confirmAction: () -> Unit

    when (updateUiStatus) {
        UpdateUiStatus.Checking -> {
            title = stringResource(R.string.app_update_check_title) // "Checking for updates"
            message = stringResource(R.string.app_update_check_message) // "Please wait while we check Google Play for the latest version..."
            confirmButtonText = null
            confirmAction = {}
        }

        UpdateUiStatus.UpdateAvailable -> {
            title = stringResource(R.string.app_update_available_title) // "Application update"
            message = stringResource(R.string.app_update_available_message) // "A new version of Intuition is available. Would you like to download and install it now?"
            confirmButtonText = stringResource(R.string.app_update_update_button) // "Update"
            confirmAction = {
                activity?.let { onUpdate(it) }
            }
        }

        UpdateUiStatus.UpdateInProgress -> {
            title = stringResource(R.string.app_update_downloading_title) // "Downloading update..."
            message = stringResource(R.string.app_update_downloading_message) // "The update is currently downloading in the background. You can safely close this window and continue using the app."
            confirmButtonText = null
            confirmAction = {}
        }

        UpdateUiStatus.UpdateDownloaded -> {
            title = stringResource(R.string.app_update_ready_title) // "Update ready"
            message = stringResource(R.string.app_update_ready_message) // "The update has been successfully downloaded. Restart the application to complete installation."
            confirmButtonText = stringResource(R.string.app_update_restart_button) // "Restart"
            confirmAction = onRestart
        }

        UpdateUiStatus.RetryNeeded -> {
            title = stringResource(R.string.app_update_failed_title) // "Update failed"
            message = stringResource(R.string.app_update_failed_message) // "Unable to start or complete the update. Please check your internet connection and try again."
            confirmButtonText = stringResource(R.string.app_update_retry_button) // "Retry"
            confirmAction = onRetry
        }

        UpdateUiStatus.NoUpdateAvailable -> {
            return
        }
    }

    AlertDialog(
        containerColor = colors.backgroundSection,
        titleContentColor = colors.activeElementText,
        textContentColor = colors.activeElementText,
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
                style = typography.h2
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = message,
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = typography.bodyMedium
                )

                // Індикатор під час перевірки
                if (updateUiStatus == UpdateUiStatus.Checking) {
                    Spacer(modifier = Modifier.height(16.dp))
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(32.dp)
                            .height(32.dp),
                        color = colors.activeElementText,
                        strokeWidth = 3.dp
                    )
                }

                // Прогрес-бар під час завантаження
                if (updateUiStatus == UpdateUiStatus.UpdateInProgress) {
                    Spacer(modifier = Modifier.height(16.dp))
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp),
                        color = colors.activeElementText,
                        trackColor = colors.backgroundButton
                    )
                }
            }
        },
        dismissButton = null,
        confirmButton = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .background(
                          color = colors.backgroundButton,
                          shape = RoundedCornerShape(12.dp)
                        )
                        .weight(1f)
                ) {
                    Text(
                        text =
                            if (updateUiStatus == UpdateUiStatus.UpdateInProgress)
                                stringResource(R.string.app_update_hidden_button)
                            else
                                stringResource(R.string.app_update_dismiss_button),
                        color = colors.activeElementText,
                        style = typography.bodyMedium
                    )
                }

                if (confirmButtonText != null) {
                    TextButton(
                        onClick = confirmAction,
                        modifier = Modifier
                            .background(
                                color = colors.backgroundButton,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .weight(1f)

                    ) {
                        Text(
                            text = confirmButtonText,
                            color = colors.activeElementText,
                            style = typography.bodyMedium
                        )
                    }
                }
            }
        }
        /*
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    if (updateUiStatus == UpdateUiStatus.UpdateInProgress)
                        stringResource(R.string.app_update_hidden_button) // "Hide"
                    else
                        stringResource(R.string.app_update_dismiss_button) // "Dismiss"
                )
            }
        },
        confirmButton = {
            if (confirmButtonText != null) {
                TextButton(onClick = confirmAction) {
                    Text(confirmButtonText)
                }
            }
        }

         */
    )
}