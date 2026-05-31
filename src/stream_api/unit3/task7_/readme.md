⚙️ Модуль №7 — Незмінні колектори

📘 Мета:
практикувати створення незмінних колекцій;
зрозуміти різницю між List.of() і Collectors.toUnmodifiableList();
перевірити поведінку при спробі змінити елементи.

✅ Завдання 1 — Створення незмінного списку

📋 Умова:
Зроби список через toUnmodifiableList().

List<String> names = Stream.of("Bob", "Alice", "Tom")
.collect(Collectors.toUnmodifiableList());

System.out.println(names); // [Bob, Alice, Tom]
names.add("Jack"); // 💥 UnsupportedOperationException


🧠 Колекція створена, але змінювати її не можна.

✅ Завдання 2 — Незмінна множина

    public void practice2 () {
        Set <Integer> set = Stream.of(1,2,3,2)
                .collect(Collectors.toUnmodifiableSet());
        System.out.println(set);
    }

// [1, 2, 3]
set.remove(1); // 💥 UnsupportedOperationException

🧠 toUnmodifiableSet() автоматично унікалізує дані, але не дозволяє їх змінювати.

✅ Завдання 3 — Незмінна мапа

    public void practice3 () {
        Map<String, Integer> map = Stream.of("A", "B", "C")
                .collect(Collectors.toUnmodifiableMap(
                        s->s,
                        String::length
                ));
    }

System.out.println(map); // {A=1, B=1, C=1}
map.put("D", 2); // 💥 UnsupportedOperationException

✅ Завдання 4 — Мапа з колізіями (помилка)

🎯 Мета: переконатися, що без mergeFunction toUnmodifiableMap падає.

# що таке mergeFunction ?? чому падає

    public void ptactice4 () {
        List<String> list = List.of("apple", "apricot");
        Map <Character, String> map = list.stream()
                .collect(Collectors.toUnmodifiableMap(
                        s-> s.charAt(0),
                        s->s
                ));
        System.out.println(map);
    }

💥 IllegalStateException: duplicate key 'a'
🧠 Ті ж правила, що й у toMap() — колізії не прощаються.

✅ Завдання 5 — Використання mergeFunction

Map<Character, String> map = Stream.of("apple", "apricot")
.collect(Collectors.toUnmodifiableMap(
s -> s.charAt(0),
s -> s,
(a, b) -> a + "," + b
));

System.out.println(map); // {a=apple,apricot}

✅ Завдання 6 — Незмінна мапа з колекціями

📋 Умова:
Зроби незмінну мапу, де значення — список слів на ту саму літеру.

    public void practice6() {
        Map<Character, List<String>> map = Stream.of("cat", "cow", "dog")
                .collect(Collectors.toUnmodifiableMap(
                        w -> w.charAt(0),
                        w -> List.of(w),
                        (l1, l2) -> Stream.concat(l1.stream(), l2.stream()).toList()));
        System.out.println(map);
    }

System.out.println(map); // {c=[cat, cow], d=[dog]}
map.get('c').add("cup"); // 💥 UnsupportedOperationException


🧠 Не лише мапа, а й внутрішні списки — незмінні.

✅ Завдання 7 — Порівняння з toList()

🎯 Мета: побачити різницю між колекторами.

    public void practice7 () {
        var modifiable = Stream.of("A", "B").collect(Collectors.toList());
        var unmodifiable = Stream.of("A", "B").collect(Collectors.toUnmodifiableList());
    }

modifiable.add("C"); // ✅ можна
unmodifiable.add("C"); // 💥 не можна


🧠 toList() після Java 16 теж дає незмінну колекцію, але не гарантує цього в Java 8–11.

✅ Завдання 8 — Захист від зовнішніх змін

📋 Умова:
Переконайся, що зміни вихідного списку не впливають на результат.

List<String> source = new ArrayList<>(List.of("A", "B", "C"));
List<String> result = source.stream().collect(Collectors.toUnmodifiableList());

source.add("D");
System.out.println(result); // [A, B, C]


🧠 Потік робить копію — результат незалежний.

✅ Завдання 9 — Незмінна мапа з різними типами

📋 Умова:
Зроби мапу з назви → довжина → незмінну.

    public void practice9 () {
        Map<String, Integer> map = Stream.of("Mars", "Venus", "Earth")
                .collect(Collectors.toUnmodifiableMap(
                        s->s.toUpperCase(),
                        String::length
                ));
        System.out.println();
    }

System.out.println(map); // {MARS=4, VENUS=5, EARTH=5}
map.replace("MARS", 10); // 💥 Exception

✅ Завдання 10 — Комбіноване: захист конфігурації

# шо значить unmofofiable? не можна змінювати кількість, 
# порядок, значення які є посиланням? не додавати і не видаляти?
📋 Умова:
Зроби "config map" із налаштувань, яку не можна змінювати.

Map<String, String> config = Stream.of("host=localhost", "port=8080", "mode=dev")
.map(s -> s.split("="))
.collect(Collectors.toUnmodifiableMap(
arr -> arr[0],
arr -> arr[1]
));

System.out.println(config); // {host=localhost, port=8080, mode=dev}
config.put("port", "9090"); // 💥 Exception


🧠 Дуже реалістичний кейс — для Spring, конфігів, сервісів тощо.

💡 Підсумок
Метод	Тип колекції	Можна змінювати?	Особливість
toList()	Залежить від версії JDK	⛔ / ✅	До Java 16 — змінна
toUnmodifiableList()	Незмінна	❌	Копія, гарантія
toUnmodifiableSet()	Незмінна унікальна	❌	Автоматично без дублікатів
toUnmodifiableMap()	Незмінна	❌	Падає при колізії, без mergeFunction