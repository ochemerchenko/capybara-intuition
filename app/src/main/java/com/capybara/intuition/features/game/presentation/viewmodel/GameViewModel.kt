
package com.capybara.intuition.features.game.presentation.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capybara.intuition.features.game.data.repository.GameRoundRepository
import com.capybara.intuition.features.game.data.repository.GameSessionRepository
import com.capybara.intuition.features.game.data.repository.NotebookRepository
import com.capybara.intuition.features.game.domain.events.GameEvent
import com.capybara.intuition.features.game.domain.model.GameContext
import com.capybara.intuition.features.game.domain.model.GameSession
import com.capybara.intuition.features.game.domain.model.Resource
import com.capybara.intuition.features.game.domain.round.GameRound
import com.capybara.intuition.features.game.domain.router.GameFlowRouter
import com.capybara.intuition.features.game.domain.router.RuntimeDependencies
import com.capybara.intuition.features.profile.data.repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GameViewModel(
    private val profileRepository: ProfileRepository,
    private val sessionRepository: GameSessionRepository,
    private val roundRepository: GameRoundRepository,
    private val notebookRepository: NotebookRepository,
    private val gameFlowRouter: GameFlowRouter,

) : ViewModel() {

    // =====================================================
    // STATE
    // =====================================================

    private val runtimeDependencies =
        RuntimeDependencies(
            notebookRepository =
                notebookRepository
        )

    private val _state = MutableStateFlow(
        GameContext(
            session = null,
            currentRound = null,
            roundIndex = 0
        )
    )
    val state: StateFlow<GameContext> = _state.asStateFlow()


    // =====================================================
    // EVENT LOOP
    // =====================================================



    suspend fun submit(
        event: GameEvent
    ) {
        gameFlowRouter.route(
            state = _state.value,
            event = event,
            runtimeDependencies = runtimeDependencies,
            callback = ::callbackReceiver,
        )
    }

    suspend fun callbackReceiver(
        updatedState: GameContext,
        nextEvent: GameEvent?
    ) {
        _state.value = updatedState

        when (nextEvent) {

            GameEvent.SessionCreated -> {

                val session = updatedState.session ?: return
                val sessionId = persistSession(session)
                _state.value = updatedState.copy(
                    session = session.copy(
                        id = sessionId
                    )
                )
            }




            GameEvent.RoundFinished -> {
                updatedState.currentRound?.let {
                    persistRound(it)
                }
            }
            else -> Unit // нічого не робимо
        }




        if (nextEvent != null) {
            submit(nextEvent)
        }
    }


    // =====================================================
    // SESSION
    // =====================================================


    fun startSession() {
        Log.d("SUBMIT", "startSession() entered")

        viewModelScope.launch(Dispatchers.IO) {

            Log.d("SUBMIT", "Before getProfiles()")

            val profiles = profileRepository
                .getProfiles()
                .first()

            Log.d("SUBMIT", "Profiles count = ${profiles.size}")

            profiles.forEach {
                Log.d("SUBMIT", "Profile: id=${it.id}, active=${it.active}, type=${it.type}")
            }

            val profile = profiles.firstOrNull { it.active }

            Log.d("SUBMIT", "Active profile = $profile")

            if (profile == null) {
                Log.e("SUBMIT", "No active profile")
                return@launch
            }

            Log.d("SUBMIT", "Submitting InitiateSessionCreation")

            submit(
                GameEvent.InitiateSessionCreation(profile)
            )

            Log.d("SUBMIT", "submit() finished")
        }
    }

    // =====================================================
    // USER INPUT
    // =====================================================

    suspend fun onUserSelected(
        resource: Resource
    ) {
        Log.d("SUBMIT", "At gameViewModel.onUserSelected, gameState = $state")
        submit(
            GameEvent.PlaceUserBet(
                selectedResource = resource
            )
        )
    }

    suspend fun onMainButtonClicked() {
        submit(
            GameEvent
                .ContinueRoundFlow
        )
    }


    private suspend fun persistSession(
        session: GameSession
    ): Long {

        return withContext(Dispatchers.IO) {
            sessionRepository.insert(session)
        }
    }

    private fun persistRound(
        round: GameRound
    ) {
        Log.d("SUBMIT","gameViewModel: persistRound called")
        val session = _state.value.session ?: return
        viewModelScope.launch(
            Dispatchers.IO
        ) {
            roundRepository.insert(
                round = round,
                sessionId = session.id,
                level = session.profile.currentLevel,
                mode = session.profile.preferredMode.name
            )
        }
    }
}





