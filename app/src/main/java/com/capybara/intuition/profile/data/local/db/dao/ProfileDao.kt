package com.capybara.intuition.profile.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.capybara.intuition.profile.data.local.db.entity.ProfileEntity
import com.capybara.intuition.profile.data.local.db.entity.TesterProfileSnapshot
import com.capybara.intuition.profile.data.local.db.relation.ProfileWithTopology
import com.capybara.intuition.profile.domain.model.ProfileType
import kotlinx.coroutines.flow.Flow


@Dao
interface ProfileDao {

    // =====================================================
    // INSERT
    // =====================================================

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insert(
        profile: ProfileEntity
    ): Long

    // =================================================
    // QUERIES
    // =================================================

    @Query(
        """
        SELECT *
        FROM profiles
        WHERE type != 'SYSTEM'
        """
    )
    fun getVisibleProfiles(): Flow<List<ProfileEntity>>

    @Query(
        """
    SELECT *
    FROM profiles
    WHERE type = 'SYSTEM'
    LIMIT 1
    """
    )
    suspend fun getSystemProfile(): ProfileEntity

    @Query(
        """
    SELECT *
    FROM profiles
    WHERE type = :profileType
    LIMIT 1
    """)
    suspend fun getProfileByType(
        profileType: ProfileType
    ): ProfileEntity?


    // =================================================
    // CREATE
    // =================================================


    /*
    @Insert
    suspend fun createChildProfile(
        profile: ProfileEntity
    )


     */




    // =================================================
    // UPDATE SINGLE
    // =================================================

    @Update
    suspend fun updateProfile(
        profile: ProfileEntity
    )

    // =================================================
    // ACTIVE PROFILE TRANSITION
    // =================================================

    @Transaction
    suspend fun switchActiveProfile(
        oldProfile: ProfileEntity,
        newProfile: ProfileEntity
    ) {
        updateProfile(
            oldProfile.copy(
                active = false
            )
        )
        updateProfile(
            newProfile.copy(
                active = true
            )
        )
    }

    /*
    @Query(
        """
    INSERT INTO profiles (
        name,
        type,
        installationId,
        isSystemDefault,
        pinHash,
        activeNotebookId,
        preferredMode,
        minLevel,
        maxLevel,
        currentLevel,
        rendererId,
        maskId,
        topologyFamilyId,
        maxTopologyWidth,
        maxTopologyHeight,
        activeTopologyId,
        isDeleted,
        active,
        createdAt,
        lastUsedAt
    )

    SELECT
        :name,
        :type,
        installationId,
        0,
        NULL,
        activeNotebookId,
        preferredMode,
        minLevel,
        maxLevel,
        currentLevel,
        rendererId,
        maskId,
        topologyFamilyId,
        maxTopologyWidth,
        maxTopologyHeight,
        activeTopologyId,
        0,
        0,
        :createdAt,
        :lastUsedAt
    FROM profiles
    WHERE isSystemDefault = 1
    LIMIT 1
    """
    )

     */
    @Transaction
    suspend fun createChildProfile(
        name: String,
        type: ProfileType,
        createdAt: Long,
        lastUsedAt: Long
    ): Int {
        val systemProfile = getSystemProfile() ?: throw IllegalStateException("System default profile not found")
        val childProfile = systemProfile.copy(
            id = 0,
            name = name,
            type = type,
            isSystemDefault = false,
            pinHash = null,
            isDeleted = false,
            active = false,
            createdAt = createdAt,
            lastUsedAt = lastUsedAt
        )
        return insert(childProfile).toInt()

    }

    @Query(
        """
    UPDATE profiles
    SET name = :name
    WHERE type = :type
    AND isDeleted = 0
    """
    )
    suspend fun updateProfileNameByType(
        type: ProfileType,
        name: String
    )

    @Query(
        """
    UPDATE profiles

    SET
        name = :name,
        pinHash = :pinHash

    WHERE type = :type
    AND isDeleted = 0
    """
    )
    suspend fun setupPremiumParentProfile(
        type: ProfileType,
        name: String,
        pinHash: String
    )

    @Query(
        """
    SELECT EXISTS(
        SELECT 1
        FROM profiles
        WHERE type = :type
        AND pinHash = :pinHash
        AND isDeleted = 0
    )
    """
    )
    suspend fun isPinCorrect(
        type: ProfileType,
        pinHash: String
    ): Boolean

    @Query("""
        SELECT id
        FROM profiles
        WHERE type = 'TESTER'
    """)
    suspend fun getTesterProfileId(): Int?

    @Query(
        """
    SELECT
        id,
        activeNotebookId,
        activeTopologyId,
        maskId,
        rendererId
    FROM profiles
    WHERE type = 'TESTER'
    LIMIT 1
    """
    )
    suspend fun getTesterSnapshot(): TesterProfileSnapshot?


    // =============== START LEGACY =============



    // =====================================================
    // COUNTS
    // =====================================================

    @Query(
        """
        SELECT COUNT(*)
        FROM profiles
        """
    )
    suspend fun countProfiles(): Int

    // =====================================================
    // ACTIVE PROFILE
    // =====================================================

    @Query(
        """
        SELECT *
        FROM profiles
        WHERE active = 1
        LIMIT 1
        """
    )
    suspend fun getActiveProfile(): ProfileEntity?

    @Query(
        """
        UPDATE profiles
        SET active = 0
        """
    )
    suspend fun clearActiveProfiles()

    @Query(
        """
        UPDATE profiles
        SET active = 1
        WHERE id = :profileId
        """
    )
    suspend fun setProfileActive(
        profileId: Int
    )


    @Query(
        """
    UPDATE profiles

    SET
        currentLevel = :newLevel,

        activeTopologyId = (

            SELECT topologies.id

            FROM topology_family_relations relations

            INNER JOIN topologies
                ON topologies.id = relations.topologyId

            WHERE relations.familyId = profiles.topologyFamilyId
            AND topologies.occupancyCapacity = :newLevel

            LIMIT 1
        )

    WHERE id = :profileId
    """
    )
    suspend fun updateLevelAndTopology(
        profileId: Int,
        newLevel: Int
    )


    // =====================================================
    // GENERAL
    // =====================================================


    @Transaction
    @Query(
        """
    SELECT *
    FROM profiles
    WHERE isDeleted = 0
    AND type != "SYSTEM"
    ORDER BY createdAt ASC
    """
    )
    fun getProfiles(): Flow<List<ProfileWithTopology>>


    @Query(
        """
        SELECT *
        FROM profiles
        WHERE id = :profileId
        LIMIT 1
        """
    )
    suspend fun getProfileById(
        profileId: Int
    ): ProfileEntity?

    @Update
    suspend fun update(
        profile: ProfileEntity
    )

    @Query(
        """
    SELECT *
    FROM profiles
    WHERE type = 'HUMAN'
    LIMIT 1
    """
    )
    suspend fun getParentProfile(): ProfileEntity?

    @Insert
    suspend fun insertAll(
        profiles: List<ProfileEntity>
    )
    // =============== END LEGACY =============
}
