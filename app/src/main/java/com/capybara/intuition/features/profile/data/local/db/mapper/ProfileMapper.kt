package com.capybara.intuition.features.profile.data.local.db.mapper

import com.capybara.intuition.features.game.data.mapper.GameModeMapper
import com.capybara.intuition.features.profile.data.local.db.entity.ProfileEntity
import com.capybara.intuition.features.profile.domain.model.Profile

fun Profile.toEntity(

): ProfileEntity {

    return ProfileEntity(

        id = id,
        name = name,
        type = type,
        installationId = installationId,

        isSystemDefault = isSystemDefault,

        pinHash = pinHash,

        activeNotebookId = activeNotebookId,

        preferredMode = GameModeMapper.toId(preferredMode),

        minLevel = minLevel,
        maxLevel = maxLevel,
        currentLevel = currentLevel,

        rendererId = rendererId,
        maskId = maskId,
        topologyFamilyId = topologyFamily.id,

        maxTopologyWidth = maxTopologyWidth,
        maxTopologyHeight = maxTopologyHeight,

        activeTopologyId = activeTopology.id,

        isDeleted = isDeleted,
        active = active,

        createdAt = createdAt,
        lastUsedAt = lastUsedAt,

        noiseCorridorVisibility = noiseCorridorVisibility,
        physicalStateVisibility = physicalStateVisibility,
        happinessVisibility = happinessVisibility,
        selfConfidenceVisibility = selfConfidenceVisibility
    )
}
