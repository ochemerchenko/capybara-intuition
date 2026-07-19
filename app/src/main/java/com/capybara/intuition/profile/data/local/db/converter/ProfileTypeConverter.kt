package com.capybara.intuition.profile.data.local.db.converter

import androidx.room.TypeConverter
import com.capybara.intuition.profile.domain.model.ProfileType

class ProfileTypeConverter {

    @TypeConverter
    fun fromProfileType(
        type: ProfileType
    ): String {

        return type.name
    }

    @TypeConverter
    fun toProfileType(
        value: String
    ): ProfileType {

        return ProfileType.valueOf(value)
    }
}