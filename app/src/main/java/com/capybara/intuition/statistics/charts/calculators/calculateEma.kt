package com.capybara.intuition.statistics.charts.calculators

fun calculateEma(
    values: List<Double>,
    period: Int
): List<Double> {

    if (values.isEmpty()) {
        return emptyList()
    }
    val result = mutableListOf<Double>()
    val multiplier = calculateEmaAlpha(period)
    var ema = values.first()
    result += ema

    for (i in 1 until values.size) {

        ema = (values[i] - ema) * multiplier + ema
        result += ema
    }

    return result
}
