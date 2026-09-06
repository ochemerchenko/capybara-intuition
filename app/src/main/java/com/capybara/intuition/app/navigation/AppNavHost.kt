package com.capybara.intuition.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.NavHostController
import com.capybara.intuition.achievements.presentation.routes.AchievementsRoute
import com.capybara.intuition.app.App
import com.capybara.intuition.app.BuildConfiguration
import com.capybara.intuition.app.navigation.telemetry.model.AppScreen
import com.capybara.intuition.game.presentation.routes.GameRoute
import com.capybara.intuition.info.presentation.routes.InfoRoute
import com.capybara.intuition.license.domain.CapabilityEnvironment
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

    // =====================================================
    // TELEMETRY LOGIC
    // =====================================================
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var previousRoute by rememberSaveable { mutableStateOf<String?>(null) }

    LaunchedEffect(currentRoute) {
        if (currentRoute != previousRoute) {
            val from = if (previousRoute == null) AppScreen.STARTUP else AppScreen.fromRoute(previousRoute)
            val to = AppScreen.fromRoute(currentRoute)

            if (to != null) {
                container.telemetry.telemetryRepository.recordTransition(from, to)
            }
            previousRoute = currentRoute
        }
    }

    // =====================================================
    // NAVIGATION GRAPH
    // =====================================================
    NavHost(
        navController = navController,
        startDestination = AppDestination.Game.route,
        modifier = modifier
    ) {
        composable(AppDestination.Game.route) { entry ->
            GameRoute(
                modifier = modifier
            )
        }

        composable(AppDestination.Statistics.route) { entry ->
            StatisticsRoute(
                navController = navController,
                modifier = modifier
            )
        }

        composable(AppDestination.Settings.route) { entry ->
            SettingsRoute(
                navController = navController,
                modifier = modifier
            )
        }

        composable(AppDestination.Info.route) { entry ->
            InfoRoute(
                modifier = modifier
            )
        }

        composable(AppDestination.Achievements.route) { entry ->
            AchievementsRoute(
                modifier = modifier
            )
        }

        if (BuildConfiguration.environment == CapabilityEnvironment.TEST) {
            composable(AppDestination.TesterTools.route) { entry ->
                TesterToolsRoute(
                    modifier = modifier
                )
            }
        }
    }
}
