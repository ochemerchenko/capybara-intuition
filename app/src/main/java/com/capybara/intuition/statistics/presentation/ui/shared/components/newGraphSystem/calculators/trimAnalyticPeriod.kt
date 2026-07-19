package com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.calculators

import com.capybara.intuition.statistics.presentation.ui.shared.components.newGraphSystem.model.data.AnalyticsChartPoint

fun trimAnalyticPeriod (
    analyticsChartPoints: List<AnalyticsChartPoint>,
    analyticPeriod: Int
): List<AnalyticsChartPoint> =
    analyticsChartPoints.takeLast(analyticPeriod)