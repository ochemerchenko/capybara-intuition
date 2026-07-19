package com.capybara.intuition.game.data.local.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "round_records",

    foreignKeys = [
        ForeignKey(
            entity = GameSessionEntity::class,

            parentColumns = ["id"],
            childColumns = ["sessionId"],

            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GameModeEntity::class,
            parentColumns = ["id"],
            childColumns = ["roundType"]
        )
    ],

    indices = [
        Index("sessionId"),
        Index("roundType")
    ]
)
data class ObservedRoundEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val sessionId: Long,
    val roundType: Short,
    val level: Int,

    val result: Boolean,

    val startedAt: Long,
    val userSelectedAt: Long?,
    val finishedAt: Long,
)
