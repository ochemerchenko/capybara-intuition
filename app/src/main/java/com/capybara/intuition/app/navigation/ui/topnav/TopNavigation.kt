package com.capybara.intuition.app.navigation.ui.topnav

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.capybara.intuition.services.updater.presentation.AppUpdateViewModel
import com.capybara.intuition.services.updater.model.UpdateUiStatus
import com.capybara.intuition.services.updater.ui.AppUpdateDialog
import com.capybara.intuition.app.navigation.AppDestination
import com.capybara.intuition.core.theme.IntuitionTheme

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
                painter = painterResource(id = R.drawable.notification_bell),
                selected = false,
                useOriginalColors = true,
                onClick = {
                    Log.d("Bell", "Called: showAppUpdateDialog")
                    showAppUpdateDialog = true
                }
            )
        }

        // =============================================
        // GAME
        // =============================================
        val isGameSelected = currentRoute == AppDestination.Game.route
        NavIcon(
            painter = painterResource(
                id = if (isGameSelected)
                    R.drawable.active_play
                else
                    R.drawable.passive_play
            ),
            selected = isGameSelected,
            useOriginalColors = true,
            onClick = {
                navController.navigate(AppDestination.Game.route) {
                    launchSingleTop = true
                }
            }
        )

        // =============================================
        // ACHIEVEMENTS
        // =============================================

        val isAchievementsSelected = currentRoute == AppDestination.Achievements.route
        NavIcon(
            painter = painterResource(
                id = if (isAchievementsSelected)
                    R.drawable.active_cup
                else
                    R.drawable.passive_cup
            ),
            selected = isAchievementsSelected,
            useOriginalColors = true,
            onClick = {
                navController.navigate(AppDestination.Achievements.route) {
                    launchSingleTop = true
                }
            }
        )



        // =============================================
        // STATISTICS
        // =============================================
        val isStatisticsSelected = currentRoute == AppDestination.Statistics.route
        NavIcon(
            painter = painterResource(
                id = if (isStatisticsSelected)
                    R.drawable.active_stats
                else
                    R.drawable.passive_stats
            ),
            selected = isStatisticsSelected,
            useOriginalColors = true,
            onClick = {
                navController.navigate(AppDestination.Statistics.route) {
                    launchSingleTop = true
                }
            }
        )

        // =============================================
        // SETTINGS
        // =============================================
        val isSettingsSelected = currentRoute == AppDestination.Settings.route
        NavIcon(
            painter = painterResource(
                id = if (isSettingsSelected)
                    R.drawable.active_settings
                else
                    R.drawable.passive_settings
            ),
            selected = isSettingsSelected,
            useOriginalColors = true,
            onClick = {
                navController.navigate(AppDestination.Settings.route) {
                    launchSingleTop = true
                }
            }
        )

        // =============================================
        // INFO
        // =============================================
        val isInfoSelected = currentRoute == AppDestination.Info.route
        NavIcon(
            painter = painterResource(
                id = if (isInfoSelected)
                    R.drawable.active_info
                else
                    R.drawable.passive_info
            ),
            selected = isInfoSelected,
            useOriginalColors = true,
            onClick = {
                navController.navigate(AppDestination.Info.route) {
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

