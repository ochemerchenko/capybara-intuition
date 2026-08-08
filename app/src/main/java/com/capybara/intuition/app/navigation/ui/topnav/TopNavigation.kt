package com.capybara.intuition.app.navigation.ui.topnav

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.capybara.intuition.R
import com.capybara.intuition.app.jobs.appUpdater.AppUpdateViewModel
import com.capybara.intuition.app.jobs.appUpdater.model.UpdateUiStatus
import com.capybara.intuition.app.jobs.appUpdater.ui.AppUpdateDialog
import com.capybara.intuition.app.navigation.AppDestination
import com.capybara.intuition.app.presentation.theme.IntuitionTheme

@Composable
fun TopNavigation(
    navController: NavController,
    appUpdateViewModel: AppUpdateViewModel
) {

    // =====================================================
    // TOP NAVIGATION PRESENTATION SEMANTICS
    // =====================================================
    val topNavigationOuterPadding = 0.dp //8.dp
    val topNavigationDividerThickness = 1.dp
    val topNavigationDividerAlpha = 0.12f

    // =====================================================
    // NAVIGATION STATE
    // =====================================================

    val currentBackStackEntry by
    navController.currentBackStackEntryAsState()

    val currentRoute = currentBackStackEntry
            ?.destination
            ?.route

    // =====================================================
    // UI
    // =====================================================
    val density = LocalDensity.current

    val updateUiState by appUpdateViewModel
        .appUpdateUiState
        .collectAsStateWithLifecycle()

    LaunchedEffect(updateUiState) {

        Log.d(
            "TopNavigation",
            "updateUiState = $updateUiState"
        )
    }

    var showAppUpdateDialog by rememberSaveable {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                topNavigationOuterPadding
            ),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (updateUiState != UpdateUiStatus.NoUpdateAvailable) {
            NavIcon(
                icon = Icons.Default.Notifications,
                selected = false,
                onClick = {
                    Log.d("Bell", "Called: showAppUpdateDialog")
                    showAppUpdateDialog = true
                }
            )
        }


        NavIcon(
            icon = Icons.Default.Info,
            selected = currentRoute == AppDestination.Info.route,
            onClick = {
                Log.d(
                    "NAV",
                    "Navigate to Statistics, controller=${navController.hashCode()}, currentRoute=$currentRoute"
                )
                navController.navigate(
                    AppDestination.Info.route
                ) {
                    launchSingleTop = true
                }
            }
        )

        NavIcon(
            icon = Icons.Default.PlayArrow,
            selected = currentRoute == AppDestination.Game.route,
            onClick = {
                Log.d(
                    "NAV",
                    "Navigate to Statistics, controller=${navController.hashCode()}, currentRoute=$currentRoute"
                )
                navController.navigate(
                    AppDestination.Game.route
                ) {
                    launchSingleTop = true
                }
            }
        )

        NavIcon(
            icon = Icons.Default.BarChart,
            selected = currentRoute == AppDestination.Statistics.route,
            onClick = {
                Log.d(
                    "NAV",
                    "Navigate to Statistics, controller=${navController.hashCode()}, currentRoute=$currentRoute"
                )
                navController.navigate(
                    AppDestination.Statistics.route
                ) {
                    launchSingleTop = true
                }
            }
        )

        NavIcon(
            icon = Icons.Default.Settings,
            selected = currentRoute == AppDestination.Settings.route,
            onClick = {
                Log.d(
                    "NAV",
                    "Navigate to Statistics, controller=${navController.hashCode()}, currentRoute=$currentRoute"
                )
                navController.navigate(
                    AppDestination.Settings.route
                ) {
                    launchSingleTop = true
                }
            }
        )
    }

    HorizontalDivider(
        thickness = topNavigationDividerThickness,
        color = IntuitionTheme
            .colors
            .textSecondary
            .copy(
                alpha = topNavigationDividerAlpha
            )
    )

    if (showAppUpdateDialog) {

        AppUpdateDialog(
            updateUiStatus = updateUiState,
            onDismiss = {
                showAppUpdateDialog = false
            },
            onUpdate = appUpdateViewModel::onUpdate,
            onRestart = appUpdateViewModel::onRestart
        )
    }
}

