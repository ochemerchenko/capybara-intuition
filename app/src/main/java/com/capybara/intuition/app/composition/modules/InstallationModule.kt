package com.capybara.intuition.app.composition.modules

import android.content.Context
import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.app.DataStoreAdapter
import com.capybara.intuition.app.bootstrap.BootstrapDataStore
import com.capybara.intuition.app.bootstrap.BootstrapStateRepository
import com.capybara.intuition.app.bootstrap.installation.data.local.repository.InstallationRepository
import com.capybara.intuition.app.bootstrap.installation.data.local.repository.ReleaseNotesDataStore
import com.capybara.intuition.app.bootstrap.installation.data.local.repository.ReleaseNotesRepository
import com.capybara.intuition.app.bootstrap.installation.domain.installation.InstallationInitializer

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