package com.capybara.intuition.game.domain.model

enum class GameMode {
    AFTER,
    BEFORE,
    HYBRID;

    companion object {

        /**
         * Всі режими, які мають сенс для аналітики (без ALL)
         */
        fun analyticsModes(): List<GameMode> =
            listOf(AFTER, BEFORE, HYBRID)

        /**
         * З урахуванням "ALL" (через null, як у тебе зараз)
         */
        fun analyticsModesWithAll(): List<GameMode?> =
            listOf(null) + analyticsModes()
    }
}