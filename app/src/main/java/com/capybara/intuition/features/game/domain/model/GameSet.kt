package com.capybara.intuition.features.game.domain.model




data class GameSet(

    /**
     * Semantic gameplay candidates
     * participating in current round.
     */
    val resources: List<Resource>
)