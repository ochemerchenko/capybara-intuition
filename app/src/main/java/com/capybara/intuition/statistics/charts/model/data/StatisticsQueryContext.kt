package com.capybara.intuition.statistics.charts.model.data

data class StatisticsQueryContext(
    val profileId: Int,
    val fromTimestamp: Long,
    val toTimestamp: Long,
    val noiseLevel: Int
)