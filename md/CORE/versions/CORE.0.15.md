# CORE

## Version: 0.15

## Last Updated: 2026-05-13

# Project: Інтуіція

---

# Purpose

Інтуіція — інтерактивна Android-система для дослідження власних поведінкових патернів через perceptual interaction, explainable analytics та long-term behavioral observation.

Проєкт поєднує:

* perceptual gameplay
* behavioral analytics
* explainable statistics
* emotional UX
* trust-oriented interpretation
* long-term self-observation

---

# Product Identity

Інтуіція — це НЕ:

* casual mini-game
* reaction trainer
* gambling-like reward system
* opaque “intuition score” simulator

---

# Інтуіція — це:

* perceptual behavioral exploration platform
* explainable analytics product
* self-observation tool
* long-term experimentation environment

---

# Core Product Value

Основна цінність продукту:

* спостереження за власною поведінкою
* виявлення патернів
* довготривале behavioral tracking
* explainable interpretation
* conscious exploration
* emotional engagement without manipulation

---

# 🧠 Core Philosophy

Проєкт intentionally prioritizes:

* supportability
* explainability
* explicit ownership
* deterministic lifecycle
* bounded contexts
* trustworthiness
* psychological clarity

---

# Important Architectural Principle

The project intentionally prefers:

```text
explicit ownership
```

over:

```text
generalized abstraction
```

---

# Supportability Principle

The project intentionally prioritizes:

```text
supportability over theoretical architectural elegance
```

---

# Behavioral Philosophy

Statistics НЕ повинні:

* маніпулювати користувачем
* створювати fake confidence
* генерувати opaque scoring
* стимулювати addictive interaction loops

---

# Trust Model

Користувач повинен:

* розуміти що відбувається
* бачити explainable metrics
* мати predictable UX
* не залежати від hidden mechanics

---

# Gameplay Model

---

# Session

Session — сукупність раундів без зміни параметрів gameplay.

Session defines:

* gameplay mode
* gameplay level
* perceptual configuration
* renderer ownership
* visual identity
* profile ownership

---

# Session Types

* AFTER
* BEFORE
* HYBRID

---

# Session Ends When

* змінюється level
* змінюється mode
* користувач виходить з gameplay
* process death завершує runtime

---

# Round

Round — базова behavioral interaction unit.

Round:

* belongs to session
* has deterministic lifecycle
* tracks user behavior
* stores perceptual interaction
* acts as primary analytics source

---

# Stimulus

Проєкт більше НЕ базується на:

```text
color guessing
```

---

# Current Philosophy

Gameplay базується на:

```text
renderer-independent perceptual stimuli
```

---

# Stimulus Purpose

Stimulus intentionally abstracts:

* colors
* images
* future perceptual entities

from:

* rendering mechanics
* visual representation
* gameplay semantics

---

# Architectural Importance

Stimulus migration стала однією з ключових architectural evolutions проекту.

Вона дозволила:

* renderer separation
* resource separation
* notebook ownership
* bounded perceptual semantics
* supportable extensibility

---

# Level

Level визначається кількістю perceptual options.

---

# Constraints

* min = 2
* max = 12

---

# Gameplay Flow

Раунд:

1. генерується perceptual option set
2. визначається correct stimulus
3. користувач робить selection
4. reducer computes result
5. результат показується користувачу
6. behavioral data persists

---

# Game Modes

---

# AFTER

system → user

Core free gameplay mode.

---

# BEFORE

user → system

Premium capability.

---

# HYBRID

roundType randomized dynamically.

Premium capability.

---

# Capability Philosophy

Free version intentionally provides:

* complete AFTER gameplay loop
* statistics access
* explainability support
* full perceptual experience

---

# Premium Extends Depth

Premium intentionally extends:

* gameplay semantics
* advanced interaction modes
* ownership capabilities

NOT:

* core trust
* explainability
* analytics visibility

---

# Premium Capabilities

Current planned premium capabilities:

* BEFORE mode
* HYBRID mode
* child profiles

---

# Important Principle

Monetization should:

* preserve trust
* preserve product legitimacy
* avoid crippleware behavior
* extend depth instead of restricting basics

---

# Round Lifecycle

Round lifecycle intentionally remains:

* deterministic
* explicit
* reducer-driven

---

# Round State

Round state is represented through:

* selected stimulus
* correct stimulus
* lifecycle phase
* explicit runtime semantics

---

# Lifecycle Philosophy

The project intentionally avoids:

* generalized orchestration runtime
* implicit lifecycle graphs
* hook-driven runtime execution
* dynamic transition engines

Reducer-driven lifecycle is currently considered:

* sufficiently explicit
* supportable
* predictable
* deployment-safe

---

# Runtime Ownership

---

# GameEngine

GameEngine acts as:

* deterministic reducer
* gameplay lifecycle coordinator
* runtime transition owner

---

# GameEngine Constraints

GameEngine intentionally has:

* no persistence ownership
* no UI ownership
* no analytics ownership
* no renderer ownership

---

# Handler Philosophy

Gameplay reducer logic may be decomposed into:

* semantic handlers
* isolated runtime operations
* bounded construction logic

without losing explicit lifecycle semantics.

---

# Statistics Philosophy

Statistics are:

* read-only
* detached from gameplay runtime
* reconstruction-based
* explainable
* observational

---

# Important Rule

Statistics NEVER:

* modify gameplay
* affect outcomes
* influence GameEngine
* alter runtime behavior

---

# Analytics Philosophy

The project intentionally prioritizes:

* clarity over density
* explainability over complexity
* interpretation over opaque scoring
* trust over aggressive gamification

---

# Baseline Philosophy

Core interpretive reference:

```text
baseline = 100 / level
```

Represents:

* expected random performance
* behavioral reference point
* interpretive anchor

---

# Compression Philosophy

Large datasets are intentionally:

* aggregated
* compressed
* simplified

Priority:

```text
clarity over precision
```

---

# Explainable Analytics

Statistics should:

* explain themselves
* remain interpretable
* avoid black-box scoring
* preserve psychological clarity

---

# UX Philosophy

---

# Selection-Driven Exploration

Statistics UX intentionally avoids:

* dashboard overload
* auto-rendered complexity
* information flooding

---

# User Exploration Model

User intentionally:

* selects context
* opens details consciously
* explores trends deliberately
* interprets analytics progressively

---

# Emotional UX

---

# Capybara

Capybara is:

```text
lightweight emotional reinforcement layer
```

---

# Purpose

Capybara provides:

* emotional feedback
* soft UX personality
* lightweight reinforcement
* non-intrusive companionship

---

# Important Principle

Capybara intentionally avoids:

* manipulative engagement
* addictive loops
* aggressive gamification
* attention hijacking

---

# Emotional States

* IDLE
* WIN
* LOSE

---

# Architecture Philosophy

Capybara separates:

* behavior
* appearance

through:

* CapybaraEvent
* CapybaraMood

---

# Explainability System

---

# Markdown Help

The project supports:

* markdown-based help dialogs
* localized help content
* semantic illustrations
* contextual explanations

---

# Markdown Philosophy

Markdown subsystem intentionally remains:

* passive
* declarative
* content-driven

NOT:

* scripting runtime
* orchestration framework
* dynamic execution system

---

# Localization

Localization is treated as:

* product-quality requirement
* supportability concern
* UX validation layer

NOT merely:

* text translation

---

# Environment Philosophy

AppLayoutEnvironment intentionally acts only as:

```text
physical layout context
```

---

# Environment Responsibilities

Includes:

* screen dimensions
* orientation
* content bounds

---

# Important Constraint

Environment layer must NOT evolve into:

* mega-context runtime
* generalized adaptive engine
* global visual policy system

---

# Ownership Philosophy

The project intentionally protects:

```text
bounded ownership
```

against:

* subsystem leakage
* feature-flag scattering
* implicit cross-domain dependencies
* generalized runtime coupling

---

# Important Warning

The primary architectural risk of the project is:

```text
ownership erosion
```

where subsystems begin knowing:

```text
"a little about everything"
```

---

# Process Runtime Branch

The project contains experimental:

```text
game/domain/process/*
```

systems.

---

# Current Status

These systems are:

* experimental
* frozen during stabilization
* excluded from production runtime
* retained only for future research

---

# Stability Philosophy

The project currently prioritizes:

* deployment hardening
* lifecycle clarity
* supportability
* restore correctness
* localization stability
* predictable maintenance

over:

* feature expansion
* orchestration complexity
* generalized runtime abstraction

---

# Current Product State

Implemented:

* perceptual gameplay core
* renderer-independent stimuli
* explainable statistics
* density analytics
* pivot navigation
* markdown help system
* emotional Capybara UX
* profiles & security
* notebook-based resources
* localized help content
* reusable dialog infrastructure

---

# Current Release Direction

The project is currently transitioning from:

```text
experimental development
```

into:

```text
deployment-oriented stabilization
```

---

# Near-term Goals (0.15)

* deployment hardening
* localization stabilization
* supportability cleanup
* capability isolation
* process-death validation
* environment consolidation
* telemetry stabilization

---

# Mid-term Goals

* advanced insights
* consistency metrics
* renderer expansion
* notebook growth
* session weighting
* behavioral segmentation

---

# Long-term Goals

* cloud synchronization
* advanced perceptual content
* AI-assisted interpretation
* personalized analytics
* adaptive behavioral systems
* orchestration revisiting

