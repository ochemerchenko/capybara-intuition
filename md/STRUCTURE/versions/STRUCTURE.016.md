## Version: 0.16
## Last Updated: 2026-05-25

Intuition/app/src/main/java/com/example/intuition$ tree
.
├── app
│   ├── App.kt
│   ├── di
│   │   ├── AppContainer.kt
│   │   └── LocalAppContainer.kt
│   ├── MainActivity.kt
│   ├── navigation
│   │   ├── AppDestination.kt
│   │   └── AppNavHost.kt
│   ├── presentation
│   │   └── theme
│   │       ├── IntuitionColors.kt
│   │       ├── theme.kt
│   │       └── Type.kt
│   ├── ui
│   │   ├── components
│   │   │   └── topnav
│   │   │       ├── NavIcon.kt
│   │   │       └── TopNavigation.kt
│   │   └── IntuitionApp.kt
│   └── viewmodel
│       └── MainSharedViewModel.kt
├── core
│   ├── capability
│   │   ├── CapabilityEnvironment.kt
│   │   ├── configs
│   │   │   ├── ProductionFreeCapabilities.kt
│   │   │   ├── ProductionPremiumCapabilities.kt
│   │   │   ├── TestFreeCapabilities.kt
│   │   │   └── TestPremiumCapabilities.kt
│   │   ├── RuntimeCapabilities.kt
│   │   ├── CapabilityFactory.kt
│   │   └── LicenseRuntime.kt
│   ├── config
│   │   └── FeatureFlags.kt
│   └── ui
│       └── layout
│           ├── AppColors.kt
│           ├── AppLayoutEnvironment.kt
│           ├── colors
│           │   ├── factory
│           │   │   └── rememberAppColors.kt
│           │   └── SettingsColors.kt
│           ├── LocalAppLayoutEnvironment.kt
│           └── rememberAppLayoutEnvironment.kt
├── debug
│   ├── DebugDataInitializer.kt
│   ├── scenario
│   │   ├── BehavioralScenario.kt
│   │   ├── EvidenceVolume.kt
│   │   ├── model
│   │   │   └── ScenarioTarget.kt
│   │   ├── ScenarioConfig.kt
│   │   └── TrainingCadence.kt
│   ├── seed
│   │   └── BehaviorScenarioGenerator.kt
│   └── simulation
│       ├── BehaviorSimulationEngine.kt
│       ├── ChronologyGenerator.kt
│       └── SessionFactory.kt
├── game
│   ├── data
│   │   ├── local
│   │   │   └── db
│   │   │       ├── AppDatabase.kt
│   │   │       ├── bootstrap
│   │   │       │   ├── BootstrapLoader.kt
│   │   │       │   ├── MaskSeeder.kt
│   │   │       │   ├── NotebookResourceLinkSeeder.kt
│   │   │       │   ├── NotebookSeeder.kt
│   │   │       │   ├── ProfileSeeder.kt
│   │   │       │   ├── RendererSeeder.kt
│   │   │       │   └── ResourceSeeder.kt
│   │   │       ├── dao
│   │   │       │   ├── GameRoundDao.kt
│   │   │       │   ├── LayoutConfigDao.kt
│   │   │       │   ├── LayoutPositionDao.kt
│   │   │       │   ├── MaskDao.kt
│   │   │       │   ├── NotebookDao.kt
│   │   │       │   ├── NotebookResourceLinkDao.kt
│   │   │       │   ├── RendererDao.kt
│   │   │       │   ├── ResourceDao.kt
│   │   │       │   └── SessionDao.kt
│   │   │       ├── entity
│   │   │       │   ├── GameSessionEntity.kt
│   │   │       │   ├── LayoutConfigEntity.kt
│   │   │       │   ├── LayoutPositionEntity.kt
│   │   │       │   ├── MaskEntity.kt
│   │   │       │   ├── NotebookEntity.kt
│   │   │       │   ├── NotebookResourceLinkEntity.kt
│   │   │       │   ├── ObservedRoundEntity.kt
│   │   │       │   ├── RendererEntity.kt
│   │   │       │   └── ResourceEntity.kt
│   │   │       ├── projection
│   │   │       │   └── WinLoseCount.kt
│   │   │       └── relation
│   │   │           └── NotebookWithResources.kt
│   │   ├── mapper
│   │   │   ├── GameRoundToRecordMapper.kt
│   │   │   ├── GameSessionMapper.kt
│   │   │   ├── ObservedRoundEntity.kt
│   │   │   └── ResourceEntityMapper.kt
│   │   └── repository
│   │       ├── GameRoundRepository.kt
│   │       ├── GameSessionRepository.kt
│   │       ├── LayoutRepository.kt
│   │       └── NotebookRepository.kt
│   ├── domain
│   │   ├── config
│   │   │   ├── BetLayerConfig.kt
│   │   │   └── GameConfig.kt
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
│   │   │   ├── GameMode.kt
│   │   │   ├── GameRoundRecord.kt
│   │   │   ├── GameSession.kt
│   │   │   ├── GameSet.kt
│   │   │   ├── GameContext.kt
│   │   │   ├── Resource.kt
│   │   │   ├── RoundResult.kt
│   │   │   └── RoundType.kt
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
│   │   │   └── RendererResolver.kt
│   │   ├── resource
│   │   │   └── ResourceType.kt
│   │   ├── round
│   │   │   └── GameRound.kt
│   │   ├── router
│   │   │   ├── RoundFlowRouter.kt
│   │   │   └── RuntimeDependencies.kt
│   │   ├── stimulus
│   │   │   ├── MaskType.kt
│   │   │   └── RendererCategory.kt
│   │   └── topology
│   │       ├── matrix
│   │       │   ├── NarrowToWideTopology.kt
│   │       │   └── WideToNarrowTopology.kt
│   │       └── LegacyTopology.kt
│   └── presentation
│       ├── components
│       ├── layout
│       │   ├── GameLayoutMetrics.kt
│       │   └── rememberGameLayoutMetrics.kt
│       ├── routes
│       │   └── GameRoute.kt
│       ├── ui
│       │   ├── capybara
│       │   │   ├── BubbleShape.kt
│       │   │   ├── BubbleWithTail.kt
│       │   │   ├── CapybaraContext.kt
│       │   │   ├── CapybaraEvent.kt
│       │   │   ├── Capybara.kt
│       │   │   ├── CapybaraTextMapper.kt
│       │   │   ├── CapybaraTextProvider.kt
│       │   │   └── CapybaraTone.kt
│       │   ├── components
│       │   │   ├── BetButton.kt
│       │   │   ├── BottomPanel.kt
│       │   │   ├── CapybaraView.kt
│       │   │   ├── GameGrid.kt
│       │   │   └── Indicators.kt
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
│           └── InfoScreen.kt
├── profile
│   ├── data
│   │   ├── local
│   │   │   └── db
│   │   │       ├── converter
│   │   │       │   └── ProfileTypeConverter.kt
│   │   │       ├── dao
│   │   │       │   ├── ProfileDao.kt
│   │   │       │   ├── ProfileSecurityStateDao.kt
│   │   │       │   └── SecurityEventDao.kt
│   │   │       ├── entity
│   │   │       │   ├── ProfileEntity.kt
│   │   │       │   ├── ProfileSecurityStateEntity.kt
│   │   │       │   └── SecurityEventEntity.kt
│   │   │       └── mapper
│   │   │           └── ProfileMapper.kt
│   │   └── repository
│   │       ├── ProfileRepository.kt
│   │       └── ProfileSecurityRepository.kt
│   ├── domain
│   │   ├── config
│   │   │   └── ProfileConfig.kt
│   │   ├── model
│   │   │   ├── Profile.kt
│   │   │   ├── ProfileSecurityState.kt
│   │   │   ├── ProfileType.kt
│   │   │   ├── recovery
│   │   │   │   └── RecoveryChallenge.kt
│   │   │   └── SecurityEventType.kt
│   │   ├── recovery
│   │   │   └── RecoveryChallengeGenerator.kt
│   │   └── security
│   │       ├── PinHasher.kt
│   │       ├── recovery
│   │       │   ├── RecoveryChallengeGenerator.kt
│   │       │   └── RecoveryChallenge.kt
│   │       └── Sha256PinHasher.kt
│   └── presentation
│       └── viewmodel
│           └── ProfileViewModel.kt
├── settings
│   └── presentation
│       ├── model
│       ├── routes
│       │   └── SettingsRoute.kt
│       ├── ui
│       │   ├── components
│       │   │   ├── SettingsOptionRow.kt
│       │   │   └── SettingsSection.kt
│       │   ├── content
│       │   │   └── ParentSettingsContent.kt
│       │   ├── sections
│       │   │   ├── CreateNewPinSection.kt
│       │   │   ├── DebugActionsSection.kt
│       │   │   ├── DebugCadenceSection.kt
│       │   │   ├── DebugScenarioSection.kt
│       │   │   ├── DebugVolumeSection.kt
│       │   │   ├── ParentAccessRecoverySection.kt
│       │   │   └── ready
│       │   │       ├── ChildProfileCreateSection.kt
│       │   │       ├── FreeHumaneSetupProfileSection.kt
│       │   │       ├── LevelSettingsSection.kt
│       │   │       ├── ModeSettingsSection.kt
│       │   │       ├── PremiumHumanProfileSetupSection.kt
│       │   │       ├── ProfileList.kt
│       │   │       └── ReturnToParentByPinSection.kt
│       │   └── SettingsScreen.kt
│       └── viewmodel
│           └── SettingsViewModel.kt
├── shared
│   └── markdown
│       └── render
│           ├── ActivityLegendImage.kt
│           ├── DensityPreviewLegendImage.kt
│           ├── MarkdownImage.kt
│           └── MarkdownText.kt
├── statistics
│   ├── data
│   │   ├── local
│   │   │   └── db
│   │   │       ├── dao
│   │   │       │   └── StatisticsDao.kt
│   │   │       └── model
│   │   │           ├── SessionCountPerDay.kt
│   │   │           └── SessionDensityRaw.kt
│   │   └── repository
│   │       └── StatisticsRepository.kt
│   ├── domain
│   │   ├── analytics
│   │   │   ├── bootstrap
│   │   │   │   └── BootstrapGenerator.kt
│   │   │   ├── corridor
│   │   │   │   └── RandomnessEnvelopeEngine.kt
│   │   │   ├── insight
│   │   │   └── trend
│   │   ├── config
│   │   │   └── StatsConfig.kt
│   │   ├── engine
│   │   │   ├── SessionDensityEngine.kt
│   │   │   ├── SessionsActivityEngine.kt
│   │   │   └── SessionsAggregator.kt
│   │   ├── insight
│   │   │   ├── InsightBuilder.kt
│   │   │   └── model
│   │   │       ├── PerformanceInsight.kt
│   │   │       ├── PerformanceLevel.kt
│   │   │       └── Trend.kt
│   │   ├── model
│   │   │   ├── AggregatedSessions.kt
│   │   │   ├── DensityMap.kt
│   │   │   ├── GraphPoint.kt
│   │   │   ├── RandomnessEnvelope.kt
│   │   │   └── SessionDensityGraphData.kt
│   │   ├── pipeline
│   │   │   ├── DensityPipelineBuilder.kt
│   │   │   ├── DensityPipelinePoint.kt
│   │   │   └── DensityViewport.kt
│   │   └── transform
│   │       ├── DensityCompression.kt
│   │       └── DensityProjection.kt
│   └── presentation
│       ├── content
│       │   └── MarkdownLoader.kt
│       ├── mapper
│       │   ├── GameModeUiMapper.kt
│       │   └── PerformanceInsightMapper.kt
│       ├── model
│       │   ├── ActivitySectionType.kt
│       │   ├── DayBasedStats.kt
│       │   ├── DensityCardInput.kt
│       │   ├── GraphData.kt
│       │   ├── LevelGridInput.kt
│       │   ├── LineChartStyle.kt
│       │   ├── ModeGridInput.kt
│       │   ├── PivotMode.kt
│       │   ├── SelectedGraph.kt
│       │   ├── SessionDensityStats.kt
│       │   ├── StatisticsUiState.kt
│       │   └── TimeChartStyle.kt
│       ├── routes
│       │   └── StatisticsRoute.kt
│       ├── ui
│       │   ├── components
│       │   │   ├── blocks
│       │   │   │   ├── ActivitySectionHeader.kt
│       │   │   │   └── GraphBlock.kt
│       │   │   ├── cards
│       │   │   │   └── DensityCard.kt
│       │   │   ├── charts
│       │   │   │   ├── BarChart.kt
│       │   │   │   ├── draw
│       │   │   │   │   └── DrawEnvelope.kt
│       │   │   │   ├── LineChart.kt
│       │   │   │   ├── XAxis.kt
│       │   │   │   └── YAxis.kt
│       │   │   ├── controls
│       │   │   │   ├── LevelSelector.kt
│       │   │   │   ├── ModeSelector.kt
│       │   │   │   ├── PivotSwitch.kt
│       │   │   │   └── SelectorChip.kt
│       │   │   ├── dialogs
│       │   │   │   └── MarkdownDialog.kt
│       │   │   └── grid
│       │   │       ├── GridCell.kt
│       │   │       ├── LevelGrid.kt
│       │   │       └── ModeGrid.kt
│       │   ├── DensityDetailScreen.kt
│       │   ├── StatisticsActivitySection.kt
│       │   ├── StatisticsDensitySection.kt
│       │   └── StatisticsScreen.kt
│       └── viewmodel
│           ├── StatisticsViewModelFactory.kt
│           └── StatisticsViewModel.kt
├── telemetry
│   ├── data
│   │   └── local
│   │       ├── datasource
│   │       ├── db
│   │       │   ├── dao
│   │       │   │   └── InstallationDao.kt
│   │       │   └── entity
│   │       │       └── InstallationEntity.kt
│   │       └── repository
│   │           └── InstallationRepository.kt
│   ├── domain
│   │   ├── export
│   │   ├── installation
│   │   │   └── InstallationInitializer.kt
│   │   └── model
│   └── runtime
│       └── InstallationRuntime.kt
└── VERSION
