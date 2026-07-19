## Version: 0.14
## Last Updated: 2026-05-02

Intuition/app/src/main$ tree java/
java/
└── com
    └── example
        └── intuition
            ├── app
            │   ├── App.kt
            │   ├── di
            │   │   └── AppContainer.kt
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
            │   └── seed
            │       └── BehaviorScenarioGenerator.kt
            ├── game
            │   ├── data
            │   │   ├── local
            │   │   │   └── db
            │   │   │       ├── AppDatabase.kt
            │   │   │       ├── dao
            │   │   │       │   ├── GameRoundDao.kt
            │   │   │       │   └── SessionDao.kt
            │   │   │       ├── entity
            │   │   │       │   ├── CardOptionEntity.kt
            │   │   │       │   ├── GameRoundRecordEntity.kt
            │   │   │       │   └── SessionEntity.kt
            │   │   │       └── projection
            │   │   │           └── WinLoseCount.kt
            │   │   ├── mapper
            │   │   │   ├── GameRoundMappers.kt
            │   │   │   ├── GameRoundRecordMapper.kt
            │   │   │   ├── GameRoundToRecordMapper.kt
            │   │   │   └── GameSessionMapper.kt
            │   │   └── repository
            │   │       ├── GameRoundRepository.kt
            │   │       └── GameSessionRepository.kt
            │   ├── domain
            │   │   ├── config
            │   │   │   ├── BetLayerConfig.kt
            │   │   │   ├── BetLayerConfigs.kt
            │   │   │   ├── BetLayoutConfig.kt
            │   │   │   └── GameConfig.kt
            │   │   ├── engine
            │   │   │   ├── DefaultGameModeStrategy.kt
            │   │   │   ├── GameEngine.kt
            │   │   │   ├── GameModeStrategy.kt
            │   │   │   └── GameRound.kt
            │   │   ├── events
            │   │   │   └── GameEvent.kt
            │   │   ├── library
            │   │   │   └── DefaultCardLibrary.kt
            │   │   └── model
            │   │       ├── BetButtonId.kt
            │   │       ├── BetLevel.kt
            │   │       ├── CardId.kt
            │   │       ├── CardOptionRecord.kt
            │   │       ├── FillSpec.kt
            │   │       ├── GameCard.kt
            │   │       ├── GameMode.kt
            │   │       ├── GameRoundRecord.kt
            │   │       ├── GameRoundResult.kt
            │   │       ├── GameSession.kt
            │   │       ├── GameContext.kt
            │   │       ├── PostRoundActionType.kt
            │   │       ├── RoundPhase.kt
            │   │       ├── RoundResult.kt
            │   │       ├── RoundStep.kt
            │   │       ├── RoundType.kt
            │   │       └── ShapeSpec.kt
            │   └── presentation
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
            ├── settings
            │   └── presentation
            │       ├── model
            │       ├── routes
            │       │   └── SettingsRoute.kt
            │       └── ui
            │           └── SettingsScreen.kt
            ├── shared
            │   └── markdown
            │       └── render
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
            │   │   └── model
            │   │       ├── AggregatedSessions.kt
            │   │       ├── DensityMap.kt
            │   │       ├── GraphPoint.kt
            │   │       └── SessionDensityGraphData.kt
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
            └── VERSION


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


