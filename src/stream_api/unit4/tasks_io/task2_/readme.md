🧠 Коротка теорія: Path + Files
Основні класи:
Path — шлях до файлу (Paths.get(...))
Files — утилітний клас (тільки static-методи)

Найуживаніші методи:
Files.exists(path)
Files.createFile(path)
Files.createDirectories(path)
Files.write(path, data)
Files.readAllLines(path)
Files.lines(path)
Files.delete(path)
Files.copy(...), Files.move(...)

📌 Важливо:
Files працює швидше, чистіше і краще для реальних проєктів, ніж старий IO.

📘 Серія завдань java.nio.file.Files
## 🟢 Завдання 1 — Створити Path
Умова:
Створи шлях до файлу data/nio/example.txt

💡 Підказка
Path path = Paths.get("data/nio/example.txt");
✅ Рішення
import java.nio.file.Path;
import java.nio.file.Paths;

public class Task01 {
public static void main(String[] args) {
Path path = Paths.get("data/nio/example.txt");
System.out.println(path);
}
}
## 🟢 Завдання 2 — Створити папки
Умова:
Створи всі відсутні папки для цього файлу.

💡 Підказка
path.getParent()

✅ Рішення
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class Task02 {
public static void main(String[] args) throws IOException {
Path path = Paths.get("data/nio/example.txt");
Files.createDirectories(path.getParent());
}
}
📎 Нюанс:
createDirectories() не падає, якщо папки вже існують.

# 🟢 Завдання 3 — Створити файл без помилки
Умова:
Створи файл тільки якщо його ще нема.

💡 Підказка
Files.exists()

✅ Рішення
Path path = Paths.get("data/nio/example.txt");

if (Files.notExists(path)) {
Files.createFile(path);
}
# ✍️ Завдання 4 — Записати текст у файл
Умова:
Запиши рядок "Hello NIO".

💡 Підказка
Files.write()

✅ Рішення
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

public class Task04 {
public static void main(String[] args) throws IOException {
Path path = Paths.get("data/nio/example.txt");
Files.write(path, List.of("Hello NIO"));
}
}
⚠️ Важливо:
Files.write() перезаписує файл.

# ➕ Завдання 5 — Додати текст у кінець файлу
Умова:
Додай "Second line".

💡 Підказка
StandardOpenOption.APPEND

✅ Рішення
import java.nio.file.StandardOpenOption;

Files.write(
path,
List.of("Second line"),
StandardOpenOption.APPEND
);

# 📖 Завдання 6 — Прочитати всі рядки
Умова:
Виведи файл у консоль.

💡 Підказка
Files.readAllLines()

✅ Рішення
List<String> lines = Files.readAllLines(path);

lines.forEach(System.out::println);
⚠️ Нюанс:
Не використовуй для великих файлів.

# 🌊 Завдання 7 — Читання через Stream
Умова:
Прочитай файл через Stream.

💡 Підказка
Files.lines()

✅ Рішення
try (var stream = Files.lines(path)) {
stream.forEach(System.out::println);
}
📌 Плюс:
Працює потоково → добре для великих файлів.

# 🔢 Завдання 8 — Обробка даних
Умова:
У файлі числа. Порахуй суму.

💡 Підказка
mapToInt(Integer::parseInt)

✅ Рішення
int sum;

try (var stream = Files.lines(path)) {
sum = stream
.map(String::trim)
.mapToInt(Integer::parseInt)
.sum();
}

System.out.println(sum);

🛡️ Завдання 9 — Ігнорувати некоректні рядки
Умова:
Пропускай рядки, які не є числами.

✅ Рішення
try (var stream = Files.lines(path)) {
int sum = stream
.map(String::trim)
.filter(s -> s.matches("-?\\d+"))
.mapToInt(Integer::parseInt)
.sum();

    System.out.println(sum);
}
📎 Нюанс:
Регулярки — нормальна практика для чистки даних.

#🧹 Завдання 10 — Видалити файл без падіння
Умова:
Видали файл, якщо він існує.

💡 Підказка
Files.deleteIfExists()

✅ Рішення
Files.deleteIfExists(path);
⚠️ Важливі речі, які треба пам’ятати
❗ 1. Завжди закривай Files.lines()
try (Stream<String> s = Files.lines(path)) { ... }
❗ 2. readAllLines() — тільки для малих файлів
❗ 3. Files.write() → overwrite за замовчуванням
❗ 4. Path краще ніж File (portable, modern)
🎯 Результат
Після цієї серії ти:

вмієш читати / писати / обробляти файли

використовуєш Stream API + Files

готовий до логів, CSV, конфігів, імпорту даних

на 100% готовий до реальних задач