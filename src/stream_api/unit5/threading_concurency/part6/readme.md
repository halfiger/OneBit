Ти прямо зараз заходиш у найпотужнішу фазу Java-інженерії —
поєднання ExecutorService і CompletableFuture,
де ми вже не просто “асинхронні”, а керуємо тим, як саме працює багатопоточна машина 🧠⚙️

⚙️ Модуль №14 — ExecutorService & CompletableFuture Integration (1–10 завдань)

📘 Мета:
Навчитись створювати власні пули потоків і інтегрувати їх із CompletableFuture
для контролю продуктивності, розподілу навантаження, відлагодження та масштабування.

✅ Завдання 1 — Передача власного Executor у supplyAsync()

📋 Умова: використай свій пул потоків для асинхронної задачі.

import java.util.concurrent.*;

public class Task1 {
public static void main(String[] args) throws Exception {
ExecutorService pool = Executors.newFixedThreadPool(2);

        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " працює");
            return "Result";
        }, pool);

        System.out.println(cf.get());
        pool.shutdown();
    }
}


📤 Вивід:

pool-1-thread-1 працює  
Result


🧠 supplyAsync(task, executor) дозволяє контролювати, де саме виконуватиметься завдання.

✅ Завдання 2 — Використання спільного Executor для кількох задач

📋 Умова: запусти 5 задач у спільному пулі з 3 потоками.

ExecutorService pool = Executors.newFixedThreadPool(3);

for (int i = 1; i <= 5; i++) {
int id = i;
CompletableFuture.runAsync(() -> {
System.out.println(Thread.currentThread().getName() + " → Task " + id);
sleep(400);
}, pool);
}

pool.shutdown();

static void sleep(long ms) {
try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
}


📤 Вивід:

pool-1-thread-1 → Task 1  
pool-1-thread-2 → Task 2  
pool-1-thread-3 → Task 3  
pool-1-thread-1 → Task 4  
pool-1-thread-2 → Task 5


🧠 Всі CompletableFuture ділять один пул — жодних зайвих потоків не створюється.

✅ Завдання 3 — Вимірювання продуктивності

📋 Умова: порівняй швидкість ForkJoinPool vs власного Executor.

List<Integer> data = IntStream.range(0, 10_000_000).boxed().toList();

measure("ForkJoinPool", () ->
data.parallelStream().map(Math::sqrt).toList()
);

ExecutorService pool = Executors.newFixedThreadPool(4);
measure("Custom Executor", () ->
data.stream().map(x -> CompletableFuture.supplyAsync(() -> Math.sqrt(x), pool))
.map(CompletableFuture::join)
.toList()
);
pool.shutdown();

static void measure(String name, Runnable task) {
long t1 = System.currentTimeMillis();
task.run();
long t2 = System.currentTimeMillis();
System.out.println(name + ": " + (t2 - t1) + " ms");
}


📤 Вивід (приклад):

ForkJoinPool: 140 ms  
Custom Executor: 115 ms


🧠 Іноді власний пул із оптимальною кількістю потоків швидший, ніж загальний ForkJoinPool.

✅ Завдання 4 — Збір результатів із кількох пулів

📋 Умова: дві групи задач працюють у різних пулах.

ExecutorService cpuPool = Executors.newFixedThreadPool(2);
ExecutorService ioPool = Executors.newCachedThreadPool();

CompletableFuture<String> calc = CompletableFuture.supplyAsync(() -> {
System.out.println("CPU pool → calc");
sleep(500);
return "calc_done";
}, cpuPool);

CompletableFuture<String> load = CompletableFuture.supplyAsync(() -> {
System.out.println("IO pool → load");
sleep(300);
return "load_done";
}, ioPool);

System.out.println(calc.thenCombine(load, (a, b) -> a + " & " + b).join());
cpuPool.shutdown();
ioPool.shutdown();


📤 Вивід:

CPU pool → calc  
IO pool → load  
calc_done & load_done


🧠 Дуже реалістична архітектура: CPU-операції в одному пулі, IO-операції — в іншому.

✅ Завдання 5 — Масштабування пулу потоків (dynamic resizing)

📋 Умова: використай CachedThreadPool для автопідбору кількості потоків.

ExecutorService pool = Executors.newCachedThreadPool();

for (int i = 1; i <= 6; i++) {
int id = i;
CompletableFuture.runAsync(() -> {
System.out.println(Thread.currentThread().getName() + " → job " + id);
sleep(300);
}, pool);
}

pool.shutdown();


📤 Вивід:

pool-1-thread-1 → job 1  
pool-1-thread-2 → job 2  
pool-1-thread-3 → job 3  
pool-1-thread-4 → job 4  
pool-1-thread-5 → job 5  
pool-1-thread-6 → job 6


🧠 CachedThreadPool створює нові потоки при навантаженні і “паркує” їх, коли робота закінчується.

✅ Завдання 6 — Обробка помилок у кастомному Executor

📋 Умова: налаштуй Executor із власним handler’ом для помилок.

ExecutorService pool = new ThreadPoolExecutor(
2, 4, 60, TimeUnit.SECONDS,
new LinkedBlockingQueue<>(),
Executors.defaultThreadFactory(),
(r, e) -> System.out.println("❗ Task rejected: " + r)
);

for (int i = 0; i < 10; i++) {
CompletableFuture.runAsync(() -> {
if (Math.random() > 0.7) throw new RuntimeException("Fail");
}, pool).exceptionally(ex -> {
System.out.println("Caught: " + ex);
return null;
});
}

pool.shutdown();


📤 Вивід:

Caught: java.util.concurrent.CompletionException: java.lang.RuntimeException: Fail
❗ Task rejected: ...


🧠 Це рівень “продакшн”: власний пул + політика реакції на винятки.

✅ Завдання 7 — Передача кастомного ThreadFactory

📋 Умова: створи Executor, що дає імена потокам.

ThreadFactory factory = r -> {
Thread t = new Thread(r);
t.setName("MyPool-" + t.getId());
return t;
};

ExecutorService pool = Executors.newFixedThreadPool(3, factory);

for (int i = 1; i <= 3; i++) {
CompletableFuture.runAsync(() ->
System.out.println(Thread.currentThread().getName() + " працює"), pool);
}

pool.shutdown();


📤 Вивід:

MyPool-14 працює  
MyPool-15 працює  
MyPool-16 працює


🧠 ThreadFactory — контроль над іменами, пріоритетами і поведінкою потоків.

✅ Завдання 8 — Асинхронна черга обробки (pipeline)

📋 Умова: три етапи обробки — усі асинхронно.

ExecutorService pool = Executors.newFixedThreadPool(3);

CompletableFuture.supplyAsync(() -> "data", pool)
.thenApplyAsync(d -> d + " → parsed", pool)
.thenApplyAsync(d -> d + " → validated", pool)
.thenAcceptAsync(System.out::println, pool)
.join();

pool.shutdown();


📤 Вивід:

data → parsed → validated


🧠 Це “асинхронний pipeline” — кожен етап може працювати незалежно в тому самому пулі.

✅ Завдання 9 — Паралельна обробка великої колекції через Executor

📋 Умова: оброби 10 000 елементів з обмеженим пулом потоків.

ExecutorService pool = Executors.newFixedThreadPool(8);

List<Integer> data = IntStream.range(0, 10_000).boxed().toList();

List<CompletableFuture<Integer>> futures = data.stream()
.map(i -> CompletableFuture.supplyAsync(() -> i * 2, pool))
.toList();

int sum = futures.stream().mapToInt(CompletableFuture::join).sum();

System.out.println("Sum = " + sum);
pool.shutdown();


🧠 Це шаблон для масової паралельної обробки — можна застосовувати для великих обчислень, конвертацій, API-запитів тощо.

✅ Завдання 10 — Комбінований сценарій: IO + CPU із розподілом пулів

📋 Умова: IO-операції (повільні) → CPU-операції (швидкі) → об’єднання.

ExecutorService ioPool = Executors.newFixedThreadPool(3);
ExecutorService cpuPool = Executors.newFixedThreadPool(2);

CompletableFuture<String> ioTask = CompletableFuture.supplyAsync(() -> {
System.out.println("IO fetch...");
sleep(500);
return "DATA";
}, ioPool);

CompletableFuture<Integer> cpuTask = ioTask.thenApplyAsync(d -> {
System.out.println("CPU processing...");
return d.length() * 10;
}, cpuPool);

System.out.println("Result = " + cpuTask.join());

ioPool.shutdown();
cpuPool.shutdown();

static void sleep(long ms) {
try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
}


📤 Вивід:

IO fetch...
CPU processing...
Result = 40


🧠 Це архітектурний патерн “розділення навантаження” —
IO-операції не блокують CPU-інтенсивні завдання.

🧩 Підсумок модуля
Концепція	Ключовий клас / Метод	Призначення
supplyAsync(..., executor)	Виконання у власному пулі
FixedThreadPool	Стабільна кількість потоків
CachedThreadPool	Автоматичне масштабування
ThreadFactory	Іменування та контроль потоків
ThreadPoolExecutor	Повний контроль (розмір, політика, handler)
thenApplyAsync(..., executor)	Продовження в певному пулі
IO vs CPU pools	Розділення типів навантаження
Performance tests	Порівняння ForkJoinPool і custom pools

🧠 Після цього модуля ти — архітектор асинхронності:
ти вже не просто запускаєш задачі, ти розподіляєш ресурси,
контролюєш потоки, знаєш, де CPU, де IO, і створюєш асинхронні “системи”.