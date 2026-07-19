package com.capybara.intuition.profile.tester.components.generator.ver2.tools.calculators.biorhythm

import com.capybara.intuition.profile.tester.components.generator.ver2.TimeLineDay
import com.capybara.intuition.profile.tester.components.generator.ver2.tools.math.gaussian

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