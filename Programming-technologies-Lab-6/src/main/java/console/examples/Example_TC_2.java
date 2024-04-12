package console.examples;

public class Example_TC_2
{
    public static void main(String[] args)
    {
        new MyThread().start();
    }

    static class MyThread extends Thread
    {
        @Override
        public void run()
        {
            for (int i = 0; i < 10; i++)
            {
                try
                {
                    Thread.sleep(100);
                    System.out.println("new thread: " + i);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
