## Version: 0.15
## Last Updated: 2026-05-13

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
│   ├── config
│   │   └── FeatureFlags.kt
│   └── ui
│       └── layout
│           ├── AppLayoutEnvironment.kt
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
│   │   │       ├── dao
│   │   │       │   ├── GameRoundDao.kt
│   │   │       │   ├── LayoutConfigDao.kt
│   │   │       │   ├── LayoutPositionDao.kt
│   │   │       │   └── SessionDao.kt
│   │   │       ├── entity
│   │   │       │   ├── LayoutConfigEntity.kt
│   │   │       │   ├── LayoutPositionEntity.kt
│   │   │       │   ├── ObservedRoundEntity.kt
│   │   │       │   ├── SessionEntity.kt
│   │   │       │   └── StimulusEntity.kt
│   │   │       └── projection
│   │   │           └── WinLoseCount.kt
│   │   ├── mapper
│   │   │   ├── GameRoundToRecordMapper.kt
│   │   │   ├── GameSessionMapper.kt
│   │   │   └── ObservedRoundEntity.kt
│   │   └── repository
│   │       ├── GameRoundRepository.kt
│   │       ├── GameSessionRepository.kt
│   │       └── LayoutRepository.kt
│   ├── domain
│   │   ├── bootstrap
│   │   │   └── LayoutInitializer.kt
│   │   ├── config
│   │   │   ├── BetLayerConfig.kt
│   │   │   ├── BetLayerConfigs.kt
│   │   │   └── GameConfig.kt
│   │   ├── engine
│   │   │   ├── DefaultGameModeStrategy.kt
│   │   │   ├── GameEngineHandlers.kt
│   │   │   ├── GameEngine.kt
│   │   │   ├── GameEngineLegacy.kt
│   │   │   ├── GameEngineTrace.kt
│   │   │   └── GameModeStrategy.kt
│   │   ├── events
│   │   │   └── GameEvent.kt
│   │   ├── layout
│   │   │   ├── LayoutTopology.kt
│   │   │   ├── LayoutTopologyPosition.kt
│   │   │   ├── LayoutTopologyResolver.kt
│   │   │   └── SemanticPosition.kt
│   │   ├── library
│   │   │   └── generateStimuli.kt
│   │   ├── model
│   │   │   ├── BetLevel.kt
│   │   │   ├── GameMode.kt
│   │   │   ├── GameRoundRecord.kt
│   │   │   ├── GameSession.kt
│   │   │   ├── GameContext.kt
│   │   │   ├── PostRoundActionType.kt
│   │   │   ├── RoundPhase.kt
│   │   │   ├── RoundResult.kt
│   │   │   ├── RoundStep.kt
│   │   │   └── RoundType.kt
│   │   ├── persistence
│   │   │   └── RoundPersistenceCoordinator.kt
│   │   ├── process
│   │   │   ├── condition
│   │   │   │   ├── TransitionConditionEvaluator.kt
│   │   │   │   └── TransitionCondition.kt
│   │   │   ├── event
│   │   │   │   └── RoundEvent.kt
│   │   │   ├── orchestration
│   │   │   │   ├── ExecutionPhase.kt
│   │   │   │   ├── ProcessFunctionExecutor.kt
│   │   │   │   ├── ProcessFunction.kt
│   │   │   │   ├── ProcessHookDefinition.kt
│   │   │   │   └── ProcessOrchestrator.kt
│   │   │   ├── runtime
│   │   │   │   ├── RoundOperationExecutor.kt
│   │   │   │   └── RoundRuntimeContext.kt
│   │   │   ├── spec
│   │   │   │   ├── RoundMachineSpec.kt
│   │   │   │   └── RoundProcessHooks.kt
│   │   │   ├── state
│   │   │   │   └── RoundState.kt
│   │   │   └── transition
│   │   │       ├── RoundTransition.kt
│   │   │       ├── RoundTransitionResolver.kt
│   │   │       └── TransitionFunction.kt
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
│   │   │   ├── color
│   │   │   │   ├── ColorDescriptor.kt
│   │   │   │   ├── ColorLibrary.kt
│   │   │   │   └── notebook
│   │   │   │       ├── CapybaraNotebook.kt
│   │   │   │       └── DefaultNotebook.kt
│   │   │   ├── image
│   │   │   │   ├── DrawableDescriptor.kt
│   │   │   │   ├── DrawableLibrary.kt
│   │   │   │   └── notebook
│   │   │   │       └── DefaultNotebook.kt
│   │   │   ├── ResourceDescriptor.kt
│   │   │   └── StimulusResourceResolver.kt
│   │   ├── round
│   │   │   ├── GameRound.kt
│   │   │   ├── RoundFactory.kt
│   │   │   └── RoundOption.kt
│   │   ├── state
│   │   └── stimulus
│   │       ├── MaskType.kt
│   │       ├── RendererCategory.kt
│   │       ├── ResourceCategory.kt
│   │       └── Stimulus.kt
│   └── presentation
│       ├── components
│       ├── layout
│       │   ├── GameLayoutMetrics.kt
│       │   └── rememberGameLayoutMetrics.kt
│       ├── mapper
│       │   └── GameUiMapper.kt
│       ├── model
│       │   ├── CapybaraMood.kt
│       │   ├── GameUiOption.kt
│       │   ├── GameUiResult.kt
│       │   ├── GameUiState.kt
│       │   └── Position.kt
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
│   │   │       ├── dao
│   │   │       │   ├── ProfileDao.kt
│   │   │       │   ├── ProfileSecurityStateDao.kt
│   │   │       │   └── SecurityEventDao.kt
│   │   │       └── entity
│   │   │           ├── ProfileEntity.kt
│   │   │           ├── ProfileSecurityStateEntity.kt
│   │   │           └── SecurityEventEntity.kt
│   │   └── repository
│   │       ├── ProfileRepository.kt
│   │       └── ProfileSecurityRepository.kt
│   ├── domain
│   │   ├── bootstrap
│   │   │   └── ProfileInitializer.kt
│   │   ├── config
│   │   │   └── ProfileConfig.kt
│   │   ├── manager
│   │   │   └── ProfileManager.kt
│   │   ├── model
│   │   │   ├── ProfileSecurityState.kt
│   │   │   ├── ProfileType.kt
│   │   │   └── SecurityEventType.kt
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
│       │   │   ├── ChildSettingsContent.kt
│       │   │   └── ParentSettingsContent.kt
│       │   ├── sections
│       │   │   ├── CreateNewPinSection.kt
│       │   │   ├── DebugActionsSection.kt
│       │   │   ├── DebugCadenceSection.kt
│       │   │   ├── DebugScenarioSection.kt
│       │   │   ├── DebugVolumeSection.kt
│       │   │   ├── LevelSettingsSection.kt
│       │   │   ├── ModeSettingsSection.kt
│       │   │   ├── ParentPinSection.kt
│       │   │   ├── ProfilesSection.kt
│       │   │   ├── RecoveryChallengeSection.kt
│       │   │   └── ReturnToParentSection.kt
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
├── VERSION
└── visual
    ├── data
    │   ├── bootstrap
    │   │   └── VisualConfigInitializer.kt
    │   ├── local
    │   │   └── db
    │   │       ├── dao
    │   │       │   └── VisualConfigDao.kt
    │   │       └── entity
    │   │           └── VisualConfigEntity.kt
    │   └── repository
    │       └── VisualConfigRepository.kt
    ├── domain
    │   └── model
    │       └── VisualConfig.kt
    └── presentation

Intuition/app/src/main$ tree assets/
assets/
└── help
    ├── about_game.en.md
    ├── about_game.uk.md
    ├── activity_chart_legend.en.md
    ├── activity_chart_legend.uk.md
    ├── density_chart_legend.en.md
    ├── density_chart_legend.uk.md
    ├── density_detail_legend.en.md
    └── density_detail_legend.uk.md


