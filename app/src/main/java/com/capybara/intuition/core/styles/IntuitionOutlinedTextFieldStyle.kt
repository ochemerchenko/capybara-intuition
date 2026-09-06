package com.capybara.intuition.core.styles

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import com.capybara.intuition.core.theme.IntuitionTheme

object IntuitionOutlinedTextFieldStyle {


    @Composable
    fun colors() = with(IntuitionTheme.colors) {
        OutlinedTextFieldDefaults.colors(
            focusedBorderColor = outlinedFocusedBorderColor,
            unfocusedBorderColor = outlinedUnfocusedBorderColor,
            disabledBorderColor = outlinedDisabledBorderColor.copy(alpha = 0.4f),
            errorBorderColor = outlinedErrorBorderColor,

            focusedTextColor = outlinedFocusedTextColor,
            unfocusedTextColor = outlinedUnfocusedTextColor,

            cursorColor = outlinedCursorColor,

            focusedLabelColor = outlinedFocusedLabelColor,
            unfocusedLabelColor = outlinedUnfocusedLabelColor
        )
    }

    @Composable
    fun textStyle() =
        IntuitionTheme.typography.bodyMedium.copy(
            color = IntuitionTheme.colors.outlinedTextFieldText
        )

    @Composable
    fun labelStyle() =
        IntuitionTheme.typography.bodySmallItalic.copy(
            color = IntuitionTheme.colors.activeElementText
        )
}