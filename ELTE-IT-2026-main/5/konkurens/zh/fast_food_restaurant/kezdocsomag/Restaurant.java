import java.util.concurrent.ThreadLocalRandom;

public class Restaurant {
    public static final int COUNTER_COUNT = 4;
    public static final int QUEUE_LENGTH = 10;
    public static final int CUSTOMER_COUNT = 50;

    private static Restaurant instance = new Restaurant();

    public static Restaurant getInstance() {
        return instance;
    }

    // ЗАДАЧА: Создайте элемент данных open, в котором будет храниться информация о том, открыт ли ресторан!

    private Counter[] counters = new Counter[COUNTER_COUNT];

    // ЗАДАЧА: Создайте пул потоков для запуска потоков!

    // Уберите комментарий к заданию 4:
    // частная доска AnnouncementBoard = новая AnnouncementBoard();

    public void standIntoQueue(Customer customer) throws InterruptedException {
        counters[ThreadLocalRandom.current().nextInt(COUNTER_COUNT)].enqueue(customer);
    }

    public static void main(String[] args) {
        getInstance().setup();
        getInstance().execute();
        getInstance().shutdown();
    }

    // TODO3: Создайте метод под названием order, который запускает поток типа Order! (Параметры: имя клиента и заказанная еда.)

    // Уберите комментарий к заданию 4:
    // общественный AnnouncementBoard getAnnouncementBoard() {
    // возвратная доска;
    // }

    // Уберите пометку комментария к основной задаче:
    // общедоступное логическое значение isOpen() {
        // TODO: Верните, открыт ли ресторан!
    // }

    private void setup() {
        // TODO: Запустите все окна сервера!
        // TODO4: Запустите доску объявлений!
    }

    private void execute() {
        // TODO: Запустите гостей! Подождите случайно от 0,5 до 1 секунды, прежде чем предлагать каждому новому гостю!
    }

    private void shutdown() {
        // TODO: Подождите секунду перед закрытием!
        // TODO: Отключите запуск новых потоков в пуле потоков!
        // ЗАДАЧА: Закройте ресторан (открытая переменная)!
        // TODO: Подождите до 10 секунд, пока каждый поток завершится!
        // TODO: Немедленно остановите пул потоков!
    }
}