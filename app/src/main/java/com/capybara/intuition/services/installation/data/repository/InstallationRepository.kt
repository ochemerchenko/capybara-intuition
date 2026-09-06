package com.capybara.intuition.services.installation.data.repository

import com.capybara.intuition.services.installation.data.local.db.dao.InstallationDao
import com.capybara.intuition.services.installation.data.local.db.entity.InstallationEntity

class InstallationRepository(

    private val dao: InstallationDao
) {

    // =========================================================
    // QUERIES
    // =========================================================

    suspend fun getInstallation(): InstallationEntity {
        return dao.getInstallation()
    }

    suspend fun countInstallations(): Int {
        return dao.countInstallations()
    }

    // =========================================================
    // INSERT
    // =========================================================

    suspend fun insertInstallation(
        installation: InstallationEntity
    ) {

        dao.insert(
            installation
        )
    }


    // =========================================================
    // MAINTENANCE
    // =========================================================

    suspend fun deleteAll() {

        dao.deleteAll()
    }
}