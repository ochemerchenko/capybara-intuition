package com.capybara.intuition.game.presentation.ui.components.bottomPanel.capybara.emotional

import com.capybara.intuition.game.domain.model.GameSession
import com.capybara.intuition.game.domain.model.RoundResult
import com.capybara.intuition.game.domain.round.GameRound


fun mapScoreAndEventToEmotion(
    session: GameSession,
    round: GameRound
): CapybaraEmotion {

    val sessionScore = estimateSessionScore(session)

    val isStreakBroken =
        session.lastBrokenStreak > 0 &&
                (
                        session.winStreak == 1 ||
                                session.loseStreak == 1
                        )

    val emotion: CapybaraEmotion =
        when ( round.result ) {
            RoundResult.WIN ->
                if (isStreakBroken) {
                    when {
                        sessionScore < 2 -> CapybaraEmotion.LOSE_STREAK_BROKEN_ORDINARY
                        sessionScore < 3 -> CapybaraEmotion.LOSE_STREAK_BROKEN_INTERESTING
                        sessionScore < 4 -> CapybaraEmotion.LOSE_STREAK_BROKEN_UNUSUAL
                        sessionScore < 5 -> CapybaraEmotion.LOSE_STREAK_BROKEN_RARE
                        sessionScore < 6 -> CapybaraEmotion.LOSE_STREAK_BROKEN_AMAZING
                        sessionScore < 7 -> CapybaraEmotion.LOSE_STREAK_BROKEN_INCREDIBLE
                        else -> CapybaraEmotion.LOSE_STREAK_BROKEN_LEGENDARY
                    }
                } else {
                    when {
                        sessionScore < 2 -> CapybaraEmotion.WIN_CONTINUES_ORDINARY
                        sessionScore < 3 -> CapybaraEmotion.WIN_CONTINUES_INTERESTING
                        sessionScore < 4 -> CapybaraEmotion.WIN_CONTINUES_UNUSUAL
                        sessionScore < 5 -> CapybaraEmotion.WIN_CONTINUES_RARE
                        sessionScore < 6 -> CapybaraEmotion.WIN_CONTINUES_AMAZING
                        sessionScore < 7 -> CapybaraEmotion.WIN_CONTINUES_INCREDIBLE
                        else -> CapybaraEmotion.WIN_CONTINUES_LEGENDARY
                    }
                }
            RoundResult.LOSE ->
                    if (isStreakBroken) {
                        when {
                            sessionScore < 2 -> CapybaraEmotion.WIN_STREAK_BROKEN_ORDINARY
                            sessionScore < 3 -> CapybaraEmotion.WIN_STREAK_BROKEN_INTERESTING
                            sessionScore < 4 -> CapybaraEmotion.WIN_STREAK_BROKEN_UNUSUAL
                            sessionScore < 5 -> CapybaraEmotion.WIN_STREAK_BROKEN_RARE
                            sessionScore < 6 -> CapybaraEmotion.WIN_STREAK_BROKEN_AMAZING
                            sessionScore < 7 -> CapybaraEmotion.WIN_STREAK_BROKEN_INCREDIBLE
                            else -> CapybaraEmotion.WIN_STREAK_BROKEN_LEGENDARY
                        }
                    } else {
                        when {
                            sessionScore < 2 -> CapybaraEmotion.LOSE_CONTINUES_ORDINARY
                            sessionScore < 3 -> CapybaraEmotion.LOSE_CONTINUES_INTERESTING
                            sessionScore < 4 -> CapybaraEmotion.LOSE_CONTINUES_UNUSUAL
                            sessionScore < 5 -> CapybaraEmotion.LOSE_CONTINUES_RARE
                            sessionScore < 6 -> CapybaraEmotion.LOSE_CONTINUES_AMAZING
                            sessionScore < 7 -> CapybaraEmotion.LOSE_CONTINUES_INCREDIBLE
                            else -> CapybaraEmotion.LOSE_CONTINUES_LEGENDARY
                        }
                    }
            else -> {
                when (session.isWinLastBet) {
                    true -> {
                        when {
                            sessionScore < 2 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_ORDINARY
                            sessionScore < 3 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_INTERESTING
                            sessionScore < 4 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_UNUSUAL
                            sessionScore < 5 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_RARE
                            sessionScore < 6 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_AMAZING
                            sessionScore < 7 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_INCREDIBLE
                            else -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_LEGENDARY
                        }
                    }
                    false -> {
                        when {
                            sessionScore < 2 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_ORDINARY
                            sessionScore < 3 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_INTERESTING
                            sessionScore < 4 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_UNUSUAL
                            sessionScore < 5 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_RARE
                            sessionScore < 6 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_AMAZING
                            sessionScore < 7 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_INCREDIBLE
                            else -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_LEGENDARY
                        }
                    }
                    else -> CapybaraEmotion.START

                }
            }
        }

    return emotion
}