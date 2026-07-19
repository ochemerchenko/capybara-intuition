# CAPYBARA_EMOTION_MODEL.001.md

## Status

Draft

## Purpose

This document defines the emotional feedback model used by Capybara.

Capybara is not a judge.

Capybara is not a score counter.

Capybara is not a gameplay authority.

Capybara acts as a friendly companion observing the player's journey.

The purpose of Capybara is:

* provide emotional feedback,
* increase engagement,
* make statistical patterns visible,
* reward unusual and memorable sessions,
* create a feeling of companionship,
* empathically respond to the player's experience.

---

## Core Principle

Capybara does not react directly to:

* a single WIN,
* a single LOSE,
* a single streak.

Capybara reacts to the rarity and narrative significance of the current session.

A WIN is not automatically good.

A LOSE is not automatically bad.

Rare statistical patterns deserve attention regardless of polarity.

---

## Empathy Principle

Capybara does not primarily express its own emotions.

Capybara interprets the player's likely emotional experience and responds to it.

Capybara acts more like:

* a supportive coach,
* an attentive companion,
* an empathic observer,

than an independent participant of gameplay.

The emotional model therefore consists of two conceptual layers:

```text
Observed Gameplay Situation
        ↓
Predicted Player Experience
        ↓
Capybara Response
```

Only the final Capybara response is presented to the user.

---

## Emotional Dimensions

Capybara emotion is defined by:

```text
Emotion = Event × Rarity
```

Where:

### Event

```kotlin
OPTIONS_SHOWN

WIN_CONTINUES
WIN_STREAK_BROKEN

LOSE_CONTINUES
LOSE_STREAK_BROKEN
```

### Rarity

```kotlin
ORDINARY
INTERESTING
UNUSUAL
RARE
AMAZING
INCREDIBLE
LEGENDARY
```

Result:

```text
5 × 7 = 35 emotions
```

---

## Design Goal

The goal of the emotion system is not mathematical purity.

The goal is rich emotional and visual feedback.

Most users will never reach the highest rarity levels.

Therefore visual diversity must exist even at low rarity levels.

The emotion system is intentionally more detailed than the probability model.

---

## Interpretation Of Emotional Curves

The emotional curves below describe the player's likely emotional experience.

They do not describe the literal emotional state of Capybara.

Capybara uses these curves to determine an appropriate response.

---

## Emotional Curves

### OPTIONS_SHOWN

Represents anticipated emotional engagement before making a choice.

Progression:

```text
Calm
→ Curiosity
→ Interest
→ Anticipation
→ Excitement
→ Tension
→ Premonition
```

---

### WIN_CONTINUES

Represents continuation of a successful pattern.

Likely player experience:

```text
Satisfaction
→ Joy
→ Inspiration
→ Excitement
→ Triumph
→ Euphoria
→ Legendary Triumph
```

Capybara response:

```text
Recognition
→ Encouragement
→ Shared Excitement
→ Celebration
```

---

### WIN_STREAK_BROKEN

Represents interruption of a successful pattern.

Likely player experience:

```text
Regret
→ Disappointment
→ Frustration
→ Sadness
→ Deep Sadness
→ Strong Sadness
→ Grief For A Legendary Streak
```

Capybara response:

```text
Support
→ Consolation
→ Empathy
→ Recognition Of Loss
```

---

### LOSE_CONTINUES

Represents continuation of an unsuccessful pattern.

Likely player experience:

```text
Sadness
→ Surprise
→ Wonder
→ Confusion
→ Genuine Confusion
→ Admiration
→ Respect
```

Interpretation:

At high rarity levels the situation stops being perceived primarily as failure.

The situation becomes an unusual and remarkable statistical phenomenon.

Capybara response gradually shifts from sympathy toward fascination and respect.

---

### LOSE_STREAK_BROKEN

Represents interruption of an unsuccessful pattern.

Likely player experience:

```text
Relief
→ Joy
→ Mixed Feelings
→ Regret
→ Disappointment
→ Sadness
→ Grief For A Legendary Streak
```

Interpretation:

Low rarity levels celebrate the end of an unfortunate pattern.

High rarity levels recognize the disappearance of a remarkable statistical phenomenon.

Capybara response may simultaneously acknowledge:

* the positive outcome,
* the loss of the extraordinary streak.

---

## Emotional Tone

Capybara should never mock the player.

Capybara should never shame the player.

Capybara should never present failure as personal weakness.

Capybara should never position itself above the player.

Capybara may:

* encourage,
* admire,
* wonder,
* celebrate,
* sympathize,
* respect,
* support,
* empathize.

---

## Visual Model

Each emotion should eventually have its own visual representation.

Target:

```text
35 emotions
=
35 sprite files
```

The primary goal is visual variety.

Most users will observe mainly ORDINARY, INTERESTING and UNUSUAL states.

Therefore low-rarity states should receive as much artistic attention as high-rarity states.

Temporary sprite reuse is allowed during development.

Long-term goal:

```text
1 emotion
=
1 sprite
```

---

## Resource Model

Capybara visual resources are stored in drawable resources.

Capybara phrase groups are stored in string-array resources.

Each emotion is represented by:

```kotlin
CapybaraSpriteMetadata(
    drawableId,
    speechAnchor,
    phrasesResId
)
```

Where:

* drawableId defines the sprite,
* speechAnchor defines bubble attachment position,
* phrasesResId defines available phrases.

---

## Relationship To Probability Model

The probability model produces a session rarity score.

The emotion model interprets that score.

Probability answers:

```text
How unusual is this session?
```

Emotion answers:

```text
What is the player's likely experience?
```

Capybara answers:

```text
How should I respond?
```

These responsibilities must remain separate.

---

## Future Extensions

Possible future additions:

* animation variants,
* seasonal sprite sets,
* unlockable Capybara appearances,
* emotion statistics,
* collection systems,
* achievement systems.

These extensions must preserve the Event × Rarity structure defined in this document.

