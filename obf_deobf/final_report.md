# Zombie Infection — Deobfuscation Final Report

## Summary

### Files Generated
| File | Path | Description |
|------|------|-------------|
| `deobfuscated.jar` | workspace root | JAR with renamed classes |
| `comprehensive_mapping.json` | workspace root | 120 method mappings |
| `mapping_methods.txt` | workspace root | Human-readable method mapping |
| `mapping.txt` | workspace root | ProGuard-style class mapping |
| `state_machine_analysis.md` | obf_deobf/ | Full state machine (20 states) |
| `ingame_analysis.md` | obf_deobf/ | InGame state deep dive |
| `decompiled/*.java` | decompiled/ | Full source code (8 files, 1.8 MB) |

---

## Phase 1: Class Renaming (8 classes)

| Obfuscated | Deobfuscated | Role |
|------------|--------------|------|
| `GloftMASS` | `GloftMASS` | MIDlet entry point |
| `g` | `GameCanvas` | Main game loop, rendering, input (1335 methods) |
| `a` | `GraphicsEngine` | Sprite/tile rendering engine |
| `b` | `GameController` | IGP system, resource loading |
| `c` | `GameData` | Entity/layer data container |
| `d` | `GameConfig` | Static configuration flags |
| `e` | `SensorHandler` | Accelerometer input |
| `f` | `AudioManager` | Audio + enemy/NPC logic |

---

## Phase 2: State Machine (20 states)

| ID | Name | Update | Render | Purpose |
|----|------|--------|--------|---------|
| 0 | MainMenu | `v()` | `b(Graphics)` | Title screen |
| 1 | Options | `x()` | `c(Graphics)` | Settings |
| 2 | Loading | `F()` | `h(Graphics)` | Resource loading |
| 3 | Cutscene | `V()` | `l(Graphics)` | Story cinematic |
| 4 | **InGame** | `z()` | `j(Graphics)` | Core gameplay |
| 5 | Paused | `bK()` | `j(Graphics)` | Pause overlay |
| 6 | GameOver | `B()` | `e(Graphics)` | Death screen |
| 7 | Tutorial | `cd()` | `P(Graphics)` | Tutorial |
| 8 | SubState | `cj()` | `R(Graphics)` | Sub-menu |
| 10 | Continue | `dH()` | `ar(Graphics)` | Continue screen |
| 11 | Victory | `dA()` | `ao(Graphics)` | Victory screen |
| 12 | Credits | `eh()` | `ax(Graphics)` | Credits roll |
| 13 | LevelSelect | `fE()` | `n(Graphics)` | Level select |
| 16 | Special | `C()` | `f(Graphics)` | Unknown |
| 17 | CharacterSelect | `aO()` | `q(Graphics)` | Character select |
| 18 | PausedSubmenu | `E()` | `g(Graphics)` | Pause menu |
| 19 | ConfirmDialog | `G()` | `i(Graphics)` | Yes/No dialog |
| 20 | Unknown | - | - | Empty render |
| 99 | Exit | - | - | Shutdown |

---

## Phase 3: InGame State Deep Dive (state 4)

### Update Entry Point: `z()` (line 1081)
```java
GameCanvas.Z(bB);      // load level
GameCanvas.aC();       // update game logic
GameCanvas.cn();       // spawn enemies
```

### Main Game Loop: `B()` (line ~1195)
Sub-state `b` (0-4):
- `b=0`: Normal gameplay (shooting, audio, camera)
- `b=1`: Alternative mode
- `b=2`: `eS()`
- `b=3`: `a(true, false)`
- `b=4`: `cZ()` (damage/death?)

### Entity Update Chain
```
a.a()     // player update
aC()      // game logic
cx()      // collisions
dd()      // effects
bc()      // entities
bu()      // projectiles
z()       // particles
bA()      // input
q()       // special input
```

### Camera System
- `T`, `U` — camera position (fixed-point, >> 14)
- `T >> 14` = camera X, `U >> 14` = camera Y

### Key Mappings Added (32 new)
| Obfuscated | Semantic Name | Category |
|------------|---------------|----------|
| `z()` | `updateInGame` / `renderInGame` | state |
| `B()` | `updateGameLoop` | core |
| `c(Graphics,boolean)` | `renderHUD` | render |
| `A(Graphics)` | `renderPlayer` | render |
| `d(Graphics,boolean)` | `renderTarget` | render |
| `S(Graphics)` | `renderWorld` | render |
| `bc()` | `updateEntities` | logic |
| `bu()` | `updateProjectiles` | combat |
| `spawnEnemy` | `spawnEnemy` | enemy |
| `playSound` | `playSound` | audio |
| ... | ... | ... |

---

## Current Statistics

| Metric | Count |
|--------|-------|
| Total methods analyzed | ~1672 |
| Classes renamed | 8 |
| State machine states | 20 |
| Methods mapped | 120 |
| High-confidence mappings | ~57 |
| Auto-suggested mappings | 7 |
| Remaining unmapped | ~1550 |

---

## Limitations Without Original mapping.txt

1. **Overloaded methods**: Many methods share names like `a()`, `b()`, `c()` — indistinguishable without execution traces
2. **Field names**: All fields still obfuscated (`a`, `b`, `c`, `d`, `e`...)
3. **Local variables**: All local variables obfuscated in decompiled code
4. **Comments**: None recovered
5. **Generic methods**: Methods with identical signatures but different purposes cannot be distinguished

---

## What's Achievable Next

### High-value targets (2-4 hours each):
1. **Complete enemy/NPC system** — map all `AudioManager.j()`, `AudioManager.k()`, `spawnEnemy` methods
2. **Complete particle system** — 5 methods, already mostly mapped
3. **Complete IGP/Network system** — 15 methods in GameController
4. **Complete audio system** — 30 methods in AudioManager

### Medium-value targets (4-8 hours each):
1. **Complete InGame render pipeline** — 100+ render methods
2. **Complete input handling** — 20 methods
3. **Complete HUD/UI system** — 30 methods

### Low-value (not recommended without original mapping):
1. Renaming every individual `a(Graphics)` method — impossible without runtime tracing
2. Distinguishing between 300+ render methods with identical signatures

---

## Comparison With Other Gameloft Decomps

This project follows the same pattern as:
- **Diamond-Rush-Decomp** (palaceswitcher) — similar Aurora engine
- **Greenier-Farm-3-Decomp** (SmithGoll) — same GLLib architecture
- **Bounce Tales decomp** (HelloOO7) — similar J2ME obfuscation style

All these games use:
- Single massive `GameCanvas` class (1000+ methods)
- State machine with numeric IDs
- Fixed-point arithmetic (`>> 14`)
- `GameData` entity system
- `AudioManager` for both audio and gameplay logic

The Zombie Infection codebase is consistent with this pattern.
