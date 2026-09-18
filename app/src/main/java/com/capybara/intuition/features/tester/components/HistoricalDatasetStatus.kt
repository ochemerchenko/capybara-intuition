package com.capybara.intuition.features.tester.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.capybara.intuition.core.theme.IntuitionTheme

@Composable
fun HistoricalDatasetStatus(
    source: String?,
    operationResult: Boolean?,
    modifier: Modifier = Modifier
) {

    val dataSetGeneratedString = stringResource(
        com.capybara.intuition.R.string.settings_historical_dataset_generated
    )
    val dataSetGenerationFailedString = stringResource(
        com.capybara.intuition.R.string.settings_historical_dataset_generation_failed
    )
    val dataSetCleanedString = stringResource(
        com.capybara.intuition.R.string.settings_historical_dataset_cleaned
    )
    val dataSetCleaningFailedString = stringResource(
        com.capybara.intuition.R.string.settings_historical_dataset_cleaning_failed
    )

    var message = ""
    var color = Color.Transparent
    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    when (source) {
        "generator" -> {
            when (operationResult) {
                true -> {
                    message = dataSetGeneratedString
                    color = colors.commonSuccessText
                }
                false -> {
                    message = dataSetGenerationFailedString
                    color = colors.commonFailText
                }
                else -> return
            }
        }
        "cleaner" -> {
            when (operationResult) {
                true -> {
                    message = dataSetCleanedString
                    color = colors.commonSuccessText
                }
                false -> {
                    message = dataSetCleaningFailedString
                    color = colors.commonFailText
                }
                else -> return
            }
        }
    }

    if (message != "") {
        Text(
            text = message,
            modifier = modifier,
            color = color,
            style = typography.bodyMedium

            )
    }
}