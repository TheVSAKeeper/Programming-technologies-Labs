package console.examples;

public class Example_SB_3
{
    public static void main(String[] args)
    {
        System.out.println("Start");
        new Thread(() -> method()).start();
        new Thread(() -> method()).start();
    }

    public synchronized static void method()
    { // синхронизация по классу
        for (int i = 0; i < 10; i++)
        {
            System.out.println(i);
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
