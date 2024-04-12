package console.examples;

import java.util.Arrays;

public class Example
{
    private static final int SIZE = 50_000_000;
    private static final int HALF = SIZE / 2;

    public static void main(String[] args)
    {
        float[] singleThread = performSingleThread();
        float[] doubleThread = performDoubleThread();
        float[] multiThread = performMultiThread(8);

        System.out.println(Arrays.equals(singleThread, doubleThread));
        System.out.println(Arrays.equals(singleThread, multiThread));

        System.out.println(singleThread[SIZE - 1]);
        System.out.println(doubleThread[SIZE - 1]);
        System.out.println(multiThread[SIZE - 1]);
    }

    private static float[] performMultiThread(int threadCount)
    {
        int size = SIZE;
        float[] array = new float[size];
        Arrays.fill(array, 1);

        long startTime = System.currentTimeMillis();

        while (size % threadCount != 0)
        {
            threadCount++;
        }

        int partSize = size / threadCount;
        System.out.println("New threads count = " + threadCount);

        Thread[] threads = new Thread[threadCount];
        float[][] parts = new float[partSize][threadCount];

        for (int i = 0; i < threadCount; i++)
        {
            float[] arrayPart = new float[partSize];
            System.arraycopy(array, partSize * i, arrayPart, 0, partSize);

            int offset = i * partSize;
            int finalI = i;

            threads[i] = new Thread(() -> {
                for (int j = 0; j < partSize; j++)
                {
                    arrayPart[j] = (float) (arrayPart[j] * Math.sin(0.2f + (j + offset) / 5) * Math.cos(0.2f + (j + offset) / 5) * Math.cos(0.4f + (j + offset) / 2));
                }
                parts[finalI] = arrayPart;
            });

            threads[i].start();
        }

        for (int i = 0; i < threadCount; i++)
        {
            try
            {
                threads[i].join();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            System.arraycopy(parts[i], 0, array, partSize * i, partSize);
        }

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        return array;
    }

    private static float[] performDoubleThread()
    {
        float[] array = new float[SIZE];
        Arrays.fill(array, 1);

        long startTime = System.currentTimeMillis();

        float[] firstHalf = new float[HALF];
        float[] secondHalf = new float[HALF];

        System.arraycopy(array, 0, firstHalf, 0, HALF);
        System.arraycopy(array, HALF, secondHalf, 0, HALF);

        Thread firstHalfCalculation = new Thread(() -> {
            for (int i = 0; i < HALF; i++)
            {
                firstHalf[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread secondHalfCalculation = new Thread(() -> {
            for (int i = 0; i < HALF; i++)
            {
                secondHalf[i] = (float) (array[i] * Math.sin(0.2f + (i + HALF) / 5) * Math.cos(0.2f + (i + HALF) / 5) * Math.cos(0.4f + (i + HALF) / 2));
            }
        });

        firstHalfCalculation.start();
        secondHalfCalculation.start();

        try
        {
            firstHalfCalculation.join();
            secondHalfCalculation.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.arraycopy(firstHalf, 0, array, 0, HALF);
        System.arraycopy(secondHalf, 0, array, HALF, HALF);

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        return array;
    }

    private static float[] performSingleThread()
    {
        float[] array = new float[SIZE];
        Arrays.fill(array, 1);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < SIZE; i++)
        {
            array[i] = (float) (array[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);

        return array;
    }
}