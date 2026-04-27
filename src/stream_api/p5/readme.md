Блок 5 — Comparator / Sorting / toMap / entries (10 задач)

Мета: складніші сортування, комбіновані порівняння, робота з Map.Entry.

## 5.1 — Сортувати за кількістю голосних, потім алфавіт

Умова: custom comparator через thenComparing.
Рішення:

    public List<String> practice1 () {
        return Stream.of("cooperation", "stream", "banana",
                "supernova", "moon", "queueing", "idealism")
                .sorted(Comparator.comparing(Main1::count).thenComparing(Comparator.naturalOrder()))
                .collect(Collectors.toList());
    }

    public static Long count (String s) {
        return s.chars().filter(c->"aeiou".indexOf(c)>=0).count();
    }

Коментар: multi-criteria sorting.

## 5.2 — Сортування чисел: парні першими, всередині спадання

Рішення: (як у твоєму файлі)

    public List <Integer> practice2 (List <Integer> numbers) {
        return numbers.stream().sorted(Comparator.comparing((Integer n)->n%2==0)
                .thenComparing(Comparator.reverseOrder()))
                .toList();
    }


Коментар: використовуємо два критерії.

## 5.3 — Сортувати Map<Integer,List<String>> за розміром списку

Умова: повернути LinkedHashMap в порядку зростання розміру списків.
Рішення:

    public LinkedHashMap<Integer, List<String>> practice3(Map<Integer, List<String>> input) {
        return input
                .entrySet()
                .stream()
                .sorted(Comparator.comparing(e -> e.getValue().size()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (a, b) -> b, LinkedHashMap::new));


Теорія: .entrySet().stream() — шаблон для сортування map.

## 5.4 — Сортувати слова за довжиною у зворотному порядку

Рішення:

    public List <String> practice4 (List <String> words) {
        return words.stream().sorted(Comparator.comparing(String::length)
                .reversed()).toList();
    }

Коментар: reversed() інвертує порядок.

## 5.5 — Сортування Map за значенням (reverse)

Умова: {a=2,b=5,c=1} → b,a,c
Рішення: (аналогічно 4.8)

    public LinkedHashMap <Character, Integer> practice5 (Map <Character, Integer> map) {
        return  map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (e1,e2)->e1, LinkedHashMap::new
                        ));
    }

Теорія: зберігаємо порядок через LinkedHashMap.

## 5.6 — Знайти слово з найбільшою кількістю літер 'a'

Умова: List.of("alpha","arena","java","banana","lava") → "banana"
Рішення:

    public String practice6 () {
        return List.of("alpha","arena","java","banana","lava").stream()
                .max(Comparator.comparing(w->w.chars().filter(c->c=='a').count())).orElse("");
    }

Коментар: map->count pattern, потім max.

5.7 — Сортування з thenComparing: vowels then length

Умова: спочатку за кількістю голосних, якщо рівно — за довжиною.
Рішення:

    public List <String> practice7 (List <String> words) {
        return words.stream()
        .sorted(Comparator
            .comparing(Main1::count)
            .thenComparing(String::length))
        .toList();
    }

Коментар: thenComparingInt прискорює для int.

5.8 — Витягти топ-N елементів після сортування Map.Entry

Умова: повернути keys top-N за value.
Рішення:

    public List <String> practice8 (Map <String, Long> map, int n) {
        return map.entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(n)
                .map(Map.Entry::getKey)
                .toList();
    }

Теорія: часто корисно у frequency-based задачах.

5.9 — Перетворити list → Map<element, count>

Умова: ["1","2","2","3","3","3"] → Map<Long,Long> (value→count).
Рішення:

    public Map <Long, Long> practice9 (List <Long> inputs) {
        return inputs
                .stream()
                .map(Long::valueOf)
                .collect(Collectors
                        .groupingBy(x->x,
                                Collectors.counting()));
    }

Коментар: стандартна побудова frequency map.

5.10 — Найчастіший перший символ (виправлений)

Умова: знайти найчастіший перший символ (коректна версія).
Рішення:

return Arrays.stream(words)
.collect(Collectors.groupingBy(w->w.charAt(0), Collectors.counting()))
.entrySet().stream()
.max(Map.Entry.comparingByValue())
.map(Map.Entry::getKey)
.orElse(null);


Коментар: зверни увагу на коректний Comparator та типи.
