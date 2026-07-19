package com.capybara.intuition.app.bootstrap.colors

import android.content.Context
import com.capybara.intuition.game.data.local.db.dao.NotebookDao
import com.capybara.intuition.game.data.local.db.entity.NotebookEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotebookSeeder(
    private val context: Context,
    private val notebookDao: NotebookDao
) {

    suspend fun seedIfEmpty() {

        withContext(
            Dispatchers.IO
        ) {
            val existing = notebookDao.getAll()

            if (
                existing.isNotEmpty()
            ) {
                return@withContext
            }

            val json = context.assets
                    .open(
                        "bootstrap/notebooks.json"
                    )
                    .bufferedReader()
                    .use {
                        it.readText()
                    }

            val type =
                object : TypeToken<
                        List<NotebookEntity>
                        >() {}.type

            val notebooks: List<NotebookEntity> =
                Gson().fromJson(
                    json,
                    type
                )

            notebookDao.insertAll(
                notebooks
            )
        }
    }
}