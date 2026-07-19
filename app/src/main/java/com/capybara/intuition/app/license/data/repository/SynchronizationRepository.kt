package com.capybara.intuition.app.license.data.repository

import com.capybara.intuition.app.license.data.datastore.SynchronizationDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SynchronizationRepository(
    private val synchronizationDataStore: SynchronizationDataStore
) {

    private val scope = CoroutineScope(
        SupervisorJob() + Dispatchers.IO
    )

    private val _lastBillingSynchronization =
        MutableStateFlow<Long?>(null)

    val lastBillingSynchronization: StateFlow<Long?> =
        _lastBillingSynchronization.asStateFlow()

    init {
        scope.launch {
            synchronizationDataStore
                .observeLastBillingSynchronization()
                .collect { time ->
                    _lastBillingSynchronization.value = time
                }
        }
    }

    suspend fun setLastBillingSynchronization(
        time: Long
    ) {
        synchronizationDataStore.editLastBillingSynchronization(
            time
        )
    }
}