package my_package;

import java.util.concurrent.ThreadLocalRandom;

public class Fifth {

    static class Waiter extends Thread {
        public Waiter() {
            this(false);
        }

        public Waiter(boolean isDaemon) {
            this.setDaemon(isDaemon);
        }

        @Override
        public void run() {
            // обычный Random — не лучшее решение
            // если создавать экземпляр Random внутри потока, это неэффективно, а семена (seeds) могут оказаться очень близкими, что снижает случайность
            // использование общего Random в многопоточной среде вызывает конкуренцию за состояние и неэффективно
            // правильное решение: использовать ThreadLocalRandom
            int waitFor = ThreadLocalRandom.current().nextInt(1, 4) * 1000;

            System.out.println(this.threadId() + (this.isDaemon() ? " (daemon) " : "") +  " waiting: " + waitFor + "ms");
            try {
                Thread.sleep(waitFor);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println(this.threadId() + " woke up");
        }
    }


    public static void main(String[] args) {
        Thread w0 = new Waiter(true);
        Thread w1 = new Waiter();
        Thread w2 = new Waiter(true);
        Thread w3 = new Waiter();
        Thread w4 = new Waiter(true);

        w0.start();
        w1.start();
        w2.start();
        w3.start();
        w4.start();

        // JVM продолжает работу до тех пор, пока выполняется хотя бы один обычный (не daemon) поток
    }
}
