package com.capybara.intuition.features.game.domain.model

import com.capybara.intuition.features.profile.domain.model.Profile


data class GameSession(

    // =================================================
    // IDENTITY
    // =================================================

    val id: Long = 0,

    // =================================================
    // RUNTIME EXECUTION CONTEXT
    // =================================================

    /**
     * Mutable gameplay identity owner.
     *
     * Runtime session references
     * current gameplay identity state.
     *
     * Historical persistence snapshotting
     * belongs to GameSessionEntity.
     */
    val profile: Profile,

    /**
     * Runtime-materialized playable resources.
     *
     * Exists to avoid runtime DB access
     * during round execution.
     */
    val resources: List<Resource>,

    /**
     * Legacy runtime legacyTopology realization.
     */
    //val legacyTopology: LegacyTopology,

    // =================================================
    // SESSION STATISTICS
    // =================================================

    /**
     * Total successful bets
     * during current runtime session.
     */
    val winCount: Int,

    /**
     * Total failed bets
     * during current runtime session.
     */
    val loseCount: Int,

    /**
     * Previous round result.
     *
     * true  -> previous round won
     * false -> previous round lost
     * null  -> no previous round yet
     */
    val isWinLastBet: Boolean?,

    /**
     * Consecutive successful bets.
     */
    val winStreak: Int,

    /**
     * Consecutive failed bets.
     */
    val loseStreak: Int,

    val lastBrokenStreak: Int,

    // =================================================
    // LIFECYCLE
    // =================================================

    /**
     * Runtime session start timestamp.
     */
    val startedAt: Long,

    /**
     * Runtime session completion timestamp.
     */
    val finishedAt: Long?
)

