package com.capybara.intuition.game.domain.events


import com.capybara.intuition.game.domain.model.Resource
import com.capybara.intuition.profile.domain.model.Profile

sealed interface GameEvent {

    // =====================================================
    // SESSION
    // =====================================================

    data class InitiateSessionCreation(
        val profile: Profile
    ) : GameEvent

    object SessionCreated : GameEvent

    // =====================================================
    // ROUND LIFECYCLE
    // =====================================================

    object InitiateRoundCreation : GameEvent

    object CreateRound : GameEvent

    object RoundCreated : GameEvent

    // =====================================================
    // ROUND FLOW
    // =====================================================

    object ShowGameSet : GameEvent

    object PlaceSystemBet : GameEvent

    data class PlaceUserBet(

        val selectedResource:
        Resource

    ) : GameEvent

    // =====================================================
    // RESOLUTION
    // =====================================================

    object ResolveRound : GameEvent

    object RoundFinished : GameEvent

    // =====================================================
    // CONTINUATION
    // =====================================================

    object ContinueRoundFlow : GameEvent
}

/*
package com.capybara.intuition.game.domain.events


import com.capybara.intuition.game.domain.model.GameMode
import com.capybara.intuition.game.domain.resource.ResourceDescriptor
import com.capybara.intuition.game.domain.resource.ResourceType
import com.capybara.intuition.game.domain.stimulus.MaskType
import com.capybara.intuition.game.domain.stimulus.RendererCategory
import com.capybara.intuition.game.presentation.model.Position
import com.capybara.intuition.profile.domain.model.Profile
import com.capybara.intuition.visual.domain.model.VisualConfig


sealed interface GameEvent {

    // =====================================================
    // SESSION
    // =====================================================
    data class InitiateSessionCreation(

        // =====================================================
        // PLAYER
        // =====================================================
        val profile: Profile,

        // =====================================================
        // GAMEPLAY
        // =====================================================
        val mode: GameMode? = null,
        val level: Int? = null,
        val notebookId: String? = null,
        val resourceType: ResourceType? = null,

        // =====================================================
        // RENDER ENVIRONMENT
        // =====================================================
        val rendererCategory: RendererCategory? = null,
        val maskType: MaskType? = null

    ) : GameEvent

    // =====================================================
    // ROUND
    // =====================================================
    object InitiateRoundCreation : GameEvent

    // =====================================================
    // ROUND
    // =====================================================

    object RoundCreated : GameEvent
    object ShowGameSet : GameEvent
    object PlaceSystemBet : GameEvent
    data class PlaceUserBet(
        val selectedResource: ResourceDescriptor,
        val selectedPosition: Position
    ) : GameEvent

    object ResolveRound : GameEvent
    object RoundFinished : GameEvent
}


 */