package com.capybara.intuition.features.game.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.capybara.intuition.features.game.domain.resource.ResourceType

@Entity(
    tableName = "resources"
)
data class ResourceEntity(

    /**
     * Canonical semantic identity.
     *
     * Examples:
     * - RED
     * - SKY_BLUE
     * - CAPYBARA_SLEEPY
     *
     * Immutable.
     */
    @PrimaryKey
    val id: Short,

    val name: String,

    /**
     * Perceptual resource type.
     */
    val type: ResourceType,

    /**
     * Resource payload.
     *
     * COLOR:
     *   #RRGGBB
     *
     * DRAWABLE:
     *   assets/path/image.webp
     */
    val value: String,

    /**
     * Null for shipped system resources.
     *
     * Non-null for user-created resources.
     */
    val createdByProfileId: String?,

    /**
     * Defines whether resource
     * can be modified by user.
     */
    val isEditable: Boolean
)

