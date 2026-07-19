# USER_ANALYTIC_GRAPHICS_DOMAIN_MODEL

## Purpose

This document describes the domain model used to build user-facing analytic graphics.

The goal of analytics is not to visualize gameplay internals.

The goal of analytics is to help the user understand:

* activity;
* dynamics;
* progress;
* regression;
* stability;
* deviation from probabilistic expectations.

This document intentionally focuses on domain concepts.

Visualization techniques, chart rendering, smoothing algorithms, moving averages, trend indicators, rendering optimizations and presentation details are secondary concerns and must be built on top of the domain model defined here.

---

# Domain Principles

## Analytics Must Be User-Centric

Analytics exists for the user.

The primary question is not:

> What data can be calculated?

The primary question is:

> What information does the user need to understand their current state and dynamics?

---

## Presentation Must Not Affect Analytics

Analytical results must not depend on:

* screen width;
* screen height;
* device type;
* chart resolution;
* rendering strategy;
* viewport density;
* visible point count.

User behavior is a property of the user.

User behavior is not a property of the device.

---

## Domain Before Visualization

Analytics is built in the following order:

```text
Round
    ↓
Session
    ↓
Session Density
    ↓
Day
    ↓
Day Density
    ↓
Baseline
    ↓
Baseline Corridor
    ↓
Higher-Level Analytics
    ↓
Visualization
```

Visualization consumes domain data.

Visualization must never redefine domain data.

---

## Calendar-Day Principle

User-facing analytics are organized around calendar days.

The user naturally perceives activity through time:

* today;
* yesterday;
* this week;
* this month.

Therefore:

```text
Day
```

is the primary temporal observation unit used by analytics.

Visualization may aggregate days.

Visualization may smooth days.

Visualization may interpolate missing values.

Visualization must not replace the calendar-day domain model.

---

# Round

## Definition

Round is the smallest observable gameplay event.

During a round:

1. User selects one option.
2. System selects one option.
3. Result is determined.

Possible outcomes:

```text
WIN
LOSE
```

---

## Interpretation

Round represents a single observable outcome.

Round is the atomic gameplay observation.

No smaller gameplay observation exists.

---

# Session

## Definition

Session is a collection of rounds played without leaving GameScreen.

Session begins when GameScreen becomes active.

Session ends when GameScreen is left.

---

## Interpretation

Session is a behavioral observation unit.

A session represents one uninterrupted attempt by the user to interact with the trainer.

Different sessions may correspond to different:

* emotional states;
* levels of concentration;
* motivations;
* expectations.

Therefore sessions are treated as independent observations.

---

# Session Density

## Definition

Session Density is the ratio of successful rounds to all rounds within a session.

```text
SessionDensity =
wins / rounds
```

---

## Range

```text
0.0 ≤ SessionDensity ≤ 1.0
```

---

## Interpretation

Session Density represents the observed result of a single gameplay session.

Session Density is a property of a Session.

Session Density is not a property of a User.

---

# Day

## Definition

Day is a calendar date containing zero or more sessions.

```text
Day
    ├─ Session
    ├─ Session
    ├─ Session
    └─ ...
```

A day may contain:

* zero sessions;
* one session;
* many sessions.

---

## Interpretation

Day is the primary analytical container used for user-facing analytics.

A day groups together all observed user sessions that occurred during the same calendar date.

---

# Day Density

## Definition

Day Density is the arithmetic mean of all Session Density values observed during a day.

```text
DayDensity =
(SessionDensity₁ + SessionDensity₂ + ... + SessionDensityₙ)
-----------------------------------------------------------
n
```

where:

```text
n = SessionCount
```

---

## Session Equality Principle

All sessions have equal analytical weight.

The analytical model intentionally does not weight sessions by:

* round count;
* duration;
* activity volume;
* elapsed time.

Reason:

Different sessions may correspond to different user states.

Each session is treated as an independent observation.

---

## Null Day Density

If a day contains no sessions:

```text
SessionCount = 0
```

then:

```text
DayDensity = null
```

---

## Interpretation

A null Day Density means:

> No observations exist for this day.

It does not mean:

```text
DayDensity = 0
```

No gameplay activity and zero result are different situations.

---

## Behavioral Meaning

Day Density answers the question:

> How successful were the user's sessions during this day?

It does not answer:

* how much the user played;
* how long the user played;
* how many rounds were played.

Those concerns belong to Activity.

---

# Activity

## Definition

Activity is the count of sessions performed during a day.

```text
Activity =
SessionCount
```

---

## Interpretation

Activity describes user engagement.

Activity is independent from Day Density.

A user may have:

```text
high activity
low density
```

or:

```text
low activity
high density
```

Both situations are valid.

---

## Behavioral Meaning

Activity answers the question:

> How much did the user engage with the trainer?

It does not answer:

> How successful was the user?

That question belongs to Day Density.

---

# Baseline

## Definition

Baseline represents the expected result of a completely random player.

For a gameplay level:

```text
Baseline =
1 / Level
```

---

## Examples

```text
Level 2
Baseline = 0.50

Level 4
Baseline = 0.25

Level 8
Baseline = 0.125

Level 12
Baseline = 0.0833
```

---

## Interpretation

Baseline is not derived from user behavior.

Baseline is a property of gameplay mathematics.

Baseline represents random guessing.

---

# Baseline Corridor

## Purpose

Baseline Corridor represents the range of results that may be explained by random guessing.

It answers the question:

> Is the observed user behavior still explainable by the random model?

---

## Inputs

Baseline Corridor depends on:

```text
Level
RoundCount
```

---

## Baseline Model

The underlying random process is a Bernoulli process.

Probability of success:

```text
p = 1 / Level
```

Expected value:

```text
E = p
```

Variance:

```text
Var = p(1-p)/n
```

where:

```text
n = RoundCount
```

Standard deviation:

```text
σ = sqrt(Var)
```

---

## Time Window Principle

RoundCount is not calculated from the entire account history.

RoundCount is not calculated from a single day.

RoundCount is calculated using the same analytical window visible to the user.

Current analytical window:

```text
30 calendar days
```

---

For day D:

```text
RoundCount(D)
=
all rounds
inside
[D - 29 ; D]
```

for the selected level.

---

## Interpretation

If user behavior remains inside the Baseline Corridor:

> Random guessing remains a plausible explanation.

If user behavior consistently leaves the Baseline Corridor:

> Random guessing is no longer a sufficient explanation.

---

# Observed Reality

Observed reality is represented by:

```text
DayDensity(day)
```

These values originate from gameplay observations.

Observed values are authoritative.

Observed values must never be modified by visualization.

---

# Trend Visualization

## Purpose

Trend visualization exists to improve readability.

Trend visualization is not analytical evidence.

Trend visualization is not a domain concept.

---

## Examples

Possible implementations:

```text
EMA
Spline
Polynomial approximation
Fourier approximation
Regression
```

---

## Ownership

Trend algorithms belong to the visualization layer.

The domain model remains independent from a specific trend algorithm.

---

## Interpolation

Missing calendar days may exist:

```text
DayDensity = null
```

Trend algorithms may interpolate missing values.

Interpolation belongs to visualization.

Interpolation does not create gameplay observations.

---

## Minimum Data Principle

Some trend algorithms require a minimum amount of data.

Examples:

```text
EMA5
requires at least 5 observations

EMA20
requires at least 20 observations
```

If insufficient observations exist:

> The trend algorithm is not displayed.

The system must not create synthetic historical observations to satisfy algorithm requirements.

---

# Analytical Interpretation

The analytical model intentionally separates:

```text
Observed Reality
```

from

```text
Expected Reality
```

Observed Reality:

```text
DayDensity
```

Expected Reality:

```text
Baseline
+
Baseline Corridor
```

The primary analytical comparison is:

```text
User Behavior
vs
Random Model
```

---

# Explicitly Rejected Concepts

## Device-Dependent Aggregation

User behavior must not depend on:

* screen width;
* point count;
* viewport size;
* rendering density.

Such concepts may exist inside visualization implementations.

They must never appear in analytical domain definitions.

---

## Weighted Sessions

Sessions are not weighted by:

* duration;
* round count;
* activity volume.

Session equality is a domain rule.

---

## Synthetic Historical Observations

The analytical model must not create fictional gameplay observations.

Missing observations may remain missing.

Visualization algorithms may interpolate trend lines.

Visualization algorithms must not create synthetic Day Density values.

---

## Visualization-Owned Semantics

Charts must not invent domain meaning.

Domain meaning must exist before chart implementation begins.

---

# Current Scope

Current domain model formally defines:

```text
Round

Session

SessionDensity

Day

DayDensity

Activity

Baseline

BaselineCorridor
```

All higher-level analytical concepts must be derived from these entities.

---

# Open Questions

## OPEN-01

Definition of user progress.

---

## OPEN-02

Definition of user regression.

---

## OPEN-03

Definition of user stagnation.

---

## OPEN-04

Definition of statistically significant deviation from the random model.

---

## OPEN-05

Definition of user insights.

---

## OPEN-06

Definition of recommendation logic.

---

## OPEN-07

Definition of forecasting models.

---

## OPEN-08

Selection of default trend visualization algorithms.

---

# Non-Goals

The current domain model does not define:

* chart rendering;
* chart styling;
* chart interaction;
* UI behavior;
* recommendation generation;
* forecasting;
* predictive analytics.

These concerns belong to higher analytical or presentation layers.

