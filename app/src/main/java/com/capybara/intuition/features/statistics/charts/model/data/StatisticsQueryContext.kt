package com.capybara.intuition.features.statistics.charts.model.data

data class StatisticsQueryContext(
    val profileId: Int,
    val fromTimestamp: Long,
    val toTimestamp: Long,
    val noiseLevel: Int
)