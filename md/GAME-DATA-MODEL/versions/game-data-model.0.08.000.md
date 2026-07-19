## Entities


### ColorCard
- id: CardId
- color: GameColor
- isDealed: Boolean
- isCorrect: Boolean
- isSelected: Boolean

### ColorOptionRecord
- color: GameColor
- position: Position
- isCorrect: Boolean
- isSelected: Boolean


### GameColor
- id: String
- hex: Int
- name: String

### GameRoundRecord

// --- identity ---
- sessionId
- roundIndex
// --- context ---
- mode
- roundType
- level
- configId
// --- timing ---
- startedAt
- finishedAt
// --- user vs system ---
- selectedPosition  // : Position?, null = ABANDONED
- correctPosition
// --- options ---
options // List<ColorOptionRecord>
// --- result ---
result

### Session
- id
- mode (before / after / hybrid)
- level
- startedAt
- finishedAt
- rounds (derived from round_records where sessionId)

### GameState
- session
- currentRound
- roundIndex

---

## SQLite Schema

### sessions
- id TEXT, PRIMARY KEY
- mode TEXT
- level INTEGER
- startedAt INTEGER
- finishedAt INTEGER

### round_records
- id INTEGER PRIMARY KEY
- sessionId TEXT (FK → sessions.id)
- roundIndex INTEGER
- mode TEXT
- roundType TEXT
- level INTEGER
- configId INTEGER
- startedAt INTEGER
- finishedAt INTEGER
- selectedRow INTEGER // Це і наступне поле (selectedCol) мають бути замінені простим position_id, має з'явитись явний опис ігрового поля, у якому позиція має id/row/col, а конфігурація ігрового поля - версію.
- selectedCol INTEGER
- correctRow INTEGER
- correctCol INTEGER
- result TEXT, ∈ {'WIN', 'LOSE'}
- UNIQUE(sessionId, roundIndex)

### color_options
- id INTEGER, PRIMARY KEY
- roundId INTEGER (FK → round_records.id)
- colorId TEXT
- row INTEGER
- col INTEGER
- isCorrect INTEGER
- isSelected INTEGER

### colors
- id TEXT PRIMARY KEY
- hex INTEGER (ARGB)
- name TEXT



---
## Mapping

SessionEntity ↔ GameSession  
GameRoundRecordEntity ↔ GameRoundRecord
selectedPosition ↔ (selectedRow, selectedCol)
correctPosition ↔ (correctRow, correctCol)

## Invariants

- round.sessionId ∈ sessions.id
- selectedPosition ∈ options.positions OR null
- correctPosition ∈ options.positions
- options.size ≥ 2
- options не містить дубльованих позицій
- (row, col) унікально визначає позицію в межах раунду, опис ігрового поля - у майбутньому, але не зараз
- selectedPosition = null означає, що користувач не зробив вибір (ABANDONED)
- round.level = session.level

## Notes

- hex: INTEGER (ARGB, 0xAARRGGBB)
- всі випадковості можуть бути відтворені через seed

