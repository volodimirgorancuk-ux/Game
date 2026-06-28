# Zombie Infection — Полный план восстановления
*Обновлено: 2026-06-28*

## Текущий статус

### Готово
| Объект | Статус |
|--------|--------|
| JAR с переименованными классами | ✅ `fully_method_renamed.jar` |
| 568 методов переименовано в байткоде | ✅ |
| Архивы распакованы | ✅ 300+ чанков в `obf_deobf/decoded/` |
| Тайлсет и тайлмап | ✅ `tileset_sheet.png`, `tilemap_240x220.png` |
| MIDI музыка | ✅ `m13_2_music.mid` |
| Очистка CFR-артефактов | ✅ `fix_decompilation.py` |
| Инструкция по сборке | ✅ `BUILD_GUIDE.md` |
| Эмуляторы | ✅ KEmulator + MicroEmulator |

### Блокеры
| Проблема | Объём работы | Приоритет |
|----------|--------------|-----------|
| Дубликаты имён полей | 3–6 часов ручной работы | P0 |
| 118 методов `Unable to fully structure code` | 2–4 часа | P0 |
| Спрайты не декодированы | Неизвестно, требует реверса формата | P1 |
| Палитры не применены к спрайтам | 1–2 часа | P1 |

## Пошаговый план

### Шаг 1. Передекомпиляция (рекомендуется)
Вместо CFR использовать Fernflower или Jadx:
```bash
# Получить Fernflower (входит в IntelliJ IDEA или скачать отдельно)
java -jar fernflower.jar fully_method_renamed.jar decompiled_ff/

# Или Jadx
jadx -d decompiled_jadx fully_method_renamed.jar
```

Оба декомпилятора лучше справляются с переименованным байткодом и создают меньше дубликатов полей.

### Шаг 2. Автоматическая чистка
```bash
python3 obf_deobf/fix_decompilation.py decompiled_ff/
```

Исправит:
- `}/* catch (Exception) */ {}` → `} catch (Exception) {}`
- `cfr_ignored_*` переменные
- `var_do` → `stateCounter`
- Метки `// 2 sources`, `lbl18:`

### Шаг 3. Устранение дубликатов полей
Автоматически (не полностью):
```bash
python3 obf_deobf/rename_duplicate_fields.py decompiled_ff/
```

Переименовывает `int a`, `Graphics a` → `a_int`, `a_Graphics`.

После этого требуется **ручная работа**:
1. Найти все оставшиеся дубликаты: `grep -n '^\s*[a-z]\s\+[a-z]\s*[;=]' GameCanvas.java`
2. Заменить на уникальные имена: `graphics`, `image`, `intArray` и т.п.
3. Обновить все использования в коде.

### Шаг 4. Исправление методов с `Unable to fully structure code`
Варианты:
1. **Оставить заглушками** — если методы неcritical для компиляции:
   ```java
   public void someMethod() {
       // TODO: restore from bytecode
   }
   ```
2. **Восстановить через ASM** — изучать байткод метод и переписать тело. Требует навыков работы с ASM.

### Шаг 5. Сборка
```bash
javac -source 1.6 -target 1.6 \
  -bootclasspath microemulator-2.0.4/lib/cldcapi10.jar:microemulator-2.0.4/lib/midpapi20.jar:stub \
  -d build/classes decompiled_ff/*.java

jar cvf build/package/ZombieInfection.jar -C build/classes .
```

### Шаг 6. Тестирование
```bash
java -jar kemnnx64/KEmulator.jar
# File → Open JAD/JAR → ZombieInfection.jar
```

## Если нужно запустить СЕЙЧАС (без исходников)

```bash
java -jar build/package/ZombieInfection.jar
```

Готовый JAR уже работает. Основной остаток — читаемость кода.

## Дополнительные задачи

| Задача | Файлы | Статус |
|--------|-------|--------|
| Декодировать спрайты | `m10_chunk02.sprite`, `m8_chunk01.sprite` | 🔴 Не начато |
| Применить палитры | `palettesAmount.bin` → тайлы | 🔴 Не начато |
| Парсить уровни | `m2_*.archive`, `m9_*.level` | 🔴 Не начато |

## Файлы-помощники

| Файл | Назначение |
|------|-----------|
| `BUILD_GUIDE.md` | Пошаговая инструкция по сборке |
| `WHAT_IS_NEXT.md` | Этот документ |
| `obf_deobf/fix_decompilation.py` | Чистка CFR-артефактов |
| `obf_deobf/rename_duplicate_fields.py` | Переименование дубликатов полей |
| `obf_deobf/build_tileset.py` | Сборка tileset_sheet.png |
| `obf_deobf/decode_graphic.py` | Декодер тайлов/спрайтов |
| `obf_deobf/debug_sprite.py` | Отладка формата спрайтов |

---

**Итог:** игра запускается. Полное восстановление исходников требует 6–10 часов ручной работы по устранению дубликатов полей и восстановлению тел методов. Если нужна читаемость кода — начинать с передекомпиляции Fernflower/Jadx.
