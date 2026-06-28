# Zombie Infection — Restoration Status
*Автоматически сгенерировано: 2026-06-28*

## Что готово

### 1. Рабочий JAR (можно запускать)
- `fully_method_renamed.jar` — переименованы классы и 568 методов
- `build/package/ZombieInfection.jar` — упакован с J2ME manifest
- Оба JAR идентичны по содержимому (43 файла каждый)

### 2. Расшифровка ассетов
- Архивы `m*` распакованы (300+ чанков)
- Тайлсет: 256 тайлов 16×16 → `tileset_sheet.png` (исправлен баг размера)
- Тайлмап уровня: `tilemap_240x220.png`
- Пагины встроенные в `.tile` файлы (магические числа `17476`, `22018`)
- MIDI музыка: `m13_2_music.mid` (29 треков)
- Sprite заголовки частично расшифрованы

### 3. Инструменты
- `obf_deobf/png_writer.py` — pure Python PNG encoder
- `obf_deobf/build_tileset.py` — сборка tileset_sheet.png
- `obf_deobf/decode_graphic.py` — декодер Gameloft graphic format
- `obf_deobf/debug_sprite.py` — отладчик спрайтов
- `obf_deobf/parse_jar.py` — парсер Java class файлов

## Что НЕ получилось

### Деобфускация исходников
- CFR 0.152 падает на `m10_chunk02.sprite` (frame count = 35349, неразрешимый формат)
- Дубликаты имён полей: Java не разрешает `int a` и `Graphics a` в одном классе
- `--renamedupmembers` у CFR создаёт `var_boolean_a`, `var_int_a` но ломает control flow
- `** GOTO lblXXX` артефакты — 100+ вхождений
- `try` без `catch` — сломанные блоки восстановления
- `do`, `if`, `else` как имена методов/переменных — конфликты с Java keywords

### Прочее
- `palettesAmount.bin` — 31 таблица яркосности, но не подключена к рендерингу
- Уровни m2/m9 — форматы не распознаны
- Спрайты m8, m10, m13 — пиксельные данные не извлечены

## Файлы-результаты

| Файл | Описание |
|------|----------|
| `fully_method_renamed.jar` | Рабочий JAR с переименованными классами/методами |
| `build/package/ZombieInfection.jar` | Тот же JAR, упакованный с manifest |
| `obf_deobf/decoded/tileset_sheet.png` | Собранный тайлсет 256×256 |
| `obf_deobf/decoded/tilemap_240x220.png` | Визуализация тайлмапа |
| `obf_deobf/decoded/tiles/` | 256 отдельных тайлов 16×16 |
| `obf_deobf/decoded/m13_2_music.mid` | Извлечённая музыка |
| `obf_deobf/PROGRESS_UPDATE.md` | Детали открытий по форматам |
| `BUILD_GUIDE.md` | Инструкция по сборке |
| `RESTORATION_PLAN.md` | План работ |

## Как запустить игру

```bash
# Через KEmulator
java -jar kemnnx64/KEmulator.jar
# File -> Open JAD/JAR -> fully_method_renamed.jar

# Или через MicroEmulator
java -jar microemulator-2.0.4/microemulator.jar -Xapp fully_method_renamed.jar
```

## Что ещё нужно для полной деобфускации

1. **Fernflower/Jadx** — CFR недостаточно хорош для этого кода
2. **Ручная работа** — 4–12 часов на устранение артефактов CFR
3. **Полный mapping** — 1207 методов, но только 568 переименовано в байткоде
4. **Спрайты** — нужен точный формат decoder
5. **Палитры** — понять `palettesAmount.bin` и применить к тайлам
