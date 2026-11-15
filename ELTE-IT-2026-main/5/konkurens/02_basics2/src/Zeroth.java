package my_package;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class Zeroth {
    public static void main(String[] args) throws InterruptedException {
        // Управление датами в Java

        // Система
        long start = System.currentTimeMillis();
        System.out.println("Started at: "+start);
        Thread.sleep(200);
        long end = System.currentTimeMillis();
        System.out.println("Elapsed: "+(end-start));

        // Дата
        Date startDate = new Date();
        System.out.println("Start date: "+startDate);
        Thread.sleep(200);
        Date endDate = new Date();
        // getTime() возвращает его как длинное
        System.out.println("diff date: "+(endDate.getTime()-startDate.getTime()));

        // Мгновенный
        Instant startInstant = Instant.now();
        System.out.println("Start instant: " + startInstant);
        Thread.sleep(200);
        Instant endInstant = Instant.now();
        // Класс Duration предоставляет операции для подсчета времени.
        System.out.println("Diff duration: " + Duration.between(startInstant, endInstant));
    }
}
