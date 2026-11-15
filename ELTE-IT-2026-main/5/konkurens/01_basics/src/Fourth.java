package my_package;

import java.util.Random;

public class Fourth {
    public static void main(String[] args) {
        // зачем упаковывать в массив?
        // в лямбде мы не можем переопределять (изменять) локальные переменные
        // кратко: лямбда хочет захватить локальную переменную, но если она не (effectively) final, то не сможет
        // этот трюк делает переменную effectively final (ссылка на массив не меняется, хотя содержимое массива можно изменять)
        Object[] array = {null};

        Thread a = new Thread(() -> {
            while (array[0] == null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("Nincs itt semmi");
            }
            System.out.println("Az érték: " + array[0]);
        });

        Thread b = new Thread(() -> {
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(2, 6) * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            array[0] = 42;
        });

        a.start();
        b.start();
    }
}
