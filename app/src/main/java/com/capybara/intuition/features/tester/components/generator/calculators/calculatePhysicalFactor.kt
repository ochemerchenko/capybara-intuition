package com.capybara.intuition.features.tester.components.generator.calculators

import com.capybara.intuition.features.tester.components.generator.model.TimeLineDay
import com.capybara.intuition.features.tester.components.generator.math.gaussian

fun calculatePhysicalFactor (
    timeLines: MutableList<TimeLineDay>,
    optimalPhysicalRange: Double,
    physicalInfluencePeakPosition: Double
): MutableList<TimeLineDay> {
    timeLines.forEachIndexed { index, day ->

        timeLines[index] = day.copy(
            physicalFactor = gaussian(
                x = day.physical,
                center = physicalInfluencePeakPosition,
                sigma = optimalPhysicalRange
            )
        )
    }

    return timeLines
}