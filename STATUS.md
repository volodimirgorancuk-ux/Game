# Zombie Infection Restaura`tion — Status Report
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
- PNG encoder (pure Python, без Pillow): `obf_deobf/png_writer.py`
- Tile map визуализировано: `obf_deobf/decoded/tilemap_240x220.png`
  - Размер: 240x220 пикселей (ближайшее к 240x320)

### 4. Документация
- State machine: `obf_deobf/state_machine_detailed.md`
- Основные состояния: MENU (0/1), INGAME (4), INGAME_SUB (6), GAMEOVER (7), SPECIAL (99)
- Отчет: `RESTORATION_REPORT.md`

## Что остаётся сделать

### High priority
1. **Спрайты**: реализовать декодер Gameloft sprite format (RLE + tile-based)
2. **Палитры**: декод `palettesAmount.bin` и применить к тайлам
3. **Уровни**: парсить m2/m9 форматы (tilemap + entities)

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
| `obf_deobf/decoded/*` | Извлеченные ассеты |
| `obf_deobf/state_machine_detailed.md` | State machine документация |
