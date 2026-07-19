package com.capybara.intuition.game.presentation.ui.components.bottomPanel.geometry

import android.util.Log
import androidx.compose.ui.unit.dp
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.model.BottomPanelContext
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.model.BottomPanelSceneBounds
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.model.SceneRect


private val ACTOR_TO_BUBBLE_SPACING = 8.dp
private val BUBBLE_TO_INDICATORS_SPACING = 8.dp


fun resolveBubbleAvailableArea(
    bubbleContext: BottomPanelContext,
    sceneBounds: BottomPanelSceneBounds,
) {
    Log.d(
        "BubbleDebug",
        "resolveBubbleAvailableArea called with bubbleContext = $bubbleContext"
    )

    val actorRect =
        bubbleContext.actorRect ?: return

    val indicatorsRect =
        bubbleContext.indicatorsRect ?: return

    Log.d(
        "BubbleDebug",
        "sceneBounds.width=${sceneBounds.width}"
    )

    Log.d(
        "BubbleDebug",
        "actorRect.left=${actorRect.left}"
    )

    Log.d(
        "BubbleDebug",
        "actorRect.right=${actorRect.right}"
    )

    Log.d(
        "BubbleDebug",
        "actorRect.width=${actorRect.width}"
    )

    Log.d(
        "BubbleDebug",
        "indicatorsRect.top=${indicatorsRect.top}"
    )

    bubbleContext.bubbleAvailableArea = SceneRect(
        left = actorRect.right + ACTOR_TO_BUBBLE_SPACING,
        top = 0.dp,
        right = sceneBounds.width,
        bottom = indicatorsRect.top - BUBBLE_TO_INDICATORS_SPACING
    )
}