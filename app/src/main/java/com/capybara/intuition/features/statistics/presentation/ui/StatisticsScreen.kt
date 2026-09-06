package com.capybara.intuition.features.statistics.presentation.ui


import android.app.Activity
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.capybara.intuition.core.ui.ExitDialog
import com.capybara.intuition.services.license.domain.LicenseType
import com.capybara.intuition.features.game.domain.model.GameMode
import com.capybara.intuition.features.profile.presentation.viewmodel.ProfileViewModel
import com.capybara.intuition.features.statistics.presentation.model.SessionFilter
import com.capybara.intuition.features.statistics.presentation.ui.free.FreeStatisticsScreen
import com.capybara.intuition.features.statistics.presentation.ui.model.StatisticsSelectionState
import com.capybara.intuition.features.statistics.presentation.ui.premium.PremiumStatisticsScreen
import com.capybara.intuition.features.statistics.presentation.ui.shared.components.EmptyStatisticsScreen
import com.capybara.intuition.features.statistics.charts.calculators.calculateRequiredHistoryDays
import com.capybara.intuition.features.statistics.charts.model.data.DayDensity
import com.capybara.intuition.features.statistics.charts.model.data.PreviewByLevelRequest
import com.capybara.intuition.features.statistics.charts.model.data.PreviewByModeRequest
import com.capybara.intuition.features.statistics.charts.model.data.StatisticsQueryContext
import com.capybara.intuition.features.statistics.presentation.viewmodel.StatisticsViewModel


@Composable
fun StatisticsScreen(
    statisticsViewModel: StatisticsViewModel,
    profileViewModel: ProfileViewModel,
    licenseType: LicenseType,
    onNavigateToInfo: () -> Unit,
    modifier: Modifier = Modifier
) {

    val emaModelingPeriod = 10
    val analyticPeriod = 30
    val noiseLevel = 3
    val epsilon = 0.01f // відхилення ema від baseline, при якому вважаю, що даних не залишилось, виключно шум.
    val profiles by profileViewModel
        .profiles
        .collectAsState()
    val activeProfile = profiles.firstOrNull { it.active }
    if (activeProfile == null) {
        return
    }
    val availableLevels = activeProfile.minLevel .. activeProfile.maxLevel
    val dailyDensitiesPerLevel by statisticsViewModel.dailyDensitiesPerLevel.collectAsState()
    val dailyDensitiesPerMode by statisticsViewModel.dailyDensitiesPerMode.collectAsState()



    val now = System.currentTimeMillis()
    val historyDays = calculateRequiredHistoryDays(
        analyticPeriod = analyticPeriod,
        emaPeriod = emaModelingPeriod,
        epsilon = epsilon
    )

    val from = remember(
        now,
        historyDays
    ) {
        now - historyDays * 24L * 60L * 60L * 1000L
    }

    // =====================================================
    // STATE
    // =====================================================

    var selectionState by remember {
        mutableStateOf(
            StatisticsSelectionState()
        )
    }
    LaunchedEffect(selectionState) {
        Log.d(
            "SELECTION",
            "level=${selectionState.selectedLevel}, filter=${selectionState.selectedFilter}"
        )
    }


    val analyticalMode =
        when (licenseType) {
            LicenseType.FREE -> GameMode.AFTER
            LicenseType.PREMIUM -> statisticsViewModel.sessionFilterToGameMode(
                selectionState.selectedFilter
            )
        }


    LaunchedEffect(
        activeProfile.id,
        analyticalMode,
        selectionState,
        from,
        now
    ) {

        statisticsViewModel.loadAnalyticalGraph(
            profileId = activeProfile.id,
            sessionMode = analyticalMode,
            level = selectionState.selectedLevel,
            noiseLevel = noiseLevel,
            fromTimestamp = from,
            toTimestamp = now
        )
    }

    // Start of BACK-dialog

    var showExitDialog by rememberSaveable { mutableStateOf(false) }

    val activity = LocalContext.current as? Activity

    BackHandler {
        showExitDialog = true
    }

    if (showExitDialog) {
        ExitDialog(
            onConfirm = {
                activity?.finish()
            },
            onDismiss = {
                showExitDialog = false
            }
        )
    }


    // End of BACK-dialog


    // =====================================================
    // UI
    // =====================================================

    when (licenseType) {

        LicenseType.PREMIUM -> {

            LaunchedEffect(
                selectionState.selectedFilter
            ) {
                statisticsViewModel.loadDailyDensitiesPerMode(
                    PreviewByModeRequest(
                        mode = selectionState.selectedFilter.gameMode,
                        levels = availableLevels.toList(),
                        scope = StatisticsQueryContext(
                            profileId = activeProfile.id,
                            fromTimestamp = from,
                            toTimestamp = now,
                            noiseLevel = noiseLevel,
                        )
                    )
                )
            }

            LaunchedEffect(
                selectionState.selectedLevel
            ) {
                statisticsViewModel.loadDailyDensitiesPerLevel(
                    PreviewByLevelRequest(
                        level = selectionState.selectedLevel,
                        gameModes = SessionFilter.entries.map { it.gameMode },
                        scope = StatisticsQueryContext(
                            profileId = activeProfile.id,
                            fromTimestamp = from,
                            toTimestamp = now,
                            noiseLevel = noiseLevel,
                        )
                    )
                )
            }

            val analyticalGraphsPerLevel = LinkedHashMap<SessionFilter, List<DayDensity>>()

            dailyDensitiesPerLevel?.datasets?.forEach {
                    (gameMode, dayDensities) ->
                analyticalGraphsPerLevel[
                    SessionFilter.fromGameMode(gameMode)
                ] = dayDensities
            }

            if (dailyDensitiesPerLevel?.datasets?.isNotEmpty() == true ||
                dailyDensitiesPerMode?.datasets?.isNotEmpty() == true) {
                PremiumStatisticsScreen(
                    availableLevels = availableLevels,
                    selectionState = selectionState,
                    onSelectionStateChanged = {
                        selectionState = it
                        Log.d(
                            "SELECTION",
                            "callback: $it"
                        )
                    },
                    onNavigateToInfo = onNavigateToInfo,
                    emaModelingPeriod = emaModelingPeriod,
                    analyticPeriod = analyticPeriod,
                    dateFrom = from,
                    dateNow = now,

                    analyticalGraphsPerLevel = analyticalGraphsPerLevel,
                    analyticalGraphsPerMode = dailyDensitiesPerMode?.datasets,
                    onHelpLinkClick = {
                        onNavigateToInfo()
                    }
                )
            } else {
                EmptyStatisticsScreen()
            }
        }

        LicenseType.FREE -> {

            LaunchedEffect(
                selectionState.selectedLevel
            ) {
                statisticsViewModel.loadDailyDensitiesPerMode(
                    PreviewByModeRequest(
                        mode = GameMode.AFTER,
                        levels = availableLevels.toList(),
                        scope = StatisticsQueryContext(
                            profileId = activeProfile.id,
                            fromTimestamp = from,
                            toTimestamp = now,
                            noiseLevel = noiseLevel,
                        )
                    )
                )
            }

            val dailyDensities = dailyDensitiesPerMode
                ?.datasets
                ?.get(selectionState.selectedLevel)
                ?: emptyList()


            if (dailyDensities.isNotEmpty()) {
                FreeStatisticsScreen(
                    selectedLevel = selectionState.selectedLevel,
                    availableLevels = availableLevels,
                    emaModelingPeriod  = emaModelingPeriod,
                    analyticPeriod = analyticPeriod,
                    dateFrom = from,
                    dateNow = now,
                    analyticalGraph = dailyDensities,
                    onLevelSelected = { level ->
                        selectionState =
                            selectionState.copy(
                                selectedLevel = level
                            )
                    },
                    onHelpLinkClick = {
                        onNavigateToInfo()
                    }
                )
            } else {
                EmptyStatisticsScreen()
            }
        }
    }
}




