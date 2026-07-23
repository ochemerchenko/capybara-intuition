package com.capybara.intuition.statistics.presentation.model

import com.capybara.intuition.statistics.charts.model.data.DayDensity

data class SelectedChart (
    val mode: SessionFilter,
    val level: Int,
    val dayDensities: List<DayDensity>
)