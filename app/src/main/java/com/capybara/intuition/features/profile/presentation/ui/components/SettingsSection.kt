package com.capybara.intuition.features.profile.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.capybara.intuition.core.theme.IntuitionTheme

@Composable
fun SettingsSection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    SettingsSection(
        titleContent = {
            val colors = IntuitionTheme.colors
            val typography = IntuitionTheme.typography
            Text(
                text = title,
                color = colors.textH2,
                style = typography.h2
            )
        },
        modifier = modifier,
        content = content
    )
}

@Composable
fun SettingsSection(
    titleContent: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val colors = IntuitionTheme.colors

    val settingsSectionCornerRadius = 24.dp
    val settingsSectionContentPadding = 16.dp
    val settingsSectionContentSpacing = 16.dp

    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(settingsSectionCornerRadius),
        color = colors.backgroundSection
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(settingsSectionContentPadding),
            verticalArrangement = Arrangement.spacedBy(settingsSectionContentSpacing)
        ) {
            titleContent()
            // Spacer removed if content spacing in Column is enough, 
            // but the original code had Spacer(4.dp) after title.
            // Arrangement.spacedBy(16.dp) will handle the gap.
            content()
        }
    }
}
