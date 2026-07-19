## Version: 0.12

## Last Updated: 2026-04-11

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

## 6. Multi-Graph Architecture

* not one graph, but a graph space
* indexed by (mode, level)

---

---

# 🚧 Current Limitations (0.12)

* no zoom (intentionally)
* compression removes spikes
* equal session weight (round count ignored)
* no advanced analytics (yet)
* UI still evolving (grid not fully implemented)

---

---

# 🔜 Next Evolution

* Level → grid UI
* Click → detail navigation
* Pivot selector (LEVEL / MODE)
* advanced metrics (consistency, trend, volatility)
* optional session weighting

