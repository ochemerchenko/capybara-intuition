package com.capybara.intuition.profile.tester.components.generator.ver2.tools.generators

import com.capybara.intuition.game.domain.model.GameMode
import com.capybara.intuition.profile.tester.components.generator.ver2.TimeLineDay
import com.capybara.intuition.profile.tester.components.generator.ver2.tools.calculators.rounds.calculateRoundsCount
import com.capybara.intuition.profile.tester.components.generator.ver2.tools.calculators.sessions.distributeSessionsByLevels
import com.capybara.intuition.profile.tester.model.GeneratedSession
import java.time.ZoneId


fun generateSessions(
    lastSessionId: Long,
    timeLines: MutableList<TimeLineDay>,
    minLevel: Int,
    maxLevel: Int,
    competence: Double,
    selfConfidence: Double,
    mentalEndurance: Double,
    baseFatigueGrowth: Double

): MutableList<TimeLineDay> {

    val pauseBetweenSession = 60_000L

    var sessionId = lastSessionId

    timeLines.forEach { day ->

        var sessionStartedAt =
            day.date
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant()
                .toEpochMilli()

        // Розподіл усіх сесій цього дня по рівнях
        val sessionLevels = distributeSessionsByLevels(
            sessionCount = day.sessionCount,
            minLevel = minLevel,
            maxLevel = maxLevel
        )

        day.sessions = sessionLevels.map { level ->

            val sessionMode = GameMode.entries.random()

            val roundCount = calculateRoundsCount(
                physicalState = day.physical,
                mentalEndurance = mentalEndurance,
                baseFatigueGrowth = baseFatigueGrowth
            )

            sessionId++

            val rounds = generateRounds(
                sessionId = sessionId,
                mode = sessionMode,
                level = level,
                sessionStartedAt = sessionStartedAt,
                roundCount = roundCount,
                competence = competence,
                intellectFactor = day.intellectFactor,
                selfConfidence = selfConfidence,
                baseDecisionTime = day.baseDecisionTime
            )

            val sessionFinishedAt = rounds.last().finishedAt

            val session = GeneratedSession(
                id = sessionId,
                level = level,
                mode = sessionMode.name,
                startedAt = sessionStartedAt,
                finishedAt = sessionFinishedAt,
                roundCount = roundCount,
                rounds = rounds
            )

            sessionStartedAt = sessionFinishedAt + pauseBetweenSession

            session
        }
    }

    return timeLines
}