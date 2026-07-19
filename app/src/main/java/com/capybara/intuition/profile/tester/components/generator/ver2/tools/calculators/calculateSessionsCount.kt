package com.capybara.intuition.profile.tester.components.generator.ver2.tools.calculators

import com.capybara.intuition.profile.tester.components.generator.ver2.TimeLineDay
import com.capybara.intuition.profile.tester.model.TrainingIntensity
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