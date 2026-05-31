🧵 THREADS MODULE 01 — Thread Creation & Basics
  
🎯 Мета модуля:
Зрозуміти, як створюються потоки, як вони працюють у JVM, що таке планувальник,
join(), sleep() і daemon, а також відчути перші прояви непередбачуваності при паралельному виконанні.

✅ Завдання 1: DualRunner (A vs B)

📋 Умова:

Створи два потоки:

один виводить "A" 100 разів із випадковою затримкою;

інший — "B" 100 разів із випадковою затримкою.

🔧 Підказка:

Thread t1 = new Thread(() -> {
for (int i = 0; i < 100; i++) {
System.out.print("A");
try { Thread.sleep((int)(Math.random() * 50)); } catch (InterruptedException e) {}
}
});

Thread t2 = new Thread(() -> {
for (int i = 0; i < 100; i++) {
System.out.print("B");
try { Thread.sleep((int)(Math.random() * 50)); } catch (InterruptedException e) {}
}
});

t1.start();
t2.start();
t1.join();
t2.join();
System.out.println("\nDone!");


💡 Мета:
Побачити, що порядок виводу "A" та "B" щоразу різний — керує планувальник ОС, не ви.

🧩 Розширення:
Додай третій потік "C", який чекає на завершення перших двох і тоді виводить "FINISHED".

✅ Завдання 2: ParallelCounter — Розділений лічильник

📋 Умова:
Створи спільний лічильник (int count = 0;)
і два потоки, які інкрементують його по 1000 разів кожен.
Виведи фінальне значення.

🔧 Підказка:

int[] counter = {0};
Runnable increment = () -> {
for (int i = 0; i < 1000; i++) counter[0]++;
};


🧠 Очікувано:
Результат не завжди буде 2000 — через race condition.
Пояснення цього стане темою наступного модуля.

🧩 Розширення:
Додай Thread.sleep(1) усередині циклу, щоб підсилити прояв колізій.

✅ Завдання 3: JoinControl — Ланцюг потоків

📋 Умова:
Створи три потоки:

Перший друкує "Start" → чекає 1 с

Другий чекає завершення першого (join()) і друкує "Middle"

Третій чекає завершення другого і друкує "End"

🔧 Підказка:

Thread t1 = new Thread(() -> { ... });
Thread t2 = new Thread(() -> { t1.join(); ... });
Thread t3 = new Thread(() -> { t2.join(); ... });


💡 Мета:
Показати, як потоки можуть формувати логічний порядок, якщо використовується join().

🧩 Розширення:
Зроби з цього “ефект друкарської машинки”: Start → ... → Middle → ... → End.

✅ Завдання 4: DaemonPulse — Демон, який живе доти, доки живе main

📋 Умова:
Створи демон-потік, який раз на секунду друкує "♥ heartbeat".
Поки основний потік працює — він живе.
Коли main закінчує роботу — демон завершується автоматично.

🔧 Підказка:

Thread daemon = new Thread(() -> {
while (true) {
System.out.println("♥ heartbeat");
try { Thread.sleep(1000); } catch (InterruptedException e) {}
}
});
daemon.setDaemon(true);
daemon.start();
Thread.sleep(5000);
System.out.println("Main done!");


💡 Мета:
Побачити різницю між звичайним і демон-потоком.

🧩 Розширення:
Додай логіку, щоб демон зупинявся вручну після сигналу volatile boolean stop.

✅ Завдання 5: ThreadInspector — Дослідження живих потоків

📋 Умова:
Запусти 3 різних потоки (включно з демоном),
а потім виведи всі активні потоки через Thread.getAllStackTraces().keySet().

🔧 Підказка:

Set<Thread> threads = Thread.getAllStackTraces().keySet();
threads.forEach(t -> System.out.println(
t.getName() + " | " + (t.isDaemon() ? "daemon" : "user") + " | " + t.getState()
));


💡 Мета:
Зрозуміти, які потоки створює JVM за замовчуванням (Finalizer, Reference Handler тощо).
Це допоможе пізніше при налагодженні ExecutorService і ForkJoinPool.

🧩 Розширення:
Зроби сортування потоків за станом (Thread.State) і порахуй, скільки їх у кожному стані.

💬 Післямова

🧠 Після проходження цього модуля ти:

вільно створюєш і керуєш потоками;

розумієш, як працює join() і sleep();

знаєш, що порядок виконання — непередбачуваний;

побачив перший прояв race condition, який виправимо у модулі 02.