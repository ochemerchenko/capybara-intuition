package com.capybara.intuition.features.profile.di

import com.capybara.intuition.services.database.data.DatabaseRetentionRepository
import com.capybara.intuition.features.profile.presentation.viewmodel.SettingsViewModel

class SettingsModule(
    databaseRetentionRepository: DatabaseRetentionRepository
) {

    val settingsViewModel = SettingsViewModel(
        repository = databaseRetentionRepository
    )
}