package com.capybara.intuition.features.game.domain.router

import com.capybara.intuition.features.game.domain.events.GameEvent
import com.capybara.intuition.features.game.domain.handlers.HandlerResult
import com.capybara.intuition.features.game.domain.handlers.handleCreateRound
import com.capybara.intuition.features.game.domain.handlers.handleCreateSession
import com.capybara.intuition.features.game.domain.handlers.handlePlaceSystemBet
import com.capybara.intuition.features.game.domain.handlers.handlePlaceUserBet
import com.capybara.intuition.features.game.domain.handlers.handleResolveRound
import com.capybara.intuition.features.game.domain.model.GameContext
import com.capybara.intuition.features.game.domain.model.RoundType


class GameFlowRouter {

    // =====================================================
    // ROUTING
    // =====================================================

    suspend fun route(
        state: GameContext,
        event: GameEvent,
        runtimeDependencies: RuntimeDependencies,
        callback: suspend (
            GameContext,
            GameEvent?
        ) -> Unit
    ) {

        // =================================================
        // HANDLE EVENT
        // =================================================

        val handlerResult: HandlerResult =

            when (event) {

                // =========================================
                // CREATE SESSION
                // =========================================

                is GameEvent
                .InitiateSessionCreation -> {
                    handleCreateSession(
                        profile = event.profile,
                        notebookRepository = runtimeDependencies.notebookRepository,
                    )
                }

                // =========================================
                // ROUND CREATION
                // =========================================


                is GameEvent
                .CreateRound -> {

                    handleCreateRound(
                        state = state
                    )
                }

                // =========================================
                // PLACE SYSTEM BET
                // =========================================

                is GameEvent
                .PlaceSystemBet -> {

                    handlePlaceSystemBet(
                        state = state
                    )
                }

                // =========================================
                // USER BET
                // =========================================

                is GameEvent
                .PlaceUserBet -> {

                    handlePlaceUserBet(
                        state = state,
                        selectedResource =
                            event.selectedResource
                    )
                }

                // =========================================
                // ROUND RESOLUTION
                // =========================================

                is GameEvent
                .ResolveRound -> {

                    handleResolveRound(
                        state = state
                    )
                }

                // =========================================
                // PASSIVE EVENTS
                // =========================================

                is GameEvent
                .InitiateRoundCreation,

                is GameEvent
                .SessionCreated,

                is GameEvent
                .RoundCreated,

                is GameEvent
                .ShowGameSet,

                is GameEvent
                .RoundFinished,

                is GameEvent
                .ContinueRoundFlow -> {

                    HandlerResult(
                        state = state
                    )
                }
            }

        // =================================================
        // UPDATED STATE
        // =================================================

        val updatedState =
            handlerResult.state

        val round =
            updatedState.currentRound

        // =================================================
        // DETERMINE NEXT EVENT
        // =================================================

        val nextEvent =

            when (event) {

                // =========================================
                // SESSION CREATED
                // =========================================

                is GameEvent
                .InitiateSessionCreation -> {

                    GameEvent.SessionCreated
                }

                // =====================================
                // START FIRST ROUND
                // =====================================

                is GameEvent
                .SessionCreated -> {

                    GameEvent
                        .InitiateRoundCreation
                }

                // =========================================
                // CONTINUE FLOW
                // =========================================

                is GameEvent
                .ContinueRoundFlow -> {

                    GameEvent
                        .InitiateRoundCreation
                }

                // =========================================
                // ROUND CREATION
                // =========================================

                is GameEvent
                .InitiateRoundCreation -> {

                    GameEvent
                        .CreateRound
                }

                is GameEvent
                .CreateRound -> {

                    GameEvent
                        .RoundCreated
                }

                is GameEvent
                .RoundCreated -> {

                    val createdRound =

                        requireNotNull(round) {
                            "Round expected after creation"
                        }

                    when (

                        createdRound.roundType

                    ) {

                        // =================================
                        // AFTER
                        // =================================

                        RoundType.AFTER -> {

                            GameEvent
                                .PlaceSystemBet
                        }

                        // =================================
                        // BEFORE
                        // =================================

                        RoundType.BEFORE -> {

                            GameEvent
                                .ShowGameSet
                        }
                    }
                }

                // =========================================
                // SYSTEM BET
                // =========================================

                is GameEvent
                .PlaceSystemBet -> {

                    when (

                        round?.roundType

                    ) {

                        // =================================
                        // AFTER
                        // =================================

                        RoundType.AFTER -> {

                            GameEvent
                                .ShowGameSet
                        }

                        // =================================
                        // BEFORE
                        // =================================

                        RoundType.BEFORE -> {

                            GameEvent
                                .ResolveRound
                        }

                        null -> {
                            null
                        }
                    }
                }

                // =========================================
                // Game Set
                // =========================================

                is GameEvent
                .ShowGameSet -> {

                    null
                }

                // =========================================
                // USER BET
                // =========================================

                is GameEvent
                .PlaceUserBet -> {

                    when (

                        round?.roundType

                    ) {

                        // =================================
                        // AFTER
                        // =================================

                        RoundType.AFTER -> {

                            GameEvent
                                .ResolveRound
                        }

                        // =================================
                        // BEFORE
                        // =================================

                        RoundType.BEFORE -> {

                            GameEvent
                                .PlaceSystemBet
                        }

                        null -> {
                            null
                        }
                    }
                }

                // =========================================
                // RESOLUTION
                // =========================================

                is GameEvent
                .ResolveRound -> {

                    GameEvent
                        .RoundFinished
                }

                // =========================================
                // ROUND FINISHED
                // =========================================

                is GameEvent
                .RoundFinished -> {

                    null
                }
            }

        // =================================================
        // CALLBACK
        // =================================================

        callback(
            updatedState,
            nextEvent
        )
    }
}


