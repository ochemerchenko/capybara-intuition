package com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.capybara.emotional

import kotlin.math.pow

fun calculatePrefixProbability (
    winsCount: Int,
    losesCount: Int,
    level: Int
): Double {

    val pWin = calculateSingleBetProbability(
        level = level,
        isWin = true
    )

    val pLose = calculateSingleBetProbability(
        level = level,
        isWin = false
    )

    return countCompatibleSequences(
        wins = winsCount,
        loses = losesCount
    ) * pWin.pow(winsCount) * pLose.pow(losesCount)

}