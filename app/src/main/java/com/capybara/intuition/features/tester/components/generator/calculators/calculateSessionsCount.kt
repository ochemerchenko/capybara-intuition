package com.capybara.intuition.features.tester.components.generator.calculators

import com.capybara.intuition.features.tester.components.generator.model.TimeLineDay
import com.capybara.intuition.features.tester.model.TrainingIntensity
import kotlin.math.roundToInt

fun calculateSessionsCount (
    timeLines: MutableList<TimeLineDay>,
    trainingIntensity: TrainingIntensity,
    motivationScale: Double
): MutableList<TimeLineDay> {

    timeLines.forEachIndexed { index, day ->

        day.sessionCount = (
            trainingIntensity.sessionsPerDay *
            day.motivation *
            motivationScale
        ).roundToInt()
    }

    return timeLines
}