package com.capybara.intuition.app.bootstrap.installation.data.local.repository

class ReleaseNotesRepository(
    private val dataStore: ReleaseNotesDataStore
) {

    val releaseNotesVersionRead =
        dataStore.observeReadVersion()

    suspend fun completeOnboarding() {
        dataStore.setReadVersion(
            1
        )
    }
}