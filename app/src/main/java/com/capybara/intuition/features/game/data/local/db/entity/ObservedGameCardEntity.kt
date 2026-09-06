package com.capybara.intuition.features.game.data.local.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "round_cards",
    foreignKeys = [
        ForeignKey(
            entity = ObservedRoundEntity::class,
            parentColumns = ["id"],
            childColumns = ["roundId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ResourceEntity::class,
            parentColumns = ["id"],
            childColumns = ["resourceId"]
        )
    ],

    indices = [
        Index("roundId"),
        Index("resourceId")
    ]
)
data class ObservedGameCardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val roundId: Long,
    val resourceId: Short,
    val topologyCellIndex: Int,
    val isCorrect: Boolean,
    val isSelected: Boolean
)