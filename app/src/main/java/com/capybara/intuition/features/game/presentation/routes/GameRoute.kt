package com.capybara.intuition.features.game.presentation.routes

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.capybara.intuition.app.App
import com.capybara.intuition.core.ui.WaitingScreen
import com.capybara.intuition.features.game.presentation.ui.GameScreen
import com.capybara.intuition.features.game.presentation.viewmodel.GameViewModel
import com.capybara.intuition.features.game.presentation.viewmodel.GameViewModelFactory



@Composable
fun GameRoute(
    modifier: Modifier
) {
    Log.d("SUBMIT", "GameRoute: called")
    val context = LocalContext.current.applicationContext
    val app = context.applicationContext as App
    val container = app.appContainer
    val gameViewModel: GameViewModel = viewModel(
        factory = GameViewModelFactory(
            container
        )
    )
    val state by gameViewModel
        .state
        .collectAsState()

    LaunchedEffect(Unit) {
        gameViewModel.startSession()
    }

    val session = state.session
    val currentRound = state.currentRound
    Log.d("BOOT", "GameRoute: state.session = $session, currentRound = $currentRound")
    Log.d(
        "BOOT",
        "GameRoute recomposed: session=${state.session != null}, round=${state.currentRound != null}"
    )
    if (
        state.session == null || state.currentRound == null
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Log.d("SUBMIT", "GameRoute2: state.session = $session, currentRound = $currentRound")
            //Text("Loading ar GameRouter...")
            WaitingScreen()
        }
        return
    }

    GameScreen(
        modifier = modifier,
        gameViewModel = gameViewModel
    )
}
