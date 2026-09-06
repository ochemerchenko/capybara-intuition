package com.capybara.intuition.services.installation.di

import android.content.Context
import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.core.data.DataStoreAdapter
import com.capybara.intuition.services.bootstrap.data.BootstrapDataStore
import com.capybara.intuition.services.bootstrap.data.BootstrapStateRepository
import com.capybara.intuition.services.installation.data.repository.InstallationRepository
import com.capybara.intuition.services.installation.data.repository.ReleaseNotesDataStore
import com.capybara.intuition.services.installation.data.repository.ReleaseNotesRepository
import com.capybara.intuition.services.installation.domain.InstallationInitializer

class InstallationModule(
    database: AppDatabase,
    context: Context,
    dataStoreAdapter: DataStoreAdapter
) {

    val installationDao = database.installationDao()

    val installationRepository =
        InstallationRepository(
            installationDao
        )

    val installationInitializer =
        InstallationInitializer(
            installationRepository = installationRepository,
            context = context.applicationContext
        )

    val bootstrapDataStore =
        BootstrapDataStore(
            dataStore = dataStoreAdapter
        )

    val bootstrapStateRepository =
        BootstrapStateRepository(
            bootstrapDataStore = bootstrapDataStore
        )

    val releaseNotesDataStore =
        ReleaseNotesDataStore(
            dataStoreAdapter = dataStoreAdapter
        )

    val releaseNotesRepository =
        ReleaseNotesRepository(
            dataStore = releaseNotesDataStore
        )

}