## Version: 0.17
## Last Updated: 2026-07-19

Intuition/app/src/main/java/com/capybara/intuition$ tree
.
├── app
│   ├── AppDatabase.kt
│   ├── App.kt
│   ├── billing
│   │   ├── BillingConnectionManager.kt
│   │   ├── BillingPurchaseProcessor.kt
│   │   ├── BillingSynchronizationProcessor.kt
│   │   ├── GooglePlayBillingManager.kt
│   │   ├── handlers
│   │   │   ├── handleAlreadyOwned.kt
│   │   │   ├── handleGoogleError.kt
│   │   │   ├── handleGoogleOk.kt
│   │   │   ├── handleOk
│   │   │   │   ├── handlePendingPurchase.kt
│   │   │   │   ├── handleSuccessfullPurchase.kt
│   │   │   │   ├── handleUnexpectedEmptyPurchases.kt
│   │   │   │   ├── handleUnexpectedPurchaseState.kt
│   │   │   │   └── handleUnknownPurchase.kt
│   │   │   └── handleUserCanceled.kt
│   │   ├── purchase
│   │   │   ├── createBillingFlowParams.kt
│   │   │   ├── createQueryProductDetailsParams.kt
│   │   │   ├── resolveLicenseType.kt
│   │   │   └── resolveProductId.kt
│   │   └── ui
│   │       ├── components
│   │       │   ├── PurchaseFailedSection.kt
│   │       │   ├── PurchaseLoadingSection.kt
│   │       │   ├── PurchaseOfferSection.kt
│   │       │   ├── PurchasePendingSection.kt
│   │       │   ├── PurchaseSuccessSection.kt
│   │       │   └── PurchaseUnknownResultSection.kt
│   │       ├── LicensePurchaseDialog.kt
│   │       └── model
│   │           ├── BillingConnectionState.kt
│   │           ├── BillingEvent.kt
│   │           ├── BillingState.kt
│   │           └── BillingUiState.kt
│   ├── bootstrap
│   │   ├── BootstrapLoader.kt
│   │   ├── colors
│   │   │   ├── NotebookResourceLinkSeeder.kt
│   │   │   ├── NotebookSeeder.kt
│   │   │   └── ResourceSeeder.kt
│   │   ├── gameModes
│   │   │   └── GameModeSeeder.kt
│   │   ├── masks
│   │   │   └── MaskSeeder.kt
│   │   ├── ProfileSeeder.kt
│   │   ├── renderers
│   │   │   └── RendererSeeder.kt
│   │   └── topology
│   │       ├── BootstrapTopologyCell.kt
│   │       ├── BootstrapTopologyFamilyRelation.kt
│   │       ├── TopologyBootstrapModels.kt
│   │       ├── TopologySeeder.kt
│   │       └── TopologyStyleProjection.kt
│   ├── BuildConfiguration.kt
│   ├── composition
│   │   ├── AppContainer.kt
│   │   └── modules
│   │       ├── GameplayModule.kt
│   │       ├── InstallationModule.kt
│   │       ├── LicenseModule.kt
│   │       ├── profile
│   │       │   ├── ProfileResourcesModule.kt
│   │       │   ├── ProfileSecurityModule.kt
│   │       │   └── ProfileTopologyModule.kt
│   │       ├── ProfileModule.kt
│   │       ├── StatisticsModule.kt
│   │       └── TesterModule.kt
│   ├── DataStoreAdapter.kt
│   ├── ExitDialog.kt
│   ├── installation
│   │   ├── data
│   │   │   └── local
│   │   │       ├── db
│   │   │       │   ├── dao
│   │   │       │   │   └── InstallationDao.kt
│   │   │       │   └── entity
│   │   │       │       └── InstallationEntity.kt
│   │   │       └── repository
│   │   │           └── InstallationRepository.kt
│   │   └── domain
│   │       └── installation
│   │           └── InstallationInitializer.kt
│   ├── IntuitionApp.kt
│   ├── jobs
│   │   └── JobManager.kt
│   ├── license
│   │   ├── capability
│   │   │   ├── configs
│   │   │   │   ├── ProductionFreeCapabilities.kt
│   │   │   │   ├── ProductionPremiumCapabilities.kt
│   │   │   │   ├── TestFreeCapabilities.kt
│   │   │   │   └── TestPremiumCapabilities.kt
│   │   │   └── RuntimeCapabilities.kt
│   │   ├── data
│   │   │   ├── datastore
│   │   │   │   ├── LicenseDataStore.kt
│   │   │   │   └── SynchronizationDataStore.kt
│   │   │   └── repository
│   │   │       ├── LicenseRepository.kt
│   │   │       └── SynchronizationRepository.kt
│   │   └── domain
│   │       ├── CapabilityEnvironment.kt
│   │       └── LicenseType.kt
│   ├── MainActivity.kt
│   ├── navigation
│   │   ├── AppDestination.kt
│   │   ├── AppNavHost.kt
│   │   └── ui
│   │       └── topnav
│   │           ├── NavIcon.kt
│   │           └── TopNavigation.kt
│   ├── presentation
│   │   ├── components
│   │   ├── layout
│   │   │   ├── AppLayoutEnvironment.kt
│   │   │   ├── LocalAppLayoutEnvironment.kt
│   │   │   └── rememberAppLayoutEnvironment.kt
│   │   ├── styles
│   │   │   └── IntuitionOutlinedTextFieldStyle.kt
│   │   └── theme
│   │       ├── colors
│   │       │   └── IntuitionColors.kt
│   │       ├── theme.kt
│   │       └── typography
│   │           ├── ChartTypography.kt
│   │           ├── IntuitionTypography.kt
│   │           └── Type.kt
│   └── tools
│       └── markdown
│           ├── ActivityLegendImage.kt
│           ├── DensityPreviewLegendImage.kt
│           ├── HelpActivityChartImage.kt
│           ├── HelpDecisionTimeChartImage.kt
│           ├── HelpFullDensityChartImage.kt
│           ├── MarkdownImage.kt
│           └── MarkdownText.kt
├── game
│   ├── data
│   │   ├── local
│   │   │   └── db
│   │   │       ├── dao
│   │   │       │   ├── GameModeDao.kt
│   │   │       │   ├── GameRoundDao.kt
│   │   │       │   ├── MaskDao.kt
│   │   │       │   ├── NotebookDao.kt
│   │   │       │   ├── NotebookResourceLinkDao.kt
│   │   │       │   ├── RendererDao.kt
│   │   │       │   ├── ResourceDao.kt
│   │   │       │   ├── SessionDao.kt
│   │   │       │   └── topology
│   │   │       │       ├── TopologyDao.kt
│   │   │       │       └── TopologyFamilyDao.kt
│   │   │       ├── entity
│   │   │       │   ├── GameModeEntity.kt
│   │   │       │   ├── GameSessionEntity.kt
│   │   │       │   ├── MaskEntity.kt
│   │   │       │   ├── NotebookEntity.kt
│   │   │       │   ├── NotebookResourceLinkEntity.kt
│   │   │       │   ├── ObservedGameCardEntity.kt
│   │   │       │   ├── ObservedRoundEntity.kt
│   │   │       │   ├── RendererEntity.kt
│   │   │       │   ├── ResourceEntity.kt
│   │   │       │   └── topology
│   │   │       │       ├── TopologyCellEntity.kt
│   │   │       │       ├── TopologyEntity.kt
│   │   │       │       ├── TopologyFamilyEntity.kt
│   │   │       │       └── TopologyFamilyRelationEntity.kt
│   │   │       └── relation
│   │   │           ├── NotebookWithResources.kt
│   │   │           └── TopologyWithCells.kt
│   │   ├── mapper
│   │   │   ├── GameModeMapper.kt
│   │   │   ├── GameSessionMapper.kt
│   │   │   ├── ResourceEntityMapper.kt
│   │   │   ├── RoundResultMapper.kt
│   │   │   ├── RoundTypeMapper.kt
│   │   │   └── topology
│   │   │       └── TopologyFamilyEntity.kt
│   │   └── repository
│   │       ├── GameRoundRepository.kt
│   │       ├── GameSessionRepository.kt
│   │       └── NotebookRepository.kt
│   ├── domain
│   │   ├── events
│   │   │   └── GameEvent.kt
│   │   ├── handlers
│   │   │   ├── handleCreateRound.kt
│   │   │   ├── handleCreateSession.kt
│   │   │   ├── handlePlaceSystemBet.kt
│   │   │   ├── handlePlaceUserBet.kt
│   │   │   ├── handleResolveRound.kt
│   │   │   └── HandlerResult.kt
│   │   ├── model
│   │   │   ├── BetLevel.kt
│   │   │   ├── GameContext.kt
│   │   │   ├── GameMode.kt
│   │   │   ├── GameSession.kt
│   │   │   ├── GameSet.kt
│   │   │   ├── Resource.kt
│   │   │   ├── RoundResult.kt
│   │   │   ├── RoundType.kt
│   │   │   └── topology
│   │   │       ├── TopologyCell.kt
│   │   │       ├── TopologyFamily.kt
│   │   │       ├── Topology.kt
│   │   │       └── TopologyProjection.kt
│   │   ├── renderer
│   │   │   ├── BetButtonRenderer.kt
│   │   │   ├── color
│   │   │   │   └── renderer
│   │   │   │       ├── CheckerRenderer.kt
│   │   │   │       ├── CircumferenceRenderer.kt
│   │   │   │       └── SolidFillRenderer.kt
│   │   │   ├── image
│   │   │   │   └── renderer
│   │   │   │       ├── DirectImageRenderer.kt
│   │   │   │       └── DistortedImageRenderer.kt
│   │   │   └── rendererResolver.kt
│   │   ├── resource
│   │   │   └── ResourceType.kt
│   │   ├── round
│   │   │   └── GameRound.kt
│   │   └── router
│   │       ├── RoundFlowRouter.kt
│   │       └── RuntimeDependencies.kt
│   └── presentation
│       ├── layout
│       │   ├── calculateGameScreenLayout.kt
│       │   ├── GameLayoutMetrics.kt
│       │   ├── GameScreenLayout.kt
│       │   └── rememberGameLayoutMetrics.kt
│       ├── routes
│       │   └── GameRoute.kt
│       ├── ui
│       │   ├── components
│       │   │   ├── bottomPanel
│       │   │   │   ├── BottomPanel.kt
│       │   │   │   ├── bubble
│       │   │   │   │   ├── Bubble.kt
│       │   │   │   │   └── BubblePhraseContext.kt
│       │   │   │   ├── capybara
│       │   │   │   │   ├── CapybaraActor.kt
│       │   │   │   │   ├── emotional
│       │   │   │   │   │   ├── calculatePrefixProbability.kt
│       │   │   │   │   │   ├── calculateSingleBetProbability.kt
│       │   │   │   │   │   ├── calculateSingleStreakSessionProbability.kt
│       │   │   │   │   │   ├── CapybaraEmotion.kt
│       │   │   │   │   │   ├── countCompatibleSequences.kt
│       │   │   │   │   │   ├── estimateSessionScore.kt
│       │   │   │   │   │   └── mapScoreToEmotion.kt
│       │   │   │   │   └── model
│       │   │   │   │       ├── CapybaraEvent.kt
│       │   │   │   │       ├── CapybaraSpriteMetadata.kt
│       │   │   │   │       ├── CapybaraSprites.kt
│       │   │   │   │       ├── CapybaraTone.kt
│       │   │   │   │       └── SpeechAnchor.kt
│       │   │   │   ├── geometry
│       │   │   │   │   ├── resolveBubbleAvailableArea.kt
│       │   │   │   │   └── resolveBubbleOccupiedArea.kt
│       │   │   │   ├── indicators
│       │   │   │   │   ├── AnimatedIndicatorText.kt
│       │   │   │   │   └── Indicators.kt
│       │   │   │   └── model
│       │   │   │       ├── BottomPanelContext.kt
│       │   │   │       ├── BottomPanelSceneBounds.kt
│       │   │   │       ├── ScenePoint.kt
│       │   │   │       └── SceneRect.kt
│       │   │   └── gameGrid
│       │   │       ├── BetButton.kt
│       │   │       ├── GameGrid.kt
│       │   │       ├── geometry
│       │   │       │   ├── calculateGameGridParams.kt
│       │   │       │   ├── CellGeometry.kt
│       │   │       │   └── GameGridParams.kt
│       │   │       ├── MaskIds.kt
│       │   │       └── RendererIds.kt
│       │   └── GameScreen.kt
│       └── viewmodel
│           ├── GameViewModelFactory.kt
│           └── GameViewModel.kt
├── info
│   └── presentation
│       ├── model
│       ├── routes
│       │   └── InfoRoute.kt
│       └── ui
│           ├── components
│           │   ├── InfoMarkdownSection.kt
│           │   ├── InfoNavigation.kt
│           │   └── InfoSectionType.kt
│           └── InfoScreen.kt
├── profile
│   ├── data
│   │   ├── local
│   │   │   └── db
│   │   │       ├── converter
│   │   │       │   └── ProfileTypeConverter.kt
│   │   │       ├── dao
│   │   │       │   └── ProfileDao.kt
│   │   │       ├── entity
│   │   │       │   ├── ProfileEntity.kt
│   │   │       │   └── TesterProfileSnapshot.kt
│   │   │       ├── mapper
│   │   │       │   └── ProfileMapper.kt
│   │   │       └── relation
│   │   │           └── ProfileWithTopology.kt
│   │   └── repository
│   │       └── ProfileRepository.kt
│   ├── domain
│   │   ├── model
│   │   │   ├── PersonalizationSettings.kt
│   │   │   ├── Profile.kt
│   │   │   ├── ProfileSecurityState.kt
│   │   │   ├── ProfileType.kt
│   │   │   ├── recovery
│   │   │   │   ├── ArithmeticOperation.kt
│   │   │   │   ├── ChallengeOperand.kt
│   │   │   │   ├── ChallengeTask.kt
│   │   │   │   ├── ChallengeValueSourceType.kt
│   │   │   │   └── TemporalRelation.kt
│   │   │   └── SecurityEventType.kt
│   │   ├── recovery
│   │   │   └── RecoveryChallengeGenerator.kt
│   │   └── security
│   │       ├── PinHasher.kt
│   │       └── Sha256PinHasher.kt
│   ├── presentation
│   │   ├── routes
│   │   │   └── SettingsRoute.kt
│   │   ├── ui
│   │   │   ├── components
│   │   │   │   ├── SettingsOptionRow.kt
│   │   │   │   └── SettingsSection.kt
│   │   │   ├── sections
│   │   │   │   ├── free
│   │   │   │   │   ├── FreeHumanProfileSetupSection.kt
│   │   │   │   │   ├── FreePersonalizationSetupSection.kt
│   │   │   │   │   └── ModeRestrictedSection.kt
│   │   │   │   ├── LevelSettingsSection.kt
│   │   │   │   ├── premium
│   │   │   │   │   ├── ChildProfileCreateSection.kt
│   │   │   │   │   ├── ModeSettingsSection.kt
│   │   │   │   │   ├── ParentAccessRecoverySection.kt
│   │   │   │   │   ├── PremiumHumanProfileSetupSection.kt
│   │   │   │   │   ├── PremiumPersonalizationSetupSection.kt
│   │   │   │   │   └── ReturnToParentByPinSection.kt
│   │   │   │   └── ProfileList.kt
│   │   │   └── SettingsScreen.kt
│   │   └── viewmodel
│   │       └── ProfileViewModel.kt
│   └── tester
│       ├── components
│       │   ├── CompetenceSelector.kt
│       │   ├── DeleteHistoricalDatasetButton.kt
│       │   ├── GenerateHistoricalDatasetButton.kt
│       │   ├── generator
│       │   │   ├── CreateHistoricalDatasetManager.kt
│       │   │   ├── HistoricalDatasetGenerator.kt
│       │   │   ├── HistoricalDatasetManager.kt
│       │   │   └── ver2
│       │   │       ├── TimeLineDay.kt
│       │   │       └── tools
│       │   │           ├── calculators
│       │   │           │   ├── biorhythm
│       │   │           │   │   ├── calculateBiorhythm.kt
│       │   │           │   │   ├── calculateEmotionalFactor.kt
│       │   │           │   │   ├── calculateIntellectFactor.kt
│       │   │           │   │   └── calculatePhysicalFactor.kt
│       │   │           │   ├── calculateBaseDecisionTime.kt
│       │   │           │   ├── calculateDailyMotivation.kt
│       │   │           │   ├── calculateMotivationBoost.kt
│       │   │           │   ├── calculateSessionsCount.kt
│       │   │           │   ├── rounds
│       │   │           │   │   └── calculateRoundsCount.kt
│       │   │           │   └── sessions
│       │   │           │       └── distributeSessionsByLevels.kt
│       │   │           ├── generators
│       │   │           │   ├── generateRounds.kt
│       │   │           │   ├── generateSessions.kt
│       │   │           │   └── generateTimeline.kt
│       │   │           └── math
│       │   │               ├── gaussian.kt
│       │   │               ├── randomValues.kt
│       │   │               └── sinus01.kt
│       │   ├── HistoricalDatasetGroup.kt
│       │   ├── HistoricalDatasetStatus.kt
│       │   ├── MotivationBoostSelector.kt
│       │   ├── MotivationScaleSelector.kt
│       │   ├── SelfConfidenceSelector.kt
│       │   ├── TesterChipSelector.kt
│       │   └── wrappers
│       │       ├── HistoricalDatasetPeriodSelector.kt
│       │       └── TrainingIntensitySelector.kt
│       ├── model
│       │   ├── BehavioralPattern.kt
│       │   ├── GeneratedHistoricalDataset.kt
│       │   ├── GeneratedRound.kt
│       │   ├── GeneratedSession.kt
│       │   ├── HistoricalDatasetRequest.kt
│       │   ├── LifeScenario.kt
│       │   ├── RecoveryError.kt
│       │   └── TrainingIntensity.kt
│       ├── presentation
│       │   ├── routes
│       │   │   └── TesterToolsRoute.kt
│       │   └── ui
│       │       ├── ChangeLicenseTypeSection.kt
│       │       ├── LicenseChipSelector.kt
│       │       └── TesterToolsScreen.kt
│       └── repository
│           └── HistoricalDatasetRepository.kt
└── statistics
    ├── data
    │   ├── local
    │   │   └── db
    │   │       └── dao
    │   │           └── StatisticsDao.kt
    │   └── repository
    │       └── StatisticsRepository.kt
    ├── domain
    │   └── insight
    │       └── model
    │           ├── PerformanceInsight.kt
    │           ├── PerformanceLevel.kt
    │           └── Trend.kt
    └── presentation
        ├── content
        │   └── MarkdownLoader.kt
        ├── mapper
        │   ├── GameModeUiMapper.kt
        │   └── PerformanceInsightMapper.kt
        ├── model
        │   ├── SelectedChart.kt
        │   └── SessionFilter.kt
        ├── routes
        │   └── StatisticsRoute.kt
        ├── ui
        │   ├── free
        │   │   ├── FreeStatisticsScreen.kt
        │   │   └── header
        │   │       └── FreeStatisticsHeader.kt
        │   ├── model
        │   │   ├── PivotMode.kt
        │   │   └── StatisticsSelectionState.kt
        │   ├── premium
        │   │   ├── components
        │   │   │   ├── PivotSwitch.kt
        │   │   │   └── SessionFilterSelector.kt
        │   │   ├── header
        │   │   │   ├── PremiumDensityDetailStatisticsHeader.kt
        │   │   │   └── PremiumStatisticsHeader.kt
        │   │   ├── PremiumStatisticsScreen.kt
        │   │   └── sections
        │   │       ├── PremiumDensityDetailSection.kt
        │   │       └── PremiumStatisticsDensitySection.kt
        │   ├── shared
        │   │   └── components
        │   │       ├── dialogs
        │   │       │   └── MarkdownDialog.kt
        │   │       ├── EmptyStatisticsScreen.kt
        │   │       ├── LevelSelector.kt
        │   │       ├── newGraphSystem
        │   │       │   ├── arts
        │   │       │   │   ├── activity
        │   │       │   │   │   ├── ActivityGraphic.kt
        │   │       │   │   │   └── components
        │   │       │   │   │       ├── activityGraphContentArea.kt
        │   │       │   │   │       ├── activityGraphStartPointArea.kt
        │   │       │   │   │       ├── activityGraphXAxisArea.kt
        │   │       │   │   │       ├── activityGraphYAxisArea.kt
        │   │       │   │   │       ├── activityGraphYTitleArea.kt
        │   │       │   │   │       └── content
        │   │       │   │   │           ├── drawActivityGraphActivityBars.kt
        │   │       │   │   │           ├── drawActivityGraphActivityValues.kt
        │   │       │   │   │           ├── drawActivityGraphHorizontalGrid.kt
        │   │       │   │   │           └── drawActivityGraphVerticalGrid.kt
        │   │       │   │   ├── decisionTime
        │   │       │   │   │   ├── components
        │   │       │   │   │   │   ├── content
        │   │       │   │   │   │   │   ├── drawDecisionTimeGraphHorizontalGrid.kt
        │   │       │   │   │   │   │   ├── drawDecisionTimeGraphLine.kt
        │   │       │   │   │   │   │   └── drawDecisionTimeGraphVerticalGrid.kt
        │   │       │   │   │   │   ├── drawDecisionTimeGraphContentArea.kt
        │   │       │   │   │   │   ├── drawDecisionTimeGraphStartPointArea.kt
        │   │       │   │   │   │   ├── drawDecisionTimeGraphXAxisArea.kt
        │   │       │   │   │   │   ├── drawDecisionTimeGraphYAxisArea.kt
        │   │       │   │   │   │   └── drawDecisionTimeGraphYTitleArea.kt
        │   │       │   │   │   └── DecisionTimeGraphic.kt
        │   │       │   │   ├── fullDensity
        │   │       │   │   │   ├── components
        │   │       │   │   │   │   ├── content
        │   │       │   │   │   │   │   ├── drawDensityFullGraphBaselineCorridor.kt
        │   │       │   │   │   │   │   ├── drawDensityFullGraphBaseline.kt
        │   │       │   │   │   │   │   ├── drawDensityFullGraphHorizontalGrid.kt
        │   │       │   │   │   │   │   ├── drawDensityFullGraphUserLine.kt
        │   │       │   │   │   │   │   └── drawDensityFullGraphVerticalGrid.kt
        │   │       │   │   │   │   ├── drawDensityFullGraphContentArea.kt
        │   │       │   │   │   │   ├── drawDensityFullGraphStartPointArea.kt
        │   │       │   │   │   │   ├── drawDensityFullGraphXAxisArea.kt
        │   │       │   │   │   │   ├── drawDensityFullGraphYAxisArea.kt
        │   │       │   │   │   │   ├── drawDensityFullGraphYTitleArea.kt
        │   │       │   │   │   │   └── header
        │   │       │   │   │   │       └── ChartHeader.kt
        │   │       │   │   │   └── DensityFullGraphic.kt
        │   │       │   │   └── preview
        │   │       │   │       ├── components
        │   │       │   │       │   ├── content
        │   │       │   │       │   │   ├── drawPreviewGraphBaseline.kt
        │   │       │   │       │   │   └── drawPreviewGraphUserLine.kt
        │   │       │   │       │   ├── drawPreviewDensityGraphContentArea.kt
        │   │       │   │       │   ├── drawPreviewDensityGraphTitleArea.kt
        │   │       │   │       │   └── drawPreviewDensityGraphYAxisArea.kt
        │   │       │   │       └── previewDensityGraphic.kt
        │   │       │   ├── calculators
        │   │       │   │   ├── applyDayDensities.kt
        │   │       │   │   ├── buildChartPoints.kt
        │   │       │   │   ├── buildChartPointsTimeDecision.kt
        │   │       │   │   ├── buildSmoothPath.kt
        │   │       │   │   ├── buildSmoothPathTimeDecision.kt
        │   │       │   │   ├── calculateAnalyticsChartPoints.kt
        │   │       │   │   ├── calculateEmaAlpha.kt
        │   │       │   │   ├── calculateEma.kt
        │   │       │   │   ├── calculateRequiredHistoryDays.kt
        │   │       │   │   ├── enrichWithCorridors.kt
        │   │       │   │   ├── enrichWithEma.kt
        │   │       │   │   ├── graphLayoutsModels
        │   │       │   │   │   ├── getActivityGraphLayoutModel.kt
        │   │       │   │   │   ├── getDecisionTimeGraphLayoutModel.kt
        │   │       │   │   │   ├── getFullDensityGraphLayoutModel.kt
        │   │       │   │   │   └── getPreviewDensityGraphLayoutModel.kt
        │   │       │   │   ├── trimAnalyticPeriod.kt
        │   │       │   │   └── yScale
        │   │       │   │       ├── calculateActivityYScale.kt
        │   │       │   │       ├── calculateDecisionTimeYScale.kt
        │   │       │   │       ├── calculateFullDensityYScale.kt
        │   │       │   │       ├── calculatePreviewDensityYScale.kt
        │   │       │   │       └── model
        │   │       │   │           ├── ActivityYScaleModel.kt
        │   │       │   │           ├── DecisionTimeYScaleModel.kt
        │   │       │   │           ├── FullDensityYScaleModel.kt
        │   │       │   │           ├── PreviewDensityYScaleModel.kt
        │   │       │   │           └── TimeMeasureUnit.kt
        │   │       │   ├── model
        │   │       │   │   ├── chartsAreas
        │   │       │   │   │   ├── Area.kt
        │   │       │   │   │   ├── DecisionTimeGraphLayoutModel.kt
        │   │       │   │   │   ├── FullDensityGraphLayoutModel.kt
        │   │       │   │   │   └── PreviewDensityGraphLayoutModel.kt
        │   │       │   │   ├── data
        │   │       │   │   │   ├── AnalyticsChartPoint.kt
        │   │       │   │   │   ├── DayDensity.kt
        │   │       │   │   │   ├── PreviewByLevelRequest.kt
        │   │       │   │   │   ├── PreviewByLevelResponse.kt
        │   │       │   │   │   ├── PreviewByModeRequest.kt
        │   │       │   │   │   ├── PreviewByModeResponse.kt
        │   │       │   │   │   └── StatisticsQueryContext.kt
        │   │       │   │   └── EmaTarget.kt
        │   │       │   └── tools
        │   │       │       └── BuildEmptyChart.kt
        │   │       └── StatisticsChipSelector.kt
        │   └── StatisticsScreen.kt
        └── viewmodel
            ├── StatisticsViewModelFactory.kt
            └── StatisticsViewModel.kt
