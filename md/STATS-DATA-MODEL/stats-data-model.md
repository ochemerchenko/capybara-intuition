# Statistics Data Model

## Version: 0.16

## Last Updated: 2026-05-19

---

# Overview

Statistics is a read-only observational analytics subsystem built on top of persisted deterministic gameplay runtime records.

The subsystem is intentionally designed around:

* explainable analytics
* deterministic reconstruction
* bounded interpretation
* supportability-first architecture
* trust-oriented UX
* compression-aware rendering
* runtime-independent observation

Statistics architecture intentionally remains isolated from:

* gameplay continuation
* runtime orchestration
* reducers
* routers
* renderers
* profile lifecycle
* monetization logic

Statistics MUST NEVER influence gameplay runtime behavior.

---

# 🧠 Core Principle

Statistics is now formally defined as:

```text
deterministic runtime reconstruction layer
```

instead of:

```text
UI-driven analytics projection
```

The subsystem intentionally reconstructs behavioral interpretation from persisted runtime records.

Flow remains strictly one-directional:

```text
DB
→ DAO
→ Repository
→ Engine
→ Pipeline
→ Insight
→ ViewModel
→ UI
```

Statistics:

* never modifies gameplay runtime
* never owns runtime continuation
* never generates gameplay events
* never coordinates lifecycle
* never affects outcomes

---

# ⚠️ Historical Clarification

Prior to Sprint 15:

* gameplay runtime itself contained semantic inconsistencies,
* especially around `BEFORE` mode behavior.

This affected analytics validity because:

* runtime labels claimed support for BEFORE,
* while gameplay behavior remained AFTER-only internally.

As of 0.16:

```text
statistics now observes actual deterministic runtime behavior
```

instead of observing partially synthesized reducer semantics.

This significantly improves:

* behavioral validity
* explainability
* trustworthiness
* legal/product safety

---

# 📦 Data Sources

Statistics derives everything from persisted deterministic runtime records.

Primary sources:

* sessions
* observed rounds
* runtime timing data
* round lifecycle timestamps
* gameplay mode/runtime semantics

No gameplay analytics are synthesized from UI state.

---

# Statistics Philosophy

Statistics intentionally prioritizes:

* interpretation over scoring
* explainability over opacity
* trust over manipulation
* bounded analytics over adaptive AI
* behavioral context over gamification

The subsystem intentionally avoids:

* hidden ranking systems
* opaque confidence scoring
* adaptive gameplay feedback loops
* predictive manipulation systems
* engagement optimization mechanics

---

# 📊 Statistics Runtime Relationship

Gameplay runtime and statistics are now explicitly separated.

Gameplay owns:

```text
runtime truth
```

Statistics owns:

```text
runtime interpretation
```

Statistics MUST NEVER:

* continue gameplay lifecycle
* reconstruct missing runtime semantics
* infer hidden gameplay transitions
* emulate reducer behavior

---

# 📦 Data Layers

---

# 1. Raw Projection Layer

Located in:

```text
statistics/data/local/db/model
```

---

# Philosophy

Projection models represent:

```text
SQL-level deterministic reconstruction inputs
```

They are intentionally:

* lightweight
* read-only
* aggregation-oriented
* persistence-derived

---

# SessionCountPerDay

Represents aggregated session counts grouped by local calendar day.

---

## Structure

```kotlin
SessionCountPerDay(
    val dayStartMillis: Long,
    val count: Int
)
```

---

## Purpose

Used for:

* activity charts
* engagement timelines
* participation observation

---

## Notes

* projection-only
* aggregated in SQL
* not persisted independently

---

# SessionDensityRaw

Represents session-level behavioral performance projection.

---

## Structure

```kotlin
SessionDensityRaw(
    val sessionId: String,
    val startedAt: Long,
    val totalRounds: Int,
    val winRounds: Int
)
```

---

## Purpose

Used for:

* density analytics
* trend reconstruction
* baseline comparison
* behavioral interpretation

---

## Notes

* one projection per session
* derived from persisted rounds
* no cached analytics storage

---

# 2. Repository Layer

Located in:

```text
statistics/data/repository
```

---

# StatisticsRepository

Acts as:

```text
bounded analytics query adapter
```

---

## Responsibilities

* define query windows
* delegate aggregation to DAO
* expose analytics-ready projections
* isolate persistence semantics

---

## Constraints

Repository intentionally contains:

* no interpretation logic
* no visualization logic
* no runtime orchestration
* no gameplay knowledge
* no UI assumptions

---

# 3. Engine Layer

Located in:

```text
statistics/domain/engine
```

---

# Philosophy

All analytics engines remain:

* deterministic
* pure
* side-effect free
* UI-independent
* runtime-independent

---

# SessionsActivityEngine

Processes:

* activity timelines
* missing-day reconstruction
* chronological stabilization

---

## Responsibilities

* normalize time ranges
* fill missing dates
* stabilize chart continuity
* preserve chronological order

---

# SessionDensityEngine

Primary behavioral analytics engine.

Processes:

* density calculation
* filtering
* compression
* moving averages
* baseline interpretation

---

# Pipeline

```text
SessionDensityRaw
↓
filtering
↓
sorting
↓
density projection
↓
compression
↓
moving averages
↓
SessionDensityGraphData
```

---

# Processing Stages

---

# Filtering

Excluded:

* sessions with 0 rounds
* sessions with 1 round

---

## Reason

Such sessions contain:

```text
insufficient behavioral signal
```

---

# Density Calculation

Formula:

density = \frac{winRounds}{totalRounds} \times 100

---

## Meaning

Represents:

* behavioral efficiency
* runtime consistency
* perceptual performance

---

# Compression

Adaptive readability-oriented graph compression.

---

## Goal

Preserve interpretability under:

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
* explainable UX

---

# Moving Average

Computed AFTER compression.

---

## Windows

* MA short = 5
* MA long = 10

---

## Purpose

Used for:

* trend extraction
* noise reduction
* behavioral smoothing
* interpretive readability

---

## Critical Rule

Moving averages MUST remain post-compression to avoid:

* double smoothing
* false trends
* visual distortion

---

# Baseline

Formula:

baseline = \frac{100}{level}

---

## Meaning

Represents:

* expected random performance
* interpretive anchor
* cognitive reference point

---

## UX Role

Baseline acts as the primary explainability mechanism.

The project intentionally prioritizes:

```text
understandable comparison
```

instead of:

```text
opaque statistical sophistication
```

---

# 4. Pipeline Layer

Located in:

```text
statistics/domain/pipeline
```

---

# Philosophy

Pipeline layer isolates:

* rendering-oriented preprocessing
* viewport constraints
* graph-space transformation

from:

* analytics engines
* behavioral interpretation

---

# DensityPipelineBuilder

Responsibilities:

* prepare graph-ready datasets
* stabilize viewport rendering
* coordinate density projection flow

---

# DensityPipelinePoint

Intermediate transformation model.

Purpose:

* preserve transformation semantics
* isolate rendering preparation
* prevent engine contamination

---

# DensityViewport

Represents bounded graph rendering constraints.

Purpose:

* stable scaling
* bounded rendering
* predictable chart behavior

---

# 5. Transform Layer

Located in:

```text
statistics/domain/transform
```

---

# DensityCompression

Dedicated point-reduction transformation.

Purpose:

* readability preservation
* spike smoothing
* stable rendering density

---

# DensityProjection

Dedicated graph-space projection transformation.

Purpose:

* behavioral normalization
* coordinate projection
* rendering-safe mapping

---

# 6. Analytics Layer

Located in:

```text
statistics/domain/analytics
```

---

# Philosophy

Separates:

```text
raw observation
```

from:

```text
behavioral interpretation
```

---

# BootstrapGenerator

Provides low-data statistical stabilization.

Purpose:

* prevent misleading early interpretation
* stabilize low-session UX
* improve trustworthiness

---

# RandomnessEnvelopeEngine

Dedicated uncertainty interpretation subsystem.

Purpose:

* contextualize variance
* estimate expected fluctuation
* reduce false-positive interpretation

---

# RandomnessEnvelope

Represents expected uncertainty boundaries.

Purpose:

* trust-oriented interpretation
* volatility explanation
* contextual analytics

---

# Important Principle

RandomnessEnvelope intentionally explains:

```text
possible statistical randomness
```

NOT:

```text
prediction confidence
```

---

# 7. Insight Layer

Located in:

```text
statistics/domain/insight
```

---

# InsightBuilder

Dedicated explainable interpretation subsystem.

---

## Responsibilities

* generate human-readable observations
* explain trend direction
* contextualize behavioral performance
* preserve deterministic interpretation

---

# Important Principle

Insights remain:

* deterministic
* reproducible
* explainable
* bounded

NOT:

* opaque AI output
* adaptive coaching
* black-box prediction

---

# Insight Models

---

# PerformanceInsight

Represents user-facing interpretation result.

---

# PerformanceLevel

Represents qualitative performance category.

---

# Trend

Represents directional behavioral semantics.

---

# 8. Domain Models

Located in:

```text
statistics/domain/model
```

---

# GraphPoint

Universal graph coordinate model.

```kotlin
GraphPoint(
    val x: Float,
    val y: Float
)
```

---

# SessionDensityGraphData

Primary behavioral analytics graph model.

---

## Structure

```kotlin
SessionDensityGraphData(
    val raw: List<GraphPoint>,
    val maShort: List<GraphPoint>,
    val maLong: List<GraphPoint>,
    val baseline: Float,
    val totalSessions: Int
)
```

---

# Layer Semantics

---

# raw

High-frequency behavioral variability.

---

# maShort

Short-term signal.

---

# maLong

Long-term trend.

---

# baseline

Expected random performance.

---

# DensityMap

```kotlin
Map<GameMode?, Map<Int, SessionDensityGraphData>>
```

---

# Meaning

---

# Outer Key

```kotlin
GameMode?
```

Values:

* null → ALL
* BEFORE
* AFTER
* HYBRID

---

# Inner Key

```kotlin
Int
```

Represents gameplay level.

---

# Purpose

Supports:

* mode-first exploration
* level-first exploration
* bounded graph grouping
* deterministic navigation

---

# 9. Presentation Models

Located in:

```text
statistics/presentation/model
```

---

# StatisticsUiState

Main statistics presentation state.

---

## Responsibilities

* screen-level orchestration
* graph selection state
* pivot coordination
* dialog ownership

---

# SessionDensityStats

Presentation-oriented density aggregation.

---

# DayBasedStats

Presentation-oriented activity aggregation.

---

# SelectedGraph

Represents active exploration context.

---

# PivotMode

Supports:

* LEVEL exploration
* MODE exploration

---

# DensityCardInput

Preview-oriented graph rendering model.

---

# 10. UI Integration

---

# Philosophy

Statistics UI intentionally follows:

```text
selection-driven conscious exploration
```

instead of:

```text
dashboard overload
```

---

# StatisticsScreen

Main statistics orchestration screen.

---

## Responsibilities

* section composition
* graph selection
* navigation coordination
* markdown integration

---

# StatisticsDensitySection

Handles:

* density exploration
* graph previews
* pivot rendering
* contextual explanations

---

# StatisticsActivitySection

Handles:

* activity timelines
* engagement interpretation
* contextual help

---

# DensityDetailScreen

Provides:

* expanded graph view
* trend exploration
* baseline interpretation
* uncertainty visualization

---

# Visualization Components

---

# BarChart

Used for:

* activity analytics

---

# LineChart

Used for:

* density analytics
* trend rendering
* baseline comparison
* uncertainty overlays

---

# Supported Layers

* raw
* MA short
* MA long
* baseline
* randomness envelope

---

# DrawEnvelope

Dedicated uncertainty renderer.

Purpose:

* contextualize randomness
* reduce false certainty
* improve trustworthiness

---

# 11. UX Model

Statistics intentionally prioritizes:

* conscious exploration
* explainability
* trustworthiness
* progressive disclosure

---

# Supported Navigation

---

# LEVEL Pivot

```text
Level → Modes
```

---

# MODE Pivot

```text
Mode → Levels
```

---

# Important UX Principle

Graphs are intentionally NOT auto-rendered.

Users consciously:

* select context
* open previews
* navigate into detail
* interpret trends intentionally

This reduces:

* visual fatigue
* graph overload
* false interpretation
* analytics intimidation

---

# 12. Explainability Infrastructure

Statistics integrates with:

* markdown-driven help
* localized explanations
* semantic illustrations
* contextual interpretation

---

# Markdown Philosophy

Markdown subsystem intentionally remains:

* passive
* declarative
* content-driven

NOT:

* runtime scripting
* orchestration layer
* dynamic execution system

---

# 13. Runtime Isolation

Statistics intentionally remains isolated from:

* GameFlowRouter
* runtime continuation
* gameplay handlers
* renderer ownership
* capability evaluation

---

# Important Principle

Statistics reconstructs:

```text
what happened
```

NOT:

```text
what should happen next
```

---

# 14. Persistence Philosophy

Statistics intentionally avoids:

```text
cached aggregate ownership
```

Everything derives from persisted gameplay runtime records.

This improves:

* auditability
* explainability
* reconstruction correctness
* deployment safety

---

# 15. Architectural Constraints

---

# Read-only Guarantee

Statistics MUST NEVER:

* mutate gameplay state
* influence outcomes
* inject runtime events
* modify persistence

---

# Engine Isolation

Engines:

* do not know Compose
* do not know navigation
* do not know runtime continuation
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
* ignores round-count weighting

This remains intentional until future validation.

---

# ⚠️ Known Limitations (0.16)

* spikes may be smoothed
* session weighting not implemented
* anomaly detection incomplete
* low-data tuning still evolving
* personalization not implemented
* telemetry export still stabilizing

---

# 🔜 Near-term Direction

---

# Stabilization

* low-data validation
* restore correctness verification
* telemetry export stabilization
* localization validation

---

# Analytics Expansion

* consistency metrics
* volatility analysis
* session weighting
* advanced explainable insights

---

# Long-term Direction

* anomaly detection
* longitudinal behavioral modeling
* cloud synchronization
* AI-assisted interpretation

---

# Important Clarification

Future AI-assisted interpretation MUST remain:

* explainable
* bounded
* observational

and MUST NEVER:

* manipulate gameplay
* alter runtime behavior
* introduce opaque scoring systems

