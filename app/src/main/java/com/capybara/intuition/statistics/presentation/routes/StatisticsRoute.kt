package com.capybara.intuition.statistics.presentation.routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.capybara.intuition.app.App
import com.capybara.intuition.app.navigation.AppDestination
import com.capybara.intuition.statistics.presentation.ui.StatisticsScreen
import com.capybara.intuition.statistics.presentation.viewmodel.StatisticsViewModel
import com.capybara.intuition.statistics.presentation.viewmodel.StatisticsViewModelFactory


@Composable
fun StatisticsRoute(
    navController: NavHostController,
    modifier: Modifier
) {

    val context = LocalContext.current.applicationContext
    val app = context.applicationContext as App
    val container = app.appContainer

    val statisticsViewModel: StatisticsViewModel = viewModel(
        factory = StatisticsViewModelFactory(
            container = container
        )
    )

    StatisticsScreen(
        statisticsViewModel = statisticsViewModel,
        profileViewModel = container.profile.profileViewModel,
        onNavigateToInfo = {
            navController.navigate(
                AppDestination.Info.route
            )
        },
        modifier = modifier
    )
}