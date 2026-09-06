package com.capybara.intuition.features.profile.tester.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.capybara.intuition.R
import com.capybara.intuition.core.theme.IntuitionTheme
import com.capybara.intuition.features.profile.presentation.ui.components.SettingsSection
import com.capybara.intuition.features.profile.tester.components.CompetenceSelector
import com.capybara.intuition.features.profile.tester.components.DeleteHistoricalDatasetButton
import com.capybara.intuition.features.profile.tester.components.GenerateHistoricalDatasetButton
import com.capybara.intuition.features.profile.tester.components.HistoricalDatasetGroup
import com.capybara.intuition.features.profile.tester.components.HistoricalDatasetStatus
import com.capybara.intuition.features.profile.tester.components.MotivationBoostSelector
import com.capybara.intuition.features.profile.tester.components.MotivationScaleSelector
import com.capybara.intuition.features.profile.tester.components.SelfConfidenceSelector
import com.capybara.intuition.features.profile.tester.components.generator.HistoricalDatasetManager
import com.capybara.intuition.features.profile.tester.components.wrappers.HistoricalDatasetPeriodSelector
import com.capybara.intuition.features.profile.tester.components.wrappers.TrainingIntensitySelector
import com.capybara.intuition.features.profile.tester.model.HistoricalDatasetRequest
import com.capybara.intuition.features.profile.tester.model.TrainingIntensity
import kotlinx.coroutines.launch

private val DEFAULT_DATASET_REQUEST =
    HistoricalDatasetRequest(
        periodDays = 30,
        trainingIntensity = TrainingIntensity.MEDIUM,
        minLevel = 2,
        maxLevel = 12,
        competence = 0.6,
        selfConfidence = 0.6,
        motivationBoostPerPeriod = 4.0, // per month
        motivationScale = 20.0,
        lastSessionId = -1
    )

@Composable
fun TesterToolsScreen (
    historicalDatasetManager: HistoricalDatasetManager,
    modifier: Modifier = Modifier
){

    val colors = IntuitionTheme.colors
    val typography = IntuitionTheme.typography

    val coroutineScope = rememberCoroutineScope()

    var request by remember {
        mutableStateOf(
            DEFAULT_DATASET_REQUEST
        )
    }

    var statusMessage by remember {
        mutableStateOf<String?>(
            null
        )
    }

    var source by remember {
        mutableStateOf<String?>(
            null
        )
    }
    var operationResult by remember {
        mutableStateOf<Boolean?>(
            null
        )
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {

            ChangeLicenseTypeSection()

            Spacer(
                Modifier.height(
                    16.dp
                )
            )

            SettingsSection(
                title = stringResource(
                    R.string.settings_historical_dataset_title
                )
            ) {

                HistoricalDatasetGroup(
                    title = stringResource(
                        R.string.settings_historical_dataset_scope
                    )
                ) {
                    HistoricalDatasetPeriodSelector(
                        selectedPeriodDays = request.periodDays,
                        onPeriodSelected = {
                            request = request.copy(
                                periodDays = it
                            )
                        }
                    )
                }

                HistoricalDatasetGroup(
                    title = stringResource(
                        R.string.settings_historical_behavior_model
                    )
                ) {

                    TrainingIntensitySelector(
                        selectedValue = request.trainingIntensity,
                        onValueSelected = {
                            request = request.copy(
                                trainingIntensity = it
                            )
                        }
                    )

                    CompetenceSelector(
                        value = request.competence,
                        onValueChange = {
                            request = request.copy(
                                competence = it
                            )
                        }
                    )

                    SelfConfidenceSelector(
                        value = request.selfConfidence,
                        onValueChange = {
                            request = request.copy(
                                selfConfidence = it
                            )
                        }
                    )

                    MotivationBoostSelector(
                        value = request.motivationBoostPerPeriod.toInt(),
                        onValueChange = {
                            request = request.copy(
                                motivationBoostPerPeriod = it.toDouble()
                            )
                        }
                    )

                    MotivationScaleSelector(
                        value = request.motivationScale.toInt(),
                        onValueChange = {
                            request = request.copy(
                                motivationScale = it.toDouble()
                            )
                        }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                    )

                    GenerateHistoricalDatasetButton(
                        onClick = {
                            coroutineScope.launch {
                                if (
                                    historicalDatasetManager.generate(
                                        request
                                    )
                                ) {
                                    source = "generator"
                                    operationResult = true
                                } else {
                                    source = "generator"
                                    operationResult = false
                                }
                            }
                        }
                    )

                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                    )

                    DeleteHistoricalDatasetButton(
                        onClick = {
                            coroutineScope.launch {
                                if (
                                    historicalDatasetManager.clear()
                                ) {
                                    source = "cleaner"
                                    operationResult = true
                                } else {
                                    source = "cleaner"
                                    operationResult = false
                                }
                            }
                        }
                    )

                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                    )
                }

                HistoricalDatasetStatus(
                    source = source,
                    operationResult = operationResult
                )
            }
        }
    }


}