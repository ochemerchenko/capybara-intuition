# PROFILE_TRANSITIONS

## Version

0.16

## Last Updated

2026-05-25

---

# Overview

The profile transition subsystem is evolving from:

```text
UI-oriented profile switching
```

toward:

```text
deterministic runtime ownership transfer infrastructure
```

The project now treats profile transitions as:

```text
runtime topology ownership mutations
```

instead of:

```text
simple UI selection changes
```

Current architecture intentionally prioritizes:

* explicit ownership transfer,
* deterministic runtime semantics,
* bounded transition rules,
* reactive topology projection,
* publication-safe security semantics,
* topology-aware capability integration,
* supportable recovery semantics.

The subsystem intentionally avoids:

* hidden runtime activation,
* distributed transition ownership,
* synchronization-oriented profile switching,
* UI-driven runtime semantics,
* runtime identity duplication.

---

# Core Transition Principle

The canonical transition meaning is now:

```text
switch profile
=
transfer runtime ownership
```

NOT:

```text
change currently selected UI item
```

This principle now governs:

* gameplay runtime materialization,
* statistics ownership,
* renderer ownership,
* notebook ownership,
* progression ownership,
* runtime visibility projection.

---

# Canonical Transition Flow

Current transition flow:

```text
UI Intent
→ ViewModel
→ Profile Transition Gateway
→ Repository Ownership Mutation
→ Reactive Topology Update
→ Runtime Projection Refresh
```

The architecture intentionally avoids:

```text
UI
→ activeProfileId mutation
→ manual reload chain
→ cache synchronization
```

---

# Transition Ownership

Profile transitions intentionally belong to:

```text
profile runtime domain
```

NOT:

* SettingsScreen,
* gameplay runtime,
* statistics runtime,
* capability runtime,
* UI state containers.

---

# Canonical Transition Operation

Current canonical runtime operation:

```kotlin
switchActiveProfile(
    oldProfile,
    newProfile
)
```

This operation intentionally represents:

```text
pairwise ownership transfer
```

instead of:

* arbitrary activation,
* global topology reconstruction,
* multi-profile synchronization.

---

# Pairwise Ownership Semantics

Transition assumptions:

* exactly one active runtime owner exists,
* ownership transfer is explicit,
* previous owner loses ownership,
* new owner receives ownership,
* topology updates reactively.

The architecture intentionally avoids:

* multi-owner ambiguity,
* implicit ownership reassignment,
* hidden topology mutation,
* runtime synchronization heuristics.

---

# Reactive Transition Projection

Transitions are now projected through:

```text
Flow<List<Profile>>
→ topology projection
→ UI/runtime reaction
```

instead of:

```text
activeProfileId
→ manual synchronization
→ explicit reload
```

Current reactive consumers include:

* gameplay runtime,
* statistics runtime,
* SettingsScreen projection,
* runtime visibility projection.

---

# Transition Categories

---

# HUMAN → HUMAN

Allowed directly.

Semantics:

```text
runtime ownership transferred between primary identities
```

This transition:

* updates active ownership,
* rematerializes runtime identity,
* preserves deterministic topology semantics.

---

# HUMAN → CHILD

Allowed only when:

```text
supportsMultiProfileRuntime = true
```

Semantics:

```text
runtime ownership delegated to subordinate identity
```

This transition enables:

* protected child runtime,
* restricted gameplay ownership,
* parent-controlled runtime topology.

---

# CHILD → HUMAN

Protected transition.

Requires:

* parent PIN authorization,
* ownership verification,
* bounded recovery semantics.

Current meaning:

```text
child requests ownership return
```

NOT:

```text
child activates arbitrary runtime target
```

The architecture intentionally avoids:

* unrestricted child escalation,
* arbitrary activation semantics,
* hidden authority reassignment.

---

# Parent Authorization Runtime

Protected CHILD → HUMAN transition now supports:

* direct PIN authorization,
* failed authorization tracking,
* bounded recovery escalation,
* recovery-based PIN replacement,
* deterministic ownership restoration.

Protected flow topology:

```text
CHILD
→ parent PIN request
→ authorization verification
→ ownership transfer
→ topology update
```

---

# Parent Recovery Runtime

Parent recovery flow is now operational.

Recovery flow topology:

```text
CHILD
→ failed PIN attempts
→ recovery challenge
→ PIN reset
→ HUMAN transition
```

The recovery subsystem intentionally remains:

* bounded,
* deterministic,
* supportable,
* publication-safe.

---

# Recovery Challenge Runtime

Recovery challenges are generated through:

```text
bounded temporal semantic arithmetic generation
```

Supported semantic categories:

* previous/current/next year,
* previous/current/next month,
* previous/current/next day of month,
* previous/current/next weekday.

Recovery runtime intentionally prioritizes:

* human-readable semantics,
* positive-only arithmetic,
* bounded operand spaces,
* deterministic runtime guarantees,
* supportable runtime behavior.

The recovery generator intentionally does NOT attempt to implement full formal calendar semantics.

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

# HUMAN → TESTER

Allowed only in:

```text
TEST topology
```

and only when:

```text
supportsAnalyticTesterProfileRuntime = true
```

Purpose:

* analytics validation,
* telemetry validation,
* runtime topology testing,
* gameplay verification,
* statistics dataset generation,
* graph validation.

TESTER intentionally acts as:

```text
analytics/testing runtime actor
```

NOT:

* developer superuser,
* authority override,
* hidden production runtime.

---

# TESTER → HUMAN

Allowed only in TEST topology.

Semantics:

```text
return runtime ownership to primary identity topology
```

This transition exists for:

* deterministic topology validation,
* analytics flow testing,
* runtime observation testing.

---

# CHILD → TESTER

Forbidden.

Reason:

* TESTER is not a parental authority,
* TESTER must not bypass protected runtime semantics,
* TESTER must not become recovery owner.

The architecture intentionally preserves:

* bounded security semantics,
* deterministic authority relationships,
* publication-safe runtime topology.

---

# SYSTEM Transition Rules

SYSTEM profile intentionally remains:

```text
immutable bootstrap-owned gameplay identity
```

SYSTEM MUST NOT:

* participate in runtime ownership transfer,
* become active gameplay runtime owner,
* appear in runtime profile switching,
* participate in protected transition recovery.

SYSTEM exists exclusively for:

* canonical bootstrap defaults,
* gameplay identity materialization,
* deterministic initialization semantics.

---

# Capability Integration

Capabilities intentionally define:

```text
which transitions can exist
```

NOT:

```text
how transitions execute
```

Example:

```text
supportsMultiProfileRuntime = false
```

causes:

```text
CHILD runtime topology absent
```

Therefore:

* CHILD transitions do not exist,
* protected transitions collapse,
* recovery flow unnecessary.

The project intentionally prefers:

```text
topology collapse
```

instead of:

* runtime-blocked dead transitions,
* invisible transition branches,
* permission-oriented UI hiding.

---

# FREE Runtime Transition Topology

FREE runtime intentionally collapses into:

```text
single HUMAN runtime topology
```

Characteristics:

* CHILD absent,
* TESTER absent in production,
* protected transitions absent,
* recovery semantics unnecessary.

This intentionally minimizes:

* security complexity,
* runtime surfaces,
* transition ambiguity,
* support burden.

---

# PREMIUM Runtime Transition Topology

PREMIUM runtime enables:

* CHILD runtime graph,
* protected transitions,
* parent authorization,
* bounded recovery semantics,
* topology-aware runtime projection.

Capabilities intentionally expand:

```text
runtime topology existence
```

NOT:

* permission flags,
* UI-only visibility states.

---

# Transition Security Model

Protected transitions intentionally separate:

```text
runtime ownership request
```

from:

```text
authorization approval
```

Current protected flow:

```text
CHILD request
→ PIN verification
→ ownership transfer
→ topology update
```

The architecture intentionally avoids:

* implicit authorization,
* cached approval state,
* hidden privilege escalation,
* distributed security ownership.

---

# Runtime Materialization Relationship

Successful transition triggers:

```text
runtime identity rematerialization
```

Conceptual flow:

```text
Profile
→ Session Materialization
→ Runtime Execution Context
→ Gameplay Runtime
```

The transition subsystem intentionally does NOT:

* orchestrate gameplay continuation,
* preserve transient gameplay execution,
* synchronize runtime caches,
* reconstruct hidden runtime defaults.

---

# Statistics Relationship

Statistics intentionally reacts to:

* ownership transitions,
* topology updates,
* active runtime projection.

Statistics observes:

```text
what runtime topology became active
```

NOT:

```text
why the transition happened
```

Statistics remains:

* observational,
* read-only,
* deterministic.

---

# Repository Responsibilities

ProfileRepository currently owns:

* ownership mutation persistence,
* topology observation,
* transition persistence semantics.

Repository intentionally does NOT own:

* runtime orchestration,
* UI synchronization,
* gameplay continuation,
* capability evaluation.

---

# Deprecated Transition Infrastructure

The following direction is now considered obsolete:

* activeProfileId synchronization,
* ProfileManager-driven switching,
* reload rituals,
* runtime identity caches,
* UI-owned activation semantics,
* synchronization-oriented profile switching.

---

# Architectural Risks

The project continuously monitors risks of:

---

# Distributed Ownership

Especially dangerous when:

* UI begins owning transition semantics,
* capabilities begin mutating identity,
* gameplay runtime begins activating profiles.

---

# Implicit Security Semantics

Especially dangerous when:

* child transitions bypass PIN semantics,
* TESTER gains hidden authority,
* authorization state becomes cached implicitly.

---

# Runtime Synchronization Explosion

Especially dangerous when:

* topology caches reappear,
* manual reload chains return,
* transition propagation becomes imperative.

---

# Current Strategic Direction

The project is intentionally optimizing for:

```text
bounded deterministic runtime ownership transfer
```

with:

* pairwise ownership semantics,
* reactive topology projection,
* publication-safe security boundaries,
* capability-compatible topology collapse,
* minimized synchronization complexity,
* supportable transition semantics,
* bounded recovery semantics.
