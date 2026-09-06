package com.capybara.intuition.features.game.data.local.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.capybara.intuition.features.game.data.local.db.entity.topology.TopologyEntity
import com.capybara.intuition.features.profile.data.local.db.entity.ProfileEntity

@Entity(

    tableName = "sessions",

    foreignKeys = [

        ForeignKey(
            entity = ProfileEntity::class,
            parentColumns = ["id"],
            childColumns = ["profileId"]
        ),

        ForeignKey(
            entity = NotebookEntity::class,
            parentColumns = ["id"],
            childColumns = ["notebookId"]
        ),

        ForeignKey(
            entity = TopologyEntity::class,
            parentColumns = ["id"],
            childColumns = ["topologyId"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.RESTRICT
        ),

        ForeignKey(
            entity = RendererEntity::class,
            parentColumns = ["id"],
            childColumns = ["rendererId"]
        ),

        ForeignKey(
            entity = MaskEntity::class,
            parentColumns = ["id"],
            childColumns = ["maskId"]
        ),

        ForeignKey(
            entity = GameModeEntity::class,
            parentColumns = ["id"],
            childColumns = ["mode"]
        )
    ],
    indices = [
        Index("profileId"),
        Index("notebookId"),
        Index("topologyId"),
        Index("maskId"),
        Index("rendererId"),
        Index("mode")
    ]
)

data class GameSessionEntity(
    // =====================================================
    // IDENTITY
    // =====================================================
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val profileId: Int,

    // =====================================================
    // GAMEPLAY
    // =====================================================
    val mode: Short,
    val level: Int,

    // =====================================================
    // RESOURCE ENVIRONMENT
    // =====================================================
    val notebookId: Short,
    val topologyId: Short,

    // =====================================================
    // RENDER ENVIRONMENT
    // =====================================================
    val maskId: Short,
    val rendererId: Short,

    // =====================================================
    // TIMING
    // =====================================================
    val startedAt: Long,
    val finishedAt: Long?

)