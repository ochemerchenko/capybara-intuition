package com.capybara.intuition.app.composition.modules

import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.game.data.repository.GameRoundRepository
import com.capybara.intuition.game.data.repository.GameSessionRepository
import com.capybara.intuition.game.domain.router.GameFlowRouter

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