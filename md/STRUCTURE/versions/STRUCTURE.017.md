## Version: 0.17
## Last Updated: 2026-06-21

Intuition/app/src/main/java/com/example/intuition$ tree
.
├── app
│   ├── App.kt
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
│   ├── MainActivity.kt
│   ├── navigation
│   │   ├── AppDestination.kt
│   │   └── AppNavHost.kt
│   ├── presentation
│   │   └── theme
│   │       ├── IntuitionColors.kt
│   │       ├── theme.kt
│   │       └── Type.kt
│   └── ui
│       ├── components
│       │   └── topnav
│       │       ├── NavIcon.kt
│       │       └── TopNavigation.kt
│       └── IntuitionApp.kt
├── core
│   ├── capability
│   │   ├── CapabilityEnvironment.kt
│   │   ├── configs
│   │   │   ├── BuildConfiguration.kt
│   │   │   ├── ProductionFreeCapabilities.kt
│   │   │   ├── ProductionPremiumCapabilities.kt
│   │   │   ├── TestFreeCapabilities.kt
│   │   │   └── TestPremiumCapabilities.kt
│   │   ├── LicenseRuntime.kt
│   │   ├── RuntimeCapabilities.kt
│   │   └── CapabilityFactory.kt
│   ├── config
│   │   └── FeatureFlags.kt
│   ├── license
│   │   ├── data
│   │   │   └── db
│   │   │       ├── LicenseDao.kt
│   │   │       └── LicenseEntity.kt
│   │   ├── domain
│   │   │   └── LicenseType.kt
│   │   ├── LicenseInitializer.kt
│   │   └── presentation
│   └── ui
│       └── layout
│           ├── AppLayoutEnvironment.kt
│           ├── LocalAppLayoutEnvironment.kt
│           └── rememberAppLayoutEnvironment.kt
├── game
│   ├── data
│   │   ├── local
│   │   │   └── db
│   │   │       ├── AppDatabase.kt
│   │   │       ├── bootstrap
│   │   │       │   ├── BootstrapLoader.kt
│   │   │       │   ├── TopologyStyleProjection.kt
│   │   │       │   ├── BootstrapTopologyCell.kt
│   │   │       │   ├── BootstrapTopologyFamilyRelation.kt
│   │   │       │   ├── MaskSeeder.kt
│   │   │       │   ├── NotebookResourceLinkSeeder.kt
│   │   │       │   ├── NotebookSeeder.kt
│   │   │       │   ├── ProfileSeeder.kt
│   │   │       │   ├── RendererSeeder.kt
│   │   │       │   ├── ResourceSeeder.kt
│   │   │       │   ├── TopologyBootstrapModels.kt
│   │   │       │   └── TopologySeeder.kt
│   │   │       ├── dao
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
│   │   │       ├── projection
│   │   │       │   └── WinLoseCount.kt
│   │   │       └── relation
│   │   │           ├── NotebookWithResources.kt
│   │   │           └── TopologyWithCells.kt
│   │   ├── mapper
│   │   │   ├── GameSessionMapper.kt
│   │   │   ├── ResourceEntityMapper.kt
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
│   │   │   └── RendererResolver.kt
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
│       │   │   │   ├── gamer
│       │   │   │   │   └── ProfileName.kt
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
│       │   │       └── geometry
│       │   │           ├── calculateGameGridParams.kt
│       │   │           ├── CellGeometry.kt
│       │   │           └── GameGridParams.kt
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
├── installation
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
│   │   │       │   ├── SecurityEventEntity.kt
│   │   │       │   └── TesterProfileSnapshot.kt
│   │   │       ├── mapper
│   │   │       │   └── ProfileMapper.kt
│   │   │       └── relation
│   │   │           └── ProfileWithTopology.kt
│   │   └── repository
│   │       ├── ProfileRepository.kt
│   │       └── ProfileSecurityRepository.kt
│   ├── domain
│   │   ├── model
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
│   └── presentation
│       └── viewmodel
│           └── ProfileViewModel.kt
├── settings
│   └── presentation
│       ├── routes
│       │   └── SettingsRoute.kt
│       └── ui
│           ├── components
│           │   ├── SettingsOptionRow.kt
│           │   └── SettingsSection.kt
│           ├── sections
│           │   ├── ChildProfileCreateSection.kt
│           │   ├── FreeHumaneSetupProfileSection.kt
│           │   ├── LevelSettingsSection.kt
│           │   ├── ModeSettingsSection.kt
│           │   ├── ParentAccessRecoverySection.kt
│           │   ├── PremiumHumanProfileSetupSection.kt
│           │   ├── ProfileList.kt
│           │   ├── ReturnToParentByPinSection.kt
│           │   └── tester
│           │       ├── ChangeLicenseTypeSection.kt
│           │       ├── components
│           │       │   ├── ChipSelector.kt
│           │       │   ├── DeleteHistoricalDatasetButton.kt
│           │       │   ├── FloatSliderSelector.kt
│           │       │   ├── GenerateHistoricalDatasetButton.kt
│           │       │   ├── generator
│           │       │   │   ├── HistoricalDatasetGenerator.kt
│           │       │   │   └── HistoricalDatasetManager.kt
│           │       │   ├── HistoricalDatasetGroup.kt
│           │       │   ├── HistoricalDatasetStatus.kt
│           │       │   └── wrappers
│           │       │       ├── BehavioralPatternSelector.kt
│           │       │       ├── DensityVarianceSelector.kt
│           │       │       ├── HistoricalDatasetPeriodSelector.kt
│           │       │       ├── LifeScenarioSelector.kt
│           │       │       ├── TrainingIntensitySelector.kt
│           │       │       └── TrendAmplitudeSelector.kt
│           │       ├── HistoricalDatasetSection.kt
│           │       ├── model
│           │       │   ├── BehavioralPattern.kt
│           │       │   ├── GeneratedHistoricalDataset.kt
│           │       │   ├── GeneratedRound.kt
│           │       │   ├── GeneratedSession.kt
│           │       │   ├── HistoricalDataset.kt
│           │       │   ├── HistoricalDatasetRequest.kt
│           │       │   ├── LifeScenario.kt
│           │       │   ├── RecoveryError.kt
│           │       │   └── TrainingIntensity.kt
│           │       └── repository
│           │           └── HistoricalDatasetRepository.kt
│           └── SettingsScreen.kt
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
│   │   │   └── corridor
│   │   │       └── RandomnessEnvelopeEngine.kt
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
│   │   └── pipeline
│   │       ├── DensityPipelineBuilder.kt
│   │       └── DensityPipelinePoint.kt
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
│       │   ├── SelectedGraph.kt
│       │   ├── SessionDensityStats.kt
│       │   ├── SessionFilter.kt
│       │   ├── StatisticsUiState.kt
│       │   └── TimeChartStyle.kt
│       ├── routes
│       │   └── StatisticsRoute.kt
│       ├── ui
│       │   ├── free
│       │   │   ├── FreeStatisticsActivitySection.kt
│       │   │   ├── FreeStatisticsScreen.kt
│       │   │   └── header
│       │   │       └── FreeStatisticsHeader.kt
│       │   ├── model
│       │   │   ├── PivotMode.kt
│       │   │   └── StatisticsSelectionState.kt
│       │   ├── premium
│       │   │   ├── components
│       │   │   │   ├── PivotSwitch.kt
│       │   │   │   └── SessionFilterSelector.kt
│       │   │   ├── header
│       │   │   │   └── PremiumStatisticsHeader.kt
│       │   │   ├── PremiumStatisticsScreen.kt
│       │   │   └── sections
│       │   │       ├── PremiumDensityDetailSection.kt
│       │   │       ├── PremiumStatisticsActivitySection.kt
│       │   │       └── PremiumStatisticsDensitySection.kt
│       │   ├── shared
│       │   │   └── components
│       │   │       ├── blocks
│       │   │       │   ├── ActivitySectionHeader.kt
│       │   │       │   └── GraphBlock.kt
│       │   │       ├── cards
│       │   │       │   └── DensityCard.kt
│       │   │       ├── charts
│       │   │       │   ├── BarChart.kt
│       │   │       │   ├── draw
│       │   │       │   │   └── DrawEnvelope.kt
│       │   │       │   ├── LineChart.kt
│       │   │       │   ├── XAxis.kt
│       │   │       │   └── YAxis.kt
│       │   │       ├── DensityDetailContent.kt
│       │   │       ├── dialogs
│       │   │       │   └── MarkdownDialog.kt
│       │   │       ├── grid
│       │   │       │   ├── GridCell.kt
│       │   │       │   ├── LevelGrid.kt
│       │   │       │   └── ModeGrid.kt
│       │   │       ├── LevelSelector.kt
│       │   │       └── SelectorChip.kt
│       │   └── StatisticsScreen.kt
│       └── viewmodel
│           ├── StatisticsViewModelFactory.kt
│           └── StatisticsViewModel.kt
└── VERSION
