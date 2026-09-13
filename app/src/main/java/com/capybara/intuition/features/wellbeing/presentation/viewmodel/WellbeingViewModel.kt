package com.capybara.intuition.features.wellbeing.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.capybara.intuition.features.wellbeing.data.repository.WellbeingRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WellbeingViewModel(
    private val repository: WellbeingRepository
) : ViewModel() {

    private val _showDialog = MutableStateFlow(false)
    val showDialog = _showDialog.asStateFlow()

    private val _physical = MutableStateFlow(50)
    val physical = _physical.asStateFlow()

    private val _happiness = MutableStateFlow(50)
    val happiness = _happiness.asStateFlow()

    private val _confidence = MutableStateFlow(50)
    val confidence = _confidence.asStateFlow()

    /**
     * Checks if we need to show the dialog for the given profile today.
     * Initializes slider values with the latest available data or 50.
     */
    fun checkStatus(profileId: Int) {
        viewModelScope.launch {
            val alreadyReported = repository.hasRecordForToday(profileId)
            
            if (!alreadyReported) {
                val latestRecord = repository.getLatestRecord(profileId)
                if (latestRecord != null) {
                    _physical.value = latestRecord.physical
                    _happiness.value = latestRecord.happiness
                    _confidence.value = latestRecord.confidence
                } else {
                    _physical.value = 50
                    _happiness.value = 50
                    _confidence.value = 50
                }
            }
            
            _showDialog.value = !alreadyReported
        }
    }

    fun updatePhysical(value: Int) {
        _physical.value = value
    }

    fun updateHappiness(value: Int) {
        _happiness.value = value
    }

    fun updateConfidence(value: Int) {
        _confidence.value = value
    }

    fun save(profileId: Int) {
        viewModelScope.launch {
            repository.saveRecord(
                profileId = profileId,
                physical = _physical.value,
                happiness = _happiness.value,
                confidence = _confidence.value
            )
            _showDialog.value = false
        }
    }

    fun skip() {
        // User closed without saving. 
        // We just hide it, but since no DB record is created, 
        // it will prompt again on next check.
        _showDialog.value = false
    }
}
