package com.capybara.intuition.features.game.data.local.db.entity.topology

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "topology_cells",
    primaryKeys = [
        "topologyId",
        "index"
    ],
    foreignKeys = [
        ForeignKey(
            entity = TopologyEntity::class,
            parentColumns = ["id"],
            childColumns = ["topologyId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("topologyId")
    ]
)
data class TopologyCellEntity(

    val topologyId: Short,
    val index: Int,
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val blocked: Boolean

)