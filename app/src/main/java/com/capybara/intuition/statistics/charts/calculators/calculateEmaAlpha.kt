package com.capybara.intuition.statistics.charts.calculators

fun calculateEmaAlpha(
    emaPeriod: Int
): Double {

    require(emaPeriod > 0)

    return 2.0 / (emaPeriod + 1.0)
}