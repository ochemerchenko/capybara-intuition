# Game Data Model

## Version: 0.14

## Last Updated: 2026-05-02

---

# Overview

The gameplay subsystem is built around:

* deterministic round lifecycle
* event-driven state transitions
* immutable runtime state
* analytics-oriented persistence
* strict separation between runtime and analytics

The system is intentionally designed so that:

* gameplay writes data
* analytics only reads data
* all behavioral interpretation derives from persisted records

---

# 🧠 Core Principle

Gameplay flow:

```text
UI
→ ViewModel
→ GameEngine
→ Mapper
→ Repository
→ DB
```

Analytics NEVER modifies gameplay.

---

# 🎮 Runtime Domain Model

Located in:

```text
game/domain/model
```

---

# GameState

Single source of truth for runtime gameplay.

---

## Responsibilities

Contains:

* current session state
* current round
* current mode
* current level
* round phase
* runtime interaction state

---

## Important Rule

GameState is:

* runtime-only
* mutable through GameEngine only
* NOT persisted directly

---

# GameRound

Located in:

```text
game/domain/engine/GameRound.kt
```

Represents active runtime round.

---

## Contains

* options
* correct answer
* selected answer
* runtime phase
* roundType
* timestamps
* runtime interaction state

---

## Lifecycle

```text
OPTIONS_READY
↓
(selection)
↓
RESOLVED
```

---

# RoundPhase

Located in:

```text
game/domain/model/RoundPhase.kt
```

---

## OPTIONS_READY

User may interact.

---

## RESOLVED

Round result already determined.

Input ignored.

---

# RoundResult

Located in:

```text
game/domain/model/RoundResult.kt
```

---

## Values

* WIN
* LOSE
* ABANDONED

---

# Game Modes

Located in:

```text
game/domain/model/GameMode.kt
```

---

## BEFORE

user → system

---

## AFTER

system → user

---

## HYBRID

Round type selected dynamically.

---

# RoundType

Located in:

```text
game/domain/model/RoundType.kt
```

Defines actual round behavior inside session.

---

# Session Model

---

# GameSession

Located in:

```text
game/domain/model/GameSession.kt
```

Represents continuous gameplay without parameter changes.

---

## Session Ends When

* level changes
* user exits
* process death
* runtime reset

---

## Session Types

* BEFORE
* AFTER
* HYBRID

---

# 🎯 Gameplay Data Model

---

# GameRoundRecord

Located in:

```text
game/domain/model/GameRoundRecord.kt
```

Persistent analytics-oriented round model.

---

# Identity

```kotlin
sessionId: String
roundIndex: Int
```

---

# Context

```kotlin
mode: GameMode
roundType: RoundType
level: Int
configId: Int
```

---

# Timing

---

## Core Timing

```kotlin
startedAt: Long
finishedAt: Long
```

---

## User Timing

```kotlin
userSelectedAt: Long?
```

Timestamp when user selected an option.

---

```kotlin
resultShownAt: Long?
```

Timestamp when result became visible.

---

```kotlin
firstUserActionAt: Long?
```

First action after result screen.

---

```kotlin
postRoundActionType: PostRoundActionType?
```

Behavior after result:

* next round
* level change
* navigation
* exit

---

# Selection Data

---

## Selected Position

```kotlin
selectedPosition: Position?
```

Nullable:

* null = abandoned/no selection

---

## Correct Position

```kotlin
correctPosition: Position
```

---

# Options

```kotlin
options: List<ColorOptionRecord>
```

---

# Result

```kotlin
result: RoundResult
```

---

# 📦 Option Model

---

# ColorOptionRecord

Located in:

```text
game/domain/model/CardOptionRecord.kt
```

Represents single option shown to user.

---

## Fields

Contains:

* visual identity
* semantic identity
* rendering information

---

## Constraints

Inside one round:

* unique only
* no duplicates

---

# 🎲 Randomness Model

Randomness used for:

* option generation
* correct answer selection
* hybrid round type selection
* debug seed generation

---

# ⚡ Timing Model

---

# Decision Time

```text
userSelectedAt - startedAt
```

Measures:

* reaction speed
* hesitation
* confidence patterns

---

# Post-result Delay

```text
firstUserActionAt - resultShownAt
```

Measures:

* reflection behavior
* emotional pause
* continuation patterns

---

# Behavioral Continuation

Captured via:

```kotlin
postRoundActionType
```

---

# 📊 Analytics Relationship

Game data is intentionally analytics-oriented.

---

# Session-based Metrics

Derived from:

```text
GameRoundRecord
```

Examples:

* total rounds
* win count
* session density

---

# Time-based Metrics

Derived from:

* startedAt
* finishedAt

Examples:

* sessions per day
* activity trends
* engagement patterns

---

# Behavioral Metrics

Derived from:

* decision time
* post-result delay
* continuation behavior

---

# 🧱 Persistence Layer

Located in:

```text
game/data/local/db
```

---

# Main Tables

---

## SessionEntity

Persistent gameplay session.

---

## GameRoundRecordEntity

Persistent round record.

---

## CardOptionEntity

Persistent option representation.

---

# DAO Layer

Located in:

```text
game/data/local/db/dao
```

---

## SessionDao

Handles:

* session persistence
* session retrieval

---

## GameRoundDao

Handles:

* round persistence
* round queries

---

# Mapping Layer

Located in:

```text
game/data/mapper
```

---

# Responsibilities

Transforms:

```text
Runtime models
↓
Persistent models
↓
Analytics-ready records
```

---

# Important Rule

Persistence mapping is isolated from GameEngine.

GameEngine:

* does not know DB
* does not know Room
* does not create entities

---

# 🧠 GameEngine

Located in:

```text
game/domain/engine/GameEngine.kt
```

Pure deterministic reducer.

---

# Responsibilities

* apply events
* produce new GameState
* control round lifecycle
* validate transitions

---

# Constraints

GameEngine:

* no persistence
* no side effects
* no timing tracking
* no UI logic

---

# 🎮 Game Events

Located in:

```text
game/domain/events/GameEvent.kt
```

Game is event-driven.

---

# Examples

* start round
* select option
* confirm result
* change level
* switch mode

---

# 📱 UI Model Layer

Located in:

```text
game/presentation/model
```

---

# GameUiState

Presentation-oriented state.

---

## Fields

```kotlin
options: List<GameUiOption>
result: GameUiResult?
isRoundActive: Boolean
isMainButtonEnabled: Boolean
capybaraMood: CapybaraMood
```

---

# Purpose

Separates:

* runtime domain
* presentation rendering

---

# GameUiOption

UI-ready option representation.

---

# GameUiResult

UI-oriented result abstraction.

Values:

* WIN
* LOSE

---

# 🐹 Emotional Layer

---

# CapybaraMood

Located in:

```text
game/presentation/model/CapybaraMood.kt
```

---

## Values

* IDLE
* WIN
* LOSE

---

# Purpose

Controls:

* emotional visual feedback
* companion appearance

---

# CapybaraEvent

Located in:

```text
game/presentation/ui/capybara/CapybaraEvent.kt
```

Controls:

* animation behavior
* motion style
* transition profile

---

# Important Separation

```text
CapybaraMood
≠
CapybaraEvent
```

---

## Mood

Visual appearance.

---

## Event

Animation behavior.

---

# 🔒 Constraints

---

## Gameplay Constraints

* levels: 2–12
* unique options per round
* resolved round immutable
* repeated input ignored

---

## Persistence Constraints

* analytics derives from raw records only
* no redundant aggregate storage
* no cached analytics

---

## Analytics Constraints

Game subsystem:

* never computes analytics
* never generates insights
* never interprets behavior

---

# 🧠 Design Decisions

---

## Runtime vs Persistence Separation

Runtime:

* optimized for interaction

Persistence:

* optimized for analytics

---

## Analytics-first Persistence

GameRoundRecord intentionally stores:

* behavioral timing
* interaction patterns
* contextual metadata

Reason:

* future analytics flexibility

---

## Pure Engine Design

GameEngine intentionally isolated from:

* DB
* Compose
* navigation
* analytics

---

# 🚧 Known Limitations (0.14)

* no multiplayer
* no cloud sync
* no session resume after process death
* no advanced behavioral interpretation in gameplay layer
* no adaptive difficulty

---

# 🔜 Evolution Path

---

## Near-term (0.14)

* analytics validation
* low-data behavior testing
* capybara transition polish
* internal testing stabilization

---

## Mid-term

* richer behavioral metrics
* consistency tracking
* hesitation analysis
* session weighting

---

## Long-term

* adaptive systems
* personalization
* AI-assisted interpretation
* cloud synchronization

