package com.capybara.intuition.services.sync.domain

import android.content.Context
import com.capybara.intuition.features.profile.data.local.db.entity.ProfileEntity
import com.google.gson.JsonObject

suspend fun exportProfile (
    profileEntity: ProfileEntity,
    context: Context
){

    val jsonObject = JsonObject().apply {
        addProperty("id", profileEntity.id)
        addProperty("installationId", profileEntity.installationId)
        addProperty("type", profileEntity.type.name)
        addProperty("createdAt", profileEntity.createdAt)
    }

    archivateExport(
        context = context,
        installationId = profileEntity.installationId,
        exportType = "profile",
        objectKey = profileEntity.id.toString(),
        data = jsonObject
    )

}