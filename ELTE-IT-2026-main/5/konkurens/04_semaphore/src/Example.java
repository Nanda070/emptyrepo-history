import java.util.concurrent.Semaphore;

// железнодорожный семафор
// n разрешение предоставлено, его можно предоставить или отказаться от него
public class Example {
    public static volatile int common = 0;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2, true); // для планирования строит очередь ожидающих потоков

        for (int i = 0; i < 2; i++) {
            int n = i;
            new Thread(() -> {
                while (true) {
                    semaphore.acquireUninterruptibly(2); // запросить разрешение
                    // количество человек можно уточнить
                    try {
                        System.out.println(n + " acquired the permit at: " + common);
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        ++common;
                    } finally {
                        // если поток остановится с ошибкой, лицензия не будет возвращена
                        semaphore.release(2); // возврат разрешения
                    }


                }
            }).start();
        }
    }
}