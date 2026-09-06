package com.capybara.intuition.features.profile.tester.components.generator

import com.capybara.intuition.app.BuildConfiguration
import com.capybara.intuition.services.license.domain.CapabilityEnvironment
import com.capybara.intuition.features.profile.tester.repository.HistoricalDatasetRepository

fun createHistoricalDatasetManager(
    historicalDatasetRepository: HistoricalDatasetRepository
): HistoricalDatasetManager {
    check(
        BuildConfiguration.environment == CapabilityEnvironment.TEST
    ) {
        "HistoricalDatasetManager is available only in TEST environment"
    }

    return HistoricalDatasetManager(
        generator = HistoricalDatasetGenerator(),
        repository = historicalDatasetRepository
    )
}