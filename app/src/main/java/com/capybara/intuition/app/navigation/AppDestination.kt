package com.capybara.intuition.app.navigation

sealed class AppDestination(
    val route: String
) {
    data object Game : AppDestination("game")
    data object Statistics : AppDestination("statistics")
    data object Settings : AppDestination("settings")
    data object Info : AppDestination("info")
    data object TesterTools : AppDestination("tools")

}