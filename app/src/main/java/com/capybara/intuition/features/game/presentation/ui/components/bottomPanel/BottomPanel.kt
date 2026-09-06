package com.capybara.intuition.features.game.presentation.ui.components.bottomPanel


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.game.domain.model.GameSession
import com.capybara.intuition.features.game.domain.model.RoundResult
import com.capybara.intuition.features.game.domain.round.GameRound
import com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.bubble.Bubble
import com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.bubble.BubblePhraseContext
import com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.bubble.render
import com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.capybara.CapybaraActor
import com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.capybara.emotional.mapScoreAndEventToEmotion
import com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.capybara.model.capybaraSprites
import com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.geometry.resolveBubbleAvailableArea
import com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.geometry.resolveBubbleOccupiedArea
import com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.indicators.Indicators
import com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.model.BottomPanelContext
import com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.model.BottomPanelSceneBounds
import kotlin.math.floor
import kotlin.math.pow


val CAPYBARA_HEIGHT_FRACTION = 0.8f

@Composable
fun BottomPanel(
    session: GameSession,
    round: GameRound,
    onNewSession: () -> Unit,
    modifier: Modifier
) {
    val context = LocalContext.current
    val typography = IntuitionTheme.typography

    val capybaraEmotion = mapScoreAndEventToEmotion(
        session = session,
        round = round
    )

    val capybaraSprite = capybaraSprites(
        capybaraEmotion,
        session.profile.preferredMode
    )

    val textStyle = typography.bodyMedium

    val roundResult = round.result
    val streak = maxOf(
        session.winStreak,
        session.loseStreak
    )

    val betSuccessProbability = 1.0 / session.profile.currentLevel

    val roundCount = session.winCount + session.loseCount

    val tailProbability = when (roundResult) {
        RoundResult.WIN -> {
            if (streak == roundCount) {
                betSuccessProbability.pow(roundCount)
            } else {
                (1 - betSuccessProbability) * betSuccessProbability.pow(streak)
            }
        }

        RoundResult.LOSE -> {
            if (streak == roundCount) {
                (1 - betSuccessProbability).pow(roundCount)
            } else {
                betSuccessProbability * (1 - betSuccessProbability).pow(streak)
            }
        }

        else -> betSuccessProbability
    }

    val lastBrokenStreak = session.lastBrokenStreak

    val phraseContext = BubblePhraseContext(
        profileName = session.profile.name,
        winCount = session.winCount,
        loseCount = session.loseCount,
        streak = streak,
        lastBrokenStreak = lastBrokenStreak,
        sessionSuccess = if (roundCount > 0) {
            (100 * session.winCount / roundCount).toInt()
        } else {
            0
        },
        tailOneIn = if (tailProbability > 0.0) {
            floor(1.0 / tailProbability).toInt()
        } else {
            1
        }
    )

    val text = remember(
        capybaraEmotion,
        session.winCount,
        session.loseCount,
        session.winStreak,
        session.loseStreak,
        round.result,
    ) {
        context.resources
            .getStringArray(
                capybaraSprite.phrasesResId
            )
            .random()
    }
        .render(phraseContext)

    val bubbleContext = remember (
        text,
        capybaraSprite
    ) {
        BottomPanelContext(
            text = text,
            capybaraSprite = capybaraSprite,
            textStyle = textStyle
        )
    }

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        val sceneBounds = BottomPanelSceneBounds(
            width = this.maxWidth,
            height = this.maxHeight
        )

        CapybaraActor(
            spriteMetadata = capybaraSprite,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .height(this.maxHeight*CAPYBARA_HEIGHT_FRACTION),
            bubbleContext = bubbleContext
        )

        Indicators(
            session = session,
            round = round,
            onNewSession = onNewSession,
            modifier = Modifier
                .align(Alignment.BottomEnd),
            bubbleContext = bubbleContext
        )

        resolveBubbleAvailableArea(
            bubbleContext,
            sceneBounds,
        )

        val textMeasurer = rememberTextMeasurer()
        val localDensity = LocalDensity.current

        resolveBubbleOccupiedArea(
            bubbleContext = bubbleContext,
            textMeasurer = textMeasurer,
            density = localDensity,
        )

        Bubble(
            bubbleContext = bubbleContext,
            textMeasurer = textMeasurer
        )

    }
}