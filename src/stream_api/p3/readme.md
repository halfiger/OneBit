## 3.1 — Map слово → чи має голосні

Умова: повернути Map<String,Boolean>.
Рішення:

    public Map<String, Boolean> practice1 () {
        String [] words = new String[] {"Aa", "Bba", "Cec"};
        return Arrays.stream(words)
                        .collect(Collectors.toMap(Function.identity(),
                                w->w.chars().anyMatch(c->"aeiou".indexOf(c)>=0)));
    }


Коментар: toMap з булевою функцією.

## 3.2 — Розділити слова на ті, що містять голосну і ні

Умова: partitioningBy → Map<Boolean,List<String>>.
Рішення:

    public Map <Boolean, List<String>> practice2 () {
        String [] array = new String [] {"Aa", "Bba", "Cec"};
        return Arrays.stream(array)
                .collect(Collectors.partitioningBy(w->w.chars()
                        .anyMatch(c->"aeiou".indexOf(c)>=0)));
    }

Теорія: partitioningBy швидкий для true/false.

## 3.3 — Порахувати кількість слів за довжиною (але лише ті, що містять голосну)

Умова: groupingBy length + counting.
Рішення:

    public void practice3 () {
        String [] array = new String [] {"Aa", "Bba", "Cec"};
        return Arrays.stream(array).
                filter(w->w.chars().anyMatch(c->"aeiou".indexOf(c)>=0))
                .collect(Collectors.groupingBy(String::length, Collectors.counting()))
    }

Коментар: комбінуємо фільтр і groupingBy.

## 3.4 — Map<перша літера, середня довжина>

Умова: для кожної першої літери порахувати average length.
Рішення:

    public Map <Character, Double> practice4() {
        String [] array = new String [] {"Aa", "Bba", "Cec"};
        return Arrays.stream(array).collect(Collectors.groupingBy(w->w.charAt(0), Collectors.averagingInt(String::length)));
    }

Теорія: averagingInt дає Double.

## 3.5 — Найчастіший перший символ

Умова: знайти символ найчастіше на початку слів.
Рішення:

    public Character practice5() {
        String[] array = new String[]{"Aa", "Bba", "Cec", "Aata"};
        return Arrays.stream(array)
                .collect(Collectors.groupingBy(w -> w.charAt(0), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }


Коментар: два кроки: grouping -> max.

## 3.6 — Map довжина → words (але підняти в UpperCase)

Умова: згрупувати слова за довжиною,
але значення — uppercase списки.
Рішення:

    public Map<Integer, List<String>> practice6 () {
        String [] array = new String[]{"Aa", "Bba", "Cec", "Aata"};
        return Arrays.stream(array)
                .collect(Collectors
                        .groupingBy(String::length, 
                                Collectors.mapping(String::toUpperCase, 
                                        Collectors.toList())));
    }

Теорія: mapping використовується
для трансформації елементів перед колекцією.

## 3.7 — IntSummaryStatistics по довжинах слів з повторюваними буквами

Умова: відфільтрувати слова з повторюваними буквами →
summaryStatistics по довжинах.
Рішення:

    public IntSummaryStatistics practice7 () {
        String [] array = new String[]{"Aa", "Bba", "Cec", "Aata"};
        return Arrays.stream(array).filter(w->hasRepeatingLetters(w))
                .mapToInt(String::length)
                .summaryStatistics();
    }

    public static Boolean hasRepeatingLetters(String w) {
        return w.length() - w.chars().distinct().count() == 0;
    }


Коментар: hasRepeatingLetters — допоміжний метод,
що перевіряє дублікати.

## 3.8 — Побудувати Map<Boolean,List<String>> —
починаються з великої літери

Умова: розділити по першому символу UpperCase.
Рішення:

    public Map <Boolean, List<String>> practice8 () {
        String [] array = new String[]{"Aa", "Bba", "Cec", "Aata"};
        return Arrays.stream(array)
                .collect(Collectors
        .partitioningBy(w->Character.isUpperCase(w.charAt(0))));
    }

Коментар: зручне для UI/форматування.

## 3.9 — Найдовше слово, що починається з великої літери

Умова: знайти найбільше слово серед тих,
що починаються з UpperCase.
Рішення:

    public String task9 () {
        return Arrays.stream(words).filter(w->Character.isUpperCase(w.charAt(0)))
                .max(Comparator.comparingInt(String::length))
                .orElse("");


Коментар: подібний шаблон для будь-якого фільтру + max.

## 3.10 — Map<довжина, середня кількість голосних> — unmodifiable ?
Умова: зробити результат незмінним Map <Long, Double>
Рішення:

return Stream.of(words).collect(Collectors.collectingAndThen(
Collectors.groupingBy(String::length, Collectors.averagingInt(
w -> (int) w.chars().filter(c -> "aeiou".indexOf(c) >= 0).count()
)),
Collections::unmodifiableMap
));


Теорія: collectingAndThen дозволяє обгорнути результат у read-only.

(Приклади на тему collectingAndThen зустрічаються в твоєму файлі — я опирався на них).

Блок 4 — Collectors & collectingAndThen (10 задач)

Мета: навчитися комбінувати колектори, використовувати collectingAndThen, 
toList→unmodifiableList, mapping, flatMapping.