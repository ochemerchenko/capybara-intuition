# ARCHITECTURE

## Version: 0.16

## Last Updated: 2026-05-20

---

# Overview

The system follows a bounded layered architecture with explicit separation between:

* gameplay runtime
* analytics subsystem
* rendering infrastructure
* profile/security domain
* visual configuration domain
* presentation/UI infrastructure
* telemetry infrastructure
* persistent perceptual resource infrastructure
* persistent gameplay identity infrastructure

The project intentionally prioritizes:

* explicit ownership
* deterministic runtime semantics
* supportability-first architecture
* explainable analytics
* bounded contexts
* deployment-safe complexity
* long-term maintainability
* canonical relational semantics

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

and:

```text
transition coordination
```

from:

```text
continuation ownership
```

and:

```text
runtime continuation
```

from:

```text
UI projection
```

and:

```text
persistent gameplay identity
```

from:

```text
runtime materialization
```

and:

```text
runtime execution context
```

from:

```text
historical persistence snapshots
```

---

# Primary System Flows

---

# 🎮 Gameplay Runtime Flow

```text
UI
→ ViewModel
→ Runtime Router
→ Runtime Handlers
→ Runtime Execution Context
→ Next Runtime Event
```

---

# 🧩 Gameplay Identity Materialization Flow

```text
SYSTEM Profile
→ HUMAN Profile Materialization
→ Runtime Session Materialization
→ Gameplay Runtime
```

---

# 📊 Statistics Read Flow

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

---

# 🎨 Rendering Flow

```text
Runtime Resource
→ RendererResolver
→ Renderer
→ Compose UI
```

---

# 🗄 Resource Catalog Flow

```text
Bootstrap JSON
→ DB Seeder
→ ResourceEntity
→ NotebookRepository
→ Runtime Resource
→ Runtime Execution
```

---

# 🎭 Renderer/Mask Dictionary Flow

```text
Bootstrap
→ RendererEntity / MaskEntity
→ Profile
→ Runtime Session
→ RendererResolver
```

---

# Gameplay Philosophy

Gameplay runtime intentionally follows:

```text
explicit deterministic event continuation
```

instead of:

```text
reducer-driven pseudo-orchestration
```

The architecture intentionally avoids:

* generalized orchestration engines
* implicit runtime graphs
* hook-driven gameplay execution
* mapper-owned runtime semantics
* synthesized UI gameplay state
* runtime-owned gameplay defaults
* distributed progression policy
* runtime-owned perceptual catalogs

---

# Gameplay Runtime Model

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

The runtime intentionally removed intermediate semantic realization layers such as:

* RoundOption
* Stimulus
* SemanticPosition
* Position
* gameplay-owned layout realization

The runtime is also actively migrating away from:

* descriptor inheritance hierarchies
* hardcoded perceptual catalogs
* runtime-owned notebook resolution
* runtime-owned gameplay defaults

---

# Runtime Router

Location:

```text
game/domain/router
```

Acts as:

* deterministic transition coordinator
* runtime transition publisher
* next-event resolver

NOT:

* runtime continuation owner
* orchestration engine
* lifecycle scheduler
* runtime queue manager
* gameplay policy owner

Current continuation semantics are intentionally localized inside ViewModel continuation handling:

```kotlin
publish(updatedState)

if (nextEvent != null) {
    submit(nextEvent)
}
```

---

# Runtime Event Chain

Gameplay runtime follows:

```text
event
→ router
→ handlers
→ runtime execution projection
→ next event
```

Examples:

```text
InitiateSessionCreation
→ InitiateRoundCreation
→ CreateRound
→ RoundCreated
→ ContinueRoundFlow
```

Passive runtime events intentionally exist to preserve:

* runtime traceability
* lifecycle checkpoints
* explicit transitions

---

# 🗄 Persistent Gameplay Identity Architecture

Located in:

```text
profile/
```

The project now treats Profile as:

```text
canonical persistent gameplay identity owner
```

instead of:

```text
runtime metadata holder
```

Profiles now explicitly own:

* activeNotebookName
* preferredMode
* currentLevel
* minLevel
* maxLevel
* rendererId
* maskId

Gameplay runtime no longer owns:

* default notebook
* default renderer
* default mask
* default level
* progression bounds

These semantics are now persisted as identity-owned relational state.

---

# 👤 ProfileType Semantics

ProfileType remains:

```text
code-owned invariant semantics
```

NOT:

```text
runtime dictionary semantics
```

Current invariant profile roles:

| Type   | Meaning                                      |
| ------ | -------------------------------------------- |
| SYSTEM | immutable bootstrap-owned canonical defaults |
| HUMAN  | mutable primary gameplay identity            |
| CHILD  | restricted subordinate gameplay identity     |

ProfileType defines:

* lifecycle semantics
* mutability semantics
* bootstrap semantics
* security semantics
* ownership constraints

ProfileType intentionally does NOT act as:

* runtime dimension
* analytics dictionary
* user-editable registry
* dynamic capability system

---

# 🧩 SYSTEM Profile Architecture

The SYSTEM profile now acts as:

```text
immutable bootstrap-owned gameplay identity
```

Responsibilities:

* canonical notebook defaults
* canonical renderer defaults
* canonical mask defaults
* canonical progression defaults
* canonical progression bounds

Human profiles materialize SYSTEM defaults once during creation and then evolve independently.

This intentionally avoids:

* runtime-owned hidden defaults
* distributed configuration ownership
* gameplay policy duplication
* runtime bootstrap reconstruction

---

# 🎮 Runtime Session Architecture

Gameplay runtime now treats:

```text
GameSession
```

as:

```text
runtime execution context
```

NOT:

```text
historical persistence snapshot
```

GameSession may carry:

* Profile
* runtime resources
* runtime timing
* execution-scoped state

because it exists strictly inside runtime execution boundaries.

GameSession intentionally does NOT act as:

* canonical gameplay identity owner
* immutable persistence snapshot
* long-term historical truth

---

# 🗄 Historical Session Persistence

The architecture now explicitly separates:

```text
runtime execution
```

from:

```text
historical persistence reconstruction
```

Historical truth belongs to:

```text
GameSessionEntity
```

NOT:

```text
GameSession
```

GameSessionEntity intentionally snapshots:

* notebookId
* mode
* level
* rendererId
* maskId

to preserve:

* deterministic reconstruction
* explainability
* telemetry correctness
* historical stability

even if Profile changes later.

---

# 🗄 Resource Architecture

Located in:

```text
game/domain/resource
```

Gameplay runtime intentionally separates:

* perceptual resources
* notebook ownership
* resource identity
* renderer realization

from:

* rendering mechanics
* gameplay continuation
* Compose implementation
* telemetry interpretation

Perceptual space is now formally defined by:

```text
(notebookId, resourceId)
```

Where:

* notebookId == NotebookEntity.name
* resourceId == ResourceEntity.name

This coordinate system is now the canonical semantic identity for:

* gameplay runtime
* persistence
* telemetry
* Firebase export
* analytics reconstruction
* behavioral interpretation

---

# 🎨 Relational Gameplay Dimensions

The following concepts are now treated as:

```text
stable relational dimensions
```

instead of runtime-owned semantics:

* notebooks
* resources
* renderers
* masks

These dimensions are:

* bootstrap-seeded
* canonical
* read-only
* persistence-backed
* telemetry-safe
* analytics-safe

The architecture intentionally avoids:

* dynamic capability discovery
* mutable semantic catalogs
* plugin registries
* orchestration-oriented renderer systems

---

# 🎨 Rendering Architecture

Located in:

```text
game/domain/renderer
```

Rendering infrastructure is responsible for:

* visual realization
* perceptual projection
* renderer semantics
* resource interpretation

Gameplay runtime intentionally delegates:

```text
visual realization
```

to rendering infrastructure.

Gameplay runtime MUST NOT know:

* Compose internals
* drawable implementation
* renderer internals
* adaptive scaling
* layout realization

Renderer infrastructure is actively migrating away from:

* ColorDescriptor
* DrawableDescriptor
* ResourceDescriptor inheritance chains

toward:

```text
canonical runtime resource semantics
```

---

# 📊 Statistics Architecture

Statistics acts as:

```text
deterministic runtime reconstruction layer
```

instead of:

```text
UI-driven analytics projection
```

Statistics MUST NEVER:

* influence gameplay runtime
* inject runtime events
* coordinate lifecycle
* modify outcomes

Statistics now observes canonical perceptual coordinates:

```text
(notebookId, resourceId)
```

instead of transient renderer-oriented runtime abstractions.

Statistics intentionally remains isolated from:

* runtime continuation
* gameplay orchestration
* renderer ownership
* profile lifecycle
* gameplay policy ownership

---

# 🧱 Persistence Philosophy

Persistence intentionally remains:

```text
boring bounded adapters
```

Repositories SHOULD:

* persist validated runtime data
* expose storage queries
* remain runtime-agnostic

Repositories MUST NOT:

* orchestrate gameplay lifecycle
* coordinate subsystem synchronization
* mutate runtime state implicitly

Main persistence entities:

* GameSessionEntity
* ObservedRoundEntity
* VisualConfigEntity
* ResourceEntity
* NotebookEntity
* NotebookResourceLinkEntity
* RendererEntity
* MaskEntity
* ProfileEntity

Transitional artifacts still under evaluation:

* LayoutConfigEntity
* LayoutPositionEntity
* StimulusEntity

---

# 🚀 Bootstrap Architecture

Bootstrap infrastructure intentionally remains:

* explicit
* deterministic
* bounded
* understandable

Bootstrap layers currently include:

* ProfileInitializer
* VisualConfigInitializer
* InstallationInitializer
* ResourceSeeder
* NotebookSeeder
* NotebookResourceLinkSeeder
* RendererSeeder
* MaskSeeder
* BootstrapLoader

Bootstrap is now additionally responsible for:

```text
SYSTEM profile materialization
```

Bootstrap MUST NOT evolve into:

* generalized orchestration runtime
* application-wide lifecycle engine
* dynamic capability platform
* runtime policy orchestration

---

# 🌍 Environment Architecture

Located in:

```text
core/ui/layout
```

Environment systems intentionally remain:

* lightweight
* bounded
* UI-oriented
* deterministic

Environment MAY provide:

* screen dimensions
* spacing metrics
* layout constraints
* chart metrics

Environment MUST NOT evolve into:

* generalized adaptive engine
* mega-context runtime
* global orchestration layer

---

# 📘 Markdown Explainability

Markdown subsystem intentionally remains:

```text
passive and declarative
```

Responsibilities:

* help content
* localized explanations
* markdown dialogs
* semantic illustrations

Markdown MUST NOT evolve into:

* runtime scripting
* orchestration layer
* gameplay coordination
* execution infrastructure

---

# ☁️ Telemetry Architecture

Telemetry intentionally remains:

```text
passive observational infrastructure
```

Telemetry MUST NOT:

* influence gameplay runtime
* mutate gameplay semantics
* coordinate continuation
* inject runtime behavior

Current direction:

```text
Runtime Records
→ Telemetry Repository
→ Export Layer
→ Firebase
```

Telemetry now relies on canonical perceptual identity coordinates instead of transient runtime descriptors.

---

# Removed / Deprecated Architecture

Removed or obsolete:

* reducer-driven continuation
* mapper-owned runtime semantics
* synthesized gameplay UI state
* RoundMachineSpec
* process runtime branch
* engine runtime branch
* semantic positioning runtime
* gameplay-owned layout runtime
* topology-owned coordinate system
* RoundState lifecycle runtime
* PostRoundActionType continuation semantics
* Stimulus runtime abstraction
* RoundOption realization layer

Actively deprecated during current migration:

* ColorLibrary
* DrawableLibrary
* ResourceDescriptor hierarchy
* hardcoded notebook ownership
* runtime-owned catalog resolution
* runtime-owned gameplay defaults

---

# Architectural Risk Awareness

The project continuously monitors risks of:

---

# Ownership Erosion

When subsystems begin to:

```text
know a little about everything
```

---

# Implicit Relationship Explosion

Especially dangerous in:

* capability systems
* orchestration remnants
* telemetry integration
* adaptive runtime systems
* runtime resource migration
* transitional compatibility layers
* distributed gameplay policy ownership
* identity/runtime duplication

---

# Lifecycle Coupling

When:

* UI
* telemetry
* analytics
* persistence

begin influencing gameplay runtime semantics.

---

# Semantic Duplication

Especially dangerous when:

* runtime recreates persisted identity semantics
* bootstrap defaults reappear in handlers
* progression policy exists in multiple layers
* dictionaries begin owning mutable state
* runtime execution context starts acting as persistence truth

---

# 🎯 Product Direction

The project intentionally transitioned from:

```text
experimental architecture playground
```

toward:

```text
supportable deterministic runtime product
```

The architectural priority is now:

* bounded ownership
* deterministic lifecycle
* deployment safety
* supportability
* explainability
* maintainability
* canonical persistent semantics
* telemetry-safe resource identity
* persistent gameplay identity
* relational semantic consistency

The system now intentionally optimizes for:

```text
predictable long-term evolution
```

instead of:

```text
continuous architectural reinvention
```


