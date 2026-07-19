## Version: 0.12
## Last Updated: 2026-04-11

# Project: Інтуіція

## Purpose
Інтерактивна Android-система, де користувач намагається вгадати колір, загаданий системою, з метою збору статистики та аналізу поведінки.

---

## Domain Model

### Session
- сукупність раундів без зміни параметрів гри
- типи: before / after / hybrid
- завершується при:
  - зміні рівня
  - виході з GameFragment
  - process death

---

### Round
- одиниця взаємодії
- належить session
- має lifecycle (state-driven)

---

### Palette
- фіксований список кольорів (HEX + name)
- для раунду формується унікальна підмножина без повторів

---

### Level
- визначається кількістю кнопок (2–12)

---

## Core Mechanics

Раунд:
1. генерується набір кнопок (options)
2. визначається correct choice (залежно від mode)
3. користувач робить вибір
4. результат обчислюється

---

## Game Modes

### Before
- user → system

### After
- system → user

### Hybrid
- roundType визначається випадково

---

## Round Flow (State Logic)

Round не має жорсткої enum-state машини, а визначається полями:

- selectedColor
- correctColor
- phase

---

### Phase (RoundPhase)

- OPTIONS_READY
- RESOLVED

---

## Flow Rules

- новий раунд запускається:
  - при старті session
  - після підтвердження результату

- повторний input ігнорується

---

## Result

- WIN: selected == correct
- LOSE: selected != correct
- ABANDONED: user не зробив вибір

---

## User Timing (NEW)

Система відстежує поведінку користувача:

- decision time:
  - userSelectedAt - startedAt

- post-result delay:
  - firstUserActionAt - resultShownAt

---

## State Management

- Single source of truth: GameState
- GameEngine → pure reducer
- ViewModel → orchestration + timing
- UI → тільки відображення

---

## Architecture Notes

### GameEngine
- pure state machine
- без side effects
- без persistence

---

### ViewModel
- керує lifecycle
- обробляє UI timing:
  - resultShownAt
  - firstUserActionAt
  - postRoundActionType

---

### Application Layer

- App.kt — entry point
- відповідає за ініціалізацію
- запускає debug seed (DEBUG only)

---

## Session Lifecycle

- SESSION_STARTED → при старті гри або зміні параметрів
- session триває через rotation
- SESSION_ENDED:
  - зміна параметрів
  - вихід
  - process death

---

## Data

### Runtime
- current session
- current round

### Persisted
- sessions
- round_records

---

## Randomness

- використовується для:
  - вибору кольорів
  - correct answer
  - roundType

- seed використовується для тестування

---

## Constraints

- кнопки: 2–12
- кольори унікальні
- раунд не може бути RESOLVED без correctPosition

---

## Current State

- чиста архітектура (separation of concerns)
- user timing інтегровано
- seed інфраструктура реалізована

---

## Evolution

### Implemented
- user-facing statistics (analytics layer)
- behavioral metrics (session density)

### Planned
- advanced insights
- Firebase sync

## Analytics (Read-only Layer)

Statistics is built on top of persisted data and does not affect core gameplay.

- No influence on GameEngine
- No side effects on game flow
- Derived from sessions and rounds
