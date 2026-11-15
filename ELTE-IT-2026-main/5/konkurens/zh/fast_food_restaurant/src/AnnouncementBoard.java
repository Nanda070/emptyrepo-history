import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AnnouncementBoard extends Thread {
    // Создайте структуру данных в виде карты, содержащую имена гостей и заказанную ими еду!
    private final ConcurrentMap<String, String> ready = new ConcurrentHashMap<>();

    public void announceReady(String customer, String food) {
        // Введите данные выполненного заказа в структуру данных!
        ready.put(customer, food);
    }

    @Override
    public void run() {
        // Перечислите все выполненные заказы за секунду, пока ресторан открыт и еще не готов.
        // все заказы пропали!
        while(Restaurant.getInstance().isOpen() && ready.size() < Restaurant.CUSTOMER_COUNT) {
            System.out.println("Elkészült rendelések: ");
            ready.forEach((customer, food) -> System.out.println("    " + customer + ": " + food));

            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
