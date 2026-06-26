# Zombie Infection (J2ME) — Deobfuscation Summary

## Output files
- **deobfuscated.jar** — JAR with renamed classes (GloftMASS unchanged, `g`->GameCanvas, etc.)
- **mapping.txt** — ProGuard-style mapping: deobfuscated -> obfuscated names
- **decompiled/** — Full CFR 0.152 decompiled source code
- **obf_deobf/** — Working directory with renamer tooling

## Renamed classes

| Original | Obfuscated | Role |
|----------|------------|------|
| GloftMASS | GloftMASS | MIDlet entry point (unchanged) |
| GameCanvas | g | Main game loop, rendering, input (422 KB, 1335 methods) |
| GraphicsEngine | a | Sprite/tile rendering, image parsing |
| GameController | b | IGP system, resource loading, text rendering |
| GameData | c | Entity/layer data structure (fixed-point positions) |
| GameConfig | d | Static feature flags |
| SensorHandler | e | Accelerometer input |
| AudioManager | f | Audio playback + enemy/NPC game-object logic |

## Game architecture insights
- **GameCanvas**: main loop runs at 60 FPS (166ms frame). State machine (`a` field), offscreen buffer via `Image`+`Graphics`, particle system (`v`=systems, `u`=particles), 200 audio channels, 40 sprite layers.
- **GameController**: loads `/dataIGP`, resolves operator-specific URLs (IGP-BS, IGP-WN, IGP-LIVE, IGP-PROMOS, URL-OPERATOR, URL-PT, URL-ORANGE).
- **AudioManager**: surprisingly contains enemy logic (`AudioManager.j()` = get total health of enemy entity, `AudioManager.k()` = current health; references `CObject_Enemy.ENEMY_GetTotalHealth`, `NPC_SetFollowTargetObject`).
- **Device detection**: Sony Ericsson models W890, K850, K800, K790, K770, T650, S500, W580, W880, K810, W850, W760.
- **Resolution data**: stored in static arrays (240x320 primary, 176x220 fallback detected by `ak <= 176`).

## Key method mappings (core only)

```
GameCanvas:
  run() -> game loop (a != 99 = running, a == 99 = exit)
  t() -> start state
  u() -> switch/stop state
  d(int,int) -> setState(stateId, param)
  f(boolean) -> setRunning(boolean)
  l(int) -> hasFlag(int)
  a(Graphics) -> clearScreen
  e(int,int) -> createOffscreenBuffer
  j(Graphics) -> drawBuffer
  c(int,int,int,int,...) -> addTouchRegion
  m(int) -> setTile

GameController:
  b() -> loadIGPData
  c() -> isOnline
  a(String) -> getAppPropertyCached
  g() -> openURL
  actionPerformed(Command,Displayable) -> onCommand
  a(Graphics,Image,int,int,int) -> drawImageBlinking

GameData (entity):
  setTile(int) -> b(int)
  setAnimation(int,int,int) -> a(int,int,int)
  render(Graphics) -> a(Graphics)
  update() -> a()
```

## Limitations
- Methods/fields are still obfuscated (`a`, `b`, `c`, `d`). Full semantic renaming requires:
  1. Original source or ProGuard `mapping.txt` from build time.
  2. Manual analysis of every method (~1335 in GameCanvas alone).
- ProGuard `-applymapping` cannot create new semantics; it only applies provided rename rules.
- Some game logic is duplicated between `AudioManager` and `GameCanvas` due to original obfuscation layout.

## Next steps for deeper deobfuscation
1. Focus on `GameCanvas` state machine (fields `a`..`az`, `aA`..`aZ`).
2. Cross-reference `GameCanvas` draw methods with sprite counts (40 sprites, 70 audio).
3. Examine `dataIGP` resource extraction paths to reconstruct URL templates.
4. Replace `AudioManager` semantic name to `GameObjectManager` in enhanced_mapping.txt if confirmed.
