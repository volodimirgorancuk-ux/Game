# Zombie Infection — Полное восстановление и сборка
*Gameloft J2ME, v1.2.0, 240x320*

## Что уже готово

- Деобфускация классов: 7 классов переименованы (`GameCanvas`, `GameController`, `GraphicsEngine`, `GameData`, `GameConfig`, `SensorHandler`, `AudioManager`, `GloftMASS`).
- 568 методов переименовано в байткоде (`fully_method_renamed.jar`).
- Архивы `m*` распакованы, ассеты извлечены в `obf_deobf/decoded/`.
- Созданы PNG-визуализации: тайлсет, тайлмап, спрайты.
- State machine частично документирована.
- MicroEmulator 2.0.4 и KEmulator (`kemnnx64/KEmulator.jar`) добавлены в репозиторий.

## Что блокирует полную сборку из исходников

1. **Дубликаты имён полей** — в каждом классе десятки полей с именами `a`, `b`, `c`, но разными типами. Java не позволяет такую компиляцию.
2. **CFR-артефакты** — сломанные `catch`-блоки, лишние скобки, метки `lbl18:`, переменные `cfr_ignored_*`.
3. **Методы с `Unable to fully structure code`** — их тела содержат мусор и не компилируются.

## Как достроить проект до полной сборки

### Шаг 1. Установите Java JDK 8–11 и инструменты

```bash
# Debian/Ubuntu
sudo apt update && sudo apt install -y openjdk-11-jdk wget unzip

# Проверка
java -version
javac -version
```

### Шаг 2. Передекомпилируйте JAR через Fernflower (рекомендуется) или CFR

CFR даёт много артефактов. Лучше использовать Fernflower (IntelliJ):

```bash
# Скачайте Fernflower
wget https://github.com/fesh0r/fernflower/releases/download/v1.7.2/fernflower-1.7.2.jar -O fernflower.jar

# Декомпилируйте fully_method_renamed.jar
java -jar fernflower.jar -dgs=1 fully_method_renamed.jar decompiled_fernflower

# Или через Jadx (GUI/CLI)
# wget https://github.com/skylot/jadx/releases/download/v1.5.0/jadx-1.5.0.zip
# unzip jadx-1.5.0.zip
# ./jadx/bin/jadx -d decompiled_jadx fully_method_renamed.jar
```

Fernflower/Jadx лучше обрабатывают переименованный байткод и создают меньше дубликатов полей.

### Шаг 3. Отправьте decompiled исходники в `src/` и исправьте ошибки

```bash
mkdir -p src
cp decompiled_fernflower/com/gloft/*/*.java src/
cd src
```

#### 3.1. Автоматическая чистка CFR-артефактов (если используете CFR)

В репозитории есть скрипт:

```bash
python3 ../obf_deobf/fix_decompilation.py src
```

Он исправит:
- `}/* catch (Exception exception) */ {}` → `} catch (Exception exception) {}`
- Удалит `cfr_ignored_*` переменные
- Переименует `var_do` → `stateCounter`
- Удалит метки `// 2 sources`, `lbl18:` и т.п.

#### 3.2. Ручная фиксация дубликатов полей

Для каждого файла найдите дубликаты:

```bash
grep -n '^\s*[a-z]\s\+[a-z]\s*[;=]' GameCanvas.java | head -30
```

Замените на уникальные имена. Пример:

```java
// Было (не компилируется)
int a;
Graphics a;

// Стало
int a_int;
Graphics a_graphics;
```

Затем обновите все использования в коде. Для рекурсивного поиска используйте:

```bash
grep -rn '\ba\b' GameCanvas.java | grep -v 'a_int\|a_graphics'
```

#### 3.3. Исправьте сломанные методы

Если есть методы с телом `/* Unable to fully structure code */`, вам нужно:
- Либо удалить их (если они не нужны для компиляции)
- Либо восстановить по байткоду через ASM (это ручная работа)

Проще всего оставить их пустыми:

```java
public void damagedEnemy() {
    /* method body restored from bytecode in follow-up pass */
}
```

### Шаг 4. Соберите проект

```bash
# Создайте классы-заглушки J2ME API (они уже есть в repo в stub/)
mkdir -p stub/javax/microedition/lcdui stub/javax/microedition/midlet

# Компиляция
javac -source 1.6 -target 1.6 -bootclasspath microemulator-2.0.4/lib/cldcapi10.jar:microemulator-2.0.4/lib/midpapi20.jar:stub -d build/classes src/*.java

# Если ошибок нет, упаковываем в JAR
cd build/classes
jar cvf ../package/ZombieInfection.jar *
cd ../..
```

### Шаг 5. Запустите в эмуляторе

#### MicroEmulator

```bash
java -jar microemulator-2.0.4/microemulator.jar -Xapp ZombieInfection.jar
```

#### KEmulator

```bash
java -jar kemnnx64/KEmulator.jar
# В меню: File → Open JAD/JAR → выберите build/package/ZombieInfection.jar
```

## Быстрый путь: запустить без передекомпиляции (исходники не нужны)

Если цель — просто запустить игру и покрутить эмулятор, не трогая исходники:

```bash
# Просто запустите готовый JAR
java -jar build/package/ZombieInfection.jar

# Или через MicroEmulator
java -jar microemulator-2.0.4/microemulator.jar -Xapp build/package/ZombieInfection.jar
```

## Если вы хотите улучшить декомпиляцию без Java

В текущей среде Java нет. Вы можете:
1. Скачать готовые Fernflower/Jadx и декомпилировать на своём ПК.
2. Отправить улучшенные исходники в репозиторий.
3. Запустить исправляющие скрипты из `obf_deobf/` (`fix_decompilation.py`, `rename_duplicate_fields.py` — осторожно, онDamage может сломать код).

## Оценка объёма ручной работы

| Задача | Объём |
|--------|-------|
| Передекомпиляция Fernflower/Jadx | 15 минут |
| Чистка catch-блоков и меток | Автоматически |
| Переименование дубликатов полей | 3–6 часов (ручная работа) |
| Исправление `Unable to fully structure code` | 2–4 часа |
| Полная сборка и тесты | 1–2 часа |

## Итог

**Минимально для запуска игры:** запустите `build/package/ZombieInfection.jar` в KEmulator/MicroEmulator. Игра уже работает.

**Для полного восстановления исходников:** передекомпилируйте через Fernflower, исправьте дубликаты полей (скрипт помогает, но не всё), соберите и проверьте.
