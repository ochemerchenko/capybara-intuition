# ARCHITECTURE

## Version: 0.17

## Last Updated: 2026-05-28


## Purpose

The project is a domain-oriented interactive gameplay system built around deterministic event processing, bounded complexity, and explicit responsibility separation.

The architecture intentionally prioritizes:

* semantic clarity,
* bounded operational complexity,
* deterministic gameplay behavior,
* supportability,
* future-compatible extensibility without speculative overengineering.

The system is implementation-grounded and describes the actual runtime structure of the application rather than an abstract or idealized architecture model.

---

# Architectural Decomposition

The architecture uses two independent decomposition axes:

1. Technical layering.
2. Semantic domain decomposition.

These axes are orthogonal and coexist simultaneously.

Domains span multiple technical layers.

The architecture is therefore matrix-structured rather than strictly linear.

---

# Technical Layers

## UI Layer

Responsible only for UI materialization and user interaction rendering.

The UI layer:

* displays runtime data,
* forwards user interaction events,
* does not own gameplay semantics,
* does not own persistence semantics,
* does not orchestrate gameplay flow.

UI components are projection surfaces rather than authoritative system components.

---

## Control / Data Flow Layer

Responsible for:

* event propagation,
* transition execution,
* mutable gameplay/process representations,
* gameplay coordination,
* runtime continuation flow.

This layer contains:

* ViewModels,
* routers,
* handlers,
* mutable runtime objects,
* gameplay coordination structures.

The control/data-flow layer acts as middleware between UI and persistence.

---

## Persistence Layer

Responsible for:

* historical observation storage,
* operational data storage,
* structured retrieval,
* export support,
* reconstruction support.

The persistence layer includes:

* repositories,
* DAO objects,
* persistence entities,
* relational storage infrastructure.

Persistence does not orchestrate gameplay behavior.

Persistence does not act as a gameplay authority.

---

# Domain Decomposition

The project uses domain-oriented decomposition.

Domains exist as semantic responsibility boundaries rather than isolated enterprise-DDD runtime silos.

The architecture intentionally avoids:

* ritual enterprise-DDD isolation,
* abstraction-for-abstraction patterns,
* artificial DTO propagation,
* unnecessary synchronization layers,
* speculative orchestration structures.

Domain decomposition exists to preserve semantic clarity and bounded responsibility ownership.

Examples of domains include:

* Profile,
* Gameplay,
* Statistics,
* Topology,
* Capability/Monetization,
* Support infrastructure.

Domains may span multiple technical layers simultaneously.

---

# Event-Driven Transition Architecture

Gameplay behavior emerges from deterministic event processing.

The architecture explicitly models:

* events,
* transition handlers,
* event propagation,
* runtime context evolution.

The architecture intentionally avoids:

* formal FSM orchestration systems,
* reducer-centric runtime ownership,
* centralized orchestration engines.

---

## Events

Gameplay transitions are initiated through explicitly modeled events.

Events represent:

* user interactions,
* runtime continuation triggers,
* gameplay flow transitions,
* system-level operational actions.

Events are deterministic transition triggers.

---

## Router

The router acts as a transition dispatcher.

The router:

* receives events,
* delegates transition handling,
* produces continuation events when required.

The router does not:

* own gameplay truth,
* own persistence,
* act as a centralized orchestration engine,
* implement a formal FSM graph.

---

## ViewModel

ViewModels coordinate event continuation and cross-layer interaction.

ViewModels:

* exchange events with routers,
* coordinate mutable runtime objects,
* isolate UI from persistence infrastructure,
* act as integration/control-flow coordinators.

ViewModels are not reducers.

The architecture intentionally avoids reducer-style centralized runtime mutation.

---

# Runtime Representations

The term "runtime" refers to mutable live-process representations inside the control/data-flow layer.

Runtime is not a standalone architectural layer.

Examples:

* runtime session,
* runtime round,
* runtime profile.

Runtime objects:

* are mutable,
* participate in gameplay flow,
* evolve through event processing,
* exist independently from persistence entities.

---

# GameContext

`GameContext` is a gameplay coordination aggregate.

`GameContext` is not:

* a formal FSM state,
* a global authoritative state,
* a reducer-owned state container.

`GameContext` represents the current mutable gameplay/process coordination context.

It aggregates:

* current session,
* current round,
* current gameplay coordination data.

The architecture intentionally avoids fake FSM semantics.

---

# Persistence Semantics

Persistence semantics are intentionally split into two categories.

---

## Operational Persistence

Operational persistence stores mutable operational context data.

Examples:

* Profile,
* preferences,
* selected mode,
* current level,
* operational configuration.

Operational persistent objects:

* may mutate,
* participate in gameplay context formation,
* survive application restarts,
* provide operational continuity.

---

## Observational Persistence

Observational persistence stores append-only historical gameplay observations.

Examples:

* sessions,
* rounds,
* round_cards,
* user interaction observations,
* timestamps,
* gameplay outcomes.

Observational records:

* are immutable after persistence,
* preserve historical gameplay truth,
* support deterministic reconstruction,
* support analytics and telemetry export.

The architecture intentionally avoids reinterpretation or mutation of historical gameplay observations after persistence.

---

# Truth Ownership

The system does not use a single global "source of truth".

Truth ownership is contextual and domain-scoped.

Examples:

* gameplay domains own gameplay semantics,
* persistence owns historical observations,
* runtime objects own mutable process context,
* statistics own analytical reconstruction,
* telemetry/export infrastructure owns external projections.

The architecture intentionally avoids centralized truth absolutism.

---

# Topology

Topology describes abstract spatial occupancy structure.

Topology is:

* renderer-independent,
* gameplay-domain-owned,
* projection-agnostic,
* future-compatible.

Topology is not:

* a UI layout system,
* a screen adaptation system,
* a geometry engine,
* a renderer implementation.

---

## Topology Cells

Topology cells describe occupancy structure through spatial descriptors and future-compatible metadata.

Topology intentionally preserves:

* coordinate semantics,
* sequencing semantics,
* occupancy semantics,
* projection flexibility.

The current production implementation uses matrix/table-based projection semantics.

This is an implementation choice rather than an architectural invariant.

---

# Renderers

Renderers provide topology projection/materialization strategies.

A renderer:

* interprets topology coordinates,
* projects topology structures into UI space,
* materializes spatial relationships.

A renderer does not:

* alter gameplay semantics,
* redefine topology semantics,
* own gameplay structure.

Topology and renderer form a valid materialization pair.

Renderer metadata may participate in:

* persistence,
* telemetry,
* export compatibility,
* reconstruction support.

The architecture intentionally keeps topology renderer-independent.

---

# Capability System

The capability system is intentionally minimal and bounded.

Capabilities exist only for:

* FREE vs PREMIUM licensing,
* PREMIUM-only gameplay modes,
* child profile availability,
* TEST-only infrastructure availability.

Capabilities are operational availability constraints.

Capabilities are not:

* gameplay semantics,
* topology semantics,
* orchestration systems,
* dynamic policy engines.

The architecture intentionally avoids capability-system overengineering.

---

# Statistics and Telemetry

Statistics are reconstructive and observational.

Statistics operate on persisted historical observations.

Telemetry and Firebase export infrastructure operate as external observational projection systems.

Telemetry does not:

* orchestrate gameplay,
* mutate gameplay flow,
* redefine gameplay semantics.

---

# Architectural Constraints

The architecture intentionally avoids:

* centralized orchestration engines,
* speculative abstraction layers,
* artificial enterprise-DDD isolation,
* reducer-centric runtime ownership,
* fake FSM semantics,
* uncontrolled implicit coupling,
* semantic duplication,
* abstraction-for-abstraction engineering.

The architecture intentionally favors:

* explicit semantics,
* bounded complexity,
* deterministic transitions,
* operational clarity,
* supportability,
* future-compatible extensibility,
* implementation-grounded design.

---

# Current Production Scope

The current production implementation intentionally remains bounded.

Current production scope includes:

* matrix-based topology rendering,
* deterministic event-driven gameplay flow,
* bounded capability system,
* observational persistence,
* local statistics,
* Firebase export infrastructure,
* FREE/PREMIUM operational separation.

Future extensibility is preserved without introducing speculative runtime complexity into the current implementation.

