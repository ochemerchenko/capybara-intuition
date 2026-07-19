# ARCHITECTURE

## Version: 0.15

## Last Updated: 2026-05-13

---

# Overview

The system follows a bounded layered architecture with explicit separation between:

* gameplay runtime
* analytics subsystem
* rendering infrastructure
* profile/security domain
* visual configuration domain
* presentation/UI infrastructure

The project intentionally prioritizes:

* explicit ownership
* deterministic lifecycle
* supportability-first architecture
* explainable analytics
* bounded contexts
* deployment-safe complexity

---

# 🧠 Core Architectural Principle

The application intentionally separates:

```text
runtime execution
```

from:

```text
analytics interpretation
```

and:

```text
visual rendering
```

from:

```text
gameplay semantics
```

---

# Primary System Flows

---

# 🎮 Gameplay Runtime Flow

UI
→ ViewModel
→ GameEngine
→ Handlers
→ Mapper
→ Repository
→ DB

---

# 📊 Statistics Read Flow

DB
→ DAO
→ Repository
→ Engine
→ Pipeline
→ ViewModel
→ UI

---

# 🎨 Rendering Flow

Stimulus
→ ResourceResolver
→ RendererResolver
→ Renderer
→ Compose UI

---

# 🔐 Profile Flow

UI
→ ProfileManager
→ Repository
→ DB

---

# Architectural Rules

---

# Statistics is Read-only

Statistics:

* never modifies gameplay runtime
* never writes gameplay state
* never influences GameEngine
* never changes outcomes

---

# Engines are Pure

All engines intentionally:

* have no UI knowledge
* have no DB awareness
* have no Compose awareness
* are deterministic
* avoid side effects

---

# Renderer Isolation

Gameplay runtime intentionally does NOT know:

* drawable internals
* Compose rendering details
* image-loading semantics
* adaptive layout metrics

---

# Bounded Ownership

Subsystems intentionally avoid:

* cross-domain lifecycle control
* feature leakage
* generalized runtime ownership
* implicit orchestration

---

# 🎮 Gameplay Architecture

Located in:

```text
game/
```

---

# Gameplay Philosophy

Gameplay runtime intentionally remains:

* reducer-driven
* explicit
* deterministic
* bounded
* deployment-safe

The project intentionally avoids:

* generalized orchestration engines
* implicit runtime graphs
* hook-driven gameplay execution
* abstraction-heavy lifecycle systems

---

# Gameplay Domain Structure

```text
game
├── data
├── domain
├── presentation
```

---

# Gameplay Runtime Model

```text
GameState
↓
GameSession
↓
GameRound
↓
RoundOption
↓
Stimulus
```

---

# GameEngine

Location:

```text
game/domain/engine/GameEngine.kt
```

---

## Responsibilities

* process events
* transition immutable state
* coordinate round lifecycle
* control deterministic progression
* trigger continuation semantics

---

## Constraints

GameEngine intentionally has:

* no persistence ownership
* no analytics ownership
* no renderer ownership
* no localization ownership
* no adaptive layout ownership

---

# GameEngineHandlers

Location:

```text
game/domain/engine/GameEngineHandlers.kt
```

---

## Purpose

Provides semantic reducer decomposition while preserving explicit lifecycle semantics.

---

## Responsibilities

Examples:

* handleUserSelection()
* handleResolveRound()

---

# Game Events

Location:

```text
game/domain/events
```

---

# Philosophy

Gameplay remains explicitly event-driven.

Examples:

```kotlin
GameEvent.StartSession
GameEvent.StartRound
GameEvent.UserSelected
GameEvent.ResolveRound
```

---

# RoundFactory

Location:

```text
game/domain/round
```

---

## Purpose

Isolates:

* round construction
* topology binding
* option generation
* runtime initialization

---

## Architectural Role

Prevents:

* reducer construction explosion
* lifecycle ambiguity
* topology leakage

---

# Stimulus Architecture

Located in:

```text
game/domain/stimulus
```

---

# Philosophy

The project intentionally migrated from:

```text
color-centric gameplay
```

into:

```text
renderer-independent perceptual stimuli
```

---

# Stimulus Responsibilities

Stimulus abstracts:

* colors
* images
* future perceptual entities

from:

* gameplay semantics
* rendering mechanics
* visual representation

---

# Resource Architecture

Located in:

```text
game/domain/resource
```

---

# Purpose

Separates:

* resource ownership
* notebook organization
* resource descriptors
* stimulus resolution

---

# Resource Categories

Current categories:

* COLOR
* IMAGE

---

# Notebook Philosophy

Notebook acts as:

```text
bounded perceptual resource collection
```

---

# Renderer Architecture

Located in:

```text
game/domain/renderer
```

---

# Philosophy

Gameplay runtime intentionally delegates visual rendering.

Gameplay never owns:

* drawing mechanics
* renderer internals
* visual implementation

---

# RendererResolver

Resolves renderer implementations using:

* RendererCategory
* stimulus semantics
* visual configuration

---

# Current Renderers

---

# Color Renderers

* CheckerRenderer
* CircumferenceRenderer
* SolidFillRenderer

---

# Image Renderers

* DirectImageRenderer
* DistortedImageRenderer

---

# Layout Topology Architecture

Located in:

```text
game/domain/layout
```

---

# Philosophy

The architecture intentionally separates:

* semantic position ownership

from:

* visual coordinates

---

# LayoutTopologyResolver

Responsible for:

* persisted topology loading
* level-based topology resolution
* semantic layout ownership

---

# Persistence Architecture

Located in:

```text
game/data/local/db
```

---

# Main Persistence Entities

---

# SessionEntity

Persistent gameplay session.

---

# ObservedRoundEntity

Persistent behavioral interaction record.

---

# LayoutConfigEntity

Persistent semantic topology configuration.

---

# StimulusEntity

Persistent stimulus representation.

---

# Persistence Philosophy

Persistence intentionally remains:

* append-oriented
* reconstruction-friendly
* deterministic
* analytics-derived

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
runtime domain
→ persistence model
```

Examples:

* GameRoundToRecordMapper
* GameSessionMapper

---

# ViewModel Layer

Located in:

```text
game/presentation/viewmodel
```

---

# Responsibilities

* orchestrate UI lifecycle
* dispatch GameEvents
* coordinate persistence
* expose reactive state
* manage runtime synchronization

---

# Important Constraint

ViewModels may coordinate lifecycle,

but MUST NOT:

* own business rules
* perform analytics
* implement rendering logic

---

# 📊 Statistics Architecture

Located in:

```text
statistics/
```

---

# Philosophy

Statistics is intentionally:

* observational
* explainable
* deterministic
* reconstruction-based
* read-only

---

# Statistics Flow

```text
DB
→ DAO
→ Repository
→ Engine
→ Pipeline
→ ViewModel
→ UI
```

---

# DAO Layer

Located in:

```text
statistics/data/local/db/dao
```

---

# Responsibilities

* aggregation
* grouping
* projection generation
* SQL-level filtering

---

# Main Projections

---

# SessionCountPerDay

Used for:

* activity charts
* engagement timelines

---

# SessionDensityRaw

Used for:

* behavioral density analytics
* trend exploration

---

# Repository Layer

Located in:

```text
statistics/data/repository
```

---

# Responsibilities

* define query windows
* isolate storage semantics
* provide analytics-ready projections

---

# Engine Layer

Located in:

```text
statistics/domain/engine
```

---

# SessionsActivityEngine

Responsible for:

* timeline normalization
* missing-date reconstruction
* chronological stabilization

---

# SessionDensityEngine

Responsible for:

* density calculation
* filtering
* compression
* moving averages
* baseline computation

---

# Pipeline Layer

Located in:

```text
statistics/domain/pipeline
```

---

# Purpose

Separates:

* rendering-oriented preprocessing
* viewport constraints
* graph transformation semantics

from:

* analytics engines

---

# Analytics Layer

Located in:

```text
statistics/domain/analytics
```

---

# Purpose

Separates:

* raw statistics

from:

* behavioral interpretation

---

# RandomnessEnvelopeEngine

Provides expected behavioral uncertainty boundaries.

Purpose:

* reduce false-positive interpretation
* contextualize fluctuation
* improve trustworthiness

---

# Insight Layer

Located in:

```text
statistics/domain/insight
```

---

# InsightBuilder

Provides:

* human-readable interpretation
* trend explanation
* behavioral contextualization

---

# Important Constraint

Insights intentionally remain:

* deterministic
* explainable
* bounded

NOT:

* opaque AI scoring
* black-box prediction systems

---

# 📱 Presentation Architecture

---

# Compose Philosophy

Compose UI intentionally follows:

* state hoisting
* reactive rendering
* screen-level orchestration
* reusable bounded components

---

# UI Structure

```text
routes
→ screens
→ sections
→ reusable components
```

---

# Statistics UI

Main screens:

* StatisticsScreen
* DensityDetailScreen

Main sections:

* StatisticsDensitySection
* StatisticsActivitySection

---

# UX Philosophy

Statistics intentionally uses:

* selection-driven exploration
* conscious navigation
* detail-first interaction

Graphs are intentionally NOT auto-rendered by default.

---

# Markdown Architecture

Located in:

```text
shared/markdown
```

and:

```text
statistics/presentation/content
```

---

# Purpose

Supports:

* contextual help
* explainable analytics
* localized explanations
* semantic illustrations

---

# Markdown Philosophy

Markdown subsystem intentionally remains:

* passive
* declarative
* content-driven

NOT:

* scripting runtime
* dynamic execution system
* orchestration layer

---

# 🐹 Capybara Architecture

Located in:

```text
game/presentation/ui/capybara
```

---

# Philosophy

Capybara acts as:

```text
lightweight emotional reinforcement layer
```

---

# Purpose

Provides:

* emotional feedback
* soft UX personality
* non-intrusive reinforcement

---

# Important Constraint

Capybara intentionally avoids:

* manipulative engagement systems
* addictive loops
* attention hijacking

---

# Architecture Split

Capybara separates:

* behavior
* appearance

through:

* CapybaraEvent
* CapybaraMood

---

# 🔐 Profile Architecture

Located in:

```text
profile/
```

---

# Purpose

Provides:

* bounded user ownership
* child profile support
* parental separation
* security state tracking

---

# Important Principle

Profiles intentionally remain:

* isolated from gameplay reducers
* isolated from analytics engines
* isolated from renderer infrastructure

---

# Security Architecture

Includes:

* PIN hashing
* recovery challenge generation
* security event persistence

---

# Capability Direction

Current monetization direction:

Free:

* AFTER mode
* analytics
* explainability
* full core gameplay

Premium:

* BEFORE mode
* HYBRID mode
* child profiles

---

# 🎨 Visual Architecture

Located in:

```text
visual/
```

---

# Purpose

Separates:

* gameplay semantics

from:

* visual identity

---

# VisualConfig

Acts as:

```text
bounded visual identity descriptor
```

---

# Important Principle

Gameplay references:

```text
visualConfigId
```

but does NOT own:

* adaptive layout
* screen metrics
* rendering internals

---

# 🌍 Environment Architecture

Located in:

```text
core/ui/layout
```

---

# Purpose

Provides:

* screen bounds
* orientation
* physical layout constraints

---

# Important Constraint

Environment layer must NOT evolve into:

* mega-context runtime
* generalized adaptive engine
* global UI orchestration layer

---

# 🧪 Debug Infrastructure

Located in:

```text
debug/
```

---

# Purpose

Supports:

* realistic seed generation
* behavioral simulation
* analytics stress testing
* low-data validation

---

# Important Principle

Debug infrastructure remains:

* isolated from production runtime
* deterministic where possible
* development-oriented only

---

# ⚠️ Process Runtime Branch

Located in:

```text
game/domain/process
```

---

# Current Status

Experimental.

Frozen during 0.15 stabilization.

---

# Contains

* orchestration runtime
* transition engine
* hook system
* machine specifications
* runtime adapters

---

# Important Clarification

These systems are:

* retained for future research
* excluded from release runtime
* NOT considered active production architecture

---

# ⚠️ Architectural Risks

The primary architectural risks currently are:

* ownership erosion
* feature-flag scattering
* implicit lifecycle coupling
* generalized abstraction explosion
* adaptive mega-context growth

---

# Current Architectural Direction

The project intentionally prioritizes:

* deployment hardening
* supportability
* bounded ownership
* lifecycle clarity
* explainability
* predictable maintenance

over:

* orchestration-heavy execution
* generalized runtime abstraction
* theoretical framework elegance
* architecture-driven complexity

---

# Current State (0.15)

Implemented:

* perceptual gameplay core
* stimulus-based runtime
* renderer/resource separation
* explainable analytics
* randomness envelopes
* pipeline-based statistics rendering
* markdown help infrastructure
* emotional Capybara UX
* profiles & security
* visual configuration domain
* localized help content
* topology persistence

---

# Near-term Direction (0.15)

* deployment stabilization
* localization validation
* environment consolidation
* capability isolation
* supportability cleanup
* telemetry export stabilization
* restore correctness validation

---

# Mid-term Direction

* renderer expansion
* notebook growth
* consistency metrics
* session weighting
* behavioral segmentation

---

# Long-term Direction

* cloud synchronization
* advanced perceptual content
* AI-assisted interpretation
* personalized analytics
* orchestration revisiting
* multiplatform runtime

