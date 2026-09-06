package com.capybara.intuition.services.bootstrap.domain.seeders.renderers

import android.content.Context
import com.capybara.intuition.features.game.data.local.db.dao.RendererDao
import com.capybara.intuition.features.game.data.local.db.entity.RendererEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RendererSeeder(

    private val context: Context,
    private val rendererDao: RendererDao

) {

    suspend fun seed() {

        val json = context.assets
                .open("bootstrap/renderers.json")
                .bufferedReader()
                .use { it.readText() }

        val dto: List<RendererDto> = Gson().fromJson(
                json,
                object : TypeToken<List<RendererDto>>() {}.type
            )

        val entities =
            dto.map {
                RendererEntity(
                    id = it.id,
                    code = it.code
                )
            }

        rendererDao.insertAll(
            entities
        )
    }
}

private data class RendererDto(

    val id: Short,
    val code: String

)