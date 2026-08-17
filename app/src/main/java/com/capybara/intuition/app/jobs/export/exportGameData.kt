package com.capybara.intuition.app.jobs.export

import android.content.Context
import com.capybara.intuition.app.DataStoreAdapter
import com.capybara.intuition.app.bootstrap.installation.data.local.db.dao.InstallationDao
import com.capybara.intuition.game.data.local.db.dao.GameRoundDao
import com.capybara.intuition.game.data.local.db.dao.SessionDao
import com.capybara.intuition.license.data.datastore.LicenseDataStore
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

suspend fun exportGameData (
    context: Context,
    installationDao: InstallationDao,
    sessionDao: SessionDao,
    roundDao: GameRoundDao,
    dataStoreAdapter: DataStoreAdapter
) {

    val gameDataExportStore = ExportDataStore(
        dataStoreAdapter
    )

    val installation = installationDao.getInstallation()

    val lastConfirmedExportDate = gameDataExportStore.getLastConfirmedExportDate(
        defaultDate = Instant
            .ofEpochMilli(installation.installedAt)
            .atZone(ZoneOffset.UTC)
            .toLocalDate()
    )

    val today = LocalDate.now()

    val startExportPeriod = lastConfirmedExportDate.plusDays(1)
    val endExportPeriod = today.minusDays(1)

    var exportDate = startExportPeriod
    while (exportDate <= endExportPeriod) {

        // експортуємо exportDate
        val dayStart = exportDate
            .atStartOfDay(ZoneOffset.UTC)
            .toInstant()
            .toEpochMilli()

        val dayEnd = exportDate
            .plusDays(1)
            .atStartOfDay(ZoneOffset.UTC)
            .toInstant()
            .toEpochMilli()

        val sessions = sessionDao.getSessionsForExport(
            dayStart = dayStart,
            dayEnd = dayEnd
        )

        val rounds = roundDao.getRoundsForExport(
            dayStart = dayStart,
            dayEnd = dayEnd
        )

        val cards = roundDao.getGameCardsForExport(
            dayStart = dayStart,
            dayEnd = dayEnd
        )

        val jsonObject = JsonObject().apply {

            addProperty(
                "installationId",
                installation.id
            )

            add(
                "sessions",
                Gson().toJsonTree(sessions)
            )

            add(
                "rounds",
                Gson().toJsonTree(rounds)
            )

            add(
                "roundCards",
                Gson().toJsonTree(cards)
            )
        }

        archivateExport(
            context = context,
            installationId = installation.id,
            exportType = "gameData",
            objectKey = exportDate.toString(),
            data = jsonObject
        )

        gameDataExportStore.setLastConfirmedExportDate(exportDate)


        exportDate = exportDate.plusDays(1)
    }

}