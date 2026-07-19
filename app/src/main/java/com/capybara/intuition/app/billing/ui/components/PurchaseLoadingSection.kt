package com.capybara.intuition.app.billing.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.capybara.intuition.app.presentation.theme.IntuitionTheme

@Composable
fun PurchaseLoadingSection(
    message: String
) {

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    val contentPadding = 24.dp
    val contentSpacing = 20.dp

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(contentPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(contentSpacing)
    ) {

        CircularProgressIndicator()

        Text(
            text = message,
            style = typography.bodyMedium,
            color = colors.activeElementText,
            textAlign = TextAlign.Center
        )
    }
}