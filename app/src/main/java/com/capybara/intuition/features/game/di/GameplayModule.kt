package com.capybara.intuition.features.game.di

import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.features.game.data.repository.GameRoundRepository
import com.capybara.intuition.features.game.data.repository.GameSessionRepository
import com.capybara.intuition.features.game.domain.router.GameFlowRouter

class GameplayModule (
    database: AppDatabase
) {

    val sessionDao = database.sessionDao()
    val roundDao = database.gameRoundDao()

    val gameSessionRepository = GameSessionRepository(
        dao = sessionDao
    )
    val gameRoundRepository = GameRoundRepository(
        dao = roundDao,
        database = database
    )

    val gameFlowRouter = GameFlowRouter()
}