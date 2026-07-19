package com.capybara.intuition.statistics.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capybara.intuition.app.license.data.repository.LicenseRepository
import com.capybara.intuition.license.domain.LicenseType
import com.capybara.intuition.game.domain.model.GameMode
import com.capybara.intuition.statistics.data.repository.StatisticsRepository
import com.capybara.intuition.statistics.presentation.model.SessionFilter
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data.DayDensity
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data.PreviewByLevelRequest
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data.PreviewByLevelResponse
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data.PreviewByModeRequest
import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data.PreviewByModeResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StatisticsViewModel(

    private val statisticsRepository: StatisticsRepository,
    private val licenseRepository: LicenseRepository

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


    var licenseType by mutableStateOf(
        LicenseType.FREE
    )
        private set

    init {
        observeLicenseChanges()
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

    private fun observeLicenseChanges() {
        viewModelScope.launch {
            licenseRepository
                .currentLicense
                .collectLatest {
                    licenseType = it
                }
        }

    }
}