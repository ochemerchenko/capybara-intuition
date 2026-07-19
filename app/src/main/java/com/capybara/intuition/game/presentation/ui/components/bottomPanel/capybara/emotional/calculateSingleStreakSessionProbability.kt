package com.capybara.intuition.game.presentation.ui.components.bottomPanel.capybara.emotional

import kotlin.math.pow

fun calculateSingleStreakSessionProbability(
    bets: Int,
    level: Int,
    isWin: Boolean
): Double {
    val probability = calculateSingleBetProbability(
        level = level,
        isWin = isWin
    )
    return probability.pow(bets)
}