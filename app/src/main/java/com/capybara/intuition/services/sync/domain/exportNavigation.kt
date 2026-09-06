package com.capybara.intuition.services.sync.domain

import android.content.Context
import com.capybara.intuition.services.installation.data.local.db.dao.InstallationDao
import com.capybara.intuition.services.telemetry.data.repository.TelemetryRepository
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

suspend fun exportNavigation (
    context: Context,
    installationDao: InstallationDao,
    telemetryRepository: TelemetryRepository
) {
    val installation = installationDao.getInstallation()
    
    // Знаходимо найстаріший запис у БД
    val oldestTimestamp = telemetryRepository.getOldestTransitionTimestamp() ?: return
    
    var exportDate = Instant
        .ofEpochMilli(oldestTimestamp)
        .atZone(ZoneOffset.UTC)
        .toLocalDate()

    val yesterday = LocalDate.now(ZoneOffset.UTC).minusDays(1)

    // Йдемо по кожній добі, для якої є дані, до вчорашнього дня включно
    while (exportDate <= yesterday) {

        val dayStart = exportDate
            .atStartOfDay(ZoneOffset.UTC)
            .toInstant()
            .toEpochMilli()

        val dayEnd = exportDate
            .plusDays(1)
            .atStartOfDay(ZoneOffset.UTC)
            .toInstant()
            .toEpochMilli()

        val transitions = telemetryRepository.getTransitionsForExport(
            startTime = dayStart,
            endTime = dayEnd
        )

        // Створюємо файл для кожної дати, навіть якщо список переходів порожній []
        val jsonObject = JsonObject().apply {
            addProperty("installationId", installation.id)
            add("screenTransitions", Gson().toJsonTree(transitions))
        }

        archivateExport(
            context = context,
            installationId = installation.id,
            exportType = "navigation",
            objectKey = exportDate.toString(),
            data = jsonObject
        )

        // Очищаємо базу за цю добу ПІСЛЯ успішної архівації
        telemetryRepository.cleanup(dayEnd)

        exportDate = exportDate.plusDays(1)
    }
}
