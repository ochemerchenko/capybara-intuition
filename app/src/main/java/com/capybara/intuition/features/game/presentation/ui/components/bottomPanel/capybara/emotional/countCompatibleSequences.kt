package com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.capybara.emotional

fun countCompatibleSequences(
    wins: Int,
    loses: Int
): Double { // Повертаємо Long для абсолютної точності цілих чисел

    if (wins < 0 || loses < 0) {
        return 0.0
    }

    if (wins == 0 || loses == 0) {
        return 1.0
    }

    val total = wins + loses
    val effectiveK =
        // C(n,k) = C(n,n-k)
        minOf(wins, loses)

    var result = 1.0

    for (i in 1..effectiveK) {
        result = result * (total - i + 1.0) / i
    }

    return result
}