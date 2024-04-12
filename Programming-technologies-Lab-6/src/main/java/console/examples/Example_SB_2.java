package console.examples;

public class Example_SB_2
{
    private final Object lock1 = new Object();

    public static void main(String[] args)
    {
        Example_SB_2 e2 = new Example_SB_2();
        System.out.println("Start");
        new Thread(() -> e2.method1()).start();
        new Thread(() -> e2.method1()).start();
    }

    public void method1()
    {
        System.out.println("Block-1 begin");
        for (int i = 0; i < 3; i++)
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
        System.out.println("Block-1 end");
        synchronized (lock1)
        {
            System.out.println("Synch block begin");
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
            System.out.println("Synch block end");
        }
        System.out.println("M2");
    }
}
