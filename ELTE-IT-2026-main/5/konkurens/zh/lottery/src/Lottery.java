import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

// Задачу необходимо решить в файле Lottery.java анонимного пакета, в классе Lottery.
public class Lottery {
    // Имейте постоянный элемент данных уровня класса с именем WINNING_NUMBERS, содержащий выигрышные номера:
    // 13, 42, 444, 999, 2023, 2024, 9999.
    // Это должен быть тип структуры данных, позволяющий легко определить, содержит ли она значение или нет.
    public static final HashSet<Integer> WINNING_NUMBERS = new HashSet<>(List.of(13, 42, 444, 999, 2_023, 2_024, 9_999));

    // Значение THREAD_COUNT может быть произвольным, например. 10.
    public static final int THREAD_COUNT = 10;

    // Создайте статическую переменную с именем HITS типа int, массив, количество элементов THREAD_COUNT, которые являются участниками (потоками)
    // записывает количество попаданий.
    public static final int[] HITS = new int[THREAD_COUNT];

    public static AtomicBoolean isOver = new AtomicBoolean(false);

    public static void main(String[] args) {
        // Запустить THREAD_COUNT тредов. Это значение может быть произвольным, например. 10.
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        ArrayList<Callable<Integer>> tasks = new ArrayList<>(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            // Каждый поток должен иметь порядковый номер от 0 до 9, я думаю, он должен был быть THREAD_COUNT, если нет, то
            // Эту задачу можно решить, разделив остаток на 10, чтобы получилось 0–9.
            int threadIndex = i;
            tasks.add(() -> {
                Thread.currentThread().setName(String.valueOf(threadIndex));

                int correctGuessCount = 0;
                int sleepAmount;
                int guess;
                while(!isOver.get()) {
                    sleepAmount = 1;
                    guess = ThreadLocalRandom.current().nextInt(1, 10_000);

                    if (WINNING_NUMBERS.contains(guess)) {
                        System.out.println("[" + threadIndex + "] Got a hit: " + guess);

                        synchronized (HITS) {
                            HITS[threadIndex]++;
                            correctGuessCount++;
                        }

                        if (correctGuessCount == WINNING_NUMBERS.size()) {
                            isOver.set(true);
                            return threadIndex;
                        }

                        sleepAmount = ThreadLocalRandom.current().nextInt(100, 300);
                    }
                    try {
                        Thread.sleep(sleepAmount);
                    } catch (InterruptedException e) {
                        return null;
                    }
                }

                return null;
            });
        }

        List<Future<Integer>> results = null;
        try {
            results = executorService.invokeAll(tasks);
            executorService.shutdown();
        } catch (InterruptedException e) {
            System.exit(1);
        } finally {
            executorService.shutdownNow();
        }

        Optional<Integer> winner = results.stream().map(integerFuture -> {
            try {
                return integerFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).filter(Objects::nonNull).findFirst();

        winner.ifPresent(integer -> System.out.println("We got a winner: " + integer));
    }
}
