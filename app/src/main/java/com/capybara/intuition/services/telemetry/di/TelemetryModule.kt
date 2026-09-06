package com.capybara.intuition.services.telemetry.di

import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.services.telemetry.data.repository.TelemetryRepository

class TelemetryModule(
    database: AppDatabase
) {
    val telemetryRepository = TelemetryRepository(
        telemetryDao = database.telemetryDao()
    )
}
