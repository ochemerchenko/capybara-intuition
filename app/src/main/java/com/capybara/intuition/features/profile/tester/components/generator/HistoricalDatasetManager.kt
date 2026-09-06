package com.capybara.intuition.features.profile.tester.components.generator
import android.util.Log
import com.capybara.intuition.features.profile.tester.model.HistoricalDatasetRequest
import com.capybara.intuition.features.profile.tester.repository.HistoricalDatasetRepository

class HistoricalDatasetManager(
    private val generator: HistoricalDatasetGenerator,
    private val repository: HistoricalDatasetRepository
) {
    suspend fun generate(
        request: HistoricalDatasetRequest
    ): Boolean {

        Log.d("GENERATOR", "Generated request retrieved at HistoricalDatasetManager.generate() = $request")

        val dataset = generator.generate(
            request.copy(
                lastSessionId = repository.getLastSessionId()
            )
        )

        return repository.save(
            dataset
        )
    }

    suspend fun clear(): Boolean {
        return repository.clear()
    }

}