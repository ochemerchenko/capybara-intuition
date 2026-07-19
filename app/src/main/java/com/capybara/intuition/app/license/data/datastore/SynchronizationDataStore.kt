package com.capybara.intuition.app.license.data.datastore

import com.capybara.intuition.app.DataStoreAdapter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SynchronizationDataStore(
    private val dataStore: DataStoreAdapter
) {

    companion object {
        const val LAST_BILLING_SYNCHRONIZATION_KEY =
            "last_billing_synchronization"
    }

    fun observeLastBillingSynchronization(): Flow<Long?> =
        dataStore.observe(LAST_BILLING_SYNCHRONIZATION_KEY)
            .map { it?.toLongOrNull() }

    suspend fun editLastBillingSynchronization(
        time: Long
    ) {
        dataStore.edit(
            key = LAST_BILLING_SYNCHRONIZATION_KEY,
            value = time.toString()
        )
    }
}