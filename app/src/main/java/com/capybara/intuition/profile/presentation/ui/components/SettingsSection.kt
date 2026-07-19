package com.capybara.intuition.profile.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.capybara.intuition.app.presentation.theme.IntuitionTheme


@Composable
fun SettingsSection(
    title: String,
    content: @Composable () -> Unit
) {

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    // =====================================================
    // SETTINGS SECTION PRESENTATION SEMANTICS
    // =====================================================

    val settingsSectionCornerRadius = 24.dp
    val settingsSectionContentPadding = 16.dp
    val settingsSectionContentSpacing = 16.dp

    // =====================================================
    // UI
    // =====================================================

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(
            settingsSectionCornerRadius
        ),
        color = colors.backgroundSection
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    settingsSectionContentPadding
                ),
            verticalArrangement = Arrangement.spacedBy(
                settingsSectionContentSpacing
            )
        ) {

            Text(
                text = title,
                color = colors.textH2,
                style = typography.h2
            )

            Spacer(modifier = Modifier.height(4.dp))

            content()


        }
    }
}
