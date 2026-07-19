package com.capybara.intuition.app.composition.modules.profile

import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.game.data.repository.NotebookRepository

class ProfileResourcesModule (
    database: AppDatabase
) {

    val notebookDao = database.notebookDao()

    val notebookRepository =NotebookRepository(
        notebookDao = notebookDao
    )

}