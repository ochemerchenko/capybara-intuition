package com.capybara.intuition.statistics.presentation.viewmodel

import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capybara.intuition.game.domain.model.GameMode
import com.capybara.intuition.statistics.data.repository.StatisticsRepository
import com.capybara.intuition.statistics.presentation.model.SessionFilter
import com.capybara.intuition.statistics.charts.model.data.DayDensity
import com.capybara.intuition.statistics.charts.model.data.PreviewByLevelRequest
import com.capybara.intuition.statistics.charts.model.data.PreviewByLevelResponse
import com.capybara.intuition.statistics.charts.model.data.PreviewByModeRequest
import com.capybara.intuition.statistics.charts.model.data.PreviewByModeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StatisticsViewModel(

    private val statisticsRepository: StatisticsRepository,


) : ViewModel() {

    private val _dailyDensities = MutableStateFlow(
        emptyList<DayDensity>()
    )

    private val _dailyDensitiesPerLevel = MutableStateFlow<PreviewByLevelResponse?>(null)
    val dailyDensitiesPerLevel: StateFlow<PreviewByLevelResponse?> = _dailyDensitiesPerLevel
    private val _dailyDensitiesPerMode = MutableStateFlow<PreviewByModeResponse?>(null)
    val dailyDensitiesPerMode: StateFlow<PreviewByModeResponse?> = _dailyDensitiesPerMode


    fun loadAnalyticalGraph(
        profileId: Int,
        sessionMode: GameMode?,
        level: Int,
        noiseLevel: Int,
        fromTimestamp: Long,
        toTimestamp: Long,
    ) {

        viewModelScope.launch {
            val dayDensities = statisticsRepository.getDayDensities(
                profileId = profileId,
                sessionMode = sessionMode,
                level = level,
                fromTimestamp = fromTimestamp,
                toTimestamp = toTimestamp,
                noiseLevel = noiseLevel,
            )
            _dailyDensities.value = dayDensities

            Log.d(
                "ANALYTICS",
                "dayDensities=$dayDensities"

            )
        }
    }

    fun loadDailyDensitiesPerMode(
        previewByModeRequest: PreviewByModeRequest
    ) {
        viewModelScope.launch {
            _dailyDensitiesPerMode.value =
                statisticsRepository.getPreviewByMode(
                    previewByModeRequest
                )
        }
    }

    fun loadDailyDensitiesPerLevel(
        previewByLevelRequest: PreviewByLevelRequest
    ) {
        viewModelScope.launch {
            _dailyDensitiesPerLevel.value =
                statisticsRepository.getPreviewByLevel(
                    previewByLevelRequest
                )
        }
    }


    fun sessionFilterToGameMode (
        uiState: SessionFilter
    ): GameMode? {
        return when (uiState) {
            SessionFilter.AFTER -> GameMode.AFTER
            SessionFilter.BEFORE -> GameMode.BEFORE
            SessionFilter.HYBRID -> GameMode.HYBRID
            SessionFilter.ALL -> null
        }
    }


}