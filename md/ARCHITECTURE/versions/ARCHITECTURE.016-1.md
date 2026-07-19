# ARCHITECTURE

## Version: 0.16

## Last Updated: 2026-05-20

# ARCHITECTURE

## Vision

Intuition is a deterministic perceptual training system built around stable gameplay geometry, profile-driven runtime state, and explicit ownership semantics.

The project intentionally avoids runtime chaos:

* no implicit gameplay generation,
* no hidden orchestration,
* no scattered runtime ownership,
* no renderer-driven geometry invention,
* no duplicated state synchronization.

The architecture prioritizes:

* deterministic behavior,
* stable geometry,
* explicit aggregate ownership,
* persistence consistency,
* reactive synchronization,
* append-only visual evolution,
* runtime transparency.

---

# Core Architectural Principles

## Deterministic Runtime

Runtime behavior must remain deterministic.

The same:

* profile,
* level,
* topology family,
* topology,
* renderer,
* mode,

must always produce the same gameplay geometry and gameplay behavior.

The runtime intentionally avoids:

* random layout generation,
* unstable geometry,
* hidden runtime derivation,
* non-repeatable gameplay structures.

---

## Explicit Ownership

Every mutable runtime concern must have a single canonical owner.

Ownership must never become implicitly shared between:

* ViewModels,
* repositories,
* sessions,
* renderers,
* UI state,
* persistence.

Ambiguous ownership is treated as architectural risk.

---

## Stable Geometry

Gameplay geometry is canonical domain data.

Geometry is not:

* renderer-owned,
* UI-generated,
* session-generated,
* runtime-randomized.

Geometry exists independently from rendering implementation.

---

## Aggregate Consistency

Aggregates must remain internally valid.

Cross-field invariants must be preserved atomically.

The architecture intentionally avoids:

* partially synchronized aggregates,
* runtime patching,
* UI-driven consistency repair,
* manual synchronization discipline.

---

## Reactive Synchronization

Reactive flows are the canonical runtime synchronization mechanism.

Room Flow streams propagate runtime state changes through repositories into ViewModels and UI.

Reactive synchronization is preferred over:

* manual refresh,
* imperative synchronization,
* mutable shared runtime caches.

---

# Runtime Architecture

## Profile

Profile is the primary gameplay identity aggregate.

A profile owns:

* gameplay progression,
* current level,
* gameplay mode,
* renderer selection,
* mask selection,
* topology family,
* active topology,
* notebook selection,
* activity state,
* profile type semantics.

Gameplay sessions are always created from the currently active profile snapshot.

---

## Session

Session is a temporary gameplay runtime aggregate.

A session:

* does not own topology catalogs,
* does not derive gameplay geometry,
* does not resolve topology families,
* does not generate layouts,
* does not invent button placement.

Instead, session receives a resolved gameplay snapshot from the active profile.

Session is intentionally lightweight.

---

## Round

Round is a transient gameplay execution unit.

Round operates on already resolved runtime state.

Round never:

* resolves topology,
* loads topology catalogs,
* derives geometry,
* chooses renderer layout semantics.

---

# Gameplay Geometry Architecture

## Topology

Topology is the canonical gameplay geometry source.

Topology defines:

* occupancy layout,
* cell coordinates,
* spatial structure,
* projection behavior,
* renderer geometry,
* occupancy capacity.

Topology exists independently from:

* profiles,
* sessions,
* rounds,
* renderers,
* gameplay modes.

Topology is a reusable geometry definition.

---

## Topology Cells

TopologyCell defines a concrete spatial occupancy slot.

Cells contain:

* coordinates,
* dimensions,
* blocking state,
* index order.

Cells are immutable geometry descriptors.

---

## Topology Projection

TopologyProjection defines topology rendering semantics.

Projection controls:

* alignment,
* scaling behavior,
* renderer projection semantics.

Projection does not generate geometry.

Projection interprets existing geometry.

---

## Topology Family

TopologyFamily defines a constrained topology evolution space.

Families group topology variants that belong to the same gameplay geometry progression line.

Topology families preserve stable visual semantics across level transitions.

---

# Topology Selection Model

Profiles persist:

* topologyFamilyId,
* activeTopologyCode,
* currentLevel.

The active topology is not arbitrary.

Active topology is deterministically synchronized with:

* topology family,
* current level.

Topology selection is deterministic.

---

# Topology Invariant

The following invariant must always hold:

```text id="arch_a1"
activeTopology.occupancyCapacity == currentLevel
```

Any level transition must automatically update active topology selection.

---

# Level Transition Architecture

Level transitions are aggregate mutations.

Changing profile level must atomically:

1. update currentLevel,
2. resolve matching topology inside topology family,
3. update activeTopologyCode.

Topology synchronization is persistence-driven.

The architecture intentionally avoids:

* runtime topology patching,
* ViewModel synchronization logic,
* UI-driven topology repair.

---

# Geometry Semantics

Topology codes are semantic geometry identifiers.

Examples:

* `2_0`
* `2_1`
* `2_2_1`

Topology codes are not synthetic numeric identifiers.

Codes represent stable geometry signatures.

Example:

```text id="arch_a2"
2_2_1
=
2 buttons in row 1
2 buttons in row 2
1 button in row 3
```

---

# Renderer Architecture

Renderer consumes topology geometry directly.

Renderer does not:

* derive geometry,
* invent layouts,
* generate coordinates,
* reinterpret occupancy structure.

Topology is the single source of truth for:

* button coordinates,
* layout structure,
* occupancy semantics,
* projection behavior,
* gameplay geometry.

Renderer is intentionally geometry-dependent rather than geometry-owning.

---

# Gameplay Runtime Flow

The canonical runtime geometry flow is:

```text id="arch_a3"
Profile
→ activeTopology
→ Session
→ Round
→ Renderer
```

Geometry flows downward through runtime snapshots.

Renderer never reconstructs geometry independently.

---

# Persistence Architecture

## Profile Persistence

Profiles persist stable gameplay identity state.

Profiles persist:

* currentLevel,
* topologyFamilyId,
* activeTopologyCode,
* rendererId,
* maskId,
* gameplay mode,
* notebook identity,
* profile activity state.

---

## Aggregate Materialization

Complex runtime aggregates are materialized through Room relations.

Runtime aggregates should be reconstructed through explicit relational composition rather than manual orchestration.

The architecture intentionally avoids:

* manual aggregate stitching,
* repository-side entity reconstruction,
* scattered relational loading.

---

## SQL-Level Invariant Enforcement

Persistence-level invariants may be enforced directly through deterministic SQL mutations.

The architecture intentionally allows:

* atomic relational transitions,
* SQL-driven invariant preservation,
* deterministic relational synchronization.

Persistence logic must remain:

* deterministic,
* explicit,
* side-effect free.

---

# Reactive Runtime Model

Room Flow streams are the authoritative runtime synchronization mechanism.

Repositories expose canonical runtime flows.

ViewModels may cache repository state for UI synchronization purposes, but cached UI state is not authoritative.

The architecture intentionally avoids:

* duplicated runtime truth sources,
* manual synchronization chains,
* mutable shared caches.

---

# Repository Responsibilities

Repositories coordinate:

* runtime synchronization,
* aggregate materialization,
* persistence access,
* deterministic runtime mutations.

Repositories must not become:

* hidden UI state containers,
* visibility-policy engines,
* renderer orchestration layers.

---

# DAO Responsibilities

DAOs own:

* persistence primitives,
* deterministic relational queries,
* atomic SQL mutations,
* invariant-preserving persistence transitions.

DAOs must not become hidden service layers.

---

# Rendering Boundaries

Rendering logic must remain separated from:

* persistence semantics,
* profile ownership,
* gameplay progression,
* topology resolution.

Renderer receives already resolved gameplay geometry.

Renderer is intentionally runtime-passive.

---

# Statistics Architecture

Statistics are historical runtime records.

Statistics are append-only observational data.

Statistics must never become:

* runtime authority,
* gameplay source of truth,
* mutable progression state.

Statistics reconstruction must remain deterministic.

---

# Telemetry Philosophy

Telemetry exists to observe runtime behavior rather than control runtime behavior.

Telemetry must never become:

* gameplay authority,
* synchronization mechanism,
* hidden orchestration system.

Observability and runtime control are intentionally separated.

---

# Bootstrap Philosophy

SYSTEM profiles exist as technical bootstrap templates.

SYSTEM profiles:

* are not runtime-visible gameplay entities,
* are not selectable profiles,
* are not capability-controlled runtime actors.

SYSTEM profile exclusion is a persistence rule rather than a capability rule.

---

# Capability Boundaries

Capabilities define runtime feature availability.

Capabilities do not define:

* persistence filtering semantics,
* SYSTEM profile visibility,
* aggregate invariants,
* topology ownership semantics.

Capability logic must not leak into persistence architecture.

---

# Runtime Visibility Semantics

Runtime profile visibility rules and persistence semantics are separate concerns.

SYSTEM profile exclusion is deterministic persistence behavior.

Additional runtime visibility policies may exist independently from capability logic.

---

# Visual Configuration Philosophy

VisualConfigEntity instances are immutable append-only semantic snapshots.

Visual configurations:

* are never modified,
* are never rewritten,
* are never retroactively mutated.

Visual evolution is append-only.

---

# Architectural Risk Areas

The architecture explicitly treats the following as risk zones:

* implicit ownership,
* hidden orchestration,
* duplicated synchronization,
* renderer-owned geometry,
* ViewModel business orchestration,
* aggregate patching,
* runtime-generated layout semantics,
* capability leakage into persistence,
* topology/runtime desynchronization.

Any increase in implicit relationships must be treated as architectural risk.

---

# Current Architectural Direction

The current architectural direction prioritizes:

* topology-driven rendering,
* deterministic runtime geometry,
* SQL-level invariant preservation,
* profile-owned gameplay state,
* reactive runtime synchronization,
* aggregate consistency,
* lightweight sessions,
* removal of legacy layout derivation,
* removal of topology catalogs from sessions,
* removal of synthetic topology identifiers,
* elimination of duplicated runtime orchestration.

---

# Deprecated Runtime Concepts

The following concepts are deprecated and being removed from runtime architecture:

* legacy layout derivation pipelines,
* renderer-owned geometry logic,
* topology catalogs inside sessions,
* synthetic numeric topology ids,
* implicit runtime geometry generation,
* scattered topology selection logic,
* repository-level visibility filtering semantics,
* manual aggregate reconstruction pipelines.

