package com.capybara.intuition.game.presentation.ui.components.bottomPanel.capybara.model

import androidx.annotation.ArrayRes
import androidx.annotation.DrawableRes


data class CapybaraSpriteMetadata(
    @DrawableRes val drawableId: Int,
    val speechAnchor: SpeechAnchor,
    @ArrayRes val phrasesResId: Int
)

