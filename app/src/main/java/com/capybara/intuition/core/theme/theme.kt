package com.capybara.intuition.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.capybara.intuition.core.theme.colors.IntuitionColors
import com.capybara.intuition.core.theme.typography.IntuitionTypography


private val DefaultIntuitionColors = IntuitionColors()
private val DefaultIntuitionTypography = IntuitionTypography()


private val LocalIntuitionColors =
    staticCompositionLocalOf<IntuitionColors> {
        error("No IntuitionColors provided")
    }

private val LocalIntuitionTypography =
    staticCompositionLocalOf<IntuitionTypography> {
        error("No IntuitionTypography provided")
    }

// 🔹 Theme object

object IntuitionTheme {

    val colors: IntuitionColors
        @Composable
        get() = LocalIntuitionColors.current

    val typography: IntuitionTypography
        @Composable
        get() = LocalIntuitionTypography.current
}

// 🔹 App theme

@Composable
fun IntuitionTheme(
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalIntuitionColors provides DefaultIntuitionColors,
        LocalIntuitionTypography provides DefaultIntuitionTypography
    ) {

        MaterialTheme(
            content = content
        )
    }
}