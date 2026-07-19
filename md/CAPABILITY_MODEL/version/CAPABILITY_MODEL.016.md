# CAPABILITY_MODEL

## Version

0.16

## Last Updated

2026-05-25

---

# Overview

The project now treats capabilities as:

```text
runtime topology composition infrastructure
```

instead of:

```text
permission-oriented feature flags
```

Capabilities explicitly materialize:

* runtime topology,
* publication variants,
* monetization boundaries,
* environment-specific runtime actors,
* gameplay mode availability,
* profile graph existence,
* topology-specific runtime projection,
* publication-safe runtime collapse.

The capability system intentionally remains:

* deterministic,
* bounded,
* explicit,
* deployment-oriented,
* topology-first,
* publication-oriented.

---

# Current Strategic Context

The project is currently in:

```text
publication stabilization phase
```

User-facing functionality for the first public release is considered:

```text
feature-complete
```

The capability system is now primarily responsible for:

* publication runtime composition,
* free/premium runtime topology separation,
* TEST/PRODUCTION topology separation,
* bounded deployment-safe runtime surfaces,
* publication-safe topology collapse.

The project intentionally avoids:

* continuous capability expansion,
* generalized policy systems,
* enterprise authorization frameworks,
* speculative runtime composition infrastructure.

---

# Core Principle

Capabilities intentionally define:

```text
what runtime topology exists
```

NOT:

```text
what UI widgets are visible
```

Capabilities MAY influence:

* runtime topology existence,
* profile graph materialization,
* gameplay mode availability,
* bootstrap composition,
* SettingsScreen projection,
* runtime topology collapse,
* profile visibility projection,
* publication runtime composition.

Capabilities MUST NOT:

* own gameplay semantics,
* orchestrate runtime continuation,
* mutate gameplay correctness,
* synthesize hidden runtime behavior,
* own authorization logic,
* coordinate persistence,
* own active runtime identity.

---

# Capability Philosophy

The project intentionally avoids:

```text
enterprise capability engine architecture
```

The project intentionally avoids:

* RBAC,
* role hierarchy systems,
* generalized policy engines,
* orchestration-oriented permission systems,
* dynamic authority graphs,
* distributed runtime authority ownership,
* runtime-owned monetization orchestration.

The project intentionally optimizes for:

```text
bounded deployable runtime composition
```

---

# Runtime Identity Relationship

Current runtime actors:

* HUMAN
* CHILD
* TESTER
* SYSTEM

remain:

```text
semantic runtime actors
```

NOT:

```text
permission bundles
```

Capabilities define:

```text
what runtime topology is materialized
```

NOT:

```text
what type a profile becomes
```

Capabilities intentionally do NOT own:

* active profile state,
* profile transitions,
* runtime ownership transfer,
* topology observation,
* parent authorization semantics.

---

# RuntimeCapabilities

Current runtime capability projection:

```kotlin
RuntimeCapabilities(

    supportsMultiProfileRuntime,

    supportsBeforeMode,

    supportsHybridMode,

    supportsAnalyticTesterProfileRuntime
)
```

Current capability ownership:

* runtime topology,
* gameplay mode availability,
* bootstrap composition,
* profile graph existence,
* SettingsScreen projection,
* runtime topology collapse,
* publication runtime composition.

Capabilities intentionally do NOT own:

* gameplay logic,
* authorization,
* runtime transitions,
* persistence,
* billing lifecycle,
* Firebase integration,
* runtime synchronization.

---

# Current Capability Set

---

# supportsMultiProfileRuntime

Represents:

```text
runtime identity topology expansion
```

This is currently the primary runtime topology capability.

---

## Enables

* CHILD profile existence,
* parent authorization semantics,
* protected profile transitions,
* child profile creation,
* recovery semantics,
* multi-profile runtime graph,
* topology-aware runtime projection.

---

## Disables

When disabled:

* CHILD runtime graph does not exist,
* parent authorization becomes unnecessary,
* protected transitions collapse,
* recovery flow becomes unnecessary,
* profile topology simplifies reactively.

FREE runtime intentionally collapses into:

```text
single HUMAN runtime
```

---

# supportsBeforeMode

Enables:

```text
BEFORE gameplay runtime
```

When disabled:

* BEFORE mode must not materialize,
* BEFORE runtime transitions must be rejected,
* BEFORE runtime topology must remain absent,
* BEFORE setup projection must not exist.

---

# supportsHybridMode

Enables:

```text
HYBRID gameplay runtime
```

When disabled:

* HYBRID mode must not materialize,
* HYBRID runtime transitions must be rejected,
* HYBRID setup projection must remain absent.

---

# supportsAnalyticTesterProfileRuntime

Represents:

```text
analytics/testing runtime actor support
```

NOT:

```text
developer cheat mode
```

---

# TESTER Responsibilities

TESTER exists to:

* generate gameplay statistics,
* populate analytics graphs,
* validate telemetry,
* validate gameplay semantics,
* validate statistics runtime,
* validate topology transitions,
* validate capability projection,
* validate graph dataset generation.

---

# TESTER Restrictions

TESTER MUST NOT:

* create CHILD profiles,
* own parent authorization,
* own recovery semantics,
* own monetization authority,
* become topology authority owner.

---

# TESTER Gameplay Access

TESTER currently has access to:

* AFTER,
* BEFORE,
* HYBRID.

because TESTER exists specifically for:

```text
runtime and analytics validation
```

---

# Runtime Topology Materialization

Capabilities directly materialize:

```text
runtime topology presets
```

instead of:

* giant conditional UI,
* hidden runtime branches,
* scattered permission checks,
* runtime synchronization heuristics.

---

# Runtime Topology Collapse

The current capability philosophy intentionally prioritizes:

```text
runtime topology collapse
```

instead of:

```text
runtime blocking
```

Meaning:

* unavailable topology branches should not exist,
* unavailable runtime actors should not materialize,
* unavailable gameplay modes should not appear reactively.

The system intentionally prefers:

* topology absence,
* bounded runtime surfaces,
* deterministic runtime graphs.

---

# Current Runtime Topologies

The project currently materializes 4 explicit runtime topologies.

---

# TEST + FREE

```text
supportsMultiProfileRuntime = false
supportsBeforeMode = false
supportsHybridMode = false
supportsAnalyticTesterProfileRuntime = true
```

Characteristics:

* TESTER visible,
* single HUMAN runtime,
* AFTER-only gameplay,
* no CHILD runtime graph.

---

# TEST + PREMIUM

```text
supportsMultiProfileRuntime = true
supportsBeforeMode = true
supportsHybridMode = true
supportsAnalyticTesterProfileRuntime = true
```

Characteristics:

* full runtime topology,
* TESTER visible,
* CHILD runtime graph,
* protected transitions,
* BEFORE/HYBRID gameplay.

---

# PRODUCTION + FREE

```text
supportsMultiProfileRuntime = false
supportsBeforeMode = false
supportsHybridMode = false
supportsAnalyticTesterProfileRuntime = false
```

Characteristics:

* publication-safe simplified runtime,
* single HUMAN runtime,
* AFTER-only gameplay,
* TESTER absent,
* no CHILD runtime graph.

---

# PRODUCTION + PREMIUM

```text
supportsMultiProfileRuntime = true
supportsBeforeMode = true
supportsHybridMode = true
supportsAnalyticTesterProfileRuntime = false
```

Characteristics:

* publication-safe premium runtime,
* CHILD runtime graph,
* protected transitions,
* BEFORE/HYBRID gameplay,
* TESTER absent.

---

# Environment vs Entitlement

The capability system explicitly separates:

```text
environment
```

from:

```text
entitlement
```

This separation is considered architecturally critical.

---

# Environment

Current environments:

```text
TEST
PRODUCTION
```

Environment represents:

* deployment/runtime composition,
* publication topology,
* TESTER materialization ownership,
* bootstrap topology filtering.

Environment is NOT:

* user-owned,
* billing-owned,
* persistent entitlement state,
* runtime authority source.

Current implementation:

* manually switched during development.

---

# Entitlement

Current entitlement model:

```text
isPremium
```

Entitlement represents:

```text
premium runtime ownership
```

NOT:

* deployment environment,
* publication variant,
* runtime authority graph,
* runtime identity ownership.

Current implementation:

* development mock repository,
* manual runtime switching,
* deterministic runtime rematerialization.

---

# Capability Factory

Current runtime composition is materialized through:

```text
CapabilityFactory
```

Current responsibilities:

* environment-aware runtime selection,
* entitlement-aware runtime selection,
* runtime topology materialization,
* topology preset projection.

Current direction intentionally avoids:

* giant provider systems,
* distributed capability ownership,
* orchestration runtimes,
* reactive synchronization layers.

---

# Runtime Topology Presets

The project intentionally uses:

```text
explicit immutable topology presets
```

instead of:

* nested conditional capability graphs,
* giant mutable runtime capability objects,
* runtime-generated topology composition.

Current preset structure:

```text
configs/

    ProductionFreeCapabilities
    ProductionPremiumCapabilities
    TestFreeCapabilities
    TestPremiumCapabilities
```

This approach intentionally prioritizes:

* explicit topology visibility,
* deployment clarity,
* supportability,
* bounded runtime complexity,
* deterministic runtime materialization.

---

# Reactive Topology Projection

Capabilities now project runtime topology reactively through:

```text
profiles topology
→ projection
→ UI
```

instead of:

* manual runtime synchronization,
* hidden active profile propagation,
* imperative visibility refresh logic.

Current direction:

```text
Flow<List<Profile>>
→ topology projection
→ Settings projection
→ UI
```

---

# Capability Projection

Capabilities influence:

* SettingsScreen sections,
* gameplay mode availability,
* CHILD profile materialization,
* TESTER bootstrap materialization,
* profile visibility projection,
* runtime topology collapse.

Important principle:

```text
capabilities project runtime topology
```

instead of:

* hiding arbitrary widgets,
* fragmenting business logic,
* scattering permissions across UI,
* synchronizing invisible runtime branches.

---

# Bootstrap Integration

Bootstrap runtime now supports:

* capability-driven TESTER materialization,
* production TESTER exclusion,
* topology-aware profile seeding,
* topology collapse.

Current preferred direction:

```text
TESTER physically absent from production topology
```

instead of:

* UI-hidden tester,
* runtime-blocked tester,
* post-bootstrap runtime filtering.

---

# SettingsScreen Direction

SettingsScreen is evolving toward:

```text
topology-specific runtime projection
```

instead of:

* giant conditional forms,
* widget-level permission gating,
* mixed runtime semantics,
* invisible runtime branches.

Examples:

* humanFreeProfileSetupSection
* humanPremiumProfileSetupSection

FREE and PREMIUM setup flows are intentionally treated as:

```text
different runtime topologies
```

NOT:

```text
same form with hidden fields
```

---

# Visibility vs Runtime Existence

The project intentionally distinguishes between:

---

# Visibility Projection

Example:

* hide unavailable actions.

---

# Runtime Topology Existence

Example:

* CHILD runtime graph absent entirely,
* TESTER physically absent in production bootstrap,
* BEFORE runtime topology absent.

The project intentionally prefers:

```text
runtime topology collapse
```

instead of:

* UI-only hiding,
* runtime-blocked dead branches,
* invisible runtime semantics.

---

# Runtime Identity Compatibility

Capability system intentionally remains compatible with:

```text
pairwise runtime ownership transition
```

through:

```kotlin
switchActiveProfile(
    oldProfile,
    newProfile
)
```

Capabilities do NOT:

* participate in ownership transfer,
* synchronize active profile state,
* own topology observation.

Capabilities only define:

```text
which topology can exist
```

---

# Publication-Oriented Runtime Strategy

The current capability system now primarily exists to support:

* publication-safe runtime composition,
* monetization-ready topology separation,
* deployable runtime collapse,
* bounded production runtime surfaces.

The project intentionally prioritizes:

```text
stable deployable runtime topology
```

over:

```text
continuous capability expansion
```

---

# Billing Direction

Future billing integration intentionally remains bounded.

Planned direction:

```text
Google Billing
→ entitlement state
→ capability rematerialization
→ runtime topology update
```

Capabilities intentionally remain:

* billing-agnostic,
* persistence-agnostic,
* Firebase-agnostic,
* runtime-synchronization-agnostic.

---

# Current Strategic Direction

The project is intentionally prioritizing:

```text
minimal deployable capability composition
```

instead of:

```text
formal capability framework completeness
```

Current optimization target:

```text
stable publication-oriented runtime topology
```

with:

* deterministic topology materialization,
* bounded runtime surfaces,
* topology collapse,
* reactive topology projection,
* supportable monetization composition,
* deployment-safe capability semantics,
* publication-safe runtime stabilization.
