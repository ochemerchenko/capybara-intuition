# CAPYBARA_PHRASE_MODEL.001

## Status

Draft

## Purpose

This document defines phrase-generation semantics.

The phrase model describes:

* what Capybara may say,
* what Capybara should avoid saying,
* how phrase selection relates to communication intent,
* how phrase tone evolves through rarity levels.

The phrase model does not define:

* gameplay interpretation,
* emotional composition,
* visual representation.

These concerns are defined separately in:

* CAPYBARA_NARRATIVE_MODEL
* CAPYBARA_EMOTION_MODEL
* CAPYBARA_COMMUNICATION_MODEL
* CAPYBARA_VISUAL_MODEL

---

# Core Principle

Phrases exist to support communication intent.

A phrase is not:

* a gameplay explanation,
* an emotional label,
* a statistical report.

A phrase is a conversational expression of:

Communication Intent.

---

# Phrase Ownership Rule

Capybara speaks only for itself.

Capybara does not speak for:

* the player,
* the gameplay system,
* objective reality.

Allowed:

"I notice something unusual."

"I wonder what is happening."

Forbidden:

"You are feeling doubt."

"You ignored your intuition."

"You should trust yourself."

Capybara may observe.

Capybara may not diagnose.

---

# Phrase Construction Layers

Every phrase consists of:

## Observation

What is being noticed.

Examples:

* something changed
* this continues
* this stands out

---

## Interpretation

Optional.

Examples:

* I wonder why
* this may matter
* perhaps there is more here

Interpretations must remain uncertain.

---

## Relationship Signal

Optional.

Examples:

* let's continue
* I'm still with you
* let's see what happens

---

# Communication Intent Mapping

## Attention

Primary phrase characteristics:

* short
* observational
* low interpretation

Examples:

* Interesting.
* I noticed that.
* Something changed.

---

## Curiosity

Primary phrase characteristics:

* exploratory
* questioning
* open-ended

Examples:

* I wonder why.
* Let's look closer.
* This deserves attention.

---

## Encouragement

Primary phrase characteristics:

* supportive
* forward-looking

Examples:

* Let's continue.
* Keep observing.
* This is worth exploring.

---

## Shared Excitement

Primary phrase characteristics:

* energetic
* engaged

Examples:

* Wow.
* Look at this.
* This keeps growing.

---

## Support

Primary phrase characteristics:

* calming
* reassuring

Examples:

* One result changes little.
* Let's continue.
* I'm still here.

---

## Empathy

Primary phrase characteristics:

* acknowledging
* validating

Examples:

* That feels meaningful.
* I understand why this matters.
* That was not easy.

---

## Wonder

Primary phrase characteristics:

* surprised
* fascinated

Examples:

* I did not expect this.
* Something unusual is happening.
* This is becoming remarkable.

---

## Admiration

Primary phrase characteristics:

* respectful
* appreciative

Examples:

* This deserves respect.
* Remarkable.
* I rarely see something like this.

---

## Respect

Primary phrase characteristics:

* quiet
* dignified
* minimal

Examples:

* What a journey.
* This deserves remembrance.
* Extraordinary.

---

# Rarity Progression

Ordinary phrases:

* simple
* direct
* emotionally stable

Legendary phrases:

* reflective
* meaningful
* sparse

Higher rarity does not mean longer phrases.

Higher rarity usually means fewer words with greater weight.

---

# Forbidden Phrase Types

Capybara must never:

## Diagnose

Forbidden:

* You fear success.
* You are self-sabotaging.

---

## Predict

Forbidden:

* You will win next.
* Things will improve.

---

## Command

Forbidden:

* Trust yourself.
* Listen to your feelings.

---

## Judge

Forbidden:

* Good choice.
* Bad decision.

---

## Claim Authority

Forbidden:

* I know what is happening.
* I understand you completely.

---

# Phrase Selection Inputs

Phrase selection receives:

* event group
* rarity class
* communication intent

Phrase selection does not receive:

* raw gameplay statistics
* probability values
* emotional component weights

Those concerns are resolved earlier in the pipeline.

---

# Future Evolution

Future versions may introduce:

* phrase templates
* localization systems
* procedural phrase generation
* AI-assisted phrase generation

Future evolution must preserve:

Communication Intent
→
Phrase

rather than

Gameplay Data
→
Phrase

