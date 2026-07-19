package com.capybara.intuition.game.presentation.ui.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.capybara.game.presentation.ui.components.BottomPanel
import com.example.capybara.game.presentation.ui.components.GameGrid

import com.example.capybara.game.presentation.viewmodel.GameViewModel
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    gameViewModel: GameViewModel
) {

    Log.d("SUBMIT", "GameScreen: called")

    // =====================================================
    // STATE
    // =====================================================

    val state by gameViewModel
        .state
        .collectAsState()


    // =====================================================
    // SESSION START
    // =====================================================
    LaunchedEffect(Unit) {
        Log.d("SUBMIT", "GameScreen: LaunchedEffect(Unit) { started at GameScreen")
        if (state.session == null) {
            gameViewModel.startSession()
        }
    }

    // =====================================================
    // RUNTIME STATE
    // =====================================================

    val session = state.session
    val round = state.currentRound

    // =====================================================
    // WAIT FOR SESSION/ROUND
    // =====================================================

    if ( session == null || round == null
    ) {
        Log.d("SUBMIT", "GameScreen died")
        return
    }

    // =====================================================
    // SCREEN
    // =====================================================

    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                enabled = round.result != null,
                indication = null,
                interactionSource =
                    remember {
                        MutableInteractionSource()
                    }
            ) {
                coroutineScope.launch {
                    gameViewModel
                        .onMainButtonClicked()
                }
            }
    ) {

        Column(

            modifier =
                Modifier.fillMaxSize()
        ) {

            // =============================================
            // GAME GRID
            // =============================================

            GameGrid(

                round = round,
                session = session,
                modifier = Modifier.weight(1f),
                onOptionClick = { resource ->

                    coroutineScope.launch {

                        gameViewModel
                            .onUserSelected(
                                resource
                            )
                    }
                }
            )

            // =============================================
            // BOTTOM PANEL
            // =============================================

            BottomPanel(
                round = round,
                session = session
            )
        }
    }


}