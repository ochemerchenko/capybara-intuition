package com.capybara.intuition.game.data.mapper

import com.capybara.intuition.game.data.local.db.entity.GameSessionEntity
import com.capybara.intuition.game.domain.model.GameSession

fun GameSession.toEntity(): GameSessionEntity {

    return GameSessionEntity(

        // =============================================
        // IDENTITY
        // =============================================
        id = id,
        profileId = profile.id,

        // =============================================
        // GAMEPLAY SNAPSHOT
        // =============================================
        mode = GameModeMapper.toId(
            profile.preferredMode
        ),
        level = profile.currentLevel,

        // =====================================================
        // RESOURCE ENVIRONMENT
        // =====================================================
        notebookId = profile.activeNotebookId,
        topologyId = profile.activeTopology.id,

        // =====================================================
        // RENDER ENVIRONMENT
        // =====================================================
        maskId = profile.maskId,
        rendererId = profile.rendererId,


        // =============================================
        // LIFECYCLE
        // =============================================
        startedAt = startedAt,
        finishedAt = finishedAt
    )
}