# GAMEPLAY_MODEL

## Version: 0.17

## Last Updated: 2026-05-28



## Purpose

This document describes gameplay semantics, gameplay flow, gameplay entities, and gameplay operational behavior.

The document intentionally describes:

* what gameplay is from the user perspective,
* how rounds function,
* how gameplay context is formed,
* how gameplay results are determined,
* how gameplay observations are persisted.

The document does not describe:

* architectural decomposition,
* infrastructure implementation details,
* renderer internals,
* persistence implementation details,
* emotional reinforcement philosophy,
* educational or cognitive goals,
* research or analytical systems outside the application.

Architectural concerns are described in `ARCHITECTURE.md`.

---

# Gameplay Overview

Gameplay consists of repeated rounds.

During a round:

* the user observes selectable interaction targets,
* the user selects one target,
* the system independently selects one target,
* the selections are compared,
* the result is exposed as WIN or LOSE.

After result exposure:

* session indicators are updated,
* emotional feedback may be displayed,
* gameplay pauses.

The next round starts only after explicit user interaction.

Gameplay does not auto-advance.

---

# Gameplay Structure

The gameplay model consists of:

* Profiles,
* Sessions,
* Rounds,
* Resources,
* Topologies,
* Renderers,
* Gameplay observations.

---

# Profiles

Profiles provide gameplay configuration.

Profiles define:

* gameplay mode,
* selected topology,
* selected renderer,
* selected notebook,
* selected level,
* operational gameplay configuration,
* capability availability.

Profiles are operationally mutable.

Gameplay configuration changes are legitimate profile behavior.

---

## Child Profiles

Child profiles:

* may participate in gameplay independently,
* may accumulate independent gameplay observations,
* remain operationally owned by parent profiles.

Parent profiles remain authoritative for:

* operational access,
* monetization ownership,
* capability ownership.

---

# Sessions

A session represents a sequence of rounds played under stable gameplay configuration.

A session preserves:

* gameplay continuity,
* gameplay configuration linkage,
* gameplay observation grouping.

The system simultaneously uses:

* runtime sessions,
* persistent sessions.

---

## Runtime Session

Runtime sessions:

* are mutable,
* store current gameplay context,
* participate in active gameplay flow,
* evolve during gameplay execution.

Runtime sessions exist only during gameplay execution.

---

## Persistent Session

Persistent sessions:

* aggregate persisted gameplay observations,
* preserve gameplay configuration linkage,
* preserve historical gameplay continuity.

Persistent sessions are immutable after persistence.

---

# Rounds

A round is the primary gameplay unit.

A round represents one complete gameplay interaction cycle.

A round includes:

* interaction target presentation,
* user selection,
* system selection,
* result determination,
* result exposure,
* result persistence.

A round is not complete until:

* the result is determined,
* the result is exposed,
* the result is persisted.

---

# Round Result

A round result is determined through comparison of:

* user selection,
* system selection.

The system does not define:

* objectively correct answers,
* predefined valid targets,
* canonical solutions.

WIN means:

```text
user selection == system selection
```

LOSE means:

```text
user selection != system selection
```

Round outcomes are selection-matching results rather than correctness evaluations.

---

# Gameplay Modes

The current production implementation supports:

* AFTER,
* BEFORE.

No other gameplay modes currently exist.

---

## AFTER Mode

In AFTER mode:

1. the system independently selects a target,
2. selectable targets are displayed,
3. the user independently selects a target,
4. selections are compared,
5. the result is exposed.

The user does not know the system selection before result exposure.

---

## BEFORE Mode

In BEFORE mode:

1. selectable targets are displayed,
2. the user independently selects a target,
3. the system independently selects a target,
4. selections are compared,
5. the result is exposed.

The system does not know the user selection before system selection.

---

# Level

Level defines ONLY:

* the number of simultaneously projected selectable resources.

Level does not define:

* gameplay progression,
* gameplay difficulty,
* topology density,
* renderer behavior,
* spatial projection semantics.

Renderer adaptation behavior may depend on level indirectly through the number of projected interaction targets.

---

# Resources

Resources are visual gameplay artifacts used during gameplay projection.

A resource may be:

* a color,
* a drawable image.

Resources are grouped into notebooks.

During round formation:

* a notebook is selected,
* resources are randomly selected from the notebook,
* the number of selected resources equals the current level value.

Resources participate in gameplay projection through interaction targets.

---

# Interaction Targets

The current production implementation uses BetButtons as gameplay interaction targets.

BetButtons:

* display projected resources,
* allow user resource selection,
* act as selectable gameplay surfaces.

Gameplay semantics do not depend on Android UI button semantics.

BetButtons are current production interaction materialization.

---

# Topology

Topology defines abstract spatial relationships between interaction targets.

Topology itself is not directly renderable.

Topology describes:

* relative placement structure,
* occupancy structure,
* interaction target positioning relationships.

Topology requires renderer materialization to become visible to the user.

---

# Renderers

Renderers materialize topology into visible gameplay structures.

Renderers:

* interpret topology descriptions,
* adapt topology to actual display environments,
* project interaction targets into visible UI structures.

The current production implementation uses:

* BetButton renderers,
* topology materialization renderers.

Renderers do not alter gameplay rules.

---

# Gameplay Flow

The gameplay flow is deterministic.

Gameplay transitions include:

* round creation,
* system selection,
* user selection,
* result determination,
* result exposure,
* result persistence,
* explicit continuation trigger.

The next round starts only after explicit user interaction.

Gameplay continuation is intentionally user-driven.

---

# Gameplay Observations

Gameplay observations preserve:

* user selections,
* system selections,
* round results,
* timestamps,
* session linkage,
* profile linkage,
* topology linkage,
* renderer linkage,
* gameplay mode linkage.

Gameplay observations are append-oriented and reconstructable.

---

# Observational Persistence

Gameplay observations become immutable after persistence.

The system intentionally avoids:

* mutation of historical gameplay observations,
* reinterpretation of historical gameplay results,
* destructive gameplay rewriting.

Historical gameplay observations remain reconstructable.

---

# Statistics

Statistics provide user-visible gameplay activity and gameplay result summaries.

The current production implementation includes:

* session activity statistics,
* gameplay result statistics.

Statistics are reconstructive rather than authoritative.

Statistics derive from persisted gameplay observations.

---

# Session Indicators and Emotional Feedback

After round completion:

* gameplay indicators may be updated,
* emotional feedback may be displayed,
* session state visualization may change.

Emotional reinforcement semantics are intentionally described outside this document.

---

# Capability Participation

Capabilities participate only as gameplay availability constraints.

Capabilities currently control:

* FREE vs PREMIUM availability,
* BEFORE mode availability,
* child profile availability,
* TESTER operational availability.

Capabilities do not alter gameplay rules.

---

# Production Scope

The current production gameplay implementation intentionally remains bounded.

Current production scope includes:

* repeated round-based gameplay,
* matrix/table-oriented topology materialization,
* deterministic gameplay transitions,
* local statistics,
* Firebase export support,
* bounded capability system,
* explicit user-driven gameplay continuation.

Future extensibility is intentionally preserved without introducing speculative gameplay complexity into the current production implementation.

