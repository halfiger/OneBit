⚙️ Модуль №5 — Stream.concat() / flatMap() злиття потоків

📘 Мета:
зрозуміти, як об’єднувати кілька потоків;
відчути різницю між concat() і flatMap(Stream::of);
навчитися прибирати дублікати, маркувати елементи й комбінувати різні типи.

✅ Завдання 1 — Просте злиття двох потоків

📋 Умова:
Об’єднай два списки і виведи всі елементи.

    public void practice1() {
        Stream<String> a = Stream.of("A", "B", "C");
        Stream<String> b = Stream.of("D", "F");
        Stream.concat(a, b).forEach(System.out::println);
    }

// ABCDE

🧠 concat() — з’єднує два потоки в один.

✅ Завдання 2 — Злиття списків без дублікатів

📋 Умова:
Об’єднай два списки і залиш тільки унікальні елементи.

    public void practice2 () {
        List<String> one = List.of("apple", "banana", "cherry");
        List<String> two = List.of("banana", "date", "apple");
        List <String> list = Stream.concat(one.stream(), two.stream())
                .distinct()
                .toList();
        System.out.println(list);
    }

// [apple, banana, cherry, date]

✅ Завдання 3 — Використання flatMap для злиття

🎯 Мета: поєднати кілька колекцій у потік без Stream.concat.

    public void practice3() {
        List<List<String>> lists = List.of(
                List.of("A", "B"),
                List.of("C", "D", "E")
        );
        List<String> result = lists.stream()
                .flatMap(Collection::stream)
                .toList();
        System.out.println(result);
    }

System.out.println(result); // [A, B, C, D, E]

✅ Завдання 4 — Злиття трьох потоків через reduce

🎯 Мета: динамічно поєднувати більше ніж 2 потоки.

    public void practice4() {
        Stream<String> s1 = Stream.of("a");
        Stream<String> s2 = Stream.of("b");
        Stream<String> s3 = Stream.of("c");

        Stream<String> merged = Stream.of(s1, s2, s3)
                .reduce(Stream::concat)
                .orElse(Stream.empty());
        merged.forEach(System.out::println);
    }

// abc

✅ Завдання 5 — Маркування джерел

🎯 Мета: після злиття знати, з якого потоку елемент.

    public void practice5() {
        List<String> java = List.of("Spring", "Hibernate");
        List<String> js = List.of("React, Vue");
        Stream.concat(
                        java.stream().map(j -> "[JAVA] " + j),
                        js.stream().map(j -> "[JS ]" + j))
                .forEach(System.out::println);
    }

📤

[Java] Spring  
[Java] Hibernate  
[JS] React  
[JS] Vue

✅ Завдання 6 — Злиття числових потоків (IntStream)

🎯 Мета: показати, як працює IntStream.concat().

    public void practice6 () {
        IntStream evens = IntStream.of(2,4,6);
        IntStream odds = IntStream.of(1,3,5);
        IntStream.concat(odds, evens).sorted().forEach(System.out::println);
    }

// 123456

✅ Завдання 7 — Злиття потоків різних типів

🎯 Мета: перетворити типи перед злиттям.

    public void practice7() {
        Stream<String> names = Stream.of("Bob", "Ann");
        Stream<Integer> ages = Stream.of(25, 30);
        Stream.concat(names, ages.map(String::valueOf))
                .forEach(System.out::println);
    }

📤

Bob  
Ann  
25  
30

✅ Завдання 8 — Злиття файлів (рядки з двох файлів)

🎯 Мета: об’єднати два текстових файли в один потік.

    public void practice8 () {
        try (Stream <String> file1 = Files.lines(Path.of("a.txt"));
        Stream <String> file2 = Files.lines(Path.of("b.txt"))
        ) {
            Stream.concat(file1, file2).forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

🧠 Ідеально для логів, статистики, великих текстових об’єднань.

    public void practic9 () {
        List <Integer> l1 = List.of(5,2,8);
        Set<Integer> l2 = Set.of(3,8,1);
        List <Integer> sorted = Stream.concat(l1.stream(), l2.stream())
                .distinct()
                .sorted()
                .toList();
        System.out.println(sorted);
    }
// [1, 2, 3, 5, 8]

✅ Завдання 10 — Комбіноване: 3 типи + обробка + колект

🎯 Мета: об’єднати числа, слова і символи.

    public void practice10 () {
        Stream <String> numbers = Stream.of("1", "2");
        Stream <String> letters = Stream.of("A", "B");
        Stream <String> signs = Stream.of("!", "?");
        
        String merged = Stream.of(numbers, letters, signs)
                .reduce(Stream::concat)
                .orElse(Stream.empty())
                .collect(Collectors.joining(", "));
        System.out.println(merged);
// 1, 2, A, B, !, ?

🧠 Різниця між підходами
Підхід Для чого Особливість
Stream.concat(a, b)    просте злиття 2 потоків порядок зберігається
flatMap(Collection::stream)    злиття колекцій у колекції універсальний і зручний
reduce(Stream::concat)    злиття довільної кількості потоків трохи важчий, але гнучкий
IntStream.concat()    числові потоки не можна комбінувати з Stream<T> напряму