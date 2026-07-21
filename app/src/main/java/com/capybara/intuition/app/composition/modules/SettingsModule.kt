package com.capybara.intuition.app.composition.modules

import DatabaseRetentionRepository
import com.capybara.intuition.profile.presentation.viewmodel.SettingsViewModel

class SettingsModule(
    databaseRetentionRepository: DatabaseRetentionRepository
) {

    val settingsViewModel = SettingsViewModel(
        repository = databaseRetentionRepository
    )
}