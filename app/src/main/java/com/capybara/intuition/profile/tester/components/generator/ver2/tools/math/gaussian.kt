package com.capybara.intuition.profile.tester.components.generator.ver2.tools.math

import kotlin.math.exp
import kotlin.math.pow

fun gaussian(
    x: Double,
    center: Double,
    sigma: Double,
): Double {

    return exp(
        -((x - center).pow(2)) /
                (2.0 * sigma * sigma)
    )
}