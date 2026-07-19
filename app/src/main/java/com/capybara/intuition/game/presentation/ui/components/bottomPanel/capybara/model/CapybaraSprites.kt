package com.capybara.intuition.game.presentation.ui.components.bottomPanel.capybara.model

import com.capybara.intuition.R
import com.capybara.intuition.game.domain.model.GameMode
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.capybara.emotional.CapybaraEmotion

fun capybaraSprites(
    emotion: CapybaraEmotion,
    mode: GameMode
): CapybaraSpriteMetadata {

    val sprite = when (emotion) {

        ////////////////////////////////////////////////
        //  OPTIONS_SHOWN_START
        ////////////////////////////////////////////////
        CapybaraEmotion.START ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_options_shown_start, // DEFAULT !
                speechAnchor = SpeechAnchor(
                    xFraction = 0.78f,
                    yFraction = 0.08f
                ),
                phrasesResId = when (mode) {
                    GameMode.AFTER -> R.array.capybara_emotion_options_shown_start_mode_after
                    GameMode.BEFORE -> R.array.capybara_emotion_options_shown_start_mode_before
                    GameMode.HYBRID -> R.array.capybara_emotion_options_shown_start_mode_hybrid
                }
            )


        ////////////////////////////////////////////////
        //  OPTIONS_SHOWN_AFTER_WIN
        ////////////////////////////////////////////////
        CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_ORDINARY ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_options_shown_after_win_ordinary,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.72f,
                    yFraction = 0.06f
                ),
                phrasesResId = R.array.capybara_emotion_options_shown_after_win_ordinary
            )
        CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_INTERESTING ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_options_shown_after_win_interesting,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.73f,
                    yFraction = 0.09f
                ),
                phrasesResId = R.array.capybara_emotion_options_shown_after_win_interesting
            )
        CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_UNUSUAL ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_options_shown_after_win_unusual,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.71f,
                    yFraction = 0.07f
                ),
                phrasesResId = R.array.capybara_emotion_options_shown_after_win_unusual
            )
        CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_RARE ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_options_shown_after_win_rare,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.81f,
                    yFraction = 0.07f
                ),
                phrasesResId = R.array.capybara_emotion_options_shown_after_win_rare
            )
        CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_AMAZING ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_options_shown_after_win_amazing,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.72f,
                    yFraction = 0.08f
                ),
                phrasesResId = R.array.capybara_emotion_options_shown_after_win_amazing
            )
        CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_INCREDIBLE ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_options_shown_after_win_incredible,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.74f,
                    yFraction = 0.11f
                ),
                phrasesResId = R.array.capybara_emotion_options_shown_after_win_incredible
            )
        CapybaraEmotion.OPTIONS_SHOWN_AFTER_WIN_LEGENDARY ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_options_shown_after_win_legendary,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.75f,
                    yFraction = 0.12f
                ),
                phrasesResId = R.array.capybara_emotion_options_shown_after_win_legendary
            )


        ////////////////////////////////////////////////
        //  OPTIONS_SHOWN_AFTER_LOSE
        ////////////////////////////////////////////////
        CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_ORDINARY ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_options_shown_after_lose_ordinary,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.78f,
                    yFraction = 0.10f
                ),
                phrasesResId = R.array.capybara_emotion_options_shown_after_lose_ordinary
            )
        CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_INTERESTING ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_options_shown_after_lose_interesting,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.76f,
                    yFraction = 0.08f
                ),
                phrasesResId = R.array.capybara_emotion_options_shown_after_lose_interesting
            )
        CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_UNUSUAL ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_options_shown_after_lose_unusual,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.74f,
                    yFraction = 0.07f
                ),
                phrasesResId = R.array.capybara_emotion_options_shown_after_lose_unusual
            )
        CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_RARE ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_options_shown_after_lose_rare,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.71f,
                    yFraction = 0.07f
                ),
                phrasesResId = R.array.capybara_emotion_options_shown_after_lose_rare
            )
        CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_AMAZING ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_options_shown_after_lose_amazing,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.70f,
                    yFraction = 0.07f
                ),
                phrasesResId = R.array.capybara_emotion_options_shown_after_lose_amazing
            )
        CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_INCREDIBLE ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_options_shown_after_lose_incredible,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.81f,
                    yFraction = 0.10f
                ),
                phrasesResId = R.array.capybara_emotion_options_shown_after_lose_incredible
            )
        CapybaraEmotion.OPTIONS_SHOWN_AFTER_LOSE_LEGENDARY ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_options_shown_after_lose_legendary,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.81f,
                    yFraction = 0.17f
                ),
                phrasesResId = R.array.capybara_emotion_options_shown_after_lose_legendary
            )


        ////////////////////////////////////////////////
        //  WIN_CONTINUES
        ////////////////////////////////////////////////
        CapybaraEmotion.WIN_CONTINUES_ORDINARY ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_win_continues_ordinary,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.78f,
                    yFraction = 0.17f
                ),
                phrasesResId = R.array.capybara_emotion_win_continues_ordinary
            )
        CapybaraEmotion.WIN_CONTINUES_INTERESTING ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_win_continues_interesting,
                //drawableId = R.drawable.capybara_happy,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.78f,
                    yFraction = 0.17f
                ),
                phrasesResId = R.array.capybara_emotion_win_continues_interesting
            )
        CapybaraEmotion.WIN_CONTINUES_UNUSUAL ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_win_continues_unusual,
                //drawableId = R.drawable.capybara_happy,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.78f,
                    yFraction = 0.15f
                ),
                phrasesResId = R.array.capybara_emotion_win_continues_unusual
            )
        CapybaraEmotion.WIN_CONTINUES_RARE ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_win_continues_rare,
                //drawableId = R.drawable.capybara_happy,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.81f,
                    yFraction = 0.15f
                ),
                phrasesResId = R.array.capybara_emotion_win_continues_rare
            )
        CapybaraEmotion.WIN_CONTINUES_AMAZING ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_win_continues_amazing,
                //drawableId = R.drawable.capybara_happy,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.78f,
                    yFraction = 0.16f
                ),
                phrasesResId = R.array.capybara_emotion_win_continues_amazing
            )
        CapybaraEmotion.WIN_CONTINUES_INCREDIBLE ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_win_continues_incredible,
                //drawableId = R.drawable.capybara_happy,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.79f,
                    yFraction = 0.16f
                ),
                phrasesResId = R.array.capybara_emotion_win_continues_incredible
            )
        CapybaraEmotion.WIN_CONTINUES_LEGENDARY ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_win_continues_legendary,
                //drawableId = R.drawable.capybara_happy,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.83f,
                    yFraction = 0.14f
                ),
                phrasesResId = R.array.capybara_emotion_win_continues_legendary
            )



        ////////////////////////////////////////////////
        //  WIN_STREAK_BROKEN
        ////////////////////////////////////////////////

        CapybaraEmotion.WIN_STREAK_BROKEN_ORDINARY ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_win_streak_broken_ordinary,
                //drawableId = R.drawable.capybara_sad,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.81f,
                    yFraction = 0.16f
                ),
                phrasesResId = R.array.capybara_emotion_win_streak_broken_ordinary
            )
        CapybaraEmotion.WIN_STREAK_BROKEN_INTERESTING ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_win_streak_broken_interesting,
                //drawableId = R.drawable.capybara_sad,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.81f,
                    yFraction = 0.15f
                ),
                phrasesResId = R.array.capybara_emotion_win_streak_broken_interesting
            )
        CapybaraEmotion.WIN_STREAK_BROKEN_UNUSUAL ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_win_streak_broken_unusual,
                //drawableId = R.drawable.capybara_sad,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.83f,
                    yFraction = 0.14f
                ),
                phrasesResId = R.array.capybara_emotion_win_streak_broken_unusual
            )
        CapybaraEmotion.WIN_STREAK_BROKEN_RARE ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_win_streak_broken_rare,
                //drawableId = R.drawable.capybara_sad,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.69f,
                    yFraction = 0.07f
                ),
                phrasesResId = R.array.capybara_emotion_win_streak_broken_rare
            )
        CapybaraEmotion.WIN_STREAK_BROKEN_AMAZING ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_win_streak_broken_amazing,
                //drawableId = R.drawable.capybara_sad,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.78f,
                    yFraction = 0.13f
                ),
                phrasesResId = R.array.capybara_emotion_win_streak_broken_amazing
            )
        CapybaraEmotion.WIN_STREAK_BROKEN_INCREDIBLE ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_win_streak_broken_incredible,
                //drawableId = R.drawable.capybara_sad,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.81f,
                    yFraction = 0.13f
                ),
                phrasesResId = R.array.capybara_emotion_win_streak_broken_incredible
            )
        CapybaraEmotion.WIN_STREAK_BROKEN_LEGENDARY ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_win_streak_broken_legendary,
                //drawableId = R.drawable.capybara_sad,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.78f,
                    yFraction = 0.17f
                ),
                phrasesResId = R.array.capybara_emotion_win_streak_broken_legendary
            )



        ////////////////////////////////////////////////
        //  LOSE_CONTINUE
        ////////////////////////////////////////////////
        CapybaraEmotion.LOSE_CONTINUES_ORDINARY ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_lose_continues_ordinary,
                //drawableId = R.drawable.capybara_sad,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.81f,
                    yFraction = 0.13f
                ),
                phrasesResId = R.array.capybara_emotion_lose_continues_ordinary
            )
        CapybaraEmotion.LOSE_CONTINUES_INTERESTING ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_lose_continues_interesting,
                //drawableId = R.drawable.capybara_sad,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.81f,
                    yFraction = 0.15f
                ),
                phrasesResId = R.array.capybara_emotion_lose_continues_interesting
            )
        CapybaraEmotion.LOSE_CONTINUES_UNUSUAL ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_lose_continues_unusual,
                //drawableId = R.drawable.capybara_sad,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.81f,
                    yFraction = 0.15f
                ),
                phrasesResId = R.array.capybara_emotion_lose_continues_unusual
            )
        CapybaraEmotion.LOSE_CONTINUES_RARE ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_lose_continues_rare,
                //drawableId = R.drawable.capybara_sad,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.81f,
                    yFraction = 0.15f
                ),
                phrasesResId = R.array.capybara_emotion_lose_continues_rare
            )
        CapybaraEmotion.LOSE_CONTINUES_AMAZING ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_lose_continues_amazing,
                //drawableId = R.drawable.capybara_sad,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.78f,
                    yFraction = 0.16f
                ),
                phrasesResId = R.array.capybara_emotion_lose_continues_amazing
            )
        CapybaraEmotion.LOSE_CONTINUES_INCREDIBLE ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_lose_continues_incredible,
                //drawableId = R.drawable.capybara_sad,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.78f,
                    yFraction = 0.17f
                ),
                phrasesResId = R.array.capybara_emotion_lose_continues_incredible
            )
        CapybaraEmotion.LOSE_CONTINUES_LEGENDARY ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_lose_continues_legendary,
                //drawableId = R.drawable.capybara_sad,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.78f,
                    yFraction = 0.15f
                ),
                phrasesResId = R.array.capybara_emotion_lose_continues_legendary
            )


        ////////////////////////////////////////////////
        //  LOSE_STREAK_BROKEN
        ////////////////////////////////////////////////
        CapybaraEmotion.LOSE_STREAK_BROKEN_ORDINARY ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_lose_streak_broken_ordinary,
                //drawableId = R.drawable.capybara_happy,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.75f,
                    yFraction = 0.13f
                ),
                phrasesResId = R.array.capybara_emotion_lose_streak_broken_ordinary
            )
        CapybaraEmotion.LOSE_STREAK_BROKEN_INTERESTING ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_lose_streak_broken_interesting,
                //drawableId = R.drawable.capybara_happy,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.78f,
                    yFraction = 0.13f
                ),
                phrasesResId = R.array.capybara_emotion_lose_streak_broken_interesting
            )
        CapybaraEmotion.LOSE_STREAK_BROKEN_UNUSUAL ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_lose_streak_broken_unusual,
                //drawableId = R.drawable.capybara_happy,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.81f,
                    yFraction = 0.13f
                ),
                phrasesResId = R.array.capybara_emotion_lose_streak_broken_unusual
            )
        CapybaraEmotion.LOSE_STREAK_BROKEN_RARE ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_lose_streak_broken_rare,
                //drawableId = R.drawable.capybara_happy,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.83f,
                    yFraction = 0.13f
                ),
                phrasesResId = R.array.capybara_emotion_lose_streak_broken_rare
            )
        CapybaraEmotion.LOSE_STREAK_BROKEN_AMAZING ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_lose_streak_broken_amazing,
                //drawableId = R.drawable.capybara_happy,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.78f,
                    yFraction = 0.17f
                ),
                phrasesResId = R.array.capybara_emotion_lose_streak_broken_amazing
            )
        CapybaraEmotion.LOSE_STREAK_BROKEN_INCREDIBLE ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_lose_streak_broken_incredible,
                //drawableId = R.drawable.capybara_happy,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.89f,
                    yFraction = 0.09f
                ),
                phrasesResId = R.array.capybara_emotion_lose_streak_broken_incredible
            )
        CapybaraEmotion.LOSE_STREAK_BROKEN_LEGENDARY ->
            CapybaraSpriteMetadata(
                drawableId = R.drawable.capybara_emotion_lose_streak_broken_legendary,
                //drawableId = R.drawable.capybara_happy,
                speechAnchor = SpeechAnchor(
                    xFraction = 0.87f,
                    yFraction = 0.11f
                ),
                phrasesResId = R.array.capybara_emotion_lose_streak_broken_legendary
            )

    }

    return sprite

}