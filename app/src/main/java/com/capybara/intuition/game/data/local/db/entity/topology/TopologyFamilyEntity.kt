package com.capybara.intuition.game.data.local.db.entity.topology

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "topology_families"
)
data class TopologyFamilyEntity(

    @PrimaryKey
    val id: Long,
    val code: String

)