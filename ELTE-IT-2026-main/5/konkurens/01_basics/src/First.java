package my_package;

class First {
    // Thread: высокоуровневый интерфейс для параллельного программирования
    // Thread можно создать многими способами
    public static void main(String[] args) throws InterruptedException {
        // 1. Внедрение (инъекция) Runnable
        // недостаток: при создании мы не получаем доступ к полям данных потока
        Thread a = new Thread(() -> {
            System.out.println("A");
            try {
                // сон как минимум n миллисекунд
                // InterruptedException, если прерывается -> нужен try/catch
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // получение и прерывание текущего потока
                Thread.currentThread().interrupt();
            }
            System.out.println("A выходит!");
        });

        Thread b = new B(a);

        // start запускает выполнение потока
        a.start();
        b.start();
    }

    // 2. Наследование - переопределение метода run()
    // недостаток: мы расходуем наследование (Java поддерживает только одно наследование)
    public static class B extends Thread {
        private final Thread waitFor;

        B(Thread waitFor) {
            this.waitFor = waitFor;
        }

        @Override
        public void run() {
            System.out.println("B");
            try {
                // ожидание завершения данного потока, может прерываться
                waitFor.join();
            } catch (InterruptedException e) {
                this.interrupt();
            }
            System.out.println("B выходит после A");
        }
    }
    // 3. ThreadBuilder, нововведение Java 21
}