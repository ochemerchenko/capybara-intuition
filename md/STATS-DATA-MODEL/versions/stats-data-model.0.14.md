# Statistics Data Model

## Version: 0.14

## Last Updated: 2026-05-02

---

# Overview

Statistics is a read-only analytics subsystem built on top of persisted gameplay data.

The system is intentionally designed around:

* explainable analytics
* deterministic transformations
* UI-independent domain models
* compression-first rendering
* selection-driven exploration

---

# 🧠 Core Principle

Statistics NEVER affects gameplay.

Flow is strictly one-directional:

```text
DB
→ DAO
→ Repository
→ Engine
→ ViewModel
→ UI
```

Statistics:

* does not modify game state
* does not write gameplay data
* does not interact with GameEngine

---

# 📊 Data Layers

---

# 1. Raw Data Layer (Database Projections)

Located in:

```text
statistics/data/local/db/model
```

---

## SessionCountPerDay

Represents aggregated sessions per calendar day.

### Fields

```kotlin
dayStartMillis: Long
count: Int
```

---

## Source

```text
StatisticsDao
```

---

## Purpose

Used for:

* activity charts
* sessions-per-day graphs
* day-based statistics

---

## Notes

* aggregated in SQL
* grouped by local calendar day
* filtered by mode and time range
* projection only (not persisted separately)

---

## SessionDensityRaw

Represents aggregated session-level performance data.

### Fields

```kotlin
sessionId: String
startedAt: Long
totalRounds: Int
winRounds: Int
```

---

## Source

```text
StatisticsDao (GROUP BY sessionId)
```

---

## Purpose

Used for:

* session density analytics
* behavioral trend analysis
* performance visualization

---

## Notes

* derived from round_records
* projection only
* no redundant storage
* one entry per session

---

# 2. Repository Layer

Located in:

```text
statistics/data/repository
```

---

## StatisticsRepository

Responsibilities:

* define query windows
* delegate filtering to DAO
* provide analytics-ready raw data
* isolate data source from engines

---

# 3. Domain Transformation Layer (Engines)

Located in:

```text
statistics/domain/engine
```

All engines are:

* pure
* deterministic
* UI-independent
* read-only

---

# SessionsActivityEngine

Processes:

* sessions-per-day data
* calendar normalization
* missing-day filling

---

## Responsibilities

* normalize time ranges
* fill missing dates
* guarantee stable graph size
* sort old → new

---

# SessionDensityEngine

Processes:

* session-based behavioral analytics

---

# Pipeline

```text
SessionDensityRaw
↓
(filter)
↓
(sort)
↓
(density calculation)
↓
(compression)
↓
(moving average)
↓
SessionDensityGraphData
```

---

# Processing Stages

---

## 1. Filtering

Excluded:

* sessions with 0 rounds
* sessions with 1 round

Reason:

* insufficient statistical signal

---

## 2. Density Calculation

Formula:

```text
density = (winRounds / totalRounds) * 100
```

---

## Purpose

Represents:

* session performance
* behavioral efficiency
* outcome consistency

---

## 3. Compression

Adaptive graph compression.

---

## Goal

Keep graph readable under:

* 50 sessions
* 500 sessions
* 1000+ sessions

---

## Strategy

* target ≈ 80 points
* bucket aggregation
* average density per bucket

---

## Trade-off

Precision is intentionally sacrificed for:

* readability
* trend visibility
* rendering stability

---

## 4. Moving Average

Computed AFTER compression.

---

## Windows

```text
MA short = 5
MA long = 10
```

---

## Purpose

Used for:

* signal extraction
* noise reduction
* trend visibility

---

## Important Rule

Moving average MUST be calculated after compression to:

* avoid double smoothing
* preserve visual consistency

---

## 5. Baseline

Formula:

```text
baseline = 100 / level
```

---

## Meaning

Represents:

* expected random performance
* reference expectation
* behavioral anchor

---

## UX Role

Baseline is a primary interpretive reference for users.

---

# 4. Domain Models

Located in:

```text
statistics/domain/model
```

---

## GraphPoint

Universal graph coordinate model.

### Fields

```kotlin
x: Float
y: Float
```

---

## GraphData (legacy/day-based)

Used by activity charts.

### Fields

```kotlin
points: List<GraphPoint>
labels: List<String>
daysOfWeek: List<DayOfWeek>
```

---

## SessionDensityGraphData

Main behavioral analytics graph model.

### Fields

```kotlin
raw: List<GraphPoint>
maShort: List<GraphPoint>
maLong: List<GraphPoint>
baseline: Float
totalSessions: Int
```

---

## Layer Semantics

### raw

High-frequency behavioral noise.

---

### maShort

Short-term signal.

---

### maLong

Long-term trend.

---

### baseline

Expected random performance.

---

## DensityMap

```kotlin
Map<GameMode?, Map<Int, SessionDensityGraphData>>
```

---

# Meaning

---

## Outer Key

```kotlin
GameMode?
```

Possible values:

* null → ALL
* BEFORE
* AFTER
* HYBRID

---

## Inner Key

```kotlin
Int
```

Represents:

* level

---

## Value

```kotlin
SessionDensityGraphData
```

---

# Purpose

Supports:

* pivot navigation
* level-first rendering
* mode-first rendering
* flexible UI grouping

---

# 5. Insight Layer

Located in:

```text
statistics/domain/insight
```

---

## InsightBuilder

Dedicated analytics interpretation layer.

---

## Responsibilities

* derive human-readable observations
* compute trend explanations
* generate behavioral insights
* simplify interpretation

---

## Important Principle

Insights are:

* derived
* explainable
* deterministic

NOT:

* AI-generated
* opaque
* probabilistic black-boxes

---

# 📱 UI Integration

---

# Activity Charts

Use:

```text
GraphData
```

Visualization:

* BarChart

---

# Density Charts

Use:

```text
SessionDensityGraphData
```

Visualization:

* LineChart

---

# Supported Layers

* raw
* MA short
* MA long
* baseline

---

# UX Model

Statistics UI is intentionally:

* selection-driven
* detail-first
* exploration-oriented

---

# Supported Navigation

## LEVEL pivot

```text
Level → modes
```

---

## MODE pivot

```text
Mode → levels
```

---

# Important UX Principle

Graphs are NOT auto-rendered by default.

User:

* selects context
* explores intentionally
* opens detail views consciously

---

# Explainable Analytics

Statistics are expected to:

* explain themselves
* remain interpretable
* avoid opaque metrics
* prioritize trustworthiness

---

# Markdown Help Integration

Statistics supports:

* markdown-driven help dialogs
* contextual explanations
* semantic illustrations
* localized content

Assets:

```text
assets/help/*.md
```

---

# ⚠️ Constraints

---

## Read-only

Statistics:

* never writes to DB
* never changes gameplay
* never affects outcomes

---

## Engine Isolation

Engines:

* do not know about UI
* do not know about Compose
* do not know about navigation

---

## Compression

Compression:

* intentionally reduces precision
* may smooth spikes
* prioritizes readability

---

## Equal Session Weight

Currently:

* all sessions have equal weight
* round count is ignored

---

# 🧠 Design Decisions

---

## No Redundant Storage

Everything derives from:

```text
round_records
```

No cached aggregates.

---

## SQL-level Aggregation

DAO performs:

* grouping
* counting
* aggregation

Reason:

* reduce memory transfer
* improve efficiency

---

## Pure Transformation Engines

Engines:

* deterministic
* testable
* isolated
* composable

---

## Compression-first Rendering

UI constraints define:

* graph simplification
* point reduction
* readability strategy

---

## Baseline as UX Anchor

Baseline serves as:

* interpretive reference
* expectation marker
* behavioral comparison tool

---

# 🚧 Known Limitations (0.14)

* spikes may be smoothed out
* sessions have equal weight
* no anomaly detection
* no advanced volatility metrics
* no user customization
* low-data behavior still under validation

---

# 🔜 Evolution Path

---

## Near-term (0.14)

* analytics validation
* low-data tuning
* insight stabilization
* graph readability validation
* trustworthiness validation

---

## Mid-term

* consistency metrics
* volatility analysis
* session weighting
* advanced insights

---

## Long-term

* anomaly detection
* behavioral segmentation
* AI-assisted interpretation
* personalized analytics

