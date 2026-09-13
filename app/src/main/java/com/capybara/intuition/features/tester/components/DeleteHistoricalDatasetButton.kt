package com.capybara.intuition.features.tester.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.capybara.intuition.R
import com.capybara.intuition.core.theme.IntuitionTheme

@Composable
fun DeleteHistoricalDatasetButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.activeLevelButton,
            contentColor = colors.activeLevelButtonText,
            disabledContainerColor = colors.passiveLevelButton,
            disabledContentColor = colors.passiveLevelButtonText
        ),
        enabled = enabled
    ) {
        Text(
            text = stringResource(
                R.string.settings_delete_historical_dataset_button
            )
        )
    }

}