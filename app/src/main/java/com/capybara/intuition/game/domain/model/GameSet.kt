package com.capybara.intuition.game.domain.model




data class GameSet(

    /**
     * Semantic gameplay candidates
     * participating in current round.
     */
    val resources: List<Resource>
)