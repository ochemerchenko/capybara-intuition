# GRAPH_TEST_DATA_GENERATION

## Version

0.16

## Last Updated

2026-05-25

---

# Overview

This document defines the bounded graph dataset generation infrastructure used for statistics validation, rendering validation, telemetry validation, and TESTER runtime analytics generation.

The graph test data generation subsystem intentionally exists as:

```text
deterministic analytics validation infrastructure
```

NOT:

```text
fake gameplay simulation
```

The subsystem currently supports:

* statistics graph generation,
* moving-average validation,
* compression validation,
* rendering stress testing,
* telemetry validation,
* deterministic reconstruction validation,
* low/high-density analytics validation,
* TESTER runtime graph population.

The subsystem intentionally avoids:

* synthetic gameplay semantics,
* fake behavioral manipulation,
* hidden statistics mutation,
* analytics fabrication,
* non-deterministic dataset generation,
* runtime-specific analytics shortcuts.

---

# Strategic Purpose

The graph dataset generation subsystem exists primarily to support:

* statistics runtime validation,
* chart rendering validation,
* graph compression validation,
* moving-average validation,
* telemetry export validation,
* publication-safe analytics stabilization,
* deterministic reconstruction testing.

The project intentionally prioritizes:

```text
truthful bounded analytics generation
```

instead of:

```text
artificially impressive fake statistics
```

---

# Current Strategic Context

The project is currently in:

```text
publication stabilization phase
```

User-facing functionality is considered:

```text
feature-complete
```

The graph dataset subsystem now primarily supports:

* publication-safe analytics validation,
* graph rendering stabilization,
* telemetry correctness verification,
* TESTER runtime topology,
* rendering stress validation,
* statistics reconstruction verification.

Remaining work currently includes:

* restoration of automated dataset generation,
* restoration of graph generation flows,
* rendering stress verification,
* telemetry validation.

---

# Core Principle

Generated datasets MUST preserve:

* canonical persistence semantics,
* deterministic runtime reconstruction,
* truthful gameplay semantics,
* valid relational coordinates,
* analytics correctness,
* bounded supportability.

Generated datasets MUST NOT:

* bypass persistence integrity,
* fabricate impossible runtime states,
* generate invalid profile topology,
* violate capability topology,
* inject hidden analytics semantics.

---

# Dataset Generation Philosophy

The subsystem intentionally treats graph generation as:

```text
runtime-scale observational validation
```

NOT:

```text
gameplay cheating infrastructure
```

The project intentionally avoids:

* direct graph fabrication,
* renderer-only statistics injection,
* UI-level fake analytics,
* non-persisted synthetic statistics,
* hidden analytics mutation layers.

Generated statistics MUST continue to represent:

```text
what happened
```

even inside TESTER topology.

---

# Canonical Data Flow

Current intended flow:

```text
Dataset Generator
→ canonical runtime creation
→ sessions
→ rounds
→ persistence
→ statistics reconstruction
→ graph rendering
```

NOT:

```text
graph UI
→ injected fake chart data
```

This preserves:

* deterministic reconstruction,
* analytics correctness,
* telemetry correctness,
* rendering truthfulness,
* supportability.

---

# Runtime Relationship

Graph dataset generation currently operates through:

```text
TESTER runtime topology
```

The TESTER runtime intentionally exists for:

* analytics validation,
* graph generation,
* rendering validation,
* telemetry validation,
* deterministic runtime reconstruction testing.

Graph generation intentionally remains:

```text
topology-aware
```

instead of:

```text
hidden developer bypass infrastructure
```

---

# Statistics Validation Goals

Generated datasets currently support validation of:

* activity graphs,
* density graphs,
* moving averages,
* graph compression,
* baseline rendering,
* viewport scaling,
* uncertainty rendering,
* rendering density stability.

---

# Supported Validation Scenarios

---

# Low-Density Validation

Purpose:

* early-user analytics validation,
* sparse graph rendering,
* low-session moving averages,
* bootstrap stabilization validation.

Typical dataset characteristics:

* 5–20 sessions,
* irregular spacing,
* unstable trends,
* sparse density points.

---

# Mid-Density Validation

Purpose:

* normal-user statistics behavior,
* compression transition validation,
* trend readability validation.

Typical dataset characteristics:

* 50–200 sessions,
* realistic variability,
* readable trends,
* moderate compression.

---

# High-Density Validation

Purpose:

* rendering stress testing,
* compression validation,
* viewport stability,
* performance verification.

Typical dataset characteristics:

* 500–5000+ sessions,
* dense graph projections,
* heavy compression scenarios,
* moving-average stress validation.

---

# Longitudinal Validation

Purpose:

* timeline continuity validation,
* activity graph continuity,
* missing-day reconstruction validation,
* long-term trend rendering.

Typical characteristics:

* multi-month session spread,
* irregular temporal gaps,
* realistic engagement variability.

---

# Deterministic Reconstruction Principle

Generated datasets MUST remain compatible with:

```text
deterministic runtime reconstruction
```

Generated records must preserve:

* valid sessions,
* valid rounds,
* canonical perceptual coordinates,
* valid gameplay modes,
* valid timestamps,
* valid relational ownership.

Canonical coordinate system:

```text
(notebookId, resourceId)
```

must remain valid in all generated datasets.

---

# Gameplay Semantics Validation

Generated datasets intentionally support validation of:

* AFTER gameplay,
* BEFORE gameplay,
* HYBRID gameplay.

Generation MUST NOT:

* fabricate unsupported gameplay semantics,
* bypass capability topology,
* generate impossible runtime states.

---

# Capability Relationship

Dataset generation MUST remain compatible with:

```kotlin
RuntimeCapabilities
```

The generator MUST respect:

* topology collapse,
* TEST/PRODUCTION separation,
* FREE/PREMIUM runtime composition,
* TESTER topology existence.

The project intentionally avoids:

```text
hidden capability bypasses
```

during analytics generation.

---

# TESTER Runtime Integration

The graph dataset subsystem currently integrates with:

```text
TESTER runtime topology
```

Purpose:

* graph rendering validation,
* telemetry validation,
* statistics validation,
* topology verification,
* runtime reconstruction testing.

The TESTER runtime intentionally remains:

```text
non-production validation infrastructure
```

---

# Rendering Validation Goals

Generated datasets currently validate:

* graph readability,
* chart scaling,
* viewport stability,
* moving-average rendering,
* compression rendering,
* small-screen rendering,
* tablet rendering,
* rendering density limits.

The subsystem intentionally supports:

```text
realistic rendering stress
```

instead of:

```text
synthetic visual demos
```

---

# Compression Validation

Graph generation intentionally validates:

* bucket aggregation,
* readability preservation,
* spike smoothing,
* rendering density reduction,
* moving-average post-compression correctness.

Compression validation currently targets:

```text
≈ 80 graph points
```

after adaptive compression.

---

# Telemetry Validation

Generated datasets support validation of:

* Firebase export,
* analytics reconstruction,
* telemetry correctness,
* deterministic export semantics,
* statistics reconstruction integrity.

Telemetry MUST remain:

```text
observational only
```

even during TESTER dataset generation.

---

# Persistence Constraints

Generated datasets MUST use:

* canonical persistence,
* valid foreign keys,
* valid profile ownership,
* valid session ownership,
* valid runtime coordinates.

The generator MUST NOT:

* bypass repositories,
* inject invalid persistence,
* fabricate orphaned relations,
* bypass integrity validation.

---

# Dataset Realism Goals

Generated datasets should preserve:

* realistic success variability,
* realistic timing distribution,
* realistic session density,
* realistic progression distribution,
* realistic mode usage variability.

The project intentionally avoids:

```text
perfect artificial statistics
```

because unrealistic datasets reduce:

* rendering trustworthiness,
* analytics validation quality,
* compression realism,
* telemetry usefulness.

---

# Performance Constraints

Dataset generation infrastructure must remain:

* bounded,
* deterministic,
* supportable,
* reproducible,
* publication-safe.

The subsystem intentionally avoids:

* giant simulation engines,
* probabilistic AI generation,
* runtime orchestration systems,
* distributed generation pipelines.

---

# Current Remaining Work

Current remaining graph-generation-related work:

* restore automated dataset generation,
* restore TESTER graph flows,
* validate large-scale rendering,
* validate compression correctness,
* validate telemetry export behavior,
* validate longitudinal graph rendering,
* validate cross-device chart stability.

---

# QA Checklist

---

# Dataset Integrity

* [ ] generated sessions valid
* [ ] generated rounds valid
* [ ] foreign keys valid
* [ ] canonical coordinates valid
* [ ] gameplay modes valid
* [ ] timestamps deterministic
* [ ] reconstruction deterministic

---

# Analytics Validation

* [ ] moving averages validated
* [ ] compression validated
* [ ] baseline validated
* [ ] uncertainty rendering validated
* [ ] graph readability validated
* [ ] low-density rendering validated
* [ ] high-density rendering validated

---

# Rendering Validation

* [ ] small-screen rendering validated
* [ ] tablet rendering validated
* [ ] viewport stability validated
* [ ] graph scaling validated
* [ ] rendering performance acceptable
* [ ] chart interaction stable

---

# Telemetry Validation

* [ ] Firebase export deterministic
* [ ] telemetry observational only
* [ ] export semantics canonical
* [ ] analytics reconstruction stable

---

# Publication Safety Validation

* [ ] TESTER absent from production topology
* [ ] no hidden graph generation runtime
* [ ] no production analytics corruption
* [ ] no fake production statistics injection

---

# Strategic Principle

The graph dataset generation subsystem intentionally optimizes for:

```text
truthful deterministic analytics validation
```

NOT:

```text
synthetic fake statistics generation
```

The subsystem should preserve:

* deterministic reconstruction,
* supportability,
* analytics correctness,
* rendering realism,
* topology safety,
* publication-safe runtime behavior.

