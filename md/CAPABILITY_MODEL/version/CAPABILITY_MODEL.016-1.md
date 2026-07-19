# CAPABILITY_MODEL

## Version

0.16

## Last Updated

2026-05-27

---

# Overview

The capability model exists as:

```text
bounded runtime topology composition infrastructure
```

NOT:

```text
generic feature-flag orchestration
```

The capability system intentionally prioritizes:

* deterministic topology composition,
* publication-safe runtime collapse,
* explicit runtime existence semantics,
* supportability,
* deployment-safe configuration,
* bounded runtime surfaces,
* monetization-safe runtime expansion,
* TEST/PRODUCTION topology separation.

The architecture intentionally avoids:

* runtime permission chaos,
* distributed feature gating,
* hidden capability mutation,
* UI-driven runtime visibility,
* monetization-owned gameplay semantics,
* developer-only hidden branches,
* runtime policy synthesis,
* generalized entitlement orchestration.

Core runtime philosophy remains aligned with: 

---

# Strategic Capability Principle

Capabilities intentionally define:

```text
what runtime topology exists
```

NOT:

```text
how gameplay executes
```

This distinction is strategically critical.

Capabilities MAY determine:

* whether BEFORE topology exists,
* whether HYBRID topology exists,
* whether CHILD topology exists,
* whether TESTER topology exists,
* whether runtime graphs collapse.

Capabilities MUST NOT determine:

* gameplay correctness,
* runtime continuation,
* renderer behavior,
* statistics interpretation,
* telemetry semantics,
* persistence ownership.

This preserves:

* deterministic runtime semantics,
* explicit ownership,
* bounded operational complexity,
* publication-safe topology behavior.

Capability philosophy is intentionally aligned with the CORE runtime direction. 

---

# Canonical Capability Surface

Current canonical capability surface intentionally remains small:

```kotlin
RuntimeCapabilities(

    supportsMultiProfileRuntime,

    supportsBeforeMode,

    supportsHybridMode,

    supportsAnalyticTesterProfileRuntime
)
```

This boundedness is strategically important. 

The capability system intentionally avoids:

* arbitrary boolean explosion,
* UI-specific flags,
* renderer-specific flags,
* analytics policy flags,
* telemetry feature branching,
* gameplay semantic branching.

The current surface already supports:

* FREE topology,
* PREMIUM topology,
* TEST topology,
* PRODUCTION topology,
* TESTER inclusion/exclusion,
* BEFORE/HYBRID topology collapse,
* CHILD runtime topology.

---

# Capability Topology Philosophy

The capability system intentionally treats unavailable runtime branches as:

```text
non-existent runtime topology
```

instead of:

```text
disabled UI
```

This distinction is fundamental.

The project intentionally prefers:

```text
topology collapse
```

instead of:

```text
runtime blocking
```

Examples:

* FREE runtime physically lacks BEFORE topology,
* FREE runtime physically lacks CHILD runtime,
* TESTER is physically absent in PRODUCTION,
* unavailable gameplay modes do not materialize.

This direction strongly improves:

* supportability,
* runtime clarity,
* lifecycle visibility,
* publication safety,
* ownership semantics.

Capability topology philosophy remains aligned with CORE and MONETIZATION direction. 


---

# Environment Separation

The capability system intentionally separates:

```text
environment topology
```

from:

```text
entitlement topology
```

Canonical environments:

| Environment | Purpose              |
| ----------- | -------------------- |
| TEST        | validation topology  |
| PRODUCTION  | publication topology |

Canonical entitlement/runtime states:

| Runtime | Purpose            |
| ------- | ------------------ |
| FREE    | simplified runtime |
| PREMIUM | expanded runtime   |

Environment MUST remain independent from entitlement.

This prevents:

* monetization leakage into testing,
* testing-only premium semantics,
* production/testing ambiguity,
* hidden runtime divergence.

The current codebase already reflects this separation through explicit capability configs. 

---

# Canonical Runtime Presets

Current runtime presets are intentionally explicit.

---

# PRODUCTION + FREE

Characteristics:

* single HUMAN runtime,
* AFTER gameplay only,
* no BEFORE topology,
* no HYBRID topology,
* no CHILD topology,
* no TESTER topology,
* simplified supportable runtime.

Current capability projection:

```kotlin
RuntimeCapabilities(

    supportsMultiProfileRuntime = false,

    supportsBeforeMode = false,

    supportsHybridMode = false,

    supportsAnalyticTesterProfileRuntime = false
)
```

Aligned with monetization philosophy: 

---

# PRODUCTION + PREMIUM

Characteristics:

* BEFORE gameplay,
* HYBRID gameplay,
* CHILD runtime topology,
* protected transitions,
* parent authorization,
* recovery runtime,
* expanded deterministic topology.

Current capability projection:

```kotlin
RuntimeCapabilities(

    supportsMultiProfileRuntime = true,

    supportsBeforeMode = true,

    supportsHybridMode = true,

    supportsAnalyticTesterProfileRuntime = false
)
```

Premium intentionally acts as:

```text
runtime topology expansion
```

instead of arbitrary feature unlocking. 

---

# TEST + FREE

Characteristics:

* TESTER visible,
* AFTER-only gameplay,
* simplified topology,
* analytics validation support.

Purpose:

* telemetry validation,
* runtime validation,
* graph validation,
* deterministic reconstruction testing.

---

# TEST + PREMIUM

Characteristics:

* TESTER visible,
* BEFORE gameplay,
* HYBRID gameplay,
* CHILD topology,
* premium runtime validation,
* analytics topology validation.

Purpose:

* publication validation,
* capability rematerialization validation,
* rendering validation,
* topology verification.

TESTER semantics remain bounded and observational. 

---

# Capability Ownership Boundaries

The capability layer intentionally owns only:

```text
runtime topology composition
```

Capabilities intentionally DO NOT own:

| Concern                  | Canonical Owner   |
| ------------------------ | ----------------- |
| gameplay semantics       | Gameplay runtime  |
| runtime continuation     | Session           |
| gameplay identity        | Profile           |
| topology semantics       | Topology          |
| analytics reconstruction | Statistics        |
| telemetry export         | Telemetry         |
| rendering                | Renderer          |
| persistence semantics    | Persistence layer |

This ownership separation is strategically important for long-term supportability. 

---

# Capability Rematerialization

Capability changes intentionally behave as:

```text
runtime topology rematerialization
```

instead of:

```text
UI mutation
```

Canonical flow:

```text
entitlement/environment change
→ capability projection
→ topology rematerialization
→ runtime projection refresh
```

This preserves:

* deterministic runtime semantics,
* reactive projection correctness,
* bounded lifecycle ownership,
* supportable runtime reconstruction.

Current runtime direction already strongly aligns with this model. 

---

# Reactive Capability Projection

Current strategic direction:

```text
Capabilities
→ runtime topology
→ reactive projection
→ UI
```

NOT:

```text
UI checks feature flags everywhere
```

This significantly reduces:

* scattered conditional logic,
* hidden topology branches,
* runtime ambiguity,
* lifecycle corruption risks.

Reactive runtime projection remains one of the strongest architectural improvements in the project. 

---

# TESTER Capability Semantics

TESTER is intentionally modeled as:

```text
bounded validation topology
```

NOT:

```text
developer authority
```

TESTER existence is controlled only by:

```kotlin
supportsAnalyticTesterProfileRuntime
```

Meaning:

```text
TESTER topology exists
```

NOT:

```text
TESTER receives privileged permissions
```

This distinction is strategically critical.

TESTER MUST remain:

```text
physically absent from production topology
```

instead of hidden behind UI filtering. 

---

# Capability and Monetization Relationship

Monetization intentionally operates through:

```text
capability projection
```

instead of:

```text
runtime mutation
```

Google Billing direction:

```text
Google Billing
→ entitlement state
→ capability rematerialization
→ topology update
```

This preserves:

* deterministic semantics,
* supportability,
* bounded complexity,
* deployment-safe runtime behavior.

The monetization layer intentionally avoids:

* gameplay mutation,
* hidden premium overrides,
* runtime continuation manipulation,
* analytics distortion.

Monetization philosophy remains structurally coherent. 

---

# Architectural Strengths

The current capability system already provides:

* bounded capability surface,
* deterministic topology collapse,
* publication-safe TEST separation,
* monetization-safe topology expansion,
* supportable runtime semantics,
* explicit topology existence,
* deployment-safe runtime branching.

This is substantially more supportable than:

* generalized feature-flag systems,
* distributed runtime permissions,
* hidden entitlement mutation,
* runtime-specific UI gating.

---

# Current Remaining Risks

## 1. Capability Surface Explosion

Current capability surface is intentionally small.

Risk:

```text
capabilities begin encoding runtime policy
```

instead of topology existence.

Especially dangerous:

* UI-specific flags,
* analytics behavior flags,
* renderer capability flags,
* telemetry behavior flags.

---

## 2. Hidden Runtime Branches

Danger:

```text
runtime topology exists but is visually hidden
```

instead of physically absent.

This especially threatens:

* TESTER topology,
* FREE runtime simplification,
* premium collapse behavior.

---

## 3. Ownership Leakage

Capabilities must NOT begin owning:

* gameplay semantics,
* persistence semantics,
* statistics semantics,
* telemetry semantics,
* rendering semantics.

Ownership erosion is considered architectural risk.

---

# Strategic Capability Conclusion

The capability system is now strategically aligned with the overall runtime philosophy.

It already successfully acts as:

```text
bounded deterministic runtime topology composition infrastructure
```

instead of:

```text
generalized feature permission chaos
```

The current direction strongly supports:

* publication-safe deployment,
* deterministic topology collapse,
* monetization-safe runtime expansion,
* TEST/PRODUCTION separation,
* supportability-first runtime stabilization,
* bounded operational semantics.

The most important next objective is now:

```text
maintaining bounded capability semantics
```

NOT:

```text
expanding capability sophistication
```

Capability direction remains structurally coherent with the current publication stabilization strategy. 

