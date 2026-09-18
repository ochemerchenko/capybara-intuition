package com.capybara.intuition.services.installation.data.repository

import com.capybara.intuition.core.data.DataStoreAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ReleaseNotesDataStore(
    private val dataStoreAdapter: DataStoreAdapter
) {

    companion object {
        private const val KEY = "release_notes_version_read"
    }

    fun observeReadVersion(): Flow<Int> =
        dataStoreAdapter.observe(KEY)
            .map { it?.toIntOrNull() ?: 0 }

    suspend fun setReadVersion(version: Int) {
        dataStoreAdapter.edit(
            key = KEY,
            value = version.toString()
        )
    }

    suspend fun clear() {
        dataStoreAdapter.delete(KEY)
    }
}