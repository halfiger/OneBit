Тоді готуємо Модуль №12 — CompletableFuture Deep Dive 🧠

Це — наступний рівень після потоків і паралельних стрімів.
Якщо у потоках ми “створювали і керували руками”,
то тут Java робить усе асинхронно і декларативно — 
ти лише описуєш що робити після чого, 
і воно виконується у фонових потоках.

⚙️ Модуль №12 — CompletableFuture Deep Dive (1–10 завдань)

📘 Мета:
🔹 навчитися створювати, комбінувати, обробляти та синхронізувати асинхронні задачі без блокувань;
🔹 зрозуміти різницю між thenApply, thenCompose, thenCombine, allOf, anyOf, exceptionally, join і get.

✅ Завдання 1 — supplyAsync() базовий приклад

📋 Умова: створити асинхронне завдання, що повертає результат.

import java.util.concurrent.*;

public class Task1 {
public static void main(String[] args) throws Exception {
CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
sleep(500);
return "Hello, Async World!";
});

        System.out.println("Result: " + cf.get());
    }

    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}


📤 Вивід:

Result: Hello, Async World!


🧠 supplyAsync() створює задачу в ForkJoinPool.commonPool()
get() блокує головний потік, доки результат не готовий.

✅ Завдання 2 — thenApply(): трансформація результату

📋 Умова: додай обробку після завершення попередньої задачі.

CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "Java")
.thenApply(s -> s + " Streams")
.thenApply(String::toUpperCase);

System.out.println(cf.get()); // JAVA STREAMS


🧠 thenApply() виконується послідовно, після завершення попереднього етапу,
але у тому ж потоці ForkJoinPool — без блокування.

✅ Завдання 3 — thenAccept(): споживач результату

📋 Умова: виведи результат без повернення нового значення.

CompletableFuture.supplyAsync(() -> "Async printing")
.thenAccept(System.out::println)
.join();


📤 Вивід:

Async printing


🧠 thenAccept() — як forEach для CompletableFuture:
виконує дію з результатом, але не повертає нічого (void).

✅ Завдання 4 — thenRun(): код без результату

📋 Умова: просто виконай дію після завершення завдання.

CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
sleep(300);
System.out.println("Background task finished.");
}).thenRun(() ->
System.out.println("All done.")
);

cf.join();


📤 Вивід:

Background task finished.
All done.


🧠 runAsync() — коли не треба результат, а лише дія.
thenRun() — запускає нову дію після завершення.

✅ Завдання 5 — thenCombine(): об’єднання двох задач

📋 Умова: обчисли суму результатів двох асинхронних задач.

CompletableFuture<Integer> a = CompletableFuture.supplyAsync(() -> 10);
CompletableFuture<Integer> b = CompletableFuture.supplyAsync(() -> 20);

CompletableFuture<Integer> sum = a.thenCombine(b, Integer::sum);

System.out.println(sum.get()); // 30


🧠 thenCombine() — об’єднує два CompletableFuture після завершення обох.

✅ Завдання 6 — thenCompose(): ланцюжок асинхронних задач

📋 Умова: створити другу асинхронну задачу, що залежить від результату першої.

CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "User123")
.thenCompose(user ->
CompletableFuture.supplyAsync(() -> "Profile for " + user)
);

System.out.println(cf.join()); // Profile for User123


🧠 thenCompose() — плоский ланцюжок.
Він "розпаковує" вкладений CompletableFuture і не створює CompletableFuture<CompletableFuture<T>>.

✅ Завдання 7 — exceptionally(): обробка помилки

📋 Умова: зімітуй виняток і перехопи його.

CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
if (true) throw new RuntimeException("Error!");
return 42;
}).exceptionally(ex -> {
System.out.println("Caught: " + ex.getMessage());
return 0;
});

System.out.println("Result = " + cf.join());


📤 Вивід:

Caught: Error!
Result = 0


🧠 exceptionally() — дозволяє “підхопити” помилку та продовжити з дефолтним значенням.

✅ Завдання 8 — allOf(): чекати кілька задач

📋 Умова: виконай 3 завдання і дочекайся всіх.

CompletableFuture<Void> all = CompletableFuture.allOf(
CompletableFuture.runAsync(() -> sleepAndPrint("A", 400)),
CompletableFuture.runAsync(() -> sleepAndPrint("B", 200)),
CompletableFuture.runAsync(() -> sleepAndPrint("C", 100))
);

all.join();
System.out.println("All completed!");

static void sleepAndPrint(String name, long ms) {
try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
System.out.println("Done " + name);
}


📤 Вивід (порядок може змінюватись):

Done C
Done B
Done A
All completed!


🧠 allOf() завершується, коли всі CompletableFuture завершені.

✅ Завдання 9 — anyOf(): результат першої завершеної задачі

📋 Умова: вибери найшвидше завдання.

CompletableFuture<Object> fastest = CompletableFuture.anyOf(
CompletableFuture.supplyAsync(() -> {
sleep(300);
return "Task A";
}),
CompletableFuture.supplyAsync(() -> {
sleep(100);
return "Task B";
})
);

System.out.println(fastest.join()); // Task B


🧠 anyOf() повертає результат першої завершеної задачі.

✅ Завдання 10 — Комбінований приклад: API-запити

📋 Умова: симулюй три паралельні запити та збери результати в один.

CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> fetch("users"));
CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> fetch("orders"));
CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> fetch("payments"));

CompletableFuture<Void> all = CompletableFuture.allOf(f1, f2, f3);

all.thenRun(() -> {
String combined = f1.join() + ", " + f2.join() + ", " + f3.join();
System.out.println("Combined: " + combined);
}).join();

static String fetch(String name) {
sleep((int)(Math.random() * 500));
System.out.println("Fetched " + name);
return name.toUpperCase();
}

static void sleep(long ms) {
try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
}


📤 Вивід (порядок випадковий):

Fetched payments  
Fetched orders  
Fetched users  
Combined: USERS, ORDERS, PAYMENTS


🧠 Це типова реальна ситуація: паралельні API-запити → збір результатів → обробка після всіх.

🧩 Підсумок модуля
Метод	Опис
supplyAsync()	створює асинхронну задачу з результатом
thenApply()	трансформація результату
thenAccept()	дія без повернення
thenRun()	код після завершення
thenCombine()	комбінує дві задачі
thenCompose()	ланцюжить залежні задачі
exceptionally()	обробка помилок
allOf()	очікування всіх задач
anyOf()	перша завершена задача
join()	отримає результат без обробки винятку

🧠 Після цього модуля ти можеш будувати асинхронну логіку на рівні “як у Spring WebFlux чи мікросервісах”.
Тепер кожна задача може виконуватись паралельно, без ручного створення потоків.