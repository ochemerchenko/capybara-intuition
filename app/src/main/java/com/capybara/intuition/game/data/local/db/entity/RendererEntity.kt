package com.capybara.intuition.game.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "renderers"
)
data class RendererEntity(

    @PrimaryKey
    val id: Short,

    /**
     * Human-readable canonical renderer name.
     */
    val code: String

)