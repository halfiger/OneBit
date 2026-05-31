⚙️ Модуль №13 — CompletableFuture Advanced Patterns (1–10 завдань)

📘 Мета:
навчитися застосовувати CompletableFuture у реальних сценаріях:
паралельна обробка колекцій, таймаути, помилки, кешування, каскадні ланцюги.

✅ Завдання 1 — Паралельна обробка списку (mapAsync)

📋 Умова: оброби список імен асинхронно.

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;

public class Task1 {
public static void main(String[] args) {
List<String> names = List.of("Alice", "Bob", "Charlie");

        List<CompletableFuture<String>> futures = names.stream()
            .map(name -> CompletableFuture.supplyAsync(() -> process(name)))
            .toList();

        List<String> results = futures.stream()
            .map(CompletableFuture::join)
            .toList();

        System.out.println(results);
    }

    static String process(String name) {
        try { Thread.sleep(300); } catch (InterruptedException ignored) {}
        return name.toUpperCase();
    }
}


📤 Вивід:

[ALICE, BOB, CHARLIE]


🧠 Три задачі працюють паралельно — і join() збирає всі результати.

✅ Завдання 2 — Обмеження паралельності через custom Executor

📋 Умова: обмеж кількість одночасних задач до 2.

ExecutorService pool = Executors.newFixedThreadPool(2);

List<String> items = List.of("A", "B", "C", "D");

List<CompletableFuture<Void>> futures = items.stream()
.map(i -> CompletableFuture.runAsync(() -> doWork(i), pool))
.toList();

CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

pool.shutdown();

static void doWork(String i) {
System.out.println(Thread.currentThread().getName() + " → " + i);
try { Thread.sleep(400); } catch (InterruptedException ignored) {}
}


📤 Вивід:

pool-1-thread-1 → A
pool-1-thread-2 → B
pool-1-thread-1 → C
pool-1-thread-2 → D


🧠 Використовуємо ExecutorService, щоб керувати рівнем паралельності.

✅ Завдання 3 — Обробка помилки в ланцюгу (exceptionally + handle)

📋 Умова: зімітуй помилку на одному етапі, але оброби її далі.

CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
if (true) throw new RuntimeException("Boom!");
return "OK";
}).handle((res, ex) -> {
if (ex != null) return "Recovered: " + ex.getMessage();
return res;
});

System.out.println(cf.join());


📤 Вивід:

Recovered: Boom!


🧠 handle() спрацьовує завжди — і для помилки, і для успіху.

✅ Завдання 4 — Таймаут: orTimeout()

📋 Умова: завдання має завершитися максимум за 1 секунду.

CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
return "Done!";
}).orTimeout(1, TimeUnit.SECONDS)
.exceptionally(ex -> "Timeout!");

System.out.println(cf.join());


📤 Вивід:

Timeout!


🧠 orTimeout() автоматично завершує задачу з TimeoutException.

✅ Завдання 5 — Таймаут із дефолтним значенням: completeOnTimeout()

📋 Умова: якщо задача занадто довга — підстав значення.

CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
try { Thread.sleep(2000); } catch (InterruptedException ignored) {}
return "OK";
}).completeOnTimeout("DEFAULT", 1, TimeUnit.SECONDS);

System.out.println(cf.join());


📤 Вивід:

DEFAULT


🧠 completeOnTimeout() не кидає виняток, а просто дає “план Б”.

✅ Завдання 6 — Каскадне обчислення (ланцюжок thenCompose)

📋 Умова: перший запит → потім другий на основі його результату.

CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "user42")
.thenCompose(u -> CompletableFuture.supplyAsync(() -> "Profile of " + u))
.thenCompose(p -> CompletableFuture.supplyAsync(() -> p + " [ok]"));

System.out.println(cf.join());


📤 Вивід:

Profile of user42 [ok]


🧠 thenCompose — “асинхронний flatMap” для послідовних залежних запитів.


✅ Завдання 7 — Комбінування різних джерел (thenCombine)

📋 Умова: паралельно отримай дані з 2 джерел і об’єднай.

CompletableFuture<String> user = CompletableFuture.supplyAsync(() -> "User: Alex");
CompletableFuture<String> orders = CompletableFuture.supplyAsync(() -> "Orders: 5");

String summary = user.thenCombine(orders, (u, o) -> u + " | " + o).join();

System.out.println(summary);


📤 Вивід:

User: Alex | Orders: 5

✅ Завдання 8 — Злиття кількох результатів у список

📋 Умова: зібрати результати 5 паралельних задач у колекцію.

List<CompletableFuture<String>> futures = IntStream.range(1, 6)
.mapToObj(i -> CompletableFuture.supplyAsync(() -> "Task " + i))
.toList();

List<String> results = futures.stream()
.map(CompletableFuture::join)
.toList();

System.out.println(results);


📤 Вивід:

[Task 1, Task 2, Task 3, Task 4, Task 5]


🧠 Це “колективне” очікування кількох задач, як у Promise.all() в JS.

✅ Завдання 9 — Комбіноване: асинхронний кеш результатів

📋 Умова: якщо дані вже є — поверни кеш, інакше обчисли.

Map<String, String> cache = new ConcurrentHashMap<>();

CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
return cache.computeIfAbsent("key1", k -> fetchFromServer());
});

System.out.println(cf.join());

static String fetchFromServer() {
System.out.println("Fetching...");
sleep(500);
return "DATA_FROM_SERVER";
}

static void sleep(long ms) {
try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
}


📤 Вивід:

Fetching...
DATA_FROM_SERVER


🧠 computeIfAbsent — класика для кешування результатів у потокобезпечному контексті.

✅ Завдання 10 — Реальний сценарій: асинхронний агрегаційний сервіс

📋 Умова: симулюй систему, що одночасно тягне користувача, історію, транзакції.

CompletableFuture<String> user = CompletableFuture.supplyAsync(() -> fetch("User"));
CompletableFuture<String> history = CompletableFuture.supplyAsync(() -> fetch("History"));
CompletableFuture<String> transactions = CompletableFuture.supplyAsync(() -> fetch("Transactions"));

CompletableFuture<Void> all = CompletableFuture.allOf(user, history, transactions);

all.thenRun(() -> {
String report = String.join(" | ", user.join(), history.join(), transactions.join());
System.out.println("Report: " + report);
}).join();

static String fetch(String type) {
try { Thread.sleep((long)(Math.random() * 700)); } catch (InterruptedException ignored) {}
System.out.println("Fetched " + type);
return type + "_DATA";
}


📤 Вивід (порядок випадковий):

Fetched Transactions  
Fetched User  
Fetched History  
Report: User_DATA | History_DATA | Transactions_DATA


🧠 Це вже готовий патерн “агрегаційного сервісу” у мікросервісній архітектурі.

💡 Підсумок
Концепція	                    Метод / Патерн	        Призначення
mapAsync	                    Паралельна обробка колекцій
Executor	                    Керування рівнем паралельності
handle / exceptionally	        Обробка помилок
orTimeout / completeOnTimeout	Таймаути
thenCompose	                    Послідовні async-залежності
thenCombine	                    Об’єднання незалежних async-запитів
allOf / anyOf	                Координація груп
computeIfAbsent	                Потокобезпечне кешування

🧠 Після цього модуля ти володієш практичними асинхронними патернами на рівні Spring WebFlux, Reactor і продакшн-рішень Java EE.