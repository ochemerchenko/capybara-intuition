# CAPYBARA_EMOTION_MODEL.002

## Status

Approved

## Purpose

This document defines Capybara emotional composition.

The emotion model describes:

- emotional dimensions,
- emotional weights,
- emotional evolution across rarity tiers,
- emotional reactions to narrative interpretation.

The emotion model does not define:

- gameplay interpretation,
- teaching intent,
- communication strategy,
- phrase generation,
- visual representation.

These concerns are defined separately in:

- CAPYBARA_NARRATIVE_MODEL
- CAPYBARA_COMMUNICATION_MODEL
- CAPYBARA_PHRASE_MODEL
- CAPYBARA_VISUAL_MODEL

---

# Architectural Position

Capybara emotions are not generated directly from gameplay events.

Capybara emotions emerge as a reaction to Capybara's narrative interpretation of observed gameplay.

Canonical flow:

Gameplay Observation
    →
Narrative Interpretation
    →
Emotion Composition
    →
Communication Intent
    →
Visual Representation

Communication Intent
    →
Phrase Selection

Emotion composition is therefore a secondary layer.

Narrative interpretation always precedes emotional response.

---

# Emotional Components

Every Capybara emotional state is represented as a weighted mixture of emotional components.

Component weights are expressed as relative proportions.

Weights are intended to guide communication behavior rather than simulate psychological realism.

---

## Empathy

Represents emotional presence.

Questions:

- Can Capybara emotionally accompany the player?
- Can Capybara acknowledge emotional significance?

Typical manifestations:

- warmth
- acceptance
- companionship
- support

---

## Mentor

Represents teaching attention.

Questions:

- Can Capybara help the player observe themselves?
- Can Capybara redirect attention toward self-knowledge?

Typical manifestations:

- guidance
- reframing
- reflection
- teaching

---

## Curiosity

Represents investigative attention.

Questions:

- Does Capybara feel uncertainty?
- Does Capybara wish to understand more?

Typical manifestations:

- questioning
- observation
- exploration
- model revision

---

## Admiration

Represents appreciation of observed phenomena.

Questions:

- Does Capybara feel respect?
- Does Capybara perceive something extraordinary?

Typical manifestations:

- wonder
- fascination
- inspiration
- respect

---

# Emotional Evolution Principle

Rarity progression does not simply increase emotional intensity.

Rarity progression may:

- increase emotional intensity,
- change emotional composition,
- shift emotional priorities,
- alter Capybara's interpretation strategy.

Different event groups therefore exhibit different emotional arcs.

---

# Event Group Identity

Each event group represents a distinct emotional journey.

Emotional weights should always be interpreted within the context of that journey.

---

## OPTIONS_SHOWN_AFTER_WIN

Narrative focus:

Trust in self-observation.

Emotional progression:

Empathy
    →
Mentorship
    →
Admiration

---

## OPTIONS_SHOWN_AFTER_LOSE

Narrative focus:

Exploration of tension between desires and feelings.

Emotional progression:

Empathy
    →
Mentorship
    →
Curious reflection

---

## WIN_CONTINUES

Narrative focus:

Observation of successful growth.

Emotional progression:

Support
    →
Curiosity
    →
Admiration

---

## WIN_STREAK_BROKEN

Narrative focus:

Integration of disappointment.

Emotional progression:

Empathy
    →
Perspective
    →
Respect

---

## LOSE_CONTINUES

Narrative focus:

Investigation of persistent rejection of success.

Emotional progression:

Support
    →
Investigation
    →
Recognition of a meaningful pattern

---

## LOSE_STREAK_BROKEN

Narrative focus:

Revision of Capybara's previous interpretation.

Emotional progression:

Relief
    →
Fascination
    →
Curiosity

This is the only event group where increasing rarity corresponds to decreasing certainty.

Higher tiers represent increasing willingness to revise existing models.

---

# Emotional Matrices

## OPTIONS_SHOWN_AFTER_WIN

| Tier | Empathy | Mentor | Curiosity | Admiration |
|--------|----------|----------|----------|----------|
| Ordinary | 70 | 10 | 20 | 0 |
| Interesting | 50 | 30 | 20 | 0 |
| Unusual | 40 | 40 | 20 | 0 |
| Rare | 30 | 50 | 10 | 10 |
| Amazing | 25 | 50 | 10 | 15 |
| Incredible | 20 | 40 | 10 | 30 |
| Legendary | 15 | 25 | 10 | 50 |

---

## OPTIONS_SHOWN_AFTER_LOSE

| Tier | Empathy | Mentor | Curiosity | Admiration |
|--------|----------|----------|----------|----------|
| Ordinary | 70 | 10 | 20 | 0 |
| Interesting | 50 | 30 | 20 | 0 |
| Unusual | 40 | 40 | 20 | 0 |
| Rare | 30 | 50 | 10 | 10 |
| Amazing | 25 | 50 | 10 | 15 |
| Incredible | 20 | 40 | 10 | 30 |
| Legendary | 15 | 25 | 10 | 50 |

---

## WIN_CONTINUES

| Tier | Empathy | Mentor | Curiosity | Admiration |
|--------|----------|----------|----------|----------|
| Ordinary | 70 | 20 | 10 | 0 |
| Interesting | 50 | 30 | 20 | 0 |
| Unusual | 40 | 30 | 30 | 0 |
| Rare | 20 | 40 | 30 | 10 |
| Amazing | 20 | 35 | 25 | 20 |
| Incredible | 10 | 30 | 20 | 40 |
| Legendary | 10 | 20 | 10 | 60 |

---

## WIN_STREAK_BROKEN

| Tier | Empathy | Mentor | Curiosity | Admiration |
|--------|----------|----------|----------|----------|
| Ordinary | 60 | 25 | 15 | 0 |
| Interesting | 60 | 30 | 10 | 0 |
| Unusual | 60 | 35 | 5 | 0 |
| Rare | 55 | 45 | 0 | 0 |
| Amazing | 45 | 50 | 0 | 5 |
| Incredible | 35 | 55 | 0 | 10 |
| Legendary | 25 | 60 | 0 | 15 |

---

## LOSE_CONTINUES

| Tier | Empathy | Mentor | Curiosity | Admiration |
|--------|----------|----------|----------|----------|
| Ordinary | 80 | 20 | 0 | 0 |
| Interesting | 65 | 35 | 0 | 0 |
| Unusual | 45 | 50 | 5 | 0 |
| Rare | 25 | 60 | 10 | 5 |
| Amazing | 15 | 60 | 15 | 10 |
| Incredible | 10 | 50 | 20 | 20 |
| Legendary | 5 | 40 | 25 | 30 |

---

## LOSE_STREAK_BROKEN

| Tier | Empathy | Mentor | Curiosity | Admiration |
|--------|----------|----------|----------|----------|
| Ordinary | 60 | 30 | 0 | 10 |
| Interesting | 50 | 30 | 0 | 20 |
| Unusual | 40 | 30 | 0 | 30 |
| Rare | 30 | 50 | 20 | 0 |
| Amazing | 20 | 50 | 30 | 0 |
| Incredible | 10 | 50 | 40 | 0 |
| Legendary | 0 | 50 | 50 | 0 |

---

# Canonical Rules

Emotion weights describe Capybara's internal emotional composition.

Emotion weights do not describe:

- player personality,
- player emotional state,
- player intentions,
- player motivations.

Emotion weights influence communication.

Emotion weights do not directly generate communication.

Communication Intent remains the canonical bridge between:

Emotion
    →
Phrase

and

Emotion
    →
Visual Expression

---

# Separation Of Concerns

CAPYBARA_NARRATIVE_MODEL
    owns meaning

CAPYBARA_EMOTION_MODEL
    owns emotional composition

CAPYBARA_COMMUNICATION_MODEL
    owns communication intent

CAPYBARA_PHRASE_MODEL
    owns textual realization

CAPYBARA_VISUAL_MODEL
    owns visual realization
