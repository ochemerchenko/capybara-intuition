package com.capybara.intuition.features.profile.tester.components.generator.ver2.tools.generators

import com.capybara.intuition.features.game.domain.model.GameMode
import com.capybara.intuition.features.profile.tester.model.GeneratedRound
import kotlin.collections.plusAssign
import kotlin.random.Random

fun generateRounds (
    sessionId: Long,
    mode: GameMode,
    level: Int,
    sessionStartedAt: Long,
    roundCount: Int,
    competence: Double,
    selfConfidence: Double,
    intellectFactor: Double,
    baseDecisionTime: Long
): List<GeneratedRound>{

    val rounds = mutableListOf<GeneratedRound>()

    val pauseBetweenRound = 60_000L
    var roundStartedAt = sessionStartedAt

    repeat(roundCount) {

        val roundMode = when (mode) {
            GameMode.BEFORE -> "BEFORE"
            GameMode.AFTER -> "AFTER"
            GameMode.HYBRID -> if (Random.nextBoolean()) "BEFORE" else "AFTER"
        }

        // Початковий результат визначається компетентністю.
        var isWin = Random.nextDouble() < competence

        // Людина починає аналізувати свою відповідь.
        if (Random.nextDouble() < intellectFactor) {

            // Не змогла довіритися результату власного аналізу
            // і перейшла до випадкового вибору.
            if (Random.nextDouble() > selfConfidence) {
                isWin = Random.nextDouble() < 1.0/level
            }
        }

        val userSelectedAt = roundStartedAt + baseDecisionTime
        val roundFinishedAt = userSelectedAt + Random.nextLong(50, 200)

        rounds += GeneratedRound(
            sessionId = sessionId,
            mode = roundMode,
            startedAt = roundStartedAt,
            userSelectedAt = userSelectedAt,
            finishedAt = roundFinishedAt,
            result = if (isWin) "WIN" else "LOSE"
        )

        roundStartedAt = roundFinishedAt + pauseBetweenRound


    }

    return rounds

}