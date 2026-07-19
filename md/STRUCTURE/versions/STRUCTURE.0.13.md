## Version: 0.13
## Last Updated: 2026-04-12

.
├── app
│   ├── App.kt
│   ├── MainActivity.kt
│   ├── ui
│   │   ├── IndicatorsFragment.kt
│   │   ├── InfoFragment.kt
│   │   ├── SettingsFragment.kt
│   │   ├── StatisticsFragment.kt
│   │   └── TopNavigationFragment.kt
│   └── viewmodel
│       └── MainSharedViewModel.kt
├── debug
│   ├── DebugDataInitializer.kt
│   └── seed
│       └── BehaviorScenarioGenerator.kt
├── game
│   ├── data
│   │   ├── local
│   │   │   └── db
│   │   │       ├── AppDatabase.kt
│   │   │       ├── ColorInitializer.kt
│   │   │       ├── dao
│   │   │       │   ├── ColorDao.kt
│   │   │       │   ├── GameRoundDao.kt
│   │   │       │   └── SessionDao.kt
│   │   │       └── entity
│   │   │           ├── ColorEntity.kt
│   │   │           ├── CardOptionEntity.kt
│   │   │           ├── GameRoundRecordEntity.kt
│   │   │           └── SessionEntity.kt
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
│   │   │   └── BetLayoutConfig.kt
│   │   ├── engine
│   │   │   ├── DefaultGameModeStrategy.kt
│   │   │   ├── GameEngine.kt
│   │   │   ├── GameModeStrategy.kt
│   │   │   └── GameRound.kt
│   │   ├── events
│   │   │   └── GameEvent.kt
│   │   ├── model
│   │   │   ├── BetButtonId.kt
│   │   │   ├── BetLevel.kt
│   │   │   ├── CardId.kt
│   │   │   ├── ColorCard.kt
│   │   │   ├── CardOptionRecord.kt
│   │   │   ├── GameColor.kt
│   │   │   ├── GameColorPalette.kt
│   │   │   ├── GameMode.kt
│   │   │   ├── GamePhase.kt
│   │   │   ├── GameRandom.kt
│   │   │   ├── GameRoundRecord.kt
│   │   │   ├── GameRoundResult.kt
│   │   │   ├── GameSession.kt
│   │   │   ├── GameContext.kt
│   │   │   ├── PostRoundActionType.kt
│   │   │   ├── RoundPhase.kt
│   │   │   ├── RoundResult.kt
│   │   │   ├── RoundStep.kt
│   │   │   └── RoundType.kt
│   │   └── reducer
│   │       └── SelectCardReducer.kt
│   ├── ui
│   │   ├── fragments
│   │   │   └── GameFragment.kt
│   │   ├── GameUiMapper.kt
│   │   ├── mapper
│   │   └── model
│   │       ├── GameUiOption.kt
│   │       ├── GameUiResult.kt
│   │       ├── GameUiState.kt
│   │       ├── Position.kt
│   │       └── UiCard.kt
│   └── viewmodel
│       ├── GameViewModelFactory.kt
│       └── GameViewModel.kt
├── shared
│   ├── navigation
│   │   ├── NavControllerExt.kt
│   │   └── NavEvent.kt
│   ├── ui
│   │   ├── binding
│   │   │   └── HtmlBindingAdapters.java
│   │   └── components
│   │       └── MarkdownText.kt
│   └── utils
│       └── MarkdownLoader.kt
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
│   │   ├── engine
│   │   │   ├── SessionDensityEngine.kt
│   │   │   ├── SessionsAggregator.kt
│   │   │   └── SessionsActivityEngine.kt
│   │   ├── insight
│   │   │   └── InsightBuilder.kt
│   │   └── model
│   │       ├── AggregatedSessions.kt
│   │       ├── DensityMap.kt
│   │       ├── GraphPoint.kt
│   │       └── SessionDensityGraphData.kt
│   └── presentation
│       ├── model
│       │   ├── DayBasedStats.kt
│       │   ├── DensityCardInput.kt
│       │   ├── GraphData.kt
│       │   ├── LevelGridInput.kt
│       │   ├── LineChartStyle.kt
│       │   ├── ModeGridInput.kt
│       │   ├── PivotMode.kt
│       │   ├── SelectedGraph.kt
│       │   ├── SessionDensityStats.kt
│       │   └── StatisticsUiState.kt
│       ├── ui
│       │   ├── components
│       │   │   ├── blocks
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
│       │   │   └── grid
│       │   │       ├── GridCell.kt
│       │   │       ├── LevelGrid.kt
│       │   │       └── ModeGrid.kt
│       │   ├── DensityDetailScreen.kt
│       │   └── StatisticsScreen.kt
│       └── viewmodel
│           ├── StatisticsViewModelFactory.kt
│           └── StatisticsViewModel.kt
└── VERSION
