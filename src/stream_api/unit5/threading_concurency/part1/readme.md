🚀 Модуль №11 — Concurrency & Parallel Streams Deep Dive
(Частина 1: Threads — основи багатопоточності)

Це найважливіша основа: тут ми створимо свої потоки,
побачимо race condition, навчимось керувати порядком
виконання, і відчуємо, як мислить JVM під навантаженням.

⚙️ Частина 1 — Threads Basics (Завдання 1–5)
### ✅ Завдання 1 — Створення та запуск потоку (Thread + Runnable)

# Runnable — це функціональний інтерфейс в Java,
# який використовується для опису задачі,
# що може виконуватися в окремому потоці (thread).
# Він знаходиться в пакеті java.lang і виглядає так:

@FunctionalInterface
public interface Runnable {
void run();
}

# Runnable — це як контракт: «цей об’єкт можна виконати в потоці».

https://chatgpt.com/c/69033f9c-b7a0-832f-986b-abe7f6a202f2 
📋 Умова: створити новий потік, який виведе повідомлення 5 разів.

public class Task1 {
public static void main(String[] args) {
Runnable job = () -> {
for (int i = 1; i <= 5; i++) {
System.out.println(Thread.currentThread().getName()
+ " → mesasage # " + i);
}
};
        Thread t = new Thread(job);
        t.start();

        System.out.println("Main thread finished setup.");
    }
}


📤 Приклад виводу:

Main thread finished setup.
Thread-0 → 1
Thread-0 → 2
Thread-0 → 3
Thread-0 → 4
Thread-0 → 5


🧠 Пояснення:

t.start() запускає новий потік асинхронно, не блокує main.
Виконання main і нового потоку може перемішуватись.

# ✅ Завдання 2 — Відмінність між start() і run()

📋 Умова: перевір різницю між викликом .start() і .run().

Thread t = new Thread(() -> System.out.println(Thread
.currentThread().getName()));
t.run();   // ❌ просто виклик методу
t.start(); // ✅ створює новий потік


📤 Результат:

main
Thread-0


🧠 run() виконується в тому ж потоці (main),
а start() створює новий — із власним стеком виконання.

# ✅ Завдання 3 — Метод sleep() і затримка виконання

📋 Умова: затримай потік на 500 мс між кроками.

Thread t = new Thread(() -> {
try {
for (int i = 1; i <= 3; i++) {
System.out.println("Step " + i);
Thread.sleep(500);
}
} catch (InterruptedException e) {
e.printStackTrace();
}
});

t.start();


📤 Вивід (з паузами):

Step 1
(0.5s pause)
Step 2
(0.5s pause)
Step 3


🧠 Thread.sleep() призупиняє потік, але не завершує його.
Інші потоки можуть працювати далі.

# ✅ Завдання 4 — Race condition (зіткнення потоків)
📋 Умова: покажи проблему, коли два потоки 
змінюють спільну змінну.

public class RaceConditionDemo {
static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) counter++;
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Counter = " + counter);
    }
}

📤 Очікуєш: 2000
📉 Отримуєш: 1784, 1922, 2000... (випадково кожен раз!)

🧠 Це і є race condition — обидва потоки читають і змінюють змінну одночасно, перезаписуючи результат.


# ✅ Завдання 5 — synchronized блокує одночасний доступ

📋 Умова: виправ race condition за допомогою synchronized.

public class SyncDemo {
static int counter = 0;

    public static synchronized void increment() {
        counter++;
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) increment();
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("Counter = " + counter);
    }
}


📤 Результат стабільний:

Counter = 2000


🧠 synchronized гарантує, що лише один потік одночасно виконує метод increment().

🧩 Підсумок частини 1
Тема	            Метод / Концепція	Що робить
Створення потоку	new Thread(Runnable)	запускає новий потік
Запуск	            .start() vs .run()	start створює новий потік
Затримка	        Thread.sleep(ms)	призупиняє потік
Зіткнення	        Race condition	    кілька потоків змінюють одні дані
Блокування	        synchronized	    обмежує одночасний доступ

🧠 Після цього блоку ти вже вмієш:

створювати і запускати потоки;
керувати їх життєвим циклом;
розумієш, чому потрібна синхронізація.

👉 Наступна частина (2️⃣) — Locks & Atomics:
Там ми подивимось:

як ReentrantLock дає більше контролю ніж synchronized,
як AtomicInteger прибирає потребу в блокуваннях,
і що відбувається, коли потік зависає у lock-стані.