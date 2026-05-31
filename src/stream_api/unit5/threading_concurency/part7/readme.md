Модуль №15: CompletableFuture Testing & Debugging 💣
Тут ми навчимося не тільки створювати асинхронність, а й бачити, що саме вона робить “під капотом”.
Ти зможеш тестувати, логувати й ловити найпідступніші race conditions, не блокуючи головний потік 🧠⚙️

⚙️ Модуль №15 — CompletableFuture Testing & Debugging (1–10 завдань)

📘 Мета:
Навчитися діагностувати, тестувати, логувати і контролювати поведінку асинхронних задач.

✅ Завдання 1 — Використання join() без блокування тесту
📋 Умова: заміни get() на join() і перевір різницю.

CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
sleep(300);
return "Hello async!";
});

System.out.println("Before join");
System.out.println("Result = " + cf.join());
System.out.println("After join");

static void sleep(long ms) {
try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
}


📤 Вивід:

Before join  
Result = Hello async!  
After join


🧠 join() не кидає checked exceptions (ExecutionException, InterruptedException),
що спрощує тести. Він ідеальний для unit-тестів.

✅ Завдання 2 — Тестування асинхронності через TimeUnit.SECONDS.sleep()

📋 Умова: перевір асинхронну поведінку у тесті.

CompletableFuture.runAsync(() -> {
sleep(500);
System.out.println("Task finished!");
});

System.out.println("Main done");
TimeUnit.SECONDS.sleep(1);


📤 Вивід:

Main done  
Task finished!


🧠 Тут головний потік завершується раніше, але ми “чекаємо” трохи, щоб побачити результат.
Так тестують незалежні async-завдання без join/get.

✅ Завдання 3 — Перевірка часу виконання (performance test)

📋 Умова: виміряй швидкість паралельного vs послідовного виконання.

long t1 = System.currentTimeMillis();

List<CompletableFuture<Void>> tasks = IntStream.range(0, 10)
.mapToObj(i -> CompletableFuture.runAsync(() -> sleep(300)))
.toList();

CompletableFuture.allOf(tasks.toArray(new CompletableFuture[0])).join();

long t2 = System.currentTimeMillis();
System.out.println("Time: " + (t2 - t1) + " ms");


📤 Вивід:

Time: ~310 ms


🧠 10 завдань виконались паралельно, не по 3000 мс, а всього ~300.
Це класичний тест паралельної продуктивності.

✅ Завдання 4 — Логування потоків

📋 Умова: виведи, у яких потоках працює кожен етап.

CompletableFuture.supplyAsync(() -> {
log("Stage 1");
return "Data";
}).thenApplyAsync(d -> {
log("Stage 2");
return d + " processed";
}).thenAcceptAsync(d -> log("Stage 3: " + d))
.join();

static void log(String msg) {
System.out.println(Thread.currentThread().getName() + " → " + msg);
}


📤 Вивід:

ForkJoinPool.commonPool-worker-3 → Stage 1  
ForkJoinPool.commonPool-worker-5 → Stage 2  
ForkJoinPool.commonPool-worker-7 → Stage 3: Data processed


🧠 Тепер ти бачиш, у яких потоках працює кожна частина ланцюжка —
це найкраща техніка для відлагодження асинхронності.

✅ Завдання 5 — Використання complete() для підміни результату

📋 Умова: примусово завершити задачу перед її виконанням.

CompletableFuture<String> cf = new CompletableFuture<>();

cf.complete("Forced result");

System.out.println(cf.join());


📤 Вивід:

Forced result


🧠 complete() часто використовують у тестах — щоб задати готовий результат
(імітація API-відповіді без фактичного запиту).

✅ Завдання 6 — Перевірка завершеності завдання (isDone)

📋 Умова: перевір, коли CompletableFuture завершується.

CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> sleep(400));

while (!cf.isDone()) {
System.out.println("Still running...");
sleep(100);
}
System.out.println("Done!");


📤 Вивід:

Still running...  
Still running...  
Still running...  
Done!


🧠 isDone() корисний у тестах і дебазі — щоб чекати завершення без блокування.

✅ Завдання 7 — exceptionally() у тесті

📋 Умова: перевір, чи правильно обробляються винятки.

CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
if (true) throw new RuntimeException("Boom!");
return 42;
}).exceptionally(ex -> {
System.out.println("Handled: " + ex.getMessage());
return 0;
});

System.out.println("Result: " + cf.join());


📤 Вивід:

Handled: java.lang.RuntimeException: Boom!  
Result: 0


🧠 Це стандартний патерн тестування асинхронних помилок —
перевіряєш, що виняток не “втек” і був перехоплений.

✅ Завдання 8 — handle(): комбінована обробка успіху/помилки

📋 Умова: одна точка для обох сценаріїв.

CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> {
if (Math.random() > 0.5) throw new RuntimeException("Random fail");
return 10;
}).handle((res, ex) -> {
if (ex != null) {
System.out.println("Handled: " + ex.getMessage());
return -1;
}
return res * 2;
});

System.out.println("Result = " + cf.join());


📤 Вивід (варіант 1):

Result = 20


📤 Вивід (варіант 2):

Handled: Random fail  
Result = -1


🧠 handle() — дуже зручний для юніт-тестів:
він перевіряє і успіх, і виняток в одному місці.

✅ Завдання 9 — Debug chain (ланцюг логування)

📋 Умова: додай лог після кожного thenApply для контролю послідовності.

CompletableFuture.supplyAsync(() -> "A")
.thenApply(v -> logStage("1: " + v))
.thenApply(v -> logStage("2: " + v))
.thenApply(v -> logStage("3: " + v))
.join();

static String logStage(String msg) {
System.out.println(Thread.currentThread().getName() + " → " + msg);
return msg;
}


📤 Вивід:

ForkJoinPool.commonPool-worker-1 → 1: A  
ForkJoinPool.commonPool-worker-3 → 2: 1: A  
ForkJoinPool.commonPool-worker-5 → 3: 2: 1: A


🧠 Ідеально для дебагу довгих ланцюгів —
бачиш, який етап виконується і в якому потоці.

✅ Завдання 10 — Timeout + логування винятку

📋 Умова: використай orTimeout() для обмеження часу.

CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
sleep(800);
return "Result";
}).orTimeout(500, TimeUnit.MILLISECONDS)
.exceptionally(ex -> {
System.out.println("Timeout: " + ex);
return "Default";
});

System.out.println("Final: " + cf.join());


📤 Вивід:

Timeout: java.util.concurrent.TimeoutException
Final: Default


🧠 Це продакшн-рівень контроль:
обмеження часу + graceful fallback без зависання потоків.

🧩 Підсумок модуля
Концепція	                Метод	Призначення
join()	                    отримання результату без checked exceptions
isDone()	                перевірка завершення
complete()	                форсоване завершення
exceptionally(), handle()	тестування винятків
log(Thread)	                відстеження потоків
orTimeout()	                обмеження часу
allOf(), anyOf()	        перевірка паралельних результатів
handle()	                універсальна точка контролю

🧠 Після цього модуля ти:

вмієш тестувати асинхронний код без зависання;
можеш дебажити ланцюги CompletableFuture;
контролюєш таймаути, помилки та логи;
готовий до реальної асинхронної архітектури.


-----------------------------------------------------------------------------------------------------------
Хочеш, щоб Модуль №16 був про:
🔹 “Real-world Asynchronous Patterns” — наприклад:
API агрегація, паралельні запити до бази, асинхронна обробка колекцій (як у Spring)?
https://chatgpt.com/g/g-p-68e8fbae40bc819189c46e34e72bb031-java-mentor/c/68ea88a9-7b68-8328-93e1-a672d4289b05