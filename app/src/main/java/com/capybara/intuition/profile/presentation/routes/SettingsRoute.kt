package com.capybara.intuition.profile.presentation.routes

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.capybara.intuition.app.App
import com.capybara.intuition.app.navigation.AppDestination
import com.capybara.intuition.profile.presentation.ui.SettingsScreen


@Composable
fun SettingsRoute(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val app = LocalContext.current.applicationContext as App
    val container = app.appContainer

    val profileViewModel = container.profile.profileViewModel
    val settingsViewModel = container.settings.settingsViewModel

    val capabilities = container.license.capabilities

    val onOpenTesterTools =
        if (capabilities.supportsAnalyticTesterProfileRuntime) {
            {
                navController.navigate(AppDestination.TesterTools.route)
            }
        } else {
            {}
        }

    val onLearnMoreClick = {
        navController.navigate(AppDestination.Info.route)
    }

    SettingsScreen(
        profileViewModel = profileViewModel,
        settingsViewModel = settingsViewModel,
        capabilities = capabilities,
        onOpenTesterTools = onOpenTesterTools,
        onLearnMoreClick = onLearnMoreClick,
        modifier = modifier
    )
}
