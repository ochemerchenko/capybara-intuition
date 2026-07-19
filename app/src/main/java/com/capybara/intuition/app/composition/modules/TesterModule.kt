package com.capybara.intuition.app.composition.modules

import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.profile.tester.repository.HistoricalDatasetRepository

class TesterModule(
    database: AppDatabase
) {
    val historicalDatasetRepository = HistoricalDatasetRepository(
        database = database
    )
}