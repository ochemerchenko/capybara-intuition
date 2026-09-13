package com.capybara.intuition.features.tester.components.generator.math

import kotlin.math.PI
import kotlin.math.sin

fun sinus01(
    index: Int,
    period: Int,
    phase: Double
): Double {

    return (
            1.0 + sin(2 * PI * index / period + phase)
    ) / 2.0

}