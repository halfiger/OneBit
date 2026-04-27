Блок 1 — Основи Stream (10 задач)

Мета: закріпити map/filter/sorted/collect, chars(),
перетворення String ↔ digits.

## 1.1 — Сортувати слова за довжиною (>3)

Умова: Взяти список і повернути слова довші за 3,
відсортовані за довжиною.
Підказка: .filter(...).sorted(Comparator.comparing(String::length))

Рішення:

    public List<String> practice1() {
        List<String> list = List.of("A", "B", "C");
        return list.stream().filter(w -> w.length() > 3)
                .sorted(Comparator.comparing(String::length))
                .toList();
    }

Коментар: базове фільтрування → сортування;
використовуй method reference для читабельності.

## 1.2 — Алфавітно (toLowerCase)

Умова: Привести слова до lower-case
і відсортувати алфавітно.
Підказка: .map(String::toLowerCase).sorted()
Рішення:

    public List <String> practice2() {
        List<String> list = List.of("A", "B", "C");
        return list.stream()
                .map(String::toLowerCase)
                .sorted()
                .toList();
    }

Коментар: map змінює елементи; сортування працює з рядками.

## 1.3 — Найдовше слово (через Map.entry)

Умова: Знайти слово з максимальною довжиною.
Підказка: .map(w->Map.entry(w,w.length())).max(...)
Рішення:

    public String practice3 () {
        List<String> list = List.of("Agava", "Bob", "Cola");
        return list.stream().map(w-> Map.entry(w, w.length()))
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("");
    }

Коментар: корисний шаблон — пара (ключ,значення) для max/min за значенням.

1.4 — Сума квадратів цифр числа

Умова: Для n = 234 повернути 4+9+16 = 29.
Підказка: String.valueOf(n).chars() → c - '0'
Рішення:

    public Integer practice4 (Integer n) {
        return String.valueOf(Math.abs(n))
                .chars()
                .map(c->c-'0')
                .map(d->d*d)
                .sum();
    }

Коментар: chars дає IntStream; перетворення від символу до цифри — c - '0'.

1.5 — Витягти унікальні цифри з рядка (sorted unique)

Умова: "a1b2c3x9z7" → [1,2,3,7,9] (відсортовано).
Підказка: .filter(Character::isDigit)
.mapToObj(Character::getNumericValue)
Рішення:

    public List<Integer> practice5 (String s) {
        return s.chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .distinct()
                .sorted()
                .boxed()
                .toList();
    }

Коментар: distinct + sorted — отримуємо унікальні цифри в порядку.

1.6 — Перевернути цифри числа навпаки

Умова: 1234 → 4321 (з урахуванням знаків).
Підказка: StringBuilder(...).reverse()
Рішення:

    public Integer practice6 (int n) {
        return Integer.parseInt(
                new StringBuilder(String
                        .valueOf(Math.abs(n)))
                        .reverse()
                        .toString());
    }

Коментар: простий спосіб, але обережно з переповненням для дуже великих чисел.

1.7 — Залишити тільки парні цифри

Умова: 123456 → 246 (як число).
Підказка: .filter(d -> d % 2 == 0)
Рішення:

    public Integer practice7 (int n) {
        return Integer.parseInt(String.valueOf(Math.abs(n))
                .chars()
                .filter(a->a%2==0)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining()));
    }

Коментар: повертаємо -1, якщо немає парних цифр — це обрана поведінка.

1.8 — Помнож кожну цифру на її позицію

Умова: 234 → "2 6 12" → з'єднати в "2612".
Підказка: IntStream.range(0, s.length())
Рішення:

    public int practice8(int n) {
        String s = String.valueOf(Math.abs(n));
        String result = IntStream.range(0, s.length())
                .mapToObj(i ->
                        String.valueOf((s.charAt(i) - '0') * (i + 1)))
                .collect(Collectors.joining());
        return n < 0 ? -Integer.parseInt(result) : Integer.parseInt(result);
    }

Коментар: позиції зліва направо з 1; збираємо як рядок.

1.9 — Кількість цифр більше 5

Умова: 987654321 → 4 (9,8,7,6).
Підказка: .filter(d -> d > 5).count()
Рішення:

    public long practice (int n) {
        return String.valueOf(Math.abs(n))
                .chars()
                .map(c->c-'0')
                .filter(d->d>5)
                .count();
    }

Коментар: простий приклад лічильника через stream.

1.10 — Замінити кожну цифру на знак (>5 -> X, інакше O)

Умова: 67894 → "XXOXO".
Підказка: тернарний оператор у mapToObj.
Рішення:

    public String practice10 (int n) {
        return String.valueOf(Math.abs(n)).chars()
                .map(c->c-'0')
                .mapToObj(d->d > 5 ? "X" : "0")
                .collect(Collectors.joining());
    }