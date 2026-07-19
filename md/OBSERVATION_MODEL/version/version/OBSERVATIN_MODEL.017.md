# OBSERVATIONAL_MODEL

## Version

0.17

## Last Updated

2026-05-28



## Purpose

This document describes observational semantics, observational persistence principles, reconstruction guarantees, telemetry semantics, and export-oriented observational structure.

The document intentionally describes:

* what gameplay observations are,
* how gameplay observations are persisted,
* how gameplay observations remain reconstructable,
* how observational linkage is preserved,
* how observational exports are structured.

The document does not describe:

* gameplay mechanics,
* gameplay flow semantics,
* architectural decomposition,
* emotional reinforcement systems,
* educational or cognitive interpretation,
* external research systems.

Gameplay semantics are described in `GAMEPLAY_MODEL.md`.

Architectural semantics are described in `ARCHITECTURE.md`.

---

# Observational Philosophy

The project acts as:

* gameplay system,
* gameplay observation producer,
* observational export source.

The project intentionally preserves:

* observational completeness,
* reconstruction capability,
* append-oriented persistence,
* observational linkage continuity.

The system intentionally avoids:

* destructive historical rewriting,
* reinterpretation of persisted gameplay observations,
* mutable historical gameplay truth.

---

# Observational Structure

The observational model is hierarchical.

The current observational hierarchy is:

```text id="yqv9yq"
installation
→ profiles
→ sessions
→ rounds
→ round_cards
```

Each level preserves:

* observational continuity,
* contextual linkage,
* reconstruction support.

---

# Installations

Installations represent observational environment boundaries.

Installations preserve:

* platform information,
* platform version,
* manufacturer,
* device model,
* device class,
* screen bucket,
* locale,
* timezone,
* application version,
* distribution channel,
* installation timestamps.

Installations support:

* environment reconstruction,
* device-level aggregation,
* distribution analysis,
* deployment analysis,
* observational partitioning.

Installations participate in observational exports.

Installations do not participate directly in gameplay mechanics.

---

# Profiles

Profiles represent mutable gameplay configuration owners.

Profiles preserve:

* gameplay preferences,
* renderer preferences,
* topology preferences,
* notebook preferences,
* level configuration,
* capability availability.

Profiles participate in observational linkage through:

* session ownership,
* gameplay configuration continuity,
* installation linkage.

Profiles are operationally mutable.

---

# Sessions

Sessions represent gameplay observation aggregation units.

A session preserves:

* gameplay continuity,
* gameplay context continuity,
* observational grouping,
* gameplay configuration snapshots.

Sessions preserve gameplay context including:

* gameplay mode,
* level,
* notebook,
* renderer,
* mask,
* topology.

Sessions simultaneously support:

* gameplay reconstruction,
* telemetry reconstruction,
* export reconstruction,
* future analytical processing.

---

# Runtime and Persistent Sessions

The system simultaneously uses:

* runtime sessions,
* persistent sessions.

Runtime sessions:

* are mutable,
* participate in active gameplay execution,
* store current gameplay context.

Persistent sessions:

* preserve historical gameplay continuity,
* aggregate persisted gameplay observations,
* remain immutable after persistence.

---

# Rounds

Rounds represent atomic completed gameplay observations.

A round observation becomes complete only after:

* user selection,
* system selection,
* result determination,
* result exposure,
* persistence completion.

A round preserves:

* gameplay mode,
* round type,
* level,
* timestamps,
* result,
* interaction timing observations.

Rounds remain append-oriented and immutable after persistence.

---

# Round Cards

Round cards preserve the complete projected interaction set for a round.

Each round card preserves:

* projected resource linkage,
* topology placement,
* system-selected marker,
* user-selected marker.

Round cards preserve:

* interaction target structure,
* gameplay projection structure,
* spatial reconstruction support.

Round cards intentionally preserve sufficient information for:

* gameplay reconstruction,
* export reconstruction,
* telemetry reconstruction,
* future analytical processing.

---

# Selection Semantics

The gameplay model does not define:

* objectively correct answers,
* canonical valid targets,
* predefined gameplay truth.

Gameplay results are determined through comparison of:

* user selection,
* system selection.

Observational persistence preserves:

* user-selected targets,
* system-selected targets,
* round result.

The persistence layer intentionally avoids reinterpretation of historical gameplay outcomes.

---

# Topology Participation

Topology participates in observational reconstruction.

Topology itself is not directly visible to the user.

Topology preserves:

* spatial relationship structure,
* occupancy structure,
* interaction target placement structure.

Topology observations support:

* gameplay reconstruction,
* projection reconstruction,
* telemetry reconstruction,
* analytical reconstruction.

---

# Renderer Participation

Renderers participate in observational reconstruction through gameplay materialization linkage.

Renderer observations preserve:

* gameplay projection context,
* topology materialization compatibility,
* projection reconstruction support.

Renderers do not alter gameplay semantics.

Renderer observations remain contextual rather than gameplay-authoritative.

---

# Resource and Notebook Participation

Resources participate in observational reconstruction as projected gameplay artifacts.

Resources currently exist primarily as:

* colors,
* drawable images.

Notebooks group resources into gameplay resource collections.

Resource and notebook observations support:

* gameplay reconstruction,
* export reconstruction,
* analytical reconstruction.

Notebook organization semantics remain implementation-flexible.

---

# Observational Persistence

Observational persistence is append-oriented.

Historical gameplay observations become immutable after persistence.

The system intentionally avoids:

* destructive historical mutation,
* historical reinterpretation,
* mutable historical gameplay truth.

Observational persistence intentionally prioritizes:

* reconstruction capability,
* historical fidelity,
* linkage continuity,
* export stability.

---

# Reconstruction Guarantees

Persisted observations should remain sufficient for:

* gameplay reconstruction,
* statistics reconstruction,
* telemetry reconstruction,
* spreadsheet reconstruction,
* export reconstruction,
* future analytical processing.

The project intentionally prioritizes observational completeness over aggressive data minimization.

---

# Statistics Reconstruction

Statistics derive from persisted gameplay observations.

Statistics are reconstructive rather than authoritative.

Statistics may include:

* gameplay activity summaries,
* gameplay result summaries,
* temporal projections,
* smoothed graph projections.

Statistics intentionally avoid:

* predictive analytics,
* adaptive gameplay systems,
* behavioral manipulation systems.

---

# Telemetry Semantics

Telemetry infrastructure acts as observational export infrastructure.

Telemetry preserves:

* observational linkage,
* gameplay linkage,
* topology linkage,
* renderer linkage,
* installation linkage,
* profile linkage,
* session linkage,
* round linkage.

Telemetry infrastructure intentionally avoids:

* gameplay mutation,
* gameplay reinterpretation,
* authoritative gameplay ownership.

---

# Firebase Export Semantics

Firebase export acts as external observational projection.

Firebase payloads should remain:

* append-oriented,
* reconstruction-capable,
* version-compatible,
* linkage-preserving.

Firebase exports may include:

* installation observations,
* profile observations,
* session observations,
* round observations,
* round card observations,
* topology metadata,
* renderer metadata,
* notebook metadata,
* resource metadata.

Export compatibility between application versions should be preserved whenever reasonably possible.

---

# Version Compatibility

Observational exports should remain sufficiently stable to support:

* backward-compatible analysis,
* longitudinal aggregation,
* future analytical tooling,
* reconstruction continuity.

Schema evolution should avoid destructive observational incompatibility whenever reasonably possible.

---

# Security Residue

The current schema contains:

* `profile_security_state`,
* `security_events`.

These entities currently do not participate in active gameplay observational semantics.

They are currently considered inactive schema residue.

---

# Research Separation Principle

The application itself is not:

* a scientific analysis system,
* a research processing system,
* an analytical engine.

The application acts only as:

* gameplay system,
* gameplay observation producer,
* observational export source.

Research systems consuming exported observational datasets exist outside the application project boundary.

---

# Implementation-Fluid Areas

The following areas intentionally remain implementation-fluid:

* telemetry formatting,
* export formatting,
* graph rendering internals,
* graph smoothing,
* analytical projection internals,
* notebook organization strategies,
* renderer internals.

These areas may evolve without altering canonical observational semantics.

