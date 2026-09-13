package com.capybara.intuition.features.profile.data.local.db.relation

import android.util.Log
import androidx.room.Embedded
import androidx.room.Relation
import com.capybara.intuition.features.game.data.local.db.entity.topology.TopologyEntity
import com.capybara.intuition.features.game.data.local.db.entity.topology.TopologyFamilyEntity
import com.capybara.intuition.features.game.data.local.db.relation.TopologyWithCells
import com.capybara.intuition.features.game.data.local.db.relation.toDomain
import com.capybara.intuition.features.game.data.mapper.GameModeMapper
import com.capybara.intuition.features.game.data.mapper.topology.toDomain
import com.capybara.intuition.features.game.domain.model.GameMode
import com.capybara.intuition.features.profile.data.local.db.entity.ProfileEntity
import com.capybara.intuition.features.profile.domain.model.Profile

data class ProfileWithTopology(

    @Embedded
    val profile: ProfileEntity,

    @Relation(
        parentColumn = "topologyFamilyId",
        entityColumn = "id"
    )
    val topologyFamily: TopologyFamilyEntity,

    @Relation(
        entity = TopologyEntity::class,

        parentColumn = "activeTopologyId",
        entityColumn = "id"
    )
    val activeTopology: TopologyWithCells


)

fun ProfileWithTopology.toDomain(): Profile {
    Log.d("PROFILES", "ProfileWithTopology: toDomain(): called")
    val profile = Profile(

        id = profile.id,
        name = profile.name,
        type = profile.type,
        installationId = profile.installationId,
        isSystemDefault = profile.isSystemDefault,
        pinHash = profile.pinHash,
        activeNotebookId = profile.activeNotebookId,
        preferredMode =
            GameModeMapper.fromId(
                profile.preferredMode
            ),
        minLevel = profile.minLevel,
        maxLevel = profile.maxLevel,
        currentLevel = profile.currentLevel,
        topologyFamily = topologyFamily.toDomain(),
        maxTopologyWidth = profile.maxTopologyWidth,
        maxTopologyHeight = profile.maxTopologyHeight,
        activeTopology = activeTopology.toDomain(),
        rendererId = profile.rendererId,
        maskId = profile.maskId,
        isDeleted = profile.isDeleted,
        active = profile.active,
        createdAt = profile.createdAt,
        lastUsedAt = profile.lastUsedAt,
        noiseCorridorVisibility = profile.noiseCorridorVisibility,
        physicalStateVisibility = profile.physicalStateVisibility,
        happinessVisibility = profile.happinessVisibility,
        selfConfidenceVisibility = profile.selfConfidenceVisibility
    )
    Log.d("PROFILES", "ProfileWithTopology: toDomain(): profile = $profile")
    return profile
}