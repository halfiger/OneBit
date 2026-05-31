⚙️ Модуль №16 — Real-world Asynchronous Patterns (1–10 завдань)

📘 Мета:
Освоїти практичні патерни використання CompletableFuture у реальних системах:
API-агрегація, кешування, обробка колекцій, таймаути, fallback-логіка та аналітика.

✅ Завдання 1 — Паралельна агрегація API-відповідей

📋 Умова: симулюй три незалежні запити до мікросервісів і з’єднай їх.

CompletableFuture<String> users = CompletableFuture.supplyAsync(() -> fetch("Users"));
CompletableFuture<String> orders = CompletableFuture.supplyAsync(() -> fetch("Orders"));
CompletableFuture<String> payments = CompletableFuture.supplyAsync(() -> fetch("Payments"));

CompletableFuture<Void> all = CompletableFuture.allOf(users, orders, payments);

String result = all.thenApply(v ->
users.join() + " + " + orders.join() + " + " + payments.join()
).join();

System.out.println("Aggregated: " + result);

static String fetch(String service) {
sleep((int)(Math.random() * 700));
System.out.println("Fetched " + service);
return service.toUpperCase();
}

static void sleep(long ms) {
try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
}


📤 Вивід:

Fetched Payments  
Fetched Users  
Fetched Orders  
Aggregated: USERS + ORDERS + PAYMENTS


🧠 Типовий API Aggregator: паралельні запити → комбінований результат.
Аналогія зі Spring @Async або WebClient.zip().

✅ Завдання 2 — Паралельна обробка великої колекції

📋 Умова: оброби список користувачів асинхронно з обмеженим пулом.

ExecutorService pool = Executors.newFixedThreadPool(5);

List<String> users = List.of("Alice", "Bob", "Charlie", "Dina", "Eve");

List<CompletableFuture<String>> tasks = users.stream()
.map(u -> CompletableFuture.supplyAsync(() -> processUser(u), pool))
.toList();

List<String> results = tasks.stream()
.map(CompletableFuture::join)
.toList();

System.out.println("Processed: " + results);
pool.shutdown();

static String processUser(String name) {
sleep((int)(Math.random() * 400));
System.out.println("Processed " + name);
return name.toUpperCase();
}


📤 Вивід:

Processed Bob  
Processed Alice  
Processed Eve  
Processed Dina  
Processed Charlie  
Processed: [ALICE, BOB, CHARLIE, DINA, EVE]


🧠 Патерн “async map()” — обробка елементів колекції асинхронно.
Як Flux.parallel() у Spring WebFlux.

✅ Завдання 3 — Fallback при помилці (резервна відповідь)

📋 Умова: якщо API падає, поверни кешоване значення.

CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
if (Math.random() > 0.6) throw new RuntimeException("API failed");
return "Fresh data";
}).exceptionally(ex -> {
System.out.println("⚠️ " + ex.getMessage() + " — using cached value");
return "Cached data";
});

System.out.println("Result: " + cf.join());


📤 Вивід:

⚠️ API failed — using cached value  
Result: Cached data


🧠 Це патерн “Graceful degradation” — застосовується у продакшні постійно.

✅ Завдання 4 — Асинхронний кеш

📋 Умова: якщо дані в кеші є — поверни їх, інакше завантаж асинхронно.

Map<String, String> cache = new ConcurrentHashMap<>();

CompletableFuture<String> data = CompletableFuture.supplyAsync(() ->
cache.computeIfAbsent("users", k -> loadFromDb())
);

System.out.println("Result: " + data.join());

static String loadFromDb() {
System.out.println("Loading from DB...");
sleep(500);
return "UserList";
}


📤 Вивід (перший раз):

Loading from DB...
Result: UserList


📤 Другий запуск:

Result: UserList


🧠 Патерн “async lazy cache”: дані підтягуються лише коли треба.

✅ Завдання 5 — Обробка з таймаутом і fallback

📋 Умова: якщо завдання зависає — використовуй дефолт.

CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
sleep(1000);
return "Real result";
}).completeOnTimeout("Default result", 500, TimeUnit.MILLISECONDS);

System.out.println("→ " + cf.join());


📤 Вивід:

→ Default result


🧠 Використовується для API із невизначеною швидкістю —
completeOnTimeout() дозволяє не чекати вічно.

✅ Завдання 6 — Parallel combine (злиття даних із різних джерел)

📋 Умова: симулюй два джерела даних і об’єднай їх результат.

CompletableFuture<List<String>> dbUsers = CompletableFuture.supplyAsync(() -> List.of("Alice", "Bob"));
CompletableFuture<List<String>> apiUsers = CompletableFuture.supplyAsync(() -> List.of("Carl", "Dina"));

CompletableFuture<List<String>> all = dbUsers.thenCombine(apiUsers, (a, b) -> {
List<String> combined = new ArrayList<>(a);
combined.addAll(b);
return combined;
});

System.out.println("All users: " + all.join());


📤 Вивід:

All users: [Alice, Bob, Carl, Dina]


🧠 Патерн “parallel data merge” — часто використовується у REST API агрегації.

✅ Завдання 7 — Послідовність async етапів із логуванням

📋 Умова: симулюй ETL-процес (extract-transform-load).

CompletableFuture.supplyAsync(() -> {
log("Extract");
sleep(300);
return "Raw data";
}).thenApplyAsync(d -> {
log("Transform");
return d + " → Cleaned";
}).thenApplyAsync(d -> {
log("Load");
return d + " → Saved";
}).thenAcceptAsync(d -> log("Done: " + d))
.join();

static void log(String msg) {
System.out.println(Thread.currentThread().getName() + " → " + msg);
}


📤 Вивід:

ForkJoinPool.commonPool-worker-3 → Extract  
ForkJoinPool.commonPool-worker-5 → Transform  
ForkJoinPool.commonPool-worker-7 → Load  
ForkJoinPool.commonPool-worker-9 → Done: Raw data → Cleaned → Saved


🧠 Патерн “ETL async pipeline” — використовується для обробки великих потоків даних.

✅ Завдання 8 — Масова обробка та збір результатів

📋 Умова: оброби список чисел і збери суму результатів.

List<Integer> nums = IntStream.range(1, 11).boxed().toList();

List<CompletableFuture<Integer>> futures = nums.stream()
.map(n -> CompletableFuture.supplyAsync(() -> n * n))
.toList();

int total = futures.stream()
.mapToInt(CompletableFuture::join)
.sum();

System.out.println("Total: " + total);


📤 Вивід:

Total: 385


🧠 Це шаблон “map-reduce async” — паралельна обробка з агрегацією результатів.

✅ Завдання 9 — Паралельна перевірка сервісів (health-check)

📋 Умова: перевір 3 сервіси й дочекайся найповільнішого.

List<String> services = List.of("Auth", "DB", "Storage");

CompletableFuture<Void> all = CompletableFuture.allOf(
services.stream()
.map(s -> CompletableFuture.runAsync(() -> check(s)))
.toArray(CompletableFuture[]::new)
);

all.join();
System.out.println("✅ All services are up!");

static void check(String name) {
sleep((int)(Math.random() * 600));
System.out.println("Checked " + name);
}


📤 Вивід:

Checked DB  
Checked Auth  
Checked Storage  
✅ All services are up!


🧠 Це готовий патерн для Spring Actuator або health-monitor’ів мікросервісів.

✅ Завдання 10 — Реактивна обробка подій

📋 Умова: створюй асинхронні реакції на події в реальному часі.

List<String> events = List.of("LOGIN", "CLICK", "LOGOUT");

events.forEach(e ->
CompletableFuture.runAsync(() -> handleEvent(e))
);

sleep(500);

static void handleEvent(String e) {
System.out.println(Thread.currentThread().getName() + " → handled " + e);
}


📤 Вивід:

ForkJoinPool.commonPool-worker-1 → handled LOGIN  
ForkJoinPool.commonPool-worker-3 → handled CLICK  
ForkJoinPool.commonPool-worker-5 → handled LOGOUT


🧠 Це патерн “reactive dispatch” — реактивна система на події.

🧩 Підсумок
Патерн	Опис
API Aggregation	паралельне отримання даних із різних джерел
Async Map	асинхронна обробка колекцій
Fallback	дефолт при збої
Async Cache	завантаження лише за потреби
Timeout Control	захист від зависань
Parallel Merge	комбінування даних
ETL Pipeline	асинхронна обробка етапів
Map-Reduce	паралельна агрегація
Health Check	моніторинг сервісів
Reactive Dispatch	подієва реакція у фонових потоках

Хочеш, щоб після цього я зробила Модуль №17 — CompletableFuture + Spring Integration (де ми візьмемо Spring Boot, WebClient, і зробимо async REST API)?