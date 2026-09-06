package com.capybara.intuition.app

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.capybara.intuition.app.navigation.AppNavHost
import com.capybara.intuition.services.bootstrap.model.BootstrapState
import com.capybara.intuition.services.bootstrap.data.BootstrapStateRepository
import com.capybara.intuition.services.installation.data.repository.ReleaseNotesRepository
import com.capybara.intuition.services.installation.ui.InstallationErrorScreen
import com.capybara.intuition.core.ui.WaitingScreen
import com.capybara.intuition.services.installation.ui.ReleaseNotesScreen
import kotlinx.coroutines.launch

@Composable
fun AppRoot(
    navController: NavHostController,
    //launchState: AppLaunchState,
    bootstrapStateRepository: BootstrapStateRepository,
    releaseNotesRepository: ReleaseNotesRepository,
    modifier: Modifier = Modifier,
) {

    val scope = rememberCoroutineScope()

    val bootstrapState by bootstrapStateRepository.state.collectAsState(
        initial = BootstrapState.NOT_STARTED
    )

    val releaseNotesVersionRead by releaseNotesRepository.releaseNotesVersionRead
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

    LaunchedEffect(launchState) {
        Log.d("ROOT", "launchState = $launchState")
    }

    when (launchState) {

        AppLaunchState.INSTALLING -> WaitingScreen()

        AppLaunchState.ONBOARDING -> ReleaseNotesScreen(
            onConfirm = {
                scope.launch {
                    releaseNotesRepository.completeOnboarding()
                }
            }
        )

        AppLaunchState.READY -> {
            Log.d("ROOT", "Creating AppNavHost")
            AppNavHost(
                navController = navController,
                modifier = modifier,
            )
        }

        AppLaunchState.ERROR -> InstallationErrorScreen()

    }
}