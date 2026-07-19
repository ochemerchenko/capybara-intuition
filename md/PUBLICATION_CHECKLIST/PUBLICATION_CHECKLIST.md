# PUBLICATION_CHECKLIST

## Version

0.16

## Last Updated

2026-05-25

---

# Overview

This document defines the bounded publication checklist required for the first public Google Play release.

The checklist intentionally prioritizes:

* publication-safe runtime behavior,
* deterministic runtime correctness,
* supportability,
* deployment safety,
* monetization readiness,
* localization completeness,
* telemetry-safe analytics,
* bounded runtime topology,
* production-safe capability composition.

The checklist intentionally does NOT optimize for:

* maximum feature volume,
* speculative infrastructure,
* architectural experimentation,
* generalized platform engineering,
* post-launch expansion domains.

The project is currently considered:

```text
feature-complete for first publication
```

and is now focused on:

```text
publication stabilization
```

:contentReference[oaicite:0]{index=0}

---

# Publication Gate Philosophy

The application MUST satisfy the following before publication:

* runtime truthfulness,
* deterministic reconstruction,
* bounded ownership semantics,
* supportable runtime topology,
* publication-safe profile transitions,
* telemetry-safe persistence semantics,
* cross-device rendering stability,
* production-safe topology collapse,
* localization safety,
* monetization-safe capability composition.

The project intentionally treats:

```text
runtime correctness
```

as more important than:

```text
feature quantity
```

:contentReference[oaicite:1]{index=1}
:contentReference[oaicite:2]{index=2}

---

# 1. Runtime Integrity Checklist

## Gameplay Runtime

- [ ] BEFORE gameplay semantics verified
- [ ] AFTER gameplay semantics verified
- [ ] HYBRID gameplay semantics verified
- [ ] runtime continuation deterministic
- [ ] session materialization deterministic
- [ ] runtime root replacement stable
- [ ] gameplay runtime free from reducer-era artifacts
- [ ] no hidden gameplay default reconstruction
- [ ] runtime handlers only materialize persisted identity
- [ ] runtime event chain traceable and supportable

Relevant architecture:

```text
event
→ router
→ runtime handlers
→ runtime execution context
→ next runtime event
```

:contentReference[oaicite:3]{index=3}
:contentReference[oaicite:4]{index=4}
:contentReference[oaicite:5]{index=5}

---

## Runtime Identity

- [ ] exactly one active runtime owner invariant verified
- [ ] topology observation fully reactive
- [ ] no activeProfileId synchronization remnants
- [ ] no duplicated runtime identity state
- [ ] runtime ownership transfer deterministic
- [ ] topology projection stable across lifecycle events
- [ ] profile ownership semantics validated

Canonical transition model:

```kotlin
switchActiveProfile(
    oldProfile,
    newProfile
)
```

:contentReference[oaicite:6]{index=6}
:contentReference[oaicite:7]{index=7}

---

## Runtime Persistence Integrity

- [ ] foreign keys validated
- [ ] relational integrity validated
- [ ] historical reconstruction verified
- [ ] profile identity persistence verified
- [ ] session snapshot semantics verified
- [ ] round persistence semantics verified
- [ ] notebook-resource integrity verified
- [ ] renderer/mask relational integrity verified
- [ ] deleted runtime artifacts removed
- [ ] orphaned records eliminated

Canonical perceptual coordinate:

```text
(notebookId, resourceId)
```

:contentReference[oaicite:8]{index=8}
:contentReference[oaicite:9]{index=9}
:contentReference[oaicite:10]{index=10}

---

# 2. Security & Parent Runtime Checklist

## Parent Authorization

- [ ] CHILD → HUMAN transition protected
- [ ] parent PIN verification stable
- [ ] failed PIN attempt tracking verified
- [ ] authorization escalation stable
- [ ] authorization state bounded
- [ ] no cached authorization leaks

Protected semantics:

```text
child requests ownership return
```

NOT:

```text
child activates arbitrary runtime target
```

:contentReference[oaicite:11]{index=11}
:contentReference[oaicite:12]{index=12}

---

## Recovery Runtime

- [ ] recovery challenge generation validated
- [ ] no invalid temporal operands
- [ ] no negative answers
- [ ] no zero answers
- [ ] no invalid subtraction results
- [ ] no duplicate semantic operands
- [ ] bounded operand space verified
- [ ] recovery PIN reset verified
- [ ] recovery lifecycle bounded and supportable

Recovery invariant status:

```text
10^8 randomized invariant tests passed
```

:contentReference[oaicite:13]{index=13}
:contentReference[oaicite:14]{index=14}
:contentReference[oaicite:15]{index=15}

---

# 3. Capability & Topology Checklist

## Runtime Topology Composition

- [ ] FREE topology validated
- [ ] PREMIUM topology validated
- [ ] TEST topology validated
- [ ] PRODUCTION topology validated
- [ ] topology collapse behavior validated
- [ ] unavailable runtime branches physically absent
- [ ] runtime visibility projection deterministic

Runtime capabilities:

```kotlin
RuntimeCapabilities(

    supportsMultiProfileRuntime,

    supportsBeforeMode,

    supportsHybridMode,

    supportsAnalyticTesterProfileRuntime
)
```

:contentReference[oaicite:16]{index=16}
:contentReference[oaicite:17]{index=17}

---

## TESTER Runtime

- [ ] TESTER absent from production topology
- [ ] TESTER graph generation verified
- [ ] TESTER analytics generation restored
- [ ] TESTER transition rules validated
- [ ] TESTER cannot bypass protected runtime semantics

:contentReference[oaicite:18]{index=18}
:contentReference[oaicite:19]{index=19}

---

# 4. Statistics & Analytics Checklist

## Statistics Runtime

- [ ] statistics reconstruction deterministic
- [ ] graph generation validated
- [ ] baseline calculation verified
- [ ] moving average stability verified
- [ ] compression correctness verified
- [ ] low-data behavior validated
- [ ] graph rendering validated
- [ ] statistics remains read-only
- [ ] no gameplay mutation paths exist

Statistics principle:

```text
Statistics reconstructs what happened
```

NOT:

```text
what should happen next
```

:contentReference[oaicite:20]{index=20}
:contentReference[oaicite:21]{index=21}

---

## Telemetry

- [ ] Firebase Analytics integrated
- [ ] Crashlytics integrated
- [ ] runtime failure reporting validated
- [ ] telemetry export deterministic
- [ ] telemetry uses canonical relational identifiers
- [ ] no telemetry-driven gameplay behavior
- [ ] telemetry remains observational only

Telemetry flow:

```text
Runtime Records
→ Telemetry Repository
→ Export Layer
→ Firebase
```

:contentReference[oaicite:22]{index=22}
:contentReference[oaicite:23]{index=23}
:contentReference[oaicite:24]{index=24}

---

# 5. Rendering & Device Validation Checklist

## Rendering Stability

- [ ] small-screen rendering validated
- [ ] tablet rendering validated
- [ ] adaptive spacing validated
- [ ] graph rendering validated
- [ ] chart viewport stability verified
- [ ] no clipped interactive zones
- [ ] runtime rendering deterministic
- [ ] no renderer-specific lifecycle corruption

:contentReference[oaicite:25]{index=25}

---

## Renderer Infrastructure

- [ ] renderer resolution deterministic
- [ ] renderer dictionaries bootstrap-safe
- [ ] no descriptor-era fallback behavior remains
- [ ] runtime resources resolve canonically
- [ ] renderer semantics separated from gameplay semantics

Canonical rendering flow:

```text
Runtime Resource
→ RendererResolver
→ Renderer
→ Compose UI
```

:contentReference[oaicite:26]{index=26}
:contentReference[oaicite:27]{index=27}

---

# 6. Localization Checklist

## Localization Completeness

- [ ] Ukrainian localization complete
- [ ] English localization complete
- [ ] no mixed-language runtime states
- [ ] markdown help localized
- [ ] onboarding localized
- [ ] error messages localized
- [ ] recovery runtime localized
- [ ] statistics explanations localized
- [ ] publication metadata localized

Current localization state:

```text
partial
```

Target before publication:

```text
publication-safe localization coverage
```

:contentReference[oaicite:28]{index=28}
:contentReference[oaicite:29]{index=29}

---

# 7. Monetization Checklist

## Billing & Premium Runtime

- [ ] Google Billing integrated
- [ ] entitlement persistence verified
- [ ] runtime capability rematerialization stable
- [ ] FREE runtime validated
- [ ] PREMIUM runtime validated
- [ ] upgrade flow validated
- [ ] premium topology activation deterministic
- [ ] billing failure behavior supportable

Entitlement direction:

```text
Google Billing
→ entitlement state
→ capability rematerialization
→ runtime topology update
```

:contentReference[oaicite:30]{index=30}
:contentReference[oaicite:31]{index=31}

---

# 8. Store Publication Checklist

## Google Play Assets

- [ ] application icon finalized
- [ ] screenshots prepared
- [ ] feature graphics prepared
- [ ] store descriptions prepared
- [ ] short description prepared
- [ ] age rating completed
- [ ] privacy policy prepared
- [ ] support email configured
- [ ] support/contact infrastructure operational

:contentReference[oaicite:32]{index=32}

---

## Testing Pipeline

- [ ] internal testing configured
- [ ] closed testing configured
- [ ] release signing verified
- [ ] crash reporting verified
- [ ] upgrade path validated
- [ ] clean install validated
- [ ] reinstall behavior validated
- [ ] bootstrap determinism validated

---

# 9. Supportability Checklist

## Architectural Cleanup

- [ ] dead code removed
- [ ] deprecated runtime artifacts removed
- [ ] implicit relationships minimized
- [ ] lifecycle complexity bounded
- [ ] topology semantics explicit
- [ ] runtime ownership boundaries preserved
- [ ] synchronization rituals eliminated
- [ ] no hidden orchestration remnants remain

The project intentionally prioritizes:

```text
supportability-first publication stabilization
```

:contentReference[oaicite:33]{index=33}
:contentReference[oaicite:34]{index=34}
:contentReference[oaicite:35]{index=35}

---

# 10. Final Publication Gate

The release is considered publication-ready only if:

- [ ] runtime semantics are deterministic
- [ ] runtime topology is bounded
- [ ] gameplay analytics are truthful
- [ ] profile ownership semantics are stable
- [ ] telemetry is observational only
- [ ] localization is publication-safe
- [ ] monetization topology is stable
- [ ] production topology excludes TESTER
- [ ] recovery runtime is supportable
- [ ] rendering is stable across device categories
- [ ] persistence integrity verified
- [ ] support infrastructure operational

---

# Final Strategic Principle

The first public release intentionally optimizes for:

```text
stable deployable truthful runtime
```

NOT:

```text
maximum feature expansion
```

The publication strategy intentionally prioritizes:

* deterministic behavior,
* supportability,
* bounded complexity,
* topology clarity,
* explainability,
* runtime correctness,
* deployment safety,
* long-term maintainability.

:contentReference[oaicite:36]{index=36}
:contentReference[oaicite:37]{index=37}
