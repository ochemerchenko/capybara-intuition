## Version: 0.11
## Last Updated: 2026-04-07

# Statistics Data Model

---

## Overview

Statistics is a read-only layer built on top of persisted game data.

Data flows through multiple transformation stages:

DB → DAO → Repository → Engine → UI

---

# 📊 Data Layers

---

## 1. Raw Data (Database Projection)

### SessionCountPerDay

Represents aggregated sessions per calendar day.

dayStartMillis: Long  
count: Int  

### Source
- StatisticsDao

### Notes
- Aggregated in SQL
- Grouped by local calendar day
- Filtered by time range and mode

---

## 2. Domain Transformation

Handled by:

SessionsAnalyticsEngine

### Responsibilities

- Fill missing days (0 sessions)
- Normalize time range
- Guarantee fixed-size output (30 days)
- Sort data (old → new)

---

## 3. UI Model

### GraphData

points: List<GraphPoint>  
labels: List<String>  
daysOfWeek: List<DayOfWeek>  

---

### GraphPoint

x: Float  
y: Float  

---

## 🧠 Data Pipeline

SessionCountPerDay (raw)  
        ↓  
(normalization, fill gaps)  
        ↓  
GraphData (UI)  

---

# ❌ Not Implemented (0.11)

## DailyStats (future)

date  
sessionsCount  
afterSessionCount  
beforeSessionCount  
hybridSessionCount  

---

## SessionStats (future)

sessionId  
totalRounds  
winRounds  
density  

---

## TimingStats (future)

avgDecisionTime  
avgPostResultDelay  

---

# ⚠️ Constraints

- Statistics is read-only
- No impact on game logic
- No writes to DB
- Engine does not know about GameMode
- Fixed period: 30 days

---

# 🧠 Design Decisions

## Aggregation in DAO
SQL handles grouping and filtering

## Engine = Pure Transformation
No DB access, no filtering, only normalization

## UI Model is Dumb
GraphData contains no logic

---

# 🔜 Evolution Path

- moving average
- trend lines
- adaptive aggregation
- density metrics
- timing analytics
