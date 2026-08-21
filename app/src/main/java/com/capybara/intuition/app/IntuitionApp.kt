package com.capybara.intuition.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.capybara.intuition.app.navigation.ui.topnav.TopNavigation
import com.capybara.intuition.app.presentation.layout.LocalAppLayoutEnvironment
import com.capybara.intuition.app.presentation.layout.rememberAppLayoutEnvironment
import com.capybara.intuition.app.presentation.theme.IntuitionTheme




import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.capybara.intuition.app.bootstrap.BootstrapState
import com.capybara.intuition.app.navigation.AppLaunchState
import com.capybara.intuition.app.navigation.AppRoot

@Composable
fun IntuitionApp() {

    val app = LocalContext.current.applicationContext as App
    val appContainer = app.appContainer
    val navController = rememberNavController()
    val layoutEnvironment = rememberAppLayoutEnvironment()
    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    val activity = LocalContext.current as Activity
    var showExitDialog by rememberSaveable {
        mutableStateOf(false)
    }

    val bootstrapState by appContainer.installation.bootstrapStateRepository.state
        .collectAsState(
            initial = BootstrapState.NOT_STARTED
        )

    val releaseNotesVersionRead by appContainer.installation.releaseNotesRepository
        .releaseNotesVersionRead
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


    //val dispatcherOwner = LocalOnBackPressedDispatcherOwner.current

    //LaunchedEffect(dispatcherOwner) {
    //    Log.d("BackTest", "dispatcherOwner, msg 1 = $dispatcherOwner")
    //}


    LaunchedEffect(showExitDialog) {
        Log.d("BackTest", "showExitDialog = $showExitDialog")
    }


    if (showExitDialog) {
        ExitDialog(
            onConfirm = {
                activity.finishAffinity()
            },
            onDismiss = {
                showExitDialog = false
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .safeDrawingPadding()
            .padding(
                start = 8.dp,
                top = 0.dp,
                end = 8.dp,
                bottom = 0.dp
            )
            .background(colors.backgroundScreen)
    ) {
        CompositionLocalProvider(
            LocalAppLayoutEnvironment
                    provides layoutEnvironment
        ) {
            Scaffold(
                containerColor = Color.Transparent,
                topBar = {
                    if (launchState == AppLaunchState.READY) {
                        TopNavigation(
                            navController = navController,
                            appUpdateViewModel = appContainer.appUpdateViewModel
                        )
                    }
                },
                //bottomBar = {
                //    if (launchState == AppLaunchState.READY) {
                //        TopNavigation(
                //            navController = navController,
                //            appUpdateViewModel = appContainer.appUpdateViewModel
                //        )
                //    }
                //}
            ) {
                paddingValues -> DisposableEffect(Unit) {
                    Log.d("BackTest", "IntuitionApp BackHandler ENTER")
                        onDispose {
                        Log.d("BackTest", "IntuitionApp BackHandler EXIT")
                    }
                }

                BackHandler {
                    Log.d("BackTest", "Global BackHandler")
                    showExitDialog = true
                }

                AppRoot(
                    navController = navController,
                    //launchState = launchState,
                    releaseNotesRepository = appContainer.installation.releaseNotesRepository,
                    bootstrapStateRepository = appContainer.installation.bootstrapStateRepository,
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                )

            }
        }
    }
}