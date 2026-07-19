package com.capybara.intuition.app.tools.markdown

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun MarkdownImage(
    token: String,
    modifier: Modifier = Modifier
) {
    when (token) {

        "help_full_density_chart" -> {
            HelpFullDensityChartImage(
                modifier = modifier
            )
        }
        "help_activity_chart" -> {
            HelpActivityChartImage(
                modifier = modifier
            )
        }
        "help_decision_time_chart" -> {
            HelpDecisionTimeChartImage (
                modifier = modifier
            )
        }

        /*
        "density_preview" -> {
            DensityPreviewLegendImage(
                modifier = modifier
            )
        }

        "activity_chart_legend" -> {
            ActivityLegendImage(
                modifier = modifier
            )
        }

         */

        else -> {
            error(
                "Unknown markdown image token: $token"
            )
        }
    }
}