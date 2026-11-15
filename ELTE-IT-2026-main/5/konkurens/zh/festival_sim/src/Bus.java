import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Singleton osztaly, tehat csak egy lehet belole. Amikor a koncertek lementek, a busz elkezdi hazavinni az embereket,
 * viszont csak $LIMIT ferohely van rajta, szoval lehet, hogy varnia kell nehany fesztivalozonak, amig visszajon ertuk a
 * busz.
 */
public class Bus {

    // Максимальное количество пассажиров
    private static final int LIMIT = 2;

    // Именно столько времени нужно пассажирам, чтобы подняться на борт.
    private static final int WAITING_TIME_MSEC = 1000;

    // Именно столько времени нужно, чтобы совершить одну поездку
    private static final int TRIP_TIME_MSEC = 500;

    // Пассажиры автобуса (с фиксированными сиденьями)
    // 3. Рез: некоторая структура данных для вышеупомянутой цели.
    // О: много удалений, индексация не нужна -> цепочка, индексация не нужна, но конкуренция есть -> LinkedBlockingQueue
    // A: скажем, здесь ArrayBlockingQueue действительно хорош
    private static final BlockingQueue<Attendee> passengers = new LinkedBlockingQueue<>(LIMIT);

    // Это зависит от того, принимаете ли вы новых пассажиров или нет.
    // 3. Часть: Некоторые изменения в вышеупомянутой цели.
    // О: флажок приема пассажиров переворачивается через определенные промежутки времени.
    // О: Я не уверен, что вам нужно время для Atomic.
    private static final AtomicBoolean isTakingPassengers = new AtomicBoolean(false);

    // Синглтон вальтозо
    private static Bus instance;

    private Bus() {
    }

    /**
     * Singleton getter, ha hozza szeretnenk ferni a buszhoz, igy hasznaljuk: Bus.getInstance()
     */
    public static Bus getInstance() {
        if (instance == null) {
            instance = new Bus();
        }
        return instance;
    }

    /**
     * Elinditja a buszt, oda-vissza megy "haza" es a fesztival kozott. Amikor a fesztivalon van, akkor fel tud venni uj
     * utasokat, de amig uton van, nem szallhat fel uj utas. Leall az elso alkalom utan, hogy nem szallt fel senki,
     * amikor a fesztivalon varakozik az utasokra.
     */
    public void startBus() {
        while (true) {
            // 3. Рез: Удалить — автобус теперь принимает пассажиров на сумму LIMIT.
            System.out.println("Bus is now taking " + LIMIT + " passangers.");

            // Часть 3: С помощью только что снятого переключателя указываем, что автобус принимает новых пассажиров.
            isTakingPassengers.set(true);

            // 3. Решение: дождитесь $WAITING_TIME_MSEC.
            try {
                Thread.sleep(WAITING_TIME_MSEC);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 3. Реш: Ирджук ки - Автобус собирается высадить пассажиров.
            System.out.println("Bus is going to drop passangers off");

            // Часть 3: С помощью только что скачанного чейнджера указываем, что автобус *_НЕ_* принимает новых пассажиров
            isTakingPassengers.set(false);

            // 3. Решение: посчитаем $TRIP_TIME_MSEC.
            try {
                Thread.sleep(TRIP_TIME_MSEC);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 3-й Рез: Если пассажиры не сели, выходим из цикла.
            if (passengers.isEmpty()) {
                break;
            } else {
                // 3. Рез: Если были новые пассажиры, давайте их высадим и т.д.
                // irjuk ki - $passengerName вышел из автобуса и пошел домой

                // О: в принципе, ни одна другая ветка с этим связываться не может, но это совершенно правильно
                while (!passengers.isEmpty()) {
                    System.out.println(passengers.remove().getName() + " got off the bus, going home");
                }
            }
        }
    }

    /**
     * Egy utas megprobal felszallni a buszra. Ha a busz jelenleg nem fogad uj utasokat (nem indult meg el, vagy eppen
     * uton van), terjen vissza false-szal, egyebkent probalja fogadni az utasokat. Ha a busznak van meg ures ulohelye,
     * es fel tud szallni egy uj utas, terjen vissza true-val, ha a busz teli van ($LIMIT), azaz nincs helye az utasnak,
     * terjen vissza false-szal.
     */
    public boolean tryToGetOnBus(Attendee attendee) {
        // 3. Рез: Если автобус в данный момент не принимает новых пассажиров,
        if (!isTakingPassengers.get()) {
            // 3. Решение: вернемся к ложному
            // 3. Реш: Es irjuk ki - Автобус в данный момент пассажиров не принимает.
            System.out.println("The bus is not taking passengers currently.");
            return false;
        }

        // 3. Рез: Если пассажир (присутствующий) смог попасть в автобус, вернитесь с истинным
        // 3. Рез: и запиши - $attendeeName сел в автобус

        // О: предложение ложное, если места не оказалось, то пассажир продолжает ждать
        if (passengers.offer(attendee)) {
            System.out.println(attendee.getName() + " got on the bus");
            return true;
        }

        // 3. Рез: Если пассажир (присутствующий) не смог попасть в автобус, вернуться с ложным
        // 3. Ответ: es irjuk ki - $attendeeName не смог сесть в автобус
        System.out.println(attendee.getName() + " could not get on the bus");
        return false;
    }
}
