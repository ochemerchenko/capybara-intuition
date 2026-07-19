package com.capybara.intuition.app.navigation.ui.topnav

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun NavIcon(
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {

    // =====================================================
    // NAVIGATION ICON PRESENTATION SEMANTICS
    // =====================================================
    val selectedNavIconScale = 1.15f
    val unselectedNavIconScale = 1f
    val selectedNavIconAlpha = 1f
    val unselectedNavIconAlpha = 0.6f
    val unselectedNavIconColorAlpha = 0.5f
    val selectedNavIconColor = Color(0xFFA46850)
    val unselectedNavIconColor = MaterialTheme
            .colorScheme
            .onSurface
            .copy(
                alpha =
                    unselectedNavIconColorAlpha
            )

    // =====================================================
    // ANIMATED STATE
    // =====================================================

    val scale by animateFloatAsState(
        targetValue =
            if (selected)
                selectedNavIconScale
            else
                unselectedNavIconScale,
        label = ""
    )

    val color by animateColorAsState(
        targetValue =
            if (selected)
                selectedNavIconColor
            else
                unselectedNavIconColor,
        label = ""
    )

    val alpha by animateFloatAsState(
        targetValue =
            if (selected)
                selectedNavIconAlpha
            else
                unselectedNavIconAlpha,
        label = ""
    )

    // =====================================================
    // UI
    // =====================================================

    IconButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,

            modifier =
                Modifier.graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    this.alpha = alpha
                }
        )
    }
}