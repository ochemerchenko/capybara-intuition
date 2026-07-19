package com.capybara.intuition.app.navigation.ui.topnav

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.capybara.intuition.app.navigation.AppDestination
import com.capybara.intuition.app.presentation.theme.IntuitionTheme

@Composable
fun TopNavigation(
    navController: NavController
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

    val currentRoute =
        currentBackStackEntry
            ?.destination
            ?.route

    // =====================================================
    // UI
    // =====================================================
    val density = LocalDensity.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                topNavigationOuterPadding
            ),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        NavIcon(
            icon = Icons.Default.Info,
            selected = currentRoute == AppDestination.Info.route,
            onClick = {
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
}

