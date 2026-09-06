package com.capybara.intuition.features.profile.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.capybara.intuition.core.theme.IntuitionTheme

@Composable
fun SettingsOptionRow(

    selected: Boolean,
    text: String,
    onClick: () -> Unit

) {

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    // =====================================================
    // SETTINGS OPTION ROW PRESENTATION SEMANTICS
    // =====================================================

    val settingsOptionRowCornerRadius = 16.dp
    val settingsOptionRowHorizontalPadding = 12.dp
    val settingsOptionRowVerticalPadding = 10.dp
    val settingsOptionRowRadioSpacing = 12.dp

    // =====================================================
    // UI
    // =====================================================

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color =
                    if (selected) {
                        colors.backgroundActiveRowControl
                    } else {
                        colors.backgroundPassiveRowControl
                    },

                shape = RoundedCornerShape(
                    settingsOptionRowCornerRadius
                )
            )
            .clickable(
                onClick = onClick
            )
            .padding(
                horizontal = settingsOptionRowHorizontalPadding,
                vertical = settingsOptionRowVerticalPadding
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        RadioButton(
            selected = selected,
            onClick = null,
            colors = RadioButtonDefaults.colors(
                selectedColor = colors.activeRadioButton,
                unselectedColor = colors.passiveRadioButton,
            )
        )

        Spacer(
            modifier = Modifier.width(
                settingsOptionRowRadioSpacing
            )
        )

        Text(
            text = text,
            color =
                if (selected) {
                    colors.activeElementText
                } else {
                    colors.passiveElementText
                },
            style = typography.bodyMedium
        )
    }
}
