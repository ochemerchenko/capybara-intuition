## 🟢 VERSION 0.13 — Stabilization
## Last Updated: 2026-04-12

**Goal:** Deliver a stable, understandable product before testing

### Core

* Remove magic constants (introduce in-memory config)

  * days range (e.g. 30)
  * moving averages (MA5, MA10)
  * levels range
* Keep config simple (no DB yet)

### UX (Statistics)

* Fix Pivot behavior (LEVEL / MODE)
* Prevent overload (no 40+ graphs at once)
* Introduce proper empty states
* Remove layout gaps / spacing issues
* Separate density vs day-based graphs visually

### Stability

* Eliminate crashes
* Handle empty data safely

### Out of scope

* ❌ Config persistence (DB)
* ❌ Multi-profile
* ❌ Firebase
* ❌ Monetization

---

## 🟡 VERSION 0.14 — Internal Testing

**Goal:** Get real user feedback

### Release

* Publish via Google Play (Internal Testing)
* Ensure versioning (versionCode updates)

### Communication

* Create tester group (Telegram or similar)
* Provide simple instructions:

  * play sessions
  * check statistics
  * report confusion/issues

### Feedback collection

* UX clarity (what is not understood?)
* Bugs / crashes
* Engagement (is it interesting?)

### Iteration

* Fast fixes based on feedback

---

## 🔵 VERSION 0.15 — Systemization

**Goal:** Make system flexible based on real usage

### Config system (persistent)

* Move config to DB
* Support:

  * graph parameters
  * levels
  * gameplay tuning

### Feature flags (basic)

* Enable/disable modes
* Prepare for monetization

### Multi-profile

* Multiple users per device
* Profile isolation (separate statistics)
* Optional PIN protection

### Refactoring

* Targeted cleanup only
* Improve architecture where needed (not global rewrite)

---

## 🟣 VERSION 0.16 — Growth

**Goal:** Scale product and prepare for wider release

### Analytics

* Firebase integration

  * analytics
  * crash reporting

### Monetization

* Paid modes (e.g. BEFORE, HYBRID)
* Feature access control via flags

### UX polish

* Onboarding (optional)
* Insights / explanations
* Visual improvements

---

## 🧠 Guiding Principles

* Build minimum before testing
* Validate with real users early
* Avoid over-engineering before feedback
* Refactor only what blocks progress
* Separate UX problems from architecture problems

---

## 🔥 Current Focus

👉 Finish VERSION 0.13 and move to testing as soon as possible

