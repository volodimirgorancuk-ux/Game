
# GameCanvas State Machine Documentation

## Main State Variable: GameCanvas.u
- Range: 0-19, 99
- Sub-state: GameCanvas.v

## States:
- State 0, 1: Menu / Main Menu
  - updateMainMenu() / drawMainMenu()
  - Sub-states: various menu screens

- State 4: InGame (main gameplay)
  - updateInGame() / drawInGame()
  - Sub-states (GameCanvas.b / GameCanvas.v):
    - 0: Normal gameplay
    - 1: Paused
    - 2: Background rendering
    - 3: Entity/property loading
    - 4: Foreground rendering
    - 5: Buffer rendering
    - 6: Particle effects

- State 6: Sub-state within InGame (when u==6)
  - v==2: drawBackground()
  - v==3: getEntityProperty() / loadLevel()
  - v==4: drawForeground()
  - v==5: drawBuffer()
  - v==6: stopParticles()

- State 7: Unknown (referenced in switch)

## State Transitions:
- GameCanvas.c(int, int, boolean) sets main state u
- GameCanvas.w, GameCanvas.x track sub-states
- GameCanvas.dispatch_5f54, dispatch_8617, dispatch_913e handle state-specific logic

## Key Methods:
- updateMainMenu(): Main menu update
- updateInGame(): Main gameplay update
- updateCamera(): Camera/follow logic
- updateGameOver(): Death/game over screen
- updateOptions(): Options menu
- updateLoading(): Loading screen
- updatePaused(): Pause state
- updateConfirmDialog(): Confirmation dialogs
- updatePausedSubmenu(): Pause submenu

## Rendering Pipeline (InGame):
1. drawBackground() - render tilemap background
2. getEntityProperty() - load level data if needed
3. drawSprites() - render entities/sprites
4. drawBuffer() - render offscreen buffers
5. drawForeground() - render foreground overlay

## Input Handling:
- SensorHandler: accelerometer input
- keyPressed/keyReleased: button input
- pointerPressed/pointerReleased: touch input

## Resource Loading:
- GameCanvas.triggerDamageFlash(String) loads resources from JAR
- Resources: palettesAmount.bin, m0-m13_*, t0
- m* files are encrypted archives containing:
  - m2, m3_0, m4_0, m9: Levels/tilemaps
  - m6_*, m7, m8: Sprites
  - m11_*: Tile data
  - m13_2: MIDI music
  - m5_*: UI/Font data
  - m10, m12: Sound/audio data
  - t0: Font/character data
