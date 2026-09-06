package com.capybara.intuition.features.game.domain.model

import com.capybara.intuition.features.game.domain.resource.ResourceType

data class Resource(

    /**
     * Canonical resource identity.
     */
    val id: Short,
    val name: String,
    val type: ResourceType,
    val value: String

)