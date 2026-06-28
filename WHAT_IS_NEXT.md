# Zombie Infection — Что нужно для полного восстановления

## Текущее состояние (2026-06-28)

### Уже сделано
1. Деобфускация 7 классов: `GameCanvas`, `GameController`, `GraphicsEngine`, `GameData`, `GameConfig`, `SensorHandler`, `AudioManager`, `GloftMASS`.
2. 568 методов переименовано в байткоде (`fully_method_renamed.jar`).
3. Архивы `m*` распакованы, ассеты извлечены в `obf_deobf/decoded/`.
4. PNG-визуализации: тайлсет, тайлмап.
5. State machine частично документирована.
6. Чистка CFR-артефактов: скрипт `obf_deobf/fix_decompilation.py` исправляет catch-блоки, метки, `cfr_ignored_*`.
7. `BUILD_GUIDE.md` — пошаговая инструкция по сборке.
8. `build_tileset.py` — сборка `tileset_sheet.png` из отдельных тайлов.

### Основные блокеры
1. **Дубликаты имён полей** — в `GameCanvas.java` 74+, `AudioManager.java` — 85+ дубликатов имён `a`, `b`, `c` с разными типами. Java не позволяет такую компиляцию.
2. **118 методов с `Unable to fully structure code`** — их тела содержат мусор и не компилируются.
3. **Спрайты** — декoder для `.sprite` файлов падает с `IndexError`, пиксельные данные не извлечены.

## Что нужно сделать (пошагово)

### Phase 1. Восстановление исходников (P0)

1. **Передекомпилировать через Fernflower** (или Jadx)
   - CFR даёт слишком много артефактов и дубликатов полей.
   - Fernflower/Jadx лучше справляются с переименованным байткодом.
   - В `fully_method_renamed.jar` классы уже переименованы, декомпилятор создаст более чистые исходники.
   
2. **Очистить артефакты** (автоматически)
   - Запустить `python3 obf_deobf/fix_decompilation.py src`
   - Исправит catch-блоки, метки, `cfr_ignored_*`, `var_do`.

3. **Устранить дубликаты полей** (частично автоматически, частично вручную)
   - Скрипт `obf_deobf/rename_duplicate_fields.py` переименовывает объявления полей, добавляя тип: `int a` → `a_int`.
   - После этого нужно вручную обновить все использования этих полей по всему коду.
   - Альтернатива: использовать байткод для получения точных имён (через ASM), но это требует Java.

4. **Исправить `Unable to fully structure code`**
   - Либо восстановить тела методов по байткоду через ASM.
   - Либо оставить заглушки `{}` если методы не критичны для компиляции.

### Phase 2. Сборка и тестирование (P1)

1. **Собрать JAR**
   ```bash
   javac -source 1.6 -target 1.6 -bootclasspath <J2ME_APIs> -d build/classes src/*.java
   jar cvf build/package/ZombieInfection.jar -C build/classes .
   ```

2. **Запустить в эмуляторе**
   - MicroEmulator: `java -jar microemulator-2.0.4/microemulator.jar -Xapp build/package/ZombieInfection.jar`
   - KEmulator: `java -jar kemnnx64/KEmulator.jar`

3. **Исправить падения** (NullPointerException, ArrayIndexOutOfBounds) из-за неточностей в декodinге ассетов.

### Phase 3. Декодирование ассетов (P1)

1. **Спрайты** (`.sprite` файлы)
   - Парсер `decode_gameloft_graphic.py` падает на `m10_chunk02.sprite`, `m13_1_chunk00.sprite`.
   - Нужно выяснить точный формат: возможно, данные зашифрованы или имеют другой заголовок.
   - После дешифровки — раскодировать RLE/pixel data через палитры.

2. **Палитры** (`palettesAmount.bin`)
   - Сейчас 31 таблица яркости/контраста.
   - Нужно понять, как они применяются к тайлам/спрайтам в `GraphicsEngine`.

3. **Уровни** (`m2`, `m9`)
   - Распарсить форматы: tilemap + entity placement + triggers.

### Phase 4. Финальная сборка и инъекция ассетов (P2)

1. **Интегрировать ассеты в JAR** — упаковать декодированные PNG/MIDI обратно в ресурсы.
2. **Полная компиляция из исходников** — без CFR-артефактов и дубликатов.
3. **Создать финальный JAR** `ZombieInfection_restored.jar`.

## Быстрый старт (если хочется запустить сейчас, без исходников)

```bash
java -jar build/package/ZombieInfection.jar
```

Игра уже запускается. Основной остаток — это деобфускация исходников для читаемости.

## Контакты / follow-up

Если нужно продолжить:
1. Начать с Phase 1 — передекомпиляция Fernflower + чистка.
2. Или с Phase 3 — декодирование спрайтов.

---

Обратите внимание: `rename_duplicate_fields.py` в текущем виде может сломать код. Используйте его на копиях файлов и проверяйте результат.
