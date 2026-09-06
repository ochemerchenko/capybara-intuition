package com.capybara.intuition.app.jobs.export

import com.capybara.intuition.app.DataStoreAdapter
import java.time.LocalDate

class ExportDataStore(
    private val dataStore: DataStoreAdapter
) {

    companion object {
        const val LAST_CONFIRMED_EXPORT_DATE =
            "last_confirmed_export_date"
    }

    suspend fun getLastConfirmedExportDate(
        defaultDate: LocalDate
    ): LocalDate {
        val date = dataStore
            .get(LAST_CONFIRMED_EXPORT_DATE)
            ?.let(LocalDate::parse)

        if (date != null) {
            return date
        }

        val initialDate = defaultDate.minusDays(1)

        dataStore.edit(
            key = LAST_CONFIRMED_EXPORT_DATE,
            value = initialDate.toString()
        )

        return initialDate
    }

    suspend fun setLastConfirmedExportDate(
        date: LocalDate
    ) {
        dataStore.edit(
            key = LAST_CONFIRMED_EXPORT_DATE,
            value = date.toString()
        )
    }
}
