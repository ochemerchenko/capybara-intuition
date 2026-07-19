## Version: 0.09.001
## Last Updated: 2026-04-05

## Metrics

### DailyStats
- date (LocalDate, UTC-based)
- sessionsCount = count(sessions where finishedAt ∈ date)
- afterSessionCount = count(sessions where mode = after AND finishedAt ∈ date)
- beforeSessionCount = count(sessions where mode = before AND finishedAt ∈ date)
- hybridSessionCount = count(sessions where mode = hybrid AND finishedAt ∈ date)

---

### SessionStats
- sessionId
- totalRounds = count(round_records where sessionId)
- winRounds = count(round_records where result = match AND sessionId)
- density = winRounds / totalRounds (0 якщо totalRounds = 0)

---

## Source
- sessions (для визначення дат та групування)
- round_records (для підрахунку раундів та результатів)
