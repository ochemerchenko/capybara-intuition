package com.capybara.intuition.game.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "masks"
)
data class MaskEntity(

    @PrimaryKey
    val id: Short,

    /**
     * Human-readable canonical mask name.
     */
    val code: String

)