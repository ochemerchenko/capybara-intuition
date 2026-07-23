package com.capybara.intuition.profile.presentation.ui.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.app.jobs.db_cleaner.DatabaseRetentionPeriod
import com.capybara.intuition.app.presentation.theme.IntuitionTheme
import com.capybara.intuition.profile.presentation.ui.components.SettingsSection

@Composable
fun ThresholdHistorySection(
    retentionPeriod: DatabaseRetentionPeriod,
    onRetentionPeriodChanged: (DatabaseRetentionPeriod) -> Unit
) {

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    SettingsSection(
        title = stringResource(
            R.string.settings_threshold_history_header
        )
    ) {

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                FilterChip(
                    selected = retentionPeriod == DatabaseRetentionPeriod.DAYS_90,
                    onClick = {
                        onRetentionPeriodChanged(
                            DatabaseRetentionPeriod.DAYS_90
                        )
                    },
                    label = {
                        Text(
                            stringResource(
                                R.string.settings_threshold_history_retention_90_days
                            ),
                            style = typography.bodyMedium
                        )

                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = colors.backgroundChipsActive,
                        selectedLabelColor = colors.textChipsActive,
                        containerColor = colors.backgroundChipsPassive,
                        labelColor = colors.textChipsPassive,
                    ),
                    border = null
                )

                Spacer(
                    modifier = Modifier.weight(1f)
                )

                FilterChip(
                    selected = retentionPeriod == DatabaseRetentionPeriod.DAYS_180,
                    onClick = {
                        onRetentionPeriodChanged(
                            DatabaseRetentionPeriod.DAYS_180
                        )
                    },
                    label = {
                        Text(
                            stringResource(
                                R.string.settings_threshold_history_retention_180_days
                            ),
                            style = typography.bodyMedium
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = colors.backgroundChipsActive,
                        selectedLabelColor = colors.textChipsActive,
                        containerColor = colors.backgroundChipsPassive,
                        labelColor = colors.textChipsPassive,
                    ),
                    border = null
                )

                Spacer(
                    modifier = Modifier.weight(1f)
                )

                FilterChip(
                    selected = retentionPeriod == DatabaseRetentionPeriod.DAYS_360,
                    onClick = {
                        onRetentionPeriodChanged(
                            DatabaseRetentionPeriod.DAYS_360
                        )
                    },
                    label = {
                        Text(
                            stringResource(
                                R.string.settings_threshold_history_retention_360_days
                            ),
                            style = typography.bodyMedium
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = colors.backgroundChipsActive,
                        selectedLabelColor = colors.textChipsActive,
                        containerColor = colors.backgroundChipsPassive,
                        labelColor = colors.textChipsPassive,
                    ),
                    border = null
                )

                Spacer(
                    modifier = Modifier.weight(1f)
                )

                FilterChip(
                    selected = retentionPeriod == DatabaseRetentionPeriod.FOREVER,
                    onClick = {
                        onRetentionPeriodChanged(
                            DatabaseRetentionPeriod.FOREVER
                        )
                    },
                    label = {
                        Text(
                            stringResource(
                                R.string.settings_threshold_history_retention_forever
                            ),
                            style = typography.bodyMedium
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = colors.backgroundChipsActive,
                        selectedLabelColor = colors.textChipsActive,
                        containerColor = colors.backgroundChipsPassive,
                        labelColor = colors.textChipsPassive,
                    ),
                    border = null
                )
            }
        }
    }
}