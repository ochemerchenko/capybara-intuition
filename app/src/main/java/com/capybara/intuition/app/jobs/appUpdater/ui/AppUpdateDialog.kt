package com.capybara.intuition.app.jobs.appUpdater.ui

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import com.capybara.intuition.app.jobs.appUpdater.model.UpdateUiStatus

@Composable
fun AppUpdateDialog(
    updateUiStatus: UpdateUiStatus,
    onDismiss: () -> Unit,
    onUpdate: (Activity) -> Unit,
    onRestart: () -> Unit,
    onRetry: () -> Unit = {}
) {
    val context = LocalContext.current

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
            title = "Checking for updates"
            message = "Please wait while we check Google Play for the latest version..."
            confirmButtonText = null
            confirmAction = {}
        }

        UpdateUiStatus.UpdateAvailable -> {
            title = "Application update"
            message = "A new version of Intuition is available. Would you like to download and install it now?"
            confirmButtonText = "Update"
            confirmAction = {
                activity?.let { onUpdate(it) }
            }
        }

        UpdateUiStatus.UpdateInProgress -> {
            title = "Downloading update..."
            message = "The update is currently downloading in the background. You can safely close this window and continue using the app."
            confirmButtonText = null
            confirmAction = {}
        }

        UpdateUiStatus.UpdateDownloaded -> {
            title = "Update ready"
            message = "The update has been successfully downloaded. Restart the application to complete installation."
            confirmButtonText = "Restart"
            confirmAction = onRestart
        }

        UpdateUiStatus.RetryNeeded -> {
            title = "Update failed"
            message = "Unable to start or complete the update. Please check your internet connection and try again."
            confirmButtonText = "Retry"
            confirmAction = onRetry
        }

        UpdateUiStatus.NoUpdateAvailable -> {
            return
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(title)
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = message,
                    modifier = Modifier.fillMaxWidth()
                )

                // Індикатор під час перевірки
                if (updateUiStatus == UpdateUiStatus.Checking) {
                    Spacer(modifier = Modifier.height(16.dp))
                    CircularProgressIndicator()
                }

                // Прогрес-бар під час завантаження
                if (updateUiStatus == UpdateUiStatus.UpdateInProgress) {
                    Spacer(modifier = Modifier.height(16.dp))
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(if (updateUiStatus == UpdateUiStatus.UpdateInProgress) "Hide" else "Dismiss")
            }
        },
        confirmButton = {
            if (confirmButtonText != null) {
                TextButton(onClick = confirmAction) {
                    Text(confirmButtonText)
                }
            }
        }
    )
}