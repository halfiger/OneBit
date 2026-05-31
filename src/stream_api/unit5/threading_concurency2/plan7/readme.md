🧵 THREADS MODULE 07 — Advanced Concurrency

(ConcurrentMap, BlockingQueue, CompletableFuture)

🎯 Мета модуля:
Навчитися працювати з високорівневими конкурентними структурами 
даних та асинхронними обчисленнями:

ConcurrentHashMap
BlockingQueue
CompletableFuture

асинхронні ланцюги (thenApply, thenCombine, supplyAsync)

паралельна статистика

✅ Завдання 1: ConcurrentMapRace — Безпечна мапа при одночасних оновленнях

📋 Умова:
Створи ConcurrentHashMap<String, Integer> scores.
10 потоків одночасно збільшують рахунок "player" на 1_000 разів кожен.

🔧 Підказка:

ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
map.put("player", 0);

Runnable task = () -> {
for (int i = 0; i < 1000; i++) {
map.compute("player", (k, v) -> v + 1);
}
};


💡 Мета:
Побачити, що конкурентна колекція гарантує цілісність даних без synchronized.
Ключовий момент — методи compute(), merge(), putIfAbsent() є атомарними.

🧩 Розширення:
Запусти 100 потоків × 10_000 інкрементів — переконайся, що результат завжди правильний.

✅ Завдання 2: BlockingQueueChat — Черга повідомлень між потоками

📋 Умова:
Створи BlockingQueue<String> chat = new ArrayBlockingQueue<>(5);.
Один потік — “Writer” — додає повідомлення кожну секунду.
Інший — “Reader” — бере повідомлення з черги і друкує.

🔧 Підказка:

BlockingQueue<String> chat = new ArrayBlockingQueue<>(5);

new Thread(() -> {
int i = 1;
try {
while (true) {
chat.put("Msg " + i++);
Thread.sleep(1000);
}
} catch (InterruptedException e) {}
}, "Writer").start();

new Thread(() -> {
try {
while (true) {
String msg = chat.take();
System.out.println(Thread.currentThread().getName() + " read: " + msg);
}
} catch (InterruptedException e) {}
}, "Reader").start();


💡 Мета:
Побачити, як BlockingQueue автоматично блокує потоки при переповненні або порожнечі — без wait/notify.

🧩 Розширення:
Зроби кілька читачів і кілька письменників — “груповий чат”.

✅ Завдання 3: CompletableFutureChain — Ланцюг асинхронних дій

📋 Умова:
Створи CompletableFuture, який:

обчислює "Java" у верхньому регістрі,

потім додає " is powerful",

потім друкує результат.

🔧 Підказка:

CompletableFuture.supplyAsync(() -> "Java")
.thenApply(String::toUpperCase)
.thenApply(s -> s + " is powerful")
.thenAccept(System.out::println);


💡 Мета:
Показати, як ланцюг методів утворює “конвеєр обчислень” — кожен крок виконується після попереднього, асинхронно.

🧩 Розширення:
Додай .exceptionally(ex -> "Error: " + ex.getMessage()), щоб обробити помилки в ланцюгу.

✅ Завдання 4: CombineAsync — Об’єднання двох майбутніх результатів

📋 Умова:
Є два CompletableFuture:

один повертає "Hello",

інший "World".
Об’єднай їх у єдиний рядок "Hello World" через thenCombine().

🔧 Підказка:

CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "Hello");
CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "World");
CompletableFuture<String> combined = f1.thenCombine(f2, (a, b) -> a + " " + b);
System.out.println(combined.get());


💡 Мета:
Побачити, як можна комбінувати незалежні асинхронні обчислення, не блокуючи потоки вручну.

🧩 Розширення:
Додай третє CompletableFuture (наприклад, "!") і зроби тристороннє об’єднання через послідовне thenCombine().

✅ Завдання 5: SupplyAsyncStats — Паралельна статистика колекції

📋 Умова:
Є список чисел від 1 до 1_000_000.
Обчисли паралельно:

суму (sum)

середнє (avg)

максимум (max)

Використай три CompletableFuture<Long> і об’єднай результати після завершення.

🔧 Підказка:

List<Integer> data = IntStream.rangeClosed(1, 1_000_000).boxed().toList();

CompletableFuture<Long> sum = CompletableFuture.supplyAsync(() -> data.stream().mapToLong(i -> i).sum());
CompletableFuture<Double> avg = CompletableFuture.supplyAsync(() -> data.stream().mapToInt(i -> i).average().orElse(0));
CompletableFuture<Integer> max = CompletableFuture.supplyAsync(() -> data.stream().mapToInt(i -> i).max().orElse(0));

CompletableFuture.allOf(sum, avg, max).join();
System.out.println("Sum=" + sum.join() + ", Avg=" + avg.join() + ", Max=" + max.join());


💡 Мета:
Використати CompletableFuture.allOf() для паралельного обчислення статистик.
У реальному житті — це база для паралельного аналізу даних, мікросервісів, тощо.

🧩 Розширення:
Порівняй час із послідовним виконанням — побач, як CPU розподіляє навантаження між ядрами.

💬 Післямова

🧠 Після цього модуля ти:

володієш конкурентними структурами (ConcurrentHashMap, BlockingQueue);

вмієш працювати з асинхронними результатами (CompletableFuture);

розумієш принципи “ланцюгової обробки” (thenApply, thenCombine);

знаєш, як комбінувати незалежні завдання в один результат;

готовий перейти до останнього модуля — ForkJoin & Parallel Computation,
де ми реалізуємо справжнє розбиття задачі на підзадачі через RecursiveTask і ForkJoinPool.