Блок 6 — Advanced / Challenges / Mixed (10 задач)

Мета: комбіновані приклади, custom checks,
edge-cases, collectingAndThen validation.

## 6.1 — Знайти єдине слово, що містить 'p' (інакше виняток)

Умова: якщо не рівно одне — кидати IllegalStateException.
Рішення:

    public String practice1(List<String> data) {
        return data.stream().filter(w -> w.contains("p"))
                .collect(Collectors.collectingAndThen(Collectors.toList(),
                        list -> {
                            if (list.size() != 1) {
                                throw new IllegalStateException("Expected only one word with 'p'");
                            }
                            return list.get(0);
                        }
                ));
    }


Теорія: демонстрація валідації після collect.

## 6.2 — Слова, що містять усі голосні (a,e,i,o,u)

Умова: знайти слова, що містять всі голосні.
Рішення:

    public List <String> practice2 (List <String> words) {
        return words.stream()
                .filter(w->"aeiou"
                        .chars()
                        .allMatch(c->w.indexOf(c)>=0))
                .toList();
    }

Коментар: allMatch по символам — досить жорсткий фільтр.

6.3 — Згенерувати 100 випадкових int, відфільтрувати позитивні, відсортувати та взяти топ-10

Рішення:

    public void practice3 () {
        List<Long> top10 = new Random().ints(100, -100, 101)
                .mapToObj(String::valueOf)
                .map(Long::valueOf)
                .filter(n->n>0)
                .sorted(Comparator.reverseOrder())
                .limit(10)
                .toList();
    }

Теорія: демонстрація pipeline з рандомом.

6.4 — Порахувати, скільки груп за останньою літерою

Умова: ["hi","hello","mango","go","halo","yo"] → 3
Рішення:

    public Integer practice4 () {
        return Stream.of("hi","hello","mango","go","halo","yo")
                .collect(Collectors.groupingBy(w->w.charAt(w.length()-1)))
                .size();
    }

Коментар: groupingBy + size для кількості груп.

6.5 — Тренажер peek: показати проміжні стани

Умова: надрукувати початкові слова, upper-case, і фінальний список >3.
Рішення: (шаблон)

    public List <String> practice5 (List <String> words) {
        return words.stream().peek(w-> System.out.println("Begin " + w))
                .map(String::toUpperCase)
                .peek(w-> System.out.println(" upper " + w))
                .filter(w->w.length() > 3)
                .toList();
    }


Теорія: peek — корисний для debug pipeline.

6.6 — Product of digits at odd indexes

Умова: n=123456 → product of digits at indexes 1,3,5 → 246 = 48.
Рішення:

    public int practice6 (int n) {
        int [] digits = String.valueOf(n).chars().map(c->c-'0').toArray();
        return IntStream.range(0, digits.length).filter(i->i%2==1).map(i->digits[i]).reduce(1,(a,b)->a*b);
    }

Коментар: комбінуємо range + index lookup.

6.7 — Згенерувати Map<довжина, List<words>> але відфільтрувати дзеркальні/пусті

Умова: згрупувати за довжиною, ігнорувати пусті та palindrome.
Рішення:

    public Map <Integer, List<String>> practice7 (List <String> words) {
        return words.stream().filter(w-> !w.isBlank() && !isPalindrome(w))
                .collect(Collectors.groupingBy(String::length));
    }

    public static boolean isPalindrome (String s) {
        return new StringBuilder(s).reverse().toString().equals(s);
    }

Теорія: демонструє поєднання фільтрів з groupingBy.

6.8 — Знайти число з найбільшою кількістю різних цифр

    public Integer practice8 () {
        return Stream.of(111,123,444,9087).max(Comparator.comparing(
                a->String.valueOf(Math.abs(a))
                        .chars()
                        .distinct()
                        .map(c->c-'0').count()))
                .orElse(-1);
    }


Коментар: distinct() по chars дає різні символи.

6.9 — Collectors.flatMapping (hobbies) → group by first letter of hobby

Умова: є список людей з hobbies → згрупувати всі хобі за першою літерою.
Рішення (flatMap варіант):

return people.stream()
.flatMap(p -> p.hobbies().stream())
.collect(Collectors.groupingBy(h -> h.charAt(0), Collectors.toSet()));


Теорія: flatMapping також існує як вбудований колектор (Java16+), але flatMap
+ groupingBy — універсальний варіант.

6.10 — Final challenge: складний pipeline (filter+map+group+collectingAndThen)

Умова: відфільтрувати слова з >2 голосними, привести до lowerCase, згрупувати
по останній літері, для кожної групи повернути список унікальних слів і зробити
map незмінним.
Рішення:

    public Map<Character, Set<String>> practice10 (List <String> words) {
        return words.stream().filter(w->w.chars().filter(c->"aeiou".indexOf(c)>=0).count()>2)
                .map(String::toLowerCase)
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(w->w.charAt(w.length()-1),
                                Collectors.mapping(Function.identity(), Collectors.toSet())), Collections::unmodifiableMap
                ));
    }


Коментар: комбінуємо кілька концептів в одному pipe — ідеальний підсумковий приклад.