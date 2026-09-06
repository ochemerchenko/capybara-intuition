package com.capybara.intuition.features.profile.di

import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.features.game.data.repository.NotebookRepository

class ProfileResourcesModule (
    database: AppDatabase
) {

    val notebookDao = database.notebookDao()

    val notebookRepository =NotebookRepository(
        notebookDao = notebookDao
    )

}