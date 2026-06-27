# GameCanvas State Machine — Full Mapping

## State Dispatch Methods

### `o()` — Main update loop (update state)
Switch on field `a` (state ID):
```
case 0  -> GameCanvas.v()   // updateMainMenu
case 1  -> GameCanvas.x()   // updateOptions
case 2  -> GameCanvas.F()   // updateLoading
case 3  -> GameCanvas.V()   // updateCutscene
case 4  -> GameCanvas.z()   // updateInGame
case 5  -> GameCanvas.bK()  // updatePaused
case 6  -> GameCanvas.B()   // updateGameOver
case 7  -> GameCanvas.cd()  // updateTutorial (?)
case 8  -> GameCanvas.cj()  // updateSubState (depends on field `u`)
case 10 -> GameCanvas.dH()  // updateContinueScreen
case 11 -> GameCanvas.dA()  // updateVictoryScreen
case 12 -> GameCanvas.eh()  // updateCredits
case 13 -> GameCanvas.fE()  // updateLevelSelect
case 16 -> GameCanvas.C()   // updateSpecial (?)
case 17 -> GameCanvas.aO()  // updateCharacterSelect
case 18 -> GameCanvas.E()   // updatePausedSubmenu
case 19 -> GameCanvas.G()   // updateConfirmDialog
case 99 -> break            // exit / shutdown
```

### `p()` — Main render loop (render state)
Switch on field `a` (state ID):
```
case 0  -> GameCanvas.b(a)   // drawMainMenu
case 1  -> GameCanvas.c(a)   // drawOptions
case 2  -> GameCanvas.h(a)   // drawLoading
case 3  -> GameCanvas.l(a)   // drawCutscene
case 4  -> GameCanvas.j(a)   // drawInGame
case 5  -> GameCanvas.j(a)   // drawPaused (same as in-game overlay)
case 6  -> GameCanvas.e(GameCanvas.a())  // drawGameOver
case 7  -> GameCanvas.P(a)   // drawTutorial (?)
case 8  -> GameCanvas.R(a)   // drawSubState (depends on field `u`)
case 10 -> GameCanvas.ar(a)  // drawContinueScreen
case 11 -> GameCanvas.ao(a)  // drawVictoryScreen
case 12 -> GameCanvas.ax(a)  // drawCredits
case 13 -> GameCanvas.n(a, false)  // drawLevelSelect
case 17 -> GameCanvas.q(a)   // drawCharacterSelect
case 18 -> GameCanvas.g(a)   // drawPausedOverlay / drawInGamePaused
case 19 -> GameCanvas.i(a)   // drawConfirmDialog
case 20 -> break
```

### `u()` — State exit / cleanup handler
```
case 3  -> GameCanvas.T()    // exitCutscene
case 6  -> GameCanvas.bp(); GameCanvas.p(false); GameCanvas.dF()  // exitGameOver
case 10 -> GameCanvas.dE()   // exitContinueScreen
case 12 -> GameCanvas.bp()   // exitCredits
case 13 -> GameCanvas.bp()   // exitLevelSelect
case 18 -> GameCanvas.ai() or GameCanvas.a(3, -1)  // resumeFromPause
case 7  -> GameCanvas.ce()   // exitTutorial (?)
default -> GameCanvas.ai(); GameCanvas.ax()  // fallback cleanup
```

### `t()` — State entry / initialization handler
Called when entering a new state. Initializes audio (aw), sprites (af), level (ah).

### `d(int state, int param)` — State transition
Sets field `a` = state, field `b` = param, calls `s()` (mark dirty).

## State Descriptions (inferred)

| State | ID | Update Method | Render Method | Purpose |
|-------|-----|---------------|----------------|---------|
| MainMenu | 0 | v | b | Main menu, title screen |
| Options | 1 | x | c | Settings/options screen |
| Loading | 2 | F | h | Resource loading screen |
| Cutscene | 3 | V | l | Story cutscene / cinematic |
| InGame | 4 | z | j | Core gameplay |
| Paused | 5 | bK | j | Pause overlay (renders over in-game) |
| GameOver | 6 | B | e | Game over screen |
| Tutorial | 7 | cd | P | Tutorial/help (?)
| SubState | 8 | cj | R | Sub-menu (depends on field `u` = mode)
| Continue | 10 | dH | ar | Continue screen |
| Victory | 11 | dA | ao | Victory/success screen |
| Credits | 12 | eh | ax | Credits roll |
| LevelSelect | 13 | fE | n | Level/mission select |
| Special | 16 | C | f | Unknown special state |
| CharacterSelect | 17 | aO | q | Character selection (Anderson/Mike/Shawna)
| PausedSubmenu | 18 | E | g | In-game pause submenu |
| ConfirmDialog | 19 | G | i | Yes/No confirmation dialog |
| Unknown20 | 20 | - | - | (empty in render) |
| Exit | 99 | - | - | Shutdown / cleanup |

## Nested State: `b` field (sub-state)

Within some states (e.g., state 0 in method `x()`):
```
case 1 -> GameCanvas.w(); GameCanvas.a(0, false); GameCanvas.n(2)  // new game
case 2 -> wait for ready; a[0].a(0, 0)  // load game
case 3 -> if ready: a[0].a(c); n(4)      // continue
case 4 -> a(1, false); wait; a(0); n(5)  // ?
case 5 -> ...                             // ?
```

## Nested State: `u` field (mode within state 8)
```
u = 1 -> GameCanvas.cj() / GameCanvas.R(a)  // some sub-mode
```

## Key Observations

1. **State 4 (InGame)** is the core gameplay state — it calls `updateInGame()` which contains the main game logic
2. **State 18 (PausedSubmenu)** is entered from state 4 when pausing
3. **States 10-13** are post-game / progression screens
4. **State 17 (CharacterSelect)** matches the gameplay description: player chooses between Anderson, Mike, Shawna
5. State 99 triggers `GloftMASS.a.destroyApp(true)` in the game loop — it's the exit signal

## Unknown States (need deeper analysis)
- State 7: likely tutorial
- State 16: unknown special screen
- State 20: appears in render dispatch but has no body
- Sub-states within state 0 (main menu) and state 8
