package my_package;

public class Second {
    public static void main(String[] args) {
        // Thread.activeCount() возвращает количество активных потоков только в текущей группе (threadgroup)
        // в нашем случае все потоки попадают в одну и ту же группу

        for (int i = 0; i < 1000; i++) {
            // анонимный класс
            // потокам можно дать имя с помощью конструктора или метода setName()
            Thread thread = new Thread("sz-" + i) {
                @Override
                public void run() {
                    System.out.println("Név: " + this.getName() + " él még: " + Thread.activeCount());
                }
            };
            thread.start();
        }
    }
}
