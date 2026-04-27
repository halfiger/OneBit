Блок 2 — FlatMap & chars (10 задач)

Мета: flatMap для строк/масивів,
робота з символами, уникати вкладених структур.

2.1 — Зібрати унікальні слова з речень

Умова: список речень → всі унікальні слова
в нижньому регістрі.
Рішення:

    public List <String> practice1 (List<String> sentences) {
        return sentences.stream()
                .flatMap(s-> Arrays.stream(s.split("\\s+")))
                .map(String::toLowerCase)
                .distinct()
                .toList();
    }

Теорія: flatMap "розплющує" вкладені колекції.

2.2 — Мінімальне парне число з матриці int[][]

Умова: знайти мінімальне парне у всіх
внутрішніх масивах.
Рішення:

    public int practice2 (int [][] array) {
        return Arrays.stream(array)
                .flatMapToInt(Arrays::stream)
                .filter(x->x%2==0)
                .min()
                .orElse(-1);
    }


Коментар: flatMapToInt зручний для
числових матриць.

2.3 — Перетворити List<List<Integer>> →
відфільтрувати і відсортувати

Умова: "згорнути" усі числа,
лишити позитивні, відсортувати.
Рішення:

    public List <Integer> practice3 (List<List<Integer>> lists) {
        return lists.stream()
                .flatMap(Collection::stream)
                .filter(i->i>0)
                .sorted()
                .toList();
    }


Теорія: flatMap + фільтрація дають плоский,
чистий потік.

2.4 — Зібрати всі домени з email

Умова: отримати домени з ivan@ukr.net і
згрупувати за довжиною логіна.
Рішення:

    public Map<Integer, List<String>> practice4 (List <String> emails) {
        return emails.stream()
                .collect(Collectors.groupingBy(
                        e -> e.substring(0, e.indexOf('@')).length(),
                        Collectors.mapping(e -> e
                                        .substring(e.indexOf('@')+1),
                                Collectors.toList())
                ));
    }


Коментар: mapping усередині groupingBy
— потужне поєднання.

2.5 — Унікальні символи як String (distinct)

Умова: "abracadabra" → ["a","b","r","c","d"].
Рішення:

    public List <String> practice5 (String s) {
        return s.chars()
                .distinct()
                .mapToObj(c->String.valueOf((char)c))
                .toList();
    }


Коментар: chars → int коди; mapToObj
для об’єктів.

2.6 — Витягнути лише великі літери

Умова: "hEllO WoRLd" → ["E","O","W","R","L"]
Рішення:

    public List <String> practice6 (String s) {
        return s.chars()
                .filter(Character::isUpperCase)
                .mapToObj(c->String.valueOf((char)c))
                .toList();
    }

Коментар: корисно для парсингу метаданих.

2.7 — Рядок → місця в алфавіті

Умова: "abcxyz" → [1,2,3,24,25,26]
Рішення:

    public List <Integer> practice7 (String s) {
        return s.chars()
                .map(c->c-'a'+1)
                .boxed()
                .toList();
    }


Теорія: арифметика з символами.

2.8 — Витягти лише букви й відсортувати
у зворотному порядку

Умова: "a1C!bZ2" → ['Z','C','b','a']
Рішення:

    public List<Character> practice8 (String s) {
        return s.chars()
                .filter(Character::isAlphabetic)
                .mapToObj(c->(char)c)
                .sorted(Comparator.reverseOrder())
                .toList();
    }

Коментар: boxed/char — потрібне для comparator

2.9 — Порахувати суму ASCII-кодів слова
Умова: ["abc","aaa","zzz"] → "zzz"
має найбільшу суму.
Рішення:

return list.stream()
.max(Comparator.comparingInt(w -> w
.chars().sum()))
.orElse("");


Теорія: chars().sum() дає суму кодів символів.

2.10 — Кількість літер у рядку (частотний мап)

Умова: "banana" → {b=1,a=3,n=2}
Рішення:

return s.chars()
.filter(Character::isLetter)
.mapToObj(c -> (char)c)
.collect(Collectors.groupingBy(Function
.identity(), Collectors.counting()));


Коментар: groupBy + counting — стандарт
для частот.

Блок 3 —
Grouping / Partitioning / Counting
(10 задач)

Мета: groupingBy, partitioningBy,
counting, averaging, summaryStatistics.