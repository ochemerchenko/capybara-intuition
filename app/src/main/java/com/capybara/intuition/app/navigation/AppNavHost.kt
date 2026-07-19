package com.capybara.intuition.app.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.capybara.intuition.app.App
import com.capybara.intuition.game.presentation.routes.GameRoute
import com.capybara.intuition.info.presentation.routes.InfoRoute
import com.capybara.intuition.profile.presentation.routes.SettingsRoute
import com.capybara.intuition.profile.tester.presentation.routes.TesterToolsRoute
import com.capybara.intuition.statistics.presentation.routes.StatisticsRoute

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {

    val app = LocalContext.current.applicationContext as App
    val container = app.appContainer
    val capabilities = container.license.capabilities

    NavHost(
        navController = navController,
        startDestination = AppDestination.Game.route,
        modifier = modifier
    ) {
        composable(AppDestination.Game.route) {
            GameRoute(
                modifier = modifier
            )
        }

        composable(AppDestination.Statistics.route) {
            StatisticsRoute(
                navController = navController,
                modifier = modifier
            )
        }

        composable(AppDestination.Settings.route) {
            SettingsRoute(
                navController = navController,
                modifier = modifier
            )
        }

        composable(AppDestination.Info.route) {
            InfoRoute(
                modifier = modifier
            )
        }

        if (capabilities.supportsAnalyticTesterProfileRuntime) {
            composable(AppDestination.TesterTools.route) {
                TesterToolsRoute(
                    modifier = modifier
                )
            }
        }
    }
}
