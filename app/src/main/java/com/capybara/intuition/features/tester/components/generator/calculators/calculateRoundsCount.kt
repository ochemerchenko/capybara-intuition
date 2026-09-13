package com.capybara.intuition.features.tester.components.generator.calculators

import kotlin.math.exp
import kotlin.random.Random

fun calculateRoundsCount (
    physicalState: Double,
    mentalEndurance: Double,
    baseFatigueGrowth: Double
): Int {
    var fatigue = 0.0
    var rounds = 0

    while (rounds < 108) {
        rounds++
        fatigue += baseFatigueGrowth / (1 + physicalState)
        if (fatigue < 0.5) {
            continue
        }
        val continueProbability =
            exp(
                -(fatigue - 0.5) / mentalEndurance
            )
        if (Random.nextDouble() > continueProbability) {
            break
        }
    }

    return rounds
}