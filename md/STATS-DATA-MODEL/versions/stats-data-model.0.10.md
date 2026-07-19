## Version: 0.10
## Last Updated: 2026-04-06

# Statistics Data Model

---

## Metrics

---

### DailyStats

- date (LocalDate, UTC-based)

- sessionsCount  
  = count(sessions where finishedAt ∈ date)

- afterSessionCount  
  = count(sessions where mode = AFTER)

- beforeSessionCount  
  = count(sessions where mode = BEFORE)

- hybridSessionCount  
  = count(sessions where mode = HYBRID)

---

### SessionStats

- sessionId

- totalRounds  
  = count(round_records where sessionId)

- winRounds  
  = count(round_records where result = WIN)

- density  
  = winRounds / totalRounds

---

# 🆕 Timing Metrics

---

### TimingStats

Агрегація виконується тільки для раундів, де всі необхідні поля не null.

---

#### avgDecisionTime // Скоріше за все не буде використовуватись.

```text
AVG(userSelectedAt - startedAt)