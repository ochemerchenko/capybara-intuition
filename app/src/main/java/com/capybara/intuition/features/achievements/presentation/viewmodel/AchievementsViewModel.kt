package com.capybara.intuition.features.achievements.presentation.viewmodel

import com.capybara.intuition.features.achievements.data.repository.AchievementsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capybara.intuition.features.achievements.domain.model.Achievement
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AchievementsViewModel (
    private val achievementsRepository: AchievementsRepository
): ViewModel() {

    val records: StateFlow<List<Achievement>> = achievementsRepository
        .getRecords()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val regularities: StateFlow<List<Achievement>> = achievementsRepository
        .getRegularities()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val skills: StateFlow<List<Achievement>> = achievementsRepository
        .getSkills()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val milestones: StateFlow<List<Achievement>> = achievementsRepository
        .getMilestones()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun markAllAsSeen() {
        viewModelScope.launch {
            achievementsRepository.markAllAsSeen()
        }
    }

}