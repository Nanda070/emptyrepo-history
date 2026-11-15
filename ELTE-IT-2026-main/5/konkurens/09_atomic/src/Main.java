import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

record PolarCoordinate(double distance, double angle) {
    public PolarCoordinate getMaxByAngle(PolarCoordinate other) {
        return other.angle > this.angle ? other : this;
    }
}

public class Main {
    public static void main(String[] args) {
        // Класс-оболочка, предоставляющий атомарные операции.
        AtomicInteger atomicInteger = new AtomicInteger(0);

        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                while(atomicInteger.get() < 20_000) {
// int j = атомныйInteger.get();
// ++j;
// атомныйInteger.set(j);
                    // вместо этого приращениеAndGet();
                    System.out.println(Thread.currentThread().getName() + "\t" + atomicInteger.incrementAndGet());
                    // он не отображается подряд на входе, но объединение sout + string все портит
                    // получать
                    // набор
                    // приращениеИПолучить
                    // getAndIncrement
                    // добавить и получить
                    // получить и добавить
                    // уменьшитьAndGet
                    // накапливать и получать
                    // здесь значение a=atomicInteger, b=3
                    atomicInteger.accumulateAndGet(3, (a, b) -> {
                        if (a % 2 == 0) {
                            return 2 * a + b;
                        } else {
                            return a-b;
                        }
                    });
                    // updateAndGet
                    atomicInteger.updateAndGet(a -> {
                        if (a % 2 == 0) {
                            return 2 * a + 3;
                        } else {
                            return a-3;
                        }
                    });
                }
            }).start();
        }

        AtomicLong atomicLong = new AtomicLong(0);
        atomicLong.compareAndSet(0L, 2L);

        // если ничего не указано, начальное значение равно нулю
        AtomicReference<PolarCoordinate> polarCoordinateAtomicReference = new AtomicReference<>(new PolarCoordinate(0 , 0));

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                Random r = ThreadLocalRandom.current();
                for (int j = 0; j < 1_000; j++) {
                    PolarCoordinate c = new PolarCoordinate(r.nextDouble(), r.nextDouble() * 360);
// PolarCoordinate сейчас = null;
// PolarCoordinate save = null;
//
// // досрочное выполнение необходимо для того, чтобы нити не запутывались
// // так называемое активное ожидание
// делать {
// сейчас = PolarCoordinateAtomicReference.get();
// save = c.getMaxByAngle(сейчас);
// } while (polarCoordinateAtomicReference.compareAndSet(сейчас, сохраните));
                    // более простое решение: он записывает это повторение за нас
                    System.out.println(
                            polarCoordinateAtomicReference.accumulateAndGet(c, PolarCoordinate::getMaxByAngle)
                    );
                }
            }).start();
        }

        System.out.println("Hello world!");
    }
}