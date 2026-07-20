package com.capybara.intuition.app.bootstrap

import com.capybara.intuition.app.DataStoreAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BootstrapDataStore(
    private val dataStore: DataStoreAdapter
) {

    companion object {
        private const val KEY = "bootstrap_state"
    }

    fun observeState(): Flow<BootstrapState> =
        dataStore.observe(KEY)
            .map {
                it?.let(BootstrapState::valueOf)
                    ?: BootstrapState.NOT_STARTED
            }

    suspend fun editState(
        state: BootstrapState
    ) {
        dataStore.edit(
            KEY,
            state.name
        )
    }
}