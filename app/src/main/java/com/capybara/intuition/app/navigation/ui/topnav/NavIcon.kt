package com.capybara.intuition.app.navigation.ui.topnav

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.capybara.intuition.core.theme.IntuitionTheme

@Composable
fun NavIcon(
    icon: ImageVector,
    selected: Boolean,
    modifier: Modifier = Modifier,
    useOriginalColors: Boolean = false,
    onClick: () -> Unit
) {
    NavIcon(
        painter = rememberVectorPainter(icon),
        selected = selected,
        modifier = modifier,
        useOriginalColors = useOriginalColors,
        onClick = onClick
    )
}

@Composable
fun NavIcon(
    painter: Painter,
    selected: Boolean,
    modifier: Modifier = Modifier,
    useOriginalColors: Boolean = false,
    onClick: () -> Unit
) {

    // =====================================================
    // NAVIGATION ICON PRESENTATION SEMANTICS
    // =====================================================
    val selectedNavIconScale = 0.90f
    val unselectedNavIconScale = 0.75f
    val selectedNavIconAlpha = 1f
    val unselectedNavIconAlpha = 0.6f

    val colors = IntuitionTheme.colors

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
            if (useOriginalColors)
                Color.Unspecified
            else if (selected)
                colors.activeNavIcon
            else
                colors.passiveNavIcon,
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
            painter = painter,
            contentDescription = null,
            tint = color,

            modifier = modifier
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    this.alpha = alpha
                }
        )
    }
}
