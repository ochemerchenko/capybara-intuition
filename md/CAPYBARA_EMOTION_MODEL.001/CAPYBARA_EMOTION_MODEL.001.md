# CAPYBARA_EMOTION_MODEL.001

## Status

Approved

## Purpose

This document defines the emotional composition of Capybara emotional states.

It does not define narrative meaning, communication content, or visual representation.

Those concerns are described in:

* CAPYBARA_NARRATIVE_MODEL
* CAPYBARA_COMMUNICATION_MODEL
* CAPYBARA_VISUAL_MODEL

---

# Emotional Components

Every Capybara emotion is represented as a weighted mixture of the following emotional components.

## Empathy

Ability to emotionally accompany the player.

Typical expressions:

* support
* compassion
* acceptance
* emotional presence

---

## Mentor

Ability to guide player attention toward self-observation.

Typical expressions:

* reflection
* guidance
* reframing
* teaching

---

## Curiosity

Interest toward observed player behavior.

Typical expressions:

* observation
* investigation
* uncertainty
* questioning

---

## Admiration

Respect, inspiration, amazement or fascination toward observed events.

Typical expressions:

* delight
* wonder
* appreciation
* admiration

---

# Event Groups

## OPTIONS_SHOWN_AFTER_WIN

### Emotional Arc

Player won previous round and begins a new one.

Capybara encourages continued self-observation while gently reinforcing trust in feelings.

| Tier        | Empathy | Mentor | Curiosity | Admiration |
| ----------- | ------- | ------ | --------- | ---------- |
| Ordinary    | 70      | 10     | 20        | 0          |
| Interesting | 50      | 30     | 20        | 0          |
| Unusual     | 40      | 40     | 20        | 0          |
| Rare        | 30      | 50     | 10        | 10         |
| Amazing     | 25      | 50     | 10        | 15         |
| Incredible  | 20      | 40     | 10        | 30         |
| Legendary   | 15      | 25     | 10        | 50         |

---

## OPTIONS_SHOWN_AFTER_LOSE

### Emotional Arc

Player lost previous round and begins a new one.

Capybara encourages continued self-observation while gradually investigating the relationship between feelings and desires.

| Tier        | Empathy | Mentor | Curiosity | Admiration |
| ----------- | ------- | ------ | --------- | ---------- |
| Ordinary    | 70      | 10     | 20        | 0          |
| Interesting | 50      | 30     | 20        | 0          |
| Unusual     | 40      | 40     | 20        | 0          |
| Rare        | 30      | 50     | 10        | 10         |
| Amazing     | 25      | 50     | 10        | 15         |
| Incredible  | 20      | 40     | 10        | 30         |
| Legendary   | 15      | 25     | 10        | 50         |

---

## WIN_CONTINUES

### Emotional Arc

Player continues a winning sequence.

Capybara observes successful growth and gradually shifts from support toward admiration.

| Tier        | Empathy | Mentor | Curiosity | Admiration |
| ----------- | ------- | ------ | --------- | ---------- |
| Ordinary    | 70      | 20     | 10        | 0          |
| Interesting | 50      | 30     | 20        | 0          |
| Unusual     | 40      | 30     | 30        | 0          |
| Rare        | 20      | 40     | 30        | 10         |
| Amazing     | 20      | 35     | 25        | 20         |
| Incredible  | 10      | 30     | 20        | 40         |
| Legendary   | 10      | 20     | 10        | 60         |

---

## WIN_STREAK_BROKEN

### Emotional Arc

Player loses after a winning sequence.

Capybara supports the player while helping them integrate the experience into continued growth.

| Tier        | Empathy | Mentor | Curiosity | Admiration |
| ----------- | ------- | ------ | --------- | ---------- |
| Ordinary    | 60      | 25     | 15        | 0          |
| Interesting | 60      | 30     | 10        | 0          |
| Unusual     | 60      | 35     | 5         | 0          |
| Rare        | 55      | 45     | 0         | 0          |
| Amazing     | 45      | 50     | 0         | 5          |
| Incredible  | 35      | 55     | 0         | 10         |
| Legendary   | 25      | 60     | 0         | 15         |

---

## LOSE_CONTINUES

### Emotional Arc

Player continues a losing sequence.

Capybara gradually moves from emotional support toward investigation of possible self-rejection, self-doubt, or refusal of success.

| Tier        | Empathy | Mentor | Curiosity | Admiration |
| ----------- | ------- | ------ | --------- | ---------- |
| Ordinary    | 80      | 20     | 0         | 0          |
| Interesting | 65      | 35     | 0         | 0          |
| Unusual     | 45      | 50     | 5         | 0          |
| Rare        | 25      | 60     | 10        | 5          |
| Amazing     | 15      | 60     | 15        | 10         |
| Incredible  | 10      | 50     | 20        | 20         |
| Legendary   | 5       | 40     | 25        | 30         |

---

## LOSE_STREAK_BROKEN

### Emotional Arc

Player wins after a losing sequence.

Capybara first experiences relief and admiration, then gradually transitions into observation and model revision.

| Tier        | Empathy | Mentor | Curiosity | Admiration |
| ----------- | ------- | ------ | --------- | ---------- |
| Ordinary    | 60      | 30     | 0         | 10         |
| Interesting | 50      | 30     | 0         | 20         |
| Unusual     | 40      | 30     | 0         | 30         |
| Rare        | 30      | 50     | 20        | 0          |
| Amazing     | 20      | 50     | 30        | 0          |
| Incredible  | 10      | 50     | 40        | 0          |
| Legendary   | 0       | 50     | 50        | 0          |

---

# Design Rule

Emotion tiers do not describe the player.

Emotion tiers describe Capybara's internal emotional state while observing the player.

The same gameplay event may therefore produce different Capybara emotions depending on accumulated historical observations.

---

# Separation of Concerns

CAPYBARA_EMOTION_MODEL
defines emotional composition

CAPYBARA_NARRATIVE_MODEL
defines interpretation and teaching intent

CAPYBARA_COMMUNICATION_MODEL
defines communication principles

CAPYBARA_VISUAL_MODEL
defines visual representation

CAPYBARA_PHRASE_MODEL
defines phrase generation and phrase selection

