package com.capybara.intuition.game.presentation.layout

import androidx.compose.ui.unit.Dp

fun calculateGameScreenLayout(
    screenWidth: Dp,
    screenHeight: Dp,
    topologyWidth: Int,
    topologyHeight: Int
): GameScreenLayout {

    val columns = topologyWidth.toFloat()
    val rows = topologyHeight.toFloat()

    val gameGridHeight =
        screenWidth * rows / columns

    val bottomPanelHeight =
        screenHeight - gameGridHeight

    return GameScreenLayout(
        gameGridHeight = gameGridHeight,
        bottomPanelHeight = bottomPanelHeight
    )
}