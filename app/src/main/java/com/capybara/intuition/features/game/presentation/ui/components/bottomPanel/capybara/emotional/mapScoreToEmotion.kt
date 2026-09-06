package com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.capybara.emotional

import android.util.Log
import com.capybara.intuition.features.game.domain.model.GameSession
import com.capybara.intuition.features.game.domain.model.RoundResult
import com.capybara.intuition.features.game.domain.round.GameRound


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
                        sessionScore < 4 -> CapybaraEmotion.LOSE_STREAK_BROKEN_INTERESTING
                        sessionScore < 6 -> CapybaraEmotion.LOSE_STREAK_BROKEN_UNUSUAL
                        sessionScore < 8 -> CapybaraEmotion.LOSE_STREAK_BROKEN_RARE
                        //sessionScore < 10 -> CapybaraEmotion.LOSE_STREAK_BROKEN_AMAZING
                        //sessionScore < 12 -> CapybaraEmotion.LOSE_STREAK_BROKEN_INCREDIBLE
                        else -> CapybaraEmotion.LOSE_STREAK_BROKEN_LEGENDARY
                    }
                } else {
                    when {
                        sessionScore < 2 -> CapybaraEmotion.WIN_CONTINUES_ORDINARY
                        sessionScore < 4 -> CapybaraEmotion.WIN_CONTINUES_INTERESTING
                        sessionScore < 6 -> CapybaraEmotion.WIN_CONTINUES_UNUSUAL
                        sessionScore < 8 -> CapybaraEmotion.WIN_CONTINUES_RARE
                        //sessionScore < 10 -> CapybaraEmotion.WIN_CONTINUES_AMAZING
                        //sessionScore < 12 -> CapybaraEmotion.WIN_CONTINUES_INCREDIBLE
                        else -> CapybaraEmotion.WIN_CONTINUES_LEGENDARY
                    }
                }
            RoundResult.LOSE ->
                    if (isStreakBroken) {
                        when {
                            sessionScore < 2 -> CapybaraEmotion.WIN_STREAK_BROKEN_ORDINARY
                            sessionScore < 4 -> CapybaraEmotion.WIN_STREAK_BROKEN_INTERESTING
                            sessionScore < 6 -> CapybaraEmotion.WIN_STREAK_BROKEN_UNUSUAL
                            sessionScore < 8 -> CapybaraEmotion.WIN_STREAK_BROKEN_RARE
                            //sessionScore < 10 -> CapybaraEmotion.WIN_STREAK_BROKEN_AMAZING
                            //sessionScore < 12 -> CapybaraEmotion.WIN_STREAK_BROKEN_INCREDIBLE
                            else -> CapybaraEmotion.WIN_STREAK_BROKEN_LEGENDARY
                        }
                    } else {
                        when {
                            sessionScore < 2 -> CapybaraEmotion.LOSE_CONTINUES_ORDINARY
                            sessionScore < 4 -> CapybaraEmotion.LOSE_CONTINUES_INTERESTING
                            sessionScore < 6 -> CapybaraEmotion.LOSE_CONTINUES_UNUSUAL
                            sessionScore < 8 -> CapybaraEmotion.LOSE_CONTINUES_RARE
                            //sessionScore < 10 -> CapybaraEmotion.LOSE_CONTINUES_AMAZING
                            //sessionScore < 12 -> CapybaraEmotion.LOSE_CONTINUES_INCREDIBLE
                            else -> CapybaraEmotion.LOSE_CONTINUES_LEGENDARY
                        }
                    }
            else -> {
                when (session.isWinLastBet) {
                    true -> {
                        when {
                            sessionScore < 2 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_ORDINARY
                            sessionScore < 4 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_INTERESTING
                            sessionScore < 6 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_UNUSUAL
                            sessionScore < 8 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_RARE
                            //sessionScore < 10 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_AMAZING
                            //sessionScore < 12 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_INCREDIBLE
                            else -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_LEGENDARY
                        }
                    }
                    false -> {
                        when {
                            sessionScore < 2 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_ORDINARY
                            sessionScore < 4 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_INTERESTING
                            sessionScore < 6 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_UNUSUAL
                            sessionScore < 8 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_RARE
                            //sessionScore < 10 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_AMAZING
                            //sessionScore < 12 -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_INCREDIBLE
                            else -> CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_LEGENDARY
                        }
                    }
                    else -> CapybaraEmotion.START

                }
            }
        }
    Log.d("CapybaraEmotion", "$emotion, sessionScore = $sessionScore, result = ${round.result}")
    return emotion
}