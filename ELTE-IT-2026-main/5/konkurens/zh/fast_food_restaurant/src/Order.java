import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

// Класс реализует соответствующий интерфейс!
// A: Ему нужно возвращаемое значение, поэтому его можно вызывать.
public class Order implements Callable<String> {
    String customer;
    String food;

    public Order(String customer, String food) {
        this.customer = customer;
        this.food = food;
    }

    @Override
    public String call() {
        // Подождите от 1 до 2 секунд в случайном порядке!
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(1_000, 2_000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Сообщите о выполненном заказе на доске объявлений!
        Restaurant.getInstance().getAnnouncementBoard().announceReady(this.customer, this.food);

        // Верните значение элемента данных еды!
        return this.food;
    }
}
