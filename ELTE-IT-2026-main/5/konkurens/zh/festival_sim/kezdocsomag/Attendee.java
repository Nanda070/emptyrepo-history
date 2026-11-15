/**
 * Represents a person going to the festival.
 * They wait for the concert they would like to attend
 * then attend the concert, after that go to party
 * Part 2.: until the festival is over
 * Part 3.: until the bus can pick them up and go home.
 *
 * Egy fesztivalozot reprezental.
 * Eloszor megvarjak, amig elkezdodik a koncertjuk,
 * utan elmennek arra a koncertre, majd buliznak
 * 2. Resz: addig, ameddig vege a fesztivalnak
 * 3. Resz: addig, ameddig a busz fel tudja venni oket es haza tudnak menni
 */
public class Attendee {

    // Время ожидания до попытки сесть в автобус после неудачной попытки
    // Именно столько они ждут, прежде чем снова попытаться сесть в автобус после неудачной попытки.
    private static final int WAIT_TIME_MSEC = 1000;

    private final String name;

    public Attendee(final String name){
        this.name = name;
    }

    /**
     * Wait for the concert to begin, then invoke goToConcert()
     *
     * Megvarjak, amig elkezdodik a koncert, majd meghivjak a goToConcert() metodust
     */
    public void waitForConcert(){
        // TODO Часть 1.: Дождитесь начала концерта.
        // TODO 1st Rez: Подождите, пока не начнется концерт.

        // TODO Часть 1. После начала концерта вызовите goToConcert().
        // TODO 1. Рез: После начала концерта вызовите метод goToConcert().
    }

    /**
     * Wait until the concert is over then invoke goToParty()
     *
     * Megvarjak, amig vege a koncertnek, majd meghivjak a goToParty() metodust
     */
    public void goToConcert(){
        // TODO Часть 1.: Дождитесь окончания концерта.
        // ЗАДАЧА 1. Рез: тусоваться, пока концерт не закончится

        // TODO Часть 2./3.: После окончания концерта вызовите goToParty().
        // ЗАДАЧА 2./3. Рез: После окончания концерта давайте вызовем метод goToParty().
    }

    /**
     * Part 2: Partying until the festival ends
     * Part 3: Partying until they can get on the bus
     *
     * 2. Resz: Buliznak, amig vege a fesztivalnak
     * 3. Resz: Buliznak, amig fel tudnak szallni a buszra
     */
    public void goToParty(){

        // TODO Часть 2./3.: Распечатка — «$name начал вечеринку»
        // ЗАДАЧА 2./3. Res: Удалить — «$name начал вечеринку»

        // ТОЛЬКО ЧАСТЬ 2 / ТОЛЬКО 2-Я РЕЗ.
        // TODO Часть 2. Каждые $WAIT_TIME_MSEC проверяйте, закончился ли фестиваль, а затем заканчивайте вечеринку.
        // TODO Часть 2.: Совет: вы можете создавать новые переменные и методы, чтобы отслеживать их.
        // TODO Часть 2: Каждые $WAIT_TIME_MSEC мы проверяем, закончился ли фестиваль
        // TODO 2. Рез: если так, то мы остановим вечеринку
        // TODO Часть 2. Совет. Для этого можно создавать новые переменные и методы.
        // ТОЛЬКО ЧАСТЬ 2 / ТОЛЬКО 2-Я РЕЗ.

        // ТОЛЬКО ЧАСТЬ 3 / ТОЛЬКО 3. RES
        // TODO Часть 3. Попробуйте сесть в автобус (Bus::tryToGetOnBus) каждые $WAIT_TIME_MSEC.
        // TODO Часть 3. Если участник смог сесть в автобус, закончите вечеринку и уйдите.
        // TODO Rez 3: Попробуйте сесть в автобус (Bus::tryToGetOnBus) $WAIT_TIME_MSEC
        // TODO Часть 3. Если вам удалось сесть в автобус, остановите вечеринку и выйдите.
        // ТОЛЬКО ЧАСТЬ 3 / ТОЛЬКО 3. RES

    }

    public String getName(){
        return this.name;
    }


}
