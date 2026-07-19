package com.capybara.intuition.app.bootstrap.masks

import android.content.Context
import com.capybara.intuition.game.data.local.db.dao.MaskDao
import com.capybara.intuition.game.data.local.db.entity.MaskEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MaskSeeder(

    private val context: Context,
    private val maskDao: MaskDao

) {

    suspend fun seed() {

        val json =
            context.assets
                .open("bootstrap/masks.json")
                .bufferedReader()
                .use { it.readText() }

        val dto: List<MaskDto> = Gson().fromJson(
            json,
            object : TypeToken<List<MaskDto>>() {}.type
        )

        val entities = dto.map {
            MaskEntity(
                id = it.id,
                code = it.code
            )
        }

        maskDao.insertAll(
            entities
        )
    }
}

private data class MaskDto(

    val id: Short,
    val code: String

)