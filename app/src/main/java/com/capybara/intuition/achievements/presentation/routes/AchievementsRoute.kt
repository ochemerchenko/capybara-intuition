package com.capybara.intuition.achievements.presentation.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.capybara.intuition.achievements.presentation.ui.AchievementsScreen
import com.capybara.intuition.app.App

@Composable
fun AchievementsRoute (
    modifier: Modifier
){

    val app = LocalContext.current.applicationContext as App
    val viewModel = app.appContainer.achievements.achievementsViewModel

    AchievementsScreen(
        viewModel = viewModel,
        modifier = modifier
    )
}