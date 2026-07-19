package com.capybara.intuition.profile.data.repository

import android.util.Log
import com.capybara.intuition.profile.data.local.db.dao.ProfileDao
import com.capybara.intuition.profile.data.local.db.mapper.toEntity
import com.capybara.intuition.profile.data.local.db.relation.toDomain
import com.capybara.intuition.profile.domain.model.Profile
import com.capybara.intuition.profile.domain.model.ProfileType
import com.capybara.intuition.profile.domain.security.PinHasher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Objects.toString
import java.util.UUID

class ProfileRepository(

    private val profileDao: ProfileDao,
    private val pinHasher: PinHasher,
) {

    // =================================================
    // АКТУАЛЬНИЙ
    // ACTIVE RUNTIME API
    // =================================================


    fun getProfiles(): Flow<List<Profile>> {

        return profileDao
            .getProfiles()
            .map { aggregates ->
                aggregates.map { aggregate ->
                    aggregate.toDomain()
                }
            }
    }


















    // =================================================
    // UPDATE SINGLE PROFILE
    // =================================================
    suspend fun updateProfile(
        profile: Profile
    ) {
        // Актуальний
        val _profile = toString(profile)
        Log.d("MODE", "Repository: In suspend fun updateProfile profile = $_profile")
        profileDao.updateProfile(
            profile.toEntity()
        )
    }

    suspend fun updateLevelAndTopology(
        profileId: Int,
        newLevel: Int
    ) {

        profileDao.updateLevelAndTopology(
            profileId = profileId,
            newLevel = newLevel
        )

    }


    // =================================================
    // CREATE CHILD PROFILE
    // =================================================
    suspend fun createChildProfile(
        name: String
    ) {

        val now = System.currentTimeMillis()

        profileDao.createChildProfile(
            name = name,
            type = ProfileType.CHILD,
            createdAt = now,
            lastUsedAt = now
        )
    }


    // =================================================
    // ACTIVE PROFILE TRANSITION
    // =================================================
    suspend fun switchActiveProfile(
        oldProfile: Profile,
        newProfile: Profile
    ) {
        Log.d("PROFILE", "ProfileRepository: onProfileSelected: \n\toldProfile = $oldProfile, \n\tnewProfile = $newProfile")
        profileDao.switchActiveProfile(
            oldProfile = oldProfile.toEntity(),
            newProfile = newProfile.toEntity()
        )
    }


    suspend fun verifyParentPin(
        enteredPin: String
    ): Boolean {

        val enteredHash = pinHasher.hash(
            enteredPin
        )

        return profileDao
            .isPinCorrect(
                type = ProfileType.HUMAN,
                pinHash = enteredHash
            )
    }

    suspend fun setupPremiumParentProfile(
        name: String,
        pin: String
    ) {

        val hashedPin = pinHasher.hash(pin)
        profileDao
            .setupPremiumParentProfile(
                type = ProfileType.HUMAN,
                name = name,
                pinHash = hashedPin
            )
    }
    suspend fun setupFreeParentProfile(
        name: String
    ) {
        profileDao.updateProfileNameByType(
            type = ProfileType.HUMAN,
            name = name
        )
    }





}
