package com.capybara.intuition.profile.tester.model

data class HistoricalDatasetRequest(

    val periodDays: Int = 30,
    val trainingIntensity: TrainingIntensity = TrainingIntensity.MEDIUM,
    val minLevel: Int,
    val maxLevel: Int,

    val competence: Double,
    val selfConfidence: Double,
    val motivationBoostPerPeriod: Double,
    val motivationScale: Double,

    val lastSessionId: Long
)