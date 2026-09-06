package com.capybara.intuition.features.game.presentation.ui


import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import com.capybara.intuition.core.ui.ExitDialog
import com.capybara.intuition.features.game.presentation.layout.calculateGameScreenLayout
import com.capybara.intuition.features.game.presentation.ui.components.bottomPanel.BottomPanel
import com.capybara.intuition.features.game.presentation.ui.components.gameGrid.GameGrid
import com.capybara.intuition.features.game.presentation.ui.components.gameGrid.geometry.calculateGameGridParams
import com.capybara.intuition.features.game.presentation.viewmodel.GameViewModel
import kotlinx.coroutines.launch

@Composable
fun GameScreen(
    gameViewModel: GameViewModel,
    modifier: Modifier = Modifier
) {

    Log.d("BOOT", "GameScreen entered")

    val context by gameViewModel.state.collectAsState()

    // =====================================================
    // SESSION START
    // =====================================================
    LaunchedEffect(Unit) {
        if (context.session == null) {
            gameViewModel.startSession()
        }
    }

    // =====================================================
    // RUNTIME STATE
    // =====================================================
    val session = context.session
    val round = context.currentRound

    if ( session == null || round == null ) {
        return
    }

    val profile = session.profile

    val coroutineScope = rememberCoroutineScope()

    // Start of BACK-dialog

    var showExitDialog by rememberSaveable { mutableStateOf(false) }

    val activity = LocalContext.current as? Activity

    BackHandler {
        showExitDialog = true
    }

    if (showExitDialog) {
        ExitDialog(
            onConfirm = {
                activity?.finish()
            },
            onDismiss = {
                showExitDialog = false
            }
        )
    }


    // End of BACK-dialog

    BoxWithConstraints(
        modifier = Modifier
            /*
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

             */
    ) {
        val width = this.maxWidth
        val this_max_height = this.maxHeight

        // =====================================================
        // SCREEN
        // =====================================================

        Log.d("CapybaraActorDebug", "val layout = calculateGameScreenLayout(\n" +
                "\tscreenWidth = ${maxWidth},\n" +
                "\tscreenHeight = $maxHeight,\n" +
                "\ttopologyWidth = ${profile.maxTopologyWidth},\n" +
                "\ttopologyHeight = ${profile.maxTopologyHeight}\n" +
                ")")

        val layout = calculateGameScreenLayout(
            screenWidth = maxWidth,
            screenHeight = maxHeight,
            topologyWidth = profile.maxTopologyWidth,
            topologyHeight = profile.maxTopologyHeight
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .onGloballyPositioned {
                    Log.d(
                        "CapybaraActorDebug",
                        "Column size=${it.size}"
                    )
                }
        ) {

            val gameGridParams = calculateGameGridParams(
                session = session,
                availableWidth = width,
                availableHeight = layout.gameGridHeight
            )

            Log.d("CapybaraActorDebug", "In GameScreen before call GameGrid, layout = $layout")


            GameGrid(
                session = session,
                round = round,
                gameGridParams = gameGridParams,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        layout.gameGridHeight
                    )
                    .onGloballyPositioned {
                        Log.d(
                            "CapybaraActorDebug",
                            "GameGrid in GameGrid.modifier: size=${it.size}"
                        )
                    }
                ,
                onUserSelected = { resource ->
                    coroutineScope.launch {
                        gameViewModel
                            .onUserSelected(
                                resource
                            )
                    }
                },
                onNextRound = {
                    coroutineScope.launch {
                        gameViewModel.onMainButtonClicked()
                    }
                }
            )


            Log.d(
                "CapybaraActorDebug",
                "In GameScreen before call BottomPanel, layout = $layout"
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    //.height(100.dp)
                    .onGloballyPositioned {
                        Log.d(
                            "CapybaraActorDebug",
                            "Wrapper size=${it.size}"
                        )
                    }
            ) {

                BottomPanel(
                    session = session,
                    round = round,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(layout.bottomPanelHeight),
                    onNewSession = {
                        gameViewModel.startSession()
                    }
                )
            }

            /*
            GameGrid(
                session = session,
                round = round,
                gameGridParams = gameGridParams,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        layout.gameGridHeight
                    )
                    .onGloballyPositioned {
                        Log.d(
                            "CapybaraActorDebug",
                            "GameGrid in GameGrid.modifier: size=${it.size}"
                        )
                    }
                ,
                onUserSelected = { resource ->
                    coroutineScope.launch {
                        gameViewModel
                            .onUserSelected(
                                resource
                            )
                    }
                },
                onNextRound = {
                    coroutineScope.launch {
                        gameViewModel.onMainButtonClicked()
                    }
                }
            )
            */



        }
    }
}