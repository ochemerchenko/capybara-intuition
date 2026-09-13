package com.capybara.intuition.services.bootstrap.domain

import android.content.Context
import android.util.Log
import com.capybara.intuition.services.installation.data.repository.InstallationRepository
import com.capybara.intuition.services.sync.domain.exportProfile
import com.capybara.intuition.services.license.capability.RuntimeCapabilities
import com.capybara.intuition.features.profile.data.local.db.dao.ProfileDao
import com.capybara.intuition.features.profile.data.local.db.entity.ProfileEntity
import com.capybara.intuition.features.profile.domain.model.ProfileType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProfileSeeder(
    private val context: Context,
    private val profileDao: ProfileDao,
    private val capabilities: RuntimeCapabilities,
    private val installationRepository: InstallationRepository

) {



    suspend fun seedIfEmpty() {

        val countExistingProfiles = profileDao.countProfiles()

        if (countExistingProfiles > 0) {
            return
        }

        val json = context.assets
            .open("bootstrap/profiles.json")
            .bufferedReader()
            .use {
                it.readText()
            }

        val type =
            object : TypeToken<List<ProfileSeedModel>>() {}
                .type

        val seedModels: List<ProfileSeedModel> =
            Gson()
                .fromJson(
                    json,
                    type
                )

        val installationId = installationRepository.getInstallation().id

        val now = System.currentTimeMillis()

        val filteredSeeds = seedModels.filter { seed ->
            val isTester = seed.type == "TESTER"
            if (
                isTester &&
                !capabilities
                    .supportsAnalyticTesterProfileRuntime
            ) {
                return@filter false
            }
            true
        }

        val configuration = context.resources.configuration


        val width = configuration.screenWidthDp
        val height = configuration.screenHeightDp

        val aspectRatio = width.toFloat() / height.toFloat()

        val defaultTopologyFamilyId =
            if (aspectRatio < 0.6f) {
                1L
            } else {
                3L
            }

        val maxTopologyWidth: Int
        val maxTopologyHeight: Int

        when (defaultTopologyFamilyId) {

            1L -> {
                maxTopologyWidth = 3
                maxTopologyHeight = 4
            }

            3L -> {
                maxTopologyWidth = 4
                maxTopologyHeight = 3
            }

            else -> {
                maxTopologyWidth = 3
                maxTopologyHeight = 4
            }
        }

        val conf = configuration.orientation
        Log.d(
            "BOOTSTRAP",
            "orientation=${configuration.orientation}, " +
                    "width=$width, height=$height " +
                    "configuration.orientation=$conf"
        )

        val entities = filteredSeeds.map { seed ->
            ProfileEntity(

                // =====================================
                // IDENTITY
                // =====================================
                id = 0,
                name = seed.name,
                type = ProfileType.valueOf(
                    seed.type
                ),
                installationId = installationId,
                isSystemDefault = seed.isSystemDefault,

                // =====================================
                // SECURITY
                // =====================================
                pinHash = null,

                // =====================================
                // GAMEPLAY IDENTITY
                // =====================================
                activeNotebookId = seed.activeNotebookId,
                preferredMode = seed.preferredMode,
                minLevel = seed.minLevel,
                maxLevel = seed.maxLevel,
                currentLevel = seed.currentLevel,
                rendererId = seed.rendererId,
                maskId = seed.maskId,
                topologyFamilyId = defaultTopologyFamilyId,
                maxTopologyWidth = maxTopologyWidth,
                maxTopologyHeight = maxTopologyHeight,

                activeTopologyId = seed.activeTopologyId,

                // =====================================
                // LIFECYCLE
                // =====================================
                isDeleted = false,
                active = seed.active,
                createdAt = now,
                lastUsedAt = now,

                noiseCorridorVisibility = true,
                physicalStateVisibility = false,
                happinessVisibility = false,
                selfConfidenceVisibility = false
            )
        }
        Log.d("BOOTSTRAP", "Profiles to insert = ${entities.size}")
        Log.d("BOOTSTRAP", entities.joinToString("\n"))
        profileDao.insertAll(
            entities
        )
        Log.d("BOOTSTRAP", "Profiles inserted")

        val humanProfileEntity = profileDao.getParentProfile()
        if (humanProfileEntity != null) {
            exportProfile(
                context = context,
                profileEntity = humanProfileEntity
            )
        }
    }
}

private data class ProfileSeedModel(
    val name: String,
    val type: String,
    val isSystemDefault: Boolean,
    val active: Boolean,
    val activeNotebookId: Short,
    val preferredMode: Short,
    val minLevel: Int,
    val maxLevel: Int,
    val currentLevel: Int,
    val rendererId: Short,
    val maskId: Short,
    val topologyFamilyId: Long,
    val maxTopologyWidth: Int,
    val maxTopologyHeight: Int,
    val activeTopologyId: Short
)