package com.capybara.intuition.features.statistics.charts.calculators

import com.capybara.intuition.features.statistics.charts.model.data.AnalyticsChartPoint

fun trimAnalyticPeriod (
    analyticsChartPoints: List<AnalyticsChartPoint>,
    analyticPeriod: Int
): List<AnalyticsChartPoint> =
    analyticsChartPoints.takeLast(analyticPeriod)