🔹 Частина 1 — Класичний switch (Java 8 style)

✅ Завдання 1
Питання:
Є число (1–3). Вивести день:
1 → "Monday", 2 → "Tuesday", 3 → "Wednesday"
Підказка:
case 1: → System.out.println(...)
Теорія:
switch працює з int, char, String, enum. Потрібен break, щоб не було fall-through.
Рішення:
int day = 2;

switch (day) {
case 1:
System.out.println("Monday");
break;
case 2:
System.out.println("Tuesday");
break;
case 3:
System.out.println("Wednesday");
break;
default:
System.out.println("Unknown");
}

✅ Завдання 2
Питання:
Є char grade = 'A'. Вивести:
A → "Excellent", B → "Good", C → "Average"
Підказка:
switch працює з char
Теорія:
Тип char — це просто число Unicode, тому switch його підтримує.
Рішення:
char grade = 'A';

switch (grade) {
case 'A':
System.out.println("Excellent");
break;
case 'B':
System.out.println("Good");
break;
case 'C':
System.out.println("Average");
break;
default:
System.out.println("Fail");
}



✅ Завдання 3
Питання:
Є String role = "ADMIN" → вивести права
Підказка:
case "ADMIN":
Теорія:
З Java 7 можна використовувати String у switch.
Рішення:
String role = "ADMIN";

switch (role) {
case "ADMIN":
System.out.println("Full access");
break;
case "USER":
System.out.println("Limited access");
break;
default:
System.out.println("Guest");
}






✅ Завдання 4
Питання:
Є число 1–7 → день тижня
Підказка:
7 кейсів, не забудь default
Теорія:
default — виконується, якщо жоден case не підійшов.
Рішення:
int d = 7;

switch (d) {
case 1: System.out.println("Mon"); break;
case 2: System.out.println("Tue"); break;
case 3: System.out.println("Wed"); break;
case 4: System.out.println("Thu"); break;
case 5: System.out.println("Fri"); break;
case 6: System.out.println("Sat"); break;
case 7: System.out.println("Sun"); break;
default: System.out.println("Invalid");
}






✅ Завдання 5
Питання:
Зроби fall-through:
1,2,3 → "Low", 4,5 → "Medium", 6 → "High"
Підказка:
кілька case без break
Теорія:
Fall-through — коли кейси "провалюються" один в один без break.
Рішення:
int level = 2;

switch (level) {
case 1:
case 2:
case 3:
System.out.println("Low");
break;
case 4:
case 5:
System.out.println("Medium");
break;
case 6:
System.out.println("High");
break;
}



✅ Завдання 6
Питання:
Є month = 1,2,12 → вивести "Winter"
Підказка:
згрупуй місяці
Теорія:
Можна об’єднувати кейси.
Рішення:
int month = 2;

switch (month) {
case 12:
case 1:
case 2:
System.out.println("Winter");
break;
}







✅ Завдання 7
Питання:
Перевірити парність числа через switch (0/1)
Підказка:
number % 2
Теорія:
switch працює з результатом виразу.
Рішення:
int number = 5;

switch (number % 2) {
case 0:
System.out.println("Even");
break;
case 1:
System.out.println("Odd");
break;
}







✅ Завдання 8
Питання:
Валюта: "USD", "EUR", "UAH"
Підказка:
String switch
Теорія:
String порівнюється через equals всередині switch.
Рішення:
String currency = "USD";

switch (currency) {
case "USD":
System.out.println("Dollar");
break;
case "EUR":
System.out.println("Euro");
break;
case "UAH":
System.out.println("Hryvnia");
break;
}





✅ Завдання 9
Питання:
Калькулятор: +, -, *
Підказка:
char operator
Теорія:
switch зручний для меню/операцій.
Рішення:
int a = 5, b = 3;
char op = '+';

switch (op) {
case '+':
System.out.println(a + b);
break;
case '-':
System.out.println(a - b);
break;
case '*':
System.out.println(a * b);
break;
}





✅ Завдання 10
Питання:
Визначити голосну: a,e,i,o,u
Підказка:
об’єднати case
Теорія:
Кілька case → одна дія.
Рішення:
char c = 'a';

switch (c) {
case 'a':
case 'e':
case 'i':
case 'o':
case 'u':
System.out.println("Vowel");
break;
default:
System.out.println("Consonant");
}




🔹 Частина 2 — Новий switch (Java 14+)

✅ Завдання 11
Питання:
Те саме що №1, але повернути значення в змінну
Підказка:
->
Теорія:
switch став expression і може повертати значення.
Рішення:
int day = 2;

String result = switch (day) {
case 1 -> "Monday";
case 2 -> "Tuesday";
case 3 -> "Wednesday";
default -> "Unknown";
};





✅ Завдання 12
Питання:
Згрупувати 1,2,3 → "Low"
Підказка:
через кому
Теорія:
case 1,2,3 ->
Рішення:
int n = 2;

String res = switch (n) {
case 1,2,3 -> "Low";
case 4,5 -> "Medium";
default -> "High";
};








✅ Завдання 13
Питання:
Роль → доступ
Теорія:
без break, без fall-through
Рішення:
String role = "USER";

String access = switch (role) {
case "ADMIN" -> "Full";
case "USER" -> "Limited";
default -> "Guest";
};









✅ Завдання 14
Питання:
Калькулятор (+, -)
Підказка:
повертай int
Рішення:
int a = 5, b = 2;
char op = '+';

int result = switch (op) {
case '+' -> a + b;
case '-' -> a - b;
default -> 0;
};









✅ Завдання 15
Питання:
Якщо потрібно більше логіки
Підказка:
yield
Теорія:
блок {} + yield
Рішення:
int x = 10;

String res = switch (x) {
case 10 -> {
System.out.println("Debug");
yield "Ten";
}
default -> "Other";
};







✅ Завдання 16
Питання:
Парне/непарне
Рішення:
int n = 7;

String res = switch (n % 2) {
case 0 -> "Even";
case 1 -> "Odd";
default -> "Error";
};










✅ Завдання 17
Питання:
Місяці → сезон
Рішення:
int m = 12;

String season = switch (m) {
case 12,1,2 -> "Winter";
case 3,4,5 -> "Spring";
case 6,7,8 -> "Summer";
case 9,10,11 -> "Autumn";
default -> "Invalid";
};










✅ Завдання 18
Питання:
Символ → тип
Рішення:
char c = '+';

String type = switch (c) {
case '+','-','*','/' -> "Operator";
default -> "Other";
};











✅ Завдання 19
Питання:
String → довжина категорії
Рішення:
String s = "Hi";

String res = switch (s.length()) {
case 1 -> "Short";
case 2,3 -> "Medium";
default -> "Long";
};










✅ Завдання 20
Питання:
null-safe варіант (перевірка перед switch)
Теорія:
switch не любить null
Рішення:
String str = null;

String res = (str == null) ? "Null" : switch (str) {
case "A" -> "Letter A";
default -> "Other";
};







🔥 Далі 10 мікрозавдань — Random + альтернативи

✅ Завдання 1
Питання:
Згенеруй випадкове число від 0 до 9
Підказка:
nextInt(10)
Теорія:
Random.nextInt(bound) → [0, bound)
Рішення:
import java.util.Random;

Random random = new Random();
int n = random.nextInt(10);

System.out.println(n);






✅ Завдання 2
Питання:
Згенеруй число від 1 до 10
Підказка:
додати +1
Теорія:
[0, 9] + 1 → [1, 10]
Рішення:
Random r = new Random();
int n = r.nextInt(10) + 1;

System.out.println(n);










✅ Завдання 3
Питання:
Згенеруй число від 5 до 15
Підказка:
діапазон = max - min + 1
Теорія:
формула:
min + random.nextInt(max - min + 1)
Рішення:
Random r = new Random();

int min = 5;
int max = 15;

int n = min + r.nextInt(max - min + 1);
System.out.println(n);







✅ Завдання 4
Питання:
Згенеруй випадковий boolean
Підказка:
є метод
Теорія:
nextBoolean() → true/false
Рішення:
Random r = new Random();

boolean value = r.nextBoolean();
System.out.println(value);









✅ Завдання 5
Питання:
Згенеруй число від 0.0 до 1.0
Підказка:
nextDouble()
Теорія:
double → [0.0, 1.0)
Рішення:
Random r = new Random();

double d = r.nextDouble();
System.out.println(d);









✅ Завдання 6
Питання:
Згенеруй double від 5.0 до 10.0
Підказка:
масштабування
Теорія:
min + random * (max - min)
Рішення:
Random r = new Random();

double min = 5.0;
double max = 10.0;

double d = min + r.nextDouble() * (max - min);
System.out.println(d);








✅ Завдання 7
Питання:
Згенеруй випадковий символ a–z
Підказка:
каст до char
Теорія:
'a' + число → символ
Рішення:
Random r = new Random();

char c = (char) ('a' + r.nextInt(26));
System.out.println(c);










✅ Завдання 8
Питання:
Згенеруй пароль довжиною 5 символів (a-z)
Підказка:
цикл + StringBuilder
Теорія:
використовуй генерацію символів у циклі
Рішення:
Random r = new Random();
StringBuilder sb = new StringBuilder();

for (int i = 0; i < 5; i++) {
char c = (char) ('a' + r.nextInt(26));
sb.append(c);
}

System.out.println(sb.toString());







✅ Завдання 9
Питання:
Використай альтернативу — Math.random()
Підказка:
помножити
Теорія:
Math.random() → [0.0, 1.0)
Рішення:
int n = (int) (Math.random() * 10);

System.out.println(n);










✅ Завдання 10
Питання:
Використай сучасний варіант — ThreadLocalRandom
Підказка:
без new
Теорія:
кращий для багатопоточності
Рішення:
import java.util.concurrent.ThreadLocalRandom;

int n = ThreadLocalRandom.current().nextInt(1, 11);

System.out.println(n);








🧩 Тепер практика (10 мікрозавдань)

✅ Завдання 1
Питання:
Створи інтерфейс Animal з методом makeSound() (abstract)
Підказка:
без default
Теорія:
Методи в інтерфейсі за замовчуванням — public abstract
Рішення:
interface Animal {
void makeSound();
}






✅ Завдання 2
Питання:
Реалізуй Animal у класі Dog
Підказка:
implements
Рішення:
class Dog implements Animal {
@Override
public void makeSound() {
System.out.println("Woof");
}
}










✅ Завдання 3
Питання:
Додай default метод sleep() в Animal
Підказка:
default
Теорія:
default метод має тіло
Рішення:
interface Animal {
void makeSound();

default void sleep() {
System.out.println("Sleeping...");
}
}








✅ Завдання 4
Питання:
Виклич sleep() у Dog без override
Підказка:
об’єкт класу
Рішення:
Dog d = new Dog();
d.sleep();












✅ Завдання 5
Питання:
Перевизнач sleep() у Dog
Підказка:
override default
Теорія:
default метод можна override
Рішення:
@Override
public void sleep() {
System.out.println("Dog sleeping");
}









✅ Завдання 6
Питання:
Створи interface Pet extends Animal
Підказка:
extends
Теорія:
інтерфейси наслідують інтерфейси
Рішення:
interface Pet extends Animal {
}










✅ Завдання 7
Питання:
Створи клас Cat implements Pet і виклич sleep()
Підказка:
нічого не реалізовуй
Рішення:
class Cat implements Pet {
public void makeSound() {
System.out.println("Meow");
}
}
👉 sleep() працює автоматично









✅ Завдання 8
Питання:
Додай в Pet новий default метод play()
Підказка:
ще один default
Рішення:
interface Pet extends Animal {
default void play() {
System.out.println("Playing");
}
}










✅ Завдання 9 (ВАЖЛИВЕ ⚠️)
Питання:
Створи 2 інтерфейси з однаковим default методом
Підказка:
конфлікт
Теорія:
Java не знає який метод брати
Рішення:
interface A {
default void hello() {
System.out.println("A");
}
}

interface B {
default void hello() {
System.out.println("B");
}
}

class Test implements A, B {
@Override
public void hello() {
System.out.println("Resolved");
}
}



✅ Завдання 10 (рівень інтерв’ю)
Питання:
Як викликати конкретний default метод?
Підказка:
InterfaceName.super.method()
Рішення:
class Test implements A, B {
@Override
public void hello() {
A.super.hello();
B.super.hello();
}




















}

🔥 Контрольні питання (перевір себе)
Чи потрібно реалізовувати default метод?


Що буде, якщо 2 інтерфейси мають однаковий default метод?


Чи можна викликати default метод конкретного інтерфейсу?


Чи передається default метод через extends?



👉 Як правильно це відпрацювати
Не роби помилку:
❌ просто прочитати
❌ просто подивитись код
✔️ створи 1 файл і руками напиши всі 10 кейсів
✔️ спеціально зламай код (додай конфлікт)
✔️ подивись помилки компілятора

Коли зробиш — скажи. Далі я дам:
interface + static methods


або interface vs abstract class (дуже важливо для співбесіди)
