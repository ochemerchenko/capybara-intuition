package com.capybara.intuition.features.profile.presentation.routes

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.capybara.intuition.app.App
import com.capybara.intuition.app.navigation.AppDestination
import com.capybara.intuition.services.license.capability.RuntimeCapabilities
import com.capybara.intuition.features.profile.presentation.ui.SettingsScreen
import kotlinx.coroutines.flow.Flow


@Composable
fun SettingsRoute(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val app = LocalContext.current.applicationContext as App
    val container = app.appContainer

    val profileViewModel = container.profile.profileViewModel
    val settingsViewModel = container.settings.settingsViewModel

    val onLearnMoreClick = {
        navController.navigate(AppDestination.Info.route)
    }


    //val capabilities = container.license.capabilities
    val capabilities by container.license.capabilities.collectAsStateWithLifecycle()

    SettingsScreen(
        profileViewModel = profileViewModel,
        settingsViewModel = settingsViewModel,
        capabilities = capabilities,
        onOpenTesterTools = {
            navController.navigate(
                AppDestination.TesterTools.route
            )
        },
        onLearnMoreClick = onLearnMoreClick,
        modifier = modifier
    )
}
