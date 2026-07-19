# PROFILE_RUNTIME

## Version

0.16

## Last Updated

2026-05-25

---

# Overview

The profile runtime subsystem is currently evolving from:

```text
runtime identity synchronization infrastructure
```

toward:

```text
reactive runtime ownership topology
```

The project intentionally removed:

* secondary runtime identity caches,
* activeProfileId synchronization,
* runtime-owned profile truth,
* imperative runtime identity propagation.

The current architecture intentionally prioritizes:

* single runtime identity truth,
* reactive topology observation,
* deterministic ownership transfer,
* topology-driven runtime projection,
* bounded identity semantics,
* supportable profile transitions,
* publication-safe authorization semantics.

---

# Core Runtime Principle

The project now treats:

```text
profiles topology
```

as:

```text
single runtime identity truth source
```

instead of:

* activeProfileId cache,
* ProfileRuntime ownership,
* ProfileManager synchronization,
* duplicated runtime identity state.

Current runtime flow:

```text
DB
→ DAO
→ Repository
→ Flow<List<Profile>>
→ ViewModel
→ UI
```

This topology now acts as the canonical runtime identity projection layer.

---

# Runtime Identity Philosophy

The project intentionally avoids:

```text
distributed runtime identity ownership
```

The architecture intentionally avoids:

* runtime synchronization rituals,
* duplicated active profile state,
* identity cache invalidation,
* manual identity reconstruction,
* imperative active profile propagation,
* runtime-owned identity truth.

The project intentionally optimizes for:

```text
reactive runtime ownership observation
```

---

# Active Profile Semantics

Current runtime semantics:

```text
active profile
=
current runtime ownership holder
```

NOT:

```text
currently selected UI entry
```

The active profile now owns:

* gameplay identity,
* progression identity,
* renderer identity,
* notebook identity,
* gameplay mode identity,
* runtime statistics ownership,
* gameplay runtime materialization.

---

# Runtime Ownership Transfer

The project now formally defines:

```text
switch profile
=
transfer runtime ownership
```

Current transition semantics:

* old active profile loses ownership,
* new profile receives ownership,
* topology updates reactively,
* downstream runtime reacts automatically.

---

# Canonical Transition Model

Current canonical transition operation:

```kotlin
switchActiveProfile(
    oldProfile,
    newProfile
)
```

This operation intentionally represents:

```text
pairwise ownership transition
```

instead of:

* arbitrary activation,
* global topology reset,
* cache synchronization.

---

# Pairwise Transition Semantics

Current runtime assumptions:

* exactly one profile is active,
* ownership transfer always occurs pairwise,
* runtime transitions are explicit,
* topology consistency is deterministic.

The project intentionally avoids:

* ambiguous activation semantics,
* implicit ownership reassignment,
* multi-owner runtime states,
* runtime synchronization heuristics.

---

# Reactive Runtime Observation

Runtime observation is now intentionally reactive.

The project is actively migrating away from:

```text
activeProfileId propagation
```

toward:

```text
topology observation
```

Current direction:

```text
Flow<List<Profile>>
→ active profile projection
→ runtime reaction
```

instead of:

```text
activeProfileId
→ manual synchronization
→ reload rituals
```

---

# Statistics Runtime Relationship

Statistics runtime now observes:

* active profile topology,
* ownership transitions,
* reactive profile projection.

Current flow:

```text
profiles topology
→ active profile extraction
→ statistics recomputation
```

instead of:

```text
activeProfileId synchronization
→ manual statistics reload
```

Statistics intentionally reacts to:

* topology change,
* ownership transfer,
* runtime identity projection.

---

# Profile Transition Rules

---

# HUMAN → CHILD

Allowed directly.

Semantics:

```text
runtime ownership delegated to child profile
```

---

# CHILD → HUMAN

Protected transition.

Requires:

* parent PIN authorization,
* runtime ownership verification.

Current semantics:

```text
child requests ownership return
```

instead of:

```text
child activates arbitrary runtime
```

---

# Parent Recovery Runtime

Parent recovery flow is now fully operational.

Recovery flow topology:

```text
CHILD
→ failed PIN attempts
→ recovery challenge
→ PIN reset
→ HUMAN transition
```

---

# Parent PIN Runtime

Parent authorization runtime now supports:

* direct PIN authorization,
* failed authorization tracking,
* recovery escalation,
* bounded recovery lifecycle,
* post-recovery PIN replacement.

Runtime state currently includes:

```kotlin
isWaitingForParentPin
isWaitingForParentRecovery
recoveryChallenge
recoveryErrorMessage
failedParentPinAttempts
```

---

# Recovery Challenge Runtime

Recovery challenges are now generated through:

```text
bounded temporal semantic arithmetic generation
```

Supported semantic categories:

* previous/current/next year,
* previous/current/next month,
* previous/current/next day of month,
* previous/current/next weekday.

The recovery runtime intentionally prioritizes:

* human-readable semantics,
* positive-only arithmetic,
* bounded operand spaces,
* deterministic generation guarantees,
* publication-safe supportability.

---

# Recovery Runtime Guarantees

Recovery runtime guarantees:

* no negative answers,
* no zero answers,
* bounded semantic operand space,
* bounded numeric operand space,
* no invalid weekday transitions,
* no invalid month transitions,
* no invalid day-of-month transitions,
* no duplicate semantic operands,
* no invalid subtraction results.

Subtraction semantics:

```text
max(a, b) - min(a, b)
```

Invalid temporal semantics are removed through semantic cleaner stages before operand selection.

Numeric equality collisions trigger regeneration.

The generator has passed randomized invariant testing on the order of:

```text
10^8 generated recovery challenges
```

without invariant violation.

---

# CHILD → TESTER

Forbidden.

TESTER intentionally remains:

* analytics/testing runtime actor,
* non-parental runtime participant,
* topology validation runtime.

TESTER MUST NOT:

* participate in protected parent semantics,
* become ownership recovery target,
* bypass authorization boundaries.

---

# HUMAN → TESTER

Allowed only in TEST topology.

This transition exists specifically for:

* analytics validation,
* gameplay validation,
* telemetry validation,
* topology verification.

---

# Runtime Topology Projection

Profile runtime now projects runtime identity through:

```text
profiles topology
→ projection
→ UI
```

instead of:

* manual visibility synchronization,
* imperative active profile propagation,
* runtime cache reconstruction.

The project intentionally treats:

```text
visibility
```

as:

```text
projection of runtime topology
```

NOT:

* independent UI state,
* manually synchronized visibility cache.

---

# Capability Relationship

Capabilities intentionally do NOT own:

* active runtime identity,
* profile transitions,
* runtime ownership transfer,
* authorization flow,
* runtime synchronization.

Capabilities ONLY define:

```text
which runtime topology can exist
```

Examples:

* CHILD runtime graph exists or not,
* TESTER runtime actor exists or not,
* protected transitions required or not.

---

# Runtime Topology Collapse

FREE runtime intentionally collapses into:

```text
single HUMAN runtime topology
```

In this topology:

* CHILD runtime graph absent,
* parent authorization unnecessary,
* protected transitions absent,
* recovery flow absent.

The architecture intentionally prefers:

```text
topology collapse
```

instead of:

* runtime blocking,
* hidden runtime branches,
* dead transition surfaces.

---

# Deprecated Runtime Infrastructure

The following infrastructure is now considered:

* obsolete,
* transitional,
* under removal.

Deprecated direction:

* ProfileRuntime,
* ProfileManager,
* activeProfileId synchronization,
* reloadParentPinState(),
* runtime identity caches,
* repository-owned runtime reconstruction helpers.

---

# Repository Runtime Direction

ProfileRepository is now evolving toward:

```text
reactive topology adapter
```

instead of:

```text
runtime identity orchestrator
```

Repository responsibilities:

* persistence,
* topology observation,
* ownership mutation,
* transition persistence.

Repository intentionally avoids:

* runtime synchronization,
* identity propagation,
* manual cache coordination,
* UI lifecycle ownership.

---

# Runtime Identity Boundaries

---

# Profile owns

* persistent gameplay identity,
* progression state,
* renderer selection,
* notebook selection,
* gameplay mode identity,
* runtime ownership semantics,
* parent authorization semantics.

---

# Runtime owns

* gameplay execution,
* session lifecycle,
* round lifecycle,
* runtime continuation,
* renderer realization.

---

# Statistics owns

* runtime observation,
* behavioral reconstruction,
* explainable analytics,
* topology-aware interpretation.

---

# Capabilities own

* runtime topology composition,
* topology materialization,
* topology collapse,
* environment-specific runtime existence.

---

# Security Runtime

Current protected runtime semantics:

* CHILD → HUMAN requires PIN,
* invalid PIN attempts tracked locally,
* bounded recovery escalation exists,
* recovery challenge generation operational,
* recovery-based PIN reset operational.

The current architecture intentionally avoids:

* generalized recovery frameworks,
* distributed authority graphs,
* cached authorization state,
* adaptive authorization systems.

---

# Current Strategic Direction

The project is intentionally optimizing for:

```text
reactive deterministic runtime ownership
```

with:

* topology-driven runtime projection,
* pairwise ownership transfer,
* bounded identity semantics,
* supportable runtime transitions,
* reactive runtime observation,
* publication-safe runtime topology,
* minimized synchronization complexity,
* bounded recovery semantics.
