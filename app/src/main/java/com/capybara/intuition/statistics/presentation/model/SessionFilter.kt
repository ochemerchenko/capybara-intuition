package com.capybara.intuition.statistics.presentation.model

import com.capybara.intuition.game.domain.model.GameMode

enum class SessionFilter(
    val gameMode: GameMode?
) {

    AFTER(GameMode.AFTER),
    BEFORE(GameMode.BEFORE),
    HYBRID(GameMode.HYBRID),
    ALL(null);

    companion object {

        fun fromGameMode(
            gameMode: GameMode?
        ): SessionFilter =
            entries.first {
                it.gameMode == gameMode
            }
    }
}

