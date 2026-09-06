package com.capybara.intuition.features.game.domain.router

import com.capybara.intuition.features.game.data.repository.NotebookRepository

data class RuntimeDependencies(
    val notebookRepository: NotebookRepository
)