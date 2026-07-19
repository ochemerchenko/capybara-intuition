## Version: 0.12

## Last Updated: 2026-04-11

# Statistics Data Model

---

## Overview

Statistics is a read-only analytics layer built on top of persisted game data.

Data flows through transformation stages:

DB → DAO → Repository → Engine → UI

---

# 📊 Data Layers

---

## 1. Raw Data (Database Projection)

---

### SessionCountPerDay

Represents aggregated sessions per calendar day.

dayStartMillis: Long
count: Int

### Source

* StatisticsDao

### Notes

* Aggregated in SQL
* Grouped by local calendar day
* Filtered by time range and mode

---

### SessionDensityRaw

Represents aggregated session data.

sessionId: String
startedAt: Long
totalRounds: Int
winRounds: Int

### Source

* StatisticsDao (GROUP BY sessionId)

### Notes

* Derived from round_records
* Used for session-based analytics
* No persistence — projection only

---

---

## 2. Domain Transformation (Engines)

Handled by:

* SessionsAnalyticsEngine (day-based)
* SessionDensityEngine (session-based)

---

## 🔹 Day-based (existing)

### Responsibilities

* Fill missing days (0 sessions)
* Normalize time range
* Guarantee fixed-size output (30 days)
* Sort data (old → new)

---

## 🔹 Session-based (new)

### Pipeline

SessionDensityRaw
↓
(filter, sort)
↓
density calculation
↓
compression
↓
moving average
↓
SessionDensityGraphData

---

### Steps

#### 1. Filtering

* exclude sessions with < 2 rounds

---

#### 2. Density

```text
density = (winRounds / totalRounds) * 100
```

---

#### 3. Compression

* max ~80 points
* bucket aggregation:

  * multiple sessions → 1 point
  * value = average density

---

#### 4. Moving Average

* short window = 5
* long window = 10

---

#### 5. Baseline

```text
baseline = 100 / level
```

---

### Important Rule

> Moving average is computed AFTER compression

---

---

## 3. Domain Models

---

### GraphPoint

x: Float
y: Float

---

### GraphData (legacy, day-based)

points: List<GraphPoint>
labels: List<String>
daysOfWeek: List<DayOfWeek>

---

### SessionDensityGraphData (new)

raw: List<GraphPoint>
maShort: List<GraphPoint>
maLong: List<GraphPoint>
baseline: Float
totalSessions: Int

---

### DensityMap

```kotlin
Map<GameMode?, Map<Int, SessionDensityGraphData>>
```

---

### Meaning

* outer key → mode:

  * null → ALL
  * BEFORE / AFTER / HYBRID

* inner key → level

* value → graph data

---

### Purpose

* supports multi-graph rendering
* enables UI flexibility (level-first / mode-first)

---

---

## 🧠 Data Pipeline

---

### Day-based

SessionCountPerDay (raw)
↓
(normalization, fill gaps)
↓
GraphData

---

### Session-based

SessionDensityRaw
↓
(filter, sort, density)
↓
(compression)
↓
(moving average)
↓
SessionDensityGraphData

---

---

## 📱 UI Usage

---

### Graph Types

#### BarChart

* sessions per day
* fixed 30-day window

---

#### LineChart

Uses:

* raw (noise)
* maShort (signal)
* maLong (trend)
* baseline (reference)

---

---

## ⚠️ Constraints

* Statistics is read-only
* No impact on game logic
* No writes to DB
* Engine does not know about UI
* Engine does not know about GameMode logic (handled outside)
* Compression reduces precision (by design)

---

---

## 🧠 Design Decisions

---

### 1. No Redundant Storage

* no session aggregates in DB
* all metrics derived from round_records

---

### 2. DAO Handles Aggregation

* SQL groups by session / day
* minimizes data transfer

---

### 3. Engine = Pure Transformation

* no DB access
* no filtering by mode/level
* deterministic output

---

### 4. Compression First

* UI constraint (~80 points)
* avoids overdraw and clutter

---

### 5. Moving Average After Compression

* prevents double smoothing
* keeps consistent signal

---

### 6. Baseline as Reference

* expected random performance
* key UX anchor

---

---

## 🚧 Known Limitations (0.12)

* spikes may be smoothed out
* sessions have equal weight (round count ignored)
* no advanced metrics (yet)
* no anomaly detection
* no user customization

---

---

## 🔜 Evolution Path

---

### Near (UX)

* Level → grid (2×2)
* click → detail view
* pivot (LEVEL / MODE)

---

### Mid

* trend direction
* consistency score
* volatility

---

### Future

* spike detection
* session weighting
* behavioral segmentation

