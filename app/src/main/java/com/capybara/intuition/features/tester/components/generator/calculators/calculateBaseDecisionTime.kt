package com.capybara.intuition.features.tester.components.generator.calculators

import com.capybara.intuition.features.tester.components.generator.model.TimeLineDay
import kotlin.math.roundToLong
import kotlin.random.Random


fun calculateBaseDecisionTime(
    timeLines: MutableList<TimeLineDay>,
    baseDecisionTime: Double,
    maxEmotionalDecisionTimeInfluence: Double,
    maxIntellectDecisionTimeInfluence: Double,
): MutableList<TimeLineDay> {

    timeLines.forEachIndexed { index, day ->

        var decisionTime = baseDecisionTime

        // Емоційний стан
        if (Random.nextDouble() > day.emotionalFactor) {
            decisionTime +=
                100*Random.nextDouble(
                    0.0,
                    maxEmotionalDecisionTimeInfluence
                )
        }

        // Інтелектуальний стан
        if (Random.nextDouble() > day.intellectFactor) {
            decisionTime +=
                100*Random.nextDouble(
                    0.0,
                    maxIntellectDecisionTimeInfluence
                )
        }

        timeLines[index] = day.copy(
            baseDecisionTime = decisionTime.roundToLong()
        )
    }

    return timeLines
}
