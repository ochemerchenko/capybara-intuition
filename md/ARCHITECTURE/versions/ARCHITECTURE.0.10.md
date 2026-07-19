## Version: 0.10
## Last Updated: 2026-04-06

# Architecture

## Overview

The system follows a layered architecture:

- Domain (Game logic)
- Data (Persistence, mapping)
- UI (ViewModel, rendering)
- Application (bootstrap & initialization)

---

## GameEngine

GameEngine is a pure state machine.

### Responsibilities
- Handles game flow
- Applies events → produces new state

### Constraints
- No persistence logic
- No side effects
- No knowledge about database

---

## Mapping Layer

Responsible for transforming:

GameRound + GameState → GameRoundRecord

### Responsibilities
- Build persistent models
- Compute derived fields (result, options)

---

## ViewModel

Acts as a bridge between UI and domain.

### Responsibilities
- Dispatch events to GameEngine
- Expose UI state
- Persist round results

### UI Timing Responsibilities
- resultShownAt
- firstUserActionAt
- postRoundActionType

### Rules
- Only first post-result action is recorded
- Timing is driven by UI events

---

## Application Layer

### App.kt

Application entry point.

### Responsibilities
- Global initialization
- Debug tooling bootstrap
- Future integrations (Firebase, analytics, etc.)

Registered in AndroidManifest.

---

## Debug / Development Infrastructure

### DebugDataInitializer

- Runs on application startup (DEBUG only)
- Seeds local database with synthetic data
- Idempotent (checks existing data)

### Seed Characteristics
- Simulates realistic user behavior:
  - decision time distribution
  - post-result delays
  - user personas

---

## Data Flow

```text
UI → ViewModel → GameEngine → State
                    ↓
                Mapper
                    ↓
                Repository → DB