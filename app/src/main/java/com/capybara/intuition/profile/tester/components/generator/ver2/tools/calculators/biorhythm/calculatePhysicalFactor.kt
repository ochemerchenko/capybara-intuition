package com.capybara.intuition.profile.tester.components.generator.ver2.tools.calculators.biorhythm

import com.capybara.intuition.profile.tester.components.generator.ver2.TimeLineDay
import com.capybara.intuition.profile.tester.components.generator.ver2.tools.math.gaussian

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