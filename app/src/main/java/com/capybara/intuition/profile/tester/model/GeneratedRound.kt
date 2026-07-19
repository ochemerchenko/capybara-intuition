package com.capybara.intuition.profile.tester.model


data class GeneratedRound(

    val sessionId: Long,

    val mode: String,

    val startedAt: Long,
    val userSelectedAt: Long,
    val finishedAt: Long,

    val result: String
)

