package my_package;

public class Third {
    static class Interrupter extends Thread {
        private final Thread toInterrupt;

        Interrupter(Thread toInterrupt) {
            this.toInterrupt = toInterrupt;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // потоки можно прерывать; прерывание не завершает выполнение потока мгновенно, а меняет его флаг прерывания
            // исключением является случай, когда поток ожидает (например, спит) или выполняет операции ввода-вывода (I/O) — тогда выбрасывается InterruptedException
            toInterrupt.interrupt();
        }
    }

    static class ConcatThread extends Thread {
        private static final String MESSAGE = "I am very slow, but at least you can see how I preform, when I do a slow task on a different thread";
        private int count = 0;

        @Override
        public void run() {
            System.out.println("Munka elkezdve");
            String string = "";

            // с помощью метода isInterrupted() можно проверить, был ли поток прерван
            // это позволяет сделать проверку прерывания частью логики управления программой
            for (int i = 0; i < 1_000_000 && !this.isInterrupted(); i++) {
                for (int j = 0; j < ConcatThread.MESSAGE.length() /* && !this.isInterrupted() */; j++) {
                    string += ConcatThread.MESSAGE.charAt(j);
                }
                count++;
            }

            System.out.println("count: " + count);
            System.out.println("length: " + string.length());
            System.out.println("end: " + string.substring(string.length() - 500));
        }
    }



    public static void main(String[] args) {
        Thread writer = new ConcatThread();
        Thread interrupter = new Interrupter(writer);

        writer.start();
        interrupter.start();
    }
}
