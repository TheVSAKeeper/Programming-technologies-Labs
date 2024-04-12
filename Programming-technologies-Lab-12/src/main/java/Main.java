import entities.Item;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PessimisticLockException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

public class Main
{
    private static final int ROWS = 40;
    private static final int THREADS = 16;
    private static final int ITERATIONS = 160_000 / THREADS;
    private static final SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Item.class)
            .buildSessionFactory();
    private static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException
    {
        CountDownLatch countDownLatch = new CountDownLatch(THREADS);

        fillItems();

        long start = System.currentTimeMillis();

        for (int i = 0; i < THREADS; i++)
        {
            int threadNumber = i + 1;
            new Thread(() -> incrementValues(threadNumber, countDownLatch)).start();
        }

        countDownLatch.await();

        long end = System.currentTimeMillis();

        long milliseconds = end - start;

        Date date = new Date(milliseconds);
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss.SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

        String dateFormatted = formatter.format(date);
        System.out.println(dateFormatted);

        showItemsSum();
    }

    private static void fillItems()
    {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        Stream.generate(Item::new).limit(ROWS).forEach(session::persist);

        session.getTransaction().commit();
    }

    private static void incrementValues(int threadNumber, CountDownLatch countDownLatch)
    {
        int pessimisticLockExceptionCount = 0;

        System.out.println("Thread #" + threadNumber + " started");

        for (int i = 0; i < ITERATIONS; i++)
        {
            Session session = factory.getCurrentSession();
            Transaction transaction = session.beginTransaction();

            int randomId = random.nextInt(40) + 1;

            Item item = session.get(Item.class, randomId, LockMode.PESSIMISTIC_WRITE);
            item.increment();

            try
            {
                Thread.sleep(5);
                transaction.commit();
            }
            catch (PessimisticLockException e)
            {
                pessimisticLockExceptionCount++;
                transaction.rollback();
                System.out.println("Thread #" + threadNumber + " rollback");
            }
            catch (InterruptedException e)
            {
                transaction.rollback();
                System.out.println("Thread #" + threadNumber + " rollback");
            }
        }

        System.out.println("Thread #" + threadNumber + " committed.\t" +
                           "Count of PessimisticLockException = " + pessimisticLockExceptionCount);

        countDownLatch.countDown();
    }

    private static void showItemsSum()
    {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        int sum = session.createQuery("FROM Item", Item.class)
                         .setLockMode(LockModeType.PESSIMISTIC_READ)
                         .getResultStream()
                         .mapToInt(Item::getValue)
                         .sum();

        session.getTransaction().commit();

        System.out.println("Required sum = " + (ITERATIONS * THREADS) + ". Resulting sum = " + sum);
    }
}

