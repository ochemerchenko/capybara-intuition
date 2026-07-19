package com.capybara.intuition.game.domain.router

import com.capybara.intuition.game.data.repository.NotebookRepository

data class RuntimeDependencies(
    val notebookRepository: NotebookRepository
)