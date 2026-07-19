package com.capybara.intuition.game.presentation.ui.components.bottomPanel.capybara

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.CAPYBARA_HEIGHT_FRACTION
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.capybara.model.CapybaraSpriteMetadata
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.model.BottomPanelContext
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.model.ScenePoint
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.model.SceneRect
import java.util.Objects.toString

@Composable
fun CapybaraActor(
    spriteMetadata: CapybaraSpriteMetadata,
    modifier: Modifier = Modifier,
    bubbleContext: BottomPanelContext
) {
    val density = LocalDensity.current

    Log.d("CapybaraActorDebug", "Called: fun CapybaraActor(\n" +
            "    spriteMetadata: CapybaraSpriteMetadata // $spriteMetadata,\n" +
            "    modifier: Modifier = Modifier // $modifier,\n" +
            "    bubbleContext: BottomPanelContext // ${toString(bubbleContext)}\n" +
            ") ")

    Box(
        modifier = modifier
            .onGloballyPositioned { coordinates ->

                Log.d(
                    "CapybaraActorDebug",
                    "ACTOR positionInParent=${coordinates.positionInParent()}"
                )

                Log.d(
                    "CapybaraActorDebug",
                    "ACTOR positionInRoot=${coordinates.positionInRoot()}"
                )

                Log.d(
                    "CapybaraActorDebug",
                    "ACTOR before calculations size=${coordinates.size}"
                )
                val position = coordinates.positionInParent()
                Log.d(
                    "CapybaraActorDebug",
                    "ACTOR after calculation position, position=$position"
                )


                val size = coordinates.size

                Log.d(
                    "CapybaraActorDebug",
                    "ACTOR after calculations size=${coordinates.size}"
                )

                with(density) {

                    val left = position.x.toDp()
                    val top = position.y.toDp()
                    val width = size.width.toDp()
                    val height = size.height.toDp()

                    bubbleContext.actorRect = SceneRect(
                        left = left,
                        top = top,
                        right = left + width,
                        bottom = top + height
                    )
                }
                Log.d(
                    "CapybaraActorDebug",
                    "In CapybaraActor, bubbleContext.actorRect=${bubbleContext.actorRect}"
                )

            }
    ) {





        Image(
            painter = painterResource(
                id = spriteMetadata.drawableId
            ),
            contentDescription = null,

            alignment = Alignment.BottomStart,
            modifier = Modifier
                .aspectRatio(1f)
                .onGloballyPositioned { coordinates ->

                    Log.d(
                        "CapybaraGreenPointDebug",
                        "Capybara positioned with heightFraction=${CAPYBARA_HEIGHT_FRACTION}"
                    )

                    val actorRect = bubbleContext.actorRect ?: return@onGloballyPositioned

                    val position = coordinates.positionInParent()
                    Log.d(
                        "CapybaraGreenPointDebug",
                        "positionInParent=${coordinates.positionInParent()}"
                    )

                    Log.d(
                        "CapybaraGreenPointDebug",
                        "positionInRoot=${coordinates.positionInRoot()}"
                    )
                    val size = coordinates.size
                    Log.d(
                        "CapybaraGreenPointDebug",
                        "Before calculation by 'with(density) {':" +
                                "\tposition=$position," +
                                "\tsize=${size.width} x ${size.height}"
                    )


                    with(density) {

                        val left = position.x.toDp()
                        val top = position.y.toDp()
                        val width = size.width.toDp()
                        val height = size.height.toDp()
                        bubbleContext.actorRect = SceneRect(
                            left = left,
                            top = top,
                            right = left + width,
                            bottom = top + height
                        )
                        Log.d(
                            "CapybaraGreenPointDebug",
                            "For bubbleContext.actorRect in 'with(density) {', params for bubbleContext.actorRect are:" +
                                    "left = $left" +
                                    "top = $top" +
                                    "right = $width" +
                                    "height = $height"
                        )

                        bubbleContext.speechAnchor = ScenePoint(
                            x = actorRect.left +
                                    width * spriteMetadata.speechAnchor.xFraction,

                            y = actorRect.top +
                                    height * spriteMetadata.speechAnchor.yFraction
                        )
                        Log.d(
                            "CapybaraGreenPointDebug",
                            "Calculated bubbleContext.speechAnchor:" +
                                    "\tx = ${left + width * spriteMetadata.speechAnchor.xFraction}" +
                                    "\ty = ${top + height * spriteMetadata.speechAnchor.yFraction}"
                        )
                    }
                }
        )
    }
}