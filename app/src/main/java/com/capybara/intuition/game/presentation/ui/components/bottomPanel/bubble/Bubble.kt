package com.capybara.intuition.game.presentation.ui.components.bottomPanel.bubble

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.model.BottomPanelContext


@Composable
fun Bubble(
    bubbleContext: BottomPanelContext
) {

    val context = LocalContext.current

    val availableArea = bubbleContext.bubbleAvailableArea ?: return
    val speechAnchor = bubbleContext.speechAnchor ?: return
    val textWidth = bubbleContext.occupiedWidthByText ?: return
    val textHeight = bubbleContext.occupiedHeightByText ?: return
    val bubbleWidth = textWidth +
                BottomPanelContext.TextPaddingLeft +
                BottomPanelContext.TextPaddingRight - 2.dp
    val bubbleHeight = textHeight +
                BottomPanelContext.TextPaddingTop +
                BottomPanelContext.TextPaddingBottom
    val bubbleLeft = availableArea.left +
                BottomPanelContext.BubbleLeftOffset
    val bubbleTop = availableArea.top

    val colors = IntuitionTheme.colors

    Box(
        modifier = Modifier
            .offset(
                x = bubbleLeft,
                y = bubbleTop
            )
            .size(
                width = bubbleWidth,
                height = bubbleHeight
            )
    ) {

        val fillColor = colors.bubbleBackground
        val strokeColor = colors.bubbleBorder

        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            val cornerRadius = BottomPanelContext.BubbleCornerRadius.toPx()
            val bubbleRight = size.width
            val bubbleBottom = size.height

            val tailBaseOffset = BottomPanelContext.TailBaseOffsetFromTop.toPx()
            val tailBaseHeight = BottomPanelContext.TailBaseHeight.toPx()



            val tailTop = Offset(
                    x = 0f,
                    y = tailBaseOffset
                )

            val tailBottom = Offset(
                    x = 0f,
                    y = tailBaseOffset + tailBaseHeight
                )

            val tailAnchor = Offset(
                    x = speechAnchor.x.toPx() - bubbleLeft.toPx(),
                    y = speechAnchor.y.toPx()
                            - bubbleTop.toPx()
                )

            val path = Path().apply {

                moveTo(
                    tailTop.x,
                    tailTop.y
                )

                lineTo(
                    tailAnchor.x,
                    tailAnchor.y
                )


                lineTo(
                    tailBottom.x,
                    tailBottom.y
                )

                // My point
                lineTo(
                    tailBottom.x,
                    bubbleBottom - cornerRadius
                )

                arcTo(
                    rect = Rect(
                        left = 0f,
                        top = bubbleBottom - 2 * cornerRadius,
                        right = 2 * cornerRadius,
                        bottom = bubbleBottom
                    ),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = -90f,
                    forceMoveTo = false
                )

                lineTo(
                    bubbleRight - cornerRadius,
                    bubbleBottom
                )

                arcTo(
                    rect = Rect(
                        left = bubbleRight - 2 * cornerRadius,
                        top = bubbleBottom - 2 * cornerRadius,
                        right = bubbleRight,
                        bottom = bubbleBottom
                    ),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = -90f,
                    forceMoveTo = false
                )

                lineTo(
                    bubbleRight,
                    cornerRadius
                )

                arcTo(
                    rect = Rect(
                        left = bubbleRight - 2 * cornerRadius,
                        top = 0f,
                        right = bubbleRight,
                        bottom = 2 * cornerRadius
                    ),
                    startAngleDegrees = 0f,
                    sweepAngleDegrees = -90f,
                    forceMoveTo = false
                )

                lineTo(
                    cornerRadius,
                    0f
                )

                arcTo(
                    rect = Rect(
                        left = 0f,
                        top = 0f,
                        right = 2 * cornerRadius,
                        bottom = 2 * cornerRadius
                    ),
                    startAngleDegrees = 270f,
                    sweepAngleDegrees = -90f,
                    forceMoveTo = false
                )

                lineTo(
                    0f,
                    tailTop.y
                )

                close()
            }

            drawPath(
                path = path,
                color =fillColor
            )

            drawPath(
                path = path,
                color = strokeColor,
                style = Stroke(
                    width = 2.dp.toPx()
                )
            )
        }

        Text(
            text = bubbleContext.text,
            style = bubbleContext.textStyle.copy(
                color = colors.activeElementText
            ),
            modifier = Modifier.padding(
                start = BottomPanelContext.TextPaddingLeft,
                top = BottomPanelContext.TextPaddingTop,
                end = BottomPanelContext.TextPaddingRight,
                bottom = BottomPanelContext.TextPaddingBottom
            )
        )
    }
}
