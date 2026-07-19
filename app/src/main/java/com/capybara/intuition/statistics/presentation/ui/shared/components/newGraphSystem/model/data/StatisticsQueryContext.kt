package com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data

data class StatisticsQueryContext(
    val profileId: Int,
    val fromTimestamp: Long,
    val toTimestamp: Long,
    val noiseLevel: Int
)