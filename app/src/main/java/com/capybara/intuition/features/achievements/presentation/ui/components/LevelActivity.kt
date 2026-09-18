package com.capybara.intuition.features.achievements.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.capybara.intuition.R
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.achievements.presentation.ui.components.shared.achievement
import com.capybara.intuition.features.achievements.presentation.viewmodel.AchievementsViewModel

@Composable
fun LevelActivity(
    viewModel: AchievementsViewModel,
    modifier: Modifier = Modifier,
) {
    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    val maxSessions by viewModel.maxSessionsPerLevel.collectAsStateWithLifecycle()
    val maxRounds by viewModel.maxRoundsPerLevel.collectAsStateWithLifecycle()

    val levels = remember(maxSessions, maxRounds) {
        (maxSessions.map { it.level } + maxRounds.map { it.level }).distinct().sorted()
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        if (levels.isEmpty()) {
            Text(
                text = stringResource(R.string.achievements_regularity_no_data),
                style = typography.bodyMedium,
                color = colors.activeElementText
            )
        } else {
            levels.forEachIndexed { index, level ->
                if (index > 0) {
                    Spacer(modifier = Modifier.height(16.dp))
                }

                Text(
                    text = stringResource(R.string.achievements_level_activity_level_label, level),
                    style = typography.h2,
                    color = colors.textH2
                )
                
                maxSessions.find { it.level == level }?.let {
                    achievement(
                        string_id = R.string.achievements_level_activity_max_sessions,
                        value = it.value
                    )
                }
                
                maxRounds.find { it.level == level }?.let {
                    achievement(
                        string_id = R.string.achievements_level_activity_max_rounds,
                        value = it.value
                    )
                }
            }
        }
    }
}
