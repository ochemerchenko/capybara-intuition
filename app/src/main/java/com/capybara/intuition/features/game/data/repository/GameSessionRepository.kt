package com.capybara.intuition.features.game.data.repository

import android.util.Log
import com.capybara.intuition.features.game.data.local.db.dao.SessionDao
import com.capybara.intuition.features.game.data.mapper.toEntity
import com.capybara.intuition.features.game.domain.model.GameSession


class GameSessionRepository (
    private val dao: SessionDao
) {
    suspend fun insert (
        session: GameSession
    ): Long {
        Log.d("SUBMIT","GameSessionRepository: insert called")
        return dao.insert (
            session.toEntity ()
        )
    }
}