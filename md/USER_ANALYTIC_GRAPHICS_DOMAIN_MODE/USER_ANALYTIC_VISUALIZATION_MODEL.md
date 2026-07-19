# USER_ANALYTIC_GRAPHICS_VISUALIZATION_MODEL
## Purpose
This document defines visualization semantics for user analytics.
The purpose of visualization is not to expose raw statistics.
The purpose of visualization is to help the user understand:
* current behavior;
* behavioral direction;
* relationship to the random model.
The visualization layer answers: 
* Where am I moving?
and
* Am I still inside the region of randomness?
The visualization layer does not define:
* gameplay observations;
* SessionDensity;
* DayDensity;
* Baseline;
* Baseline Corridor mathematics.
These concerns are defined separately.
---
# Visualization Philosophy
Visualization exists for human understanding.
Visualization does not exist to expose implementation details.
The user should not be required to understand:
* EMA;
* standard deviation;
* variance;
* probability theory;
* statistical estimation methods.
Visualization should communicate conclusions visually.
---
# Canonical User Question
The primary question is:
* Am I still behaving like a random process?
The secondary question is:
* Am I moving toward or away from randomness?
All visualization decisions should support these questions.
---
# Primary Analytical Objects
Visualization operates on:
```text
DayDensity(day)
```
defined by:
```text
USER_ANALYTIC_GRAPHICS_DOMAIN_MODEL
```
and
```text
BaselineCorridor(day)
```
defined by:
```text
USER_ANALYTIC_GRAPHICS_BASELINE_MODEL
```
---
# Day Density Points
Observed Day Density values are authoritative observations.
They represent real gameplay history.
Observed points must never be altered.
Observed points may be visualized as:
* markers;
* dots;
* hidden support points.
Visualization style is implementation-dependent.
Observation semantics are not.
---
# Missing Days
Some calendar days may contain:
```text
0 sessions
```
For such days:
```text
DayDensity = null
```
Null observations are legitimate observations.
Missing days must never be replaced with synthetic gameplay data.
---
# Trend Line
## Purpose
Trend Line represents a continuous approximation of user behavior.
Trend Line exists to help users perceive direction.
Trend Line is not gameplay truth.
Trend Line is not an observation.
Trend Line is a visualization artifact.
---
# Trend Line Inputs
Trend Line is built from:
```text
DayDensity(day)
```
for all available observations.
---
# Trend Line Requirements
Trend Line should:
* remain visually stable;
* preserve long-term direction;
* tolerate missing observations;
* avoid excessive noise.
Trend Line should not:
* create artificial performance;
* reinterpret gameplay history;
* modify observations.
---
# Interpolation
Interpolation exists solely to bridge missing observations.
Interpolation applies only to:
```text
TrendLine
```
Interpolation never modifies:
```text
DayDensity
```
---
# Interpolated Values
Interpolated values are visualization artifacts.
Interpolated values:
* are not observations;
* are not persisted;
* are not exported;
* are not analytical facts.
Interpolation exists only during chart construction.
---
# Smoothing Algorithms
Trend Line may be generated through different smoothing algorithms.
Examples:
```text
EMA
Polynomial Approximation
Spline Approximation
Fourier Approximation
```
Visualization semantics must remain independent of algorithm choice.
---
# EMA
Current preferred implementation:
```text
EMA
```
EMA is selected because:
* computational simplicity;
* stable behavior;
* tolerance to sparse observations.
EMA is an implementation detail.
EMA is not part of the analytical domain model.
---
# EMA Flexibility
Visualization should support:
```text
Enable EMA
Disable EMA
Replace EMA
```
without changing analytical semantics.
---
# Short History Handling
Users may have insufficient historical observations.
Visualization should adapt smoothing parameters to available history.
Preferred behavior:
```text
Short history
    →
Short EMA
Long history
    →
Long EMA
```
Synthetic observations must never be generated solely to satisfy visualization algorithms.
---
# Baseline Corridor Visualization
Baseline Corridor should be visualized as a continuous region.
Examples:
```text
Shaded Area
Band
Envelope
```
Visual implementation is not fixed.
The semantic meaning is fixed.
---
# Corridor Meaning
Baseline Corridor represents:
```text
Region Of Randomness
```
for the selected level.
Any behavior inside the corridor remains explainable by the random model.
---
# User Trend Visualization
User behavior should be visualized as:
```text
TrendLine
```
superimposed on:
```text
BaselineCorridor
```
This is the primary analytical chart.
---
# Interpretation Rule
The chart should visually answer:
```text
UserTrend
vs
BaselineCorridor
```
without requiring statistical knowledge.
---
# User Sigma
User-specific confidence corridors are currently rejected.
Reason:
Observed statistical uncertainty is already represented through:
```text
BaselineCorridor
```
Additional UserSigma visualization introduces complexity without improving the primary analytical question.
---
# Observation Window
Visualization uses the same analytical window as activity charts.
Current window:
```text
30 calendar days
```
Future versions may support:
```text
30 days
90 days
180 days
```
---
# X Axis
The horizontal axis represents:
```text
Calendar Day
```
No normalization is allowed.
No point compression is allowed.
Calendar semantics must remain visible.
---
# Y Axis
The vertical axis represents:
```text
DayDensity
```
Range:
```text
0.0
to
1.0
```
---
# Canonical Visualization
The canonical density chart consists of:
```text
Observed DayDensity Points
+
TrendLine
+
Baseline Corridor
```
All other graphical elements are optional.
---
# Explicitly Rejected Concepts
## Synthetic Gameplay History
Artificial observations generated for chart stabilization.
Rejected.
---
## Lifetime Baseline Corridor
Corridor based on all historical rounds.
Rejected.
---
## User Sigma Corridor
User-specific confidence envelope.
Rejected.
---
## Normalized Time Axis
Time compression based on visible point count.
Rejected.
Calendar semantics must be preserved.
---
# Open Questions
## OPEN-01
Single EMA vs dual EMA visualization.
---
## OPEN-02
Whether raw DayDensity points should always remain visible.
---
## OPEN-03
Future interpolation algorithm selection.
---
## OPEN-04
Optional visualization toggles:
```text
Show Points
Show Trend
Show Corridor
```

