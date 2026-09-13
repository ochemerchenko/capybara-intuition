package com.capybara.intuition.features.wellbeing.data.local.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.capybara.intuition.features.profile.data.local.db.entity.ProfileEntity

@Entity(
    tableName = "wellbeing_records",
    foreignKeys = [
        ForeignKey(
            entity = ProfileEntity::class,
            parentColumns = ["id"],
            childColumns = ["profileId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index("profileId")
    ]
)
data class WellbeingRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val profileId: Int,
    
    /**
     * Physical state (0-100)
     */
    val physical: Int,
    
    /**
     * Happiness level (0-100)
     */
    val happiness: Int,
    
    /**
     * Self-confidence level (0-100)
     */
    val confidence: Int,
    
    /**
     * Precise recording time (UTC)
     */
    val timestamp: Long
)
