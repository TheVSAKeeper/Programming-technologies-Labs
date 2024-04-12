package console;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Main
{
    public static final int CARS_COUNT = 5;

    public static void main(String[] args)
    {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        Race race = new Race(new Road(60), new Tunnel(3), new Road(40));
        Car[] cars = new Car[CARS_COUNT];

        CyclicBarrier startBarrier = new CyclicBarrier(CARS_COUNT, () -> System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!"));
        CountDownLatch endLatch = new CountDownLatch(CARS_COUNT);

        Car.setStartBarrier(startBarrier);
        Car.setEndLatch(endLatch);

        for (int i = 0; i < cars.length; i++)
        {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 100));
        }

        for (Car car : cars)
        {
            new Thread(car).start();
        }

        try
        {
            endLatch.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }

        Car.showWinners(3);
    }
}

