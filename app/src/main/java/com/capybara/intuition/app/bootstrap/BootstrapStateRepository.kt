package com.capybara.intuition.app.bootstrap

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BootstrapStateRepository(
    private val bootstrapDataStore: BootstrapDataStore
) {

    private val scope = CoroutineScope(
        SupervisorJob() + Dispatchers.IO
    )

    private val _state =
        MutableStateFlow(BootstrapState.NOT_STARTED)

    val state: StateFlow<BootstrapState> =
        _state.asStateFlow()

    init {
        scope.launch {
            bootstrapDataStore
                .observeState()
                .collect {
                    _state.value = it
                }
        }
    }

    suspend fun setState(
        state: BootstrapState
    ) {
        bootstrapDataStore.editState(state)
    }
}