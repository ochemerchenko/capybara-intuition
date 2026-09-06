package com.capybara.intuition.app.composition.modules

import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.app.navigation.telemetry.data.repository.TelemetryRepository

class TelemetryModule(
    database: AppDatabase
) {
    val telemetryRepository = TelemetryRepository(
        telemetryDao = database.telemetryDao()
    )
}
