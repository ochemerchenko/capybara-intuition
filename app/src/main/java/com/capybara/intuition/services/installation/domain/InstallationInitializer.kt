package com.capybara.intuition.services.installation.domain

import android.content.Context
import android.os.Build
import com.capybara.intuition.services.installation.data.local.db.entity.InstallationEntity
import com.capybara.intuition.services.installation.data.repository.InstallationRepository
import com.capybara.intuition.BuildConfig
import com.capybara.intuition.services.sync.domain.exportInstallation
import java.util.Locale
import java.util.TimeZone
import java.util.UUID

class InstallationInitializer(
    private val installationRepository: InstallationRepository,
    private val context: Context,
) {

    suspend fun ensureInstallationExists() {

        val existing = installationRepository.countInstallations()
        if (existing > 0) {
            return
        }

        val configuration = context.resources.configuration
        val metrics = context.resources.displayMetrics

        val now = System.currentTimeMillis()

        val installation = InstallationEntity(

            id = UUID.randomUUID().toString(),

            // =====================================================
            // PLATFORM
            // =====================================================
            platform = "android",
            platformVersion = Build.VERSION.RELEASE ?: "unknown",
            platformVersionSdkInt = Build.VERSION.SDK_INT,

            // =====================================================
            // DEVICE
            // =====================================================
            manufacturer = Build.MANUFACTURER ?: "unknown",
            deviceBrand = Build.BRAND,
            deviceModel = Build.MODEL ?: "unknown",
            deviceHardware = Build.HARDWARE,
            supportedAbis = Build.SUPPORTED_ABIS.joinToString(","),

            // =====================================================
            // DISPLAY
            // =====================================================
            screenWidthDp = configuration.screenWidthDp,
            screenHeightDp = configuration.screenHeightDp,
            smallestWidthDp = configuration.smallestScreenWidthDp,

            densityDpi = metrics.densityDpi,

            screenWidthPx = metrics.widthPixels,
            screenHeightPx = metrics.heightPixels,

            // =====================================================
            // LOCALIZATION
            // =====================================================
            locale = Locale.getDefault().toLanguageTag(),
            timezone = TimeZone.getDefault().id,

            // =====================================================
            // APP
            // =====================================================
            appVersion = BuildConfig.VERSION_NAME,
            appVersionCode = BuildConfig.VERSION_CODE,
            distributionChannel = "google_play",

            // =====================================================
            // USER EXPERIENCE
            // =====================================================
            lastAcceptedWelcomeVersion = 0,

            // =====================================================
            // LIFETIME
            // =====================================================
            installedAt = now,

            // =====================================================
            // EXPORT
            // =====================================================
            firstSuccessfulExportAt = null

        )

        exportInstallation(
            context = context,
            installation = installation
        )

        installationRepository.insertInstallation(
            installation
        )
    }
}