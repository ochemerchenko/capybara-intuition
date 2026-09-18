package com.capybara.intuition.features.achievements.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.capybara.intuition.R
import com.capybara.intuition.features.achievements.presentation.viewmodel.AchievementsViewModel
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.achievements.presentation.ui.components.shared.achievement

@Composable
fun Regularity (
    viewModel: AchievementsViewModel,
    modifier: Modifier = Modifier
) {
    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    val lastActiveDays by viewModel.lastActiveDays.collectAsStateWithLifecycle()
    val totalActiveDays by viewModel.totalActiveDays.collectAsStateWithLifecycle()
    val maxStreak by viewModel.maxStreak.collectAsStateWithLifecycle()
    val maxBreak by viewModel.maxBreak.collectAsStateWithLifecycle()
    val daysWithApp by viewModel.daysWithApp.collectAsStateWithLifecycle()

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        achievement(
            string_id = R.string.achievements_regularity_last_active_days,
            value = lastActiveDays
        )

        achievement(
            string_id = R.string.achievements_regularity_days_with_app,
            value = daysWithApp
        )

        achievement(
            string_id = R.string.achievements_regularity_total_active_days,
            value = totalActiveDays
        )

        achievement(
            string_id = R.string.achievements_regularity_max_streak,
            value = maxStreak
        )

        achievement(
            string_id = R.string.achievements_regularity_max_break,
            value = maxBreak
        )

        if (totalActiveDays == 0L) {
            Text(
                text = stringResource(R.string.achievements_regularity_no_data),
                style = typography.bodyMedium,
                color = colors.activeElementText
            )
        }
    }
}
