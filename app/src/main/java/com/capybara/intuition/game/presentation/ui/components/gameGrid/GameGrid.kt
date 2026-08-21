package com.capybara.intuition.game.presentation.ui.components.gameGrid

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.capybara.intuition.game.domain.model.GameSession
import com.capybara.intuition.game.domain.model.Resource
import com.capybara.intuition.game.domain.round.GameRound
import com.capybara.intuition.game.presentation.ui.components.gameGrid.geometry.GameGridParams


@Composable
fun GameGrid(
    session: GameSession,
    round: GameRound,
    gameGridParams: GameGridParams,
    modifier: Modifier = Modifier,
    onUserSelected: (Resource) -> Unit,
    onNextRound: () -> Unit
) {
    // =====================================================
    // GAME GRID PRESENTATION SEMANTICS
    // =====================================================
    val gameGridLoadingMessage = "Topology loading..."

    // =====================================================
    // TOPOLOGY
    // =====================================================
    val topology = session.profile.activeTopology

    if (
        topology == null || round == null
    ) {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                gameGridLoadingMessage
            )
        }
        return
    }

    // =====================================================
    // UI
    // =====================================================

    val activeCells =
        topology.cells
            .filter { !it.blocked }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {

        activeCells.forEach { cell ->
            Log.d("GRID", "cells=${topology.cells.size}, active=${activeCells.size}")
            val occupancyIndex =
                activeCells.indexOfFirst {
                    it.index == cell.index
                }

            if (occupancyIndex == -1) {
                return@forEach
            }

            val resource = round.gameSet[occupancyIndex]
            Log.d("GRID", "gameSet=${round.gameSet.size}")

            val geometry =
                gameGridParams.cells
                    .first {
                        it.index == cell.index
                    }
            Log.d(
                "GRID",
                "cell=${cell.index} left=${geometry.left} top=${geometry.top}"
            )

            Box(
                modifier = Modifier.offset(
                    x = geometry.left,
                    y = geometry.top
                )
            ) {

                BetButton(
                    resource = resource,
                    session = session,
                    round = round,
                    size = gameGridParams.buttonDiameter,
                    onClick = {
                        onUserSelected(resource)
                    }
                )


            }
        }

        // =============================================
        // TAP TO CONTINUE
        // =============================================
        if (round.result != null) {
            Log.d("AFTER_ROUND_TAP", "Draw tap-to-continue box")
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        indication = null,
                        interactionSource = remember {
                            MutableInteractionSource()
                        }
                    ) {
                        onNextRound()
                    }
            )
        }
    }



}

