# CAPYBARA_VISUAL_MODEL.001

## Status

Draft

## Purpose

This document defines visual communication semantics.

The visual model describes:

* what visual message should be communicated,
* what visual signals represent,
* how visual communication evolves through rarity levels.

The visual model does not define:

* emotional composition,
* narrative interpretation,
* phrase generation,
* gameplay mechanics.

These concerns are defined separately in:

* CAPYBARA_EMOTION_MODEL
* CAPYBARA_NARRATIVE_MODEL
* CAPYBARA_COMMUNICATION_MODEL

---

# Core Principle

Visuals exist to support communication.

Visuals do not exist to display emotions.

The player should perceive:

"What Capybara is trying to communicate."

not:

"What emotion name Capybara currently has."

---

# Visual Communication Layers

Every visual state consists of four independent layers.

## Attention Layer

Determines:

* gaze direction
* eye openness
* focus intensity

Communicates:

* attention
* distraction
* observation
* concentration

---

## Energy Layer

Determines:

* body activation
* posture tension
* movement intensity

Communicates:

* calmness
* engagement
* excitement
* astonishment

---

## Affiliation Layer

Determines:

* openness
* softness
* body orientation
* social warmth

Communicates:

* support
* empathy
* companionship
* acceptance

---

## Cognitive Layer

Determines:

* thinking posture
* questioning posture
* realization posture
* discovery posture

Communicates:

* reflection
* curiosity
* uncertainty
* insight

---

# Visual Intents

Visual communication is built around visual intents.

## Companion

Meaning:

I am with you.

Visual characteristics:

* soft posture
* relaxed body
* warm eyes

---

## Mentor

Meaning:

Look closer.

Visual characteristics:

* attentive gaze
* focused posture
* calm confidence

---

## Curious Observer

Meaning:

Something deserves investigation.

Visual characteristics:

* head tilt
* asymmetric expression
* exploratory attention

---

## Admirer

Meaning:

This deserves appreciation.

Visual characteristics:

* elevated attention
* visible fascination
* energetic engagement

---

# Rarity Progression

Rarity primarily affects:

* intensity
* focus
* energy

Rarity does not create entirely new personalities.

The same Capybara remains present.

---

# Event Group Visual Arcs

## OPTIONS_SHOWN_AFTER_WIN

Visual arc:

Support
→ Reflection
→ Admiration

Capybara gradually shifts from companion toward mentor.

---

## OPTIONS_SHOWN_AFTER_LOSE

Visual arc:

Support
→ Reflection
→ Investigation

Capybara gradually shifts from companion toward reflective mentor.

---

## WIN_CONTINUES

Visual arc:

Encouragement
→ Admiration
→ Celebration

Energy increases significantly.

---

## WIN_STREAK_BROKEN

Visual arc:

Empathy
→ Perspective
→ Respect

Energy decreases.

Warmth increases.

---

## LOSE_CONTINUES

Visual arc:

Support
→ Concern
→ Investigation

Capybara increasingly focuses on understanding the pattern.

Thinking-related signals become stronger.

---

## LOSE_STREAK_BROKEN

Visual arc:

Relief
→ Surprise
→ Curiosity

Early tiers emphasize emotional release.

Higher tiers emphasize uncertainty and model revision.

---

# Sprite System

Sprites are implementation assets.

Sprites are not canonical emotional states.

A sprite should represent:

Visual Intent
+
Rarity Intensity
+
Narrative Context

rather than:

Emotion Name

---

# Visual Consistency Rule

The same visual intent may appear in multiple gameplay situations.

The same gameplay situation may be represented through multiple visual implementations.

Visual meaning is canonical.

Sprite assets are replaceable.

---

# Future Evolution

Future versions may introduce:

* animation layers,
* facial systems,
* pose systems,
* procedural expression generation,
* dynamic visual blending.

Future evolution must preserve:

Visual Intent
→
Visual Expression

rather than

Sprite
→
Meaning

