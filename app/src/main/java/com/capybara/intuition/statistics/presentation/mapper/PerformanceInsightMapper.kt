package com.capybara.intuition.statistics.presentation.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.capybara.intuition.R
import com.capybara.intuition.statistics.domain.insight.model.PerformanceInsight
import com.capybara.intuition.statistics.domain.insight.model.PerformanceLevel
import com.capybara.intuition.statistics.domain.insight.model.Trend

@Composable
fun PerformanceInsight.toText(): String {

    val sentence = when (level) {
        PerformanceLevel.ABOVE_RANDOM ->
            when (trend) {
                Trend.IMPROVING -> stringResource(R.string.statistics_performance_above_random_improved)
                Trend.DECLINING -> stringResource(R.string.statistics_performance_above_random_declined)
                Trend.NONE -> stringResource(R.string.statistics_performance_above_random)
            }
        PerformanceLevel.BELOW_RANDOM ->
            when (trend) {
                Trend.IMPROVING -> stringResource(R.string.statistics_performance_below_random_improved)
                Trend.DECLINING -> stringResource(R.string.statistics_performance_below_random_declined)
                Trend.NONE -> stringResource(R.string.statistics_performance_below_random)
            }
        PerformanceLevel.NEAR_RANDOM ->
            when (trend) {
                Trend.IMPROVING -> stringResource(R.string.statistics_performance_near_random_improved)
                Trend.DECLINING -> stringResource(R.string.statistics_performance_near_random_declined)
                Trend.NONE -> stringResource(R.string.statistics_performance_near_random)
            }
    }

    return sentence
}