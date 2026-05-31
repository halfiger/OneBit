Files.copy / move / backup — 10 завдань, від простого до “майже продакшен”.

🧠 Коротка теорія (перед стартом)
Основні методи:
Files.copy(source, target, options…)

Files.move(source, target, options…)
Files.delete() / deleteIfExists()
Files.exists()

StandardCopyOption.REPLACE_EXISTING

StandardCopyOption.COPY_ATTRIBUTES

📌 Важливо знати:

copy не створює папки
move може бути rename
без REPLACE_EXISTING буде FileAlreadyExistsException

📦 Серія завдань: Copy / Move / Backup
# 🟢 Завдання 1 — Просте копіювання файлу
Умова:
Скопіюй data/source.txt у data/copy.txt.

💡 Підказка
Files.copy()

✅ Рішення
Path source = Paths.get("data/source.txt");
Path target = Paths.get("data/copy.txt");

Files.copy(source, target);

# 🟢 Завдання 2 — Копіювання з перезаписом
Умова:
Якщо файл вже існує — перезаписати.

💡 Підказка
StandardCopyOption.REPLACE_EXISTING

✅ Рішення
Files.copy(
source,
target,
StandardCopyOption.REPLACE_EXISTING
);

# 🟢 Завдання 3 — Копіювання з перевірками
Умова:
Не копіювати, якщо source не існує.

💡 Підказка
Files.exists()

✅ Рішення
if (!Files.exists(source)) {
System.out.println("Джерело не знайдено");
return;
}

Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

# 🟡 Завдання 4 — Копіювання у нову папку
Умова:
Скопіюй файл у папку backup/, створивши її автоматично.

💡 Підказка
Files.createDirectories()

target.getParent()

✅ Рішення
Path target = Paths.get("backup/source.txt");

Files.createDirectories(target.getParent());
Files.copy(source, target, 
StandardCopyOption.REPLACE_EXISTING);

# 🟡 Завдання 5 — Move (переміщення файлу)
Умова:
Перемісти файл у папку archive/.

💡 Підказка
Files.move()

✅ Рішення
Path target = Paths.get("archive/source.txt");

Files.createDirectories(target.getParent());

Files.move(
source,
target,
StandardCopyOption.REPLACE_EXISTING
);
📎 Факт:
move() в межах одного диска — це майже миттєвий rename.

# 🟡 Завдання 6 — Перейменування файлу
Умова:
Перейменуй report.txt у report_old.txt.

💡 Підказка
move() + новий Path

✅ Рішення
Path oldName = Paths.get("data/report.txt");
Path newName = Paths.get("data/report_old.txt");

Files.move(
oldName,
newName,
StandardCopyOption.REPLACE_EXISTING
);

# 🔵 Завдання 7 — Простий бекап
Умова:
Зроби копію файлу з суфіксом .bak

💡 Підказка
getFileName()

resolveSibling()

✅ Рішення
Path source = Paths.get("data/config.txt");

Path backup = source.resolveSibling(
source.getFileName() + ".bak"
);

Files.copy(
source,
backup,
StandardCopyOption.REPLACE_EXISTING
);

# 🔵 Завдання 8 — Бекап з датою
Умова:
Створи бекап з поточною датою у назві.

💡 Підказка
LocalDateTime

DateTimeFormatter

✅ Рішення
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

String timestamp = LocalDateTime.now()
.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

Path backup = source.resolveSibling(
source.getFileName() + "_" + timestamp + ".bak"
);

Files.copy(source, backup);

# 🔴 Завдання 9 — Масовий бекап папки
Умова:
Скопіюй всі файли з data/ у backup/.

💡 Підказка
Files.list()

✅ Рішення
Path sourceDir = Paths.get("data");
Path backupDir = Paths.get("backup");

Files.createDirectories(backupDir);

try (var stream = Files.list(sourceDir)) {
stream
.filter(Files::isRegularFile)
.forEach(path -> {
try {
Files.copy(
path,
backupDir.resolve(path.getFileName()),
StandardCopyOption.REPLACE_EXISTING
);
} catch (Exception e) {
System.out.println("Помилка: " + path);
}
});
}
🔴 Завдання 10 — Безпечний backup (atomic)
Умова:
Спочатку скопіювати у .tmp, потім перейменувати.

💡 Навіщо?
Щоб не зламати файл при збої.

✅ Рішення
Path tmp = Paths.get("backup/config.tmp");
Path backup = Paths.get("backup/config.bak");

Files.copy(source, tmp, StandardCopyOption.REPLACE_EXISTING);
Files.move(
tmp,
backup,
StandardCopyOption.REPLACE_EXISTING,
StandardCopyOption.ATOMIC_MOVE
);
⚠️ Реальні нюанси, які питають на співбесідах
copy ≠ move

move може бути rename

ATOMIC_MOVE не всюди підтримується

папки не копіюються автоматично

Files.walk() — для рекурсії

🎯 Після цієї серії ти реально вмієш:
робити backup-и

переміщати дані безпечно

працювати з файловою системою як з ресурсом

писати утиліти рівня junior+/middle

