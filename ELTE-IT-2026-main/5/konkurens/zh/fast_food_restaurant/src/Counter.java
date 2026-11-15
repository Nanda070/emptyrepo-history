import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Counter extends Thread {
    private static int MAX_ID = 0;
    private int id = ++MAX_ID;

    // Создайте очередь для гостей!
    private final BlockingQueue<Customer> queue = new LinkedBlockingQueue<>(Restaurant.QUEUE_LENGTH);

    @Override
    public void run() {
        // Пока ресторан открыт и ресторан не пуст, сделайте следующее:
        while (Restaurant.getInstance().isOpen()) {
            Customer customer;
            try {
                // Попробуйте вывести гостя из очереди, но подождите всего 1 секунду!
                customer = queue.poll(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Если в очереди был покупатель, спросите его, что он хочет заказать:
            if (customer == null) {
                continue;
            }
            System.out.println(id + ". eladó: Jó napot! Mit tehetek Önért, kedves " + customer.getName() + "?");

            // Подождите 0,1 секунды, чтобы избежать раннего пробуждения!
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // Разбуди гостя!
            customer.takeOrder();

            System.out.println(id + ". eladó: is closed.");
        }

    }

    public void enqueue(Customer customer) throws InterruptedException {
        this.queue.put(customer);
    }
}
