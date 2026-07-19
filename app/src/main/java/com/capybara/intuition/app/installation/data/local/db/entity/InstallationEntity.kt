package com.capybara.intuition.app.installation.data.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "installations"
)
data class InstallationEntity(

    @PrimaryKey
    val id: String,

    // =====================================================
    // PLATFORM
    // =====================================================
    val platform: String,
    val platformVersion: String,
    val platformVersionSdkInt: Int,

    // =====================================================
    // DEVICE
    // =====================================================
    val manufacturer: String,
    val deviceBrand: String,
    val deviceModel: String,
    val deviceHardware: String,
    val supportedAbis: String,

    // =====================================================
    // DISPLAY
    // =====================================================
    val screenWidthDp: Int,
    val screenHeightDp: Int,
    val smallestWidthDp: Int,

    val densityDpi: Int,

    val screenWidthPx: Int,
    val screenHeightPx: Int,

    // =====================================================
    // LOCALIZATION
    // =====================================================
    val locale: String,
    val timezone: String,

    // =====================================================
    // APPLICATION
    // =====================================================
    val appVersion: String,
    val appVersionCode: Int,
    val distributionChannel: String,

    // =====================================================
    // USER EXPERIENCE
    // =====================================================
    val lastAcceptedWelcomeVersion: Int,

    // =====================================================
    // LIFETIME
    // =====================================================
    val installedAt: Long,

    // =====================================================
    // EXPORT
    // =====================================================
    val firstSuccessfulExportAt: Long?
)