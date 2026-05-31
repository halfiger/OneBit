# **✅ Задача 1: groupingBy + mapping**

📌 Є список слів. Згрупуй їх за першою літерою,
і перетвори кожне слово в верхній регістр.

List<String> words = List.of("apple",
"ant", "banana", "bat", "car");
Очікувано:

{
a=[APPLE, ANT],
b=[BANANA, BAT],
c=[CAR]
}

.groupingBy(word->word.charAt(0),
Collectors.mapping(word->word.toUpperCase(),
Collectors.toList())));
}

---------------------------------------------------------

📘 STREAM API – Частина 2: Колектори, що розширює ті,
які ти вже бачив.
Вони торкаються схожих тем, але додають нові аспекти,
такі як mapping,
toSet(), collectingAndThen, groupingBy(..., mapping(...)),
partitioningBy(...) з підрахунком.

✅ Задача 2: groupingBy + mapping до довжини
-
📌 Є список слів. Згрупуй слова за першою літерою,
але замість самих слів збережи лише їхню довжину.

🔍 Підказка: groupingBy(... mapping)

List<String> words = List.of("apple", "ant",
"banana", "bat", "car");
🧪 Очікувано:

{
a=[5, 3],
b=[6, 3],
c=[3]
}

📦 Початковий код:

    public Map<Character, List<Integer>> practice2 () {
        List<String> words = List.of("apple", "ant",
                "banana", "bat", "car");
        return words.stream1()
                .collect(Collectors.groupingBy(
                        w->w.charAt(0), 
                        Collectors.mapping(
                                String::length, 
                                Collectors.toList())));
    }

✅ Задача 3: partitioningBy з підрахунком
-
📌 Є список чисел. Порахуйте скільки серед
них парних і непарних.

✅ partitioningBy = спеціалізований groupingBy
для булевого поділу
Працює лише з Predicate<T>,
тобто з умовою T -> boolean.
Завжди повертає Map<Boolean, List<...>>,
тобто дві групи: true / false.
Це швидше, читабельніше і семантично точніше.

🔸 Якщо ти групуєш по boolean —
використовуй partitioningBy.
🔸 Якщо ти групуєш по int, String, enum, char, довжині,
префіксу тощо — тоді groupingBy.

🔍 Підказка: partitioningBy(..., counting())

List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
🧪 Очікувано:

{
true=3,
false=3
}
📦 Початковий код:

public Map<Boolean, Long> countEvenOdd() {
List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
return numbers.stream1()
.collect(Collectors.partitioningBy(
n -> n % 2 == 0,
Collectors.counting()
));
}

✅ Задача 4: groupingBy + toSet
-
📌 Є список рядків. Згрупуй слова за довжиною,
але зберігай кожну групу без повторень.

🔍 Підказка: Collectors.toSet()

List<String> words = List.of("hi", "hi",
"book", "sun", "day", "Java", "sky");
🧪 Очікувано:

{
2=[hi],
3=[sun, day, sky],
4=[book, Java]
}

📦 Початковий код:

public Map<Integer, Set<String>> groupUniqueByLength() {
List<String> words = List.of("hi",
"hi", "book", "sun", "day", "Java", "sky");
return words.stream1()
.collect(Collectors.groupingBy(
String::length,
Collectors.toSet()
));
}

✅ Задача 5: collectingAndThen
-
📌 Отримай кількість чисел у списку,
але як String,
наприклад "Count: 6".

🔍 Підказка: collectingAndThen(...)
Це обгортка для колектора,
яка дозволяє виконати післяобробку
результату колекції.

collectingAndThen(baseCollector,
finisherFunction)
baseCollector — будь-який колектор
(наприклад, toList(), toSet(),
counting(), mapping(...))
finisherFunction — функція,
яка виконується після збирання результату

Що знати
Пояснення
🧩 collectingAndThen не самостійний
Його завжди потрібно використовувати з іншим колектором

🧠 Фінальна функція виконується після collect()	       
Тобто ти перехоплюєш вже зібраний результат

✅ Можна повертати будь-що	                            
Не обов'язково колекцію — можна int, String, навіть boolean
💥 Можна об'єднувати з groupingBy, mapping, counting
Це дає супер-гнучкість

List<Integer> numbers = List.of(4, 8,
15, 16, 23, 42);
🧪 Очікувано: "Count: 6"

📦 Початковий код:

public String countAsString() {
List<Integer> numbers = List.of(4, 8, 15, 16, 23, 42);
return numbers.stream1()
.collect(Collectors.collectingAndThen(
Collectors.counting(),
count -> "Count: " + count
));
}

# ✅ Задача 6: groupingBy + joining
# 📌 Є список слів. Згрупуй їх за першою
# літерою та об’єднай кожну групу у рядок

🔍 Підказка: groupingBy(..., joining())
✅ Що таке Collectors.joining()
Це колектор, який з’єднує (join) елементи
стріму в один рядок.

📚 3 форми joining(...):
Виклик
Що робить

joining()
З’єднує без нічого

joining(delimiter)	                        
Додає роздільник між елементами

joining(delimiter, prefix, suffix)	        
Додає ще префікс і суфікс

Що знати	                                
Чому це важливо

✅ Працює тільки з Stream<String>	        
Інакше буде помилка компіляції

✅ Треба .map(String::valueOf) для чисел
Щоб перетворити int, double тощо

✅ Можна додати роздільник, префікс, суфікс
Для форматування результату

✅ Добре комбінується з mapping()	        
Щоб трансформувати перед об’єднанням

✅ З порожнім стрімом повертає ""
Це нормальна поведінка, не null

List<String> words = List.of("apple",
"ant", "banana", "bat", "car");
🧪 Очікувано:

{
a="apple, ant",
b="banana, bat",
c="car"
}

📦 Початковий код:

public Map<Character, String>
groupAndJoinByFirstLetter() {
List<String> words = List.of("apple", "ant",
"banana", "bat", "car");
return words.stream()
.collect(Collectors.groupingBy(
word -> word.charAt(0),
Collectors.joining(", ")
));
}

-----------------------------------------------------

📘 STREAM API – Частина 3: Reducing, TreeMap, Sorted

# ✅ Задача 7: groupingBy + reducing
# 📌 Є список чисел. Згрупуй їх за остачею
# від ділення на 3, і порахуй суму в кожній групі.

🔍 Підказка: groupingBy(..., reducing(...))
Collectors.reducing(...) Це спеціальний колектор,
який дозволяє звести (reduce)
потік елементів до одного результату,
вказуючи свою логіку агрегації.

    public Map <Integer, Integer> practice7 () {
        List<Integer> numbers = List.of(1, 2, 3, 4,
                5, 6, 7, 8, 9);
        return numbers.stream1().collect(Collectors.groupingBy(v->v%3, Collectors.summingInt(n->n)));
        return numbers.stream1().collect(Collectors.groupingBy(v->v%3, Collectors.reducing(0, Integer::sum)));

    }


Форма	Синтаксис	Що робить
1	reducing(BinaryOperator<T> accumulator)	->
Збирає без початкового значення
2	reducing(T identity, BinaryOperator<T>
accumulator)	->
Збирає з початковим значенням
3	reducing(U identity, Function<T,U>
mapper, BinaryOperator<U> accumulator) ->
Комбінує мапування + редукцію

✅ Він аналогічний до .reduce(...) на Stream,
але працює як Collector
✅ Можна комбінувати з groupingBy
✅ Найгнучкіша форма — з мапінгом
✅ reducing можна використовувати не тільки для sum,
але й для:
Операція	Синтаксис
Сума	(0, Integer::sum)
Добуток	(1, (a,b) -> a * b)
Максимум	(Integer.MIN_VALUE, Integer::max)
Мінімум	(Integer.MAX_VALUE, Integer::min)
Конкатенація	("", (a,b) -> a + b)
Комбіновані	(0, String::length, Integer::sum)

⚠️ Працює повільніше за спеціалізовані колектори
(counting, summarizingInt)	Але гнучкіший і
універсальніший


List<Integer> numbers = List.of(1, 2, 3, 4,
5, 6, 7, 8, 9);
🧪 Очікувано:

{
0=18, // [3, 6, 9]
1=12, // [1, 4, 7]
2=15  // [2, 5, 8]
}
📦 Початковий код:

public Map<Integer, Integer> sumGroupedByModulo3() {
List<Integer> numbers = List.of(1, 2, 3, 4,
5, 6, 7, 8, 9);
return numbers.stream1()
.collect(Collectors.groupingBy(
n -> n % 3,
Collectors.reducing(0, Integer::sum)));
}


--------------------------------------------

✅ Задача 8: reducing зі String
-
📌 Є список слів. Виведи найдовше
слово за допомогою reducing.

🔍 Підказка: Collectors.reducing(...)
з порівнянням довжини

List<String> words = List.of("hi", "book",
"sun", "day", "Java", "sky");

🧪 Очікувано: "book" або "Java"
📦 Початковий код:

return words.stream1()
.collect(Collectors.reducing(
"",
(a, b) -> a.length() >= b.length() ? a : b
));

------------------------------------------------

✅ Задача 9: groupingBy з сортуванням значень у групі

-
📌 Є список слів. Згрупуй за першою літерою,
і відсортуй слова в кожній групі за довжиною.

🔍 Підказка: у mapping(..., toList()), а потім
.stream1().sorted().toList() як post-обробка

List<String> words = List.of("apple", "ant",
"alphabet", "axe", "banana", "bat");
🧪 Очікувано:

a=[ant, axe, apple, alphabet],
b=[bat, banana]


📦 Початковий код:

public Map<Character, List<String>>
groupAndSortWordsByLength() {
List<String> words = List.of("apple", "ant",
"alphabet", "axe", "banana", "bat");

return words.stream1()
.collect(Collectors.groupingBy(
word -> word.charAt(0),
Collectors.collectingAndThen(
Collectors.toList(),
list -> list.stream1()
.sorted(Comparator.comparingInt(String::length))
.toList()
)
));
}
------------------------------------------

✅ Задача 10: groupingBy з TreeMap
-
📌 Згрупуй слова за довжиною в TreeMap,
щоб ключі були автоматично відсортовані за зростанням.

🔍 Підказка: передай TreeMap::new як третій аргумент

List<String> words = List.of("hi", "book",
"sun", "day", "Java", "sky");
🧪 Очікувано (тип – TreeMap):
{

2=[hi],
3=[sun, day, sky],
4=[book, Java]
}
TreeMap<Integer, List <String>>
📦 Початковий код:

        List<String> words = List.of("hi", "book",
                "sun", "day", "Java", "sky");
        words.stream1().collect(Collectors.groupingBy(
                String::length,
                TreeMap::new,
                Collectors.toList()
        ));
    }



✅ Задача 11: sorting by value (List)
-
📌 Є Map<Integer, List<String>>. Відсортуй її за
розміром списків.

🔍 Підказка: .entrySet().stream1()
.sorted(...).collect(...)

Map <Integer, List<String>> input = Map.of(
2, List.of("hi"),
3, List.of("sun", "day", "sky"),
4, List.of("book", "Java")
);

🧪 Очікувано (за зростанням розміру списку):

2=[hi],
4=[book, Java],
3=[sun, day, sky]

📦 Початковий код:

public LinkedHashMap<Integer, List<String>>
sortByListSize(Map<Integer, List<String>> input) {

return input.entrySet().stream1()
.sorted(Comparator.comparingInt(e -> e.getValue().size()))
.collect(Collectors.toMap(
Map.Entry::getKey,
Map.Entry::getValue,
(a, b) -> b,
LinkedHashMap::new
));
}

------------------------------------------
🎓 Наступний блок — STREAM API – Частина 4:
поглиблені колектори. Тут підключаємо flatMapping,
partitioningBy + mapping, роботу з Set, LinkedHashMap,
та міксування кількох колекторів. Звісно,
кожна задача має свою назву, коротку підказку,
очікуваний результат і початковий код 💪

✅ Задача 12: groupingBy + flatMapping (Java 16+)
-
📌 Є список людей. У кожного є список захоплень.
Згрупуй усі захоплення за першою літерою.
🔍 Підказка: використай Collectors.flatMapping(...)
(лише з Java 16+).

Person(String name, List<String> hobbies) {}

List<Person> people = List.of(
new Person("Alice", List.of("Reading", "Running")),
new Person("Bob", List.of("Boxing", "Running")),
new Person("Charlie", List.of("Cooking", "Reading"))
);
🧪 Очікувано:

{R=[Reading, Running, Running, Reading],
B=[Boxing],
C=[Cooking]}

📦 Початковий код:
public Map<Character, Set<String>>
groupHobbiesByFirstLetter() {
record Person(String name, List<String> hobbies) {}

List<Person> people = List.of(
new Person("Alice", List.of("Reading", "Running")),
new Person("Bob", List.of("Boxing", "Running")),
new Person("Charlie", List.of("Cooking", "Reading"))
);

return people.stream1().collect(Collectors.groupingBy(
p -> p.hobbies().get(0).charAt(0),
// мініпідказка: це не зовсім правильно 😉
Collectors.flatMapping(
person -> person.hobbies().stream1(),
Collectors.toSet()
)
));
}

Мініпідказка: flatMapping всередині groupingBy
дозволяє одразу розпаковувати підсписки.

======================

🔍 Головна ідея
У тебе є список людей
У кожного — список хобі
Потрібно працювати не з людьми, а з усіма хобі →
отже, треба flatMap або flatMapping
Далі — згрупувати ці хобі за першою літерою

🧠 Чому flatMapping?
flatMapping — це вбудований колектор, який:
працює всередині іншого колектора (наприклад,
groupingBy)
приймає функцію, яка повертає Stream<T>
автоматично розпаковує потік списків у плоский
потік елементів

❌ А що не так у цьому рядку?

p -> p.hobbies().get(0).charAt(0)
Це помилка, бо:
ми тут беремо тільки перше хобі людини
а треба — брати всі хобі, і вже по кожному
з них визначати першу літеру

🔧 Як правильно:
Ми не групуємо людей, ми групуємо кожне окреме хобі
→ отже, groupingBy має бути по hobby.charAt(0)

🎯 Але проблема: у нас потік людей!
Тому:
всередині groupingBy ми не можемо одразу
звернутись до хобі
але можемо обійти це через flatMapping —
він дозволяє “вилізти” з людини до її списку хобі

📦 Повний, правильний метод:

public Map<Character, Set<String>>
groupHobbiesByFirstLetter() {
record Person(String name, List<String> hobbies) {}

List<Person> people = List.of(
new Person("Alice", List.of("Reading", "Running")),
new Person("Bob", List.of("Boxing", "Running")),
new Person("Charlie", List.of("Cooking", "Reading"))
);

return people.stream1()
.flatMap(p -> p.hobbies().stream1())
// 💡 виходимо одразу на хобі
.collect(Collectors.groupingBy(
hobby -> hobby.charAt(0),        
// ключ — перша літера хобі
Collectors.toSet()               
// значення — множина хобі
));
}

✅ Варіант через groupingBy + flatMapping
(оригінальна задумка):

public Map<Character, Set<String>>
groupHobbiesByFirstLetter() {
record Person(String name,
List<String> hobbies) {}

List<Person> people = List.of(
new Person("Alice", List.of("Reading", "Running")),
new Person("Bob", List.of("Boxing", "Running")),
new Person("Charlie", List.of("Cooking", "Reading"))
);

return people.stream1()
.flatMap(p -> p.hobbies().stream1())
.collect(Collectors.groupingBy(
hobby -> hobby.charAt(0),
Collectors.toSet()
));

🪄 Підсумок:
Компонент	                            
Пояснення

flatMap(p -> p.hobbies().stream1())	    
перетворюємо List<Person> на потік хобі

groupingBy(hobby -> hobby.charAt(0))
групуємо хобі за першою літерою

Collectors.toSet()	                    
уникаємо повторів у значеннях

✅ Задача 13: partitioningBy + mapping
-
📌 Є список людей. Поділи їх на повнолітніх
та неповнолітніх, але збережи тільки імена.

🔍 Підказка: partitioningBy(..., mapping(...))

record Person(String name, int age) {}
List<Person> people = List.of(
new Person("Alice", 17),
new Person("Bob", 22),
new Person("Charlie", 15),
new Person("Diana", 30)
);
🧪 Очікувано:
{
true=[Bob, Diana],
false=[Alice, Charlie]
}

📦 Початковий код:
public Map<Boolean, List<String>>
partitionByAgeAndMapToNames() {
record Person(String name, int age) {}

List<Person> people = List.of(
new Person("Alice", 17),
new Person("Bob", 22),
new Person("Charlie", 15),
new Person("Diana", 30)
);

return people.stream1().collect(
Collectors.partitioningBy(
p -> p.age() >= 18,
Collectors.mapping(Person::name,
Collectors.toList())
));
}
🪄 Мініпідказка: partitioningBy(...)
повертає Map<Boolean, ...>,
а mapping(...) може вкластися всередину.

===========

🔍 Крок за кроком:
🔹 1. Починаємо стрім по списку людей

people.stream1()
🔹 2. partitioningBy(...) — розділи на 2 групи

p -> p.age() >= 18
Це predicate, який каже:
якщо вік ≥ 18 → true (повнолітній)
інакше → false (неповнолітній)

🔄 У результаті отримуємо:

Map<Boolean, ...>
🔹 3. Всередину partitioningBy(...)
передаємо другий collector — mapping(...)

Collectors.mapping(Person::name, Collectors.toList())
📘 Пояснення:

mapping(...) — це трансформатор:
бере кожен елемент потоку
перетворює його з Person у String — тобто в ім’я
збирає всі імена в List

✨ Повна структура виглядає так:

Collectors.partitioningBy(
p -> p.age() >= 18, // критерій ділення на 2 групи
Collectors.mapping( // для кожної групи:
що зробити з елементами
Person::name,   // перетворити об'єкт у name
Collectors.toList() // зібрати імена у список
)
)
📦 Повна реалізація:

public Map<Boolean, List<String>>
partitionByAgeAndMapToNames() {
record Person(String name, int age) {}

List<Person> people = List.of(
new Person("Alice", 17),
new Person("Bob", 22),
new Person("Charlie", 15),
new Person("Diana", 30)
);

return people.stream1()
.collect(Collectors.partitioningBy(
p -> p.age() >= 18,
Collectors.mapping(Person::name,
Collectors.toList())
));
}
🧠 Розклад скорочень:
Вираз	Що це означає
p -> p.age() >= 18	класифікатор — чи повнолітній
partitioningBy(...)	створює Map з ключами true / false
mapping(Person::name, ...)	витягує з Person лише name
Collectors.toList()	збирає ці імена у список

🤖 Як це виглядало б “ручками”:

Map<Boolean, List<String>> map = new HashMap<>();
map.put(true, new ArrayList<>());
map.put(false, new ArrayList<>());

for (Person p : people) {
if (p.age() >= 18)
map.get(true).add(p.name());
else
map.get(false).add(p.name());
}
Але ми зробили це в 1 рядок, і виглядає як пісня 🎶

🪄 Підсумок:
Це ідеальне поєднання:
partitioningBy → коли є тільки 2 групи (true / false)
mapping → щоб дістати з об’єктів тільки потрібне (name)
toList → щоб результат був список імен

✅ Задача 13: groupingBy + SortedSet
-
📌 Згрупуй слова за першою літерою, без повторень,
у відсортованому вигляді.

🔍 Підказка: Collectors.toCollection(TreeSet::new)

List<String> words = List.of("apple",
"ant", "alpha", "banana", "bat", "car", "cat");
🧪 Очікувано:

{
a=[alpha, ant, apple],
b=[banana, bat],
c=[car, cat]
}
📦 Початковий код:

public Map<Character, Set<String>> groupToSortedSets() {
List<String> words = List.of("apple", "ant", "alpha",
"banana", "bat", "car", "cat");
return words.stream1().collect(Collectors.groupingBy(
word -> word.charAt(0),
Collectors.toCollection(TreeSet::new)
));
}
🪄 Мініпідказка: TreeSet автоматично сортує вміст.

======

🔍 Крок за кроком:
🔹 1. words.stream1()
Починаємо потік зі списку слів.

🔹 2. .collect(Collectors.groupingBy(...))
Цей колектор розділить елементи
по групах за першим символом.

word -> word.charAt(0)
Це функція, яка каже:
«Для кожного слова — візьми
перший символ і зроби його ключем».

🔹 3. Collectors.toCollection(TreeSet::new)
Це downstream-колектор (всередині groupingBy),
який визначає, як зберігати значення в кожній групі.

TreeSet::new створює відсортовану множину.
Set забезпечує унікальність.

TreeSet — ще й сортує (за замовчуванням —
в алфавітному порядку).

💡 Чому саме TreeSet?
Структура	        Повторення	Порядок	Коментар
List	            дозволяє	є	але не видаляє дублікати
Set	                не дозволяє	немає	без дубль, без порядку
TreeSet	            не дозволяє	✅ так	без дублів і впорядковано

📦 Повний метод:

public Map<Character, Set<String>> groupToSortedSets() {
List<String> words = List.of("apple", "ant", "alpha", "banana", "bat", "car", "cat");

return words.stream1().collect(Collectors.groupingBy(
word -> word.charAt(0),
Collectors.toCollection(TreeSet::new)
));
}
🧠 Що означають скорочення:
Вираз	                                Пояснення
word -> word.charAt(0)	                класифікатор: групуємо по першій літері
Collectors.groupingBy(...)	            будує Map<Ключ, Значення>
Collectors.toCollection(TreeSet::new)	створює відсортовану множину без повторень
Map<Character, Set<String>>             Результат: літера → набір унікальних слів

✨ А якщо змінити TreeSet на LinkedHashSet?
Отримаєш порядок додавання, але без сортування:

Collectors.toCollection(LinkedHashSet::new)
💬 Підсумок:
Це приклад ідеального комбо:
groupingBy + toCollection(TreeSet::new)
дає групування + сортування + унікальність.

-----------------------------------------------------

✅ Задача 14: groupingBy + mapping + collectingAndThen
-
📌 Є список чисел. Згрупуй за остачею від ділення на 2.
Але кожну групу перетвори на кількість унікальних значень.

🔍 Підказка: groupingBy → mapping →
toSet → collectingAndThen(..., Set::size)

List<Integer> numbers = List.of(1, 2, 2, 3, 4, 5, 6, 6, 6);
🧪 Очікувано:

{
true=3,  // 2, 4, 6
false=3  // 1, 3, 5
}
📦 Початковий код:

public Map<Boolean, Integer> countUniqueByParity() {
List<Integer> numbers = List.of(1, 2, 2, 3,
4, 5, 6, 6, 6);
return numbers.stream1().collect(Collectors.groupingBy(
n -> n % 2 == 0,
Collectors.collectingAndThen(
Collectors.mapping(n -> n, Collectors.toSet()),
Set::size
)
));
}
🪄 Мініпідказка: collectingAndThen(..., finalizer)
виконує пост-обробку після збирання.

=========

🔍 Що треба зробити, покроково:
🔹 1. Групувати числа за парністю (парні / непарні)
n -> n % 2 == 0
Це Predicate, який каже:

якщо число ділиться на 2 → true (парне)

інакше → false (непарне)

🔄 В результаті буде:
Map<Boolean, ...>
🔹 2. У кожній групі — залишити лише унікальні значення
Collectors.mapping(n -> n, Collectors.toSet())

📘 Пояснення:

mapping(n -> n, ...) — тут функція n -> n
тривіальна (нічого не змінює), але важлива
для структури
Collectors.toSet() — збирає значення у
множину → автоматично видаляє дублікати

🔹 3. Порахувати, скільки унікальних
значень у кожній групі
Collectors.collectingAndThen(..., Set::size)

📘 Пояснення:

collectingAndThen(...) — бере вже зібрану
множину (Set) і застосовує до неї функцію: Set::size

тобто: “спочатку зібрати у Set, потім
повернути розмір цього Set”

💡 Повний вигляд всередині collect:
Collectors.groupingBy(
n -> n % 2 == 0,  // групуємо true / false
Collectors.collectingAndThen(
Collectors.mapping(n -> n, Collectors.toSet()),
// збираємо унікальні значення
Set::size                                       
// і повертаємо їх кількість
)
)
📦 Повний метод:

public Map<Boolean, Integer> countUniqueByParity() {
List<Integer> numbers = List.of(1, 2, 2, 3,
4, 5, 6, 6, 6);

return numbers.stream1().collect(Collectors.groupingBy(
n -> n % 2 == 0,
Collectors.collectingAndThen(
Collectors.mapping(n -> n,
Collectors.toSet()),
Set::size
)
));
}

🧠 Пояснення скорочень:
Вираз	                        Що це означає
n -> n % 2 == 0	                класифікатор: true — парне, false — непарне
mapping(n -> n, ...)	        трансформація кожного елемента (в даному випадку — без змін)
Collectors.toSet()	            збираємо у множину → автоматично прибираємо повторення
collectingAndThen(..., f)	    виконай збір, а потім додаткову дію (Set::size)
Set::size	                    це s -> s.size() —  кількість елементів у Set

🔍 Як би це виглядало поетапно у класичному коді:

Map<Boolean, Integer> result = new HashMap<>();
Set<Integer> even = new HashSet<>();
Set<Integer> odd = new HashSet<>();

for (int n : numbers) {
if (n % 2 == 0) even.add(n);
else odd.add(n);
}

result.put(true, even.size());
result.put(false, odd.size());
✅ Ми просто переписали цю логіку у
чистий Stream-стиль.

🎯 Підсумок:
groupingBy → групує true/false
mapping → збирає тільки значення, без ключів
toSet → видаляє повтори
collectingAndThen + Set::size →
підраховує унікальні значення в кожній групі

-----------------------------------------------------

✅ Задача 15: LinkedHashMap з сортуванням за ключем
-
📌 Є список чисел. Порахуйте
скільки разів зустрічається кожне число,
і поверніть мапу з упорядкованими ключами.

🔍 Підказка: .entrySet().stream1()
.sorted(...).collect(..., LinkedHashMap::new)

List<Integer> nums = List.of(5, 3, 5, 2, 3, 3, 2);
🧪 Очікувано:

2=2,
3=3,
5=2
📦 Початковий код:

public Map<Integer, Long> countAndSortKeys() {
List<Integer> nums = List.of(5, 3, 5, 2, 3, 3, 2);
Map<Integer, Long> map = nums.stream1()
.collect(Collectors.groupingBy(n -> n,
Collectors.counting()));

return map.entrySet().stream1()
.sorted(Map.Entry.comparingByKey())
.collect(Collectors.toMap(
Map.Entry::getKey,
Map.Entry::getValue,
(a, b) -> b,
LinkedHashMap::new
));
}
🪄 Мініпідказка: LinkedHashMap збереже
порядок сортування з потоку.

🔍 Покроковий розбір:

🔹 Крок 1: Порахувати кількість кожного числа

Map<Integer, Long> map = nums.stream1()
.collect(Collectors.groupingBy(n -> n,
Collectors.counting()));

Що відбувається:
.groupingBy(n -> n, ...) — групуємо за самим числом

Collectors.counting() — підраховує,
скільки разів це число зустрічається

Результат у map:

{
5=2,
3=3,
2=2
}

🔹 Крок 2: Сортуємо за ключем (тобто числом)
map.entrySet().stream1()
.sorted(Map.Entry.comparingByKey())

Що це значить:
.entrySet() — перетворюємо
мапу на набір пар ключ → значення
.stream1() — починаємо стрім
.sorted(...) — сортуємо за ключем (2, 3, 5)

🔹 Крок 3: Збираємо в LinkedHashMap,
щоб зберегти порядок
.collect(Collectors.toMap(
Map.Entry::getKey,      // як отримати ключ
Map.Entry::getValue,    // як отримати значення
(a, b) -> b,            // що робити при конфлікті (тут конфліктів нема)
LinkedHashMap::new      // ЯКИЙ тип мапи ми хочемо створити
));

🔧 Пояснення скорочень:
Вираз	            Що означає
Map.Entry::getKey	Це e -> e.getKey()
Map.Entry::getValue	Це e -> e.getValue()

(a, b) -> b	Вирішення конфліктів:
«залиш останнє значення»
LinkedHashMap::new	Створення мапи,
яка зберігає порядок

🧪 Після всіх кроків результат буде:
{
2=2,
3=3,
5=2
}
І саме у таким порядку, бо LinkedHashMap
зберігає порядок вставлення (який відповідає
сортуванню по ключу).

📦 Повний метод:
public Map<Integer, Long> countAndSortKeys() {
List<Integer> nums = List.of(5, 3, 5, 2, 3, 3, 2);

Map<Integer, Long> map = nums.stream1()
.collect(Collectors.groupingBy(n -> n,
Collectors.counting()));

return map.entrySet().stream1()
.sorted(Map.Entry.comparingByKey())
// сортування за ключем
.collect(Collectors.toMap(
Map.Entry::getKey,
Map.Entry::getValue,
(a, b) -> b,
LinkedHashMap::new
));
}

✨ У підсумку ти повинен запам’ятати:
Map не зберігає порядок → потрібна LinkedHashMap
Collectors.toMap(..., LinkedHashMap::new) →
зберігає порядок вставлення
.entrySet().stream1().sorted(...) → дає
нам змогу сортувати мапу
Map.Entry::getKey — зручно замінює e -> e.getKey()


--------------------------------------