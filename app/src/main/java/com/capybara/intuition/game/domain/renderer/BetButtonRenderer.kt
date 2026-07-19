package com.capybara.intuition.game.domain.renderer

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp


interface BetButtonRenderer {

    @Composable
    fun Render(
        maskId: Short,
        resource: Any,
        size: Dp
    )
}