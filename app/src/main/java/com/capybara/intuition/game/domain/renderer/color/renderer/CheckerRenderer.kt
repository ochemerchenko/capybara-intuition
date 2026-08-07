package com.capybara.intuition.game.domain.renderer.color.renderer


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.unit.Dp
import com.capybara.intuition.game.domain.model.Resource
import com.capybara.intuition.game.domain.renderer.BetButtonRenderer
import com.capybara.intuition.game.presentation.ui.components.gameGrid.MaskIds
import kotlin.math.min


object CheckerRenderer : BetButtonRenderer {

    @Composable
    override fun Render(
        maskId: Short,
        resource: Any,
        size: Dp
    ) {

        // =====================================================
        // CHECKER RENDERER PRESENTATION SEMANTICS
        // =====================================================
        val checkerRendererMaskRadiusScaleFactor = 0.92f
        val checkerRendererGradientInnerAlpha = 0.92f
        val checkerRendererGradientOuterAlpha = 0.98f
        val checkerRendererInnerRingCount = 4
        val checkerRendererInnerRingInsetScaleFactor = 0.08f
        val checkerRendererInnerRingAlpha = 0.05f
        val checkerRendererInnerRingRadiusScaleFactor = 0.88f
        val checkerRendererInnerRingStrokeScaleFactor = 0.045f
        val checkerRendererCheckerCellsPerAxis = 6
        val checkerRendererCheckerLightCellAlpha = 0f
        val checkerRendererCheckerDarkCellAlpha = 0f
        val checkerRendererOuterGlowAlpha = 0.12f
        val checkerRendererOuterGlowRadiusScaleFactor = 0.96f
        val checkerRendererOuterGlowStrokeScaleFactor = 0.06f
        val checkerRendererShadowEdgeAlpha = 0.08f
        val checkerRendererShadowEdgeRadiusScaleFactor = 0.99f
        val checkerRendererShadowEdgeStrokeScaleFactor = 0.04f

        val betButtonBorderAlpha = 0.08f
        val betButtonBorderColor = Color.Black.copy(
            alpha = betButtonBorderAlpha
        )

        // =====================================================
        // RESOURCE
        // =====================================================

        val runtimeResource = resource as Resource
        val baseColor = Color(
            color = android.graphics.Color.parseColor(
                runtimeResource.value
            )
        )
        val betButtonBorderScaleFactor = 0.035f
        val betButtonBorderWidth = size * betButtonBorderScaleFactor



        // =====================================================
        // CANVAS
        // =====================================================
        Canvas(
            modifier = Modifier
                .size(size)
        ) {
            val canvasSize = min(
                this.size.width,
                this.size.height
            )
            val radius = canvasSize / 2f
            val center = Offset(
                x = this.size.width / 2f,
                y = this.size.height / 2f
            )


            // =================================================
            // MAIN SHAPE
            // =================================================
            val shapePath = createMaskPath(
                maskId = maskId,
                center = center,
                radius = radius * checkerRendererMaskRadiusScaleFactor
            )


            // =================================================
            // BASE FILL
            // =================================================
            drawPath(
                path = shapePath,
                brush = Brush.radialGradient(
                    colors = listOf(
                        baseColor.copy( alpha = checkerRendererGradientInnerAlpha ),
                        baseColor.copy( alpha = checkerRendererGradientOuterAlpha )
                    ),
                    center = center,
                    radius = radius
                )
            )


            // =================================================
            // BORDER
            // =================================================
            drawCircle(
                color = betButtonBorderColor,
                radius = radius * checkerRendererOuterGlowRadiusScaleFactor,
                center = center,
                style = Stroke(
                    width = radius * checkerRendererOuterGlowStrokeScaleFactor
                )
            )


            // =================================================
            // SOFT INNER RINGS
            // =================================================
            repeat(
                checkerRendererInnerRingCount
            ) { index ->
                val inset = index * radius * checkerRendererInnerRingInsetScaleFactor
                drawCircle(
                    color = Color.White.copy(
                        alpha = checkerRendererInnerRingAlpha
                    ),
                    radius = radius * checkerRendererInnerRingRadiusScaleFactor - inset,
                    center = center,
                    style = Stroke( width = radius * checkerRendererInnerRingStrokeScaleFactor )
                )
            }


            // =================================================
            // CHECKER TEXTURE
            // =================================================
            val cells = checkerRendererCheckerCellsPerAxis
            val cellSize = (radius * 2f) / cells

            clipPath(
                shapePath
            ) {

                for (x in 0 until cells) {
                    for (y in 0 until cells) {
                        val even = (x + y) % 2 == 0

                        drawRect(

                            color =
                                if (even)
                                    Color.White.copy( alpha = checkerRendererCheckerLightCellAlpha )
                                else
                                    Color.Black.copy( alpha = checkerRendererCheckerDarkCellAlpha ),
                            topLeft = Offset(
                                x = center.x - radius + x * cellSize,
                                y = center.y - radius + y * cellSize
                            ),

                            size = Size(
                                width = cellSize,
                                height = cellSize
                            )
                        )
                    }
                }
            }


            // =================================================
            // OUTER GLOW RING
            // =================================================
            drawCircle(
                color = Color.White.copy(
                    alpha = checkerRendererOuterGlowAlpha
                ),
                radius = radius * checkerRendererOuterGlowRadiusScaleFactor,
                center = center,
                style = Stroke(
                    width = radius * checkerRendererOuterGlowStrokeScaleFactor
                )
            )


            // =================================================
            // SOFT SHADOW EDGE
            // =================================================
            drawCircle(
                color = Color.Black.copy(
                    alpha = checkerRendererShadowEdgeAlpha
                ),
                radius = radius * checkerRendererShadowEdgeRadiusScaleFactor,
                center = center,
                style = Stroke(
                    width = radius * checkerRendererShadowEdgeStrokeScaleFactor
                )
            )
        }
    }


    // =============================================================
    // MASK SHAPES
    // =============================================================
    private fun createMaskPath(
        maskId: Short,
        center: Offset,
        radius: Float
    ): Path {

        return when (maskId) {

            MaskIds.CIRCLE -> {

                Path().apply {
                    addOval(
                        androidx.compose.ui.geometry.Rect(

                            left = center.x - radius,
                            top = center.y - radius,
                            right = center.x + radius,
                            bottom = center.y + radius

                        )
                    )
                }
            }

            MaskIds.SQUARE -> {
                Path().apply {
                    addRect(
                        androidx.compose.ui.geometry.Rect(

                            left = center.x - radius,
                            top = center.y - radius,
                            right = center.x + radius,
                            bottom = center.y + radius

                        )
                    )
                }
            }

            else -> {

                Path().apply {
                    addOval(
                        androidx.compose.ui.geometry.Rect(

                            left = center.x - radius,
                            top = center.y - radius,
                            right = center.x + radius,
                            bottom = center.y + radius

                        )
                    )
                }
            }
        }
    }
}
