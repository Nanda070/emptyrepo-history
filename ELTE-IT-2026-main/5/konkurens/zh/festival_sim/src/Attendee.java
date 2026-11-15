import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Egy fesztivalozot reprezental.
 * Eloszor megvarjak, amig elkezdodik a koncertjuk,
 * utan elmennek arra a koncertre, majd buliznak
 * 2. Resz: addig, ameddig vege a fesztivalnak
 * 3. Resz: addig, ameddig a busz fel tudja venni oket es haza tudnak menni
 */
public class Attendee {

    // Именно столько они ждут, прежде чем снова попытаться сесть в автобус после неудачной попытки.
    private static final int WAIT_TIME_MSEC = 1000;

    private final String name;

    /**
     * V: Ez a flag jelzi, hogy véget ért a buli, kívülről átbillentjük ha simulator szerint letelt a buli ideje is.
     */
    private final AtomicBoolean isFestivalOver = new AtomicBoolean(false);

    public Attendee(final String name){
        this.name = name;
    }

    /**
     * Megvarjak, amig elkezdodik a koncert, majd meghivjak a goToConcert() metodust
     */
    public void waitForConcert(){
        // 1. Рез: Подожди, пока не начнется концерт.
        // A: Получаем замок этого объекта. Это необходимо для работы вызова wait().
        synchronized (this) {
            try {
                // О: мы снимаем блокировку вашего объекта и ждем получения уведомления
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Шаг 1. После начала концерта вызовите метод goToConcert().
        goToConcert();
    }

    /**
     * Megvarjak, amig vege a koncertnek, majd meghivjak a goToParty() metodust
     */
    public void goToConcert() {
        // 1. Рез: Подожди, пока концерт не закончится.
        // A: Получаем замок этого объекта. Это необходимо для работы вызова wait().
        synchronized (this) {
            try {
                // О: мы снимаем блокировку вашего объекта и ждем получения уведомления
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // О: на данный момент уже пришло уведомление о том, что концерт окончен.

        // 2-й/3-й Рез: После окончания концерта вызовем метод goToParty().

        this.goToParty();
    }

    /**
     * 2. Resz: Buliznak, amig vege a fesztivalnak
     */
    public void goToParty2(){
        // 2-е и 3-е разрешения: Удалить — «$name начал вечеринку»
        System.out.println(this.name + " began partying");

        // 2. Решение: Мы проверяем каждые $WAIT_TIME_MSEC, закончился ли фестиваль.
        // 2. Рез: давайте прекратим вечеринку, если так
        // Часть 2. Совет. Чтобы помочь в этом, вы можете создавать новые переменные и методы.

        // А: ждем флага
        while (!isFestivalOver.get()) {
            try {
                Thread.sleep(WAIT_TIME_MSEC);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 2. Resz: Buliznak, amig vege a fesztivalnak
     * 3. Resz: Buliznak, amig fel tudnak szallni a buszra
     */
    public void goToParty(){
        // 2-е и 3-е разрешения: Удалить — «$name начал вечеринку»
        System.out.println(this.name + " began partying");

        // 3. Решение: Попробуйте сесть в автобус (Bus::tryToGetOnBus) $WAIT_TIME_MSEC

        // A: тест на посадку в автобус
        while (!Bus.getInstance().tryToGetOnBus(this)) {
            try {
                Thread.sleep(WAIT_TIME_MSEC);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        // О: сюда можно попасть только в том случае, если вы уже сели в автобус.

        // 3. Рез: Если вам удалось попасть в автобус, остановите вечеринку и выйдите.
    }

    public String getName(){
        return this.name;
    }

    /**
     * Ends the festival.
     */
    public void endFestival() {
        this.isFestivalOver.set(true);
    }
}
