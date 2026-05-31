⚙️ Частина 3 — Parallel Streams & ExecutorService (Завдання 11–15)
✅ Завдання 11 — ExecutorService (пул потоків)

📋 Умова: створити пул із 3 потоків і запустити кілька завдань.

import java.util.concurrent.*;

public class ExecutorDemo {
public static void main(String[] args) {
ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.submit(() ->
                System.out.println(Thread.currentThread().getName() + " → Task " + taskId)
            );
        }

        executor.shutdown();
    }
}


📤 Приклад виводу:

pool-1-thread-1 → Task 1
pool-1-thread-3 → Task 3
pool-1-thread-2 → Task 2
pool-1-thread-1 → Task 4
pool-1-thread-2 → Task 5


🧠 ExecutorService — це “диспетчер потоків”:
створює пул і повторно використовує потоки, замість створювати нові щоразу.

✅ Завдання 12 — Використання Callable + Future

📋 Умова: виконай задачу, яка повертає результат.

import java.util.concurrent.*;

public class FutureDemo {
public static void main(String[] args) throws Exception {
ExecutorService exec = Executors.newSingleThreadExecutor();

        Callable<Integer> task = () -> {
            System.out.println("Calculating...");
            Thread.sleep(1000);
            return 42;
        };

        Future<Integer> result = exec.submit(task);

        System.out.println("Result: " + result.get()); // блокує, поки не готово
        exec.shutdown();
    }
}


📤 Вивід:

Calculating...
Result: 42


🧠 Future.get() — блокує потік до завершення завдання.
Можна також використовувати isDone() для перевірки готовності.

✅ Завдання 13 — Порівняння stream vs parallelStream

📋 Умова: заміряй час обчислення квадратів 10 млн чисел.

import java.util.*;
import java.util.stream.*;

public class CompareStreams {
public static void main(String[] args) {
List<Integer> data = IntStream.rangeClosed(1, 10_000_000).boxed().toList();

        long t1 = System.currentTimeMillis();
        data.stream().map(x -> x * x).toList();
        long t2 = System.currentTimeMillis();

        data.parallelStream().map(x -> x * x).toList();
        long t3 = System.currentTimeMillis();

        System.out.println("stream: " + (t2 - t1) + " ms");
        System.out.println("parallelStream: " + (t3 - t2) + " ms");
    }
}


📤 Типовий результат:

stream: 230 ms  
parallelStream: 90 ms


🧠 parallelStream() розбиває роботу на частини та виконує їх через ForkJoinPool.commonPool.
Але виграш є лише при достатньо великих даних або CPU-bound обчисленнях.

# ✅ Завдання 14 — Обмеження кількості потоків у parallelStream

📋 Умова: зміни розмір ForkJoinPool вручну.

import java.util.concurrent.*;
import java.util.stream.*;

public class CustomPoolDemo {
public static void main(String[] args) throws Exception {
ForkJoinPool customPool = new ForkJoinPool(2);

        customPool.submit(() -> {
            IntStream.rangeClosed(1, 10)
                .parallel()
                .forEach(i -> System.out.println(Thread
                        .currentThread()
                        .getName() + " → " + i)
                );
        }).get();

        customPool.shutdown();
    }
}


📤 Вивід:

ForkJoinPool-1-worker-1 → 1
ForkJoinPool-1-worker-2 → 2
...


🧠 За замовчуванням parallelStream() використовує ForkJoinPool.commonPool,
але можна створити свій пул з обмеженою кількістю потоків (корисно для контролю ресурсів).

✅ Завдання 15 — CompletableFuture (асинхронна композиція)

📋 Умова: зроби дві незалежні задачі, що виконуються паралельно.

import java.util.concurrent.*;

public class CompletableDemo {
public static void main(String[] args) throws Exception {
CompletableFuture<Integer> task1 = CompletableFuture.supplyAsync(() -> {
System.out.println("Task 1");
sleep(500);
return 10;
});

        CompletableFuture<Integer> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 2");
            sleep(300);
            return 20;
        });

        CompletableFuture<Integer> result = task1.thenCombine(task2, Integer::sum);
        System.out.println("Result = " + result.get());
    }

    static void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}


📤 Вивід:

Task 1  
Task 2  
Result = 30


🧠 CompletableFuture — сучасний інструмент для асинхронних обчислень,
де можна комбінувати, ланцюжити та обробляти результати без блокування.

🧩 Підсумок частини 3
Концепція	Клас / Метод	Пояснення
Пул потоків	ExecutorService	ефективне повторне використання потоків
Завдання з результатом	Callable, Future	повертає результат після виконання
Асинхронні завдання	CompletableFuture	комбінує обчислення без блокування
Паралельні стріми	parallelStream()	використовує ForkJoinPool
Кастомна паралельність	ForkJoinPool(n)	контроль кількості потоків
💡 Велике резюме всього модуля
Частина	Тема	Головна ідея
#1	Threads	запуск і керування потоками
#2	Locks & Atomics	потокобезпека, блокування, продуктивність
#3	Parallel & Executor	ефективна паралельна обробка задач

🧠 Після цього модуля ти реально мислиш як системний інженер Java —
розумієш, що відбувається під капотом, коли код виконується одночасно.