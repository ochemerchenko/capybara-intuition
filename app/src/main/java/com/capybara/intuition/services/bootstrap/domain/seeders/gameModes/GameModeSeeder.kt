package com.capybara.intuition.services.bootstrap.domain.seeders.gameModes

import android.content.Context
import com.capybara.intuition.features.game.data.local.db.dao.GameModeDao
import com.capybara.intuition.features.game.data.local.db.entity.GameModeEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GameModeSeeder(

    private val context: Context,
    private val gameModeDao: GameModeDao

) {

    suspend fun seed() {

        if (gameModeDao.count() > 0) return

        val json = context.assets
            .open("bootstrap/game_modes.json")
            .bufferedReader()
            .use { it.readText() }

        val dto: List<GameModeDto> =
            Gson().fromJson(
                json,
                object : TypeToken<List<GameModeDto>>() {}.type
            )

        gameModeDao.insertAll(
            dto.map {
                GameModeEntity(
                    id = it.id,
                    code = it.code
                )
            }
        )
    }
}

private data class GameModeDto(
    val id: Short,
    val code: String
)