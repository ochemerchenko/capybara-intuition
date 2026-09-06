package com.capybara.intuition.features.profile.presentation.viewmodel

import com.capybara.intuition.services.database.data.DatabaseRetentionRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capybara.intuition.services.database.model.DatabaseRetentionPeriod
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: DatabaseRetentionRepository
) : ViewModel() {

    val retentionPeriod: StateFlow<DatabaseRetentionPeriod> =
        repository
            .retentionDays
            .map { days ->
                when (days) {
                    90L -> DatabaseRetentionPeriod.DAYS_90
                    180L -> DatabaseRetentionPeriod.DAYS_180
                    360L -> DatabaseRetentionPeriod.DAYS_360
                    else -> DatabaseRetentionPeriod.FOREVER
                }
            }
            .stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                DatabaseRetentionPeriod.DAYS_360
            )

    fun setRetentionPeriod(
        period: DatabaseRetentionPeriod
    ) {
        viewModelScope.launch {
            repository.setRetentionDays(
                when (period) {
                    DatabaseRetentionPeriod.DAYS_90 -> 90L
                    DatabaseRetentionPeriod.DAYS_180 -> 180L
                    DatabaseRetentionPeriod.DAYS_360 -> 360L
                    DatabaseRetentionPeriod.FOREVER -> Long.MAX_VALUE
                }
            )
        }
    }
}