package com.capybara.intuition.features.tester.components.generator.calculators

import com.capybara.intuition.features.tester.components.generator.model.TimeLineDay
import kotlin.random.Random

fun calculateDailyMotivation(
    timeLines: MutableList<TimeLineDay>,
    dailyMotivationDecay: Double,
    baseDayMotivation: Double,
    maxEmotionalDayInfluence: Double,
    maxPhysicalDayInfluence: Double,
): MutableList<TimeLineDay> {


    var motivation = baseDayMotivation

    timeLines.forEachIndexed { index, day ->

        // природне згасання мотивації
        motivation -= motivation * dailyMotivationDecay

        // зовнішні події
        motivation += day.motivationBoost

        // емоційний стан
        if (Random.nextDouble() > day.emotionalFactor) {
            motivation += maxEmotionalDayInfluence *
                    Random.nextDouble(-1.0, 1.0)
        }

        // фізичний стан
        if (Random.nextDouble() > day.physicalFactor) {
            motivation += maxPhysicalDayInfluence *
                    Random.nextDouble(-1.0, 1.0)
        }

        motivation = motivation.coerceIn(0.0, 1.0)

        timeLines[index] = day.copy(
            motivation = motivation
        )
    }

    return timeLines
}