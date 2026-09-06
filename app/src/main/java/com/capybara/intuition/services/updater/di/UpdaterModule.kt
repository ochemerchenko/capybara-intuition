package com.capybara.intuition.services.updater.di

import android.content.Context
import com.capybara.intuition.services.updater.data.AppUpdateManager
import com.capybara.intuition.services.updater.presentation.AppUpdateViewModel

class UpdaterModule(
    context: Context
) {
    val appUpdateManager = AppUpdateManager(context)
    
    val appUpdateViewModel = AppUpdateViewModel(appUpdateManager)
}
