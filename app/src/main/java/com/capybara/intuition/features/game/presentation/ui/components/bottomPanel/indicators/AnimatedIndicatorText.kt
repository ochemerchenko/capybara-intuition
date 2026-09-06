package com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.indicators

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.capybara.intuition.core.theme.IntuitionTheme


@Composable
fun AnimatedIndicatorText(
    text: String,
    glowColor: Color,
    scale: Float,
    blur: Float,
    alpha: Float
) {

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    Box {

        Text(
            text = text,
            style = typography.bodyMedium,
            color = glowColor.copy(
                alpha = alpha * 0.5f
            ),
            modifier = Modifier
                .blur(blur.dp)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }
        )

        Text(
            text = text,
            style = typography.bodyMedium,
            color = colors.capybaraTerracotta,
            modifier = Modifier.graphicsLayer {
                scaleX = scale
                scaleY = scale
                this.alpha = alpha
            }
        )
    }
}