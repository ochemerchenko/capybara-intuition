package com.capybara.intuition.features.game.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_modes")
data class GameModeEntity(

    @PrimaryKey
    val id: Short,
    val code: String

)