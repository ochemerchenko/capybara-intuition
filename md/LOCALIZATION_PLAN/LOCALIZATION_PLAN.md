# LOCALIZATION_PLAN

## Version

0.16

## Last Updated

2026-05-25

---

# Overview

This document defines the bounded localization strategy for the first public release and the immediate post-publication stabilization phase.

The localization system intentionally prioritizes:

* publication-safe translation quality,
* deterministic semantic consistency,
* supportability,
* bounded language expansion,
* explainable UX,
* emotionally coherent tone,
* trust-oriented communication,
* deployment-safe localization infrastructure.

The project intentionally avoids:

* aggressive globalization,
* automatic AI-only translation pipelines,
* runtime-generated localization,
* speculative localization infrastructure,
* large-scale language expansion before publication stabilization.

The localization strategy intentionally optimizes for:

```text
high-trust bounded multilingual deployment
```

instead of:

```text
maximum language count
```

---

# Current Localization Status

Current localization coverage:

| Language | Status |
|---|---|
| Ukrainian | Partial |
| English | Partial |

Current runtime state:

```text
localization incomplete
```

Current publication target:

```text
publication-safe EN + UK localization
```

:contentReference[oaicite:0]{index=0}
:contentReference[oaicite:1]{index=1}

---

# Localization Philosophy

The project intentionally treats localization as:

```text
semantic UX infrastructure
```

NOT:

```text
string replacement mechanics
```

The localization layer must preserve:

* calm emotional tone,
* trust-oriented UX,
* bounded semantics,
* deterministic runtime meaning,
* cognitive readability,
* emotionally lightweight interaction.

Localization intentionally avoids:

* aggressive gamification language,
* manipulative retention language,
* pseudo-scientific claims,
* dark-pattern monetization vocabulary,
* overstimulating UX copy.

---

# Core Tone Requirements

The product voice should remain:

* calm,
* understandable,
* emotionally lightweight,
* non-aggressive,
* non-corporate,
* non-manipulative,
* cognitively respectful.

The localization layer must preserve:

```text
quiet trustworthy cognitive interaction
```

instead of:

```text
hyper-gamified engagement language
```

---

# Localization Scope

The localization system currently includes:

* UI strings,
* onboarding,
* recovery runtime,
* statistics explanations,
* markdown help content,
* profile runtime,
* gameplay mode labels,
* Play Store metadata,
* support infrastructure,
* error messages,
* capability-related UI,
* monetization UI,
* Capybara emotional vocabulary.

---

# Localization Priority Order

---

# Phase 1 — Publication-Critical Localization

## Goal

Reach:

```text
publication-safe bilingual deployment
```

Languages:

* Ukrainian,
* English.

---

## Required Coverage

### Gameplay Runtime

- gameplay mode labels,
- round flow labels,
- answer/result semantics,
- runtime transitions,
- pause/continue semantics,
- profile switching semantics.

---

### Recovery Runtime

- recovery challenge wording,
- arithmetic wording,
- temporal semantic wording,
- parent authorization wording,
- PIN recovery UX,
- recovery error states.

Special attention required because recovery runtime already contains:

```text
bounded human-semantic arithmetic
```

and incorrect translation can break:

* readability,
* solvability,
* trustworthiness.

---

### Statistics Runtime

- graph explanations,
- baseline explanations,
- insight descriptions,
- trend explanations,
- uncertainty explanations,
- analytics labels.

Statistics localization MUST preserve:

```text
explainability
```

instead of:

```text
marketing exaggeration
```

---

### Profile Runtime

- HUMAN semantics,
- CHILD semantics,
- parent authorization semantics,
- protected transition wording,
- topology-aware runtime semantics.

---

### Publication Assets

- Play Store short description,
- Play Store long description,
- screenshots,
- onboarding copy,
- privacy policy,
- support/contact text.

---

# Phase 2 — Publication-Stable Expansion

Additional target languages after stabilization:

| Priority | Language |
|---|---|
| High | Spanish |
| High | Portuguese |
| Medium | German |
| Medium | French |
| Medium | Polish |

These priorities are based on:

* market size,
* cognitive-training audience fit,
* Play Store accessibility,
* translation ROI,
* supportability expectations.

:contentReference[oaicite:2]{index=2}

---

# Phase 3 — Long-Term Expansion

Potential future languages:

* Italian,
* Turkish,
* Japanese,
* Korean.

These are intentionally postponed until after:

```text
stable publication deployment
```

---

# Localization Architecture

The localization system intentionally prioritizes:

```text
explicit bounded translation ownership
```

instead of:

```text
distributed string chaos
```

---

# Current Localization Categories

---

# 1. Runtime UI Strings

Includes:

* buttons,
* labels,
* dialogs,
* navigation,
* profile actions,
* statistics labels,
* gameplay actions.

---

# 2. Markdown Explainability

Includes:

* help screens,
* analytics explanations,
* onboarding guidance,
* statistics interpretation,
* mode explanations.

Markdown content intentionally remains:

```text
passive declarative explainability
```

NOT:

```text
runtime scripting
```

:contentReference[oaicite:3]{index=3}

---

# 3. Emotional Vocabulary

Includes:

* Capybara reactions,
* emotional microcopy,
* motivational phrases,
* idle reactions,
* lightweight behavioral responses.

This category requires:

```text
emotional tone preservation
```

instead of:

```text
literal direct translation
```

---

# 4. Recovery Runtime Semantics

This is considered:

```text
high-risk localization infrastructure
```

because translation errors can create:

* ambiguous arithmetic,
* unreadable tasks,
* invalid semantic interpretation,
* cognitive confusion.

Recovery localization MUST remain:

* short,
* deterministic,
* grammatically simple,
* semantically unambiguous.

---

# Recovery Runtime Translation Rules

Recovery semantics MUST avoid:

* long grammar chains,
* culturally ambiguous temporal wording,
* overloaded calendar semantics,
* linguistically complex arithmetic.

Examples requiring careful localization:

* previous month,
* next weekday,
* current day of month,
* subtract tomorrow from today.

The generator intentionally does NOT attempt full formal calendar semantics.

Localization must preserve:

```text
bounded human-semantic arithmetic
```

:contentReference[oaicite:4]{index=4}
:contentReference[oaicite:5]{index=5}

---

# Capybara Localization

Capybara UX intentionally acts as:

```text
emotionally lightweight runtime layer
```

The localization strategy should preserve:

* warmth,
* calmness,
* non-aggressive personality,
* emotional softness,
* low-pressure interaction.

Capybara localization MUST avoid:

* meme slang,
* corporate jokes,
* internet irony overload,
* childish over-animation,
* manipulative emotional pressure.

---

# Technical Localization Constraints

---

# Deterministic Runtime Semantics

Localization MUST NOT change:

* gameplay semantics,
* arithmetic meaning,
* recovery correctness,
* statistics interpretation,
* profile transition meaning.

---

# Canonical Semantic IDs

Canonical identifiers remain:

```text
non-localized
```

Examples:

```text
AFTER
BEFORE
HYBRID
CAPYBARA
CHECKER
```

Human-readable labels remain localized separately.

:contentReference[oaicite:6]{index=6}
:contentReference[oaicite:7]{index=7}

---

# Supportability Constraints

Localization infrastructure must remain:

* deterministic,
* explicit,
* searchable,
* maintainable,
* deployment-safe.

The project intentionally avoids:

* runtime translation synthesis,
* AI-generated runtime localization,
* hidden translation fallbacks,
* multi-source translation ownership.

---

# Localization QA Checklist

---

# Publication-Critical Validation

- [ ] no mixed-language runtime states
- [ ] no untranslated publication UI
- [ ] no placeholder strings
- [ ] no broken pluralization
- [ ] no clipped text
- [ ] no overflow on small screens
- [ ] recovery runtime fully readable
- [ ] statistics explanations understandable
- [ ] onboarding emotionally coherent
- [ ] Play Store metadata localized
- [ ] support email localized
- [ ] privacy policy localized

---

# Recovery Runtime Validation

- [ ] arithmetic wording understandable
- [ ] subtraction semantics understandable
- [ ] temporal wording deterministic
- [ ] no ambiguous weekday wording
- [ ] no invalid month wording
- [ ] no unreadable grammatical chains

---

# Emotional Tone Validation

- [ ] Capybara tone calm
- [ ] no manipulative copy
- [ ] no aggressive CTA language
- [ ] no hyper-gamified wording
- [ ] no corporate-style UX jargon

---

# Store Metadata Strategy

The store presentation should localize:

* title,
* short description,
* long description,
* screenshots,
* captions,
* onboarding illustrations.

The publication strategy intentionally prioritizes:

```text
clarity and trust
```

instead of:

```text
marketing aggression
```

The project intentionally avoids:

* “train your IQ instantly,”
* “become smarter fast,”
* pseudo-scientific promises,
* neuroplasticity exaggeration.

:contentReference[oaicite:8]{index=8}

---

# Localization Infrastructure Direction

Current preferred direction:

```text
simple explicit localization ownership
```

instead of:

```text
globalized localization platform engineering
```

Preferred characteristics:

* bounded translation files,
* explicit ownership,
* deterministic runtime mapping,
* publication-safe maintainability.

---

# Future Localization Opportunities

After publication stabilization:

* localization-specific screenshots,
* language-specific onboarding,
* regionalized emotional vocabulary,
* educational blog localization,
* SEO landing pages,
* multilingual support center.

These are intentionally postponed until after:

```text
stable public deployment
```

---

# Strategic Localization Principle

The localization strategy intentionally optimizes for:

```text
calm trustworthy multilingual interaction
```

NOT:

```text
maximum localization scale
```

The first release should prioritize:

* translation correctness,
* emotional coherence,
* runtime readability,
* supportability,
* deterministic semantics,
* publication safety,
* trust-oriented UX.
