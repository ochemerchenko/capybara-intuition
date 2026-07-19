# CORE

## Version: 0.14

## Last Updated: 2026-05-02

# Project: Інтуіція

---

# Purpose

Інтерактивна Android-система, де користувач намагається вгадати колір, загаданий системою, з метою збору статистики, аналізу поведінки та дослідження власних патернів рішень.

Проєкт поєднує:

* гру
* behavioral analytics
* explainable statistics
* lightweight emotional feedback

---

# Product Identity

Інтуіція — це не просто mini-game.

Система побудована як:

* behavioral exploration tool
* explainable analytics product
* experimentation platform

Основна цінність:

* спостереження за поведінкою
* виявлення патернів
* довготривале самоспостереження
* інтерпретована статистика

---

# Domain Model

## Session

Сукупність раундів без зміни параметрів гри.

### Types

* before
* after
* hybrid

### Session ends when

* змінюється level
* користувач виходить з гри
* process death

---

## Round

Одиниця взаємодії.

Round:

* належить session
* має lifecycle
* є основною аналітичною одиницею

---

## Palette

Фіксований список кольорів:

* HEX
* semantic name

Для кожного раунду формується:

* унікальна підмножина
* без повторів

---

## Level

Визначається кількістю кнопок.

### Constraints

* min = 2
* max = 12

---

# Core Mechanics

Раунд:

1. генерується набір options
2. визначається correct choice
3. користувач робить вибір
4. результат обчислюється
5. результат показується користувачу
6. поведінка користувача зберігається

---

# Game Modes

## Before

user → system

---

## After

system → user

---

## Hybrid

roundType визначається випадково

---

# Round Flow

Round не базується на жорсткій enum-state машині.

Стан визначається через:

* selectedColor
* correctColor
* phase

---

## Phase

### OPTIONS_READY

користувач може зробити вибір

---

### RESOLVED

результат вже визначений

---

# Flow Rules

## New round starts

* при старті session
* після підтвердження результату

---

## Input Rules

* повторний input ігнорується
* RESOLVED round не приймає interaction

---

# Result

## WIN

selected == correct

---

## LOSE

selected != correct

---

## ABANDONED

користувач не завершив interaction

---

# User Timing

Система відстежує поведінку користувача.

---

## Decision Time

```text
userSelectedAt - startedAt
```

---

## Post-result Delay

```text
firstUserActionAt - resultShownAt
```

---

## Post-round Behavior

Відстежується:

* next round
* level change
* navigation behavior
* continuation patterns

---

# State Management

## Single Source of Truth

```text
GameState
```

---

## Responsibilities

### GameEngine

* pure reducer
* deterministic state transitions
* no side effects

---

### ViewModel

* orchestration
* timing tracking
* persistence coordination
* UI synchronization

---

### UI

* reactive rendering only
* no hidden logic
* state-driven rendering

---

# Session Lifecycle

## SESSION_STARTED

Виникає:

* при старті гри
* при зміні параметрів

---

## SESSION_ENDED

Виникає:

* при зміні level
* при виході
* при process death

---

## Rotation

Session survives configuration changes.

---

# Persistence Philosophy

## Runtime

Runtime state:

* current session
* current round

---

## Persisted

Persisted state:

* sessions
* round_records

---

## Analytics Rule

Analytics derives everything from raw data.

No redundant analytics storage.

---

# Analytics Layer

## Nature

Statistics is:

* read-only
* detached from gameplay
* derived from persisted data

Statistics:

* never modifies GameEngine
* never writes gameplay state
* never affects game outcome

---

# Analytics Philosophy

Основний фокус:

* behavioral observation
* trend exploration
* explainability
* readability
* trustworthiness

---

## Baseline

Ключовий UX reference point:

```text
baseline = 100 / level
```

Показує:

* expected random performance
* reference expectation
* behavioral anchor

---

## Compression-first Rendering

Великі об'єми даних:

* агрегуються
* компресуються
* спрощуються для читабельності

Пріоритет:

* clarity over precision

---

## Moving Average

Moving average використовується для:

* signal extraction
* trend visibility
* noise reduction

---

# Statistics UX Model

Statistics побудовані як:

* intentional exploration
* pivot-based navigation
* detail-first interaction

---

## Supported Pivots

### LEVEL

Level → modes

---

### MODE

Mode → levels

---

## Interaction Model

* preview → detail
* tap-driven exploration
* contextual explanations
* expandable activity sections

---

## Important Principle

Graphs НЕ рендеряться automatically by default.

Користувач:

* обирає контекст
* досліджує consciously
* відкриває detail intentionally

---

# Explainable Analytics

Statistics повинні:

* пояснювати себе
* бути інтерпретованими
* не вимагати knowledge from developer

---

# Markdown Help System

Система підтримує:

* markdown-based help dialogs
* localized help content
* semantic illustrations
* contextual explanations

---

# Localization

Help content локалізується через:

```text
assets/help/*.uk.md
assets/help/*.en.md
```

---

# Emotional Companion

## Capybara

Capybara — lightweight emotional companion.

Призначення:

* feedback
* emotional reinforcement
* soft UX personality
* reaction to user outcomes

---

## Emotional States

### IDLE

neutral state

---

### WIN

positive reinforcement

---

### LOSE

soft negative feedback

---

## Architecture

Система розділена на:

* behavior (CapybaraEvent)
* appearance (CapybaraMood)

---

# Randomness

Randomness використовується для:

* option generation
* correct answer selection
* hybrid round type
* seed-based testing

---

# Constraints

## Gameplay

* buttons: 2–12
* colors unique inside round
* resolved round immutable

---

## Analytics

* read-only
* no gameplay influence
* derived from raw records

---

## UX

* avoid graph overload
* prioritize readability
* clarity over density
* explainability over complexity

---

# Architecture Notes

## GameEngine

* pure state machine
* no persistence
* no side effects

---

## Statistics Engines

* pure transformations
* deterministic
* UI-independent

---

## Compose UI

* state hoisting
* reactive rendering
* screen-level orchestration
* reusable composables

---

# Current State

Implemented:

* gameplay core
* user timing tracking
* session analytics
* density analytics
* pivot navigation
* detail screens
* markdown help system
* explainable statistics
* emotional capybara feedback
* localized help content
* reusable dialog infrastructure

---

# Evolution

## Near-term

* internal testing
* analytics validation
* low-data behavior tuning
* capybara transition polish
* UX hardening

---

## Mid-term

* advanced insights
* consistency metrics
* volatility analysis
* behavioral segmentation

---

## Long-term

* Firebase sync
* cloud features
* AI-assisted insights
* personalization
* advanced behavioral models

