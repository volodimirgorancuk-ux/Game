# GameCanvas State Machine — Детальный анализ
*Источник: decompiled/GameCanvas.java, fully_deobfuscated_src/GameCanvas.java*

## Главный state variable
- `GameCanvas.u` — текущее состояние (int)
- `GameCanvas.v` — под-состояние (int)

## Полный список состояний

| u | Название | Описание | Файл/Строка |
|---|----------|----------|-------------|
| 0 | MENU_MAIN | Главное меню | GameCanvas.java:949 |
| 1 | MENU_OPTIONS | Настройки / Options | GameCanvas.java:950 |
| 4 | INGAME | Основной геймплей | GameCanvas.java:807 |
| 6 | INGAME_SUB | Под-состояния игры (v=2..6) | GameCanvas.java:3541 |
| 7 | CUTSCENE_OR_LOAD | Сюжетная сцена / загрузка | упоминается в switch |
| 99 | SPECIAL | Спец. состояние (возможно victory) | упоминается в коде |

## Под-состояния INGAME (u=6)

| v | Название | Метод | Описание |
|---|----------|-------|----------|
| 0 | GAMEPLAY | updateInGame() | Основной цикл | 
| 1 | PAUSED | setPaused() | Пауза |
| 2 | BG_RENDER | drawBackground() | Рендер фона |
| 3 | LOAD_LEVEL | loadLevel() | Загрузка уровня |
| 4 | FG_RENDER | drawForeground() | Рендер объектов |
| 5 | BUFFER_BLIT | drawBuffer() | Вывод offscreen buffer |
| 6 | PARTICLES | stopParticles() | Частицы |

## Ключевые методы состояния

### Transition / Control
- `c(int n, int n2, boolean bl)` — установка состояния u, вызов init-методов
- `S()` — инициализация палитр из `/palettesAmount.bin`
- `T()` — helper для state transitions

### Update методы
- `v()` / `updateMainMenu()` — state 0/1
- `z()` / `updateInGame()` — state 4
- `B()` / `updateGameOver()` — экран смерти
- `x()` / `updateOptions()` — опции
- `F()` / `updateLoading()` — загрузка
- `s()` / `setPaused()` — пауза
- `E()` / `updatePausedSubmenu()` — подменю паузы
- `G()` / `updateConfirmDialog()` — диалоги

### Render методы
- `b(Graphics)` / `drawMainMenu()` — state 0/1
- `j(Graphics)` / `drawInGame()` — state 4
- `e(Graphics)` / `drawGameOver()` — death screen
- `a(String)` — загрузка ресурса, индексация h[], A[]
- `b(int)` / `spawnEnemy(int)` — чтение чанка из ресурса

### Input
- `o(int)` / `handleWeaponSelect(int)` — выбор оружия
- `q(int)` / `handleSpecialInput(int)` — спец. действие

## State transition flow

### Main menu -> Gameplay
```
c(4, subState, true)  [state 4]
  -> X()  init touch
  -> ab() init game
  -> aa() init audio
  -> y(0) set paused
  -> x(0) reset input
  -> T() state helpers
  -> d(3, n) log transition
```

### Gameplay -> Paused
```
setPaused()
  -> c(1, v, false) [state 1 = paused]
```

### Gameplay -> GameOver
```
isPlayerDead(...) -> ce=0
  -> E = null
  -> T = true  
  -> triggerDamageFlash(true, true)
  -> c(7, ...) [state 7 = game over]
```

### Pause submenu
```
updatePausedSubmenu()
  -> triggerDamageFlash(true/false)
  -> playFootstep(...)
```

## Rendering pipeline (INGAME)

```
drawInGame(var0)
  block0 switch(u):
    case gameplay:
      switch(var0):
        0 -> update_8a5c()  [sub-tick]
        1 -> update_02b1()  [sub-tick]
        2 -> draw_f54d()    [render frame]
    case 0-1:
      draw_f0f7()          [low-level render]
```

## Вспомогательные state machine

### Camera (GameCanvas.u -> T, U)
- `updateCamera(int n)` — блок switch по n
  - n=0: calc_119c()
  - n=1: player follow
  - n=2: a[19].triggerDamageFlash(...)

### Entity array operations
- `GameCanvas.u` в контексте сущностей = массив `b[][]`
  - `b[n][0]` = тип сущности
  - `b[n][1]` = x
  - `b[n][2]` = y  
  - `b[n][14]`, `b[n][10]` = атрибуты

## Константы
- `L[]`, `M[]` — границы экрана/камеры
- `a[17]` — параметры UI overlay
- `AE`, `AF`, `AG` — input state bitmasks
