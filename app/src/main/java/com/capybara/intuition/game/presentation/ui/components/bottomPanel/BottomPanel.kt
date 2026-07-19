package com.capybara.intuition.game.presentation.ui.components.bottomPanel


import android.util.Log
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import com.capybara.intuition.game.domain.model.GameSession
import com.capybara.intuition.game.domain.model.RoundResult
import com.capybara.intuition.game.domain.round.GameRound
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.bubble.Bubble
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.bubble.BubblePhraseContext
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.bubble.render
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.capybara.CapybaraActor
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.capybara.emotional.mapScoreAndEventToEmotion
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.capybara.model.capybaraSprites
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.geometry.resolveBubbleAvailableArea
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.geometry.resolveBubbleOccupiedArea
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.indicators.Indicators
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.model.BottomPanelContext
import com.capybara.intuition.game.presentation.ui.components.bottomPanel.model.BottomPanelSceneBounds


val CAPYBARA_HEIGHT_FRACTION = 0.8f

@Composable
fun BottomPanel(
    session: GameSession,
    round: GameRound,
    modifier: Modifier
) {
    Log.d("BubbleDebug", "BottomPanel called with modifier = ${modifier}")

    val context = LocalContext.current

    val capybaraEmotion = mapScoreAndEventToEmotion(
        session = session,
        round = round
    )

    val capybaraSprite = capybaraSprites(
        capybaraEmotion,
        session.profile.preferredMode
    )

    val textStyle = MaterialTheme.typography.bodyMedium
    val phraseContext = BubblePhraseContext(
        profileName = session.profile.name,
        roundResult = when (round.result) {
            RoundResult.WIN -> "виграв"
            RoundResult.LOSE -> "програв"
            else -> "починаємо"
        },
        winCount = session.winCount,
        loseCount = session.loseCount,
        streak = maxOf(
            session.winStreak,
            session.loseStreak
        )
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




    Log.d("BubbleDebug", "bubbleContext created as $bubbleContext")
    //Log.d("BubbleDebug", "at bubbleContext event = ${bubbleContext.event}")




    Log.d("BubbleDebug", "bubbleContext created")

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        Log.d("CapybaraActorDebug", "In BoxWithConstraints(\n" +
                "        modifier = modifier.fillMaxSize()\n" +
                "    ) {, this.maxHeight = ${this.maxHeight}")

        val sceneBounds = BottomPanelSceneBounds(
            width = this.maxWidth,
            height = this.maxHeight
        )





        Log.d("CapybaraActorDebug", "Before CapybaraActor(, this.maxHeight = ${this.maxHeight}")
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
            bubbleContext = bubbleContext
        )





    }
}