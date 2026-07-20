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
import com.capybara.intuition.app.navigation.AppNavHost
import com.capybara.intuition.app.navigation.ui.topnav.TopNavigation
import com.capybara.intuition.app.presentation.layout.LocalAppLayoutEnvironment
import com.capybara.intuition.app.presentation.layout.rememberAppLayoutEnvironment
import com.capybara.intuition.app.presentation.theme.IntuitionTheme




import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
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
    BackHandler {
        showExitDialog = true
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
                    TopNavigation(
                        navController = navController
                    )
                }
            ) { paddingValues ->
                AppRoot(
                    navController = navController,
                    bootstrapStateRepository = appContainer.installation.bootstrapStateRepository,
                    releaseNotesRepository = appContainer.installation.releaseNotesRepository,
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                )

            }
        }
    }
}