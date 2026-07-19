package com.capybara.intuition.game.presentation.ui.components.bottomPanel.indicators


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.game.domain.model.GameSession
import com.capybara.intuition.game.domain.model.RoundType
import com.capybara.intuition.game.domain.round.GameRound
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.model.BottomPanelContext
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.model.SceneRect
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun Indicators(
    session: GameSession,
    round: GameRound,
    modifier: Modifier = Modifier,
    bubbleContext: BottomPanelContext
) {

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    // =====================================================
    // ANIMATION CONFIG
    // =====================================================

    val indicatorExpandDurationMs = 500 // 200
    val indicatorExpandedHoldDurationMs = 500 // 150
    val indicatorCollapseDurationMs = 150 // 120
    val indicatorCollapsedHoldDurationMs = 500 // 80
    val indicatorRecoveryDurationMs = 300

    val indicatorNormalScale = 1f
    val indicatorExpandedScale = 1.6f // 1.3f
    val indicatorCollapsedScale = 0.7f // 0.9f

    val indicatorExpandedBlur = 8f // 4f
    val indicatorNormalBlur = 0f

    val indicatorExpandedAlpha = 0.5f
    val indicatorNormalAlpha = 1f

    // =====================================================
    // DISPLAYED VALUES
    // =====================================================

    var displayedWinCount by remember {
        mutableIntStateOf(session.winCount)
    }

    var displayedLoseCount by remember {
        mutableIntStateOf(session.loseCount)
    }

    // =====================================================
    // WIN ANIMATION STATE
    // =====================================================

    val winScale = remember {
        Animatable(indicatorNormalScale)
    }

    val winBlur = remember {
        Animatable(indicatorNormalBlur)
    }

    val winAlpha = remember {
        Animatable(indicatorNormalAlpha)
    }

    // =====================================================
    // LOSE ANIMATION STATE
    // =====================================================

    val loseScale = remember {
        Animatable(indicatorNormalScale)
    }

    val loseBlur = remember {
        Animatable(indicatorNormalBlur)
    }

    val loseAlpha = remember {
        Animatable(indicatorNormalAlpha)
    }

    // =====================================================
    // WIN ANIMATION
    // =====================================================

    LaunchedEffect(session.winCount) {

        if (session.winCount > displayedWinCount) {

            launch {
                winScale.animateTo(
                    indicatorExpandedScale,
                    tween(indicatorExpandDurationMs)
                )
            }

            launch {
                winBlur.animateTo(
                    indicatorExpandedBlur,
                    tween(indicatorExpandDurationMs)
                )
            }

            launch {
                winAlpha.animateTo(
                    indicatorExpandedAlpha,
                    tween(indicatorExpandDurationMs)
                )
            }

            delay(indicatorExpandedHoldDurationMs.toLong())

            displayedWinCount = session.winCount

            launch {
                winScale.animateTo(
                    indicatorCollapsedScale,
                    tween(indicatorCollapseDurationMs)
                )
            }

            launch {
                winBlur.animateTo(
                    indicatorNormalBlur,
                    tween(indicatorCollapseDurationMs)
                )
            }

            launch {
                winAlpha.animateTo(
                    indicatorNormalAlpha,
                    tween(indicatorCollapseDurationMs)
                )
            }

            delay(indicatorCollapsedHoldDurationMs.toLong())

            winScale.animateTo(
                indicatorNormalScale,
                tween(indicatorRecoveryDurationMs)
            )
        }
    }

    // =====================================================
    // LOSE ANIMATION
    // =====================================================

    LaunchedEffect(session.loseCount) {

        if (session.loseCount > displayedLoseCount) {

            launch {
                loseScale.animateTo(
                    indicatorExpandedScale,
                    tween(indicatorExpandDurationMs)
                )
            }

            launch {
                loseBlur.animateTo(
                    indicatorExpandedBlur,
                    tween(indicatorExpandDurationMs)
                )
            }

            launch {
                loseAlpha.animateTo(
                    indicatorExpandedAlpha,
                    tween(indicatorExpandDurationMs)
                )
            }

            delay(indicatorExpandedHoldDurationMs.toLong())

            displayedLoseCount = session.loseCount

            launch {
                loseScale.animateTo(
                    indicatorCollapsedScale,
                    tween(indicatorCollapseDurationMs)
                )
            }

            launch {
                loseBlur.animateTo(
                    indicatorNormalBlur,
                    tween(indicatorCollapseDurationMs)
                )
            }

            launch {
                loseAlpha.animateTo(
                    indicatorNormalAlpha,
                    tween(indicatorCollapseDurationMs)
                )
            }

            delay(indicatorCollapsedHoldDurationMs.toLong())

            loseScale.animateTo(
                indicatorNormalScale,
                tween(indicatorRecoveryDurationMs)
            )
        }
    }

    // =====================================================
    // UI
    // =====================================================
    val density = LocalDensity.current
    Column(
        modifier=modifier
            .onGloballyPositioned { coordinates ->

                val position = coordinates.positionInParent()
                val size = coordinates.size
                with(density) {

                    val left = position.x.toDp()
                    val top = position.y.toDp()
                    val width = size.width.toDp()
                    val height = size.height.toDp()
                    val rect = SceneRect(
                        left = left,
                        top = top,
                        right = left + width,
                        bottom = top + height
                    )
                    bubbleContext.indicatorsRect = rect
                }
            },
        horizontalAlignment = Alignment.End
    ) {
        Row {

            AnimatedIndicatorText(
                "\uD83C\uDF4F", // 🍏 Green Apple
                glowColor = colors.capybaraWinGlow,
                scale = winScale.value,
                blur = winBlur.value,
                alpha = winAlpha.value
            )

            Spacer(Modifier.width(4.dp))

            Text(
                displayedWinCount.toString(), // 🍏 Green Apple
                color = colors.winCount,
                style = typography.bodyMedium
            )

            Text(
                text = " : ",
                style = MaterialTheme.typography.bodyMedium,
                color = colors.activeElementText
            )

            Text(
                text = displayedLoseCount.toString(), // 💩
                color = colors.loseCount,
                style = typography.bodyMedium
            )

            Spacer(Modifier.width(4.dp))

            AnimatedIndicatorText(
                text = "\uD83D\uDCA9",
                glowColor = colors.capybaraLoseGlow,
                scale = loseScale.value,
                blur = loseBlur.value,
                alpha = loseAlpha.value
            )

        }
        Text(
            text = stringResource(
                if (round.roundType == RoundType.AFTER) {
                    R.string.game_mode_after
                } else {
                    R.string.game_mode_before
                }
            ),
            style = MaterialTheme.typography.bodyMedium,
            color = colors.capybaraTerracotta
        )


    }
}



