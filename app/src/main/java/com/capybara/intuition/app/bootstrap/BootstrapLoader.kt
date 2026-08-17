package com.capybara.intuition.app.bootstrap

import android.content.Context
import android.util.Log
import com.capybara.intuition.license.capability.RuntimeCapabilities
import com.capybara.intuition.app.AppDatabase
import com.capybara.intuition.app.bootstrap.colors.NotebookResourceLinkSeeder
import com.capybara.intuition.app.bootstrap.colors.NotebookSeeder
import com.capybara.intuition.app.bootstrap.colors.ResourceSeeder
import com.capybara.intuition.app.bootstrap.gameModes.GameModeSeeder
import com.capybara.intuition.app.bootstrap.masks.MaskSeeder
import com.capybara.intuition.app.bootstrap.renderers.RendererSeeder
import com.capybara.intuition.app.bootstrap.topology.TopologySeeder
import com.capybara.intuition.app.bootstrap.installation.data.local.repository.InstallationRepository
import com.capybara.intuition.app.license.data.repository.LicenseRepository
import com.capybara.intuition.license.domain.LicenseType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class BootstrapLoader(
    private val context: Context,
    private val database: AppDatabase,
    private val installationRepository: InstallationRepository,
    private val capabilities: RuntimeCapabilities,
    private val licenseRepository: LicenseRepository,
    private val bootstrapStateRepository: BootstrapStateRepository
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
            bootstrapStateRepository.setState(
                BootstrapState.LICENSE_READY
            )


            Log.d("BOOT", "ResourceSeeder")
            ResourceSeeder(
                context = context,
                resourceDao = database.resourceDao()
            ).seedIfEmpty()
            bootstrapStateRepository.setState(
                BootstrapState.RESOURCES_READY
            )


            Log.d("BOOT", "NotebookSeeder")
            NotebookSeeder(
                context = context,
                notebookDao = database.notebookDao()
            ).seedIfEmpty()
            bootstrapStateRepository.setState(
                BootstrapState.NOTEBOOKS_READY
            )


            Log.d("BOOT", "NotebookResourceLinkSeeder")
            NotebookResourceLinkSeeder(
                context = context,
                notebookResourceLinkDao = database.notebookResourceLinkDao()
            ).seedIfEmpty()
            bootstrapStateRepository.setState(
                BootstrapState.NOTEBOOK_RESOURCE_LINKS_READY
            )

            // =================================================
            // RENDERERS
            // =================================================

            Log.d("BOOT", "RendererSeeder")
            RendererSeeder(
                context = context,
                rendererDao = database.rendererDao()
            ).seed()
            bootstrapStateRepository.setState(
                BootstrapState.RENDERERS_READY
            )

            // =================================================
            // MASKS
            // =================================================

            Log.d("BOOT", "MaskSeeder")
            MaskSeeder(
                context = context,
                maskDao = database.maskDao()
            ).seed()
            bootstrapStateRepository.setState(
                BootstrapState.MASKS_READY
            )

            Log.d("BOOT", "TopologySeeder")
            TopologySeeder(
                context = context,
                topologyDao = database.topologyDao(),
                topologyFamilyDao = database.topologyFamilyDao()
            ).seedIfEmpty()
            bootstrapStateRepository.setState(
                BootstrapState.TOPOLOGIES_READY
            )

            Log.d("BOOT", "GameModeSeeder")
            GameModeSeeder(
                context = context,
                gameModeDao = database.gameModeDao()
            ).seed()
            bootstrapStateRepository.setState(
                BootstrapState.GAME_MODES_READY
            )

            //delay(5000)



            // =================================================
            // PROFILES
            // =================================================
            Log.d("BOOT", "ProfileSeeder")
            ProfileSeeder(
                context = context,
                profileDao = database.profileDao(),
                capabilities = capabilities,
                installationRepository = installationRepository
            ).seedIfEmpty()
            bootstrapStateRepository.setState(
                BootstrapState.PROFILES_READY
            )

            Log.d("BOOT", "ProfileSeeder finished")

            bootstrapStateRepository.setState(
                BootstrapState.COMPLETED
            )
        }
    }
}

