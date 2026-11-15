import java.util.*;

/**
 * Koncertet reprezental. Vannak resztvevoi, akik a fesztivalozok, akik el szeretnenek menni a koncertre. Amikor indul
 * a koncert, ertesiti oket, amikor vege a koncertnek, ujra ertesiti oket.
 */
public class Concert {

    // Структура данных для посетителей фестиваля, желающих купить билеты на концерт
    // Часть 1. Создайте структуру данных, описанную выше (тип — «Посетитель»).
    private final List<Attendee> attendees = Collections.synchronizedList(new LinkedList<>());

    // По истечении этого времени концерт начнется.
    private final int startTime;
    // Продолжительность концерта
    private final int duration;
    private final String name;

    public Concert(final String name, final int startTime, final int duration){
        this.startTime = startTime;
        this.duration = duration;
        this.name = name;
    }

    /**
     * Var, amig a startTime letelik, majd ertesiti a resztvevoket, hogy kezdodik a koncert.
     * Utana var, amig a duration letelik, majd ertesiti a resztvevoket, hogy vege a koncernek
     */
    public void start(){
        // 1. Решение: давайте установим $startTime.
        try {
            Thread.sleep(startTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 1. Рез: Давайте сообщим зрителям, что концерт вот-вот начнется.
        // О: При итерации к синхронизированному списку его необходимо синхронизировать отдельно.
        synchronized (attendees) {
            for (var attendee : attendees) {
                // 1. Рез: и запиши - $attendeeName собирается в $name
                System.out.println(attendee.getName() + " is going to " + this.name);
                // О: для работы вызова notify() необходимо получить блокировку.
                synchronized (attendee) {
                    // О: notify() здесь безопасен, потому что только один поток ожидает данной блокировки.
                    attendee.notify();
                }
            }
        }

        // 1. Решение: давайте изменим $duration
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 1. Рез: Сообщим в рествево, что концерт окончен.
        synchronized (attendees) {
            for (Attendee attendee : attendees) {
                // 1. Ответ: es irjuk ki - $name закончил, $attendeeName собирается на вечеринку
                System.out.println(this.name + " finished, " + attendee.getName() + " is going to party");
                synchronized (attendee) {
                    attendee.notify();
                }
            }
        }
    }

    /**
     * Hozzaad egy fesztivalozot a resztvevokhoz, jelezve, hogy reszt szeretnenek venni a koncerten.
     */
    public void addAttendee(final Attendee attendee){
        // 1. Рез: давайте добавим $attendee в структуру данных, назначенную участникам.
        this.attendees.add(attendee);
    }
}
