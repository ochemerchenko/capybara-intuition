package com.capybara.intuition.features.game.data.local.db.entity.topology

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "topology_family_relations",
    primaryKeys = [
        "familyId",
        "topologyId"
    ],
    foreignKeys = [

        ForeignKey(
            entity = TopologyFamilyEntity::class,
            parentColumns = ["id"],
            childColumns = ["familyId"],
            onDelete = ForeignKey.CASCADE
        ),

        ForeignKey(
            entity = TopologyEntity::class,
            parentColumns = ["id"],
            childColumns = ["topologyId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("familyId"),
        Index("topologyId")
    ]
)


data class TopologyFamilyRelationEntity(

    val familyId: Long,
    val topologyId: Short

)