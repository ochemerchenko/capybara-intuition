# EMOTIONAL_FEEDBACK_MODEL

## Version: 0.17

## Purpose

This document describes the emotional feedback model used by the gameplay system.

The emotional feedback model determines:

* capybara visual state,
* capybara animation behavior,
* capybara text responses.

The model intentionally evaluates gameplay situations rather than individual round outcomes.

The emotional feedback system exists solely to interpret gameplay observations and provide expressive user-facing feedback.

The emotional feedback system:

* does not participate in gameplay outcome determination,
* does not alter gameplay probabilities,
* does not modify gameplay flow,
* does not influence gameplay difficulty,
* does not perform behavioral adaptation.

---

# Core Principle

Capybara does not react directly to:

* a single WIN,
* a single LOSE,
* a single streak.

Capybara reacts to the statistical rarity of the current aggregated gameplay state.

The emotional feedback model evaluates:

> How unusual is the current observed gameplay situation?

rather than:

> Was the last round won or lost?

---

# Input Data

The model uses only runtime session data already available inside GameSession.

Required inputs:

```text
level

winCount
loseCount

winStreak
loseStreak

lastBrokenStreak

isWinLastBet
```

No additional session attributes are required.

---

# Derived State

## Round Count

```text
roundCount =
    winCount + loseCount
```

---

## Current Streak Type

```text
WIN
```

if:

```text
isWinLastBet == true
```

otherwise:

```text
LOSE
```

---

## Current Streak Length

```text
max(
    winStreak,
    loseStreak
)
```

---

## Previous Streak Type

The previous streak type is always the opposite of the current streak type.

---

## Previous Streak Length

```text
lastBrokenStreak
```

---

# Aggregated Gameplay State

The emotional feedback system does not attempt to reconstruct the exact gameplay history.

Instead it evaluates an aggregated gameplay state defined by:

```text
level

roundCount

winCount
loseCount

currentStreakType
currentStreakLength

previousStreakType
previousStreakLength
```

Multiple gameplay histories may correspond to the same aggregated state.

This is intentional.

---

# Interpretation Domain

The model evaluates the probability of observing a gameplay state.

The model does NOT evaluate the probability of a specific gameplay history.

The evaluated object is:

> the family of all gameplay sessions compatible with the observed aggregated state.

This intentionally avoids the need to store complete gameplay history.

---

# Probability Model

The probability model operates on:

* gameplay level,
* session length,
* win/lose distribution,
* current streak,
* previous streak.

The model additionally considers:

* wins located outside the last two streaks,
* loses located outside the last two streaks.

These values are derived from existing session statistics.

No additional persistence is required.

---

# Rarity Score

The emotional feedback model converts probability into an information score.

```text
rarityScore =
    -log2(P)
```

where:

```text
P
```

is the probability estimate of the aggregated gameplay state.

---

# Logarithmic Scale

Base-2 logarithm is intentionally used because:

* it provides sufficient sensitivity,
* it naturally measures informational surprise,
* it produces practical value ranges,
* it preserves useful differentiation between gameplay levels.

The model intentionally avoids base-10 logarithms because they provide insufficient resolution for expected gameplay session lengths.

---

# Rarity Classification

Continuous rarity scores are mapped into seven rarity classes.

```text
ORDINARY

INTERESTING

UNUSUAL

RARE

AMAZING

INCREDIBLE

LEGENDARY
```

Final threshold values are determined through simulation and empirical balancing.

---

# Emotional Events

The capybara receives only three gameplay events.

```text
OPTIONS_SHOWN

RESULT_WIN

RESULT_LOSE
```

These events describe gameplay presentation moments rather than emotional states.

---

# Behavioral Space

Behavior is determined by:

```text
rarityClass
```

combined with:

```text
event
```

Therefore:

```text
7 rarity classes
×
3 events
=
21 behavioral states
```

---

# Presentation Mapping

Each behavioral state maps to:

* image,
* animation,
* text response.

The mapping is independent from gameplay logic.

```text
behaviorState
    →
image

behaviorState
    →
animation

behaviorState
    →
phrase
```

---

# Emotional Semantics

The emotional model intentionally allows both success and failure to become remarkable events.

Examples:

* long winning streaks may become extraordinary,
* long losing streaks may become extraordinary,
* interruption of extraordinary streaks may become noteworthy,
* statistically improbable session structures may become legendary.

The model does not assume that winning is inherently more interesting than losing.

The determining factor is rarity rather than outcome polarity.

---

# Architectural Separation

Gameplay semantics remain owned by the gameplay domain.

The emotional feedback model:

* consumes gameplay observations,
* evaluates rarity,
* generates presentation semantics.

The emotional feedback model does not own gameplay truth.

Gameplay and emotional feedback remain independent subsystems connected only through aggregated gameplay observations.

---

# Future Evolution

Future versions may refine:

* probability estimation,
* rarity thresholds,
* presentation assets,
* phrase generation.

Future evolution must preserve:

* aggregated-state evaluation,
* bounded complexity,
* independence from gameplay outcome generation,
* independence from full-history reconstruction.

The emotional feedback model intentionally remains observation-driven rather than history-driven.

