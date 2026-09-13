package com.capybara.intuition.features.tester.components.generator.model

import com.capybara.intuition.features.tester.model.GeneratedSession
import java.time.LocalDate

data class TimeLineDay (

    val date: LocalDate,
    val competence: Double = 0.0,
    val selfConfidence: Double = 0.0,

    var emotion: Double = 0.0,
    var physical: Double = 0.0,
    var intellect: Double = 0.0,

    var emotionalFactor: Double = 0.0,
    var physicalFactor: Double = 0.0,
    var intellectFactor: Double = 0.0,


    var motivationBoost: Double,
    var motivation: Double,

    var sessionCount: Int = 0,



    //var targetSessionCount: Int = 0,

    var emotionalEvent: Int = 0,

    //var actualSessionCount: Int = 0,
    var baseDecisionTime: Long = 0L,

    var sessions: List<GeneratedSession> = emptyList(),
)

