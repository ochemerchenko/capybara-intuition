# MATRIX_TOPOLOGY_RUNTIME

## Version

0.16

## Last Updated

2026-05-25

---

# Overview

This document defines the canonical matrix topology runtime model for the publication stabilization phase.

The topology runtime currently exists as:

```text
bounded deterministic matrix topology infrastructure
```

NOT:

```text
universal procedural topology engine
```

The topology system intentionally prioritizes:

* deterministic topology semantics,
* explicit topology ownership,
* bounded runtime complexity,
* supportability,
* publication-safe topology behavior,
* immutable topology identity,
* analytics-safe topology reconstruction,
* renderer-independent gameplay topology.

The topology runtime intentionally avoids:

* runtime topology generation,
* procedural topology synthesis,
* adaptive topology mutation,
* geometry-owned gameplay semantics,
* renderer-owned topology semantics,
* layout-driven runtime behavior,
* topology DSL infrastructure,
* universal topology abstraction completeness.

---

# Current Publication Boundary

Before first publication:

```text
ONLY MATRIX TOPOLOGIES EXIST
```

Current publication scope intentionally includes only:

* matrix topology,
* sparse matrix topology,
* blocked matrix cells,
* logical colspan/rowspan semantics,
* bounded topology catalogs,
* deterministic topology ordering.

The project intentionally postpones:

* sinus topologies,
* spiral topologies,
* procedural topology generation,
* curve-driven topology semantics,
* adaptive topology synthesis,
* runtime-generated topology mutation.

Future topology evolution MAY occur only after:

```text
stable public deployment
```

---

# Core Strategic Principle

The topology runtime intentionally treats topology as:

```text
canonical gameplay occupancy space
```

NOT:

```text
UI layout infrastructure
```

Topology defines:

* occupiable gameplay regions,
* deterministic ordering,
* logical topology coordinates,
* logical occupancy extent,
* blocked topology regions,
* runtime gameplay space.

Topology does NOT define:

* pixels,
* dp coordinates,
* adaptive layout behavior,
* Compose positioning,
* rendering geometry,
* renderer semantics.

---

# Topology Philosophy

The topology system intentionally optimizes for:

```text
bounded deterministic occupancy topology
```

instead of:

```text
continuous geometry modeling
```

The topology runtime intentionally prefers:

* discrete topology semantics,
* enumerable topology cells,
* immutable topology coordinates,
* finite topology catalogs,
* supportable runtime reconstruction.

The project intentionally avoids:

* arbitrary geometric runtime,
* continuous mathematical topology,
* procedural spatial synthesis,
* mutable topology identity,
* runtime geometry orchestration.

---

# Canonical Runtime Model

Current canonical runtime hierarchy:

```text
TopologySet
    → Topology
        → TopologyCell
```

---

# TopologySet

TopologySet represents:

```text
bounded collection of gameplay topologies
```

Responsibilities:

* topology grouping,
* topology catalog ownership,
* gameplay topology selection,
* deterministic topology availability.

TopologySet intentionally does NOT:

* generate topology,
* mutate topology coordinates,
* own rendering semantics,
* orchestrate runtime occupation.

---

# Topology

Topology represents:

```text
single gameplay occupancy topology
```

Topology owns:

* topology cells,
* topology ordering,
* occupancy structure,
* blocked regions,
* logical gameplay space.

Topology intentionally does NOT own:

* gameplay occupation,
* renderer projection,
* adaptive layout logic,
* runtime geometry mutation.

---

# TopologyCell

TopologyCell represents:

```text
canonical logical occupancy region
```

NOT:

```text
screen pixel coordinate
```

Current conceptual structure:

```kotlin
TopologyCell(

    index: Int,

    x: Int,
    y: Int,

    width: Int = 1,
    height: Int = 1,

    blocked: Boolean = false
)
```

Current semantics:

* x/y are logical matrix topology coordinates,
* width/height are logical topology extent,
* coordinates are immutable,
* coordinates are canonical,
* coordinates are deterministic,
* coordinates are renderer-independent.

---

# Canonical Coordinate Semantics

Current topology coordinates:

```text
x, y
```

represent:

```text
logical matrix topology coordinates
```

NOT:

* pixels,
* dp,
* adaptive UI coordinates,
* renderer projection coordinates,
* screen-space geometry.

Coordinates intentionally remain:

```text
immutable canonical topology identity
```

The topology runtime intentionally avoids:

* mutable topology coordinates,
* renderer-owned coordinates,
* adaptive coordinate mutation,
* runtime topology rewriting.

---

# Logical Topology Size

Current topology size semantics represent:

```text
logical occupancy extent
```

NOT:

```text
screen render size
```

Examples:

* colspan semantics,
* rowspan semantics,
* multi-cell occupancy,
* logical topology region expansion.

Conceptual example:

```html
<td colspan=2 />
```

Meaning:

```text
single topology region occupies
2 logical topology cells
```

Logical size intentionally remains:

* topology-space only,
* renderer-independent,
* density-independent,
* analytics-safe.

---

# Blocked Topology Cells

Blocked topology cells represent:

```text
non-occupiable topology regions
```

NOT:

```text
temporarily empty runtime cells
```

Blocked cells intentionally exist as:

```text
canonical topology structure
```

Examples:

* sparse matrix topology,
* asymmetric topology,
* topology holes,
* unavailable occupancy regions.

Blocked topology semantics intentionally remain:

* explicit,
* deterministic,
* immutable,
* topology-owned.

---

# Occupation Semantics

Topology intentionally remains separated from:

```text
runtime occupation
```

Topology defines:

```text
what occupancy space exists
```

Runtime occupation defines:

```text
which gameplay element occupies topology space
```

Current runtime direction:

```text
Topology
→ occupiable regions

Gameplay Runtime
→ occupation of regions
```

The architecture intentionally avoids:

* topology-owned gameplay state,
* renderer-owned occupation,
* layout-driven gameplay occupation,
* mutable topology occupation semantics.

---

# Deterministic Ordering

Topology cells intentionally preserve:

```text
canonical deterministic ordering
```

This ordering supports:

* gameplay reconstruction,
* analytics reconstruction,
* deterministic replay,
* telemetry consistency,
* stable runtime semantics.

Topology ordering intentionally remains:

* explicit,
* enumerable,
* stable,
* serialization-safe.

---

# Matrix Topology Semantics

Current publication runtime supports only:

```text
matrix-derived topology
```

including:

* rectangular topology,
* sparse matrix topology,
* asymmetric occupancy structure,
* blocked occupancy regions,
* logical colspan/rowspan semantics.

Examples:

```text
((1,1), (1,2), (2,1))
```

and:

```text
((1,1), (2,1), (2,2))
```

represent distinct canonical matrix topologies.

The topology runtime intentionally avoids assuming:

* perfect rectangular occupancy,
* fully populated rows,
* symmetrical topology structure.

---

# Renderer Relationship

Topology intentionally remains separated from:

```text
render projection
```

Topology defines:

```text
canonical occupancy semantics
```

Renderer defines:

```text
visual projection of topology
```

Renderer MUST NOT:

* mutate topology coordinates,
* rewrite topology identity,
* generate topology cells,
* synthesize topology structure.

---

# Future Projection Evolution

Future renderers MAY later support:

* sinus projection,
* wave projection,
* spiral projection,
* adaptive visual projection.

Such evolution MUST preserve:

```text
canonical immutable topology semantics
```

Future rendering evolution MUST operate through:

```text
canonical topology
→ visual projection transform
→ render coordinates
```

NOT through:

```text
runtime topology mutation
```

---

# Layout Separation

The topology runtime intentionally distinguishes between:

---

# Topology

Represents:

```text
gameplay occupancy semantics
```

---

# Layout

Represents:

```text
visual UI arrangement
```

The project intentionally avoids:

* layout-owned gameplay semantics,
* adaptive layout gameplay mutation,
* renderer-coupled topology identity,
* UI-driven topology reconstruction.

---

# Legacy Infrastructure Status

Reducer-era infrastructure including:

* LayoutConfig,
* LayoutPosition,
* layout mutation semantics,
* reducer-owned positioning logic

is now considered:

```text
transitional legacy infrastructure
```

These systems are NOT considered:

```text
canonical topology runtime
```

Current strategic direction favors:

```text
TopologySet
→ Topology
→ TopologyCell
```

as the canonical gameplay topology model.

---

# Supportability Constraints

The topology runtime intentionally prioritizes:

```text
supportability-first topology stabilization
```

Current priorities:

* deterministic topology semantics,
* immutable topology identity,
* bounded topology catalogs,
* explicit occupancy structure,
* topology reconstruction safety,
* renderer-independent runtime behavior.

The project intentionally avoids:

* generalized topology frameworks,
* procedural topology infrastructure,
* runtime topology orchestration,
* adaptive topology mutation,
* geometry-owned gameplay semantics.

---

# Publication-Oriented Runtime Strategy

The first public release intentionally optimizes for:

```text
stable deterministic matrix topology runtime
```

NOT:

```text
maximum topology abstraction completeness
```

The current topology strategy intentionally prioritizes:

* bounded runtime complexity,
* deterministic reconstruction,
* supportability,
* immutable topology semantics,
* renderer separation,
* topology clarity,
* deployment-safe runtime behavior.

---

# Strategic Topology Principle

The topology runtime intentionally optimizes for:

```text
bounded canonical matrix occupancy semantics
```

NOT:

```text
universal procedural topology generation
```

The publication release should prioritize:

* deterministic topology behavior,
* immutable topology identity,
* supportable topology semantics,
* renderer-independent gameplay runtime,
* analytics-safe reconstruction,
* bounded topology complexity,
* publication-safe runtime stabilization.

