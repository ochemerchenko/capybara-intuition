package com.capybara.intuition.achievements.data.local.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import com.capybara.intuition.profile.data.local.db.entity.ProfileEntity

@Entity(
    tableName = "profile_achievements",
    primaryKeys = ["profileId", "achievementId"],
    foreignKeys = [
        ForeignKey(
            entity = ProfileEntity::class,
            parentColumns = ["id"],
            childColumns = ["profileId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = AchievementEntity::class,
            parentColumns = ["id"],
            childColumns = ["achievementId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ProfileAchievementEntity(
    val profileId: Int,
    val achievementId: Int,
    val value: Long,
    val new: Boolean
)