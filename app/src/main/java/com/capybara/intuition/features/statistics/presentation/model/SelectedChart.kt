package com.capybara.intuition.features.statistics.presentation.model

import com.capybara.intuition.features.statistics.charts.model.data.DayDensity

data class SelectedChart (
    val mode: SessionFilter,
    val level: Int,
    val dayDensities: List<DayDensity>
)