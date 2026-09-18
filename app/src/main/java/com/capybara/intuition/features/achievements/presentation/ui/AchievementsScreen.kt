package com.capybara.intuition.features.achievements.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.capybara.intuition.R
import com.capybara.intuition.core.layout.LocalAppLayoutEnvironment
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.achievements.presentation.ui.components.LevelActivity
import com.capybara.intuition.features.achievements.presentation.ui.components.Records
import com.capybara.intuition.features.achievements.presentation.ui.components.Regularity
import com.capybara.intuition.features.achievements.presentation.viewmodel.AchievementsViewModel
import com.capybara.intuition.features.profile.presentation.ui.components.SettingsSection

@Composable
fun AchievementsScreen (
    viewModel: AchievementsViewModel,
    modifier: Modifier = Modifier
){
    val env = LocalAppLayoutEnvironment.current
    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography
    val scrollState = rememberScrollState()

    // Уніфікація з SettingsScreen:
    // 1. Root Box має зовнішній падінг env.screenContentPadding (16.dp) і НЕ має фону.
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(env.screenContentPadding),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(env.screenSectionSpacing)
        ) {

            Column(verticalArrangement = Arrangement.spacedBy(env.screenSectionSpacing / 2)) {
                Text(
                    text = stringResource(R.string.achievements_title),
                    style = typography.h1,
                    color = colors.textH1
                )

                Text(
                    text = stringResource(R.string.achievements_description),
                    style = typography.bodyMedium,
                    color = colors.activeElementText
                )
            }

            SettingsSection(
                title = stringResource(R.string.achievements_regularity_subtitle)
            ) {
                Regularity(
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            SettingsSection(
                title = stringResource(R.string.achievements_level_activity_subtitle)
            ) {
                LevelActivity(
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            SettingsSection(
                title = stringResource(R.string.achievements_records_subtitle)
            ) {
                Records(
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
