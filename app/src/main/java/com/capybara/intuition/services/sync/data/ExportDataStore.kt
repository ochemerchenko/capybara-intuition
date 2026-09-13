package com.capybara.intuition.services.sync.data

import com.capybara.intuition.core.data.DataStoreAdapter
import java.time.LocalDate

class ExportDataStore(
    private val dataStore: DataStoreAdapter,
    private val keySuffix: String = ""
) {

    private val storageKey: String
        get() = if (keySuffix.isEmpty()) {
            LAST_CONFIRMED_EXPORT_DATE
        } else {
            "${LAST_CONFIRMED_EXPORT_DATE}_$keySuffix"
        }

    companion object {
        const val LAST_CONFIRMED_EXPORT_DATE =
            "last_confirmed_export_date"
    }

    suspend fun getLastConfirmedExportDate(
        defaultDate: LocalDate
    ): LocalDate {
        val date = dataStore
            .get(storageKey)
            ?.let(LocalDate::parse)

        if (date != null) {
            return date
        }

        val initialDate = defaultDate.minusDays(1)

        dataStore.edit(
            key = storageKey,
            value = initialDate.toString()
        )

        return initialDate
    }

    suspend fun setLastConfirmedExportDate(
        date: LocalDate
    ) {
        dataStore.edit(
            key = storageKey,
            value = date.toString()
        )
    }
}
