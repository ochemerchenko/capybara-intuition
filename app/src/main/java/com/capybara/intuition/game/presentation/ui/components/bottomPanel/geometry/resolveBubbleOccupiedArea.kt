package com.capybara.intuition.game.presentation.ui.components.bottomPanel.geometry

import android.util.Log
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Density
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.model.BottomPanelContext


fun resolveBubbleOccupiedArea(
    bubbleContext: BottomPanelContext,
    textMeasurer: TextMeasurer,
    density: Density,
) {
    Log.d(
        "BubbleDebug",
        "resolveBubbleOccupiedArea called with text='${bubbleContext.text}'"
    )

    val area = bubbleContext.bubbleAvailableArea ?: return

    Log.d(
        "BubbleDebug",
        "availableArea.width=${area.width}"
    )

    val maxWidthPx = with(density) {
        (
                area.width
                        - BottomPanelContext.BubbleLeftOffset
                        - BottomPanelContext.TextPaddingLeft
                        - BottomPanelContext.TextPaddingRight
                ).roundToPx()
    }

    val textMaxWidth =
        area.width
    - BottomPanelContext.BubbleLeftOffset
    - BottomPanelContext.TextPaddingLeft
    - BottomPanelContext.TextPaddingRight

    Log.d(
        "BubbleDebug",
        "textMaxWidth=$textMaxWidth"
    )

    val textLayoutResult = textMeasurer.measure(
        text = AnnotatedString(bubbleContext.text),
        style = bubbleContext.textStyle,
        constraints = Constraints(
            maxWidth = maxWidthPx
        )
    )

    Log.d(
        "BubbleDebug",
        "lineCount=${textLayoutResult.lineCount}"
    )

    Log.d(
        "BubbleDebug",
        "layoutWidth=${with(density) { textLayoutResult.size.width.toDp() }}"
    )

    Log.d(
        "BubbleDebug",
        "layoutHeight=${with(density) { textLayoutResult.size.height.toDp() }}"
    )

    Log.d(
        "BubbleDebug",
        "layoutWidthPx=${textLayoutResult.size.width}"
    )

    Log.d(
        "BubbleDebug",
        "layoutHeightPx=${textLayoutResult.size.height}"
    )

    for (i in 0 until textLayoutResult.lineCount) {

        val width = with(density) {
            (
                    textLayoutResult.getLineRight(i)
                            - textLayoutResult.getLineLeft(i)
                    ).toDp()
        }

        Log.d(
            "BubbleDebug",
            "line[$i].width=$width"
        )
    }

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

    Log.d(
        "BubbleDebug",
        "occupiedWidthByText=$occupiedWidth"
    )

    Log.d(
        "BubbleDebug",
        "occupiedHeightByText=$occupiedHeight"
    )

    Log.d(
        "BubbleDebug",
        "futureBubbleWidth=${
            occupiedWidth +
                    BottomPanelContext.TextPaddingLeft +
                    BottomPanelContext.TextPaddingRight
        }"
    )

    Log.d(
        "BubbleDebug",
        "futureBubbleBodyHeight=$occupiedHeight"
    )

    Log.d(
        "BubbleDebug",
        "futureBubbleHeight=${
            occupiedHeight +
                    BottomPanelContext.TextPaddingTop +
                    BottomPanelContext.TextPaddingBottom
        }"
    )

    bubbleContext.occupiedHeightByText = occupiedHeight

    bubbleContext.occupiedWidthByText = with(density) {
        textLayoutResult.size.width.toDp()
    }

    Log.d(
        "BubbleDebug",
        "storedWidth=${bubbleContext.occupiedWidthByText}"
    )

    Log.d(
        "BubbleDebug",
        "storedHeight=${bubbleContext.occupiedHeightByText}"
    )
}

/*

fun resolveBubbleOccupiedArea(
    bubbleContext: BottomPanelContext,
    textMeasurer: TextMeasurer,
    density: Density,
) {
    Log.d(
        "BubbleDebug",
        "resolveBubbleOccupiedArea called with text='${bubbleContext.text}'"
    )

    val area = bubbleContext.bubbleAvailableArea ?: return

    Log.d(
        "BubbleDebug",
        "availableArea.width=${area.width}"
    )

    val maxWidthPx = with(density) {
        (area.width
                - BottomPanelContext.BubbleLeftOffset
                - BottomPanelContext.TextPaddingLeft
                - BottomPanelContext.TextPaddingRight
        ).roundToPx()
    }

    val textMaxWidth = area.width
                        - BottomPanelContext.BubbleLeftOffset
                        - BottomPanelContext.TextPaddingLeft
                        - BottomPanelContext.TextPaddingRight

    Log.d(
        "BubbleDebug",
        "textMaxWidth=$textMaxWidth"
    )

    val textLayoutResult = textMeasurer.measure(
        text = AnnotatedString(bubbleContext.text),
        style = bubbleContext.textStyle,
        constraints = Constraints(
            maxWidth = maxWidthPx
        )
    )

    Log.d(
        "BubbleDebug",
        "lineCount=${textLayoutResult.lineCount}"
    )

    Log.d(
        "BubbleDebug",
        "layoutWidth=${with(density) { textLayoutResult.size.width.toDp() }}"
    )

    Log.d(
        "BubbleDebug",
        "layoutHeight=${with(density) { textLayoutResult.size.height.toDp() }}"
    )

    for (i in 0 until textLayoutResult.lineCount) {

        val width = with(density) {
            (
                    textLayoutResult.getLineRight(i)
                            - textLayoutResult.getLineLeft(i)
                    ).toDp()
        }

        Log.d(
            "BubbleDebug",
            "line[$i].width=$width"
        )
    }


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

    Log.d(
        "BubbleDebug",
        "occupiedWidthByText=$occupiedWidth"
    )

    Log.d(
        "BubbleDebug",
        "occupiedHeightByText=$occupiedHeight"
    )

    Log.d(
        "BubbleDebug",
        "futureBubbleWidth=${
            occupiedWidth +
                    BottomPanelContext.TextPaddingLeft +
                    BottomPanelContext.TextPaddingRight
        }"
    )

    Log.d(
        "BubbleDebug",
        "futureBubbleHeight=${
            occupiedHeight +
                    BottomPanelContext.TextPaddingTop +
                    BottomPanelContext.TextPaddingBottom
        }"
    )



    //bubbleContext.occupiedWidthByText = occupiedWidth
    bubbleContext.occupiedHeightByText = occupiedHeight

    bubbleContext.occupiedWidthByText = with(density) {
        textLayoutResult.size.width.toDp()
    }
}


 */