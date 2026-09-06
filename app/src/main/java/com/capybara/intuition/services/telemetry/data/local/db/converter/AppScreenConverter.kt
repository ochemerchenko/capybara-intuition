package com.capybara.intuition.services.telemetry.data.local.db.converter

import androidx.room.TypeConverter
import com.capybara.intuition.services.telemetry.model.AppScreen

class AppScreenConverter {

    @TypeConverter
    fun fromAppScreen(screen: AppScreen?): String? {
        return screen?.name
    }

    @TypeConverter
    fun toAppScreen(value: String?): AppScreen? {
        return value?.let {
            try {
                AppScreen.valueOf(it)
            } catch (e: IllegalArgumentException) {
                null
            }
        }
    }
}
