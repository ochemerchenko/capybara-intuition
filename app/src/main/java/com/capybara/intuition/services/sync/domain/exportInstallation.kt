package com.capybara.intuition.services.sync.domain

import android.content.Context
import com.capybara.intuition.services.installation.data.local.db.entity.InstallationEntity
import com.google.gson.JsonObject


suspend fun exportInstallation (
    context: Context,
    installation: InstallationEntity
) {

    val jsonObject = JsonObject().apply {

        // =====================================
        // IDENTITY
        // =====================================

        addProperty("id", installation.id)

        // =====================================
        // PLATFORM
        // =====================================

        addProperty("platform", installation.platform)
        addProperty("platformVersion", installation.platformVersion)
        addProperty(
            "platformVersionSdkInt",
            installation.platformVersionSdkInt
        )

        // =====================================
        // DEVICE
        // =====================================

        addProperty("manufacturer", installation.manufacturer)
        addProperty("deviceBrand", installation.deviceBrand)
        addProperty("deviceModel", installation.deviceModel)
        addProperty("deviceHardware", installation.deviceHardware)
        addProperty("supportedAbis", installation.supportedAbis)

        // =====================================
        // DISPLAY
        // =====================================

        addProperty("screenWidthDp", installation.screenWidthDp)
        addProperty("screenHeightDp", installation.screenHeightDp)
        addProperty("smallestWidthDp", installation.smallestWidthDp)

        addProperty("densityDpi", installation.densityDpi)

        addProperty("screenWidthPx", installation.screenWidthPx)
        addProperty("screenHeightPx", installation.screenHeightPx)

        // =====================================
        // LOCALIZATION
        // =====================================

        addProperty("locale", installation.locale)
        addProperty("timezone", installation.timezone)

        // =====================================
        // APP
        // =====================================

        addProperty("appVersion", installation.appVersion)
        addProperty("appVersionCode", installation.appVersionCode)
        addProperty(
            "distributionChannel",
            installation.distributionChannel
        )

        // =====================================
        // USER EXPERIENCE
        // =====================================

        addProperty(
            "lastAcceptedWelcomeVersion",
            installation.lastAcceptedWelcomeVersion
        )

        // =====================================
        // LIFETIME
        // =====================================

        addProperty("installedAt", installation.installedAt)

    }

    archivateExport(
        context = context,
        installationId = installation.id,
        exportType = "installation",
        data = jsonObject
    )

}