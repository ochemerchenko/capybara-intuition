package com.capybara.intuition.services.bootstrap.domain.seeders.colors

import android.content.Context
import com.capybara.intuition.features.game.data.local.db.dao.ResourceDao
import com.capybara.intuition.features.game.data.local.db.entity.ResourceEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ResourceSeeder(
    private val context: Context,
    private val resourceDao: ResourceDao
) {

    suspend fun seedIfEmpty() {

        withContext(
            Dispatchers.IO
        ) {

            val existing =
                resourceDao.getAll()

            if (
                existing.isNotEmpty()
            ) {
                return@withContext
            }

            val json =
                context.assets
                    .open(
                        "bootstrap/resources.json"
                    )
                    .bufferedReader()
                    .use {
                        it.readText()
                    }

            val type =
                object : TypeToken<
                        List<ResourceEntity>
                        >() {}.type

            val resources: List<ResourceEntity> =
                Gson().fromJson(
                    json,
                    type
                )

            resourceDao.insertAll(
                resources
            )
        }
    }
}