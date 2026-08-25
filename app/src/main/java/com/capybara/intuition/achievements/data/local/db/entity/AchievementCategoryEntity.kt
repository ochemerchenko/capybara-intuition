package com.capybara.intuition.achievements.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "achievement_categories"
)
data class AchievementCategoryEntity(
    @PrimaryKey
    val id: Int,
    val value: String

)