package com.capybara.intuition.features.statistics.charts.calculators

import kotlin.math.ceil
import kotlin.math.ln

fun calculateRequiredHistoryDays(
    analyticPeriod: Int,
    emaPeriod: Int,
    epsilon: Float
): Int {

    require(analyticPeriod > 0)
    require(emaPeriod > 0)
    require(epsilon > 0f && epsilon < 1f)

    val alpha = calculateEmaAlpha(emaPeriod)
    val decay = 1.0 - alpha

    val warmupDays = ceil(
        ln(epsilon.toDouble()) / ln(decay)
    ).toInt()

    return analyticPeriod + warmupDays
}