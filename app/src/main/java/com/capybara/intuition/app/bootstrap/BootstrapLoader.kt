package com.capybara.intuition.app.bootstrap

import android.content.Context
import com.capybara.intuition.license.capability.RuntimeCapabilities
import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.app.bootstrap.colors.NotebookResourceLinkSeeder
import com.capybara.intuition.app.bootstrap.colors.NotebookSeeder
import com.capybara.intuition.app.bootstrap.colors.ResourceSeeder
import com.capybara.intuition.app.bootstrap.gameModes.GameModeSeeder
import com.capybara.intuition.app.bootstrap.masks.MaskSeeder
import com.capybara.intuition.app.bootstrap.renderers.RendererSeeder
import com.capybara.intuition.app.bootstrap.topology.TopologySeeder
import com.capybara.intuition.app.installation.data.local.repository.InstallationRepository
import com.capybara.intuition.app.license.data.repository.LicenseRepository
import com.capybara.intuition.license.domain.LicenseType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BootstrapLoader(
    private val context: Context,
    private val database: AppDatabase,
    private val installationRepository: InstallationRepository,
    private val capabilities: RuntimeCapabilities,
    private val licenseRepository: LicenseRepository
) {

    suspend fun bootstrap() {

        withContext(
            Dispatchers.IO
        ) {

            licenseRepository.setLicense(
                LicenseType.FREE
            )

            licenseRepository.setLastConfirmedLicense(
                LicenseType.FREE
            )

            ResourceSeeder(
                context = context,
                resourceDao = database.resourceDao()
            ).seedIfEmpty()

            NotebookSeeder(
                context = context,
                notebookDao = database.notebookDao()
            ).seedIfEmpty()

            NotebookResourceLinkSeeder(
                context = context,
                notebookResourceLinkDao =
                    database.notebookResourceLinkDao()
            ).seedIfEmpty()

            // =================================================
            // RENDERERS
            // =================================================

            RendererSeeder(
                context = context,
                rendererDao = database.rendererDao()
            ).seed()

            // =================================================
            // MASKS
            // =================================================

            MaskSeeder(
                context = context,
                maskDao = database.maskDao()
            ).seed()

            TopologySeeder(
                context = context,
                topologyDao = database.topologyDao(),
                topologyFamilyDao = database.topologyFamilyDao()
            ).seedIfEmpty()

            GameModeSeeder(
                context = context,
                gameModeDao = database.gameModeDao()
            ).seed()

            // =================================================
            // PROFILES
            // =================================================

            ProfileSeeder(
                context = context,
                profileDao = database.profileDao(),
                capabilities = capabilities,
                installationRepository = installationRepository
            ).seedIfEmpty()
        }
    }
}

