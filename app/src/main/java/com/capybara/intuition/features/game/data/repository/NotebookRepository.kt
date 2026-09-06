package com.capybara.intuition.features.game.data.repository

import com.capybara.intuition.features.game.data.local.db.dao.NotebookDao
import com.capybara.intuition.features.game.domain.model.Resource

class NotebookRepository(

    private val notebookDao: NotebookDao

) {

    suspend fun getResources(
        notebookId: Short
    ): List<Resource> {

        val notebook = notebookDao
            .getNotebookWithResources(
                id = notebookId
            )

        return notebook
            ?.resources
            ?.map {
                Resource(
                    id = it.id,
                    name = it.name,
                    type = it.type,
                    value = it.value
                )
            } ?: emptyList()
    }
}