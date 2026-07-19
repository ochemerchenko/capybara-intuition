package com.capybara.intuition.game.data.local.db.entity.topology

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "topologies"
)
data class TopologyEntity(

    @PrimaryKey
    val id: Short,
    val code: String,
    val spaceWidth: Int,
    val spaceHeight: Int,
    val occupancyCapacity: Int,
    val horizontalAlignment: String,
    val verticalAlignment: String,
    val scaleMode: String

)