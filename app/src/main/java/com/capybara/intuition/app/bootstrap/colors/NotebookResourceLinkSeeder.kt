package com.capybara.intuition.app.bootstrap.colors

import android.content.Context
import android.util.Log
import com.capybara.intuition.game.data.local.db.dao.NotebookResourceLinkDao
import com.capybara.intuition.game.data.local.db.entity.NotebookResourceLinkEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NotebookResourceLinkSeeder(
    private val context: Context,
    private val notebookResourceLinkDao:
    NotebookResourceLinkDao
) {

    suspend fun seedIfEmpty() {

        withContext(
            Dispatchers.IO
        ) {
            Log.d("BOOT", "NotebookResourceLinkSeeder START")
            if (notebookResourceLinkDao.count() > 0) {
                return@withContext
            }
            Log.d("BOOT", "Reading JSON")
            val json =
                context.assets
                    .open(
                        "bootstrap/notebook_resource_links.json"
                    )
                    .bufferedReader()
                    .use {
                        it.readText()
                    }

            Log.d("BOOT", "Create TypeToken")
            val type = object : TypeToken<List<NotebookResourceLinkEntity>>() {}.type
            Log.d("BOOT", "TypeToken created")
            val links: List<NotebookResourceLinkEntity> = Gson().fromJson(
                    json,
                    type
                )
            Log.d("BOOT", "Parsed ${links.size} links")

            Log.d("BOOT", "Before insertAll")


            try {
                Log.d("BOOT", "Before insertAll")

                notebookResourceLinkDao.insertAll(links)

                Log.d("BOOT", "After insertAll")

            } catch (e: Exception) {

                Log.e("BOOT", "insertAll failed", e)

                throw e
            }
            Log.d("BOOT", "After insertAll")
        }
    }
}