package com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.capybara.emotional

import com.capybara.intuition.features.game.domain.model.GameSession
import kotlin.math.floor
import kotlin.math.log2

fun estimateSessionScore(
    session: GameSession
): Double {

    val currentStreak = maxOf(
        session.winStreak,
        session.loseStreak
    )

    // Порожня сесія
    if (currentStreak == 0) {
        return 0.0
    }

    val isWinStreak = session.winStreak >= session.loseStreak

    val probability = calculateSingleStreakSessionProbability(
        bets = currentStreak,
        level = session.profile.currentLevel,
        isWin = isWinStreak
    )

    return -log2(probability)
}


/*
Потребує побудови адекватної моделі. Треба переосмислити.

fun estimateSessionScore(
    session: GameSession
): //Int {
        Double {

    var estimatedSessionProbability = 0.0

    // Поточная сесія розбивається на <tail><border><lastBrokenStreak><currentStreak>

    // Визначаємо currentStreak
    val currentStreak = maxOf(
        session.winStreak,
        session.loseStreak

    )
    val isLastWin = session.isWinLastBet == true

    // Визначаємо довжину сесії
    val sessionLength = session.winCount + session.loseCount

    // Визначаємо модель сесії
    val model = when (sessionLength) {
        currentStreak -> "SINGLE_STREAK_SESSION"
        currentStreak + session.lastBrokenStreak -> "TWO_STREAKS_SESSION"
        currentStreak + session.lastBrokenStreak + 1 -> "BORDERED_TWO_STREAKS_SESSION"
        // Останній варіант - "повноцінна" довга сесія
        else -> "AGGREGATED_SESSION"
    }

    val singleStreakSessionProbability = calculateSingleStreakSessionProbability(
        bets = currentStreak,
        level = session.profile.currentLevel,
        isWin = isLastWin
    )

    if (model == "SINGLE_STREAK_SESSION") {
        estimatedSessionProbability = singleStreakSessionProbability
    }

    val twoStreaksSessionProbability =
        singleStreakSessionProbability *
                calculateSingleStreakSessionProbability(
                    bets = session.lastBrokenStreak,
                    level = session.profile.currentLevel,
                    isWin = !isLastWin
                )

    if (model == "TWO_STREAKS_SESSION") {
        estimatedSessionProbability = twoStreaksSessionProbability
    }

    val borderedTwoStreaksSessionProbability =
        twoStreaksSessionProbability *
                calculateSingleBetProbability(
                    level = session.profile.currentLevel,
                    isWin = isLastWin
                )

    if (model == "BORDERED_TWO_STREAKS_SESSION") {
        estimatedSessionProbability = borderedTwoStreaksSessionProbability
    }

    val prefixWins: Int
    val prefixLoses: Int
    if (isLastWin) {
        prefixWins = session.winCount - (1 + currentStreak)
        prefixLoses = session.loseCount - session.lastBrokenStreak
    } else {
        prefixWins = session.winCount - session.lastBrokenStreak
        prefixLoses = session.loseCount - (1 + currentStreak)
    }

    val aggregatedSessionProbability =
        borderedTwoStreaksSessionProbability *
                calculatePrefixProbability(
                    winsCount = prefixWins,
                    losesCount = prefixLoses,
                    level = session.profile.currentLevel
                )

    if (model == "AGGREGATED_SESSION") {
        estimatedSessionProbability = aggregatedSessionProbability
    }

    //if (estimatedSessionProbability <= 0.0) {
    //    return Int.MAX_VALUE
    //}

    return -log2(estimatedSessionProbability)

    //floor(
    //    -log2(estimatedSessionProbability)
    //).toInt()
}



 */