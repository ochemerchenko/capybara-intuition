## Version: 0.09.001
## Last Updated: 2026-04-05

.
├── app
│   ├── MainActivity.kt
│   ├── ui
│   │   ├── IndicatorsFragment.kt
│   │   ├── InfoFragment.kt
│   │   ├── SettingsFragment.kt
│   │   ├── StatisticsFragment.kt
│   │   └── TopNavigationFragment.kt
│   └── viewmodel
│       └── MainSharedViewModel.kt
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
└── statistics
    ├── data
    │   ├── seed
    │   │   └── BehaviorScenarioGenerator.kt
    │   └── StatisticsRepository.kt
    ├── domain
    │   ├── engine
    │   │   ├── SessionsAggregator.kt
    │   │   └── SessionsActivityEngine.kt
    │   └── model
    │       ├── GraphData.kt
    │       └── GraphPoint.kt
    ├── model
    │   ├── AggregatedSessions.kt
    │   ├── SessionCountPerDay.kt
    │   ├── XAxis.kt
    │   └── YAxis.kt
    ├── ui
    │   └── BarChart.kt
    └── viewmodel
        ├── StatisticsViewModelFactory.kt
        └── StatisticsViewModel.kt
