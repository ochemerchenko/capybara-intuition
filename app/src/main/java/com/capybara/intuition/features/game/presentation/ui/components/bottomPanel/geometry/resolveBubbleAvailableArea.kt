package com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.geometry

import androidx.compose.ui.unit.dp
import com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.model.BottomPanelContext
import com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.model.BottomPanelSceneBounds
import com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.model.SceneRect


private val ACTOR_TO_BUBBLE_SPACING = 0.dp
private val BUBBLE_TO_INDICATORS_SPACING = 8.dp


fun resolveBubbleAvailableArea(
    bubbleContext: BottomPanelContext,
    sceneBounds: BottomPanelSceneBounds,
) {

    val actorRect =
        bubbleContext.actorRect ?: return

    val indicatorsRect =
        bubbleContext.indicatorsRect ?: return



    bubbleContext.bubbleAvailableArea = SceneRect(
        left = actorRect.right + ACTOR_TO_BUBBLE_SPACING,
        top = 0.dp,
        right = sceneBounds.width,
        bottom = indicatorsRect.top - BUBBLE_TO_INDICATORS_SPACING
    )
}