## Version: 0.09.001
## Last Updated: 2026-04-05

# Project: Інтуіція

## Purpose
Інтерактивна Android-система, де користувач намагається вгадати колір, загаданий системою, з метою збору статистики та аналізу поведінки.

---

## Domain Model

### Session
- сукупність раундів без зміни параметрів гри
- типи: before / after / hybrid
- завершується при:
  - зміні кількості кнопок
  - виході з GameFragment
  - process death

### Round
- одиниця взаємодії
- належить session
- має state machine lifecycle

### Palette
- фіксований список кольорів (HEX + name)
- для раунду формується унікальна підмножина без повторів

### Level
- визначається кількістю кнопок (2–12)

---

## Core Mechanics

Раунд:
1. генерується набір кнопок
2. генерується systemChoice (залежно від mode)
3. користувач робить вибір
4. результат порівнюється

- systemChoice обирається випадково з buttons
- buttons immutable протягом раунду

---

## Game Modes

### Before
- user → system

### After
- system → user

### Hybrid
- roundType визначається випадково

---

## Round Lifecycle (State Machine)

States:
- ROUND_INITIALIZED
- BUTTONS_GENERATED
- USER_INPUT_STARTED
- USER_CHOICE_MADE
- SYSTEM_CHOICE_GENERATED
- ROUND_EVALUATED
- ROUND_COMPLETED

Transitions:
- before: user → system
- after: system → user

---

## Events

- START_ROUND
- BUTTONS_GENERATED
- USER_INPUT_STARTED
- USER_CHOICE_MADE
- SYSTEM_CHOICE_GENERATED
- ROUND_EVALUATED
- ROUND_COMPLETED
- SESSION_STARTED
- SESSION_ENDED

---

## Event → State Mapping

- START_ROUND → ROUND_INITIALIZED
- BUTTONS_GENERATED → USER_INPUT_STARTED
- USER_CHOICE_MADE → (mode dependent)
- SYSTEM_CHOICE_GENERATED → ROUND_EVALUATED
- ROUND_EVALUATED → ROUND_COMPLETED

Rules:
- SYSTEM_CHOICE_GENERATED:
  - before → після USER_CHOICE_MADE
  - after → після BUTTONS_GENERATED

- USER_INPUT_STARTED:
  - before → після BUTTONS_GENERATED
  - after → після SYSTEM_CHOICE_GENERATED

---

## Round Flow Rules

- новий раунд запускається:
  - при старті session
  - після підтвердження результату (mainButton)

- повторний input ігнорується після USER_CHOICE_MADE

---

## Result

- match: userChoice == systemChoice
- no match: userChoice != systemChoice

---

## State Management

- Single source of truth: producer
- ViewModel → reactive flow
- UI → тільки відображення

---

## Invariants

- round не може бути COMPLETED без userChoice і systemChoice
- session (before/after) містить тільки відповідні раунди
- hybrid може містити обидва типи

---

## UI Behavior

- відображається N кнопок (2–12)
- порядок взаємодії визначається mode
- після завершення → показ результату

---

## Edge Cases

- відсутність user input → очікування
- повторний input → ігнорується
- recreation → state відновлюється з ViewModel
- process death → session завершується

---

## Session Lifecycle

- SESSION_STARTED → при відкритті GameFragment або зміні параметрів
- session триває без переривань при rotation
- раунди додаються до session
- SESSION_ENDED:
  - зміна параметрів
  - вихід з екрану
  - system interruption

---

## Data & State

### Runtime
- current session
- current round
- state machine state

### Persisted
- sessions
- rounds
- statistics

---

## Data Model

- SQLite:
  - sessions
  - rounds
  - statistics

(детально: DATA → data-model)

---

## Randomness

- використовується для:
  - кольорів
  - systemChoice
  - roundType

- підтримується seed для тестування

---

## Constraints

- кнопки: 2–12
- кольори унікальні
- система детерміновано тестується

---

## Current State

- реалізовано MVP
- MVVM + producer/repository
- статистика через Canvas

---

## Next Steps

- розширення статистики
- Firebase sync
- підготовка до релізу
