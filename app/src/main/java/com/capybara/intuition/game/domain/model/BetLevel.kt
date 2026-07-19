package com.capybara.intuition.game.domain.model

data class BetLevel(val cardsCount: Int) {

    init {
        require(cardsCount in 2..12) {
            "cardsCount must be between 2 and 12"
        }
    }

    fun increase(): BetLevel =
        if (cardsCount < 12) BetLevel(cardsCount + 1) else this

    fun decrease(): BetLevel =
        if (cardsCount > 2) BetLevel(cardsCount - 1) else this

    companion object {
        fun initial() = BetLevel(2) // або будь-яке стартове значення
    }
}

