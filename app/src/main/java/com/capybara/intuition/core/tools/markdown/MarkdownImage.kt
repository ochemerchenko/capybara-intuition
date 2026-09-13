package com.capybara.intuition.core.tools.markdown

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

        "self_test_questionnary_uk" -> {
            SelfTestQuestionnaryImage(
                resId = com.capybara.intuition.R.drawable.self_test_questionnary_uk,
                modifier = modifier
            )
        }

        "self_test_questionnary_en" -> {
            SelfTestQuestionnaryImage(
                resId = com.capybara.intuition.R.drawable.self_test_questionnary_en,
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