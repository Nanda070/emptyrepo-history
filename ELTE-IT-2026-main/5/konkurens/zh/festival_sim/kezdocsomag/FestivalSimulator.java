import java.util.List;

public class FestivalSimulator {

    // Время, отведенное на концерты
    // Время концертов
    private static final int CONCERTS_DURATION_MSEC = 6000;
    // Время, отведенное на вечеринку/возвращение домой
    // Время веселиться и идти домой
    private static final int PARTY_DURATION_MSEC = 8000;
    // Если нужно...
    // Если вам придется...
    private static final int NUMBER_OF_ADDITIONAL_THREADS = 1;

    // Список концертов
    // Список концертов
    private static final List<Concert> concerts = List.of(
            new Concert("Krubi", 2500, 1000),
            new Concert("Wellhello", 2000, 1000),
            new Concert("Halott Penz", 1500, 1000),
            new Concert("Tankcsapda", 2000, 1000),
            new Concert("Byealex", 1500, 1000),
            new Concert("Majka", 2500, 1000)
    );

    // Список участников
    // Список посетителей фестиваля
    private static final List<Attendee> attendees = List.of(
            new Attendee("Olga"),
            new Attendee("Helga"),
            new Attendee("Eszter"),
            new Attendee("Feri"),
            new Attendee("Peti"),
            new Attendee("Laci")
    );

    /**
     * 1. Invoke setup() method to assign attendees to concerts
     * 1. Hivjuk meg a setup() metodust, hogy hozzarendeljuk a fesztivalozokat a koncertekhez
     *
     * 2. Invoke the attendees' waitForConcert() method to start waiting for their concert
     *      (on a separate thread)
     * 2. Hivjuk meg a fesztivalozok waitForConcert() metodusat, hogy varakozzanak a koncertjukre
     *      (egy kulon szalon)
     *
     * 3. Invoke the concerts' start() method to get ready to perform
     *      (on a separate thread)
     * 3. Hivjuk meg a koncertek start() metodusat, hogy elkeszuljenek
     *      (egy kulon szalon)
     *
     * 4. Wait $CONCERT_DURATION_MSEC
     * 4. Varjunk $CONCERT_DURATION_MSEC-et
     *
     * ONLY Part 2./CSAK A 2. Resz:
     * 5a. After $CONCERT_DURATION_MSEC let the attendees know that the festival is over
     * 5a. $CONCERT_DURATION_MSEC utan adjuk a resztvevok tudtara, hogy vege a fesztivalnak
     *
     * ONLY Part 3./CSAK A 3. Resz:
     * 5b. Start the bus via the startBus() method (on a separate thread)
     * 5b. Inditsuk el a buszt a startBus() metodussal (egy kulon szalon)
     *
     * 6. Make any necessary adjustment so that the program terminates after
     *      $PARTY_DURATION_MSEC at the longest, but possible before, if it can
     * 6. Fejezzuk be ugy a kodot, hogy a program maximum $PARTY_DURATION_MSEC
     *      ido utan terminaljon, de ha lehetseges, ne varjuk ki mindenkepp ezt az idot
     * @param args
     */
    public static void main(String[] args) {
        // TODO Часть 1.: Вызов setup()
        // TODO 1. Решение: вызов функции setup().

        // TODO Часть 1. Вызовите waitForConcert() для каждого участника в отдельном потоке.
        // TODO Часть 1. Вызовите метод waitForConcert() для всех посетителей концертов в отдельном салоне.

        // TODO Часть 1. Вызов start() для каждого концерта в отдельном потоке
        // TODO 1. Рез: Давайте вызывать метод start() для каждого концерта в салоне Кулон.

        // TODO Часть 1. Подождите $CONCERTS_DURATION_MSEC.
        // TODO 1-е разрешение: добавим $CONCERTS_DURATION_MSEC

        // TODO Часть 2. ТОЛЬКО: сообщите участникам, что фестиваль окончен.
        // ТОЛЬКО ТОЛЬКО ЧАСТЬ 2: Сообщите посетителям фестиваля, что фестиваль окончен.

        // TODO Часть 3. ТОЛЬКО: Запустите шину (Bus::startBus) в отдельном потоке.
        // TODO 3. ТОЛЬКО ЧАСТЬ: Заведем автобус (Bus::startBus) в отдельный салон

        // TODO Часть 1. Подождите $PARTY_DURATION_MSEC максимально долго, но желательно меньше.
        // TODO Часть 1.: затем завершите симуляцию
        // TODO 1. Рез: Давайте максимально увеличить $PARTY_DURATION_MSEC, но желательно меньше,
        // TODO 1. Рез: затем отключить симуляцию

    }

    /**
     * Sophisticated algorithm to assign people to concerts
     *
     * Szofisztikalt algoritmus, ami hozzarendeli a fesztivalozokat a koncertekhez
     */
    private static void setup(){
        for(int i = 0; i < concerts.size(); ++i){
            concerts.get(i).addAttendee(attendees.get(i));
        }


    }
}
