package com.capybara.intuition.features.wellbeing.di

import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.features.wellbeing.data.repository.WellbeingRepository
import com.capybara.intuition.features.wellbeing.presentation.viewmodel.WellbeingViewModel

class WellbeingModule(
    database: AppDatabase
) {
    private val wellbeingDao = database.wellbeingDao()
    
    val wellbeingRepository = WellbeingRepository(wellbeingDao)
    
    val wellbeingViewModel = WellbeingViewModel(wellbeingRepository)
}
