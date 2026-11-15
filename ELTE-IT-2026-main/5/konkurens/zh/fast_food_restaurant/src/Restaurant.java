import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Restaurant {
    public static final int COUNTER_COUNT = 4;
    public static final int QUEUE_LENGTH = 10;
    public static final int CUSTOMER_COUNT = 50;

    private static Restaurant instance = new Restaurant();

    public static Restaurant getInstance() {
        return instance;
    }

    // Создайте элемент данных с именем open, который хранит информацию о том, открыт ресторан или нет!
    private final AtomicBoolean open = new AtomicBoolean(true);

    private final Counter[] counters = new Counter[COUNTER_COUNT];

    // Создайте пул потоков для запуска потоков!
    private final ExecutorService executorService = Executors.newFixedThreadPool(
            CUSTOMER_COUNT + COUNTER_COUNT + 1
    );

    // Уберите комментарий к заданию 4:
     private AnnouncementBoard board = new AnnouncementBoard();

    public void standIntoQueue(Customer customer) throws InterruptedException {
        counters[ThreadLocalRandom.current().nextInt(COUNTER_COUNT)].enqueue(customer);
    }

    public static void main(String[] args) {
        getInstance().setup();
        getInstance().execute();
        getInstance().shutdown();
    }

    // Создайте метод order, который запускает поток типа Order! (Параметры: имя клиента и заказанная еда.)
    public Future<String> order(String customer, String food) {
        return executorService.submit(new Order(customer, food));
    }

    // Уберите комментарий к заданию 4:
     public AnnouncementBoard getAnnouncementBoard() {
        return board;
     }

     public boolean isOpen() {
        // Скажите мне, открыт ли ресторан!
         return open.get();
     }

    private void setup() {
        // Запустите все окна сервера!
        for (int i = 0; i < COUNTER_COUNT; i++) {
            counters[i] = new Counter();
            executorService.submit(counters[i]);
        }

        // Запустите доску объявлений!
        executorService.submit(board);
    }

    private void execute() {
        // Привлеките гостей!
        for (int i = 0; i < CUSTOMER_COUNT; i++) {
            executorService.submit(new Customer());

            // Подождите случайно от 0,5 до 1 секунды, прежде чем предлагать каждому новому гостю!
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1_000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void shutdown() {
        // Подождите секунду, прежде чем закрыть!
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Отключите запуск новых потоков в пуле потоков!
        executorService.shutdown();

        // Закройте ресторан (открытая переменная)!
        open.set(false);

        // Подождите до 10 секунд, пока каждый поток завершится!
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Немедленно остановите пул потоков!
        executorService.shutdownNow();
    }
}
