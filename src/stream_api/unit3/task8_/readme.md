Ми зараз переходимо до Модуля №8 — Stream + Optional — однієї
з найвитонченіших частин сучасної Java.
Вона часто зустрічається в “чистому” коді: коли методи 
повертають Optional, а вам потрібно безпечно обробляти
колекції чи вкладені об’єкти через Stream.

Цей модуль навчить:

перетворювати Optional у потік;
витягувати колекції з Optional;
поєднувати декілька Optional без if/else;
і робити ланцюжки .flatMap(Optional::stream) як профі.

⚙️ Модуль №8 — Optional + Stream

📘 Мета:
навчитись “струменево” працювати з Optional — без null, без перевірок, і з насолодою 😎

✅ Завдання 1 — Optional.stream() базовий приклад

📋 Умова:
Виведи значення, якщо Optional не порожній.

    public void practice1 () {
        Optional<String> name = Optional.of("Java");
        name.stream().forEach(System.out::println);
     }

// Java
🧠 Optional.stream() — це потік із 0 або 1 елемента.

✅ Завдання 2 — Optional.empty() не виводить нічого

    public void practice2 () {
        Optional <String> empty = Optional.empty();
        empty.stream().forEach(System.out::println);
    }

// (нічого не виводить)

🧠 Безпечний спосіб обробки без перевірки isPresent().

✅ Завдання 3 — Stream з Optional<List>

📋 Умова:
Є Optional<List<Integer>>, виведи всі елементи списку.

    public void practice3 () {
        Optional <List<Integer>> maybeList = Optional.of(List.of(1,2,3));
        maybeList.stream()
                .flatMap(List::stream)
                .forEach(System.out::println);
    }

// 1 2 3

🧠 .flatMap(List::stream) — класика: витягуємо список із Optional.

✅ Завдання 4 — Пошук у списку з Optional результатом

📋 Умова:
Знайди перше слово довше за 4 літери.

    public void practice4() {
        List<String> words = List.of("cat", "apple", "pear");
        Optional<String> found = words.stream()
                .filter(w -> w.length() > 4)
                .findFirst();
        found.stream().forEach(System.out::println);
    }
// apple


🧠 Тепер .stream() дозволяє обробити результат Optional без if.

✅ Завдання 5 — Об’єднання кількох Optional у потік

📋 Умова:
Є кілька Optional — об’єднай їх у один Stream.

        Stream<Optional<String>> optionals = Stream.of(
                Optional.of("A"),
                Optional.empty(),
                Optional.of("B")
        );
        List<String> list = optionals.flatMap(Optional::stream).toList();
        System.out.println(list);
    } // [A, B]


🧠 “Розпаковуємо” Optionals потоком, без перевірок.

✅ Завдання 6 — Optional із Optional

📋 Умова:
Є Optional<Optional<String>>, отримай внутрішнє значення.

Optional<Optional<String>> nested = Optional.of(Optional.of("Hello"));

    public void practice6() {
        Optional<Optional<String>> nested = Optional.of(Optional.of("Hello"));
        nested.stream()
                .flatMap(Optional::stream)
                .forEach(System.out::println);
    }
// Hello


🧠 flatMap + stream — універсальна комбінація для розпаковки вкладених Optional.

✅ Завдання 7 — Optional з колекції (заміна null-check)

📋 Умова:
Є список користувачів, знайди першого з іменем довше 5 символів або "none".

    public void practice7 () {
        List<String> users = List.of("Bob", "Alexander", "Tom");
        String result = users.stream()
                .filter(u->u.length() >5)
                .findFirst()
                .orElse("none");
        System.out.println(result);
    }

// Alexander


🧠 .findFirst() повертає Optional, який легко обробити.

✅ Завдання 8 — Комбінування Optional і Stream після map()

📋 Умова:
Отримай довжину слова, якщо воно є.

    public void practice8 () {
        Optional <String> word = Optional.of("Stream");
        int len = word.stream()
                .map(String::length)
                .findFirst()
                .orElse(0);
        System.out.println(len);
    }

// 6


🧠 Optional.stream() дозволяє “продовжити” роботу в потоці без unwrap.

✅ Завдання 9 — Optional + Stream.filter() в ланцюжку

📋 Умова:
Виведи користувача лише якщо ім’я починається з великої літери.

    public void practtice9 () {
        Optional <String> name = Optional.of("Vitalii");
        name.stream().filter(w->Character.isUpperCase(w.charAt(0)))
                .forEach(System.out::println);
    }

// Vitalii

✅ Завдання 10 — Комбіноване: Optional → Stream → collect

📋 Умова:
Є список Optional-значень. Збери всі непорожні у строку через кому.

    public void practice10 () {
        List <Optional<String>> optionals = List.of(
                Optional.of("red"),
                Optional.empty(),
                Optional.of("green"),
                Optional.of("blue"));
                
        String result = optionals.stream()
                .flatMap(Optional::stream)
                .collect(Collectors.joining(", "));
        System.out.println(result);
    }

// red, green, blue

💡 Підсумок
Приклад	Опис
opt.stream()	Створює потік з 0–1 елементом
.flatMap(Optional::stream)	Розпаковує Optionals у потік
Optional<List<T>> + .flatMap(List::stream)	Витягує список із Optional
Stream<Optional<T>> + .flatMap(Optional::stream)	Об’єднує кілька Optional
.findFirst().stream()	Безпечне продовження після пошуку

🧠 Порада від Марічки:
Після цього модуля ви можете забути про if (opt.isPresent()) —
бо тепер Optional + Stream дає елегантні, “потокові” рішення без жодного null.