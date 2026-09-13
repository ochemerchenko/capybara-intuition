package com.capybara.intuition.features.tester.components.generator.calculators

import com.capybara.intuition.features.tester.components.generator.model.TimeLineDay
import com.capybara.intuition.features.tester.components.generator.math.gaussian

fun calculateEmotionalFactor (
    timeLines: MutableList<TimeLineDay>,
    optimalEmotionRange: Double,
    emotionInfluencePeakPosition: Double
): MutableList<TimeLineDay> {

    timeLines.forEachIndexed { index, day ->
        timeLines[index] = day.copy(
            emotionalFactor = gaussian(
                x = day.emotion,
                center = emotionInfluencePeakPosition,
                sigma = optimalEmotionRange
            )
        )
    }

    return timeLines
}