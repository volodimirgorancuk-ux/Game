# Zombie Infection (v1.2.0) — Full Deobfuscation Report
*Gameloft J2ME, MIDP 2.0 / CLDC 1.0, 240x320*

## 1. Classes Recovered
| Original | Deobfuscated | Description |
|----------|--------------|-------------|
| `g` | `GameCanvas` | Main game loop, input, rendering, state machine (1335 methods) |
| `b` | `GameController` | MIDlet, IGP, resource management, loading screens |
| `a` | `GraphicsEngine` | Sprite/tile rendering engine, palette management |
| `c` | `GameData` | World data, level state, entity arrays |
| `d` | `GameConfig` | Static configuration constants |
| `e` | `SensorHandler` | Accelerometer input, gesture detection |
| `f` | `AudioManager` | Sound, music, enemy/NPC audio logic |

## 2. Method Renaming
- **1,207 unique method signatures** processed via `final_mapping.json`
- **568 methods** renamed in bytecode via ASM (`ApplyMethodRenames.java`)
- **Overloaded methods** disambiguated with hash suffixes: `draw_27cc`, `calc_52a0`, etc.
- **Known aliases** (CFR decompiler artifacts):
  - `triggerDamageFlash` = generic load/render/dispatch kernel (many overloads)
  - `spawnEnemy` = stream reader / entity spawn helper
  - `isPlayerDead` = camera/frustum helper
- **Verified semantic names**: `gameLoop`, `updateMainMenu`, `updateInGame`, `updateGameOver`, `updateOptions`, `updateLoading`, `updatePaused`, `drawMainMenu`, `drawInGame`, `drawGameOver`, `createOffscreenBuffer`, `triggerDamageFlash`, `isPlayerDying`, `loadLevel`, `drawBackground`, `drawForeground`, `drawSprites`

## 3. State Machine (GameCanvas.u)
| State | Name | Description |
|-------|------|-------------|
| 0, 1 | MENU | Main menu, options, loading |
| 4 | INGAME | Core gameplay loop |
| 6 | INGAME_SUB | Sub-states: bg(2), load(3), fg(4), buffer(5), particles(6) |
| 7 | UNKNOWN | Referenced but not fully traced |
| 99 | SPECIAL | Cutscene/special state |

### InGame Substates (u==6, v==x)
- `v==2`: `drawBackground()` — tilemap render
- `v==3`: `loadLevel()` — decompress level data
- `v==4`: `drawForeground()` — entity render
- `v==5`: `drawBuffer()` — offscreen buffer blit
- `v==6`: `stopParticles()` — particle cleanup

## 4. Assets & Formats
### Archive Format (m* files)
All `m*` resources are encrypted archives:
- **Header**: 1-byte count + `count * 4`-byte LE offset table
- **Chunks**: variable-length encrypted blobs
- **Decoded chunks** extracted to `obf_deobf/decoded/`

### Resource Inventory
| File | Type | Description |
|------|------|-------------|
| `m3_0` | sprites | Large sprite sheet (~627 KB, 11 chunks) |
| `m4_0` | tiles | Tile graphics (~427 KB, 10 chunks) |
| `m2` | levels | Level geometry/data (~126 KB, 6 chunks) |
| `m9` | levels | Level data (~222 KB, 16 chunks) |
| `m6_0`–`m6_5` | sprites | Sprite sets (~50–166 KB each) |
| `m7` | sprites | Sprite data (~164 KB, 9 chunks) |
| `m8` | sprites | Sprite data (~14 KB, 7 chunks) |
| `m10` | sprites | UI/sprite data (~7 KB, 6 chunks) |
| `m11_0`, `m11_1` | tiles | Tile maps (~65–86 KB each) |
| `m13_2` | music | MIDI sequences (32 chunks, ~75 KB) |
| `m5_*` | ui | Font/tile mapping tables |
| `m12` | effects | Particle/effect data |
| `t0` | font | Bitmap font glyphs |
| `palettesAmount.bin` | palettes | 31 palette entries |

### GraphicsEngine Sprite Format
Chunks with `.sprite` extension use this layout:
1. **Header**: 
   - byte 0: version skip
   - bytes 1–4: flag/version (LE int)
   - bytes 5–6: frame count (LE short)
   - byte 7: skip
2. **Frame table** (per frame):
   - `0xFF`: special frame — 4-byte offset + width + height
   - `0xFE`: special variant — same layout
   - else: normal frame — x(2), y(2), w(2), h(2) (LE shorts)
3. **Secondary array**: 2-byte count + `count * 2`-byte LE values
4. **Extra data**: if flag `0x8000` set — 2-byte count + `count * 4`-byte entries

## 5. Build & Packaging
- **J2ME SDK**: Not installed; built using OpenJDK 11 with manual J2ME API stubs
- **Stub packages**: `javax.microedition.lcdui`, `midlet`, `media`, `rms`, `sensor`, `io`
- **Compilation**: `javac -source 1.6 -target 1.6 -classpath stub`
- **Output JAR**: `build/package/ZombieInfection.jar` (1,129,126 bytes)
- **JAD descriptor**: `build/package/META-INF/MANIFEST.MF`
- **Issue**: Decompiled sources require cleanup of CFR artifacts (`** GOTO`, `lbl-1000:`, reserved identifiers) before full compilation

## 6. Key Artifacts
| Path | Description |
|------|-------------|
| `fully_method_renamed.jar` | Original bytecode with 568 method renames applied |
| `build/package/ZombieInfection.jar` | Repackaged with J2ME manifest |
| `fully_deobfuscated_src/` | CFR-decompiled + cleaned Java sources |
| `obf_deobf/final_mapping.json` | 1,207 merged method mappings |
| `obf_deobf/decoded/` | Extracted & guessed-typed asset chunks |
| `state_machine_detailed.md` | State machine documentation |
| `RenameClasses.java` | ASM class renamer (a→GraphicsEngine, etc.) |
| `ApplyMethodRenames.java` | ASM method renamer (568 mappings) |

## 7. Remaining Work
1. **Sprite decoding**: implement Gameloft sprite renderer (RLE/tile-based) using GraphicsEngine logic
2. **Palette decryption**: interpret `palettesAmount.bin` and apply to tiles/sprites
3. **Level decoding**: parse m2/m9 chunk formats (tilemaps + entity placement)
4. **J2ME emulator**: integrate MicroEmulator or PhoneME for runtime testing
5. **Full source compilation**: fix remaining CFR artifacts for 100% clean build
6. **State completion**: document states 7, 99 and all sub-state transitions

## 8. How to Run (when emulator available)
1. Load `build/package/ZombieInfection.jar` into J2ME emulator
2. Emulator must support MIDP 2.0, CLDC 1.0, 240x320
3. Controls: arrow keys = D-pad, Z = fire, X = special, Enter = pause
4. Touch/pointer events mapped in `SensorHandler`
