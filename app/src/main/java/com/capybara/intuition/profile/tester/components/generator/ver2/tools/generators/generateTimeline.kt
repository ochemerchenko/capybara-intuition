package com.capybara.intuition.profile.tester.components.generator.ver2.tools.generators

import com.capybara.intuition.profile.tester.components.generator.ver2.TimeLineDay
import java.time.LocalDate

fun generateTimeline (
    periodDays: Int,
    competence: Double,
    selfConfidence: Double
): MutableList<TimeLineDay> {

    val today = LocalDate.now()
    val startDate = today.minusDays((periodDays - 1).toLong())

    return MutableList(periodDays) { index ->
        TimeLineDay(
            date = startDate.plusDays(index.toLong()),
            motivationBoost = 0.0,
            motivation = 0.0,
            competence = competence,
            selfConfidence = selfConfidence
        )
    }

}