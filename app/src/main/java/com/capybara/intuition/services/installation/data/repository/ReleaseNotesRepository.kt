package com.capybara.intuition.services.installation.data.repository


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