package com.capybara.intuition.profile.tester.model

enum class TrainingIntensity(
    val sessionsPerDay: Int,
    val displayName: String
) {

    LOW(
        sessionsPerDay = 3,
        displayName = "Low"
    ),

    MEDIUM(
        sessionsPerDay = 10,
        displayName = "Medium"
    ),

    HIGH(
        sessionsPerDay = 15,
        displayName = "High"
    )
}