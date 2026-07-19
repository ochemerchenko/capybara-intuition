package com.capybara.intuition.profile.tester.components.generator.ver2.tools.calculators.sessions

import kotlin.random.Random

fun distributeSessionsByLevels (
    sessionCount: Int,
    minLevel: Int,
    maxLevel: Int
): List<Int> {
    if (sessionCount == 0) return emptyList()

    val levels = (minLevel..maxLevel).toList()

    val weights = levels.associateWith { level ->
        1.0 / level
    }

    val weightSum = weights.values.sum()

    val probabilities = weights.mapValues { (_, weight) ->
        weight / weightSum
    }

    val result = mutableListOf<Int>()

    repeat(sessionCount) {

        val random = Random.nextDouble()

        var cumulative = 0.0

        for (level in levels) {

            cumulative += probabilities.getValue(level)

            if (random <= cumulative) {
                result += level
                break
            }
        }
    }

    return result
}