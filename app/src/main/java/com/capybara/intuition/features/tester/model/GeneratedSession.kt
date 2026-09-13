package com.capybara.intuition.features.tester.model

data class GeneratedSession(
    val id: Long,
    val level: Int,
    val mode: String,
    val startedAt: Long = 0L,
    val finishedAt: Long? = null, // Can be NULL - майже зайве
    val roundCount: Int? = null, // Зайве
    val rounds: List<GeneratedRound> = emptyList()
)