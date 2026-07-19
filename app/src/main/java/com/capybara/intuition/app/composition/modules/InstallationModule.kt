package com.capybara.intuition.app.composition.modules

import android.content.Context
import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.app.installation.data.local.repository.InstallationRepository
import com.capybara.intuition.app.installation.domain.installation.InstallationInitializer

class InstallationModule (
    database: AppDatabase,
    context: Context
) {
    val installationDao = database.installationDao()
    val installationRepository = InstallationRepository(
        installationDao
    )
    val installationInitializer = InstallationInitializer(
        installationRepository = installationRepository,
        context = context.applicationContext
    )
}