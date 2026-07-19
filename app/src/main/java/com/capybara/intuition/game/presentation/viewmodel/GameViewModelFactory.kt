package com.capybara.intuition.game.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.capybara.intuition.app.composition.AppContainer

class GameViewModelFactory(
    private val container: AppContainer
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (
            modelClass.isAssignableFrom(
                GameViewModel::class.java
            )
        ) {
            return GameViewModel(
                profileRepository = container.profile.profileRepository,
                sessionRepository = container.gameplay.gameSessionRepository,
                roundRepository = container.gameplay.gameRoundRepository,
                notebookRepository = container.profile.resources.notebookRepository,
                gameFlowRouter = container.gameplay.gameFlowRouter
            ) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}