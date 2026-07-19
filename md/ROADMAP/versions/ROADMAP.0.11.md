## Version: 0.11
## Last Updated: 2026-04-07

# ROADMAP

---

## 🎯 Product Direction

Проєкт переходить від “статистики” до **аналітики поведінки користувача**, але у версії 0.11 реалізовано **мінімальний, стабільний візуальний шар без аналітики**.

### Ключові принципи:
- AFTER-first (архітектурно враховано, але не активовано)
- Без скролу в графіках
- Фіксований період
- Один графік = одна ідея
- UI не містить логіки
- Graph = тільки візуалізація

---

# ✅ Phase 0 — Stabilization (DONE)

## Core
- GameEngine як pure state machine
- ViewModel → orchestration + timing
- Timing метрики інтегровані

## Statistics Base
- Sessions per Day (raw)
- Canvas-based BarChart
- Debug seed

---

# ✅ Phase 1 — Sessions Graphs MVP (0.11 DONE)

## 🎯 Goal
Реалізовано **простий, стабільний екран графіків без аналітики**

---

## 📊 Graphs

- ✔ 4 графіки:
  - All sessions
  - After
  - Before
  - Hybrid

- ✔ Кожен графік:
  - незалежний
  - не змішує режими

---

## 📅 Period

- ✔ Фіксований період:
  - останні **30 календарних днів (включно з сьогодні)**

- ✔ Без selector’ів періоду

---

## 📈 Chart Type

- ✔ Тільки **Bar Chart**
- ✔ Без line charts
- ✔ Без адаптації під кількість точок

---

## 🧠 Data Pipeline

✔ raw → normalize → graph

- ✔ Normalize:
  - заповнення відсутніх днів (0)

- ✔ Сортування:
  - від старих до нових

- ✔ Гарантія:
  - рівно 30 точок

---

## ⚙️ Architecture

- ✔ StatisticsDao (окремо від SessionDao)
- ✔ StatisticsRepository
- ✔ SessionsAnalyticsEngine
- ✔ StatisticsViewModel + UiState
- ✔ Чітке розділення:
  - data / domain / presentation

---

## 🧩 UI

- ✔ Вертикальний список графіків:
  - All
  - After
  - Before
  - Hybrid

- ✔ Кожен графік має:
  - назву
  - canvas chart

---

## ⚠️ Edge Cases

- ✔ 0 даних → графік з нулями
- ✔ <30 днів використання → частково заповнений період

---

## ❌ Explicitly OUT of scope (0.11)

- ❌ Trend (будь-який)
- ❌ Average / mean / regression
- ❌ Moving average
- ❌ Adaptive bucketing (week/month)
- ❌ Zoom / деталізація
- ❌ Кастомні періоди
- ❌ Density
- ❌ Timing metrics
- ❌ Insights
- ❌ Feature flags (але архітектурно допускаються)

---

# 🚀 Phase 2 — Basic Insights (0.12)

## 🎯 Goal
Додати **мінімальну аналітику без ускладнення UX**

---

## 📊 Features

- [ ] 7-day moving average
- [ ] Простий тренд (напрям ↑ ↓)
- [ ] Period selector:
  - 7 / 30 / 90 днів

---

## 🧠 UX

- [ ] Loading state
- [ ] Empty state ("No sessions yet")

---

## ⚙️ Data

- [ ] Підтримка різних періодів у repository
- [ ] Підготовка до adaptive aggregation

---

# 📊 Phase 3 — Advanced Metrics

- [ ] Density per session
- [ ] Timing metrics
- [ ] Поведінкові сигнали

---

# 🧩 Phase 4 — Multi-Mode UX

- [ ] Mode selector
- [ ] Порівняння режимів

---

# 💰 Phase 5 — Monetization

## Free
- AFTER + базові графіки

## Paid
- інші режими
- advanced аналітика

---

# 🚀 Phase 6 — Insights Layer

- [ ] Поведінкові інсайти
- [ ] Інтерпретація користувача

---

# 🧠 Architecture Notes

- GraphData = dumb UI contract
- Engine = source of truth
- UI = pure rendering
- Normalize layer обов’язковий
- Архітектура готова до розширення (bucket, trend)

---

# 🔜 Next Focus (Immediate)

1. Додати moving average (7 днів)
2. Додати selector періоду (7 / 30 / 90)
3. Додати empty / loading states
