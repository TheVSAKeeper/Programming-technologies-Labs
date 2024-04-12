package console;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Car implements Runnable
{
    private static final AtomicInteger winnersCount;
    private static final Map<Integer, Car> winnersTable;
    private static final Lock lock;
    private static CyclicBarrier startBarrier;
    private static CountDownLatch endLatch;
    private static int carsCount;

    static
    {
        carsCount = 0;
        lock = new ReentrantLock();
        winnersCount = new AtomicInteger(0);
        winnersTable = new TreeMap<>();
    }

    private final String name;
    private final Race race;
    private final int speed;

    public Car(Race race, int speed)
    {
        this.race = race;
        this.speed = speed;
        this.name = "Участник #" + ++carsCount;
    }

    public static void setStartBarrier(CyclicBarrier startBarrier)
    {
        Car.startBarrier = startBarrier;
    }

    public static void setEndLatch(CountDownLatch endLatch)
    {
        Car.endLatch = endLatch;
    }

    public static void showWinners(int count)
    {
        for (Map.Entry<Integer, Car> winner : winnersTable.entrySet())
        {
            if (winner.getKey() > count)
            {
                return;
            }

            System.out.println(winner.getValue().name + " победил. Место - " + winner.getKey());
        }
    }

    public String getName()
    {
        return name;
    }

    public int getSpeed()
    {
        return speed;
    }

    @Override
    public void run()
    {
        try
        {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));

            System.out.println(this.name + " готов");
            startBarrier.await();
        }
        catch (InterruptedException | BrokenBarrierException e)
        {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < race.getStages().size(); i++)
        {
            race.getStages().get(i).go(this);
        }

        lock.lock();

        int winnerNumber = winnersCount.incrementAndGet();
        System.out.println(this.name + " закончил гонку");

        endLatch.countDown();

        lock.unlock();

        synchronized (winnersTable)
        {
            winnersTable.put(winnerNumber, this);
        }
    }
}
