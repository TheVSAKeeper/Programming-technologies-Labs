package console;

public class Main
{
    public static void main(String[] args)
    {
        String[][] fibonacciArray = {
                {"1", "1", "1", "1"},
                {"1", "1", "1", "1"},
                {"1", "8", "1", "1"},
                //{"1", "9", "1", "1"},
        };

        try
        {
            int sum = getSum(fibonacciArray);
            System.out.println("Сумма элементов массива: " + sum);
        }
        catch (MyArraySizeException | MyArrayDataException | MyNoFibonacciException e)
        {
            e.printStackTrace();
        }
    }

    public static int getSum(String[][] array) throws MyArraySizeException, MyArrayDataException, MyNoFibonacciException
    {
        if (array.length != 4 || array[0].length != 4)
        {
            throw new MyArraySizeException();
        }

        int sum = 0;

        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[i].length; j++)
            {
                try
                {
                    int number = Integer.parseInt(array[i][j]);

                    if (number > 1000 || isFibonacci(number) == false)
                    {
                        throw new MyNoFibonacciException(i, j, number);
                    }

                    sum += number;
                }
                catch (NumberFormatException e)
                {
                    throw new MyArrayDataException(i, j);
                }
            }
        }

        return sum;
    }

    public static boolean isFibonacci(int number)
    {
        int n = 5 * number * number;

        return isSquareNumber(n + 4) || isSquareNumber(n - 4);
    }

    public static boolean isSquareNumber(int number)
    {
        double sqrt = Math.sqrt(number);
        int ceil = (int)Math.ceil(sqrt);
        int floor = (int)sqrt;

        return ceil * ceil == floor * floor;
    }
}

