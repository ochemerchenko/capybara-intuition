# CHANGELOG

## Version: 0.16

## Last Updated: 2026-05-20

---

# 🧭 Current Project Direction

The project has transitioned from:

```text
feature expansion and architectural experimentation
```

to:

```text
supportable deterministic product stabilization
```

The primary engineering priorities are now:

* deterministic runtime semantics
* explicit ownership
* bounded contexts
* deployment safety
* explainable analytics
* localization readiness
* telemetry preparation
* canonical persistent semantics
* telemetry-safe perceptual identity
* long-term maintainability

The project intentionally deprioritizes:

* generalized orchestration abstraction
* framework-style runtime systems
* hidden lifecycle coordination
* premature infrastructure complexity
* runtime-owned perceptual catalogs

---

# 🧠 Major Architectural Discovery

Sprint 16–17 stabilization revealed a fundamental architectural simplification:

```text
runtime never actually required specialized descriptor hierarchy
```

The surviving gameplay runtime naturally converged toward:

```text
canonical runtime resources
```

instead of:

```text
renderer-specific perceptual descriptor inheritance
```

This discovery significantly reduced:

* semantic duplication
* runtime-owned catalog complexity
* telemetry ambiguity
* renderer coupling
* analytics reconstruction complexity
* perceptual ownership ambiguity

---

# 🗄 Persistent Resource Migration

The project has now officially transitioned from:

```text
hardcoded runtime-owned perceptual catalogs
```

toward:

```text
persistent DB-backed perceptual resource infrastructure
```

This migration introduced canonical persistent perceptual identity:

```text
(notebookId, resourceId)
```

Where:

* notebookId == NotebookEntity.name
* resourceId == ResourceEntity.name

This coordinate system now acts as the canonical semantic identity for:

* gameplay runtime
* analytics
* telemetry
* Firebase export
* behavioral reconstruction
* future adaptive systems

---

## Canonical Ownership Chain

The runtime now follows:

```text
Profile
→ activeNotebookName

Notebook
→ owns resources

Session
→ owns notebookId + runtime resources

Rounds
→ reference resource identity

Analytics
→ operates on (notebookId, resourceId)
```

This eliminated previous reliance on:

* hardcoded notebooks
* runtime-owned catalog resolution
* implicit perceptual ownership
* renderer-oriented identity semantics

---

## Persistent Catalog Infrastructure

Introduced canonical persistent resource infrastructure:

* ResourceEntity
* NotebookEntity
* NotebookResourceLinkEntity
* NotebookRepository
* ResourceDao
* NotebookDao
* NotebookResourceLinkDao
* ResourceSeeder
* NotebookSeeder
* NotebookResourceLinkSeeder
* BootstrapLoader

The DB-backed catalog model now acts as the canonical perceptual source.

---

## Runtime Resource Migration

Gameplay runtime now directly operates on:

```text
Runtime Resource
```

instead of:

```text
ColorDescriptor
DrawableDescriptor
ResourceDescriptor hierarchy
```

The surviving runtime architecture became:

```text
GameState
↓
GameSession
↓
GameRound
↓
Runtime Resource
↓
RendererResolver
↓
Renderer
↓
Compose UI
```

This significantly improved:

* ownership clarity
* runtime traceability
* supportability
* maintainability
* debugging simplicity
* telemetry compatibility

---

## Active Deprecation Direction

The following structures are now formally deprecated and scheduled for removal:

```text
resource
├── color
│   ├── ColorDescriptor.kt
│   ├── ColorLibrary.kt
│   └── notebook
├── image
│   ├── DrawableDescriptor.kt
│   ├── DrawableLibrary.kt
│   └── notebook
├── ResourceDescriptor.kt
└── StimulusResourceResolver.kt
```

These structures were identified as:

```text
legacy runtime-owned catalog architecture
```

and are being removed because they:

* duplicate persistent semantics
* create implicit ownership
* couple runtime to hardcoded catalogs
* complicate telemetry semantics
* complicate analytics reconstruction
* prevent canonical perceptual identity tracking

---

# 🎮 Gameplay Runtime Evolution

## Deterministic Runtime Architecture

Gameplay runtime now follows:

```text
event
→ router
→ handlers
→ canonical GameState
→ next runtime event
```

instead of:

```text
reducer + mapper pseudo-runtime
```

This established:

* explicit runtime continuation
* deterministic sequencing
* canonical runtime ownership
* traceable lifecycle semantics
* supportable runtime flow

---

## Runtime Simplification Discovery

Sprint 16 stabilization revealed that the actual surviving runtime architecture became significantly simpler than the historical documentation suggested.

The following abstraction layers were proven obsolete and removed from active runtime semantics:

* RoundOption
* Stimulus
* SemanticPosition
* Position
* gameplay-owned layout runtime
* RoundState lifecycle runtime
* PostRoundActionType continuation semantics

The runtime architecture effectively simplified toward:

```text
GameRound
→ Runtime Resource
→ RendererResolver
→ Renderer
→ Compose UI
```

This significantly improved:

* ownership clarity
* runtime traceability
* supportability
* maintainability
* debugging simplicity

---

## GameFlowRouter Clarification

`GameFlowRouter` now acts as:

* deterministic transition coordinator
* next-event resolver
* atomic runtime transition publisher

The router intentionally does NOT own:

* continuation recursion
* async orchestration
* runtime scheduling
* queue management

Runtime continuation ownership belongs to:

```text
runtime boundary coordination layer
```

implemented inside ViewModel continuation handling.

Current continuation semantics:

```kotlin
publish(updatedState)

if (nextEvent != null) {
    submit(nextEvent)
}
```

This model intentionally prioritizes:

* explicit continuation
* localized ownership
* lifecycle visibility
* deterministic progression
* supportability

---

## Runtime Continuation Reframed

Recursive continuation is no longer treated as:

```text
system-wide architectural instability
```

It is now considered:

```text
localized continuation governance limitation
```

The current architecture already avoids:

* reducer-driven continuation
* router recursion
* hidden orchestration recursion
* distributed lifecycle ownership

Remaining future concerns are limited to:

* reentrancy semantics
* interruption policy
* async sequencing governance

---

## BEFORE Runtime Fix

Previous versions exposed BEFORE mode only as:

* UI semantics
* DB/runtime labels
* analytics metadata

while gameplay behavior internally remained AFTER-only.

0.16 implemented actual BEFORE runtime semantics:

```text
show options
→ user selection
→ system bet
→ resolve
```

This eliminated:

* semantic inconsistency
* analytics invalidity
* runtime ambiguity
* product trust risks

---

## Canonical Runtime State

Gameplay runtime now fully relies on:

```text
canonical immutable GameState
```

instead of:

* synthesized UI state
* mapper-owned runtime semantics
* reducer-generated pseudo-runtime

UI now acts as:

```text
render-only runtime projection
```

plus:

```text
event generation boundary
```

---

# 🧩 Runtime Handler Architecture

Gameplay logic decomposition into handlers was preserved and stabilized.

Handlers provide:

* semantic decomposition
* runtime readability
* explicit lifecycle semantics
* transition traceability

Handlers intentionally do NOT own:

* continuation recursion
* runtime scheduling
* orchestration governance
* async lifecycle coordination
* catalog resolution

---

# 🔄 Passive Runtime Events

Introduced explicit passive runtime events:

* `RoundCreated`
* `RoundFinished`
* `ShowGameSet`
* `ContinueRoundFlow`

Purpose:

* lifecycle traceability
* explicit runtime checkpoints
* elimination of hidden reducer transitions

---

# 🎨 Renderer Architecture Stabilization

Gameplay runtime now intentionally separates:

* gameplay semantics
* perceptual entities
* runtime resources
* renderers
* visual realization

This stabilized:

* renderer independence
* notebook ownership
* bounded perceptual semantics
* future content extensibility

Current renderer architecture includes:

### Color Renderers

* CheckerRenderer
* CircumferenceRenderer
* SolidFillRenderer

### Image Renderers

* DirectImageRenderer
* DistortedImageRenderer

---

## Renderer Migration Direction

Renderer infrastructure is actively migrating away from:

* ColorDescriptor
* DrawableDescriptor
* ResourceDescriptor hierarchy

Future renderer semantics are expected to derive presentation directly from canonical runtime resources.

---

## CheckerRenderer Recovery

Recovered correct checker-pattern rendering after Sprint 16 cleanup.

Root cause:

```text
invalid Color(Long) reconstruction caused transparent colors
```

The issue originated from incorrect packed color handling during renderer execution.

The renderer pipeline was stabilized by restoring proper color reconstruction semantics.

This restored:

* checker-pattern rendering
* runtime renderer correctness
* visual gameplay identity

---

# 🧱 Topology Architecture Simplification

Gameplay topology evolved beyond MATRIX-only assumptions.

Introduced historically:

* `Topology`
* `TopologyNode`
* semantic positioning
* realization separation

However Sprint 16 stabilization demonstrated that most topology-realization infrastructure no longer participates in actual runtime execution.

The following systems were effectively deprecated or removed from active semantics:

* semantic positioning runtime
* gameplay-owned coordinate systems
* layout realization ownership
* topology-aware placement runtime
* game/domain/layout branch

Topology now primarily acts as:

```text
presentation-oriented gameplay preset infrastructure
```

instead of semantic runtime ownership.

---

# 🎨 Presentation Layout Separation

Adaptive layout infrastructure was explicitly separated from gameplay runtime ownership.

Surviving presentation layout infrastructure now includes:

* `rememberGameLayoutMetrics()`
* `GameLayoutMetrics`

Layout calculation now formally belongs to:

```text
presentation layer
```

NOT:

```text
gameplay runtime
```

Gameplay runtime intentionally no longer owns:

* semantic coordinates
* screen-aware positioning
* adaptive scaling logic
* layout realization semantics

This significantly reduced:

* lifecycle coupling
* ownership explosion
* layout/runtime mixing

---

# 📊 Statistics Architecture

Statistics subsystem is now formally aligned with:

```text
deterministic runtime reconstruction
```

instead of:

```text
UI-driven analytics projection
```

Statistics now observes:

```text
actual gameplay runtime behavior
```

rather than synthesized reducer semantics.

Statistics now also observes canonical perceptual coordinates:

```text
(notebookId, resourceId)
```

This significantly improved:

* explainability
* trustworthiness
* behavioral validity
* auditability
* telemetry compatibility

Statistics intentionally remains:

```text
strictly read-only observational infrastructure
```

Gameplay runtime and statistics are now explicitly separated.

---

# 🔐 Profiles & Security

The project now contains isolated bounded profile/security infrastructure.

Implemented foundations include:

* profile persistence
* parent/child ownership
* PIN hashing
* recovery challenges
* security event tracking
* active notebook ownership

Profiles now explicitly own:

```text
activeNotebookName
```

instead of relying on implicit runtime notebook resolution.

Profile subsystem intentionally remains isolated from:

* gameplay runtime
* statistics
* renderer infrastructure
* telemetry lifecycle

---

# ☁️ Telemetry Direction

Telemetry architecture now exists as:

```text
passive observational infrastructure
```

Current direction:

```text
Runtime Records
→ Telemetry Repository
→ Export Layer
→ Firebase
```

Telemetry intentionally does NOT influence:

* gameplay runtime
* runtime continuation
* analytics interpretation
* gameplay outcomes

Telemetry now relies on canonical perceptual identity:

```text
(notebookId, resourceId)
```

instead of transient renderer-oriented runtime descriptors.

---

# 📘 Markdown & Explainability

The project now includes markdown-driven explainability infrastructure.

Implemented:

* localized markdown loading
* reusable markdown dialogs
* semantic illustrations
* contextual explanations

Markdown subsystem intentionally remains:

```text
passive and declarative
```

NOT:

* orchestration runtime
* scripting engine
* gameplay coordination layer

---

# 🌍 Localization Direction

Localization is now treated as:

```text
product-quality infrastructure
```

rather than simple translation support.

Primary concerns now include:

* terminology consistency
* layout resilience
* fallback correctness
* explainability clarity
* maintainability

---

# 🧹 Architectural Cleanup

## Removed / Deprecated

Removed, deprecated or proven obsolete during Sprint 16 stabilization:

* GameUiMapper orchestration semantics
* synthesized gameplay UI state
* reducer-driven continuation
* fake BEFORE semantics
* RouteResult lifecycle wrappers
* mapper-owned gameplay flow
* RoundMachineSpec
* process runtime branch
* engine runtime branch
* semantic positioning runtime
* gameplay-owned layout runtime
* topology-owned coordinate systems
* RoundOption realization layer
* Stimulus runtime abstraction
* RoundState lifecycle runtime
* PostRoundActionType continuation semantics
* ResourceCategory

Actively deprecated during current migration:

* ResourceDescriptor hierarchy
* ColorDescriptor
* DrawableDescriptor
* ColorLibrary
* DrawableLibrary
* hardcoded notebook ownership
* runtime-owned catalog resolution
* StimulusResourceResolver

---

## Runtime Branch Discovery

Sprint 16 cleanup empirically demonstrated that:

```text
game/domain/process
```

and:

```text
game/domain/engine
```

no longer participate in production runtime execution.

The application successfully:

* compiles
* launches
* executes gameplay runtime

without these branches.

This reclassified them from:

```text
frozen experimental runtime
```

to:

```text
obsolete architecture remnants
```

---

## Dead-file Cleanup

The following files/subsystems were identified as unused or obsolete during stabilization analysis:

* LayoutInitializer
* LayoutTopologyResolver
* BetLayerConfigs
* generateStimuli()
* Position
* SemanticPosition
* Stimulus
* RoundOption

The cleanup direction is now:

```text
minimum meaningful architecture surface
```

instead of preserving speculative abstraction layers.

---

# 🧠 Architectural Discoveries

Sprint 16 produced several important architectural discoveries.

---

## Real Runtime Simpler Than Documentation

The actual surviving runtime architecture became:

```text
cleaner than historical documentation
```

This indicated successful reduction of:

* speculative abstractions
* orchestration remnants
* semantic over-modeling

---

## Presentation/Layout Separation Crystallized

The project now clearly separates:

### Gameplay Runtime

Responsible for:

* runtime semantics
* deterministic continuation
* gameplay interaction
* correctness logic

### Presentation Layer

Responsible for:

* adaptive layout
* spacing
* scaling
* visual realization

This eliminated significant architectural ambiguity.

---

## Supportability-first Direction Confirmed

Sprint 16 strongly validated that:

```text
small explicit bounded systems
```

survive long-term stabilization better than:

```text
generalized orchestration abstractions
```

---

## Canonical Persistent Identity Emerged

The project converged toward:

```text
persistent perceptual identity
```

as the canonical semantic center of the system.

The coordinate system:

```text
(notebookId, resourceId)
```

now unifies:

* gameplay runtime
* persistence
* analytics
* telemetry
* Firebase export
* renderer semantics
* behavioral reconstruction
* future adaptive systems

This became the primary semantic stabilization axis of the project.

---

# ⚠️ Current Known Limitations

Still intentionally incomplete:

* HYBRID lifecycle stabilization
* process-death validation
* capability centralization
* renderer/resource semantic cleanup
* telemetry export stabilization
* continuation governance formalization
* topology cleanup finalization
* legacy resource migration completion

These are now treated as:

```text
supportability stabilization work
```

NOT:

```text
core architecture uncertainty
```

---

# 🎯 Current Strategic Position

Version 0.16 represents the stabilization point where the project fully transitioned from:

```text
experimental architecture playground
```

toward:

```text
supportable deterministic runtime product
```

The project now has:

* deterministic runtime architecture
* explicit lifecycle ownership
* canonical runtime semantics
* explainable analytics foundation
* bounded subsystem isolation
* deployment-oriented direction
* canonical persistent perceptual identity
* DB-backed perceptual resource infrastructure

The primary engineering goal is now:

```text
predictable long-term evolution
```

instead of:

```text
continuous architectural reinvention
```


# CHANGELOG

All notable changes to the Intuition project will be documented in this file.

The project currently follows a publication-oriented stabilization strategy focused on deterministic runtime behavior, bounded topology semantics, supportability, and truthful analytics.

---

# [0.16-1] - 2026-05-27

## Strategic Direction

- Transitioned fully into:
  - publication-oriented deterministic runtime stabilization
  - supportability-first architecture refinement
  - bounded topology composition
  - deployable truthful runtime preparation

- The project is now considered:
  - feature-complete for first public publication
  - structurally stable for bounded release hardening

---

## Core Runtime

### Added

- Deterministic runtime execution pipeline:
  - event
  - router
  - semantic runtime handler
  - runtime execution context
  - next runtime event

- Canonical runtime ownership semantics:
  - Profile owns gameplay identity
  - Session owns runtime execution
  - Round owns bounded gameplay interaction
  - Statistics owns observational reconstruction
  - Telemetry owns observational export

- Reactive runtime projection:
  - DB
  - DAO
  - Repository
  - Flow
  - ViewModel
  - UI

### Changed

- Removed reducer-era continuation architecture remnants
- Removed hidden gameplay reconstruction semantics
- Stabilized explicit ownership boundaries
- Simplified runtime orchestration model
- Strengthened deterministic runtime materialization semantics

### Deprecated

- Generic reducer orchestration
- Runtime-owned gameplay defaults
- Hidden synchronization chains
- Scattered runtime ownership
- UI-driven runtime repair flows

---

## Gameplay Runtime

### Added

- Stable AFTER gameplay semantics
- Stable BEFORE gameplay semantics
- Stable HYBRID gameplay semantics
- Deterministic runtime materialization flow
- Renderer-independent gameplay semantics

### Stabilized

- GameRoundRecord as canonical observational primitive:

```kotlin
GameRoundRecord(
    mode,
    level,
    shownColors,
    correctColor,
    selectedColor,
    isCorrect,
    startedAt,
    finishedAt
)
```

### Notes

- Structural mutation of GameRoundRecord is now considered high-risk and requires strong architectural justification.

---

## Topology Runtime

### Added

- Canonical matrix topology runtime
- Sparse matrix topology support
- Blocked topology cell semantics
- Logical colspan / rowspan occupancy semantics
- Immutable topology coordinates
- Deterministic topology ordering

### Changed

- Topology now acts as:
  - canonical gameplay occupancy space
  - renderer-independent gameplay structure

### Publication Boundary

Before first publication:

- ONLY MATRIX TOPOLOGIES EXIST

### Deprecated

- Runtime topology generation
- Procedural topology synthesis
- Renderer-owned geometry semantics
- Adaptive topology mutation
- Layout-driven gameplay semantics

---

## Capability System

### Added

- Explicit runtime capability presets:
  - ProductionFreeCapabilities
  - ProductionPremiumCapabilities
  - TestFreeCapabilities
  - TestPremiumCapabilities

- Capability-driven topology composition

### Changed

Capabilities now define:

- what runtime topology exists

instead of:

- runtime behavior mutation
- distributed feature permissions

### Runtime Presets

#### PRODUCTION + FREE

- AFTER gameplay only
- single HUMAN topology
- no CHILD runtime
- no TESTER runtime

#### PRODUCTION + PREMIUM

- BEFORE gameplay
- HYBRID gameplay
- CHILD topology
- protected transitions
- recovery runtime

---

## Profile Runtime

### Added

- Pairwise runtime ownership transfer semantics
- Protected CHILD → HUMAN transition flow
- Parent PIN authorization runtime
- Recovery challenge runtime
- Runtime topology projection

### Changed

Canonical transition meaning:

```text
switch profile
=
runtime ownership transfer
```

instead of:

```text
selected UI item mutation
```

### Security

- Added bounded authorization semantics
- Added failed PIN tracking
- Added deterministic recovery escalation
- Added recovery-based PIN reset support

---

## TESTER Runtime

### Added

- Dedicated TESTER topology
- Analytics validation runtime
- Graph dataset validation support
- Telemetry validation runtime
- Rendering validation support

### Security Constraints

- TESTER remains physically absent from PRODUCTION topology
- CHILD → TESTER transitions forbidden
- TESTER is not treated as developer superuser infrastructure

---

## Statistics & Analytics

### Added

- Deterministic analytics reconstruction pipeline
- Graph generation infrastructure
- Density analysis
- Trend analysis
- Compression pipelines
- Explainable behavioral interpretation

### Changed

Statistics now reconstruct:

```text
what happened
```

instead of:

```text
what should happen next
```

### Constraints

- Statistics remain strictly observational
- No gameplay mutation paths allowed

---

## Telemetry

### Added

- Telemetry foundation infrastructure
- Installation runtime
- Observational telemetry export direction
- Firebase-oriented export pipeline preparation

### Changed

Telemetry semantics now explicitly remain:

- observational only
- gameplay-independent
- runtime-passive

---

## Monetization

### Added

- Runtime topology expansion monetization model
- FREE vs PREMIUM topology separation
- Capability-based entitlement projection
- Publication-safe monetization direction

### Changed

Premium now represents:

```text
runtime topology expansion
```

instead of:

```text
arbitrary feature gating
```

### Strategic Direction

The monetization model intentionally avoids:

- manipulative subscription funnels
- dark patterns
- ad-driven gameplay interruption
- coercive upgrade pressure

---

## Localization

### Added

- Publication-safe bilingual localization strategy
- Emotional tone preservation guidelines
- Recovery-runtime localization constraints

### Current Languages

- Ukrainian
- English

### Planned Post-Publication Languages

- Spanish
- Portuguese
- German
- French
- Polish

---

## Emotional UX

### Added

- Capybara emotional runtime layer
- Contextual tone mapping
- Lightweight emotional microcopy
- Calm UX direction

### Strategic Positioning

The emotional layer intentionally optimizes for:

```text
quiet trustworthy cognitive interaction
```

instead of:

```text
hyper-gamified engagement
```

---

## Publication Stabilization

### Current Focus

- runtime stabilization
- localization completion
- rendering validation
- telemetry validation
- monetization hardening
- support infrastructure
- publication-safe UX refinement

### Remaining High-Priority Work

- Firebase Analytics integration
- Crashlytics integration
- Google Billing integration
- localization completion
- store asset preparation
- graph dataset restoration
- TESTER analytics restoration
- cross-device rendering validation

---

## Architecture

### Added

- Explicit aggregate ownership semantics
- Deterministic runtime reconstruction philosophy
- Canonical perceptual identity:
  - (notebookId, resourceId)

- Append-only observational runtime history
- SQL-level invariant preservation direction
- Supportability-first stabilization strategy

### Deprecated

- Reducer-era continuation systems
- Renderer-owned geometry
- Hidden orchestration
- Runtime-generated layout semantics
- Manual synchronization rituals
- Distributed runtime ownership

---

## Strategic Identity

The project is now positioned as:

```text
stable deployable deterministic cognitive runtime product
```

with emphasis on:

- deterministic runtime semantics
- calm cognitive interaction
- explainable analytics
- bounded runtime topology
- supportable architecture
- anti-addictive UX philosophy
- truthful behavioral reconstruction
