# InGame State (state 4) — Full Analysis

## Dispatch
- Update: `GameCanvas.z()` at line 1081
- Render: `GameCanvas.z(Graphics)` at line 11652

## Update Entry Point: `z()`
```java
private static void z() {
    GameCanvas.Z(bB);      // init something with bB param
    GameCanvas.aC();       // update game logic
    GameCanvas.cn();       // spawn enemies / update entities
}
```

## Main Game Loop: `B()` (lines ~1195-1280)
This is the **core gameplay update** method, called every frame during InGame.

### Sub-state: `b` field (0-4)
| b value | Behavior |
|---------|----------|
| 0 | Normal gameplay — player shooting, audio, camera |
| 1 | Alternative mode — cutscene/event? |
| 2 | Another mode — `eS()` |
| 3 | Another mode — `a(true, false)` |
| 4 | Another mode — `cZ()` |

### AudioManager state: `AudioManager.d` (mute toggle)
- Toggled when `u[3] == 2 && (ez & 2) != 0` (some input condition)

### Entity update chain
```
a.a()  // GameData.update() for player entity
aC()   // update game logic
cx()   // update something
dd()   // update something
bc()   // update entities/particles
bu()   // update bullets/projectiles
z()    // update particles
br()   // update particles
bm()   // update particles
v()    // update timers
bA()   // handle input
q()    // handle special input
```

## Render Entry Point: `z(Graphics)` (line 11652)
```java
private static void z(Graphics graphics) {
    // Letterbox bars (widescreen black bars)
    if (z) graphics.setColor(0xFFFFFF).fillRect(0, 0, 240, 320);
    if (aT > 0) {
        graphics.setColor(0);
        graphics.fillRect(0, 0, 240, aT);              // top bar
        graphics.fillRect(0, 320-aT, 240, aT);         // bottom bar
    }
    // ... main game rendering follows
}
```

## Render Methods for InGame
| Method | Line | Purpose |
|--------|------|---------|
| `c(Graphics, boolean)` | ~11860 | Main HUD render (health, weapon, crosshair) |
| `A(Graphics)` | ~11970 | Player/NPC sprite render |
| `d(Graphics, boolean)` | ~12000 | Target/enemy render (with health bar?) |
| `z(Graphics)` | 11652 | Screen effects (bars, fade) |
| `S(Graphics)` | unknown | World render (tilemap, sprites) |

## Key InGame Systems

### 1. Camera / Viewport
Fields `T`, `U` — camera position (fixed-point, >> 14 to get pixel coords)
- `T >> 14` = camera X
- `U >> 14` = camera Y
- `GameCanvas.c(graphics, T >> 14, U >> 14, 0, 0)` — draw world at camera offset

### 2. Player Entity
Field `a[6]` — likely the player `GameData` entity
- `a[6].a()` — update player
- `a[6].a(graphics)` — render player

### 3. HUD / Weapon System
Field `b` = sub-state (0 = default weapon view, 1 = ?)
- `b == 0`: first-person weapon view
- `b == 1`: third-person?
- `a[20]` — weapon/animation data
- `GameCanvas.a(graphics, a[20], n3, ...)` — render weapon sprite

### 4. Health/Damage System
- `AudioManager.a == 100` — player dead?
- `AudioManager.b` — player damaged flag?
- `cB` — current health/armor value
- `B` — damage flash flag
- `C` — health bar visibility flag

### 5. Input / Combat
- `L[1]` — current target entity ID
- `g[bt][L[1]]` — target type/sprite
- `M[0]` — selected weapon ID
- `L[4]` — ammo count?
- `D` — target locked flag
- Blinking logic: `aW` timer for target lock reticle

### 6. Game Flow within InGame
```
state 4, b=0: normal gameplay
    -> take damage -> b=4? (cZ)
    -> die -> state 6 (GameOver)
    -> pause -> state 18 (PausedSubmenu)
    -> complete level -> state 11 (Victory) or state 10 (Continue)
```

### 7. Level/Sprite data
- `a[20]` — current level sprite data
- `bt` — current level/mission index
- `a[6]` — player entity
- `b[]` — array of GameData entities (NPCs, enemies, objects)

## Next Steps for Full InGame Mapping

1. Map all methods called from `B()` (the main update loop)
2. Map the render methods (`c()`, `A()`, `d()`, `S()`)
3. Identify all sub-state transitions (what changes `b` from 0 to 1/2/3/4)
4. Map the entity array `b[]` — how enemies are spawned and managed
5. Map the weapon system `a[20]`, `M[0]`, `bt`
