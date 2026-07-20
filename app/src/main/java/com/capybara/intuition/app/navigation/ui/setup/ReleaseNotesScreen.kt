package com.capybara.intuition.app.navigation.ui.setup

import android.widget.Button
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ReleaseNotesScreen (
    onConfirm: () -> Unit
){
    Text(
        text = "Онбордінг"

    )

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onConfirm
    ) {
        Text(
            text = "Завершити"
        )
    }
}