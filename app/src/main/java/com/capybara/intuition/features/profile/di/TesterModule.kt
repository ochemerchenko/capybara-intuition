package com.capybara.intuition.features.profile.di

import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.features.profile.tester.repository.HistoricalDatasetRepository

class TesterModule(
    database: AppDatabase
) {
    val historicalDatasetRepository = HistoricalDatasetRepository(
        database = database
    )
}