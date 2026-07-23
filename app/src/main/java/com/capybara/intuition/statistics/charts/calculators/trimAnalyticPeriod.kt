package com.capybara.intuition.statistics.charts.calculators

import com.capybara.intuition.statistics.charts.model.data.AnalyticsChartPoint

fun trimAnalyticPeriod (
    analyticsChartPoints: List<AnalyticsChartPoint>,
    analyticPeriod: Int
): List<AnalyticsChartPoint> =
    analyticsChartPoints.takeLast(analyticPeriod)