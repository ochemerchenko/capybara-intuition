package com.capybara.intuition.app.navigation.telemetry.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.capybara.intuition.app.navigation.telemetry.model.AppScreen

@Entity(
    tableName = "screen_transitions"
)
data class ScreenTransitionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val fromScreen: AppScreen?,
    
    val toScreen: AppScreen,
    
    val timestamp: Long
)
