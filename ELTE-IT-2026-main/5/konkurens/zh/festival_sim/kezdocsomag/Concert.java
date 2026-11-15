/**
 * Represents a concert. The concert has attendees, which is people who would
 * like to attend the concert. When the concert start, it notifies them,
 * when the concert ends, it notifies them again
 *
 * Koncertet reprezental. Vannak resztvevoi, akik a fesztivalozok, akik el
 * szeretnenek menni a koncertre. Amikor indul a koncert, ertesiti oket,
 * amikor vege a koncertnek, ujra ertesiti oket
 */
public class Concert {


    // Коллекция для людей, которые хотели бы пойти на концерт
    // Структура данных для посетителей фестиваля, желающих купить билеты на концерт
    // TODO Часть 1. Создайте описанную выше коллекцию (тип — Attendee).
    // TODO Часть 1. Создайте структуру данных, описанную выше (тип — «Посетитель»).
    // Сколько времени до начала концерта
    // По истечении этого времени концерт начнется.
    private final int startTime;
    // Продолжительность концерта
    // Продолжительность концерта
    private final int duration;
    private final String name;

    public Concert(final String name, final int startTime, final int duration){
        this.startTime = startTime;
        this.duration = duration;
        this.name = name;
    }

    /**
     * Wait until startTime elapses, then notify attendees that the concert is starting.
     * Wait until duration elapses, then notify attendees that the concert is over
     *
     * Var, amig a startTime letelik, majd ertesiti a resztvevoket, hogy kezdodik a koncert.
     * Utana var, amig a duration letelik, majd ertesiti a resztvevoket, hogy vege a koncernek
     */
    public void start(){

        // TODO Часть 1. Подождите $startTime
        // TODO 1. Решение: давайте установим $startTime

        // ЗАДАЧА Часть 1. Сообщите участникам, что концерт вот-вот начнется.
        // TODO Часть 1.: и распечатайте — $attendeeName перейдет к $name
        // TODO 1st Rez: Сообщим ресствевос, что концерт вот-вот начнется.
        // TODO 1. Рез: и запишите - $attendeeName перейдет к $name


        // TODO Часть 1. Ожидание $duration
        // TODO 1. Решение: добавим $duration


        // ЗАДАЧА Часть 1. Сообщите участникам, что концерт окончен.
        // TODO Часть 1.: и распечатайте — $name закончено, $attendeeName собирается на вечеринку
        // TODO 1st Rez: Сообщим резветам, что концерт окончен
        // TODO 1. Ответ: es irjuk ki - $name закончил, $attendeeName собирается на вечеринку
    }

    /**
     * Adds an attendee to the the attendees collection, meaning they would like to go to the concert
     *
     * Hozzaad egy fesztivalozot a resztvevokhoz, jelezve, hogy reszt szeretnenek venni a koncerten
     * @param attendee
     */
    public void addAttendee(final Attendee attendee){
        // ЗАДАЧА. Часть 1. Добавьте $attendee в коллекцию посетителей.
        // TODO Часть 1. Добавьте $attendee в структуру данных, назначенную участникам.
    }

}
