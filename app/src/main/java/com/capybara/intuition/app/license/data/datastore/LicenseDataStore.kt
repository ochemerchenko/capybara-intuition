package com.capybara.intuition.license.data.datastore

import com.capybara.intuition.app.DataStoreAdapter
import com.capybara.intuition.license.domain.LicenseType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LicenseDataStore(
    private val dataStore: DataStoreAdapter
) {

    companion object {
        const val LICENSE_KEY = "license"
        const val LAST_CONFIRMED_LICENSE_KEY = "last_confirmed_license"
    }

    fun observe(
        key: String
    ): Flow<LicenseType?> =
        dataStore
            .observe(key)
            .map { value ->
                value?.let(LicenseType::valueOf)
            }

    suspend fun edit(
        key: String,
        license: LicenseType
    ) {
        dataStore.edit(
            key = key,
            value = license.name
        )
    }
}