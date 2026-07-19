# Statistics Data Model

## Version: 0.15

## Last Updated: 2026-05-13

---

# Overview

Statistics is a read-only behavioral analytics subsystem built on top of persisted gameplay records.

The subsystem is intentionally designed around:

* explainable analytics
* deterministic transformations
* supportable bounded contexts
* compression-first rendering
* trust-oriented interpretation
* selection-driven exploration

Statistics architecture remains fully isolated from gameplay runtime.

---

# 🧠 Core Principle

Statistics NEVER affects gameplay.

Flow remains strictly one-directional:

DB
→ DAO
→ Repository
→ Engine
→ Pipeline
→ ViewModel
→ UI

Statistics:

* never modifies gameplay state
* never writes gameplay runtime
* never interacts with GameEngine lifecycle
* never influences outcomes

This separation remains a critical architectural rule.

---

# 📊 Statistics Philosophy

The analytics subsystem is designed as:

* behavioral observation layer
* explainable interpretation system
* trust-oriented exploration tool

The project intentionally prioritizes:

* clarity over density
* explainability over complexity
* interpretation over opaque scoring
* emotional trust over aggressive gamification

---

# 📦 Data Sources

Statistics derives everything from persisted gameplay records.

Primary sources:

* sessions
* observed_rounds
* runtime-derived behavioral timing

No redundant aggregate storage is used.

Analytics remains reconstruction-based.

---

# 📊 Data Layers

---

# 1. Raw Projection Layer

Located in:

statistics/data/local/db/model

---

## SessionCountPerDay

Represents aggregated session counts per calendar day.

### Fields

```kotlin
dayStartMillis: Long
count: Int
```

---

## Purpose

Used for:

* activity charts
* day-based statistics
* engagement timelines

---

## Notes

* aggregated in SQL
* grouped by local calendar day
* projection-only model
* not persisted independently

---

## SessionDensityRaw

Represents session-level behavioral performance.

### Fields

```kotlin
sessionId: String
startedAt: Long
totalRounds: Int
winRounds: Int
```

---

## Source

StatisticsDao (GROUP BY sessionId)

---

## Purpose

Used for:

* behavioral density analytics
* trend analysis
* performance exploration

---

## Notes

* derived from persisted rounds
* one record per session
* projection-only
* no redundant storage

---

# 2. Repository Layer

Located in:

statistics/data/repository

---

## StatisticsRepository

Responsibilities:

* define query windows
* delegate filtering to DAO
* isolate storage from engines
* provide analytics-ready projections

Repository layer intentionally contains:

* no interpretation
* no visualization logic
* no UI assumptions

---

# 3. Engine Layer

Located in:

statistics/domain/engine

All engines remain:

* pure
* deterministic
* UI-independent
* read-only

---

# SessionsActivityEngine

Processes:

* sessions-per-day data
* activity normalization
* timeline stabilization

---

## Responsibilities

* normalize time ranges
* fill missing dates
* guarantee graph consistency
* sort chronologically

---

# SessionDensityEngine

Main behavioral analytics engine.

Processes:

* session performance
* behavioral trends
* statistical smoothing
* baseline comparison

---

# Pipeline

SessionDensityRaw
↓
(filter)
↓
(sort)
↓
(density projection)
↓
(compression)
↓
(moving average)
↓
SessionDensityGraphData

---

# Processing Stages

---

## 1. Filtering

Excluded:

* sessions with 0 rounds
* sessions with 1 round

Reason:

* insufficient behavioral signal

---

## 2. Density Calculation

Formula:

```text
density = (winRounds / totalRounds) * 100
```

---

## Meaning

Represents:

* behavioral efficiency
* session consistency
* outcome performance

---

## 3. Compression

Adaptive graph compression.

---

## Goal

Preserve readability under:

* 50 sessions
* 500 sessions
* 1000+ sessions

---

## Strategy

* target ≈ 80 points
* bucket aggregation
* average-density grouping

---

## Philosophy

Compression intentionally sacrifices precision for:

* readability
* trend visibility
* rendering stability
* supportable visualization

---

## 4. Moving Average

Computed AFTER compression.

---

## Windows

MA short = 5
MA long = 10

---

## Purpose

Used for:

* signal extraction
* trend readability
* behavioral smoothing
* noise reduction

---

## Important Rule

Moving averages MUST be computed after compression to avoid:

* double smoothing
* visual distortion
* misleading trends

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
* interpretive reference point
* behavioral anchor

---

## UX Role

Baseline acts as the primary user-facing interpretive guide.

Analytics intentionally prioritizes:

* understandable comparison

over:

* raw statistical complexity

---

# 4. Pipeline Layer

Located in:

statistics/domain/pipeline

Introduced in 0.15 stabilization work.

---

## Purpose

Pipeline layer formalizes:

* viewport preparation
* graph transformation
* density projection ownership
* rendering-oriented preprocessing

without leaking visualization semantics into engines.

---

## DensityPipelineBuilder

Responsibilities:

* build stable graph-ready datasets
* coordinate viewport-safe projection
* prepare visualization-friendly density flow

---

## DensityPipelinePoint

Intermediate projection model.

Purpose:

* isolate transformation semantics
* preserve engine purity
* stabilize rendering pipeline

---

## DensityViewport

Represents graph viewport constraints.

Purpose:

* bounded rendering
* stable chart scaling
* visualization consistency

---

# 5. Transform Layer

Located in:

statistics/domain/transform

---

## DensityCompression

Dedicated compression transformation.

Purpose:

* point reduction
* readability preservation
* trend stabilization

---

## DensityProjection

Dedicated density projection transformation.

Purpose:

* behavioral normalization
* graph-space conversion
* rendering-safe mapping

---

# 6. Analytics Layer

Located in:

statistics/domain/analytics

Expanded significantly during 0.15.

---

# Purpose

The analytics layer separates:

* raw statistics

from:

* behavioral interpretation

This preserves:

* explainability
* supportability
* future extensibility

---

# BootstrapGenerator

Provides baseline statistical initialization.

Purpose:

* stabilize low-data interpretation
* prevent misleading early trends
* improve UX trustworthiness

---

# RandomnessEnvelopeEngine

Dedicated randomness interpretation subsystem.

Purpose:

* estimate expected behavioral fluctuation
* contextualize observed variance
* reduce false-positive interpretation

---

## RandomnessEnvelope

Represents expected statistical uncertainty boundaries.

Purpose:

* trust-oriented analytics
* behavioral context explanation
* volatility interpretation

---

# 7. Insight Layer

Located in:

statistics/domain/insight

---

## InsightBuilder

Dedicated behavioral interpretation subsystem.

---

## Responsibilities

* derive human-readable observations
* explain trend direction
* contextualize performance
* simplify interpretation

---

## Important Principle

Insights remain:

* deterministic
* explainable
* reproducible
* bounded

NOT:

* opaque AI output
* probabilistic black boxes
* hidden scoring systems

---

# Insight Models

---

## PerformanceInsight

Represents user-facing interpretation.

---

## PerformanceLevel

Represents qualitative behavioral categorization.

---

## Trend

Represents directional movement semantics.

---

# 8. Domain Models

Located in:

statistics/domain/model

---

# GraphPoint

Universal graph coordinate model.

### Fields

```kotlin
x: Float
y: Float
```

---

# SessionDensityGraphData

Primary behavioral analytics graph model.

### Fields

```kotlin
raw: List<GraphPoint>
maShort: List<GraphPoint>
maLong: List<GraphPoint>
baseline: Float
totalSessions: Int
```

---

# Layer Semantics

---

## raw

High-frequency behavioral variability.

---

## maShort

Short-term signal.

---

## maLong

Long-term trend.

---

## baseline

Expected random performance.

---

# DensityMap

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

Values:

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

* gameplay level

---

# Purpose

Supports:

* pivot navigation
* level-first exploration
* mode-first exploration
* bounded graph grouping

---

# 9. Presentation Models

Located in:

statistics/presentation/model

---

# StatisticsUiState

Main statistics presentation state.

Purpose:

* screen-level orchestration
* pivot coordination
* graph selection state
* dialog state ownership

---

# SessionDensityStats

Presentation-ready density aggregation.

---

# DayBasedStats

Presentation-ready activity aggregation.

---

# SelectedGraph

Represents current exploration focus.

---

# PivotMode

Supports:

* LEVEL exploration
* MODE exploration

---

# DensityCardInput

Presentation-oriented graph preview model.

---

# 10. UI Integration

---

# StatisticsScreen

Main statistics orchestration screen.

Responsibilities:

* section composition
* dialog coordination
* state hoisting
* navigation coordination

---

# StatisticsDensitySection

Handles:

* density exploration
* graph selection
* pivot rendering
* contextual help

---

# StatisticsActivitySection

Handles:

* activity timelines
* engagement exploration
* contextual explanations

---

# Detail Screens

---

## DensityDetailScreen

Provides:

* expanded graph
* trend exploration
* baseline interpretation
* contextual explanations

---

# Visualization Components

---

# BarChart

Used for:

* activity statistics

---

# LineChart

Used for:

* behavioral density analytics

---

## Supported Layers

* raw
* MA short
* MA long
* baseline
* randomness envelope

---

# DrawEnvelope

Dedicated uncertainty visualization renderer.

Purpose:

* contextualize expected randomness
* improve interpretability
* reduce false trend confidence

---

# 11. UX Model

Statistics UI intentionally follows:

* selection-driven exploration
* detail-first interaction
* conscious navigation
* trust-oriented interpretation

---

# Supported Navigation

---

## LEVEL Pivot

Level → modes

---

## MODE Pivot

Mode → levels

---

# Important UX Principle

Graphs are NOT auto-rendered by default.

Users intentionally:

* select context
* open previews
* explore detail views
* interpret trends consciously

This reduces:

* graph overload
* visual fatigue
* accidental misinterpretation

---

# 12. Explainable Analytics

Statistics are expected to:

* explain themselves
* remain interpretable
* avoid opaque scoring
* prioritize trustworthiness

---

# Markdown Integration

Statistics integrates with:

* markdown-driven help dialogs
* localized explanations
* semantic illustrations
* contextual interpretation

Assets:

assets/help/*.md

Markdown subsystem intentionally remains:

* passive
* declarative
* content-driven

NOT:

* interactive runtime logic
* scripting framework
* orchestration layer

---

# ⚠️ Constraints

---

# Read-only

Statistics:

* never writes gameplay data
* never changes outcomes
* never controls runtime

---

# Engine Isolation

Engines:

* do not know UI
* do not know Compose
* do not know navigation
* do not know renderer internals

---

# Compression Constraints

Compression intentionally:

* smooths spikes
* reduces precision
* favors readability

---

# Equal Session Weight

Current implementation:

* treats sessions equally
* ignores round count weighting

This remains intentional until future validation.

---

# 🧠 Design Decisions

---

# No Redundant Analytics Storage

Everything derives from persisted gameplay records.

No cached aggregates are persisted.

---

# SQL-Level Aggregation

DAO layer performs:

* grouping
* counting
* aggregation

Reason:

* reduce transfer overhead
* improve scalability
* preserve deterministic pipelines

---

# Pure Transformation Strategy

All analytics transformations remain:

* deterministic
* testable
* isolated
* composable

---

# Compression-First Rendering

Visualization constraints intentionally influence:

* graph simplification
* point reduction
* readability policy

---

# Baseline as UX Anchor

Baseline acts as:

* interpretive reference
* expectation marker
* behavioral comparison tool

This remains one of the core explainability principles.

---

# ⚠️ Architectural Clarifications (0.15)

Statistics subsystem intentionally avoids:

* opaque AI scoring
* hidden confidence systems
* predictive gameplay influence
* adaptive gameplay feedback loops

The subsystem remains:

* observational
* explainable
* bounded
* supportability-oriented

---

# 🚧 Known Limitations (0.15)

* spikes may be smoothed
* sessions still have equal weight
* anomaly detection not implemented
* advanced volatility metrics incomplete
* personalization not implemented
* telemetry export still under stabilization
* low-data behavior tuning still ongoing

---

# 🔜 Evolution Path

---

# Near-term (0.15)

* deployment hardening
* low-data validation
* trustworthiness stabilization
* localization validation
* telemetry export
* process-death verification

---

# Mid-term

* consistency metrics
* volatility analysis
* session weighting
* advanced insights
* behavioral segmentation

---

# Long-term

* anomaly detection
* AI-assisted interpretation
* cloud synchronization
* personalized analytics
* longitudinal behavioral modeling

