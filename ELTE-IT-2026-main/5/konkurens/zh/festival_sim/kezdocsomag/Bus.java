/**
 * Singleton class, which means there can only be one of this.
 * When the concerts finished, the bus will begin taking people home,
 * but only has $LIMIT spaces to fill, so people might need to wait
 * until it comes back for them
 *
 * Singleton osztaly, tehat csak egy lehet belole.
 * Amikor a koncertek lementek, a busz elkezdi hazavinni az embereket,
 * viszont csak $LIMIT ferohely van rajta, szoval lehet, hogy varnia kell
 * nehany fesztivalozonak, amig visszajon ertuk a busz
 */
public class Bus {

    // Максимальное количество пассажиров
    // Максимальное количество пассажиров
    private static final int LIMIT = 2;
    // Время, которое он тратит на ожидание посадки пассажиров
    // Именно столько времени нужно пассажирам, чтобы подняться на борт.
    private static final int WAITING_TIME_MSEC = 1000;
    // Время, необходимое автобусу на поездку
    // Именно столько времени нужно, чтобы совершить одну поездку
    private static final int TRIP_TIME_MSEC = 500;

    // Экземпляр синглтона
    // Синглтон вальтозо
    private static Bus instance;
    // Пассажиры автобуса (вместимостью)
    // Пассажиры автобуса (с фиксированными сиденьями)
    // TODO Часть 3. Создайте коллекцию для вышеуказанной цели.
    // TODO 3. Рез: Какая-то структура данных для вышеупомянутой цели
    // Переменная, зависит от того, принимает ли он новых пассажиров или нет.
    // Это зависит от того, принимаете ли вы новых пассажиров или нет.
    // TODO Часть 3. Создайте переменную для указанной выше цели.
    // TODO Часть 3: Некоторые изменения в вышеупомянутой цели

    private Bus(){}

    /**
     * Singleton getter, if we want to get the bus, use:
     * Bus.getInstance()
     *
     * Singleton getter, ha hozza szeretnenk ferni a buszhoz, igy hasznaljuk:
     * Bus.getInstance()
     * @return
     */
    public static Bus getInstance(){
        if(instance == null)
            instance = new Bus();
        return instance;
    }

    /**
     * Starts the bus, so it keeps going back and forth between "home" and
     * the festival. When it's at the festival, it can take new passengers,
     * but while it's on the road, new passengers cannot get on it. Finishes
     * after the first time no passengers want to get on the bus.
     *
     * Elinditja a buszt, oda-vissza megy "haza" es a fesztival kozott.
     * Amikor a fesztivalon van, akkor fel tud venni uj utasokat,
     * de amig uton van, nem szallhat fel uj utas. Leall az elso alkalom utan,
     * hogy nem szallt fel senki, amikor a fesztivalon varakozik az utasokra.
     */
    public void startBus(){
        while(true){
            // TODO Часть 3. Распечатка: автобус теперь принимает пассажиров на сумму LIMIT.
            // TODO Часть 3. Используйте ранее созданную переменную, чтобы указать, что
            // TODO Часть 3.: автобус в данный момент принимает пассажиров.

            // TODO 3. Рез: Удалить — автобус теперь принимает пассажиров на сумму $LIMIT.
            // TODO Часть 3: С помощью только что скачанного модификатора указываем
            // TODO 3. Res: что автобус принимает новых пассажиров

            // TODO Часть 3. Подождите $WAITING_TIME_MSEC
            // TODO 3-е решение: посчитаем $WAITING_TIME_MSEC

            // TODO Часть 3.: Распечатка. Автобус высадит пассажиров.
            // TODO Часть 3. Используйте ранее созданную переменную, чтобы указать, что
            // TODO Часть 3.: автобус в настоящее время *_НЕ_* принимает пассажиров

            // ЗАДАЧА 3. Реш: Ирджук ки — Автобус собирается высадить пассажиров.
            // TODO Часть 3: С помощью только что скачанного модификатора указываем
            // TODO 3. Решение: что автобус *_НЕ_* принимает новых пассажиров

            // TODO Часть 3. Подождите $TRIP_TIME_MSEC
            // TODO 3. Решение: Давайте проверим $TRIP_TIME_MSEC

            // TODO Часть 3.: Если в автобусе не было новых пассажиров, выйдите из цикла
            // TODO Часть 3. Если в автобусе были пассажиры, удалите их и
            // TODO Часть 3.: печать — $passengerName вышел из автобуса и пошел домой
            // TODO 3. Рез: Если ни один пассажир не сел, выйдите из цикла.
            // TODO 3. Рез: Если были новые пассажиры, давайте их высадим и т.д.
            // TODO 3. Рез: запишите - $passengerName вышел из автобуса и пошел домой.

        }
    }

    /**
     * A passenger tries to get on the bus. If the bus is currently
     * not taking any passengers (hasn't started yet or is on the road) return false,
     * otherwise try to fit them in. If the bus has empty seats to fill, and the passenger
     * can get on, return true, if the bus is filled ($LIMIT), so the passenger has no seat,
     * return false.
     *
     * Egy utas megprobal felszallni a buszra. Ha a busz jelenleg nem fogad
     * uj utasokat (nem indult meg el, vagy eppen uton van), terjen vissza false-szal,
     * egyebkent probalja fogadni az utasokat. Ha a busznak van meg ures ulohelye, es fel tud
     * szallni egy uj utas, terjen vissza true-val, ha a busz teli van ($LIMIT), azaz nincs helye
     * az utasnak, terjen vissza false-szal.
     * @param attendee
     * @return
     */
    public boolean tryToGetOnBus(Attendee attendee){

        // TODO Часть 3. Если автобус в данный момент не принимает новых пассажиров
        // TODO Часть 3.: вернуть false
        // TODO Часть 3.: И распечатать — Автобус в настоящее время не принимает пассажиров
        // TODO Часть 3: Если автобус в настоящее время не принимает новых пассажиров,
        // TODO 3. Решение: вернемся к false
        // ЗАДАЧА 3. Решение: Es irjuk ki — Автобус в настоящее время не принимает пассажиров.

        // TODO Часть 3.: Если пассажир (участник) может сесть в автобус, верните true
        // TODO Часть 3.: и распечатайте — $attendeeName сел в автобус
        // TODO Часть 3.: Если пассажир (участник) не смог попасть в автобус, верните false
        // TODO Часть 3.: и распечатайте — $attendeeName не смог сесть в автобус
        // TODO 3. Рез: Если пассажир (присутствующий) смог сесть в автобус, вернитесь с true
        // TODO 3. Рез: и запишите - $attendeeName сел в автобус
        // TODO 3. Рез: Если пассажир (присутствующий) не смог попасть в автобус, вернитесь с ложным
        // TODO 3. Решение: es irjuk ki - $attendeeName не смог сесть в автобус
        return false;
    }

}
