package com.capybara.intuition.features.achievements.presentation.viewmodel

import com.capybara.intuition.features.achievements.data.repository.AchievementsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capybara.intuition.features.achievements.domain.model.LevelMetric
import com.capybara.intuition.services.installation.data.repository.InstallationRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit

class AchievementsViewModel (
    private val achievementsRepository: AchievementsRepository,
    private val installationRepository: InstallationRepository
): ViewModel() {

    private val today = LocalDate.now(ZoneOffset.UTC)

    val lastActiveDays: StateFlow<Long> = achievementsRepository
        .getLastActiveDays(today.toString())
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0L
        )

    val totalActiveDays: StateFlow<Long> = achievementsRepository
        .getTotalActiveDays(today.toString())
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0L
        )

    val maxSessionsPerLevel: StateFlow<List<LevelMetric>> = achievementsRepository
        .getMaxSessionsPerDayPerLevel(today.toString())
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val maxRoundsPerLevel: StateFlow<List<LevelMetric>> = achievementsRepository
        .getMaxRoundsPerSessionPerLevel(today.toString())
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val maxCorrectPerLevel: StateFlow<List<LevelMetric>> = achievementsRepository
        .getMaxCorrectPerSessionPerLevel(today.toString())
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val maxMissesPerLevel: StateFlow<List<LevelMetric>> = achievementsRepository
        .getMaxMissesPerSessionPerLevel(today.toString())
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val maxWinStreakPerLevel: StateFlow<List<LevelMetric>> = achievementsRepository
        .getMaxWinStreakPerSessionPerLevel(today.toString())
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val maxLoseStreakPerLevel: StateFlow<List<LevelMetric>> = achievementsRepository
        .getMaxLoseStreakPerSessionPerLevel(today.toString())
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val maxStreak: StateFlow<Long> = achievementsRepository
        .getMaxStreak(today.toString())
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0L
        )

    val maxBreak: StateFlow<Long> = achievementsRepository
        .getMaxBreak(today.toString())
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = 0L
        )

    val daysWithApp: StateFlow<Long> = flow {
        val installation = installationRepository.getInstallation()
        val installedDate = Instant.ofEpochMilli(installation.installedAt)
            .atZone(ZoneOffset.UTC)
            .toLocalDate()
        val days = ChronoUnit.DAYS.between(installedDate, today) + 1 
        emit(days)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = 0L
    )
}
