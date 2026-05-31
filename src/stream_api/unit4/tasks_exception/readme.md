# ✅ Завдання 1 — Ділення на нуль

Написати метод divide(int a, int b), 
який повертає результат ділення, 
але кидає виняток, якщо b == 0.

🔍 Підказки

Використай if (b == 0) throw new ArithmeticException
("Ділення на нуль!");

Пам’ятай, що ArithmeticException — unchecked.

🧩 Рішення
public static int divide(int a, int b) {
if (b == 0) {
throw new ArithmeticException("Ділення на нуль!");
}
return a / b;
}

📘 Теорія

ArithmeticException — unchecked виняток.
Його не потрібно оголошувати в сигнатурі методу (throws).
Його зазвичай кидають вручну при помилках у математичних
операціях.

# ✅ Завдання 2 — Перевірка рядка

Написати метод printLength(String text),
який друкує довжину рядка,
але якщо він null — кидає IllegalArgumentException.

🔍 Підказки

Будь уважний: text.length() викличе NullPointerException,
але нам треба кинути свій виняток.

🧩 Рішення
public static void printLength(String text) {
if (text == null) {
throw new IllegalArgumentException("Рядок не може бути null");
}
System.out.println("Length = " + text.length());
}

📘 Теорія

IllegalArgumentException використовують, 
коли аргумент методу некоректний.
Це кращий варіант, ніж просто дозволити програмі 
впасти з NullPointerException.

# ✅ Завдання 3 — Перевірка віку

Створити метод setAge(int age), який:

приймає лише значення від 1 до 120;

у всіх інших випадках кидає Exception із повідомленням.

🔍 Підказки

На відміну від попередніх, цього разу використай
checked exception, тобто throws Exception.

🧩 Рішення
public static void setAge(int age) throws Exception {
if (age < 1 || age > 120) {
throw new Exception("Некоректний вік: " + age);
}
System.out.println("Вік встановлено: " + age);
}

📘 Теорія

Exception (або будь-який його нащадок, 
крім RuntimeException) — checked,
тобто метод зобов’язаний оголосити throws, 
а виклик — обгорнути в try-catch.

# ✅ Завдання 4 — Читання з масиву

Створити метод getElement(int[] arr, int index), який:

повертає елемент масиву;

кидає IndexOutOfBoundsException із власним повідомленням,
якщо index виходить за межі.

🔍 Підказки

Не використовуй стандартну помилку: 
зроби своє повідомлення.

🧩 Рішення
public static int getElement(int[] arr, int index) {
if (index < 0 || index >= arr.length) {
throw new IndexOutOfBoundsException(
"Індекс поза межами масиву: " + index);
}
return arr[index];
}

📘 Теорія

IndexOutOfBoundsException — unchecked.
Його часто використовують у власних перевірках 
під час роботи з масивами, списками, рядками.

✅ Завдання 5 — Кастомний виняток

Створити свій виняток InvalidPasswordException
і метод checkPassword(String pass), який:

приймає пароль;

якщо довжина < 6 — кидає цей виняток.

🔍 Підказки

Створи клас:

public class InvalidPasswordException extends Exception {
public InvalidPasswordException(String msg) {
super(msg);
}
}


Метод має мати throws InvalidPasswordException.

🧩 Рішення
public static void checkPassword(String pass) throws InvalidPasswordException {
if (pass == null || pass.length() < 6) {
throw new InvalidPasswordException("Пароль занадто короткий");
}
System.out.println("Пароль прийнято");
}

📘 Теорія

Створення кастомних винятків — це спосіб зробити 
програми читабельними та зрозумілими.
Так легше відслідковувати, який саме тип помилки стався.


✅ Завдання 6 — try-with-resources та автоматичне закриття

Створити метод readFirstLine(String filePath), який:

☑️відкриває файл,
☑️читає перший рядок,
☑️використовує try-with-resources,
☑️коректно обробляє IOException.

🔍 Підказки

Використай BufferedReader.
try-with-resources автоматично закриває потік.
Пам’ятай: readLine() може повернути null.

🧩 Рішення
public static String readFirstLine(String filePath) {
try (BufferedReader br = new BufferedReader(
new FileReader(filePath))) {
return br.readLine();
} catch (IOException e) {
System.out.println("Помилка читання файлу: " + e.getMessage());
return null;
}
}

📘 Теорія

try-with-resources працює з об’єктами, 
які реалізують AutoCloseable,
і закриває їх автоматично, 
навіть якщо виникла помилка.

✅ Завдання 7 — Multi-Catch (кілька винятків 
в одному блоці)
Створити метод parseAndDivide(String a, String b),
який:
перетворює рядки у числа,
ділить їх,
обробляє два винятки в одному catch:
NumberFormatException
ArithmeticException

🔍 Підказки
Можна писати catch (NumberFormatException |
ArithmeticException e).
Використай Integer.parseInt().

🧩 Рішення
public static int parseAndDivide(String a, String b) {
try {
int x = Integer.parseInt(a);
int y = Integer.parseInt(b);
return x / y;
} catch (NumberFormatException || ArithmeticException e) {
System.out.println("Помилка: " + e.getMessage());
return 0;
}
}

📘 Теорія

Multi-catch дозволяє об’єднати кілька видів помилок, 
якщо реакція на них однакова.
Зручно для скорочення дублювання коду.

✅ Завдання 8 — Проброс (передача) винятку наверх

Створити метод loadConfig(String file), який:

намагається прочитати файл,
але не обробляє помилку сам,
а передає її вгору (throws IOException),
викликається з іншого методу,
який уже робить try-catch.

🔍 Підказки

Метод A → кидає IOException
Метод B → викликає A в try-catch
Покажи повідомлення про помилку у методі B

🧩 Рішення
public static String loadConfig(String file) throws IOException {
return new String(Files.readAllBytes(Paths.get(file)));
}

public static void run() {
try {
String data = loadConfig("config.txt");
System.out.println(data);
} catch (IOException e) {
System.out.println("Помилка завантаження конфігурації: "
+ e.getMessage());
}
}

📘 Теорія

Так працює нормальна архітектура:
нижній рівень кидає, а верхній — вирішує, 
що з цим робити.

# ✅ Завдання 9 — Створення вкладених 
# (cause) винятків

Створити метод process(String number), який:
намагається перетворити рядок у число,
якщо не виходить — кидає новий Exception, 
але збережи початковий через 
throw new Exception("...", e).

🔍 Підказки

Використай try { ... } catch (NumberFormatException e) { ... }
Передай e як "cause".

🧩 Рішення
public static int process(String number) throws Exception {
try {
return Integer.parseInt(number);
} catch (NumberFormatException e) {
throw new Exception("Не вдалося обробити число", e);
}
}

📘 Теорія

Внутрішній виняток (cause) дозволяє зберегти повну історію помилки —
це називається exception chaining.

✅ Завдання 10 — Кілька рівнів обробки (перехоплення та повторне кидання)

Створити три методи:
☑️level1() → кидає IOException
☑️level2() → викликає level1, але:

ловить
логує
і кидає свій виняток, наприклад RuntimeException

level3() → викликає level2 і обробляє кінцевий виняток

🔍 Підказки

Це моделює реальну структуру: DAO → Service → Controller.

🧩 Рішення
public static void level1() throws IOException {
throw new IOException("Файл недоступний");
}

public static void level2() {
try {
level1();
} catch (IOException e) {
System.out.println("LOG: level2 перехопив IOException");
throw new RuntimeException("Проблема на рівні сервісу", e);
}
}

public static void level3() {
try {
level2();
} catch (RuntimeException e) {
System.out.println("Фінальна помилка: " + e.getMessage());
}
}

📘 Теорія

Це демонструє повний цикл:

нижній рівень кидає checked виключення,

середній рівень "перекладає" його у свій format,

верхній — уже вирішує, як реагувати.

🎯 Хочеш наступний набір?

Можу продовжити у будь-якому напрямі:

класичні exception patterns у Java (fail-fast, fail-safe)

throw + Optional (м'які помилки)

створення ієрархії власних винятків

реальні завдання з міні-проектів (текстові файли, JSON, валідація даних)

Скажи, який сет хочеш далі?

https://chatgpt.com/g/g-p-68e8fbae40bc819189c46e34e72bb031-java-mentor/c/692ff80c-28e0-8330-9f1a-2491dde5dfcd