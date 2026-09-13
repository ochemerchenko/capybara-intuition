package com.capybara.intuition.features.tester.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.capybara.intuition.core.theme.IntuitionTheme

@Composable
fun LicenseChipSelector(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    // =====================================================
    // SELECTOR CHIP PRESENTATION SEMANTICS
    // =====================================================

    val selectorChipOuterHorizontalPadding = 4.dp
    val selectorChipHeight = 32.dp
    val selectorChipCornerRadius = 10.dp
    val selectorChipInnerHorizontalPadding = 6.dp

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    // =====================================================
    // UI
    // =====================================================

    Box(
        modifier = modifier
            .padding(
                horizontal = selectorChipOuterHorizontalPadding
            )
            .height(
                selectorChipHeight
            )
            .clip(
                RoundedCornerShape(
                    selectorChipCornerRadius
                )
            )
            .background(
                if (selected) {
                    colors.backgroundChipsActive
                } else {
                    colors.backgroundChipsPassive
                }
            )
            .clickable(
                enabled = !selected
            ) {
                onClick()
            }
            .padding(
                horizontal = selectorChipInnerHorizontalPadding
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            style = typography.bodyMedium,
            color =
                if (selected) {
                    colors.textChipsActive
                } else {
                    colors.textChipsPassive
                },

            )
    }
}