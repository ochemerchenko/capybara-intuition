# COMPOSITION_MODEL

## Version: 0.17

## Last Updated: 2026-06-21

---

# Purpose

This document describes runtime composition semantics.

The composition model explains:

* how the application is assembled,
* how dependencies are owned,
* how modules are organized,
* how runtime infrastructure is connected.

The composition model does not define:

* gameplay semantics,
* observational semantics,
* emotional feedback semantics,
* persistence semantics.

These concerns are described separately in:

* ARCHITECTURE.md
* GAMEPLAY_MODEL.md
* OBSERVATIONAL_MODEL.md
* EMOTIONAL_FEEDBACK_MODEL.md

---

# Core Principle

Composition exists to make dependency ownership explicit.

Composition is not a business domain.

Composition is not gameplay.

Composition is not persistence.

Composition exists solely to assemble runtime objects.

---

# Composition Root

The application uses a single composition root.

```text
AppContainer
```

AppContainer is responsible for:

* database creation,
* module creation,
* infrastructure assembly,
* bootstrap assembly.

AppContainer does not own domain semantics.

AppContainer exists only to connect runtime dependencies.

---

# Runtime Assembly

Application startup follows:

```text
Application
    →
AppContainer
    →
Modules
    →
Runtime Objects
```

The composition root is created once during application startup.

Modules are then assembled from shared infrastructure.

---

# Shared Infrastructure

The following infrastructure is shared across modules:

```text
Context

AppDatabase
```

Shared infrastructure should remain minimal.

New globally shared dependencies should be introduced cautiously.

---

# Composition Modules

Runtime ownership is organized through composition modules.

Current production modules:

```text
InstallationModule

LicenseModule

ProfileModule

GameplayModule

StatisticsModule

TesterModule
```

Modules localize dependency ownership.

Modules reduce cross-domain dependency leakage.

Modules are runtime assembly structures rather than business domains.

---

# InstallationModule

InstallationModule owns:

* InstallationDao
* InstallationRepository
* InstallationRuntime
* InstallationInitializer

InstallationModule provides installation identity services to other modules.

InstallationModule is the owner of installation infrastructure.

---

# LicenseModule

LicenseModule owns:

* LicenseDao
* LicenseInitializer
* LicenseRuntime

LicenseModule provides runtime license state and license persistence infrastructure.

LicenseModule is the owner of license-related runtime infrastructure.

LicenseModule does not own capability behavior.

Capability availability is resolved separately through capability infrastructure.

---

# ProfileModule

ProfileModule owns profile-related runtime dependencies.

Examples:

* ProfileRepository
* ProfileSecurityRepository
* ProfileViewModel

ProfileModule coordinates Profile submodules.

---

# Profile Submodules

ProfileModule currently contains:

```text
ProfileSecurityModule

ProfileTopologyModule

ProfileResourcesModule
```

Submodules exist to localize ownership inside the Profile domain.

Submodules are not independent composition roots.

ProfileModule remains their owner.

---

# GameplayModule

GameplayModule owns gameplay persistence infrastructure.

Examples:

* SessionDao
* GameRoundDao
* GameSessionRepository
* GameRoundRepository

GameplayModule provides gameplay infrastructure required by gameplay execution.

Gameplay semantics remain outside the composition model.

---

# StatisticsModule

StatisticsModule owns statistics-related infrastructure.

Examples:

* StatisticsDao
* StatisticsRepository
* StatisticsViewModel

StatisticsModule provides analytical reconstruction infrastructure.

Statistics ownership remains domain-specific.

---

# TesterModule

TesterModule owns testing and support infrastructure.

Examples:

* HistoricalDatasetRepository
* HistoricalDatasetGenerator
* HistoricalDatasetManager

TesterModule exists solely to support testing and demonstration workflows.

Tester infrastructure is operational support infrastructure.

---

# ViewModel Ownership

ViewModels are owned by composition modules.

Examples:

```text
ProfileViewModel
    →
ProfileModule

StatisticsViewModel
    →
StatisticsModule
```

ViewModels should not be created inside composables.

ViewModels should be provided through composition ownership.

---

# Dependency Direction

Dependency flow should remain predictable.

Preferred direction:

```text
AppContainer
    →
Module
    →
Repository
    →
DAO
```

Cross-module dependencies should remain explicit.

Hidden ownership should be avoided.

---

# Ownership Rule

Every runtime dependency should have exactly one composition owner.

Examples:

```text
InstallationRuntime
    →
InstallationModule

LicenseRuntime
    →
LicenseModule

ProfileRepository
    →
ProfileModule

GameSessionRepository
    →
GameplayModule

StatisticsRepository
    →
StatisticsModule
```

Ownership should always be visible from composition structure.

---

# Bootstrap Participation

Bootstrap participates in composition but is not itself a module.

Bootstrap receives infrastructure from composition modules.

Current bootstrap responsibilities include:

* default profile creation,
* topology loading,
* notebook loading,
* renderer loading,
* resource loading,
* installation initialization,
* license initialization.

Bootstrap owns initialization.

Bootstrap does not own runtime behavior.

---

# Architectural Relationship

Composition and architecture are related but different.

Architecture describes:

```text
Who owns meaning.
```

Composition describes:

```text
Who owns dependencies.
```

A domain owner and a dependency owner are often related.

They are not the same concept.

---

# Evolution Principle

Composition should evolve only when dependency ownership becomes unclear.

The preferred solution is:

* explicit ownership,
* explicit modules,
* explicit dependency flow.

The composition model intentionally avoids:

* service locators,
* hidden dependency ownership,
* circular dependency structures,
* speculative composition abstractions.

Composition clarity is preferred over abstraction quantity.

