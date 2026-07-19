# ARCHITECTURE

## Version: 0.14

## Last Updated: 2026-05-02

---

# Overview

The system follows a layered architecture with strict separation between:

* gameplay (write path)
* analytics (read-only path)
* presentation/UI infrastructure

The project is intentionally designed around:

* deterministic state management
* pure transformation layers
* explainable analytics
* scalable UI composition

---

# 🧠 Core Principle

The application is split into two independent flows:

## 🎮 Write Path (Game)

UI
→ ViewModel
→ GameEngine
→ Mapper
→ Repository
→ DB

---

## 📊 Read Path (Statistics)

DB
→ DAO
→ Repository
→ Engine
→ ViewModel
→ UI

---

# 🔑 Key Rules

## Statistics is read-only

Statistics:

* never modifies gameplay state
* never writes to DB
* never affects GameEngine

---

## Engines are pure

All domain engines:

* have no side effects
* have no UI knowledge
* have no DB awareness
* are deterministic

---

## UI is state-driven

Compose UI renders only from reactive state:

* no hidden mutable UI logic
* no implicit synchronization
* state flows top → down

---

# 🎮 Game Layer (Write Path)

---

# GameEngine

Location:

```text
game/domain/engine/GameEngine.kt
```

Pure state machine.

---

## Responsibilities

* handles game flow
* applies events
* produces new GameState
* controls round lifecycle

---

## Constraints

* no persistence
* no DB access
* no side effects
* no timing tracking

---

# GameState

Single source of truth for runtime gameplay state.

Contains:

* current round
* mode
* level
* round phase
* runtime interaction state

---

# Game Events

Location:

```text
game/domain/events
```

Game flow is event-driven.

Examples:

* round start
* selection
* next round
* parameter changes

---

# Mapping Layer

Transforms:

```text
GameRound + GameState
→ GameRoundRecord
```

---

## Responsibilities

* builds persistent records
* computes derived fields
* maps runtime → storage model

---

# ViewModel Layer (Game)

Location:

```text
game/presentation/viewmodel
```

Bridge between UI and domain.

---

## Responsibilities

* orchestrates gameplay lifecycle
* dispatches events to GameEngine
* exposes reactive UI state
* persists rounds/sessions
* tracks timing behavior

---

## User Timing Tracking

Tracked outside GameEngine:

* userSelectedAt
* resultShownAt
* firstUserActionAt
* postRoundActionType

---

# Persistence Layer

---

## Database

Room database:

```text
game/data/local/db
```

---

## Main Tables

### sessions

Persistent gameplay sessions.

---

### round_records

Persistent round-level data.

---

## Persistence Rule

Analytics derives everything from raw round/session data.

No redundant analytics storage is used.

---

# 📊 Statistics Layer (Read Path)

Statistics is a read-only analytics subsystem.

---

# Statistics Flow

```text
DB
→ DAO
→ Repository
→ Engine
→ ViewModel
→ UI
```

---

# DAO Layer

Location:

```text
statistics/data/local/db/dao
```

---

## StatisticsDao

Responsibilities:

* aggregation queries
* grouping
* filtering
* SQL-level efficiency

---

## Main Projections

### SessionCountPerDay

Used for:

* activity charts
* sessions-per-day graphs

---

### SessionDensityRaw

Used for:

* behavioral analytics
* session density computation

---

# Repository Layer

Location:

```text
statistics/data/repository
```

---

## Responsibilities

* defines time windows
* delegates filtering to DAO
* returns raw analytics data

---

# Engine Layer (Analytics Core)

Location:

```text
statistics/domain/engine
```

Pure transformation pipeline.

---

# SessionsActivityEngine

Responsibilities:

* normalize day ranges
* fill missing dates
* prepare activity graphs

---

# SessionDensityEngine

Responsibilities:

* filter sessions
* compute density
* compress graph data
* calculate moving averages
* compute baseline

---

## Density Formula

```text
density = (winRounds / totalRounds) * 100
```

---

## Compression

Adaptive compression:

* target ≈ 80 points
* bucket aggregation
* trend-preserving

---

## Moving Average

Computed AFTER compression.

Windows:

* MA short = 5
* MA long = 10

---

## Baseline

```text
baseline = 100 / level
```

Represents expected random performance.

---

# Analytics Domain Models

Location:

```text
statistics/domain/model
```

---

## SessionDensityGraphData

Contains:

* raw graph
* short MA
* long MA
* baseline
* total sessions

---

## DensityMap

```kotlin
Map<GameMode?, Map<Int, SessionDensityGraphData>>
```

Supports:

* multi-graph exploration
* pivot navigation
* flexible UI grouping

---

# Insight System

Location:

```text
statistics/domain/insight
```

---

## InsightBuilder

Dedicated analytics interpretation layer.

Purpose:

* derive human-readable observations
* generate trend explanations
* compute behavioral insights

---

# 📱 Presentation Layer

---

# Compose Architecture

UI is fully Compose-driven.

Structure:

* routes
* screens
* sections
* reusable components

---

# Statistics UI Structure

Location:

```text
statistics/presentation/ui
```

---

## StatisticsScreen

Screen-level orchestration.

Responsibilities:

* state hoisting
* dialog control
* section composition
* navigation coordination

---

## StatisticsDensitySection

Handles:

* density graph selection
* LevelGrid / ModeGrid
* preview rendering
* contextual help

---

## StatisticsActivitySection

Handles:

* activity graphs
* expandable sections
* contextual help

---

# Grid System

---

## LevelGrid

Purpose:

* compare modes inside one level

Layout:

* fixed 2×2

---

## ModeGrid

Purpose:

* compare levels inside one mode

Layout:

* dynamic rows

---

# Detail Screens

## DensityDetailScreen

Provides:

* expanded graph
* axes
* legend
* contextual help
* detail exploration

---

# Markdown Help System

---

# Purpose

Used for:

* analytics explanations
* contextual help dialogs
* Info/About screen

---

# Architecture

Assets-driven system:

```text
assets/help/*.md
```

Localized:

* `.uk.md`
* `.en.md`

---

# Components

## MarkdownLoader

Loads localized markdown assets.

---

## MarkdownDialog

Reusable markdown-based dialog shell.

---

## Markdown Rendering

Shared renderer infrastructure:

```text
shared/markdown/render
```

Supports:

* markdown text
* semantic image tokens
* localized help content

---

# 🐹 Capybara System

---

# Purpose

Emotional companion layer.

Provides:

* feedback
* emotional reinforcement
* lightweight personality

---

# Architecture

Split into two independent systems:

---

## CapybaraEvent

Controls:

* animation behavior
* motion profile
* interaction response

Examples:

* OPTIONS_SHOWN
* RESULT_WIN
* RESULT_LOSE

---

## CapybaraMood

Controls:

* visual appearance

Values:

* IDLE
* WIN
* LOSE

---

# Capybara Rendering

---

## Capybara

High-level orchestration composable.

Controls:

* animation
* bubble positioning
* layout behavior

---

## CapybaraView

Pure visual renderer.

Responsible only for:

* mood-based image rendering

---

# Application Layer

Location:

```text
app/
```

---

## Responsibilities

* app initialization
* dependency wiring
* navigation
* theme
* shared application state

---

## AppContainer

Simple dependency container.

---

## MainSharedViewModel

Cross-screen shared state holder.

---

# Debug Infrastructure

Location:

```text
debug/
```

---

# DebugDataInitializer

Seeds database in DEBUG builds only.

---

# SessionSeedGenerator

Generates:

* realistic sessions
* timing distributions
* behavioral personas

---

# State Management Strategy

---

## Principles

* single source of truth
* immutable UI state
* state hoisting
* event-driven updates
* reactive rendering

---

## Compose Strategy

State ownership:

* screen owns state
* sections receive callbacks
* components remain mostly pure

---

# Current UX Model

---

# Statistics

Selection-driven exploration:

* no auto-rendered density graphs
* intentional navigation
* pivot-based discovery

---

# Pivot Navigation

Supported modes:

## LEVEL mode

Level → modes

---

## MODE mode

Mode → levels

---

# Day-Based Section

Always visible:

* independent from pivot selection
* global activity overview

---

# Interaction Model

* tap graph → detail screen
* contextual help available everywhere
* explainable analytics prioritized

---

# Performance Strategy

---

## Compression-first analytics

UI constraints drive:

* adaptive compression
* graph simplification
* selective rendering

---

## Recomposition Control

Focus areas:

* LazyColumn stability
* graph rendering efficiency
* dialog isolation
* animation stability

---

# Constraints

---

## Gameplay

* levels: 2–12
* unique options
* deterministic round resolution

---

## Analytics

* read-only
* no gameplay influence
* derived from raw records

---

## UX

* avoid graph overload
* prioritize clarity over density
* intentional exploration over dashboards

---

# Current State (0.14)

Implemented:

* analytics subsystem
* pivot navigation
* detail screens
* markdown help system
* explainable analytics
* capybara emotional feedback
* localized markdown content
* reusable dialog infrastructure
* mood-driven rendering

---

# Planned Evolution

---

## Near-term

* internal testing
* analytics validation
* low-data behavior tuning
* capybara transition polish
* UX hardening
* recomposition optimization

---

## Mid-term

* advanced insights
* consistency metrics
* volatility analysis
* session weighting

---

## Long-term

* Firebase sync
* cloud features
* advanced behavioral segmentation
* AI-assisted insights
* personalization

