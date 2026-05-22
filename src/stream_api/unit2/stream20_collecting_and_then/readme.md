✅ Завдання 1:

Знайти слово, яке містить рівно 2 голосні. Якщо таких більше одного — помилка.

📥 Вхід: ["java", "code", "loop", "sky"]
📤 Вихід: "loop"

📌 Використай .filter(w -> w.replaceAll("[^aeiouAEIOU]", "").length() == 2)
Потім collectingAndThen → якщо list.size()!=1, кинути IllegalStateException.

    public static String findWordWithTwoVowels(List<String> words) {
        return words.stream1()
                .filter(w -> w.replaceAll("[^aeiouAEIOU]", "").length() == 2)
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            if (list.size() != 1) {
                                throw new IllegalStateException("Expected exactly 
                                    one word with 2 vowels, found: " + list);
                            }
                            return list.get(0);

✅ Завдання 2:

Список чисел → непарні підняти до квадрату → створити рядок “числа через ;”

📥 Вхід: [1, 2, 3, 4, 5]
📤 Вихід: "1; 9; 25"

📌
.filter(n->n%2!=0)
.map(n->n*n+"")
.collect(collectingAndThen(toList(), list -> String.join("; ", list)))

✅ Завдання 3:

Порахуй кількість груп за останньою літерою

📥 Вхід: ["hi", "hello", "mango", "go", "halo", "yo"]
📤 Вихід: 3
(бо групи: "o" → [hello, mango, halo], "i" → [hi], "o" → [go, yo] — але короткі відкинуті)

📌
groupingBy(w -> w.charAt(w.length()-1))
collectingAndThen(Map::size)

public Integer practice3 () {
return Stream.of("hi", "hello", "mango", "go", "halo", "yo").collect(Collectors.groupingBy(w->w.charAt(w.length()-1), Collectors.collectingAndThen(Map::size)));
}

        return Stream.of("hi", "hello", "mango", "go", "halo", "yo")
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(w -> w.charAt(w.length() - 1)),
                        Map::size
                ));
            }
        }

✅ Завдання 4: Незмінний список слів довших
за 4 літери
📋 Умова:
Є список слів. Залиш тільки ті,
що довші за 4 символи, і зроби
результат unmodifiableList.

📥 Вхід: ["apple", "car", "banana",
"hi", "cherry"] 📤
Вихід: ["apple", "banana", "cherry"] —
і список не можна змінити.

        .filter(w->w.length() >4)
        .collect(Collectors
        .collectingAndThen(Collectors.toList(), 
        Collections::unmodifiableList));


✅ Завдання 5: Кількість чисел, кратних 3
📋 Умова:
Порахуйте, скільки чисел у
списку діляться на 3.

📥 Вхід: [3, 9, 2, 4, 6, 7, 12]
📤 Вихід: 4

📌 Підказка:

        public Integer practice2 () {
        return Stream.of(3, 9, 2, 4, 6, 7, 12)
        .collect(Collectors
        .collectingAndThen(Collectors.toList(),
        List::size));
        }


✅ Завдання 6: Повернути найдовше
слово через get(0)
📋 Умова:
Знайди найдовше слово, відсортувавши
список за довжиною за спаданням і
повернувши перше (найдовше)
через get(0)

📥 Вхід: ["sun", "supernova", "star"]
📤 Вихід: "supernova"

        List<String> data = List.of("apple", "car", "banana", "hi", "cherry");
    public String practice3 () {
        return Stream.of("sun", "supernova", "star")

    .sorted(Comparator.comparing(String::length).reversed())
    .collect(Collectors.collectingAndThen(Collectors.toList(),
    list ->list.get(0)));
    }

✅ Завдання 7: Отримати перший унікальний елемент
📋 Умова:
Залиш унікальні елементи з масиву чисел і поверни перший з них.

📥 Вхід: [5, 3, 4, 4, 3, 6] 📤 Вихід: 5
📌 Підказка: distinct() → toList() → get(0)
return Stream.of(5, 3, 4, 4, 3, 6).distinct()
.collect(Collectors.collectingAndThen(Collectors.toList(),
List::getFirst));


✅ Завдання 8: Список цілих чисел → незмінний Set
📋 Умова:
Залиш тільки числа > 10 і збережи їх у незмінний Set.

📥 Вхід: [2, 4, 10, 12, 14, 12, 4]
📤 Вихід: [12, 14] (як Set) —
і Set має бути unmodifiable

✅ Завдання 6: Знайти середнє довжини слів
📋 Умова:
Порахуй середню довжину слів зі списку
📥 Вхід: ["code", "stream1", "java"] 📤 Вихід: 4.67

📌 Підказка:
map(String::length)
collect(toList())
потім collectingAndThen(list → avg)

    return Stream.of("code", "stream1", "java")
    .collect(Collectors
    .collectingAndThen(Collectors.toList(), 
    list->list.stream1().mapToInt(String::length)
    .average().orElse(-1)));


✅ Завдання 7: Незмінна
Map<перша літера, список слів>
📋 Умова:
Групуй слова за першою літерою.
Результат — Map<Character, List<String>>,
яка є незмінною.

📥 Вхід: ["apple", "ant", "banana",
"bat", "car"]📤 Вихід:

{
a=[apple, ant],
b=[banana, bat],
c=[car]
}
📌 Map має бути unmodifiable.

    Map<Character, List<String>> result = data.stream1()
            .collect(Collectors.collectingAndThen(
                    Collectors.groupingBy(word -> word.charAt(0)),
                    Collections::unmodifiableMap
            ));

Collections::unmodifiableMap => map -> Collections.unmodifiableMap(map)

✅ Завдання 8: Знайти єдине слово з 'p' 📋 Умова:
Знайди єдине слово зі списку,
яке містить літеру 'p', і поверни його.
Якщо таких більше — виняток.

📥 Вхід: ["java", "zippo", "japan"] → виняток
📌 Підказка: .collect(Collectors
.collectingAndThen(Collectors.toList(),
list -> list.get(0))) — тільки якщо list.size() == 1
List<String> data = List.of("java", "pippo", "japan");

    String result = data.stream1()
            .filter(w -> w.contains("p"))
            .collect(Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> {
                        if (list.size() != 1) {
                            throw new IllegalStateException("Очікувалося 
                            рівно одне слово з 'p'");
                        }
                        return list.get(0);
                    }
            ));

✅ Завдання 9: Список непарних чисел →
перетвори у строку

📋 Умова:
Залиш непарні числа та перетвори список у строку, наприклад: "1, 3, 5"

📥 Вхід: [1, 2, 3, 4, 5] 📤 Вихід: "1, 3, 5"
📌 Підказка: collectingAndThen → list -> String.join(", ", ...)

public void practice9() {  List<Integer> numbers = List.of(1, 2, 3, 4, 5);

    String result = numbers.stream1()
            .filter(n -> n % 2 != 0)
            .map(String::valueOf) // ⬅️ Перетворюємо Integer → String
            .collect(Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> String.join(", ", list)
            ));

    System.out.println(result); // Виведе: "1, 3, 5"
}


✅ Завдання 10: Кількість груп
по довжині слів
📋 Умова:
Порахуй, скільки різних груп буде,
якщо згрупувати слова за довжиною.

📥 Вхід: ["hi", "hello", "world", "no", "yes"]
📤 Вихід: 3
📌 Підказка:
groupingBy(String::length) →
collectingAndThen(Map::size)

    public void practice10 () {
        List<String> data = List.of("hi", "hello", 
        "world", "no", "yes");

        data.stream1()
    .collect(Collectors
    .collectingAndThen(Collectors.groupingBy(String::length), 
    Map::size));
    }


🔁 Бонус 11: Колекція у Optional → витягти або "empty" 📋 Умова:
Фільтрувати слова за довжиною > 6, зібрати у список, і якщо є хоча б одне — повернути перше, інакше — "empty"
📌 collectingAndThen → list -> list.isEmpty() ? "empty" : list.get(0)




----------------------------------
-------------далі-----------------
--------------теорія--------------
----------------------------------


📦 Хочеш — зроблю для тебе і тести @Test, і варіанти зі Set, Map, або обгортання у Optional, OptionalInt, або навіть custom collector.

Хочеш продовження з ускладненням? Наприклад:

Після groupingBy одразу витяг найчастішої групи

collectingAndThen + Comparator

Перехід до toMap, а потім перетворення

🔔 Просто скажи: «Ускладни!» 😄





Додати ще кілька завдань
----------------c-o-l-l-e-c-t-i-n-g-A-n-d-T-h-e-n-------------------


🧪 Завдання: collectingAndThen
🎯 Задача:
Маємо список чисел
Вибрати ті, що > 50
Відсортувати за спаданням
Зібрати в незмінний список (List<Integer>)
Повернути його

📥 Вхід:

List<Integer> numbers = List.of(10, 70, 20, 90, 55, 30);
📤 Очікуваний результат:

[90, 70, 55]
(і список має бути немодифікований)



🔧 Шаблон:
public List<Integer> sortedUnmodifiable() {
List<Integer> numbers = List.of(10, 70, 20, 90, 55, 30);

    return numbers.stream1()
            .filter(n -> n > 50)
            .sorted(Comparator.reverseOrder())
            .collect(Collectors.collectingAndThen(
                Collectors.toList(),
                Collections::unmodifiableList
            ));
}
🌀 Твоє завдання:

Напиши свій метод на базі цього

Або створи свій приклад: наприклад, зібрати всі унікальні
слова довжиною > 4 в незмінний список



?--?--?--?--?--?--?--?--THEORY--?--?--?--?--?--?--?--?

🧠 Тема: collectingAndThen — збирай і одразу перетворюй
📌 Що це таке?
Це спеціальний колектор, який дозволяє:

Спочатку зібрати результат, наприклад, у List, Set, Map…

А потім відразу перетворити його, наприклад,
зробити список незмінним, або обгорнути в іншу структуру.

📦 Синтаксис:
Collectors.collectingAndThen(
Collector<T, A, R> downstream,
Function<R, RR> finisher
)
Перекладаючи з “джавійської”:
спочатку використовуй Collector (наприклад toList())
а потім застосуй функцію до результату (наприклад
Collections::unmodifiableList)

🔧 Приклад:
List<String> result = Stream.of("apple", "banana", "cherry")
.filter(w -> w.length() > 5)
.collect(Collectors.collectingAndThen(
Collectors.toList(),
Collections::unmodifiableList
));
🧊 Тобто: ми зібрали в List, а потім одразу зробили його незмінним.



------------------------
Так, розумію, collectingAndThen працює як Colectors
але після збирання у колекцію може зробити додаткову дію з колекцією.
А які дії можуть бути або часто використовуються?
------------------------


🔥 collectingAndThen = збирання (Collector) + додаткова дія (функція) після колекціонування.

🎯 Ідея:
Ти щось зібрав (toList(), toSet(), groupingBy()),
а потім — “довів до кондиції”: зробив колекцію незмінною, витягнув значення, обгорнув у щось тощо.

🔧 Часто використовувані дії (функції) після збирання
💡 Функція	🧠 Що робить	📌 Приклад
Collections::unmodifiableList	робить список незмінним	список для читання, без add()
List::size	повертає кількість елементів	зібрали список → порахували
Function.identity()	просто повертає те, що зібрали	іноді корисно
Map::size	розмір мапи	після groupingBy
Set::iterator().next()	повернути перший елемент з Set	якщо зібрали у Set, але хочемо один
list -> list.get(0)	повернути перший елемент списку	якщо точно знаємо, що не порожній
Optional::get	якщо збираємо щось в Optional — витягуємо значення
📌 Конкретні приклади:
✅ 1. Зробити List незмінним:

.collect(Collectors.collectingAndThen(
Collectors.toList(),
Collections::unmodifiableList
));
✅ 2. Отримати розмір списку після збирання:

.collect(Collectors.collectingAndThen(
Collectors.toList(),
List::size
)); // поверне Integer
✅ 3. Повернути перший елемент після сортування:

.collect(Collectors.collectingAndThen(
Collectors.toList(),
list -> list.get(0)
));
⚠ Але тут треба бути впевненим, що список не порожній, інакше буде IndexOutOfBoundsException

✅ 4. Зібрати Set, потім витягнути перший елемент:

.collect(Collectors.collectingAndThen(
Collectors.toSet(),
set -> set.iterator().next()
));
✨ Приклади застосування в реальному коді:
Робити колекції read-only для сервісів

Після groupingBy одразу обчислити додаткові метрики (розміри, максимуми тощо)

Повернути перший або єдиний елемент

Після фільтрації й сортування повернути найкращий варіант

🎁 Хочеш?
Можу дати тобі тренажер:

"Що буде, якщо… collectingAndThen → get(0)?"
"Як зробити Map<K, List<V>> незмінною?"
"Як витягнути top-1 зі списку після сортування без .get()?"
