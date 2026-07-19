package com.capybara.intuition.profile.domain.model

import com.capybara.intuition.game.domain.model.GameMode
import com.capybara.intuition.game.domain.model.topology.Topology
import com.capybara.intuition.game.domain.model.topology.TopologyFamily

data class Profile(

    // =================================================
    // IDENTITY
    // =================================================

    val id: Int,

    val name: String,

    val type: ProfileType,

    val installationId: String,

    val isSystemDefault: Boolean,

    // =================================================
    // SECURITY
    // =================================================

    val pinHash: String?,

    // =================================================
    // GAMEPLAY IDENTITY
    // =================================================

    /**
     * Canonical perceptual notebook identity.
     */
    val activeNotebookId: Short,

    /**
     * Current gameplay mode.
     */
    val preferredMode: GameMode,

    /**
     * Immutable progression lower bound.
     */
    val minLevel: Int,

    /**
     * Immutable progression upper bound.
     */
    val maxLevel: Int,

    /**
     * Mutable current progression position.
     */
    val currentLevel: Int,

    /**
     * Currently Topology Family.
     */
    val topologyFamily: TopologyFamily,
    val maxTopologyWidth: Int,
    val maxTopologyHeight: Int,

    /**
     * Currently Topology.
     */
    val activeTopology: Topology,

    /**
     * Canonical renderer dictionary reference.
     */
    val rendererId: Short,

    /**
     * Canonical mask dictionary reference.
     */
    val maskId: Short,




    // =================================================
    // LIFECYCLE
    // =================================================

    val isDeleted: Boolean,

    val active: Boolean,

    val createdAt: Long,

    val lastUsedAt: Long
)