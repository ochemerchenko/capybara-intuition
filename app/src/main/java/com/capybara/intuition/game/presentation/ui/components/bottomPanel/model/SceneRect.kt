package com.capybara.intuition.game.presentation.ui.components.bottomPanel.model

import androidx.compose.ui.unit.Dp

data class SceneRect(
    val left: Dp,
    val top: Dp,
    val right: Dp,
    val bottom: Dp
) {

    val width: Dp
        get() = right - left

    val height: Dp
        get() = bottom - top
}