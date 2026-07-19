# game-data-model

## Version: 0.16

## Last Updated: 2026-05-20

---

# Overview

The gameplay persistence layer is evolving from:

```text
runtime-oriented transient structures
```

toward:

```text
canonical relational behavioral infrastructure
```

The current architecture intentionally prioritizes:

* deterministic reconstruction
* relational integrity
* explicit ownership
* explainable analytics
* telemetry-safe semantics
* Firebase-safe exportability
* persistent gameplay identity
* canonical perceptual identity

The persistence layer now acts as:

```text
canonical behavioral source of truth
```

instead of:

```text
runtime support storage
```

---

# 🧠 Core Persistence Philosophy

The database intentionally stores:

* canonical identities
* deterministic runtime facts
* persistent gameplay semantics
* observational interaction records
* stable relational dimensions

The architecture intentionally avoids:

* runtime reconstruction heuristics
* hidden defaults
* synthesized gameplay semantics
* runtime-owned catalogs
* distributed progression policy
* mutable semantic dictionaries
* reducer-oriented persistence semantics

Persistence now intentionally supports:

```text
runtime materialization from canonical identity
```

instead of:

```text
runtime-owned configuration synthesis
```

---

# Canonical Behavioral Coordinate System

The gameplay system now formally defines perceptual identity as:

```text
(notebookId, resourceId)
```

Where:

* notebookId == NotebookEntity.name
* resourceId == ResourceEntity.name

This coordinate pair is now the canonical identity used by:

* gameplay runtime
* telemetry
* Firebase export
* analytics reconstruction
* behavioral interpretation
* future longitudinal modeling

This intentionally replaces:

* transient renderer descriptors
* hardcoded runtime catalogs
* ColorDescriptor semantics
* DrawableDescriptor semantics

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

Human-readable UI labels MUST remain separated from canonical semantic identifiers.

---

# 🧩 Ownership Model

The persistence layer now follows this ownership chain:

```text
SYSTEM Profile
→ canonical defaults

HUMAN Profile
→ mutable gameplay identity

Notebook
→ owns resources

GameSession
→ runtime execution context

GameSessionEntity
→ immutable historical snapshot

Rounds
→ behavioral interaction fact

Statistics
→ observational reconstruction
```

Gameplay runtime no longer owns:

* notebook defaults
* progression defaults
* renderer defaults
* mask defaults

These semantics now belong to persistent gameplay identity ownership.

---

# 🗄 Persistent Gameplay Identity

Located in:

```text
profile/data/local/db/entity
```

Profiles are now treated as:

```text
persistent gameplay identity roots
```

instead of:

```text
runtime metadata holders
```

Profiles own:

* notebook selection
* gameplay mode
* progression state
* progression bounds
* renderer selection
* mask selection

Gameplay runtime now materializes identity state from Profile instead of reconstructing hidden defaults.

---

# 👤 ProfileEntity

Represents canonical persistent gameplay identity.

---

## Responsibilities

* identity ownership
* gameplay configuration ownership
* progression ownership
* renderer selection ownership
* notebook ownership
* parent/child ownership
* security ownership

---

## Structure

```kotlin
ProfileEntity(

    id: String,

    name: String,

    type: ProfileType,

    installationId: String,

    isSystemDefault: Boolean,

    pinHash: String?,

    activeNotebookName: String,

    preferredMode: GameMode,

    minLevel: Int,

    maxLevel: Int,

    currentLevel: Int,

    rendererId: String,

    maskId: String,

    isDeleted: Boolean,

    active: Boolean,

    createdAt: Long,

    lastUsedAt: Long
)
```

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

Current invariant roles:

| Type   | Meaning                                  |
| ------ | ---------------------------------------- |
| SYSTEM | immutable bootstrap-owned defaults       |
| HUMAN  | mutable primary gameplay identity        |
| CHILD  | restricted subordinate gameplay identity |

ProfileType defines:

* lifecycle semantics
* mutability semantics
* security semantics
* bootstrap semantics
* ownership semantics

ProfileType intentionally does NOT act as:

* analytics dimension
* runtime dictionary
* editable registry
* dynamic capability system

---

# 🧩 SYSTEM Profile Semantics

The SYSTEM profile acts as:

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

* runtime-owned defaults
* distributed gameplay policy
* bootstrap duplication
* hidden configuration reconstruction

---

# 🎯 Progression Semantics

Progression ownership now belongs to Profile.

---

# Immutable Progression Envelope

```kotlin
minLevel
maxLevel
```

Represents:

* canonical progression constraints
* bootstrap-owned limits
* deterministic gameplay envelope

These values are intentionally NOT user-editable.

---

# Mutable Progression State

```kotlin
currentLevel
```

Represents:

* current gameplay progression position
* mutable identity state
* user-controlled progression selection

Invariant:

```text
minLevel <= currentLevel <= maxLevel
```

Validation belongs to:

```text
profile identity management
```

NOT gameplay runtime handlers.

---

# 🗄 Relational Gameplay Dimensions

The following concepts are now modeled as:

```text
stable relational dimensions
```

instead of:

```text
runtime-owned semantic catalogs
```

---

# NotebookEntity

Represents canonical perceptual notebook identity.

---

# ResourceEntity

Represents canonical perceptual resource identity.

---

# RendererEntity

Represents canonical renderer dictionary value.

Structure:

```kotlin
RendererEntity(
    id: String,
    name: String
)
```

Characteristics:

* bootstrap-seeded
* read-only
* canonical
* telemetry-safe

---

# MaskEntity

Represents canonical mask dictionary value.

Structure:

```kotlin
MaskEntity(
    id: String,
    name: String
)
```

Characteristics:

* bootstrap-seeded
* read-only
* canonical
* telemetry-safe

---

# Important Principle

Relational dimensions intentionally MUST NOT own:

* mutable runtime state
* active selections
* user state
* progression state

Mutable gameplay semantics belong exclusively to Profile and Session.

---

# 🗄 Notebook Infrastructure

Notebook ownership now intentionally lives in relational persistence instead of runtime catalogs.

This replaces:

* ColorLibrary
* DrawableLibrary
* hardcoded notebook ownership
* runtime catalog resolution

---

# NotebookEntity

Represents canonical perceptual notebook identity.

---

## Structure

```kotlin
NotebookEntity(
    id: String,
    name: String,
    createdAt: Long
)
```

---

# ResourceEntity

Represents canonical perceptual resource identity.

---

## Structure

```kotlin
ResourceEntity(
    id: String,
    name: String,
    type: ResourceType,
    value: String,
    createdByProfileId: String?,
    createdAt: Long
)
```

---

# NotebookResourceLinkEntity

Represents canonical notebook-resource ownership relation.

---

## Structure

```kotlin
NotebookResourceLinkEntity(
    notebookId: String,
    resourceId: String,
    createdAt: Long
)
```

---

# 🎮 Session Model

Game sessions now act as:

```text
runtime materialization snapshots
```

instead of:

```text
runtime-owned gameplay configuration
```

GameState is now treated as:

```text
runtime execution projection
```

instead of:

```text
canonical ownership source
```

Canonical ownership belongs to:

* Profile
* Session persistence
* relational dimensions

---

# Session Materialization Flow

Session creation conceptually follows:

```text
Profile
→ Runtime Materialization
→ Session
→ Runtime Execution
```

Session creation is a:

```text
runtime root replacement operation
```

Creating a new Session intentionally replaces
the currently active gameplay runtime root.

This operation is intentionally NOT treated as:

```text
incremental reducer mutation
```

---

# GameSessionEntity

Represents canonical deterministic gameplay session.

---

## Responsibilities

* runtime snapshot persistence
* gameplay reconstruction
* telemetry export
* analytics reconstruction

---

## Structure

```kotlin
GameSessionEntity(

    id: String,

    profileId: String,

    notebookId: String,

    mode: GameMode,

    level: Int,

    rendererId: String,

    maskId: String,

    startedAt: Long,

    finishedAt: Long?
)
```

---

# Important Principle

Sessions intentionally snapshot gameplay identity state.

This preserves:

* deterministic reconstruction
* explainability
* historical correctness
* telemetry stability

Even if Profile evolves later.

---

# 🎯 Round Model

Rounds represent:

```text
deterministic behavioral interaction facts
```

instead of:

```text
UI interaction snapshots
```

---

# GameRoundRecord

Represents a single deterministic perceptual interaction.

---

## Responsibilities

* perceptual observation persistence
* runtime reconstruction
* behavioral analytics
* telemetry export

---

## Structure

```kotlin
GameRoundRecord(

    sessionId: String,

    notebookId: String,

    shownResourceId: String,

    selectedResourceId: String,

    result: RoundResult,

    startedAt: Long,

    firstUserActionAt: Long?,

    userSelectedAt: Long?,

    resultShownAt: Long?
)
```

---

# Important Principle

Rounds intentionally store:

```text
canonical perceptual coordinates
```

instead of renderer/runtime abstractions.

---

# 🎮 Runtime Handler Semantics

Handlers now act as:

```text
semantic runtime operations
```

instead of:

```text
generic reducer transformations
```

Handlers MAY:

* materialize runtime state
* resolve persistent gameplay identity
* construct runtime execution space
* replace runtime roots such as Session

Handlers intentionally operate on already existing canonical semantics.

Handlers intentionally do NOT own:

* continuation recursion
* runtime scheduling
* async orchestration
* lifecycle governance
* gameplay policy invention
* hidden default reconstruction
* renderer internals

---

# 📊 Statistics Relationship

Statistics now reconstructs behavior from:

```text
sessions
+
rounds
+
canonical perceptual identity
```

instead of synthesized runtime semantics.

Statistics intentionally observes:

```text
what happened
```

NOT:

```text
what should happen
```

---

# ☁️ Telemetry Direction

Current telemetry direction:

```text
Runtime Records
→ Telemetry Repository
→ Export Layer
→ Firebase
```

Telemetry now relies on:

```text
canonical relational behavioral semantics
```

instead of transient runtime descriptors.

The architecture intentionally prepares for:

* cross-user analytics
* longitudinal analysis
* behavioral clustering
* deterministic replay
* explainable AI-assisted interpretation

---

# 🚀 Bootstrap Infrastructure

Bootstrap now owns creation of:

* notebooks
* resources
* notebook-resource links
* renderers
* masks
* SYSTEM profile

Bootstrap intentionally remains:

* deterministic
* explicit
* bounded
* reproducible

Bootstrap MUST NOT evolve into:

* generalized orchestration runtime
* dynamic capability platform
* mutable configuration registry

---

# ⚠️ Transitional Migration State

The project is currently migrating away from:

* ResourceDescriptor
* ColorDescriptor
* DrawableDescriptor
* ColorLibrary
* DrawableLibrary
* StimulusResourceResolver
* runtime-owned notebook resolution
* runtime-owned gameplay defaults

The current transitional goal is:

```text
minimum meaningful semantic runtime surface
```

---

# ⚠️ Architectural Risks

The project continuously monitors risks of:

---

# Ownership Erosion

When multiple layers begin owning:

* progression semantics
* renderer selection
* notebook defaults
* gameplay identity

---

# Semantic Duplication

Especially dangerous when:

* runtime recreates persisted semantics
* bootstrap defaults reappear in handlers
* dictionaries own mutable state
* sessions become configuration owners

---

# Relationship Explosion

Especially dangerous in:

* compatibility bridges
* migration layers
* descriptor adapters
* runtime fallback systems
* capability-oriented abstractions

---

# Lifecycle Coupling

When:

* telemetry
* statistics
* persistence
* UI

begin influencing gameplay runtime semantics.

---

# 🎯 Long-term Direction

The persistence layer is intentionally evolving toward:

```text
globally analyzable behavioral infrastructure
```

with:

* deterministic reconstruction
* stable relational semantics
* explainable analytics
* Firebase-safe export
* telemetry-safe identity
* bounded ownership
* canonical perceptual coordinates

instead of:

```text
runtime-centric transient storage
```


