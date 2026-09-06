package com.capybara.intuition.features.profile.tester.components.generator.ver2.tools.calculators.biorhythm

import com.capybara.intuition.features.profile.tester.components.generator.ver2.TimeLineDay
import com.capybara.intuition.features.profile.tester.components.generator.ver2.tools.math.gaussian

fun calculateIntellectFactor (
    timeLines: MutableList<TimeLineDay>,
    optimalIntellectRange: Double,
    intellectInfluencePeakPosition: Double
): MutableList<TimeLineDay> {
    timeLines.forEachIndexed { index, day ->

        timeLines[index] = day.copy(
            intellectFactor = gaussian(
                x = day.intellect,
                center = intellectInfluencePeakPosition,
                sigma = optimalIntellectRange
            )
        )
    }

    return timeLines
}