package com.capybara.intuition.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.capybara.intuition.app.bootstrap.BootstrapState
import com.capybara.intuition.app.bootstrap.BootstrapStateRepository
import com.capybara.intuition.app.installation.data.local.repository.ReleaseNotesRepository
import com.capybara.intuition.app.navigation.ui.setup.InstallationErrorScreen
import com.capybara.intuition.app.navigation.ui.setup.InstallationScreen
import com.capybara.intuition.app.navigation.ui.setup.ReleaseNotesScreen
import kotlinx.coroutines.launch

@Composable
fun AppRoot(
    navController: NavHostController,
    bootstrapStateRepository: BootstrapStateRepository,
    releaseNotesRepository: ReleaseNotesRepository,
    modifier: Modifier = Modifier,
) {

    val scope = rememberCoroutineScope()

    val bootstrapState by
    bootstrapStateRepository.state.collectAsState(
        initial = BootstrapState.NOT_STARTED
    )

    val releaseNotesVersionRead by
    releaseNotesRepository.releaseNotesVersionRead
        .collectAsState(
            initial = 0
        )


    val launchState =
        when {
            releaseNotesVersionRead == 0 -> AppLaunchState.ONBOARDING
            bootstrapState == BootstrapState.FAILED -> AppLaunchState.ERROR
            bootstrapState == BootstrapState.COMPLETED -> AppLaunchState.READY
            else -> AppLaunchState.INSTALLING
        }

    when (launchState) {

        AppLaunchState.INSTALLING ->
            InstallationScreen()

        AppLaunchState.ONBOARDING ->
            ReleaseNotesScreen(
                onConfirm = {
                    scope.launch {
                        releaseNotesRepository.completeOnboarding()
                    }
                }
            )

        AppLaunchState.READY ->
            AppNavHost(
                navController = navController,
                modifier = modifier,
            )

        AppLaunchState.ERROR ->
            InstallationErrorScreen()

    }
}