## Version: 0.12 (In Progress)

## Last Updated: 2026-04-11

# ROADMAP — Behavioral Analytics

---

# 🎯 Product Vision

Перехід від:

time-based activity (коли грав)

→ до

behavioral analytics (як грав)

---

# 🧩 CURRENT STATE (0.12)

## ✅ DONE

### 📊 Session Density Analytics

* Session density (winRate per session)
* Filtering (≥2 rounds)
* Sorting (by startedAt)
* Adaptive compression (~80 points)
* Moving averages (5, 10)
* Baseline (1 / level)

---

### 📈 Visualization

* LineChart (Canvas)
* Raw + MA short + MA long
* Baseline as reference line
* Simplified X-axis (session-based)

---

### 🧠 Architecture

* New pipeline:
  DB → DAO → Repository → Engine → ViewModel → UI

* DensityMap:
  Map<GameMode?, Map<Int, SessionDensityGraphData>>

* Separation:

  * day-based stats
  * session-based stats

---

### ⚠️ CURRENT PROBLEM

UI не масштабується і перевантажений:

* занадто багато графіків одночасно
* відсутній фокус
* складно порівнювати
* accordion не вирішує проблему

---

# 🚀 PHASE 3 — UX Stabilization (CURRENT FOCUS)

## 🎯 Goal

Зробити статистику:

* читабельною
* порівнюваною
* контрольованою

---

## 🧩 Step 3.1 — LEVEL-first layout

### Concept

Level → modes (grid)

---

### UI

▶ Level 2

```
[ ALL     ]   [ BEFORE  ]
[ AFTER   ]   [ HYBRID  ]
```

▶ Level 3
▶ Level 4

---

### Rules

* кожен level = окремий блок
* 2×2 grid
* тільки існуючі графіки (без пустих)
* padding + card layout

---

## 🧩 Step 3.2 — Click → Detail

### Goal

overview → detail

---

### Behavior

* tap на графік →
* відкривається повноекранний графік

---

### Detail screen

* повний LineChart (як зараз)
* всі підписи
* легенда
* baseline пояснення

---

## 🧩 Step 3.3 — Graph Simplification (grid)

### Grid graph ≠ Detail graph

---

### Grid version

* ✔ raw (light)
* ✔ MA short
* ✔ baseline
* ❌ axis labels (мінімум)
* ❌ текст шум

---

### Goal

швидке візуальне порівняння

---

## 🧩 Step 3.4 — Visual Hierarchy

### Add:

* card container
* spacing (16dp)
* clear level headers
* consistent alignment

---

### Remove:

* edge-to-edge charts
* текст між графіками
* перевантажені підписи

---

# 🔄 PHASE 4 — Pivot Navigation (NEXT)

## 🎯 Goal

Дати користувачу вибір:

як саме дивитись дані

---

## 🧩 Pivot Selector

Compare by: [ LEVEL | MODE ]

---

## 🟢 Mode A (default)

Level → modes

---

## 🔵 Mode B

Mode → levels

---

## 📊 UI (mode-first)

Mode: BEFORE

```
[ Level 2 ]   [ Level 3 ]
[ Level 4 ]   [ Level 5 ]
```

---

## ⚠️ Constraints

* без кастомних конфігурацій
* без “basket selection”
* без tabs (бо ламають порівняння)

---

# 🔬 PHASE 5 — Advanced Analytics (LATER)

## 🔹 Possible additions

* spike detection
* consistency score
* прогрес (trend direction)
* volatility (стабільність)

---

## 🔹 Session weighting (optional)

* врахування кількості раундів

---

## 🔹 Time correlation

* density vs time
* density vs session length

---

# 💰 PHASE 6 — Monetization (FUTURE)

## Free

* density graphs
* moving average
* baseline

---

## Paid

* spike analysis
* advanced insights
* custom filters
* anomaly detection

---

# ❌ Explicitly OUT (for now)

* ❌ zoom
* ❌ user-configurable MA
* ❌ spline smoothing
* ❌ complex filters
* ❌ export / sharing

---

# 🧠 Key UX Principle

Користувач не читає дані
Користувач шукає відповідь

---

### Основні питання:

* Я кращий за випадковість?
* Я стабільний?
* Я прогресую?

---

# 🧭 Next Immediate Step

Реалізувати:

1. Level → 2×2 grid
2. Click → Detail
3. Card-based layout

→ це зробить статистику реально usable

