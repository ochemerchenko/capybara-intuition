package com.capybara.intuition.features.tester.components.generator.calculators

import com.capybara.intuition.features.tester.components.generator.model.TimeLineDay
import com.capybara.intuition.features.tester.components.generator.math.sinus01
import kotlin.math.PI
import kotlin.random.Random

fun calculateBiorhythm (
    timeLines: MutableList<TimeLineDay>,
    physicalPeriod: Int,
    emotionalPeriod: Int,
    intellectPeriod: Int
): MutableList<TimeLineDay> {

    val physicalPhase = 2 * PI * Random.Default.nextDouble()
    val emotionPhase = 2 * PI * Random.Default.nextDouble()
    val intellectPhase = 2 * PI * Random.Default.nextDouble()

    timeLines.forEachIndexed { index, _ ->
        timeLines[index].physical = sinus01(index, physicalPeriod, physicalPhase)
        timeLines[index].emotion = sinus01(index, emotionalPeriod, emotionPhase)
        timeLines[index].intellect = sinus01(index, intellectPeriod, intellectPhase)
    }

    return timeLines

}