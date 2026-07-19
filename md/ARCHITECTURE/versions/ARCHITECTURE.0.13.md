## Version: 0.13

## Last Updated: 2026-04-12

# Architecture

---

## Overview

The system follows a layered architecture with a strict separation between:

* Game (write path)
* Statistics (read-only analytics path)

---

## 🧠 Core Principle

The system is split into two independent flows:

### Write Path (Game)

UI → ViewModel → GameEngine → Mapper → Repository → DB

---

### Read Path (Statistics)

DB → DAO → Repository → Engine → ViewModel → UI

---

## 🔑 Key Rule

> Statistics is **read-only** and **does not affect gameplay**

* no writes to DB
* no influence on GameEngine
* no shared logic between Game and Analytics

---

# 🎮 Game Layer (Write Path)

## GameEngine

Pure state machine.

### Responsibilities

* handles game flow
* applies events → produces new state

### Constraints

* no persistence
* no side effects
* no database awareness

---

## Mapping Layer

Transforms:

GameRound + GameState → GameRoundRecord

### Responsibilities

* build persistent models
* compute derived fields (result, options)

---

## ViewModel (Game)

Bridge between UI and domain.

### Responsibilities

* dispatch events to GameEngine
* expose UI state
* persist round results

---

# 📊 Statistics Layer (Read Path)

Statistics is a **read-only analytics layer** built on persisted game data.

---

## 🧩 Data Flow

DB → DAO → Repository → Engine → ViewModel → UI

---

## 🧱 Components

---

### 1. DAO Layer

#### StatisticsDao

Responsibilities:

* executes aggregation queries
* groups and filters data

---

#### Queries

**Day-based (existing):**

* sessions per day
* grouped by calendar day

**Session-based (new):**

* session density raw data:

  * sessionId
  * startedAt
  * totalRounds
  * winRounds

---

---

### 2. Repository Layer

#### StatisticsRepository

Responsibilities:

* defines time window (e.g. 30 days)
* delegates filtering to DAO
* returns raw data

---

---

### 3. Engine Layer (Core of Analytics)

Engines are **pure transformation layers**.

---

## 🔹 SessionsAnalyticsEngine (day-based)

Responsibilities:

* fill missing days
* normalize data
* guarantee fixed output size
* prepare GraphData

---

## 🔹 SessionDensityEngine (new)

### Responsibilities

* filter sessions (≥2 rounds)
* sort by startedAt
* compute density:

```text
density = winRounds / totalRounds
```

* compress data:

  * max ~80 points
  * bucket aggregation (average)

* compute moving averages:

  * short window (5)
  * long window (10)

* compute baseline:

  * baseline = 100 / level

---

### Output

SessionDensityGraphData:

* raw points
* moving averages
* baseline
* totalSessions

---

---

### 🔁 Important Rule

> Moving average is computed **after compression**

---

---

### 4. Data Model (Analytics)

#### DensityMap

```kotlin
Map<GameMode?, Map<Int, SessionDensityGraphData>>
```

---

### Meaning

* outer key → mode (ALL / BEFORE / AFTER / HYBRID)
* inner key → level
* value → graph data

---

### Purpose

* supports multi-graph rendering
* enables flexible UI (level-first / mode-first)

---

---

### 5. ViewModel Layer

#### StatisticsViewModel

Responsibilities:

* loads day-based stats
* loads session-density stats
* builds UI state

---

### UI State

```kotlin
StatisticsUiState(
    dayBased: DayBasedStats,
    sessionDensity: DensityMap
)
```

---

### Behavior

* loads data on init
* transforms raw → graph via engines
* exposes reactive state

---

---

### 6. Presentation Layer

---

## 🔹 Graph Types

### BarChart (legacy)

* sessions per day
* fixed 30-day window

---

### LineChart (new)

Supports:

* raw line (noise)
* moving average (signal + trend)
* baseline (random expectation)

---

---

## 🔹 Rendering Modes

### Detail Mode (full)

* full LineChart
* axis labels
* legend
* annotations

---

### Grid Mode (simplified)

* minimal UI
* no heavy labels
* optimized for comparison

---

---

## 🔹 UI Composition Strategy

### Current (transition state)

* multiple graphs rendered sequentially

---

### Target (0.12 UX)

#### Level-first layout:

Level → 2×2 grid (modes)

Example:

Level 2

```
[ ALL     ]   [ BEFORE  ]
[ AFTER   ]   [ HYBRID  ]
```

---

#### Interaction

* tap on graph → detail screen

---

---

# 🔄 Data Flow Summary

## Game

UI
→ ViewModel
→ GameEngine
→ Mapper
→ Repository
→ DB

---

## Statistics

DB
→ StatisticsDao
→ Repository
→ Engine
→ ViewModel
→ UI

---

---

# 🧠 Design Decisions

---

## 1. Strict Separation

Game and Statistics are independent systems.

---

## 2. Engine = Pure Functions

* no DB
* no filtering
* no side effects

---

## 3. DAO Handles Aggregation

* grouping
* filtering
* SQL-level efficiency

---

## 4. Adaptive Compression

* UI-driven constraint (~80 points)
* preserves trend, not exact values

---

## 5. Baseline as UX Anchor

* baseline = expected random performance
* primary reference for user perception

---

## 6. Multi-Graph Exploration

Statistics is not a single graph but a graph space:

(mode × level)

User navigates this space via:

- pivot (LEVEL / MODE)
- selection (level or mode)

---

### Result

- controlled rendering
- focused comparison
- scalable UI

---

---

# 🚧 Current Limitations (0.13)
- no zoom
- compression smoothing
- no advanced analytics
- UI polishing still in progress

---

---

# 🔜 Next Evolution

* Level → grid UI
* Click → detail navigation
* Pivot selector (LEVEL / MODE)
* advanced metrics (consistency, trend, volatility)
* optional session weighting

## 🔹 Pivot Navigation (implemented)

Statistics supports two exploration modes:

### LEVEL mode (default)

Level → modes

- user selects a level
- UI shows grid of modes for that level

Example:

Level 2

[ ALL ] [ BEFORE ]
[ AFTER ] [ HYBRID ]

---

### MODE mode

Mode → levels

- user selects a mode
- UI shows grid of levels for that mode

Example:

Mode: BEFORE

[ Level 2 ] [ Level 3 ]
[ Level 4 ] [ Level 5 ]

---

### Behavior

- pivot switch resets selection
- no graphs shown until selection is made

### InsightBuilder
- Винести сюди побудову усіх insight-ів statistics


## 🔹 Grid Types

Two distinct grid types are used depending on pivot mode:

### LevelGrid

- groups by level
- shows multiple modes inside a level
- layout: fixed 2×2 (if all modes exist)

---

### ModeGrid

- groups by mode
- shows multiple levels
- layout: dynamic (2 columns, N rows)

---

### Purpose

- LevelGrid → compare modes within a level
- ModeGrid → compare levels within a mode

## 🔹 Selection Model

Statistics UI is selection-driven.

Rules:

- no graphs are rendered by default
- user must select:
  - level (in LEVEL mode), or
  - mode (in MODE mode)

---

### Empty State

If no selection is made:

- density graphs are hidden
- density graphs require selection
- optional hint is shown ("Select what to analyze")


---

### Rationale

- avoids overwhelming the user
- prevents rendering up to ~48 graphs at once
- enforces intentional exploration

## 🔹 Day-Based Section

Day-based graphs (sessions per day) are rendered as a separate section.
Day-based graphs are always visible

### Properties

- independent from pivot selection
- always visible
- grouped together

---

### Purpose

- provide global activity overview
- complement session-based analytics

## 🔹 Interaction

- tap on graph → opens detail screen
- detail shows full chart with axes and labels

## 🔹 Configuration (planned)

System parameters (graph ranges, MA windows, etc.)
will be externalized into a configuration layer.

Initially in-memory, later persistent.


