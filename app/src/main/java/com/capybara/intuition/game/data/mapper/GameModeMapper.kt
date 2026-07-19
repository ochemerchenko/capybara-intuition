package com.capybara.intuition.game.data.mapper

import com.capybara.intuition.game.domain.model.GameMode

object GameModeMapper {

    fun fromId(id: Short): GameMode =
        when (id) {
            1.toShort() -> GameMode.AFTER
            2.toShort() -> GameMode.BEFORE
            3.toShort() -> GameMode.HYBRID
            else -> error("Unknown GameMode id: $id")
        }

    fun toId(mode: GameMode): Short =
        when (mode) {
            GameMode.AFTER -> 1
            GameMode.BEFORE -> 2
            GameMode.HYBRID -> 3
        }
}