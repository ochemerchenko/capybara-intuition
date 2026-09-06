package com.capybara.intuition.services.installation.data.repository

import com.capybara.intuition.BuildConfig

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