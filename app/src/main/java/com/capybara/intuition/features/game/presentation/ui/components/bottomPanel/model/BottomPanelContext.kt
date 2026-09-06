package com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.capybara.model.CapybaraSpriteMetadata

class BottomPanelContext (
    text: String,
    capybaraSprite: CapybaraSpriteMetadata,
    textStyle: TextStyle
) {


    var text by mutableStateOf<String>(text)
    var capybaraSprite by mutableStateOf(capybaraSprite)
    var textStyle by mutableStateOf<TextStyle>(textStyle)

    var actorRect by mutableStateOf<SceneRect?>(null)
    var speechAnchor by mutableStateOf<ScenePoint?>(null)
    var indicatorsRect by mutableStateOf<SceneRect?>(null)
    var bubbleAvailableArea by mutableStateOf<SceneRect?>(null)

    var occupiedWidthByText by mutableStateOf<Dp?>(null)
    var occupiedHeightByText by mutableStateOf<Dp?>(null)

    var textLayoutResult: TextLayoutResult? = null

    companion object {

        val BubbleLeftOffset = 8.dp
        val TextPaddingLeft = 8.dp
        val TextPaddingTop = 8.dp
        val TextPaddingRight = 8.dp
        val TextPaddingBottom = 8.dp
        val BubbleCornerRadius = 8.dp


        val TailBaseOffsetFromTop = 8.dp
        val TailBaseHeight = 16.dp
    }
}