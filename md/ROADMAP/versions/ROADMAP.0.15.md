# ROADMAP — Release Transition Program

## Version: 0.16 → 0.19

## Last Updated: 2026-05-13

---

# 🧭 Strategic Direction

The project has transitioned from:

```text
feature expansion
```

to:

```text
deployment-oriented stabilization
```

The primary goal is no longer adding major gameplay functionality.

The focus is now:

* supportability
* bounded ownership
* deployment hardening
* localization readiness
* telemetry preparation
* capability isolation
* long-term maintainability

---

# 🧠 Core Stabilization Philosophy

The project intentionally prioritizes:

```text
supportability over theoretical architectural elegance
```

and:

```text
explicit ownership over generalized abstraction
```

---

# ⚠️ Primary Architectural Risks

The main risks during release transition are:

* implicit relationship explosion
* ownership erosion
* feature-flag scattering
* adaptive mega-context growth
* lifecycle coupling
* telemetry leakage into gameplay
* localization-driven UI instability

All roadmap stages intentionally aim to reduce these risks.

---

# 🟢 VERSION 0.16 — Supportability Hardening

## Goal

Stabilize architecture boundaries and eliminate release-critical structural risks before localization, telemetry and deployment integration.

This phase focuses on:

* runtime safety
* restore correctness
* ownership clarity
* supportability cleanup
* capability architecture
* environment consolidation

---

# 🎮 Runtime Stabilization

## Process Death & Restore Validation

Validate:

* process death recovery
* abandoned session handling
* session finalization correctness
* duplicate persistence prevention
* round restore consistency
* rotation behavior
* background/foreground lifecycle

---

## Gameplay Lifecycle Cleanup

Stabilize:

* reducer lifecycle clarity
* GameEngine ownership boundaries
* legacy runtime integration
* continuation semantics
* BEFORE/HYBRID consistency

Explicitly avoid:

* orchestration revival
* generalized runtime abstraction
* implicit lifecycle execution

---

## Process Branch Isolation

Freeze and isolate:

```text
game/domain/process/*
```

Clarify:

* experimental status
* non-production status
* future-research-only semantics

Prevent accidental runtime coupling.

---

# 🧱 Supportability Cleanup

## Ownership Validation

Audit and cleanup:

* implicit dependencies
* ownership leakage
* cross-domain knowledge
* hidden runtime coupling
* feature leakage

---

## Reducer & Runtime Cleanup

Review:

* GameEngine
* handlers
* persistence coordination
* round creation boundaries
* topology ownership

---

## TODO/FIXME Audit

Perform cleanup pass for:

* outdated assumptions
* temporary bridges
* migration leftovers
* unstable runtime notes

---

# 🔐 Capability Architecture

## Centralized Capability Model

Introduce bounded capability ownership.

Goal:

Avoid:

```kotlin
if (premium) { ... }
```

scattering across the codebase.

---

## Capability Direction

### Free

* AFTER mode
* statistics
* explainability
* full core perceptual gameplay

### Premium

* BEFORE
* HYBRID
* child profiles

---

## Constraints

Capability system must remain isolated from:

* reducers
* persistence
* renderers
* statistics
* environment runtime

---

# 🗄 Database Stabilization

## Structural Validation

Validate long-term sufficiency of:

* session schema
* round persistence
* profile ownership
* visual ownership
* telemetry compatibility
* Firebase compatibility
* migration safety

---

## Persistence Hardening

Review:

* append-only guarantees
* reconstruction correctness
* restore safety
* abandoned-session handling

---

# 🌍 Environment Consolidation

## AppLayoutEnvironment Expansion

Clarify ownership for:

* screen metrics
* spacing metrics
* chart metrics
* grid metrics
* responsive constraints

---

## Magic Constant Reduction

Begin systematic removal of:

* hardcoded spacing
* hardcoded dimensions
* hardcoded UI metrics
* screen-specific assumptions

---

## Constraints

Environment layer must NOT evolve into:

* generalized adaptive engine
* mega-context runtime
* global UI policy system

---

# 🧪 Validation

## Small-screen Validation

Validate:

* compact devices
* landscape mode
* chart readability
* markdown rendering
* statistics layouts

---

## Stability Pass

Eliminate:

* inconsistent runtime states
* restore bugs
* visual glitches
* lifecycle instability

---

# 🟡 VERSION 0.17 — Localization & Content Stabilization

## Goal

Achieve product-quality UX consistency across languages, layouts and explanatory systems.

This phase focuses on:

* localization
* wording consistency
* markdown finalization
* visual consistency
* help-system completeness

---

# 🌍 Localization

## Full String Extraction

Complete migration of hardcoded strings into:

```text
res/strings.xml
```

Including:

* gameplay UI
* statistics UI
* dialogs
* Capybara text
* settings
* debug labels
* graph labels

---

## Localization Validation

Validate:

* English
* Ukrainian
* locale fallback behavior
* mixed-language prevention
* terminology consistency

---

## Layout Stability

Validate localization impact on:

* spacing
* clipping
* chart layouts
* dialogs
* navigation
* small screens

---

# 📘 Markdown & Help System

## Help Finalization

Complete:

* help text cleanup
* terminology alignment
* graph explanations
* onboarding clarity
* explanation consistency

---

## Illustration Support

Prepare:

* graph screenshots
* legend illustrations
* analytics examples
* markdown image assets

---

## Markdown Validation

Validate:

* long content
* image rendering
* scrolling behavior
* typography readability
* localization fallback

---

# 🐹 Emotional UX Polish

## Capybara Asset Pass

Optional visual update pass for:

* emotional consistency
* scaling correctness
* illustration quality
* layout integration

---

## Transition Polish

Improve:

* emotional transitions
* visual consistency
* subtle feedback quality

Explicitly avoid:

* heavy animation systems
* sprite pipelines
* artist-dependent runtime complexity

---

# 🎨 Visual Consistency

## UI Consistency Pass

Review:

* spacing
* typography
* navigation clarity
* chart readability
* dialog readability
* visual hierarchy

---

# 🔵 VERSION 0.18 — Telemetry & Deployment Preparation

## Goal

Prepare the product for real-world deployment and telemetry-driven observation.

This phase focuses on:

* Firebase integration
* telemetry export
* monetization isolation
* internal testing
* deployment preparation

---

# ☁️ Firebase Integration

## Telemetry Architecture

Integrate Firebase as:

* passive telemetry layer
* analytics aggregation backend
* deployment observation tool

Explicitly avoid:

* gameplay runtime ownership
* cloud-driven gameplay
* remote orchestration semantics

---

## Telemetry Export

Export:

* session data
* round data
* gameplay metadata
* aggregated behavioral metrics

---

## Constraints

Telemetry must remain isolated from:

* GameEngine lifecycle
* gameplay outcomes
* reducer semantics

---

# 💳 Capability Integration

## Premium Restrictions

Activate bounded capability restrictions for:

* BEFORE mode
* HYBRID mode
* child profile creation

---

## Capability Isolation

Implement through centralized capability ownership.

Avoid:

* UI-level scattering
* reducer-level monetization logic
* persistence-level monetization rules

---

# 🧪 Internal Testing

## Google Play Internal Testing

Prepare:

* internal release
* tester onboarding
* crash validation
* telemetry validation
* update-flow validation

---

## Feedback Collection

Validate:

* analytics clarity
* onboarding quality
* UX trustworthiness
* emotional engagement
* confusion points
* stability

---

# 📦 Deployment Preparation

## Release Assets

Prepare:

* screenshots
* descriptions
* icons
* privacy policy
* store metadata

---

# 🔴 VERSION 0.19 — Publication Hardening

## Goal

Finalize production readiness and publish the application.

---

# 🧪 Final Stabilization

## Feedback-driven Fixes

Address:

* tester confusion
* UX friction
* telemetry inconsistencies
* crash reports
* visual issues

---

## Final Stability Pass

Validate:

* installation/update flow
* restore behavior
* localization correctness
* premium/free separation
* telemetry integrity

---

# 🚀 Publication

## Google Play Release

Publish:

* free version
* premium version

with bounded capability separation.

---

# 🧠 Long-term Direction (Post-release)

Future work intentionally postponed until after deployment stabilization:

* orchestration revisiting
* cloud synchronization
* adaptive gameplay systems
* AI-assisted interpretation
* advanced behavioral segmentation
* multiplatform runtime
* personalized analytics

---

# ⚠️ Final Strategic Principle

The project now intentionally evolves as:

```text
supportable long-term product
```

rather than:

```text
experimental architecture playground
```

Release transition work must prioritize:

* clarity
* bounded ownership
* maintainability
* deployment safety
* explainability
* predictable evolution

over:

* abstraction complexity
* orchestration sophistication
* framework-driven design
* premature generalization

