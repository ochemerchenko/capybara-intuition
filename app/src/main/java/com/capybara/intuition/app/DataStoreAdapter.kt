package com.capybara.intuition.app

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreAdapter(
    context: Context
) {

    companion object {

        private const val DATA_STORE_NAME = "intuition"

        private val Context.dataStore by preferencesDataStore(
            name = DATA_STORE_NAME
        )
    }

    private val dataStore = context.dataStore
    private val keys = mutableMapOf<String, Preferences.Key<String>>()

    private fun key(
        name: String
    ): Preferences.Key<String> = keys.getOrPut(name) {
            stringPreferencesKey(name)
        }

    fun observe(
        key: String
    ): Flow<String?> = dataStore.data.map { preferences ->
            preferences[this.key(key)]
        }

    suspend fun edit(
        key: String,
        value: String
    ) {
        dataStore.edit { preferences ->
            preferences[this.key(key)] = value
        }
    }

    suspend fun delete(
        key: String
    ) {
        dataStore.edit { preferences ->
            preferences.remove(
                this.key(key)
            )
        }
    }
}