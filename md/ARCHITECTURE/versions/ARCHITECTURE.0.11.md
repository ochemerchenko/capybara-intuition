## Version: 0.11
## Last Updated: 2026-04-07

# Architecture

---

## Overview

The system follows a layered architecture with a strict separation between:

- Game (write path)
- Statistics (read-only analytics path)

### Layers
- Domain (Game logic)
- Data (Persistence, mapping)
- Presentation (UI, ViewModel)
- Application (bootstrap & initialization)

---

# 🧠 Core Principle

The system is split into two independent flows:

### Write Path (Game)

UI → ViewModel → GameEngine → Mapper → Repository → DB

### Read Path (Statistics)

DB → StatisticsDao → Repository → Engine → ViewModel → UI

---

# 🎮 Game Layer

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

Transforms:

GameRound + GameState → GameRoundRecord

### Responsibilities
- Build persistent models
- Compute derived fields (result, options)

---

## ViewModel (Game)

Acts as a bridge between UI and domain.

### Responsibilities
- Dispatch events to GameEngine
- Expose UI state
- Persist round results

---

# 📊 Statistics Layer

Statistics is a read-only analytics layer built on top of persisted game data.

## Responsibilities
- Aggregate session data
- Normalize time-based data
- Prepare data for visualization

## Components

### StatisticsDao
- Executes aggregation queries
- Groups sessions by day
- Filters by GameMode

### StatisticsRepository
- Defines time window
- Delegates filtering to DAO

### SessionsAnalyticsEngine
- Fills missing days
- Normalizes data
- Prepares GraphData

### StatisticsViewModel
- Loads data
- Builds UI state

---

# 🔄 Data Flow

## Game Flow
UI → ViewModel → GameEngine → Mapper → Repository → DB

## Statistics Flow
DB → StatisticsDao → Repository → Engine → UI

---

# 🧠 Key Design Decisions

- Statistics is read-only
- Engine separation (Game vs Analytics)
- UI contains no logic
- Normalize layer is mandatory

---

# 🚧 Current Limitations (0.11)

- Fixed period (30 days)
- No trends or averages
- No advanced analytics
