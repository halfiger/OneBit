⚙️ Модуль: IntStream / DoubleStream / LongStream — практична серія (10 завдань)

# ✅ Завдання 1 — Діапазон чисел
        Надрукувати     1234   12345
        за допомогою range & rangeClosed

🎯 Мета: зрозуміти range() vs rangeClosed().

    public void practice1 () {
        IntStream.range(1,5).forEach(System.out::println);
        IntStream.rangeClosed(1,5).forEach(System.out::println);
    }

🧠 Різниця — rangeClosed() включає кінцеве число.


# ✅ Завдання 2 — Сума квадратів

🎯 Мета: використати IntStream без колекцій.

📋 Умова: знайди суму квадратів чисел від 1 до 5.

    public void practice2 () {
        int sum = IntStream.rangeClosed(1,5)
                .map(n->n*n)
                .sum();
    }



## ✅ Завдання 3 — DoubleStream.generate()

🎯 Мета: створити нескінченний потік випадкових чисел і обмежити його 5 одиницями.

    public void practice3 () {
        DoubleStream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

🧠 Генерація без колекцій, але завжди використовуйте .limit()!

## ✅ Завдання 4 — Конвертація між потоками

🎯 Мета: потренуватись mapToInt, mapToLong, asDoubleStream.

Math.round(d) - округляє до найближчого цілого і повертає long, 
тому доводиться додатково приводити до int.

    public void practice4 () {
        int result =DoubleStream.of(1.2, 2.5, 3.8)
                .mapToInt(d->(int) Math.round(d))
                .sum();
    }

## ✅ Завдання 5 — LongStream.range() і factorial

🎯 Мета: тренування reduce() з LongStream.

📋 Умова: обчисли 5! (факторіал числа 5).

    public void practice5 () {
        long factirial = LongStream.rangeClosed(1,5)
                .reduce((a,b)-> a * b).orElse(-1);
        System.out.println(factirial);
    }

✅ Завдання 6 — Знайти середнє

🎯 Мета: робота з average().

    public void practice6 () {
        OptionalDouble avg = IntStream.of(10, 20, 30, 40).average();
        System.out.println(avg.getAsDouble());
    }

✅ Завдання 7 — Конвертація у Stream<String>

🎯 Мета: використати mapToObj() для створення текстового представлення.

📋 Умова: створити рядки типу "Num: X" для кожного елемента.

    public void practice7 () {
        List<String> list = IntStream.rangeClosed(1,3)
                .mapToObj(i-> "Num " + i)
                .toList();
        System.out.println(list);
    } // [Num: 1, Num: 2, Num: 3]

✅ Завдання 8 — Комбінування IntStream і DoubleStream

🎯 Мета: побачити різницю типів.

📋 Умова: згенеруй випадкові double і перетвори їх у int.

    public void practice8() {
        List<Integer> rounded = DoubleStream.generate(Math::random)
                .limit(5)
                .map(d -> d * 10)
                .mapToInt(d -> (int) Math.round(d))
                .boxed()
                .toList();
        System.out.println(rounded);
    }


🧠 Зверни увагу: .mapToInt() → потім .boxed() (бо IntStream не є Stream<Integer>).

✅ Завдання 9 — Робота зі статистикою

🎯 Мета: практика summaryStatistics().

📋 Умова: отримай статистику чисел (мін, макс, середнє).

    public void practice9 () {
        IntSummaryStatistics stats = IntStream.of(2,4,6,8,10)
                .summaryStatistics();
        System.out.println(stats.getAverage());
        System.out.println(stats.getMax());
        System.out.println(stats.getMin());
    }

✅ Завдання 10 — Комбіноване завдання: Random → обробка → результат

📋 Умова:
Згенеруй 10 випадкових чисел типу double.
Перетвори їх у long (округливши).
Поверни середнє цих значень.

    public void practice10 () {
        double avg = DoubleStream.generate(()-> Math.random()*100)
                .limit(10)
                .mapToLong(Math::round)
                .asDoubleStream()
                .average()
                .orElse(0);
        System.out.println(avg);
    }

🧠 Після виконання всіх 10 завдань ви:
повністю відчуєте різницю між Stream<T> і примітивними потоками;
навчитеся перетворювати між типами (Int → Double → Long → Object);
знатимете, як генерувати, змінювати, аналізувати й статистично обробляти числові потоки;
і підготуєтеся до складніших тем (напр., flatMapToInt(), range().boxed(), performance tuning).