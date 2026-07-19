# ARCHITECTURE

## Version: 0.17

## Last Updated: 2026-06-19

---

# Purpose

This document describes the high-level architectural principles of the Intuition project.

The architecture prioritizes:

* explicit ownership,
* semantic clarity,
* bounded complexity,
* deterministic behavior,
* implementation-grounded design.

The architecture intentionally avoids accidental coupling and ambiguous responsibility ownership.

---

# Architectural Axes

The project is decomposed along three independent axes:

* technical layers,
* semantic domains,
* runtime composition.

These axes are intentionally orthogonal.

A domain may span multiple technical layers.

A composition module may coordinate multiple layers belonging to the same domain.

---

# Technical Layers

The project uses the following technical layers:

```text
Presentation
    →

Domain
    →

Data
    →

Persistence
```

Responsibilities:

Presentation

* UI rendering
* user interaction
* navigation
* visual adaptation

Domain

* business rules
* gameplay logic
* analytics logic
* interpretation logic

Data

* repositories
* mappers
* projections
* persistence coordination

Persistence

* Room entities
* DAOs
* database relations

````

Technical layers do not define ownership.

Ownership is defined by domains.

---

# Runtime Composition

Application assembly is performed through an explicit composition root.

```text
AppContainer
````

owns runtime composition.

AppContainer is responsible for:

* database creation,
* infrastructure assembly,
* module assembly,
* bootstrap coordination.

AppContainer does not own gameplay logic.

AppContainer does not own profile logic.

AppContainer does not own statistics logic.

Its responsibility is runtime composition only.

---

# Composition Modules

The current runtime composition consists of:

```text
InstallationModule
ProfileModule
GameplayModule
StatisticsModule
TesterModule
```

Modules localize dependency ownership.

Modules may own:

* repositories,
* runtime services,
* coordinators,
* ViewModels,
* infrastructure helpers.

Modules exist to reduce cross-domain dependency leakage.

---

# Domain Decomposition

The project currently consists of the following primary domains:

```text
Installation

Profile

Gameplay

Statistics

Tester Infrastructure
```

Each domain owns its own truth.

No global source of truth exists.

Truth ownership is contextual.

---

# Installation Domain

Installation owns:

* installation identity,
* installation initialization,
* installation metadata,
* installation persistence.

Installation provides a stable application identity.

Installation does not participate in gameplay semantics.

Installation does not participate in profile semantics.

---

# Profile Domain

Profile owns:

* profile identity,
* profile type,
* profile security state,
* profile capabilities,
* profile preferences,
* gameplay configuration selection.

Profile is the owner of user-specific configuration.

---

# Profile Subdomains

Profile currently contains the following logical subdomains:

```text
Profile Security

Profile Topology Library

Profile Resource Library
```

These subdomains support profile configuration ownership.

They do not own gameplay flow.

---

# Gameplay Configuration Ownership

Profile owns:

* selected topology,
* selected topology family,
* selected notebook,
* selected renderer,
* selected gameplay preferences.

Gameplay consumes these selections.

Gameplay does not own them.

---

# Gameplay Domain

Gameplay owns:

* gameplay flow,
* gameplay state transitions,
* gameplay truth,
* round lifecycle,
* session lifecycle.

Gameplay consumes configuration provided by Profile.

Gameplay does not own profile configuration.

---

# Topology Ownership

Topology semantics belong to Gameplay.

Topology selection belongs to Profile.

These responsibilities are intentionally separated.

```text
Profile
    →
Selects topology

Gameplay
    →
Uses topology
```

Topology is therefore not treated as an independent primary domain.

---

# Statistics Domain

Statistics owns:

* gameplay reconstruction,
* gameplay aggregation,
* analytical projections,
* trend estimation,
* insight generation.

Statistics derive meaning from observations.

Statistics do not own gameplay truth.

Statistics reconstruct gameplay history from persisted observations.

---

# Tester Infrastructure

Tester infrastructure exists solely to support:

* test environments,
* historical dataset generation,
* analytical demonstrations,
* support tooling.

Tester infrastructure is not part of production gameplay semantics.

---

# Ownership Principle

Every meaningful concept should have a single explicit owner.

Examples:

```text
Installation
    owns installation identity

Profile
    owns configuration

Gameplay
    owns gameplay truth

Statistics
    owns analytical interpretation
```

Ownership should always be explicit.

Shared ownership should be avoided.

---

# ViewModel Role

ViewModels belong to the presentation-control layer.

ViewModels coordinate:

* user interactions,
* repository access,
* navigation flow,
* state exposure.

ViewModels are not domain owners.

Domain ownership remains inside domains.

---

# Navigation

Navigation coordinates screen transitions.

Navigation does not own business state.

Navigation should remain independent from:

* gameplay truth,
* profile truth,
* analytical truth.

---

# Bootstrap

Bootstrap is responsible for initial system provisioning.

Bootstrap may:

* seed databases,
* create default profiles,
* initialize default resources,
* initialize topology libraries.

Bootstrap does not own runtime behavior.

Bootstrap executes initialization only.

---

# Text Ownership

User-visible text belongs to the domain that owns the underlying meaning.

Examples:

```text
Gameplay text
    →
Gameplay

Statistics insights
    →
Statistics

Profile configuration labels
    →
Profile

Companion phrases
    →
Emotional Feedback System
```

Text ownership must remain explicit to support future localization.

---

# Localization Principle

Within project documentation:

Localization always means:

```text
User-visible language localization
```

Examples:

* English
* Ukrainian
* German
* Polish

The term localization must not be used to describe responsibility ownership.

Responsibility ownership should instead be described using:

* ownership,
* ownership boundaries,
* ownership stabilization,
* ownership consolidation.

---

# Architectural Evolution Principle

New abstractions should only be introduced when ownership becomes unclear.

Ownership clarity is preferred over abstraction quantity.

The architecture favors:

* explicit ownership,
* semantic naming,
* bounded complexity,
* evolutionary refinement.

Every architectural change should improve ownership visibility.

