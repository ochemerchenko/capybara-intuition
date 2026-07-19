# USER_ANALYTIC_GRAPHICS_BASELINE_MODEL

## Purpose

This document defines the probabilistic baseline model used by user-facing analytics.

The purpose of the baseline model is to answer the question:

> Is the observed user behavior still explainable by a random model?

The baseline model does not describe:

* chart rendering;
* trend visualization;
* interpolation algorithms;
* EMA calculations;
* user behavior reconstruction.

These concerns are defined separately.

---

# Core Principle

The baseline model represents the behavior of a completely random player.

The random player:

* has no skill;
* has no intuition;
* has no strategy;
* performs independent random selections.

The baseline model therefore represents the expected outcome of pure chance.

---

# Baseline

## Definition

For a gameplay level:

```text
Level = N
```

exactly one option is considered successful.

The probability of success for a random selection is:

```text
Baseline = 1 / N
```

---

## Examples

```text
Level 2
Baseline = 0.5000

Level 3
Baseline = 0.3333

Level 4
Baseline = 0.2500

Level 8
Baseline = 0.1250

Level 12
Baseline = 0.0833
```

---

# Interpretation

Baseline is not derived from user behavior.

Baseline is not derived from historical observations.

Baseline is a property of gameplay mathematics.

Baseline represents the expected density produced by random guessing.

---

# Random Round Model

Each round is modeled as an independent Bernoulli experiment.

Possible outcomes:

```text
WIN
LOSE
```

Probability of success:

```text
p = Baseline
```

Probability of failure:

```text
q = 1 - p
```

---

# Random Session Model

A session consists of multiple independent rounds.

Expected Session Density:

```text
E(SessionDensity) = p
```

where:

```text
p = Baseline
```

---

# Random Day Model

A day consists of one or more sessions.

Day Density is defined in:

```text
USER_ANALYTIC_GRAPHICS_DOMAIN_MODEL
```

For a random player:

```text
E(DayDensity) = Baseline
```

regardless of the number of sessions.

---

# Baseline Corridor

## Purpose

Baseline alone is insufficient.

Even a completely random player will not produce exactly:

```text
Baseline
```

every day.

Observed results fluctuate around Baseline.

The Baseline Corridor describes these expected fluctuations.

---

# Statistical Model

For:

```text
p = Baseline
```

and

```text
n = RoundCount
```

the expected value is:

```text
μ = p
```

Variance:

```text
σ² = p(1-p)/n
```

Standard deviation:

```text
σ = sqrt(p(1-p)/n)
```

---

# Bessel Correction

Whenever variance is estimated from observed data rather than calculated from the theoretical model, Bessel correction should be applied.

For the theoretical random model:

```text
σ² = p(1-p)/n
```

remains authoritative.

---

# Corridor Construction

The corridor is centered on Baseline:

```text
Baseline ± Kσ
```

where:

```text
K
```

is a configurable confidence coefficient.

Examples:

```text
K = 1
≈ 68%

K = 2
≈ 95%

K = 3
≈ 99.7%
```

---

# Corridor Inputs

Baseline Corridor depends only on:

```text
Level
RoundCount
```

It does not depend on:

```text
User History

User Trend

User Density

User Activity
```

---

# Sliding Window Principle

Using all historical observations would eventually produce:

```text
σ → 0
```

which would make the corridor unusable.

Using only one day would produce excessive instability.

Therefore Baseline Corridor is calculated using a sliding observation window.

---

# Observation Window

Current analytical window:

```text
30 calendar days
```

For every day D:

```text
RoundCount(Day) = all day rounds 
for any day in [D - 29 ; D]
```

for the selected level.

---

# Corridor Evolution

As RoundCount increases:

```text
σ
```

decreases.

Therefore:

```text
Baseline Corridor
```

gradually narrows.

The corridor asymptotically converges toward:

```text
Baseline
```

but never becomes exactly zero for a finite sample.

---

# Interpretation

If observed behavior remains inside the corridor:

> Random guessing remains a plausible explanation.

If observed behavior consistently leaves the corridor:

> Random guessing is no longer a sufficient explanation.

The corridor therefore represents:

```text
Region Of Randomness
```

for the selected level and observation window.

---

# Relationship To User Trend

Baseline Corridor describes:

```text
Expected Random Behavior
```

User Trend describes:

```text
Observed User Behavior
```

The primary analytical comparison is:

```text
UserTrend
vs
BaselineCorridor
```

---

# Canonical User Question

The baseline model exists to answer:

> Am I still behaving like a random process?

The graphical representation becomes:

```text
Baseline Corridor

+
User Trend
```

No additional statistical interpretation is required from the user.

The visual comparison itself provides the answer.

---

# Explicitly Rejected Concepts

## Lifetime Round Count

Using all rounds since installation.

Reason:

```text
σ → 0
```

and the corridor eventually loses practical value.

---

## Single-Day Corridor

Using only rounds played during one day.

Reason:

The corridor becomes excessively unstable and noisy.

---

## Synthetic Historical Data

Artificial observations must never be added to increase RoundCount.

Only real observations participate in Baseline Corridor calculations.

---

# Current Scope

This document formally defines:

```text
Baseline

Random Round Model

Random Session Model

Random Day Model

Baseline Corridor

Sliding Window
```

All future probabilistic analytics must be derived from these concepts.

---

# Open Questions

## OPEN-01

Default confidence coefficient:

```text
1σ
2σ
3σ
```

---

## OPEN-02

Adaptive confidence coefficients for different chart types.

---

## OPEN-03

Support for multiple observation windows:

```text
30 days
90 days
180 days
```

---

## OPEN-04

Future insight-generation rules based on Baseline Corridor crossing events.

