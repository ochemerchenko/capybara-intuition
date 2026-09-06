package com.capybara.intuition.features.game.presentation.ui.components.gameGrid

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.game.domain.model.GameSession
import com.capybara.intuition.features.game.domain.model.Resource
import com.capybara.intuition.features.game.domain.renderer.rendererResolver
import com.capybara.intuition.features.game.domain.round.GameRound

@Composable
fun BetButton(
    resource: Resource,
    round: GameRound,
    session: GameSession,
    size: Dp,
    onClick: () -> Unit
) {

    // =============================================
    // GAMEPLAY STATE
    // =============================================

    val isSelected = round.selectedResource == resource
    val isCorrect = round.correctResource == resource
    val isEnabled = round.result == null

    // =============================================
    // BET BUTTON PRESENTATION SEMANTICS
    // =============================================

    val activeBetButtonAlpha = 1f
    val inactiveBetButtonAlpha = 0.3f
    val betButtonResultIndicatorScaleFactor = 0.42f
    val betButtonBorderScaleFactor = 0.035f
    val betButtonShadowScaleFactor = 0.08f
    val betButtonResultSuccessSymbol = "✓"
    val betButtonResultFailureSymbol = "✕"

    val colors = IntuitionTheme.colors

    // =============================================
    // SHAPE
    // =============================================
    val shape = when (session.profile.maskId) {
        MaskIds.CIRCLE -> CircleShape
        else -> RectangleShape
    }

    // =============================================
    // STATE-DEPENDENT VISUALS
    // =============================================

    val alpha by animateFloatAsState(

        targetValue = when {

            isEnabled -> activeBetButtonAlpha
            isSelected -> activeBetButtonAlpha
            isCorrect -> activeBetButtonAlpha
            else -> inactiveBetButtonAlpha

        },
        label = "betButtonAlphaAnimation"
    )

    val showShadow = isEnabled || isSelected || isCorrect

    // =============================================
    // RENDERER
    // =============================================

    val renderer = remember(
        resource.type,
        session.profile.rendererId,
        session.profile.maskId
    ) {

        rendererResolver(
            resourceType = resource.type.name,
            maskId = session.profile.maskId,
            rendererId = session.profile.rendererId
        )
    }

    // =============================================
    // DERIVED BET BUTTON DIMENSIONS
    // =============================================

    val betButtonResultIndicatorFontSize = (size.value * betButtonResultIndicatorScaleFactor).sp
    val betButtonBorderWidth = size * betButtonBorderScaleFactor
    val betButtonShadowElevation = size * betButtonShadowScaleFactor

    // =============================================
    // ROOT
    // =============================================

    Box(

        modifier = Modifier
            .alpha(alpha)
            .clickable(
                enabled = isEnabled
            ) {
                onClick()
            }
    ) {

        Box(
            modifier = Modifier
                .size(size)
                .aspectRatio(1f)
                .then(
                    if (showShadow) {
                        Modifier.shadow(
                            elevation = betButtonShadowElevation,
                            shape = shape,
                            clip = false
                        )
                    } else {
                        Modifier
                    }
                )
                .clip(shape)
        ) {
            renderer.Render(
                maskId = session.profile.maskId,
                resource = resource,
                size = size
            )


            // =============================================
            // RESULT OVERLAY
            // =============================================

            if (isSelected) {

                val resultIndicatorSymbol =
                    if (isCorrect) {
                        betButtonResultSuccessSymbol
                    } else {
                        betButtonResultFailureSymbol
                    }

                Text(
                    text = resultIndicatorSymbol,
                    fontSize = betButtonResultIndicatorFontSize,
                    fontWeight = FontWeight.Bold,
                    color = colors.betButtonResultIndicator,
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )

            }
        }
    }
}