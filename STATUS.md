# Zombie Infection Restoration — Status Report
*Gameloft J2ME, v1.2.0, 2026-06-27*

## Что готово

### 1. Классы и методы переименованы
- 7 классов: `GameCanvas`, `GameController`, `GraphicsEngine`, `GameData`, `GameConfig`, `SensorHandler`, `AudioManager`
- 568 методов переименовано в байткоде JAR через ASM
- Итоговый JAR: `build/package/ZombieInfection.jar` (1.1 MB)
- JAD descriptor: `build/package/META-INF/MANIFEST.MF`

### 2. Ассеты извлечены
- Формат архивов `m*`: 1-byte count + 4-byte LE offsets
- Извлечено ~300 чанков в `obf_deobf/decoded/`
- Типы определены: `.tile`, `.sprite`, `.level`, `.bin`

### 3. Визуализация
- PNG encoder (pure Python): `obf_deobf/png_writer.py`
- Сгенерировано 30+ PNG:
  - `tilemap_240x220.png` — 240x220 indexed tile map
  - `tilemap_pal04.png`, `tilemap_pal05.png`, `tilemap_pal22.png`, `tilemap_pal26.png`, `tilemap_pal27.png` — tile maps с разными палитрами (наиболее содержащие)
  - `m4_tile7_vis.png` — визуализация large tile data
  - `m8_sprite_vis.png`, `m10_sprite_vis.png` — спрайтовые атласы
- Палитры из `palettesAmount.bin`: 31 палитра переменной длины, суммарно 279 байт. Частично декодированы как RGB565.
- MIDI: `m13_2_music.mid` — 29 треков, 73 KB

### 4. State machine
- Документ: `obf_deobf/state_machine_detailed.md`
- Основные состояния: MENU (0/1), INGAME (4), INGAME_SUB (6), GAMEOVER (7), SPECIAL (99)
- Sub-states INGAME: bg(2), load(3), fg(4), buffer(5), particles(6)

## Что остаётся сделать

### High priority
1. **Спрайты**: реализовать точный декодер Gameloft sprite format (требует синхронизации с GraphicsEngine.a() байт-кодом)
2. **Палитры**: понять точный формат palettesAmount.bin и применить к тайлам
3. **Уровни**: парсить m2/m9 форматы

### Medium priority  
4. **J2ME эмулятор**: собрать MicroEmulator или PhoneME
5. **Полная компиляция**: починить CFR артефакты в исходниках
6. **Поля и переменные**: деобфускация имён полей (a, b, c...) и локальных переменных

### Low priority
7. **Тестирование**: запуск игры в эмуляторе

## Ключевые файлы
| Файл | Описание |
|------|----------|
| `build/package/ZombieInfection.jar` | Готовый JAR для эмулятора |
| `obf_deobf/final_mapping.json` | 1207 mapped методов |
| `fully_deobfuscated_src/*.java` | Переименованные исходники |
| `obf_deobf/decoded/*` | Извлеченные ассеты + PNG/MIDI |
| `obf_deobf/state_machine_detailed.md` | State machine документация |
| `RESTORATION_REPORT.md` | Полный отчёт |

## Просмотр ассетов
Готовые изображения в `obf_deobf/decoded/`:
- `tilemap_pal04.png`, `tilemap_pal05.png`, `tilemap_pal22.png`, `tilemap_pal26.png`, `tilemap_pal27.png` — наиболее содержащие tile map визуализации
- `tilemap_240x220.png` — общая раскраска
- `m4_tile7_vis.png` — сырые тайловые данные
- `m13_2_music.mid` — музыка
