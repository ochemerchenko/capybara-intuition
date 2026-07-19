# EXPORT_FILE_PROTOCOL.md

## Purpose

Export File Protocol defines:

* export artifact naming;
* export artifact discovery;
* export queue ordering;
* export artifact validation.

The protocol is intentionally minimal.

Its purpose is to identify exported data and support deterministic export processing.

The protocol is not intended for analytics, routing, versioning, or business logic.

---

## Core Principles

### File Name Identifies Data

A file name must identify the data contained within the export artifact.

A file name must not identify:

* upload attempts;
* transport state;
* processing state;
* implementation details.

---

### Queue Is File-Based

Export queue is represented by export artifacts stored in local export storage.

No separate queue registry is required.

Whenever reasonably possible, export processing should be recoverable from export files alone.

---

### One Artifact Per Data Identity

A single export artifact represents a single export identity.

Creation of duplicate artifacts is undesirable but not considered data corruption.

Duplicate artifacts may increase processing cost but must not compromise historical correctness.

---

## Export Artifact Types

The protocol currently defines two export types:

```text
REFERENCE
DAILY
```

### REFERENCE

Exports reference environment data.

Examples:

* installation projection;
* profile projections;
* notebooks;
* topology families;
* topologies;
* renderers;
* masks;
* resources.

---

### DAILY

Exports observational gameplay data.

Examples:

* sessions;
* rounds;
* round cards.

---

## Effective Date

Every export artifact is associated with an effective date.

Effective date represents the date of the exported data.

Effective date does not represent:

* file creation date;
* upload date;
* processing date.

---

### REFERENCE Effective Date

For REFERENCE exports:

```text
effectiveDate
=
reference data effective date
```

The date when the exported reference environment became valid.

Example:

```text
REFERENCE_2026-06-21
```

means:

```text
This reference environment became effective on 2026-06-21.
```

---

### DAILY Effective Date

For DAILY exports:

```text
effectiveDate
=
observation date
```

Example:

```text
DAILY_2026-06-21
```

means:

```text
This export contains observations from 2026-06-21.
```

---

## File Name Structure

Canonical file name structure:

```text
<installationId>_<exportType>_<effectiveDate>.gz
```

Where:

```text
installationId
```

is the installation identifier.

```text
exportType
```

is:

```text
REFERENCE
```

or

```text
DAILY
```

```text
effectiveDate
```

uses ISO format:

```text
YYYY-MM-DD
```

---

## Examples

Reference export:

```text
3fa85f64_REFERENCE_2026-06-21.gz
```

Daily export:

```text
3fa85f64_DAILY_2026-06-21.gz
```

---

## Temporary Files

Incomplete export artifacts must not use the canonical naming format.

Examples:

```text
tmp_12345
building_67890
```

or any other implementation-specific names.

Temporary files are not export artifacts.

Temporary files must never participate in export queue processing.

---

## Export Artifact Validation

A valid export artifact must satisfy all conditions:

* file name matches canonical naming structure;
* export type is supported;
* effective date is valid;
* file extension is `.gz`.

Files that do not satisfy protocol requirements are considered invalid.

---

## Builder Recovery

Export Builder must remove invalid temporary artifacts left from interrupted execution.

Examples:

* partially generated files;
* abandoned temporary files;
* interrupted export builds.

Only valid export artifacts may remain in export storage.

---

## Export Storage

Preferred export storage location:

```text
context.noBackupFilesDir
```

Export artifacts are private application data.

Export artifacts must not be stored in:

* cache storage;
* user-visible storage;
* shared storage.

---

## Queue Ordering

Export queue ordering is derived from export artifacts.

Primary ordering rule:

```text
effectiveDate ASC
```

Oldest effective date is processed first.

---

### Same-Date Ordering

When multiple export artifacts share the same effective date:

```text
REFERENCE
    →
DAILY
```

REFERENCE exports must be processed before DAILY exports.

This guarantees that reference environment data is available before observational data that depends on it.

---

## Delivery Confirmation

A successfully uploaded export artifact is considered delivered.

After successful delivery:

```text
upload
    →
confirm
    →
delete local artifact
```

The local export artifact is no longer required.

---

## Out of Scope

The following topics are not part of Export File Protocol:

* JSON schema;
* payload structure;
* Firebase Storage layout;
* upload implementation;
* retention policy;
* export scheduling.

These concerns are governed by separate specifications.

