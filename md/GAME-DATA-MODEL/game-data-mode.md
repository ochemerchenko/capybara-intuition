# GAME_DATA_MODEL

## Version

0.16

## Last Updated

2026-05-27

---

# Overview

The game data model exists as:

```text
canonical deterministic gameplay identity infrastructure
```

NOT:

```text
generic mutable gameplay storage
```

The data model intentionally prioritizes:

* deterministic runtime reconstruction,
* canonical perceptual identity,
* bounded relational semantics,
* explicit ownership,
* append-only historical observation,
* topology-safe gameplay persistence,
* supportable analytics reconstruction,
* publication-safe runtime integrity.

The architecture intentionally avoids:

* runtime-generated semantic mutation,
* duplicated gameplay truth,
* hidden persistence orchestration,
* renderer-owned identity semantics,
* analytics-owned progression semantics,
* mutable historical reconstruction,
* generalized entity inheritance.

---

# Core Strategic Principle

The data model intentionally treats:

```text
gameplay persistence
```

as:

```text
canonical runtime truth reconstruction
```

NOT:

```text
UI snapshot persistence
```

Persistence must support deterministic reconstruction of:

* gameplay runtime,
* profile ownership,
* perceptual interaction,
* topology occupation,
* historical analytics,
* telemetry export,
* runtime transitions.

The model intentionally optimizes for:

```text
deterministic behavioral reconstruction
```

instead of:

```text
maximum persistence flexibility
```

---

# Canonical Runtime Layers

The architecture intentionally separates:

---

# Persistent Identity Layer

Represents:

```text
stable gameplay ownership semantics
```

Owned by:

* Profile,
* Notebook,
* Resource,
* Renderer,
* Mask,
* gameplay mode,
* topology identity.

Persistent identity intentionally evolves slowly.

---

# Runtime Execution Layer

Represents:

```text
temporary gameplay realization
```

Owned by:

* GameSession,
* GameRound,
* runtime continuation,
* execution context.

Runtime execution intentionally remains transient.

---

# Historical Observation Layer

Represents:

```text
append-only deterministic runtime history
```

Owned by:

* GameSessionEntity,
* ObservedRoundEntity,
* analytics infrastructure,
* telemetry export.

Historical observation intentionally remains:

* immutable,
* reconstructable,
* analytics-safe,
* supportable.

The architecture intentionally avoids mixing these layers.

---

# Canonical Perceptual Identity

The canonical perceptual coordinate is:

```text
(notebookId, resourceId)
```

This coordinate pair represents:

* gameplay perceptual identity,
* analytics reconstruction identity,
* telemetry-safe identity,
* renderer-independent perceptual semantics.

The architecture intentionally migrated away from:

* transient descriptor identity,
* renderer-owned perceptual semantics,
* hardcoded gameplay catalogs,
* synthetic perceptual reconstruction.

Canonical identifiers intentionally use:

```text
UPPERCASE semantic IDs
```

Examples:

```text
AFTER
BEFORE
HYBRID
CAPYBARA
CHECKER
```

Localized labels remain separate.

---

# Gameplay Runtime Model

Current runtime flow:

```text
Profile
→ Runtime Materialization
→ GameSession
→ GameRound
→ Runtime Resource
→ RendererResolver
→ Renderer
→ Compose UI
```

The runtime intentionally avoids:

* session-owned topology generation,
* renderer-owned gameplay semantics,
* runtime-generated identity mutation,
* hidden gameplay reconstruction.

---

# Profile Data Semantics

Profile acts as:

```text
persistent gameplay identity root
```

Profile owns:

* gameplay mode identity,
* progression state,
* renderer identity,
* notebook identity,
* runtime ownership semantics,
* topology identity,
* profile type semantics,
* parent/child security semantics.

Profiles intentionally represent:

```text
canonical ownership holders
```

NOT:

```text
temporary UI selections
```

The active profile represents:

```text
current runtime ownership holder
```

---

# GameSession Semantics

GameSession represents:

```text
deterministic runtime materialization snapshot
```

GameSession intentionally owns:

* runtime execution lifecycle,
* gameplay continuation context,
* session-level runtime identity,
* runtime timestamps.

GameSession intentionally does NOT own:

* topology catalogs,
* renderer generation,
* gameplay identity derivation,
* perceptual reconstruction policy.

Sessions intentionally remain:

* lightweight,
* reconstructable,
* runtime-oriented,
* deterministic.

---

# GameRound Semantics

GameRound represents:

```text
single bounded gameplay execution unit
```

GameRound operates only on:

```text
already materialized runtime semantics
```

GameRound intentionally avoids:

* topology selection,
* renderer orchestration,
* notebook mutation,
* runtime identity ownership.

---

# GameRoundRecord

GameRoundRecord represents:

```text
canonical bounded gameplay observation
```

The structure intentionally remains stable.

Canonical attributes:

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

The structure intentionally optimizes for:

* deterministic analytics,
* telemetry-safe export,
* supportability,
* bounded runtime semantics,
* historical explainability.

The model intentionally avoids:

* hidden behavioral synthesis,
* mutable runtime reinterpretation,
* overloaded semantic ownership.

Structural modifications require:

```text
strong architectural justification
```

because GameRoundRecord acts as:

```text
canonical observational runtime primitive
```

---

# Statistics Relationship

Statistics intentionally acts as:

```text
read-only deterministic reconstruction infrastructure
```

Statistics reconstructs:

```text
what happened
```

NOT:

```text
what should happen next
```

Canonical statistics flow:

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

Statistics intentionally depends on:

* append-only runtime history,
* canonical perceptual identity,
* deterministic runtime ordering,
* stable session semantics.

Statistics intentionally does NOT own:

* gameplay progression,
* runtime continuation,
* gameplay correctness,
* profile identity.

---

# Telemetry Relationship

Telemetry intentionally remains:

```text
passive observational infrastructure
```

Telemetry observes:

```text
canonical deterministic runtime facts
```

Current telemetry direction:

```text
Runtime Records
→ Telemetry Repository
→ Export Layer
→ Firebase
```

Telemetry intentionally avoids:

* gameplay mutation,
* runtime orchestration,
* behavioral manipulation,
* hidden personalization.

---

# Runtime Ownership Boundaries

The architecture intentionally enforces:

```text
single canonical ownership
```

for every mutable semantic concern.

---

# Profile owns

* gameplay identity,
* progression,
* renderer identity,
* notebook identity,
* gameplay mode identity,
* runtime ownership semantics.

---

# Session owns

* runtime execution,
* continuation lifecycle,
* transient execution context.

---

# Round owns

* bounded gameplay interaction.

---

# Statistics owns

* observational reconstruction,
* analytics interpretation,
* explainable behavioral insight.

---

# Telemetry owns

* observational export semantics.

---

# Renderer owns

* visual realization only.

The architecture intentionally avoids:

* distributed ownership,
* duplicated runtime truth,
* renderer-owned semantics,
* analytics-owned gameplay mutation.

---

# Topology Relationship

Before first publication:

```text
ONLY MATRIX TOPOLOGIES EXIST
```

Topology acts as:

```text
canonical gameplay occupancy space
```

NOT:

```text
adaptive UI layout infrastructure
```

Topology semantics intentionally remain:

* immutable,
* deterministic,
* renderer-independent,
* analytics-safe.

Topology defines:

* occupancy structure,
* logical coordinates,
* blocked regions,
* canonical ordering.

The game data model intentionally avoids:

* renderer-owned topology mutation,
* adaptive geometry synthesis,
* runtime-generated topology identity.

---

# Renderer Relationship

Rendering intentionally acts as:

```text
visual realization infrastructure
```

NOT:

```text
gameplay semantics owner
```

Renderer receives:

```text
already materialized runtime semantics
```

Renderer intentionally does NOT:

* invent gameplay identity,
* mutate topology,
* reconstruct perceptual semantics,
* own runtime continuation.

---

# Persistence Philosophy

Persistence intentionally acts as:

```text
deterministic runtime reconstruction infrastructure
```

The architecture intentionally prioritizes:

* relational integrity,
* deterministic materialization,
* explicit ownership,
* append-only observation,
* topology-safe reconstruction.

Persistence intentionally avoids:

* hidden orchestration,
* runtime patching,
* implicit synchronization,
* mutable historical reinterpretation.

---

# Aggregate Materialization

Complex runtime aggregates are intentionally materialized through:

```text
explicit relational composition
```

instead of:

```text
manual orchestration pipelines
```

Current direction intentionally favors:

* Room relations,
* deterministic DAO queries,
* explicit aggregate ownership,
* SQL-level invariant preservation.

---

# SQL-Level Invariant Preservation

Persistence MAY enforce:

```text
deterministic relational invariants
```

through:

* atomic SQL mutation,
* deterministic ownership transfer,
* relational synchronization.

Persistence logic must remain:

* deterministic,
* explicit,
* side-effect bounded,
* supportable.

---

# Reactive Runtime Projection

Reactive flows intentionally act as:

```text
canonical synchronization infrastructure
```

Current direction:

```text
DB
→ DAO
→ Repository
→ Flow
→ ViewModel
→ UI
```

The architecture intentionally avoids:

* manual synchronization chains,
* imperative runtime propagation,
* duplicated runtime caches,
* UI-owned truth.

---

# Repository Philosophy

Repositories intentionally act as:

```text
reactive persistence adapters
```

NOT:

```text
hidden orchestration layers
```

Repositories own:

* persistence access,
* aggregate materialization,
* reactive projection,
* deterministic mutation coordination.

Repositories intentionally avoid:

* gameplay orchestration,
* renderer ownership,
* UI lifecycle ownership,
* runtime policy synthesis.

---

# DAO Philosophy

DAOs intentionally own:

* persistence primitives,
* deterministic relational queries,
* atomic mutations,
* invariant-preserving operations.

DAOs intentionally avoid:

* orchestration semantics,
* runtime ownership,
* business-policy synthesis.

---

# Bootstrap Philosophy

Bootstrap infrastructure intentionally materializes:

```text
canonical deterministic runtime identity
```

Bootstrap responsibilities:

* notebook seeding,
* resource seeding,
* renderer seeding,
* profile seeding,
* topology-safe relational initialization.

Bootstrap intentionally avoids:

* runtime orchestration,
* hidden gameplay synthesis,
* environment mutation heuristics.

---

# Historical Observation Philosophy

Historical gameplay records intentionally remain:

```text
append-only observational truth
```

Historical data MUST NOT become:

* gameplay authority,
* mutable progression state,
* runtime continuation source.

Historical reconstruction intentionally prioritizes:

* explainability,
* trustworthiness,
* deterministic replay,
* telemetry-safe export.

---

# Supportability Philosophy

The game data model intentionally prioritizes:

```text
supportability-first deterministic persistence
```

Current priorities:

* bounded runtime complexity,
* explicit ownership,
* deterministic reconstruction,
* relational integrity,
* topology clarity,
* lifecycle traceability.

The architecture intentionally avoids:

* hidden orchestration,
* distributed runtime semantics,
* implicit synchronization,
* speculative abstraction expansion.

---

# Architectural Risk Areas

The project continuously monitors risks of:

---

# Ownership Erosion

When:

```text
multiple systems partially own the same semantics
```

---

# Semantic Duplication

Especially dangerous when:

* runtime truth fragments,
* analytics reinterpret gameplay semantics,
* renderers reconstruct identity.

---

# Lifecycle Coupling

When:

* UI,
* telemetry,
* analytics,
* rendering

begin mutating gameplay persistence semantics.

---

# Historical Mutation

Especially dangerous when:

* append-only semantics become mutable,
* runtime reconstruction becomes synthesized,
* analytics alter canonical history.

---

# Publication-Oriented Strategy

The first public release intentionally optimizes for:

```text
stable deployable deterministic gameplay persistence
```

NOT:

```text
maximum persistence abstraction completeness
```

The current strategic direction prioritizes:

* deterministic runtime semantics,
* canonical perceptual identity,
* bounded topology semantics,
* reactive runtime projection,
* explainable analytics,
* supportable persistence,
* telemetry-safe reconstruction,
* publication-safe runtime integrity.

