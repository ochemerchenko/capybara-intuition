package com.capybara.intuition.services.billing.di

import android.content.Context
import com.capybara.intuition.services.billing.GooglePlayBillingManager
import com.capybara.intuition.services.license.di.LicenseModule

class BillingModule(
    context: Context,
    license: LicenseModule
) {
    val googlePlayBillingManager = GooglePlayBillingManager(
        context = context,
        licenseRepository = license.licenseRepository,
        synchronizationRepository = license.synchronizationRepository
    )
}
