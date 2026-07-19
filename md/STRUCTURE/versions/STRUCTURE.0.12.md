## Version: 0.12
## Last Updated: 2026-04-11

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
│   └── DebugDataInitializer.kt
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
│   │   ├── seed
│   │   │   └── BehaviorScenarioGenerator.kt
│   │   └── StatisticsRepository.kt
│   ├── domain
│   │   ├── engine
│   │   │   ├── SessionDensityEngine.kt
│   │   │   ├── SessionsAggregator.kt
│   │   │   └── SessionsActivityEngine.kt
│   │   └── model
│   │       ├── AggregatedSessions.kt
│   │       ├── DensityMap.kt
│   │       ├── GraphPoint.kt
│   │       └── SessionDensityGraphData.kt
│   ├── model
│   │   ├── XAxis.kt
│   │   └── YAxis.kt
│   └── presentation
│       ├── model
│       │   ├── DayBasedStats.kt
│       │   ├── SessionDensityStats.kt
│       │   └── StatisticsUiState.kt
│       ├── ui
│       │   ├── BarChart.kt
│       │   ├── components
│       │   │   └── GraphBlock.kt
│       │   ├── GraphData.kt
│       │   ├── LineChart.kt
│       │   └── StatisticsScreen.kt
│       └── viewmodel
│           ├── StatisticsViewModelFactory.kt
│           └── StatisticsViewModel.kt
└── VERSION

46 directories, 91 files

