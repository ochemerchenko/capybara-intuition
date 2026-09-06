package com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.bubble

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.model.BottomPanelContext
import kotlin.io.path.Path
import kotlin.io.path.moveTo


@Composable
fun Bubble(
    bubbleContext: BottomPanelContext,
    textMeasurer: TextMeasurer,
    modifier: Modifier = Modifier
) {

    val typography = IntuitionTheme.typography

    val context = LocalContext.current

    val availableArea = bubbleContext.bubbleAvailableArea ?: return
    val speechAnchor = bubbleContext.speechAnchor ?: return
    val textWidth = bubbleContext.occupiedWidthByText ?: return
    val textHeight = bubbleContext.occupiedHeightByText ?: return
    val bubbleWidth = textWidth +
                BottomPanelContext.TextPaddingLeft +
                BottomPanelContext.TextPaddingRight // - 2.dp

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
            .width(bubbleWidth)
            .height(bubbleHeight)
    ) {


        val fillColor = colors.bubbleBackground
        val strokeColor = colors.bubbleBorder



        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {

            val cornerRadius = BottomPanelContext.BubbleCornerRadius.toPx()

            val strokeWidth = 2.dp.toPx()
            val halfStroke = strokeWidth / 2f

            val bubbleRight = size.width - halfStroke
            val bubbleBottom = size.height - halfStroke

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
                y = speechAnchor.y.toPx() - bubbleTop.toPx()
            )

            val path = Path().apply {

                moveTo(tailTop.x, tailTop.y)

                lineTo(
                    tailAnchor.x,
                    tailAnchor.y
                )

                lineTo(
                    tailBottom.x,
                    tailBottom.y
                )

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
                    bubbleRight - cornerRadius - 10f,
                    bubbleBottom
                )

                arcTo(
                    rect = Rect(
                        left = bubbleRight - 2 * cornerRadius,
                        top = bubbleBottom - 2 * cornerRadius,
                        right = bubbleRight - 10f,
                        bottom = bubbleBottom
                    ),
                    startAngleDegrees = 90f,
                    sweepAngleDegrees = -90f,
                    forceMoveTo = false
                )

                lineTo(
                    bubbleRight -10f,
                    cornerRadius
                )

                arcTo(
                    rect = Rect(
                        left = bubbleRight - 2 * cornerRadius,
                        top = 0f,
                        right = bubbleRight -10f,
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
                color = fillColor
            )

            drawPath(
                path = path,
                color = strokeColor,
                style = Stroke(width = strokeWidth)
            )
        }



        Text(
            text = bubbleContext.text,
            style = bubbleContext.textStyle.copy(
                color = colors.activeElementText
            ),
            modifier = Modifier
                .padding(
                    start = BottomPanelContext.TextPaddingLeft,
                    top = BottomPanelContext.TextPaddingTop,
                    end = BottomPanelContext.TextPaddingRight,
                    bottom = BottomPanelContext.TextPaddingBottom
                )
        )
    }
}
