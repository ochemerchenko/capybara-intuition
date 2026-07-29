package com.capybara.intuition.game.presentation.ui.components.bottomPanel.geometry

import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.model.BottomPanelContext


fun resolveBubbleOccupiedArea(
    bubbleContext: BottomPanelContext,
    textMeasurer: TextMeasurer,
    density: Density,
) {
    val area = bubbleContext.bubbleAvailableArea ?: return

    val maxWidthPx = with(density) {
        (
                area.width
                        - BottomPanelContext.BubbleLeftOffset
                        - BottomPanelContext.TextPaddingLeft
                        - BottomPanelContext.TextPaddingRight
                ).roundToPx()
    }

    val textLayoutResult = textMeasurer.measure(
        text = bubbleContext.text,
        style = bubbleContext.textStyle,
        constraints = Constraints.fixedWidth(maxWidthPx),
        softWrap = true,
        maxLines = Int.MAX_VALUE,
        overflow = TextOverflow.Clip
    )

    bubbleContext.textLayoutResult = textLayoutResult

    val bubbleBodyWidthPx =
        (0 until textLayoutResult.lineCount)
            .maxOf { lineIndex ->
                textLayoutResult.getLineRight(lineIndex) -
                        textLayoutResult.getLineLeft(lineIndex)
            }

    val occupiedWidth = with(density) {
        bubbleBodyWidthPx.toDp()
    }

    val occupiedHeight = with(density) {
        textLayoutResult.size.height.toDp()
    }

    bubbleContext.occupiedHeightByText = occupiedHeight

    bubbleContext.occupiedWidthByText = occupiedWidth + 2.dp
}