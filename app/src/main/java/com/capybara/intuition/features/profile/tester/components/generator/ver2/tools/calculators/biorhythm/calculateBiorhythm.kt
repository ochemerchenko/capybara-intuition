package com.capybara.intuition.features.profile.tester.components.generator.ver2.tools.calculators.biorhythm

import com.capybara.intuition.features.profile.tester.components.generator.ver2.TimeLineDay
import com.capybara.intuition.features.profile.tester.components.generator.ver2.tools.math.sinus01
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