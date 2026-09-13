package com.capybara.intuition.features.tester.components.generator.calculators

import com.capybara.intuition.features.tester.components.generator.model.TimeLineDay
import com.capybara.intuition.features.tester.components.generator.math.randomValues
import kotlin.math.roundToInt
import kotlin.random.Random

fun calculateMotivationBoost(
    timeLines: MutableList<TimeLineDay>,
    period: Int,
    boostDensity: Double,
    motivationBoostRange: ClosedFloatingPointRange<Double>
): MutableList<TimeLineDay> {

    val eventAmount = (period * boostDensity).roundToInt()

    val randomEventDays = randomValues(
        0 until timeLines.size,
        eventAmount
    )

    randomEventDays.forEach { index ->
        timeLines[index] = timeLines[index].copy(
            motivationBoost = Random.nextDouble(
                motivationBoostRange.start,
                motivationBoostRange.endInclusive
            )
        )
    }

    return timeLines
}