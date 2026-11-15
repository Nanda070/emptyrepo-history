import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// железнодорожный семафор
// n разрешение предоставлено, его можно предоставить или отказаться от него
public class Example2 {
    public static volatile int common = 0;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2, true); // для планирования строит очередь ожидающих потоков

        for (int i = 0; i < 2; i++) {
            int n = i;
            new Thread(() -> {
                while (true) {
                    try {
                        // время ожидания указано
                        if (semaphore.tryAcquire(200L, TimeUnit.SECONDS)) {
                            try {
                                System.out.println(n + " acquired the permit at: " + common);
                                try {
                                    Thread.sleep(250);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                ++common;
                            } finally {
                                semaphore.release();
                            }
                        } else {
                            System.out.println(n + " couldn't get permit at " + common);
                            // dropPermits() для отзыва всех разрешений
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
    }
}