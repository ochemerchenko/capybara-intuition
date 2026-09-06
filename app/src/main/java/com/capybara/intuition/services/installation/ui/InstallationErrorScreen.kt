package com.capybara.intuition.services.installation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.core.theme.IntuitionTheme

@Composable
fun InstallationErrorScreen(
    modifier: Modifier = Modifier
) {

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    val contentPadding = 24.dp
    val contentSpacing = 16.dp


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(contentPadding),
        verticalArrangement = Arrangement.spacedBy(contentSpacing)

    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colors.backgroundSection,
                    shape = RoundedCornerShape(16.dp)
                )
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.installation_error_message),
                color = colors.activeElementText,
                style = typography.bodyMedium
            )

        }
    }
}