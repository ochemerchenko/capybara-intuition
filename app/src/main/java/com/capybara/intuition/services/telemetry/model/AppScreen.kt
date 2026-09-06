package com.capybara.intuition.services.telemetry.model

import com.capybara.intuition.app.navigation.AppDestination

enum class AppScreen(val route: String?) {
    // Основні екрани
    GAME(AppDestination.Game.route),
    STATISTICS(AppDestination.Statistics.route),
    SETTINGS(AppDestination.Settings.route),
    INFO(AppDestination.Info.route),
    ACHIEVEMENTS(AppDestination.Achievements.route),

    // Технічні розділи
    TESTER_TOOLS(AppDestination.TesterTools.route),

    // Спеціальні стани для аналітики
    STARTUP(null);

    companion object {
        fun fromRoute(route: String?): AppScreen? {
            return entries.find { it.route == route }
        }
    }
}