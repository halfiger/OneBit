
## 🔍 Пояснення
Повна формула:
double avg = list.stream()
.collect(Collector.of(
() -> new int[2],          // створюємо контейнер
(a, n) -> { a[0]+=n; a[1]++; },   // додаємо елемент
(a1, a2) -> { a1[0]+=a2[0]; a1[1]+=a2[1]; return a1; }, // об’єднуємо результати
a -> (double)a[0]/a[1]     // повертаємо середнє
));

Результат буде:
(5 + 10 + 15) / 3 = 10.0

Ми створюємо свій власний Collector, який:
складає суму всіх елементів
рахує кількість елементів
у кінці повертає суму / кількість
І робиться це вручну через 4 компоненти Collector’а.

🧩 Колектор складається з:
1) Постачальник (supplier)
   () -> new int[2]
Це стартовий контейнер, у якому будемо зберігати проміжні результати.
Масив із 2 чисел:
a[0] → сума
a[1] → кількість елементів

2) Акумулятор (accumulator)
   (a, n) -> { a[0] += n; a[1]++; }
Для кожного числа n у стрімі він робить:
додає n до суми
збільшує лічильник

По факту:
після 5 → [5,1]
після 10 → [15,2]
після 15 → [30,3]

3) Комбайнер (combiner)
   (a1, a2) -> { a1[0]+=a2[0]; a1[1]+=a2[1]; return a1; }
Це використовується у паралельному стрімі, 
коли потоки ділять роботу на частини.
Тут ми з'єднуємо два часткові результати:
сумуємо суми
сумуємо кількості

4) Фінішер (finisher)
   a -> (double)a[0] / a[1]
Після завершення роботи стріму:
беремо суму
ділимо на count
повертаємо double

⚗️ Модуль: Collector.of() — глибока практика

📘 Мета:
зрозуміти, як створюється власний колектор, які параметри він приймає,
і навчитися створювати свої “міні-Collectors”, 
коли стандартних (toList(), joining(), counting()) вже не вистачає.

# ✅ Завдання 1 — Базова структура Collector.of()

🎯 Мета: засвоїти форму 5 параметрів:

Collector.of(supplier, accumulator, combiner, finisher)


📋 Умова:
Створи Collector, який збирає всі елементи у StringBuilder.

List<String> list = List.of("Java", "Stream", "API");

String result = list.stream()
.collect(Collector.of(
StringBuilder::new,           // supplier
(sb, s) -> sb.append(s).append(" "), // accumulator
StringBuilder::append,        // combiner
StringBuilder::toString       // finisher
));

📤 Очікуване: "Java Stream API "

# ✅ Завдання 2 — Колектор для суми

🎯 Мета: замінити .mapToInt().sum() власним колектором.

📋 Умова:
Обчисли суму чисел із списку через Collector.of().

List<Integer> nums = List.of(1, 2, 3, 4);

int sum = nums.stream().collect(Collector.of(
() -> new int[1],
(a, n) -> a[0] += n,
(a1, a2) -> { a1[0] += a2[0]; return a1; },
a -> a[0]
));

📤 Очікуване: 10


# ✅ Завдання 3 — Колектор для середнього значення

🎯 Мета: тренування accumulator + finisher.

📋 Умова:
Обчисли середнє значення списку чисел.

List<Integer> list = List.of(5, 10, 15);

double avg = list.stream().collect(Collector.of(
() -> new int[2],                     // [sum, count]
(a, n) -> { a[0] += n; a[1]++; },
(a1, a2) -> { a1[0]+=a2[0]; a1[1]+=a2[1]; return a1; },
a -> (double)a[0]/a[1]
));


📤 Очікуване: 10.0

# ✅ Завдання 4 — Колектор для злиття унікальних слів

🎯 Мета: практика з Set і combiner.

📋 Умова:
Об’єднай усі елементи у Set без дублікатів.

List<String> words = List.of("a", "b", "a", "c");

Set<String> set = words.stream().collect(Collector.of(
HashSet::new,
Set::add,
(s1, s2) -> { s1.addAll(s2); return s1; }));


📤 Очікуване: [a, b, c]

# ✅ Завдання 5 — Collector з фільтрацією всередині
🎯 Мета: усвідомити, що логіку фільтра можна вбудовувати
прямо в collector.

📋 Умова:
Збирай лише парні числа у список.

List<Integer> nums = List.of(1, 2, 3, 4, 5);

List<Integer> evens = nums.stream().collect(Collector.of(
ArrayList::new,
(list, n) -> { if (n % 2 == 0) list.add(n); },
(l1, l2) -> { l1.addAll(l2); return l1; }
));


📤 Очікуване: [2, 4]

# ✅ Завдання 6 — Collector для створення Map

🎯 Мета: навчитися створювати складні структури через accumulator.

📋 Умова:
Зроби колектор, який будує Map<перша_літера, довжина_рядка>.

List<String> words = List.of("apple", "banana", "pear");

Map<Character, Integer> map = words.stream().collect(Collector.of(
HashMap::new,
(m, s) -> m.put(s.charAt(0), s.length()),
(m1, m2) -> { m1.putAll(m2); return m1; }
));


📤 Очікуване: {a=5, b=6, p=4}

# ✅ Завдання 7 — Collector з post-processing (finisher)

🎯 Мета: навчитися перетворювати результат після збору.

📋 Умова:
Збирай усі слова у список, а потім поверни їх у верхньому регістрі.

List<String> list = List.of("a", "b", "c");

List<String> upper = list.stream().collect(Collector.of(
ArrayList::new,
List::add,
(a, b) -> { a.addAll(b); return a; },
l -> l.stream().map(w -> ((String) w).toUpperCase()).toList()
));



📤 Очікуване: [A, B, C]

# ✅ Завдання 8 — Collector для форматування рядка

🎯 Мета: потренувати створення власного аналога joining(", ").
📋 Умова:
Об’єднай рядки через кому, без пробілу після останнього.

List<String> names = List.of("Mark", "Luke", "John");

String joined = names.stream().collect(Collector.of(
StringBuilder::new,
(sb, s) -> { if (sb.length() > 0) sb.append(", "); sb.append(s); },
(a, b) -> { if (a.length() > 0 && b.length() > 0) a.append(", "); a.append(b); return a; },
StringBuilder::toString
));


📤 Очікуване: "Mark, Luke, John"

# ✅ Завдання 9 — Collector із комбінаторикою даних

🎯 Мета: створити Collector, який рахує і суму, і середнє одночасно.
        
📋 Умова:
Поверни Map.of("sum", ..., "avg", ...)

List<Integer> nums = List.of(10, 20, 30);

Map<String, Double> result = nums.stream().collect(Collector.of(
() -> new double[2],
(a, n) -> { a[0] += n; a[1]++; },
(a1, a2) -> { a1[0]+=a2[0]; a1[1]+=a2[1]; return a1; },
a -> Map.of("sum", a[0], "avg", a[0]/a[1])
));


📤 Очікуване: {sum=60.0, avg=20.0}

# ✅ Завдання 10 — Collector для аналізу символів у рядку

🎯 Мета: поєднати все — mapToObj + власний collector + finisher.

📋 Умова:
Зроби Collector, який підраховує кількість голосних у рядку.

String text = "banana";

long vowels = text.chars()
.mapToObj(c -> (char)c)
.collect(Collector.of(
() -> new long[1],
(a, ch) -> { if ("aeiou".indexOf(ch) >= 0) a[0]++; },
(a1, a2) -> { a1[0]+=a2[0]; return a1; },
a -> a[0]
));


📤 Очікуване: 3

## 💡 Порада для глибшого розуміння:

.of() — це фактично “ручне створення” будь-якого Collector-а.

Кожен колектор має 3 фази: накопичення, об’єднання, фінішування.

Ви можете створити свій “mini-joining”, “mini-summarizing” 
або навіть Collector.toImmutableMap().

## Це чудовий приклад кастомного Collector, який склеює строки 
в один рядок.

Ось код для зручності:

public void practice1 () {
List<String> list = List.of("Java", "Stream", "API");
String result = list.stream().collect(Collector.of(
StringBuilder::new,
(sb, s) -> sb.append(s).append(" "),
StringBuilder::append,
StringBuilder::toString
));
}

🔍 Що тут відбувається крок за кроком
Ми створюємо свій Collector, який:
накопичує результат у StringBuilder
додає по одному слову
у кінці перетворює StringBuilder у String
Тобто це варіант свого власного Collectors.joining(" ").

🧩 Компоненти колектора
1) Supplier — створює контейнер
   StringBuilder::new
Це функція, яка створює новий StringBuilder.
Для послідовного стріму буде створений один такий StringBuilder.
Для паралельного — один у кожному підпотоці.

2) Accumulator — додає новий елемент
   (sb, s) -> sb.append(s).append(" ")
Беремо:
sb — StringBuilder
s — наступний елемент стріму (рядок)
І робимо:
sb.append(s).append(" ")
Результат після кожного кроку:

Крок	Дія	StringBuilder
1	додаємо "Java"	"Java "
2	додаємо "Stream"	"Java Stream "
3	додаємо "API"	"Java Stream API "

3) Combiner — об’єднує результати паралельних потоків
   StringBuilder::append
Якщо стрім паралельний, буде викликано:
sb1.append(sb2)
що з’єднає два часткові StringBuilder-и.
Для звичайного (послідовного) стріму combiner не викликається.

4) Finisher — перетворює результат у String
   StringBuilder::toString
Після завершення збирання отримуємо звичайний String.
🎯 Результат роботи
result буде:
"Java Stream API "
Зверни увагу: в кінці є пробіл, бо accumulator додає " " після кожного слова.
Якщо хочеш без пробілу в кінці — є два шляхи:

✔ Варіант 1: акуратно додавати пробіли тільки між словами
(sb, s) -> {
if (sb.length() > 0) sb.append(" ");
sb.append(s);
}

✔ Варіант 2: фінішер підчищає останній пробіл
a -> {
if (a.length() > 0) a.setLength(a.length() - 1);
return a.toString();
}

✔ Варіант 3: просто використовувати вбудований колектор
String result = list.stream().collect(Collectors.joining(" "));


Найчистіше і найшвидше рішення.

🧠 Чому цей приклад корисний?
Бо це саме той випадок, коли ти чітко бачиш усі чотири 
частини колектора:
створення контейнера
додавання елемента
об’єднання (важливо в паралельних стрімах)
фіналізація результату

## ✅ 1. Однопоточний (послідовний) режим
Це стандартна поведінка.

list.stream()       // ← послідовний стрім
.collect(...);

У цьому режимі:
створюється один контейнер (StringBuilder або int[2])
accumulator викликається 1 раз для кожного елемента
combiner НЕ викликається
працює в одному потоці

## ✅ 2. Паралельний режим

Увімкнути паралельний стрім ще простіше:
list.parallelStream()     // ← паралельний стрім
.collect(...);

або так:
list.stream()
.parallel()           // ← перетворення на паралельний
.collect(...);

У цьому режимі:
JVM ділить дані на шматки
кожен шматок обробляється в окремому потоці
для кожного потоку створюється окремий контейнер
accumulator працює у кожному потоці незалежно
combiner викликається, щоб з’єднати часткові результати
фінішер викликається тільки 1 раз для результату