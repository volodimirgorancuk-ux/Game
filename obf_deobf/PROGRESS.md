# Zombie Infection Deobfuscation Progress

## Completed

### Phase 1: Initial Deobfuscation
- [x] Extracted JAR contents
- [x] Identified all classes via `javap`
- [x] Created ASM-based class renamer (Java)
- [x] Renamed 8 classes:
  - `GloftMASS` (MIDlet entry point)
  - `g` -> `GameCanvas` (main game loop, 1335 methods)
  - `a` -> `GraphicsEngine` (sprite/tile rendering)
  - `b` -> `GameController` (IGP, resources, text)
  - `c` -> `GameData` (entity/layer data)
  - `d` -> `GameConfig` (static flags)
  - `e` -> `SensorHandler` (accelerometer)
  - `f` -> `AudioManager` (audio + enemy/NPC logic)

### Phase 2: Decompilation
- [x] Decompiled all classes with CFR 0.152
- [x] Generated readable Java source in `decompiled/`

### Phase 3: Automated Analysis
- [x] Built call graph between 1672 methods (5 classes)
- [x] Categorized methods by strings/calls:
  - Audio: ~20 methods identified
  - Particles: ~5 methods identified
  - Enemy/NPC: ~3 methods identified
  - Rendering: ~100 methods identified
  - Input: ~10 methods identified
  - Persistence: ~5 methods identified
  - Network/IGP: ~10 methods identified
- [x] Generated `suggested_mapping.json` with 7 high-confidence mappings
- [x] Generated `comprehensive_mapping.json` with 91 manual/auto mappings
- [x] Generated `callgraph.json` with cross-class call relationships

### Phase 4: Data Analysis
- [x] Parsed `dataIGP` structure (offset table + 12 entries)
- [x] Identified IGP keys: URL-WN, URL-BS, URL-GLIVE, IGP-PROMOS, etc.
- [x] Identified device list: Sony Ericsson W890, K850, K800, K790, K770, T650, S500, W580, W880, W810, W850, W760

## Current State

```
Total methods: ~1672
Manually mapped: 57 (3.4%)
High-confidence auto-mapped: 7 (0.4%)
Remaining ambiguous: ~1608 (96.2%)
```

## What Remains

### 1. Methods Without Unique Signatures
Many methods in GameCanvas have identical signatures like:
```java
void a(Graphics graphics)
void b(Graphics graphics)
void c(Graphics graphics)
...
```
These are indistinguishable without execution tracing or original mapping.txt.

### 2. Full State Machine
GameCanvas has a state machine with states 0-19, 99. Each state has an update() and render() method. We've identified ~20 states but there are ~20 more to map.

### 3. AudioManager Deep Dive
AudioManager (275 methods) contains:
- Audio playback logic
- Enemy AI logic (getEnemyTotalHealth, getEnemyCurrentHealth)
- NPC targeting logic
- Need deeper analysis to separate concerns

### 4. GameController IGP System
We identified loadIGPData(), isOnline(), openURL(), but there are 54 total methods. The IGP URL resolution logic needs full mapping.

### 5. GraphicsEngine Internals
GraphicsEngine has 26 methods for sprite/tile rendering. Signature analysis shows it handles:
- Tile parsing (byte[] input)
- Image creation
- Region blitting
- Needs deeper inspection

## What We CANNOT Do Without Original mapping.txt

- Recover original method names for ~1600 methods
- Distinguish between methods with identical signatures
- Recover original field names (a, b, c, etc.)
- Get comments or original variable names

## What We CAN Do Next (Priority Order)

### Priority 1: Focus on Gameplay Systems
Pick one subsystem and fully document it:
- **Particle system** (5 methods, already mostly mapped)
- **Enemy spawning** (spawnWave, spawnEnemy)
- **Audio system** (initAudio, playSound, stopSound)
- **IGP/Network system** (loadIGPData, resolveIGPURL)
- **Input handling** (handleKey, handleTouch)

### Priority 2: Build State Machine Map
Document all 19+ game states:
```
0 = ?
1 = ?
2 = ?
3 = ?
6 = ?
10 = ?
12 = ?
13 = ?
18 = ?
99 = exit
```

### Priority 3: Data Format Analysis
Parse game resource files:
- `m0`-`m13_*` (map/level data)
- `t0` (tile set?)
- `palettesAmount.bin` (color palettes)

### Priority 4: Cross-Reference With Other Decomps
Look at Diamond Rush / Green Farm 3 decomp for similar Gameloft engine patterns.

## Files Generated

| File | Size | Description |
|------|------|-------------|
| `deobfuscated.jar` | 1.1 MB | JAR with renamed classes |
| `mapping.txt` | 5.4 KB | ProGuard-style class mapping |
| `enhanced_mapping.txt` | 5.4 KB | Class + key method mappings |
| `comprehensive_mapping.json` | 5.6 KB | 57 manual mappings |
| `suggested_mapping.json` | 2.8 KB | 7 high-confidence auto mappings |
| `callgraph.json` | 45 KB | Cross-class call graph |
| `decompiled/*.java` | 1.8 MB | Full CFR decompiled source |
| `deobfuscation-report.txt` | 3.1 KB | Class-level analysis |
| `deobfuscation-summary.md` | 3.4 KB | Summary document |
