package com.capybara.intuition.features.tester.model

data class GeneratedHistoricalDataset(
    val sessions: List<GeneratedSession>,
    val rounds: List<GeneratedRound>,
    val wellbeingRecords: List<GeneratedWellbeingRecord> = emptyList()
)
