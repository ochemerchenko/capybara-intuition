package com.capybara.intuition.game.presentation.ui.components.bottomPanel.capybara.emotional

fun calculateSingleBetProbability (
    level: Int,
    isWin: Boolean
): Double {

    return if (isWin) {
        1.0 / level
    } else {
        1.0 - 1.0 / level
    }

}