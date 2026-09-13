package com.capybara.intuition.features.tester.di

import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.features.tester.repository.HistoricalDatasetRepository

class TesterModule(
    database: AppDatabase
) {
    val historicalDatasetRepository = HistoricalDatasetRepository(
        database = database
    )
}