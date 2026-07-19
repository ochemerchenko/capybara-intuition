# EMOTIONAL_FEEDBACK_MODEL

## Version: 0.18

## Purpose

This document describes the emotional feedback system used by the gameplay application.

The emotional feedback system transforms gameplay observations into narrative, emotional, textual and visual feedback.

The emotional feedback system determines:

* narrative interpretation,
* emotional composition,
* communication intent,
* visual expression,
* phrase selection.

The emotional feedback system:

* does not alter gameplay outcomes,
* does not modify gameplay probabilities,
* does not influence gameplay flow,
* does not adapt gameplay difficulty,
* does not manipulate user behavior.

The emotional feedback system exists solely to transform gameplay observations into meaningful companion feedback.

---

# Core Principle

Capybara does not react to individual wins or losses.

Capybara reacts to observed gameplay situations and their statistical rarity.

The emotional feedback system answers:

"How should this observed situation be interpreted?"

rather than:

"Was the player successful?"

Gameplay outcomes are observations.

Interpretations are generated separately.

---

# Input Data

The emotional feedback system operates exclusively on aggregated session observations.

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

No additional persistence is required.

---

# Derived State

## Round Count

```text
roundCount =
    winCount + loseCount
```

---

## Current Streak Type

WIN

if:

```text
isWinLastBet == true
```

otherwise:

LOSE

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

The previous streak type is always opposite to the current streak type.

---

## Previous Streak Length

```text
lastBrokenStreak
```

---

# Rarity Evaluation

The system evaluates the rarity of the current aggregated gameplay state.

The evaluated object is:

> The family of gameplay sessions compatible with the observed aggregated state.

The system intentionally avoids reconstruction of complete gameplay history.

---

# Rarity Score

The rarity score is defined as:

```text
rarityScore =
    -log2(P)
```

where:

```text
P
```

represents the estimated probability of the observed aggregated state.

---

# Rarity Classes

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

Threshold values are determined through simulation and balancing.

---

# Narrative Situations

The emotional feedback system operates on six narrative situations.

```text
OPTIONS_SHOWN_AFTER_WIN

OPTIONS_SHOWN_AFTER_LOSE

WIN_CONTINUES

WIN_STREAK_BROKEN

LOSE_CONTINUES

LOSE_STREAK_BROKEN
```

These situations represent narrative interpretation contexts rather than gameplay outcomes.

---

# Narrative State

Behavior is determined by:

```text
narrativeSituation
```

combined with:

```text
rarityClass
```

Therefore:

```text
6 narrative situations
×
7 rarity classes
=
42 narrative states
```

Narrative states are canonical behavioral identifiers.

---

# Narrative Interpretation Layer

Each narrative state maps to:

```text
Narrative State
    →
Narrative Interpretation
```

Narrative interpretation is defined by:

* CAPYBARA_NARRATIVE_MODEL

Narrative interpretation determines:

* what Capybara believes may be happening,
* what Capybara wants the player to observe,
* how Capybara frames the situation.

Narrative interpretation is the source of all subsequent feedback.

---

# Emotional Composition Layer

Each narrative interpretation maps to:

```text
Narrative Interpretation
    →
Emotion Composition
```

Emotional composition is defined by:

* CAPYBARA_EMOTION_MODEL

Emotions are reactions to interpretation.

Emotions are not reactions to gameplay events directly.

---

# Communication Layer

Each emotional composition maps to:

```text
Emotion Composition
    →
Communication Intent
```

Communication intent is defined by:

* CAPYBARA_COMMUNICATION_MODEL

Communication intent determines:

* what should be communicated,
* how attention should be directed,
* how Capybara relates to the player.

Communication intent acts as the canonical bridge between meaning and expression.

---

# Phrase Layer

Text generation is performed through:

```text
Communication Intent
    →
Phrase Selection
```

Phrase selection is defined by:

* CAPYBARA_PHRASE_MODEL

Phrases express communication intent.

Phrases do not express raw gameplay results.

---

# Visual Layer

Visual representation is performed through:

```text
Communication Intent
    →
Visual Expression
```

Visual expression is defined by:

* CAPYBARA_VISUAL_MODEL

Visuals express communication intent.

Visuals do not express rarity directly.

Visuals do not express gameplay results directly.

---

# Canonical Feedback Pipeline

The complete feedback pipeline is:

```text
Gameplay Observation
    →
Aggregated Gameplay State
    →
Rarity Evaluation
    →
Narrative Situation
    →
Narrative State
    →
Narrative Interpretation
    →
Emotion Composition
    →
Communication Intent
```

Communication Intent then drives:

```text
Communication Intent
    →
Phrase Selection

Communication Intent
    →
Visual Expression
```

---

# Emotional Semantics

The emotional feedback system intentionally allows both success and failure to become meaningful.

Examples:

* extraordinary winning streaks,
* extraordinary losing streaks,
* interruption of unusual patterns,
* unexpected changes in behavior.

Meaning emerges from rarity and interpretation.

Meaning does not emerge from outcome polarity alone.

---

# Architectural Separation

Gameplay Domain owns:

* gameplay truth

Observational Domain owns:

* gameplay observations

Narrative Model owns:

* interpretation

Emotion Model owns:

* emotional composition

Communication Model owns:

* communicative purpose

Phrase Model owns:

* textual realization

Visual Model owns:

* visual realization

The emotional feedback system coordinates these models.

The emotional feedback system does not own gameplay truth.

---

# Future Evolution

Future versions may refine:

* probability estimation,
* rarity thresholds,
* communication intent mapping,
* phrase generation,
* visual generation,
* procedural narrative generation.

Future evolution must preserve:

* aggregated-state evaluation,
* bounded complexity,
* narrative-first interpretation,
* separation of concerns,
* independence from gameplay outcome generation.

Narrative interpretation must always precede emotional expression.

