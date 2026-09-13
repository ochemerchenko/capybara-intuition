package com.capybara.intuition.features.tester.components.generator.calculators

import com.capybara.intuition.features.tester.components.generator.model.TimeLineDay
import com.capybara.intuition.features.tester.components.generator.math.gaussian

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