## Version: 0.11
## Last Updated: 2026-04-07


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
