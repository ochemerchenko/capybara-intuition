# Game Data Model

## Version: 0.15

## Last Updated: 2026-05-14

---

# Overview

The gameplay subsystem represents a bounded runtime domain responsible for:

- session lifecycle
- round execution
- perceptual interaction
- gameplay progression
- behavioral persistence
- deterministic runtime coordination

The architecture intentionally prioritizes:

- explicit ownership
- deterministic lifecycle
- reducer-driven state transitions
- supportability
- explainable runtime semantics
- bounded context clarity

Gameplay runtime remains intentionally isolated from:

- statistics
- analytics interpretation
- markdown/help rendering
- monetization infrastructure
- adaptive environment systems
- telemetry orchestration

---

# 🧠 Architectural Philosophy

Gameplay runtime follows:

- explicit reducer lifecycle
- immutable state transitions
- bounded domain ownership
- deterministic progression
- supportability-first architecture

The current runtime intentionally avoids:

- generalized orchestration engines
- implicit runtime graphs
- dynamic transition frameworks
- hook-driven lifecycle execution
- distributed ownership systems

Reducer-driven lifecycle is currently considered:

- sufficiently explicit
- predictable
- supportable
- deployment-safe
- operationally maintainable

---

# 📦 Core Runtime Model

Gameplay runtime is centered around:

```text
GameState
↓
GameSession
↓
GameRound
↓
RoundOption
↓
Stimulus + SemanticPosition
```

---

# 1. GameState

Located in:

```text
game/domain/model
```

---

## Purpose

Represents the complete immutable gameplay runtime snapshot.

Acts as the primary reducer state.

---

## Structure

```kotlin
GameState(
    val session: GameSession?,
    val currentRound: GameRound?,
    val roundIndex: Int = 0
)
```

---

## Ownership

GameState owns:

- active session
- active round
- gameplay progression index

GameState intentionally does NOT own:

- statistics
- UI state
- navigation
- dialogs
- renderer internals
- monetization state
- telemetry
- persistence orchestration

---

# 2. GameSession

Represents the long-lived gameplay context.

---

## Purpose

Defines:

- gameplay mode
- gameplay level
- visual identity
- resource ownership
- renderer ownership
- profile ownership

---

## Structure

```kotlin
GameSession(
    val id: String,
    val mode: GameMode,
    val level: Int,
    val resourceCategory: ResourceCategory,
    val notebookId: String,
    val maskType: MaskType,
    val rendererCategory: RendererCategory,
    val visualConfigId: String,
    val startedAt: Long,
    val finishedAt: Long,
    val profileId: String
)
```

---

## Session Responsibilities

Session owns:

- gameplay configuration
- content selection
- renderer semantics
- visual identity binding
- persistence ownership

Session intentionally does NOT own:

- current round state
- runtime transitions
- statistics
- capability evaluation
- UI semantics

---

# 3. GameRound

Represents a single gameplay interaction cycle.

---

## Purpose

Defines:

- perceptual options
- correctness semantics
- user selection
- timing information
- round lifecycle state

---

## Structure

```kotlin
GameRound(

    val options: List<RoundOption>,

    val roundType: RoundType,
    val phase: RoundPhase,
    val roundState: RoundState,

    val correctStimulus: Stimulus?,
    val correctPosition: Position?,

    val selectedStimulus: Stimulus?,
    val selectedPosition: Position?,

    val startedAt: Long,
    val userSelectedAt: Long?,
    val resultShownAt: Long?,
    val firstUserActionAt: Long?,

    val postRoundActionType: PostRoundActionType?
)
```

---

## Round Responsibilities

GameRound owns:

- perceptual state
- correctness state
- user interaction state
- timing state
- lifecycle phase

GameRound intentionally does NOT own:

- statistics
- persistence logic
- rendering internals
- orchestration logic
- capability ownership

---

# 4. RoundOption

Represents a playable perceptual option.

---

## Structure

```kotlin
RoundOption(
    val stimulus: Stimulus,
    val semanticPosition: SemanticPosition
)
```

---

## Responsibilities

RoundOption binds:

- perceptual stimulus
- semantic position
- visual placement

---

# 5. Stimulus

Represents abstract perceptual gameplay entity.

---

# Philosophy

Stimulus architecture replaced:

```text
color_card
```

with:

```text
renderer-independent perceptual entities
```

---

## Purpose

Stimulus intentionally abstracts:

- colors
- images
- future perceptual content

from:

- gameplay semantics
- rendering mechanics
- visual representation

---

## Architectural Importance

Stimulus migration became one of the most important architectural evolutions of the project.

It enabled:

- renderer separation
- resource separation
- notebook ownership
- supportable extensibility
- bounded perceptual semantics

---

# 6. Positioning Model

---

# Position

Represents physical grid coordinates.

```kotlin
Position(
    val row: Int,
    val col: Int
)
```

---

# SemanticPosition

Represents semantic layout ownership.

```kotlin
SemanticPosition(
    val id: String,
    val visual: Position
)
```

---

## Philosophy

The architecture intentionally separates:

- semantic position identity

from:

- visual coordinates

This improves:

- supportability
- layout flexibility
- topology reuse
- renderer independence

---

# 7. Round Lifecycle

---

# RoundType

Defines gameplay interaction ordering.

Supported:

- AFTER
- BEFORE
- HYBRID

---

## Monetization Direction

Current capability strategy:

Free:

- AFTER

Premium:

- BEFORE
- HYBRID

---

# RoundPhase

Represents explicit runtime lifecycle phase.

Examples:

- OPTIONS_READY
- RESOLVED

---

# RoundState

Represents broader round lifecycle ownership.

Examples:

- ACTIVE
- FINISHED

---

# PostRoundActionType

Represents continuation semantics after resolution.

Purpose:

- explicit progression semantics
- future continuation control
- deterministic lifecycle ownership

---

# 8. Game Events

Located in:

```text
game/domain/events
```

---

## Purpose

Events define reducer transition inputs.

Gameplay runtime intentionally uses:

- explicit events
- immutable transitions
- deterministic reducers

---

## Examples

```kotlin
GameEvent.StartSession
GameEvent.StartRound
GameEvent.UserSelected
GameEvent.ResolveRound
```

---

# 9. GameEngine

Located in:

```text
game/domain/engine
```

---

## Purpose

GameEngine acts as:

- reducer
- gameplay lifecycle coordinator
- explicit runtime transition owner

---

## Responsibilities

- process events
- transition immutable state
- coordinate round lifecycle
- trigger continuation semantics

---

## Important Principle

GameEngine intentionally remains:

- explicit
- deterministic
- bounded

NOT:

- generalized orchestration runtime
- dynamic execution graph
- hook-driven framework
- distributed lifecycle engine

---

## Architectural Constraints

GameEngine intentionally does NOT own:

- persistence coordination
- telemetry
- analytics
- renderer implementation
- localization
- profile runtime lifecycle

---

# 10. GameEngineHandlers

Represents extracted semantic reducer logic.

---

## Purpose

Improves:

- readability
- supportability
- lifecycle traceability
- reducer maintainability

---

## Responsibilities

Examples:

- handleUserSelection()
- handleResolveRound()

---

## Architectural Role

GameEngineHandlers preserve:

```text
semantic decomposition
without orchestration abstraction
```

---

# 11. RoundFactory

Located in:

```text
game/domain/round
```

---

## Purpose

Isolates:

- round construction
- topology binding
- option generation
- gameplay initialization

---

## Philosophy

RoundFactory prevents:

- reducer construction explosion
- lifecycle ambiguity
- topology leakage
- renderer coupling

---

# 12. Layout Topology

Located in:

```text
game/domain/layout
```

---

# LayoutTopology

Represents semantic layout structure.

---

# LayoutTopologyPosition

Represents persisted semantic position.

---

# LayoutTopologyResolver

Resolves:

- topology configuration
- persisted layout semantics
- level-based layout ownership

---

## Philosophy

Layout semantics remain separated from:

- renderer implementation
- gameplay rules
- UI composition
- visual metrics

---

# 13. Resource & Renderer Binding

Gameplay runtime intentionally separates:

- perceptual entities
- resources
- renderers
- visual identity

---

# Resource Ownership

Defined by:

- ResourceCategory
- notebook ownership
- resource descriptors

---

# Renderer Ownership

Defined by:

- RendererCategory
- renderer resolver
- visual presentation semantics

---

## Important Principle

Gameplay runtime intentionally does NOT know:

- drawable internals
- rendering mechanics
- visual adaptation logic
- Compose rendering semantics

---

# 14. Visual Binding

Gameplay references visual identity through:

```kotlin
visualConfigId
```

---

## Philosophy

Gameplay intentionally references:

- visual identity

but NOT:

- screen metrics
- adaptive layouts
- runtime dimensions
- device environment

---

# 15. Persistence Model

Gameplay persistence includes:

- sessions
- observed rounds
- layout configs
- visual configs

---

## Philosophy

Gameplay persistence remains:

- append-oriented
- deterministic
- reconstruction-friendly
- runtime-agnostic

Statistics derive behavior from persisted runtime records.

Gameplay itself never depends on statistics.

---

## Repository Philosophy

Repositories intentionally remain:

```text
boring bounded persistence adapters
```

Repositories MUST NOT:

- orchestrate runtime lifecycle
- coordinate gameplay flow
- mutate reducer state
- perform hidden synchronization

---

# 16. Runtime Synchronization

Gameplay runtime synchronization intentionally remains:

```text
explicit and visible
```

---

## Philosophy

The architecture intentionally avoids:

- hidden lifecycle synchronization
- implicit runtime restoration
- repository-owned runtime mutation

---

## Ownership Separation

Gameplay lifecycle synchronization belongs to:

- runtime boundaries
- ViewModels
- bootstrap layers

NOT:

- repositories
- DAOs
- persistence adapters

---

# 17. Process Runtime Branch

Located in:

```text
game/domain/process
```

---

## Status

Experimental.

Frozen during 0.15 stabilization.

---

## Contains

- orchestration runtime
- transition engine
- hooks
- machine specs
- runtime adapters

---

## Important Clarification

These systems are:

- retained for future research
- excluded from release runtime
- NOT considered active production architecture

---

## Freeze Rationale

The orchestration branch was frozen because it introduced risks of:

- implicit relationship explosion
- lifecycle ambiguity
- ownership erosion
- debugging complexity
- runtime opacity

---

# 18. Capability Strategy

Gameplay capability ownership remains bounded.

---

# Free Capabilities

- AFTER gameplay loop
- statistics access
- explainability support
- core perceptual training

---

# Premium Capabilities

- BEFORE mode
- HYBRID mode
- child profiles

---

## Architectural Principle

Capability logic must remain isolated from:

- reducers
- profiles
- renderers
- persistence
- environment runtime

This prevents:

- feature-flag scattering
- ownership erosion
- monetization coupling

---

# 19. Explainability Integration

Gameplay integrates with:

- markdown help
- contextual explanations
- semantic illustrations

But gameplay intentionally does NOT:

- render markdown
- control dialogs
- manage localization
- own help infrastructure

---

# 20. Runtime Constraints

---

# Deterministic Lifecycle

Gameplay runtime intentionally prioritizes:

- explicit transitions
- immutable state
- reproducibility
- predictable continuation

---

# No Gameplay AI

Gameplay runtime intentionally avoids:

- adaptive difficulty AI
- hidden gameplay scoring
- opaque prediction systems
- behavioral manipulation loops

---

# Renderer Isolation

Gameplay must not:

- know renderer internals
- own drawable semantics
- perform visual calculations

---

# Environment Isolation

Gameplay must not:

- own adaptive metrics
- own screen scaling
- own runtime dimensions

These belong to bounded environment models.

---

# 21. Architectural Clarifications (0.15)

The project now intentionally prioritizes:

- explicit ownership
- bounded contexts
- deterministic lifecycle semantics
- supportability-first architecture

over:

- generalized runtime abstraction
- orchestration-heavy execution
- implicit lifecycle graphs
- mega-context environments

---

# ⚠️ Architectural Risks

The primary gameplay architectural risks currently are:

- ownership erosion
- hidden runtime synchronization
- lifecycle coupling
- feature-flag scattering
- adaptive mega-context growth
- renderer/runtime semantic leakage

---

# 🚧 Known Limitations (0.15)

- process-death restore still under validation
- BEFORE flow still partially legacy
- HYBRID lifecycle incomplete
- orchestration branch unfinished
- capability model not yet centralized
- environment metric ownership still evolving

---

# 🔜 Evolution Path

---

# Near-term (0.15)

- deployment hardening
- restore validation
- localization stabilization
- capability isolation
- supportability cleanup
- environment consolidation

---

# Mid-term

- lifecycle clarification
- capability centralization
- renderer expansion
- notebook growth
- telemetry export

---

# Long-term

- orchestration revisiting
- multiplatform runtime
- advanced perceptual content
- cloud synchronization
- adaptive behavioral systems
