import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DataMover {
    public static int[] data;
    public static List<Thread> movers;

    private static final Lock lock = new ReentrantLock();
    private static int movingTime;

    public static void move(int threadNumber, int movingTime, int waitingTime) throws InterruptedException {
        Thread.sleep(waitingTime); // уникальное время ожидания

        lock.lock();
        try {
            data[threadNumber] -= threadNumber; // элемент обновления, соответствующий серийному номеру

            System.out.println(
                    "#" + threadNumber + ": data " + threadNumber + " == " + data[threadNumber]
            );

            Thread.sleep(movingTime); // время совместного движения

            int next = (threadNumber + 1) % data.length; // индекс следующего элемента
            data[next] += threadNumber;
            System.out.println("#" + threadNumber + ": data" + next + "->" + data[next]);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            // настройка списка параметров по умолчанию
            args = new String[]{"123", "111", "256", "404"};
        } else if (args.length == 1) {
            // если пришел только 1 параметр, мы не будем запускать поток, мы можем остановить программу
            System.exit(0);
        }

        final int threadCount = args.length - 1;

        // анализ параметров командной строки
        int[] waitingTimes = new int[threadCount];

        try {
            movingTime = Integer.parseInt(args[0]);

            for (int i = 1; i < args.length; i++) {
                waitingTimes[i-1] = Integer.parseInt(args[i]);
            }
        } catch (NumberFormatException e) {
            System.err.println("Hibás bemenet: " + e.getMessage());
            System.exit(1);
        }

        data = new int[threadCount];
        movers = new ArrayList<>(threadCount);

        // создание тем
        for (int i = 0; i < threadCount; i++) {
            int threadNumber = i;
            data[threadNumber] = threadNumber * 1000;

            // время совместного движения

            Thread thread = new Thread(() -> {
                try {
                    for (int k = 0; k < 10; k++) {
                        move(threadNumber, movingTime, waitingTimes[threadNumber]);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });

            thread.setName(String.valueOf(i));
            movers.add(thread);
        }

        for (Thread thread: movers) {
            thread.start();
        }

        try {
            for (Thread thread: movers) {
                thread.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(Arrays.toString(data));
    }
}
