## Version: 0.12
## Last Updated: 2026-04-11


# Game Data Model

## GameRoundRecord

Represents a completed round of the game.

### Identity
- sessionId: String
- roundIndex: Int

### Context
- mode: GameMode
- roundType: RoundType
- level: Int
- configId: Int

### Timing

#### Core Timing
- startedAt: Long
- finishedAt: Long

#### User Timing
- userSelectedAt: Long?
  - timestamp when user made a selection

- resultShownAt: Long?
  - when result was rendered in UI

- firstUserActionAt: Long?
  - first user action after result was shown

- postRoundActionType: PostRoundActionType?
  - type of that action (next round, change level, etc.)

### Positions
- selectedPosition: Position?
- correctPosition: Position

### Options
- options: List<ColorOptionRecord>

### Result
- result: RoundResult

### Session-based
- totalRounds (COUNT)
- winRounds (SUM WHERE result = WIN)
- session density = winRounds / totalRounds

### Time-based
- sessions per day
- activity trends

### Behavioral
- decision time
- post-result delay

---

### Important

- No additional fields are required for analytics
- All metrics are derived from existing data
- Statistics layer is read-only
