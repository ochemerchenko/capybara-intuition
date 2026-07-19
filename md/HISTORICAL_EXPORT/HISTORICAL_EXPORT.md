# HISTORICAL_EXPORT.md

## Purpose

Historical Export is a long-term archival mechanism for observational data produced by the application.

Historical Export is not synchronization.

Historical Export is not backup.

Historical Export is not replication of the local database.

Its purpose is to deliver historical observational data from many devices into a centralized archive for future research and analysis.

The local database remains the source of truth for the application.

The historical archive becomes the source of truth for long-term analytical processing.

---

## Core Principles

### Historical Export stores analytical facts

Historical Export exports only information required for:

* analytical processing;
* historical reconstruction;
* environment reconstruction.

Information that can be reconstructed from other exported data should not be exported again.

### Historical Export is not a database snapshot

Export models are independent from Room entities.

Exported structures may contain only a subset of fields stored in local tables.

### Database is the source of truth

All export artifacts are built exclusively from persisted database state.

Historical Export must be reconstructible from the database alone.

Runtime state, UI state and in-memory objects are irrelevant.

---

## Export Categories

Historical Export consists of two logical categories:

### Reference Export

Exports slowly changing reference data.

### Daily Observation Export

Exports observational gameplay data for a completed day.

---

## Reference Export

Reference Export describes the environment in which gameplay observations were produced.

Reference Export is typically created:

* after installation;
* after application update resulting in a new appVersion.

Reference Export may be physically represented by one or multiple files.

Physical partitioning is an implementation detail.

Reference Export logically contains:

### Installation

Installation projection.

### Profile Projections

Exported profile types:

```text
HUMAN
CHILD
```

Only immutable analytical profile fields participate in export:

```text
id
type
installationId
createdAt
```

### Dictionaries

TopologyFamilies

Topologies

Notebooks

Resources

Renderers

Masks

and any other immutable reference entities required to reconstruct the gameplay environment.

---

## Profile Projection Rules

Historical Export does not export full Profile entities.

Only the following profile fields are considered analytically relevant:

```text
id
type
installationId
createdAt
```

Examples of fields that must not be exported:

```text
name
pinHash
currentLevel
preferredMode
activeNotebookName
rendererId
maskId
topologyFamilyId
activeTopologyCode
active
lastUsedAt
```

These values are either:

* mutable runtime state;
* reconstructible from other exported data;
* not analytically useful.

---

## Daily Observation Export

Daily Observation Export contains observational gameplay data.

One export package corresponds to one completed calendar day.

Current day is never exported.

Daily Observation Export contains:

### Sessions

GameSession records.

### Rounds

ObservedRound records.

### RoundCards

RoundCard records.

Together these entities allow complete reconstruction of:

* gameplay conditions;
* available choices;
* actual user choices;
* round outcomes.

---

## Environment Reconstruction

Gameplay environment must be reconstructible exclusively from exported data.

Session records must contain the environment snapshot required for reconstruction.

Examples:

```text
notebookId
topologyCode
rendererId
maskId
mode
level
```

must belong to Session data.

Profile must not be used as the source of gameplay environment reconstruction.

---

## Immutable References

Reference entities are immutable.

Published reference data must never be modified.

If behavior changes, a new reference entity must be created.

Example:

Topology definitions are immutable.

A topology must never be modified after publication.

A new topology must be created instead.

---

## Reference Export Creation

Historical Export does not require a separate registry of previously exported versions.

The export queue acts as the primary source of truth.

If a Reference Export artifact already exists in the queue, no additional artifact should normally be created.

If a duplicate Reference Export is created accidentally, this is not considered data corruption.

Duplicate creation merely produces redundant work and should be avoided when reasonably possible.

Historical correctness must never depend on duplicate-prevention mechanisms.

---

## Export Queue

Historical Export operates through an ordered export queue.

The queue is the primary source of truth for pending exports.

Queue ordering is based on artifact creation time.

Rule:

```text
First created
    →
First exported
```

No export item may bypass an older unconfirmed export item.

Historical Export intentionally minimizes auxiliary state outside the queue itself.

Whenever reasonably possible, export processing should be recoverable from queue contents alone.

---

## Export Artifact Lifecycle

The preferred implementation uses materialized export artifacts.

Export process:

```text
Database
    →
Export Builder
    →
Export Artifact
    →
Export Sender
    →
Firebase Storage
    →
Artifact Removal
```

Artifact lifecycle:

```text
CREATE
    →
STORE
    →
UPLOAD
    →
CONFIRM
    →
DELETE
```

---

## Export Storage

Export artifacts are stored locally before transmission.

Preferred storage location:

```text
context.noBackupFilesDir
```

Reasons:

* private application sandbox;
* inaccessible to other applications;
* excluded from Android backup;
* survives cache cleanup.

---

## Builder

Export Builder creates export artifacts.

For Daily Observation Export:

* completed days only;
* one package per day;
* package created only once.

Recommended trigger:

```text
First application start after date change.
```

Builder must remove any incomplete temporary artifacts left from interrupted execution.

Builder should rely primarily on export queue contents rather than external state registries.

---

## Sender

Export Sender is responsible for transmission.

Algorithm:

```text
Find oldest export artifact.

Upload.

If upload succeeds:
    delete artifact.

If upload fails:
    stop processing.
```

Sender always processes artifacts strictly in queue order.

---

## Delivery Confirmation

Successful upload is sufficient to confirm export delivery.

Hash validation may be added as an additional integrity check.

Historical Export is not a financial system.

Perfect transactional guarantees are not required.

---

## Firebase

Current transport implementation:

```text
Firebase Authentication
    Anonymous Authentication

Firebase Storage
```

Anonymous authentication is used solely for transport authorization.

Firebase identity is not part of analytical data.

Business identity is provided by:

```text
installationId
profileId
sessionId
roundId
```

---

## Archive Guarantees

Historical Export guarantees:

* chronological export ordering;
* complete environment reconstruction;
* complete observation reconstruction;
* delivery of historical data independent of current runtime state.

Historical Export tolerates duplicate export artifact creation.

Duplicate artifacts may increase processing cost but must not compromise historical correctness.

Historical Export does not guarantee:

* real-time synchronization;
* immediate upload;
* preservation of local data after user data reset.

---

## Out of Scope

The following topics are not part of Historical Export Model:

* JSON schema;
* file naming protocol;
* retention policy;
* cleanup scheduling;
* WorkManager implementation details;
* cloud-side analytical processing.

These concerns are governed by separate specifications.

