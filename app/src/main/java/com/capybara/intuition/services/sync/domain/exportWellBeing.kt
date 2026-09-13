package com.capybara.intuition.services.sync.domain

import android.content.Context
import com.capybara.intuition.core.data.DataStoreAdapter
import com.capybara.intuition.features.wellbeing.data.local.db.dao.WellbeingDao
import com.capybara.intuition.services.installation.data.local.db.dao.InstallationDao
import com.capybara.intuition.services.sync.data.ExportDataStore
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

suspend fun exportWellBeing (
    context: Context,
    installationDao: InstallationDao,
    wellbeingDao: WellbeingDao,
    dataStoreAdapter: DataStoreAdapter
) {
    val wellbeingExportStore = ExportDataStore(
        dataStoreAdapter,
        keySuffix = "wellbeing"
    )

    val installation = installationDao.getInstallation()

    val lastConfirmedExportDate = wellbeingExportStore.getLastConfirmedExportDate(
        defaultDate = Instant
            .ofEpochMilli(installation.installedAt)
            .atZone(ZoneOffset.UTC)
            .toLocalDate()
    )

    val today = LocalDate.now(ZoneOffset.UTC)

    val startExportPeriod = lastConfirmedExportDate.plusDays(1)
    val endExportPeriod = today.minusDays(1)

    var exportDate = startExportPeriod
    while (exportDate <= endExportPeriod) {

        val dayStart = exportDate
            .atStartOfDay(ZoneOffset.UTC)
            .toInstant()
            .toEpochMilli()

        val dayEnd = exportDate
            .plusDays(1)
            .atStartOfDay(ZoneOffset.UTC)
            .toInstant()
            .toEpochMilli()

        val records = wellbeingDao.getRecordsForExport(
            dayStart = dayStart,
            dayEnd = dayEnd
        )

        val jsonObject = JsonObject().apply {
            addProperty("installationId", installation.id)
            add("wellbeingRecords", Gson().toJsonTree(records))
        }

        archivateExport(
            context = context,
            installationId = installation.id,
            exportType = "wellbeing",
            objectKey = exportDate.toString(),
            data = jsonObject
        )

        wellbeingExportStore.setLastConfirmedExportDate(exportDate)

        exportDate = exportDate.plusDays(1)
    }
}
