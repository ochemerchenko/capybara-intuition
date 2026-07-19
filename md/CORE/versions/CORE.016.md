# CORE

## Version

0.16

## Last Updated

2026-05-20

---

# 🎯 Project Identity

Intuition is a:

```text
supportable deterministic cognitive training product
```

built around:

* explicit ownership
* bounded contexts
* deterministic gameplay runtime
* explainable analytics
* deployment-oriented architecture
* supportability-first evolution
* canonical persistent semantics
* relational gameplay identity
* telemetry-safe perceptual identity

The project intentionally prioritizes:

```text
supportability over theoretical elegance
```

and:

```text
explicit ownership over generalized abstraction
```

The current architecture reflects the stabilized runtime surface after Sprint 16 cleanup and the ongoing migration toward persistent perceptual resource infrastructure and persistent gameplay identity ownership.

---

# 🧠 Core Philosophy

The system is designed around:

```text
small explicit bounded systems
```

instead of:

```text
implicit orchestration frameworks
```

The architecture intentionally avoids:

* hidden lifecycle coordination
* generalized runtime engines
* adaptive orchestration graphs
* mega-context environments
* cross-cutting runtime ownership
* speculative abstraction layers
* runtime-owned perceptual catalogs
* runtime-owned gameplay defaults
* distributed gameplay policy ownership

The project intentionally optimizes for:

* debuggability
* lifecycle visibility
* deterministic behavior
* maintainable runtime evolution
* deployment safety
* telemetry-safe semantics
* canonical perceptual identity
* persistent gameplay identity

---

# 🧩 Primary Bounded Contexts

The project currently stabilizes around the following bounded contexts.

---

# 🎮 Gameplay

Located in:

```text
game/
```

Gameplay is responsible for:

* deterministic runtime execution
* explicit runtime continuation
* gameplay semantics
* round execution
* runtime lifecycle
* perceptual interaction
* canonical runtime transitions

Gameplay runtime currently follows:

```text
event
→ router
→ runtime operation
→ GameState update
→ next runtime event
```

instead of:

```text
reducer-driven pseudo-orchestration
```

Runtime handlers are semantic runtime operations,
not generic reducer transformations.

Handlers MAY:

* materialize runtime state
* resolve persistent gameplay identity
* construct runtime execution context
* replace runtime roots such as GameSession

Handlers MUST NOT:

* invent gameplay policy
* reconstruct hidden defaults
* duplicate persistent identity semantics
* own canonical gameplay configuration

Gameplay intentionally does NOT own:

* analytics interpretation
* renderer internals
* persistence orchestration
* adaptive layout realization
* telemetry coordination
* monetization orchestration
* hardcoded perceptual catalogs
* gameplay defaults
* progression policy
* renderer selection policy
* notebook selection policy

The surviving gameplay runtime architecture is currently:

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

Gameplay runtime is actively migrating away from:

* ResourceDescriptor hierarchy
* ColorDescriptor
* DrawableDescriptor
* ColorLibrary
* DrawableLibrary
* runtime-owned notebook resolution

toward:

```text
persistent canonical perceptual resources
```

---

# 🗄 Persistent Resource Infrastructure

Located in:

```text
game/data/local/db
game/domain/resource
```

Perceptual resources are now evolving into:

```text
persistent canonical domain entities
```

instead of:

```text
hardcoded runtime catalogs
```

Canonical ownership chain:

```text
SYSTEM Profile
→ canonical bootstrap defaults

HUMAN Profile
→ mutable gameplay identity

Notebook
→ owns resources

GameSession
→ runtime execution context

GameSessionEntity
→ immutable historical snapshot

Rounds
→ reference resource identity

Analytics
→ operates on (notebookId, resourceId)
```

Runtime no longer owns:

* default notebook
* default renderer
* default mask
* default level
* progression bounds

These semantics now belong to persistent gameplay identity ownership.

---

# 🎯 Canonical Perceptual Identity

Perceptual space is now formally defined by:

```text
(notebookId, resourceId)
```

Where:

* notebookId == NotebookEntity.name
* resourceId == ResourceEntity.name

This coordinate system is now the canonical semantic identity for:

* gameplay runtime
* telemetry
* Firebase export
* analytics reconstruction
* behavioral interpretation
* future adaptive systems

---

# 🔠 Canonical Identifier Convention

Canonical relational identifiers intentionally use:

```text
UPPERCASE semantic IDs
```

Examples:

```text
CAPYBARA
CHECKER
CIRCLE
AFTER
HYBRID
```

This convention applies to:

* notebooks
* renderers
* masks
* gameplay modes
* relational semantic identifiers

The architecture intentionally avoids:

* mixed-case semantic identifiers
* runtime normalization layers
* case-sensitive reconciliation logic
* implicit identifier transformation

Human-readable UI labels MUST remain separated from canonical semantic identifiers.

---

# 🔐 Persistent Gameplay Identity

Located in:

```text
profile/
```

Profile subsystem responsibilities now include:

* identity
* profile persistence
* parent/child ownership
* runtime active profile state
* PIN security
* recovery semantics
* notebook ownership
* gameplay identity ownership
* progression ownership
* renderer selection ownership
* mask selection ownership

Profiles are now the canonical persistent owners of:

```text
activeNotebookName
preferredMode
currentLevel
minLevel
maxLevel
rendererId
maskId
```

Gameplay runtime now materializes gameplay identity from Profile instead of reconstructing runtime defaults.

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
* mutability rules
* ownership rules
* bootstrap behavior
* security semantics

ProfileType intentionally does NOT act as:

* runtime dimension
* analytics dictionary
* user-editable registry
* capability system

---

# 🧩 Gameplay Identity Materialization

Gameplay runtime now treats Profile as:

```text
canonical mutable gameplay identity
```

Gameplay runtime treats GameSession as:

```text
runtime execution context
```

Gameplay runtime treats GameSessionEntity as:

```text
immutable historical persistence snapshot
```

Session creation flow conceptually follows:

```text
Profile
→ Runtime Materialization
→ GameSession
→ Runtime Execution
```

Persistence flow conceptually follows:

```text
GameSession
→ GameSessionEntity
→ Historical Reconstruction
```

GameSession creation is a runtime root replacement operation.

Creating a new GameSession intentionally replaces
the currently active gameplay runtime root.

This operation is not treated as incremental reducer mutation.

Runtime handlers intentionally no longer invent:

* gameplay defaults
* progression limits
* renderer defaults
* notebook defaults

Runtime handlers are expected to:

```text
materialize already existing persistent identity state
```

instead of synthesizing runtime configuration.

---

# 📊 Statistics

Located in:

```text
statistics/
```

Statistics acts as:

```text
deterministic runtime reconstruction layer
```

instead of:

```text
UI-driven analytics projection
```

Statistics is responsible for:

* observational analytics
* explainable insights
* trend analysis
* density reconstruction
* behavioral interpretation
* graph preparation

Statistics intentionally remains:

```text
strictly read-only observational infrastructure
```

Statistics MUST NEVER:

* influence gameplay runtime
* inject gameplay events
* mutate lifecycle
* coordinate continuation
* alter outcomes

Statistics reconstructs:

```text
what happened
```

NOT:

```text
what should happen next
```

Current statistics flow:

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

Statistics now observes:

```text
(notebookId, resourceId)
```

instead of transient renderer-oriented runtime descriptors.

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
* read-only
* canonical
* telemetry-safe
* analytics-safe

The runtime intentionally avoids:

* dynamic capability discovery
* runtime plugin registries
* mutable semantic catalogs
* orchestration-oriented renderer systems

---

# 🎨 Rendering

Located in:

```text
game/domain/renderer
```

Rendering is responsible for:

* visual realization
* perceptual projection
* resource interpretation
* renderer semantics

Gameplay runtime intentionally delegates:

```text
visual realization
```

to renderer infrastructure.

Gameplay runtime MUST NOT know:

* Compose internals
* drawable implementation
* renderer internals
* layout realization
* adaptive scaling

Current renderer infrastructure includes:

### Color Renderers

* CheckerRenderer
* CircumferenceRenderer
* SolidFillRenderer

### Image Renderers

* DirectImageRenderer
* DistortedImageRenderer

Renderer infrastructure is actively migrating away from:

* ColorDescriptor
* DrawableDescriptor
* ResourceDescriptor inheritance chains

toward:

```text
canonical runtime resource semantics
```

---

# 🧱 Persistence Philosophy

Persistence layers intentionally remain:

```text
boring bounded adapters
```

Repositories SHOULD:

* persist validated runtime data
* expose storage queries
* remain runtime-agnostic

Repositories MUST NOT:

* orchestrate runtime lifecycle
* mutate gameplay semantics
* synchronize implicitly
* coordinate telemetry

Gameplay persistence currently includes:

* GameSessionEntity
* ObservedRoundEntity
* VisualConfigEntity
* ResourceEntity
* NotebookEntity
* NotebookResourceLinkEntity
* RendererEntity
* MaskEntity
* ProfileEntity

Transitional persistence artifacts still surviving:

* LayoutConfigEntity
* LayoutPositionEntity

---

# 🚀 Bootstrap Philosophy

Bootstrap infrastructure must remain:

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

Bootstrap is now also responsible for:

```text
canonical SYSTEM profile materialization
```

The SYSTEM profile acts as:

```text
immutable bootstrap-owned gameplay identity
```

Human profiles materialize bootstrap defaults once during creation and then evolve independently.

Bootstrap MUST NOT evolve into:

* generalized orchestration runtime
* application-wide lifecycle engine
* dynamic capability system

---

# ⚠️ Removed / Deprecated Architecture

Sprint 16 stabilization proved the following architecture obsolete or non-participatory in surviving runtime:

* reducer-driven continuation
* synthesized gameplay UI state
* mapper-owned runtime semantics
* RoundMachineSpec
* RouteResult
* RuntimeStatus
* game/domain/process
* game/domain/engine
* semantic positioning runtime
* gameplay-owned layout runtime
* topology-owned coordinate systems
* RoundState lifecycle semantics
* PostRoundActionType semantics
* Stimulus abstraction
* RoundOption abstraction
* ResourceCategory

Actively deprecated during current resource migration:

* ResourceDescriptor hierarchy
* ColorDescriptor
* DrawableDescriptor
* ColorLibrary
* DrawableLibrary
* hardcoded notebook ownership
* runtime-owned catalog resolution
* StimulusResourceResolver

The project now intentionally optimizes for:

```text
minimum meaningful architecture surface
```

---

# ⚠️ Architectural Risks

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
* telemetry integration
* environment infrastructure
* continuation governance
* profile runtime
* adaptive runtime systems
* resource migration layers
* transitional compatibility bridges

---

# Lifecycle Coupling

When:

* UI
* telemetry
* analytics
* persistence

begin influencing gameplay runtime semantics.

---

# Mega-context Growth

Especially dangerous for:

* AppContainer
* environment systems
* runtime synchronization layers

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
* explainability
* maintainability
* localization readiness
* supportability
* canonical persistent semantics
* telemetry-safe perceptual identity
* relational gameplay identity

instead of:

* maximal abstraction
* framework experimentation
* generalized orchestration
* speculative infrastructure

